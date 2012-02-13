package com.rushdevo.gitdroid.github.v3.services;

import java.lang.reflect.Type;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import com.google.gson.reflect.TypeToken;
import com.rushdevo.gitdroid.github.v3.models.User;

/**
 * @author jasonrush
 * 
 * Github User calls service (http://developer.github.com/v3/users/)
 */
public class UserService extends GithubService {
	// User URLS
	public static final String USER_URL = BASE_URL + "/user";
	public static final String USERS_URL = USER_URL + "s";
	public static final String FOLLOWERS_URL = USER_URL + "/followers";
	public static final String FOLLOWING_URL = USER_URL + "/following";
	
	public UserService(Context ctx) {
		super(ctx);
	}
	
	/**
	 * Retrieve the currently authenticated user from Github
	 * 
	 * https://api.github.com/user
	 * 
	 * @return the user
	 */
	public User retrieveCurrentUser() {
		return User.fromJson(getResponseBody(Uri.parse(USER_URL), true), User.class);
	}
	
	/**
	 * Retrieve the currently authenticated user's Followers from Github
	 * 
	 * https://api.github.com/user/followers
	 * 
	 * @param defaultAvatar - The avatar to display if they have no avatar set up
	 * @return The list of users
	 */
	public List<User> retrieveFollowers(Drawable defaultAvatar) {
		String json = getResponseBody(Uri.parse(FOLLOWERS_URL), true);
		Type listType = new TypeToken<List<User>>(){}.getType();
		List<User> followers = getGson().fromJson(json, listType);
		// Preload the avatar drawables
		for (User follower : followers) {
			follower.retrieveAvatarAsDrawable(defaultAvatar);
		}
		return followers;
	}
	
	/**
	 * Retrieve the list of users following the currently authenticated user from Github
	 * 
	 * https://api.github.com/user/following
	 * 
	 * @param defaultAvatar - The avatar to display if they have no avatar set up
	 * @return The list of users
	 */
	public List<User> retrieveFollowing(Drawable defaultAvatar) {
		String json = getResponseBody(Uri.parse(FOLLOWING_URL), true);
		Type listType = new TypeToken<List<User>>(){}.getType();
		List<User> following = getGson().fromJson(json, listType);
		// Preload the avatar drawables
		for (User follower : following) {
			follower.retrieveAvatarAsDrawable(defaultAvatar);
		}
		return following;
	}
}
