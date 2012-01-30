package com.rushdevo.gitdroid.ui.fragments;

import java.util.ArrayList;
import java.util.List;

import com.rushdevo.gitdroid.github.v3.models.Event;

/**
 * @author jasonrush
 * Display fragment for news feed content
 */
public class ReceivedEventsFragment extends BaseEventsFragment {
	// Make this static so it doesn't reload every time
	private static List<Event> receivedEvents = new ArrayList<Event>();
	private static Long lastQueried;
	
	@Override
	public void retrieveEvents() {
		receivedEvents = getEventServiceInstance().retrieveReceivedEvents(getPage());
	}
	
	@Override
	public List<Event> getEvents() {
		return receivedEvents;
	}
	
	@Override
	public Long getLastQueried() {
		return lastQueried;
	}
	
	@Override
	public void setLastQueried(Long timestamp) {
		lastQueried = timestamp;
	}
}
