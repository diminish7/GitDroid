package com.rushdevo.gitdroid.ui.fragments;

import java.util.ArrayList;
import java.util.List;

import com.rushdevo.gitdroid.github.v3.models.User;

/**
 * @author jasonrush
 * Display fragment for followers content
 */
public class FollowersFragment extends BaseUsersFragment {
	private List<User> followers = new ArrayList<User>();
	
	@Override
	public void retrieveUsers() {
		followers = getUserServiceInstance().retrieveFollowers(getDefaultAvatar());
	}

	@Override
	public List<User> getUsers() {
		return followers;
	}
}
