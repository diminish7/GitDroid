package com.rushdevo.gitdroid.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
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
	
	/**
	 * Determine if a JSON element is null (either doesn't exist, or is explicitly set to null)
	 * @param el - The JSON element to check
	 * @return true iff the JSON element is null
	 */
	public static boolean isNull(JsonElement el) {
		return el == null || el instanceof JsonNull;
	}
}
