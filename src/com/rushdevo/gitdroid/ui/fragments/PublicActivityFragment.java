/**
 * 
 */
package com.rushdevo.gitdroid.ui.fragments;

import java.util.ArrayList;
import java.util.List;

import com.rushdevo.gitdroid.github.v3.models.Event;


/**
 * @author jasonrush
 * Display fragment for public activity
 * Note that this is exactly the same as the events fragment except it is different events
 */
public class PublicActivityFragment extends EventsFragment {
	// Make this static so it doesn't reload every time
	private static List<Event> publicEvents = new ArrayList<Event>();
	
	@Override
	public void retrieveEvents() {
		publicEvents = getEventServiceInstance().retrievePublicEvents(getPage());
	}
	
	@Override
	public List<Event> getEvents() {
		return publicEvents;
	}
	
}
