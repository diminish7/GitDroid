package com.rushdevo.gitdroid.github.v3.services;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.net.Uri;

import com.google.gson.reflect.TypeToken;
import com.rushdevo.gitdroid.github.v3.models.Repository;

/**
 * @author jasonrush
 * Github repository calls (http://developer.github.com/v3/repos/)
 */
public class RepositoryService extends GithubService {
	public static final String USER_REPOSITORY_URL = UserService.USER_URL + "/repos"; 

	public RepositoryService(Context ctx) {
		super(ctx);
	}
	
	/////// URL BUILDERS ////////
	
	/**
	 * Gets the repositories URL for the current user
	 * https://api.github.com/user/repos
	 * @return the URL
	 */
	public String getMyRepositoriesUrl() {
		return USER_REPOSITORY_URL;
	}
	
	/////// API CALLS ///////////
	
	/**
	 * Get the list of repositories for the current user
	 * @return The list of repositories
	 */
	public List<Repository> retrieveMyRepositories() {
		String json = getResponseBody(Uri.parse(getMyRepositoriesUrl()), true);
		Type listType = new TypeToken<List<Repository>>(){}.getType();
		List<Repository> repos = getGson().fromJson(json, listType);
		Collections.sort(repos);
		return repos;
	}
}
