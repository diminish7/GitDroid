package com.rushdevo.gitdroid.ui.fragments;

import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rushdevo.gitdroid.R;
import com.rushdevo.gitdroid.github.v3.models.Event;
import com.rushdevo.gitdroid.github.v3.services.EventService;
import com.rushdevo.gitdroid.ui.EventsAdapter;

/**
 * @author jasonrush
 * Display fragment for news feed content
 */
public class EventsFragment extends BaseFragment {
	private EventService service;
	private List<Event> receivedEvents;
	private boolean haveRetrievedEvents = false;
	private Integer page;
	
	private EventsAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.receivedEvents = new ArrayList<Event>();
		haveRetrievedEvents = false;
		this.adapter = new EventsAdapter(getActivity(), android.R.layout.simple_list_item_1, receivedEvents);
		setListAdapter(adapter);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.events, container, false);
	}
	
	@Override
	protected void initializeData() {
		if (viewIsReady()) initializeView(); 
		else new RetrieveFeedTask().execute();
	}
	
	@Override
	protected boolean viewIsReady() {
		return (receivedEvents != null && !receivedEvents.isEmpty()) || haveRetrievedEvents;
	}
	
	@Override
	protected void initializeView() {
		hideSpinner(R.id.news_feed_container);
	}
	
	// Getters and Setters
	public List<Event> getReceivedEvents() {
		return this.receivedEvents;
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
	 * Async task for passing the temp code to github to get the access token back
	 */
	private class RetrieveFeedTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			showSpinner(R.id.news_feed_container);
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			receivedEvents = getEventServiceInstance().retrieveReceivedEvents(getPage());
			adapter.setEvents(receivedEvents);
			adapter.notifyDataSetChanged();
			haveRetrievedEvents = true;
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// Initialize the view
			getInitHandler().sendEmptyMessage(INIT_VIEW_MESSAGE);
		}
		
	}
}
