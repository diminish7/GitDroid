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
	public static final String USER_WATCHED_URL = UserService.USER_URL + "/watched";

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
	
	/**
	 * Get the member repositories URL for the current user
	 * https://api.github.com/user/repos?type=member
	 * @return the URL
	 */
	public String getMemberRepositoriesUrl() {
		StringBuilder builder = new StringBuilder();
		builder.append(USER_REPOSITORY_URL);
		builder.append("?type=member");
		return builder.toString();
	}
	
	/**
	 * Get the watched repositories URL for the current user
	 * https://api.github.com/user/watched
	 * @return the URL
	 */
	public String getWatchedRepositoriesUrl() {
		return USER_WATCHED_URL;
	}
	
	/////// API CALLS ///////////
	
	/**
	 * Get the list of repositories for the current user
	 * @return The list of repositories
	 */
	public List<Repository> retrieveMyRepositories() {
		return retrieveRepositories(getMyRepositoriesUrl());
	}
	
	/**
	 * Get the list of repositories for which the current user is a member
	 * @return The list of repositories
	 */
	public List<Repository> retrieveMemberRepositories() {
		return retrieveRepositories(getMemberRepositoriesUrl());
	}
	
	/**
	 * Get the list of repositories that the current user is watching
	 * @return The list of repositories
	 */
	public List<Repository> retrieveWatchedRepositories() {
		return retrieveRepositories(getWatchedRepositoriesUrl());
	}
	
	//////// HELPERS /////////
	
	private List<Repository> retrieveRepositories(String url) {
		String json = getResponseBody(Uri.parse(url), true);
		Type listType = new TypeToken<List<Repository>>(){}.getType();
		List<Repository> repos = getGson().fromJson(json, listType);
		Collections.sort(repos);
		return repos;
	}
}
