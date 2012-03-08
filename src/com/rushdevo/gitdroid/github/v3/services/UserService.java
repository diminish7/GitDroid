package com.rushdevo.gitdroid.github.v3.services;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import com.google.gson.reflect.TypeToken;
import com.rushdevo.gitdroid.github.v3.models.Organization;
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
	public static final String ORGS_URL = USER_URL + "/orgs";
	
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
	
	/**
	 * Retrieve the list of organizations for the currently authenticated user from Github
	 * 
	 * https://api.github.com/user/orgs
	 * 
	 * @param defaultAvatar - The avatar to display if they have no avatar set up
	 * @return The list of organizations (cast to User objects)
	 */
	public List<User> retrieveOrganizations(Drawable defaultAvatar) {
		String json = getResponseBody(Uri.parse(ORGS_URL), true);
		Type listType = new TypeToken<List<Organization>>(){}.getType();
		List<Organization> orgs = getGson().fromJson(json, listType);
		List<User> orgsAsUser = new ArrayList<User>();
		while (!orgs.isEmpty()) {
			Organization org = orgs.remove(0);
			org.retrieveAvatarAsDrawable(defaultAvatar);
			orgsAsUser.add(org);
		}
		return orgsAsUser;
	}
}
