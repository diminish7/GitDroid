package com.rushdevo.gitdroid.github.v3.models;

import com.google.gson.Gson;

/**
 * @author jasonrush
 * The base class for all models in the Github API
 */
public abstract class BaseGithubModel {
	private Gson gson;
	
	/**
	 * Basic constructor
	 */
	public BaseGithubModel() {
		this.gson = new Gson();
	}
	
	/**
	 * Creates the object and refreshes it's properties from the given JSON
	 * @param json
	 */
	public BaseGithubModel(String json) {
		super();
		fromJson(json);
	}
	
	/**
	 * @return The Gson parser
	 */
	public Gson getGson() {
		return this.gson;
	}
	
	/**
	 * @return The String JSON representation of the given object
	 */
	public String toJson() {
		return gson.toJson(this);
	}
	
	/**
	 * Refreshes this object's properties from the given JSON
	 * @param json - The String JSON that should be used to flesh out this object
	 */
	public void fromJson(String json) {
		gson.fromJson(json, this.getClass());
	}
}
