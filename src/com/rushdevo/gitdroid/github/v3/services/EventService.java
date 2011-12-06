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
	
	/**
	 * Gets the received events URL for the current user
	 * https://api.github.com/users/:user/received_events
	 * @return the URL
	 */
	public String getReceivedEventsUrl(Integer page) {
		String login = getGitDroidApplication().getCurrentUserLogin();
		if (login == null) return null;
		StringBuilder builder = new StringBuilder();
		builder.append(UserService.USERS_URL);
		builder.append("/");
		builder.append(login);
		builder.append("/received_events?page=");
		builder.append(page);
		return builder.toString();
	}
	
	/**
	 * Get the list of events
	 * @return The list of events
	 */
	public List<Event> retrieveReceivedEvents(Integer page) {
		String json = getResponseBody(Uri.parse(getReceivedEventsUrl(page)), true);
		Type listType = new TypeToken<List<Event>>(){}.getType();
		return getGson().fromJson(json, listType);
	}
	
}
