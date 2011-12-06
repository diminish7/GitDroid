package com.rushdevo.gitdroid.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rushdevo.gitdroid.github.v3.models.Event;

/**
 * @author jasonrush
 * Centralized place for Gson-related stuff
 */
public class GsonUtils {
	private static Gson gson;
	
	/**
	 * Create a new instance of Gson and tack on all our custom deserializers
	 * @return a Gson instance
	 */
	public static Gson getGsonInstance() {
		if (gson == null) {
			GsonBuilder builder = new GsonBuilder();
			builder.registerTypeAdapter(Event.class, new EventDeserializer());
			gson = builder.create();
		}
		return gson;
	}
}
