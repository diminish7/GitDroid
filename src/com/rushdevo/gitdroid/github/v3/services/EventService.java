package com.rushdevo.gitdroid.github.v3.services;

import java.lang.reflect.Type;
import java.util.List;

import android.content.Context;
import android.net.Uri;

import com.google.gson.reflect.TypeToken;
import com.rushdevo.gitdroid.github.v3.models.Event;

/**
 * @author jasonrush
 * Github Events calls service (http://developer.github.com/v3/events/)
 */
public class EventService extends GithubService {
	
	public EventService(Context ctx) {
		super(ctx);
	}
	
	/////// URL BUILDERS ////////
	
	/**
	 * Gets the received events URL for the current user
	 * https://api.github.com/users/:user/received_events
	 * @return the URL
	 */
	public String getReceivedEventsUrl(Integer page) {
		StringBuilder builder = getBuilderForCurrentUserUrl();
		builder.append("/received_events?page=");
		builder.append(page);
		return builder.toString();
	}
	
	/**
	 * Gets the public events URL for the current user
	 * https://api.github.com/users/:user/events/public
	 * @return the URL
	 */
	public String getPublicEventsUrl(Integer page) {
		StringBuilder builder = getBuilderForCurrentUserUrl();
		builder.append("/events/public?page=");
		builder.append(page);
		return builder.toString();
	}
	
	/////// API CALLS ///////////
	
	/**
	 * Get the list of received events (the private news feed)
	 * @return The list of events
	 */
	public List<Event> retrieveReceivedEvents(Integer page) {
		String json = getResponseBody(Uri.parse(getReceivedEventsUrl(page)), true);
		Type listType = new TypeToken<List<Event>>(){}.getType();
		return getGson().fromJson(json, listType);
	}
	
	/**
	 * Get the list of public activity events for the current user
	 * @return The list of events
	 */
	public List<Event> retrievePublicEvents(Integer page) {
		String json = getResponseBody(Uri.parse(getPublicEventsUrl(page)), true);
		Type listType = new TypeToken<List<Event>>(){}.getType();
		return getGson().fromJson(json, listType);
	}
}
