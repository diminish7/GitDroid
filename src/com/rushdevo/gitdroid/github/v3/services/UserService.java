package com.rushdevo.gitdroid.github.v3.services;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import com.google.gson.reflect.TypeToken;
import com.rushdevo.gitdroid.github.v3.models.Organization;
import com.rushdevo.gitdroid.github.v3.models.User;
import com.rushdevo.gitdroid.utils.UserAvatarHelper;
import com.rushdevo.gitdroid.utils.UserAvatarHelperImpl;

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
	
	private UserAvatarHelper userAvatarHelper;
	
	public UserService(Context ctx) {
		super(ctx);
		this.userAvatarHelper = new UserAvatarHelperImpl();
	}
	
	public UserService(Context ctx, HttpClient client) {
		this(ctx, client, new UserAvatarHelperImpl());
	}
	
	public UserService(Context ctx, HttpClient client, UserAvatarHelper userAvatarHelper) {
		super(ctx, client);
		this.userAvatarHelper = userAvatarHelper;
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
			follower.retrieveAvatarAsDrawable(userAvatarHelper, defaultAvatar);
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
			follower.retrieveAvatarAsDrawable(userAvatarHelper, defaultAvatar);
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
			org.retrieveAvatarAsDrawable(userAvatarHelper, defaultAvatar);
			orgsAsUser.add(org);
		}
		return orgsAsUser;
	}
}
