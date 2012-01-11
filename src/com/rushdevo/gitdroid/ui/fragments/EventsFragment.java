package com.rushdevo.gitdroid.ui.fragments;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rushdevo.gitdroid.R;
import com.rushdevo.gitdroid.github.v3.models.Event;
import com.rushdevo.gitdroid.github.v3.models.User;
import com.rushdevo.gitdroid.github.v3.services.EventService;
import com.rushdevo.gitdroid.ui.EventsAdapter;
import com.rushdevo.gitdroid.utils.ErrorDisplay;

/**
 * @author jasonrush
 * Display fragment for news feed content
 */
public class EventsFragment extends BaseFragment {
	private EventService service;
	private static List<Event> receivedEvents = new ArrayList<Event>();
	private Integer page;
	private static Long lastQueried;
	
	private EventsAdapter adapter;
	
	private Timer requeryTimer;
	private static final long REQUERY_PERIOD = 1000*60*5;	// 5 Minutes
	
	private static final int REQUERY_MESSAGE = 1;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.adapter = new EventsAdapter(getActivity(), android.R.layout.simple_list_item_1, receivedEvents);
		setListAdapter(adapter);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		if (lastQueried != null) {
			// Reset the requery timer
			// Requery every REQUERY_PERIOD
			// Start the initial requery based on how long we've been paused for
			Long current = Calendar.getInstance().getTimeInMillis();
			Long diff = current - lastQueried;
			Long nextQuery = REQUERY_PERIOD - diff;
			if (nextQuery < 0) nextQuery = 0L;
			requeryTimer = new Timer();
			requeryTimer.schedule(new PeriodicRequeryTask(), nextQuery, REQUERY_PERIOD);
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.events, container, false);
	}
	
	@Override
	public void onPause() {
		super.onPause();
		// Don't fire the requery timer in the background
		if (requeryTimer != null) {
			requeryTimer.cancel();
			requeryTimer = null;
		}
	}
	
	@Override
	protected void initializeData() {
		if (viewIsReady()) initializeView();
		else {
			if (requeryTimer == null) requeryTimer = new Timer();
			requeryTimer.schedule(new PeriodicRequeryTask(), 0, REQUERY_PERIOD);
		}
	}
	
	@Override
	protected boolean viewIsReady() {
		return (receivedEvents != null && !receivedEvents.isEmpty()) || requeryTimer != null;
	}
	
	@Override
	protected void initializeView() {
		hideSpinner(R.id.news_feed_container);
	}
	
	// Getters and Setters
	public List<Event> getReceivedEvents() {
		return receivedEvents;
	}
	
	public Integer getPage() {
		if (page == null) page = 1;
		return page;
	}
	
	public EventService getEventServiceInstance() {
		if (service == null) service = new EventService(getActivity());
		return service;
	}
	
	///////////// INNER CLASSES ////////////////////////
	/**
	 * Handler for executing UI tasks from within a TimerTask
	 */
	private Handler handler = new Handler() {
    	@Override
    	public void handleMessage(Message message) {
    		switch(message.what) {
    		case REQUERY_MESSAGE:
    			// Time to requery the feed
    			new RetrieveFeedTask().execute();
        		break;
    		}
    	}
    };
    
	/**
	 * Async task for passing the temp code to github to get the access token back
	 */
	private class RetrieveFeedTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			showSpinner(R.id.news_feed_container);
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			// Retrieve events
			if (getActivity() != null) { // In case this gets called after the activity is detached
				receivedEvents = getEventServiceInstance().retrieveReceivedEvents(getPage());
				// Download avatar images for each event
				retrieveAvatarDrawables(receivedEvents);
				adapter.setEvents(receivedEvents);
				adapter.notifyDataSetChanged();
				lastQueried = Calendar.getInstance().getTimeInMillis();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// Initialize the view
			getInitHandler().sendEmptyMessage(INIT_VIEW_MESSAGE);
		}
		
		private void retrieveAvatarDrawables(List<Event> events) {
			Map<String, List<Event>> eventsByLogin = new TreeMap<String, List<Event>>();
			// Group events by user so just one call per user
			for (Event event : events) {
				User actor = event.getActor();
				if (actor == null) continue;	// Shouldn't happen
				String login = actor.getLogin();
				if (login == null) continue;	// Shouldn't happen
				if (!eventsByLogin.containsKey(login))
					eventsByLogin.put(login, new ArrayList<Event>());
				eventsByLogin.get(login).add(event);
			}
			// Now for each unique login, grab the avatar drawable and set it up on the event actors
			for (String login : eventsByLogin.keySet()) {
				List<Event> userEvents = eventsByLogin.get(login);
				if (!userEvents.isEmpty()) {
					User user = userEvents.get(0).getActor();
					if (user == null) continue;	// Shouldn't happen
					String avatarUrl = user.getAvatarOrGravatarUrl();
					if (avatarUrl == null) continue; // Shouldn't happen
					Drawable avatar = null;
					URL url;
					InputStream is;
					try {
						url = new URL(avatarUrl);
						is = (InputStream)url.getContent();
						avatar = Drawable.createFromStream(is, user.getName());
					} catch (MalformedURLException e) {
						ErrorDisplay.debug(this, e);
					} catch (IOException e) {
						ErrorDisplay.debug(this, e);
					}
					if (avatar == null) avatar = getResources().getDrawable(R.drawable.default_avatar);
					for (Event event : userEvents) {
						User actor = event.getActor();
						if (actor == null) continue; 	// Shouldn't happen
						actor.setAvatar(avatar);
					}
				}
			}
		}
	}

	/**
	 * Timer task for requerying the event feed every 5 minutes while the activity is running 
	 */
	private class PeriodicRequeryTask extends TimerTask {
		@Override
		public void run() {
			// Run the retrieve-feed task
			handler.sendEmptyMessage(REQUERY_MESSAGE);
		}
	}
}
