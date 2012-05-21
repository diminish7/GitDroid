package com.rushdevo.gitdroid.github.v3.services;

import java.lang.reflect.Type;
import java.util.List;

import org.apache.http.client.HttpClient;

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
	public static final String GIST_LINK_URL = "https://gist.github.com";
	
	public GistService(Context ctx) {
		super(ctx);
	}
	
	public GistService(Context ctx, HttpClient client) {
		super(ctx, client);
	}
	
	/**
	 * Get the URL for the gist display script
	 * 
	 * @param gist The gist to display
	 * @return url The string URL for the gist display script
	 */
	public String getGistDisplayScriptUrl(Gist gist) {
		StringBuilder builder = new StringBuilder();
		builder.append(GIST_LINK_URL);
		builder.append("/");
		builder.append(gist.getId());
		builder.append(".js");
		return builder.toString();
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
