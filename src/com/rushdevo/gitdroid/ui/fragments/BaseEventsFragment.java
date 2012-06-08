package com.rushdevo.gitdroid.ui.fragments;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rushdevo.gitdroid.R;
import com.rushdevo.gitdroid.github.v3.models.Event;
import com.rushdevo.gitdroid.github.v3.models.User;
import com.rushdevo.gitdroid.github.v3.services.EventService;
import com.rushdevo.gitdroid.ui.EventsAdapter;
import com.rushdevo.gitdroid.utils.NonConfigurationChangeData;
import com.rushdevo.gitdroid.utils.UserAvatarHelper;
import com.rushdevo.gitdroid.utils.UserAvatarHelperImpl;

/**
 * @author jasonrush
 * Base fragment for viewing events
 */
public abstract class BaseEventsFragment extends BaseListFragment {
	private EventService service;
	
	private EventsAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.adapter = new EventsAdapter(getActivity(), android.R.layout.simple_list_item_1, getEvents());
		setListAdapter(adapter);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.events, container, false);
	}
	
	@Override
	protected void initializeData() {
		if (viewIsReady()) initializeView();
		else {
			new QueryEventsTask().execute();
		}
	}
	
	@Override
	protected boolean viewIsReady() {
		return getEvents() != null && !getEvents().isEmpty();
	}
	
	@Override
	protected void initializeView() {
		hideSpinner(R.id.news_feed_container);
	}
	
	@Override
	public Object onRetainCustomNonConfigurationInstance() {
		return new NonConfigurationChangeData(this, getEvents());
	}
	
	@Override
	protected void handleRefresh() {
		new QueryEventsTask().execute();
	}
	
	public abstract void retrieveEvents();
	
	// Getters and Setters
	public abstract List<Event> getEvents();
	
	public abstract Long getLastQueried();
	
	public abstract void setLastQueried(Long lastQueried);
	
	public EventService getEventServiceInstance() {
		if (service == null) service = new EventService(getActivity());
		return service;
	}
	
	public Integer getPage() {
		// TODO: Stubbed for future pagination
		return 1;
	}
	
	///////////// INNER CLASSES ////////////////////////
	private class QueryEventsTask extends AsyncTask<Void, Void, Void> {
		protected void onPreExecute() {
			showSpinner(R.id.news_feed_container);
		}
		
	     protected Void doInBackground(Void... args) {
	    	 retrieveEvents();
	    	 // Download avatar images for each event
	    	 retrieveAvatarDrawables(getEvents());
	    	 return null;
	     }

	     protected void onPostExecute(Void arg) {
	    	 adapter.setEvents(getEvents());
	    	 adapter.notifyDataSetChanged();
	    	 hideSpinner(R.id.news_feed_container);
	     }
	     
	     private void retrieveAvatarDrawables(List<Event> events) {
	    	UserAvatarHelper userAvatarHelper = new UserAvatarHelperImpl();
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
					Drawable avatar = userAvatarHelper.getAvatarForUserAsDrawable(user, getDefaultAvatar());
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
