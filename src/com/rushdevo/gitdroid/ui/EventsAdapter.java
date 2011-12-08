/**
 * 
 */
package com.rushdevo.gitdroid.ui;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.rushdevo.gitdroid.github.v3.models.Event;

/**
 * @author jasonrush
 * Custom adapter for showing events in a list
 */
public class EventsAdapter extends ArrayAdapter<Event> {
	private List<Event> events;
	private Context ctx;
	
	/**
	 * Constructor
	 * @param context
	 * @param textViewResourceId
	 * @param events
	 */
	public EventsAdapter(Context context, int textViewResourceId, List<Event> events) {
		super(context, textViewResourceId, events);
		this.events = events;
		this.ctx = context;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		EventView view;
		Event event = events.get(position);
		if (convertView == null) {
			view = new EventView(ctx, event);
		} else {
			view = (EventView)convertView;
			if (view.getEvent() != event) {
				view.setEvent(event);
				view.invalidate();
			}
		}
		return view;
	};
	
	// Getters and Setters
	public void setEvents(List<Event> events) {
		this.events = events;
		clear();
		for (Event event : events) {
			add(event);
		}
	}
	
}
