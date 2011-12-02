package com.rushdevo.gitdroid.github.v3.services;

import android.content.Context;
import android.net.Uri;

import com.rushdevo.gitdroid.github.v3.models.User;

/**
 * @author jasonrush
 * 
 * Github User calls service (http://developer.github.com/v3/users/)
 */
public class UserService extends GithubService {
	// User URLS
	public static final String USER_URL = BASE_URL + "/user";
	
	public UserService(Context ctx) {
		super(ctx);
	}
	
	/**
	 * Retrieve the currently authenticated user from Github
	 * @return the user
	 */
	public User retrieveCurrentUser() {
		return new User(getResponseBody(Uri.parse(USER_URL), true));
	}
}
