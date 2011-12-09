package com.rushdevo.gitdroid.ui;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.graphics.drawable.Drawable;
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
		addView(getAndSetupView(ctx), new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT));
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
		View view  = inflater.inflate(R.layout.event_list_item, null);
		// Grab the views from the layout
		ImageView avatarView = (ImageView)view.findViewById(R.id.avatar_view);
		TextView descriptionView = (TextView)view.findViewById(R.id.event_description);
		TextView timestampView = (TextView)view.findViewById(R.id.event_timestamp);
		TextView contentView = (TextView)view.findViewById(R.id.event_content);
		// Set the content of the views
		avatarView.setImageDrawable(getAvatarDrawableForEvent(event));
		descriptionView.setText(event.getFullDescription());
		timestampView.setText(event.getTimestamp());
		contentView.setText(event.getContent());
		
		return view;
	}
	
	private Drawable getAvatarDrawableForEvent(Event event) {
		Drawable avatar = null;
		URL url;
		InputStream is;
		try {
			url = new URL(event.getActorAvatarUrl());
			is = (InputStream)url.getContent();
			avatar = Drawable.createFromStream(is, event.getActorName());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return avatar;
	}
}
