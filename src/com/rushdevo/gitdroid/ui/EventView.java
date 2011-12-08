package com.rushdevo.gitdroid.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rushdevo.gitdroid.R;
import com.rushdevo.gitdroid.github.v3.models.Event;

/**
 * @author jasonrush
 * View for displaying a Github event
 */
public class EventView extends LinearLayout {
	private Event event;
	
	/**
	 * Constructor
	 * @param ctx
	 * @param event
	 */
	public EventView(Context ctx, Event event) {
		super(ctx);
		this.event = event;
		View view = getAndSetupView(ctx);
		addView(view, new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT));
	}
	
	// Getters and Setters
	public Event getEvent() {
		return this.event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	
	// Helpers
	private View getAndSetupView(Context ctx) {
		LayoutInflater inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		int layoutId = event.getLayoutId();
		View view  = inflater.inflate(layoutId, null);
		switch (layoutId) {
		case R.layout.default_event_list_item:
			setupDefaultLayout(view);
			break;
		}
		return view;
	}
	
	private void setupDefaultLayout(View view) {
		TextView textView = (TextView)view.findViewById(R.id.default_text_view);
		textView.setText("Event: " + event.getType());
	}
}
