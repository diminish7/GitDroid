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
	// Make this static so it doesn't reload every time
	private static List<Event> receivedEvents = new ArrayList<Event>();
	private Integer page;
	private static Long lastQueried;
	
	private EventsAdapter adapter;
	
	private Timer requeryTimer;
	private static final long REQUERY_PERIOD = 1000*60*5;	// 5 Minutes
	
	private static final int SHOW_SPINNER_MESSAGE = 1;
	private static final int HIDE_SPINNER_MESSAGE = 2;
	private static final int SET_EVENT_LIST_MESSAGE = 3;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.adapter = new EventsAdapter(getActivity(), android.R.layout.simple_list_item_1, getEvents());
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
		return (getEvents() != null && !getEvents().isEmpty()) || requeryTimer != null;
	}
	
	@Override
	protected void initializeView() {
		hideSpinner(R.id.news_feed_container);
	}
	
	public void retrieveEvents() {
		receivedEvents = getEventServiceInstance().retrieveReceivedEvents(getPage());
	}
	
	// Getters and Setters
	public List<Event> getEvents() {
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
    		case SHOW_SPINNER_MESSAGE:
    			showSpinner(R.id.news_feed_container);
    			break;
    		case HIDE_SPINNER_MESSAGE:
    			hideSpinner(R.id.news_feed_container);
    			break;
    		case SET_EVENT_LIST_MESSAGE:
    			adapter.setEvents(getEvents());
				adapter.notifyDataSetChanged();
				break;
    		}
    	}
    };
    
	/**
	 * Timer task for requerying the event feed every 5 minutes while the activity is running 
	 */
	private class PeriodicRequeryTask extends TimerTask {
		@Override
		public void run() {
			// Show the spinner
			handler.sendEmptyMessage(SHOW_SPINNER_MESSAGE);
			// Query the event feed
			if (getActivity() != null) { // In case this gets called after the activity is detached
				retrieveEvents();
				// Download avatar images for each event
				retrieveAvatarDrawables(getEvents());
				handler.sendEmptyMessage(SET_EVENT_LIST_MESSAGE);
				lastQueried = Calendar.getInstance().getTimeInMillis();
			}
			
			// Hide the spinner
			handler.sendEmptyMessage(HIDE_SPINNER_MESSAGE);
		}
		
		private void retrieveAvatarDrawables(List<Event> events) {
			Map<String, List<Event>> eventsByLogin = new TreeMap<String, List<Event>>();
			// Group receivedEvents by user so just one call per user
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
}
