package com.rushdevo.gitdroid.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
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
		addView(inflateView(ctx), new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT));
	}
	
	// Getters and Setters
	public Event getEvent() {
		return this.event;
	}
	public void setEvent(Event event) {
		this.event = event;
		updateView(this);
	}
	public ImageView getAvatarImageView() {
		return (ImageView)findViewById(R.id.avatar_view);
	}
	
	// Helpers
	private View inflateView(Context ctx) {
		LayoutInflater inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view  = inflater.inflate(R.layout.event_list_item, null);
		updateView(view);
		return view;
	}
	
	private void updateView(View view) {
		// Grab the views from the layout
		ImageView avatarView = (ImageView)view.findViewById(R.id.avatar_view);
		TextView descriptionView = (TextView)view.findViewById(R.id.event_description);
		TextView timestampView = (TextView)view.findViewById(R.id.event_timestamp);
		TextView contentView = (TextView)view.findViewById(R.id.event_content);
		// Set the content of the views
		descriptionView.setText(event.getFullDescription());
		timestampView.setText(event.getTimestamp());
		contentView.setText(event.getContent());
		if (event.getActor() != null)
			avatarView.setImageDrawable(event.getActor().getAvatar());
	}
}
