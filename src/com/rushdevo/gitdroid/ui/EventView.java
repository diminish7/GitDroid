package com.rushdevo.gitdroid.ui;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rushdevo.gitdroid.R;
import com.rushdevo.gitdroid.github.v3.models.Event;
import com.rushdevo.gitdroid.utils.ErrorDisplay;

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
		descriptionView.setText(event.getFullDescription());
		timestampView.setText(event.getTimestamp());
		contentView.setText(event.getContent());
		// Load the avatar image asynchronously
		new RetrieveAvatarTask(avatarView).execute(event);
		return view;
	}
	
	///////////// INNER CLASSES ////////////////////////
	/**
	 * Async task for passing the temp code to github to get the access token back
	 */
	private class RetrieveAvatarTask extends AsyncTask<Event, Void, Drawable> {
		private ImageView imageView;
		
		public RetrieveAvatarTask(ImageView imageView) {
			this.imageView = imageView;
		}
		
		@Override
		protected Drawable doInBackground(Event... params) {
			Event event = (params.length > 0) ? params[0] : null;
			return getAvatarDrawableForEvent(event);
		}
		
		@Override
		protected void onPostExecute(Drawable avatar) {
			imageView.setImageDrawable(avatar);
		}
		
		// Helpers
		private Drawable getAvatarDrawableForEvent(Event event) {
			Drawable avatar = null;
			if (event != null) {
				URL url;
				InputStream is;
				try {
					url = new URL(event.getActorAvatarUrl());
					is = (InputStream)url.getContent();
					avatar = Drawable.createFromStream(is, event.getActorName());
				} catch (MalformedURLException e) {
					ErrorDisplay.debug(this, e);
				} catch (IOException e) {
					ErrorDisplay.debug(this, e);
				}
			}
			if (avatar == null) avatar = getResources().getDrawable(R.drawable.default_avatar);
			return avatar;
		}
	}
}
