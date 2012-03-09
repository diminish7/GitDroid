/**
 * 
 */
package com.rushdevo.gitdroid.github.v3.services;

import java.lang.reflect.Type;
import java.util.List;

import android.content.Context;
import android.net.Uri;

import com.google.gson.reflect.TypeToken;
import com.rushdevo.gitdroid.github.v3.models.Gist;

/**
 * @author jasonrush
 * 
 * Github Gist calls service (http://developer.github.com/v3/gists/)
 */
public class GistService extends GithubService {
	// Static Gist URLS
	public static final String GISTS_URL = BASE_URL + "/gists";
	
	public GistService(Context ctx) {
		super(ctx);
	}
	
	/**
	 * Retrieve the list of gists from the currently authenticated user from Github
	 * 
	 * https://api.github.com/gists
	 * 
	 * @return The list of gists
	 */
	public List<Gist> retrieveGists() {
		String json = getResponseBody(Uri.parse(GISTS_URL), true);
		Type listType = new TypeToken<List<Gist>>(){}.getType();
		return getGson().fromJson(json, listType);
	}
}
