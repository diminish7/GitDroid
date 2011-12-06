package com.rushdevo.gitdroid.github.v3.models;

import com.google.gson.Gson;
import com.rushdevo.gitdroid.gson.GsonUtils;

/**
 * @author jasonrush
 * The base class for all models in the Github API
 */
public abstract class BaseGithubModel {
	
	/**
	 * @return The Gson parser
	 */
	public Gson getGson() {
		return GsonUtils.getGsonInstance();
	}
	
	/**
	 * @return The String JSON representation of the given object
	 */
	public String toJson() {
		return getGson().toJson(this);
	}
	
	/**
	 * Refreshes this object's properties from the given JSON
	 * @param json - The String JSON that should be used to flesh out this object
	 */
	public static <T extends BaseGithubModel> T fromJson(String json, Class<T> klazz) {
		return (T)GsonUtils.getGsonInstance().fromJson(json, klazz);
	}
}
