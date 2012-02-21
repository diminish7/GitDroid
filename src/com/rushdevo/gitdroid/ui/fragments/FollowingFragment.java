package com.rushdevo.gitdroid.ui.fragments;

import java.util.ArrayList;
import java.util.List;

import com.rushdevo.gitdroid.github.v3.models.User;

/**
 * @author jasonrush
 * Display fragment for following content
 */
public class FollowingFragment extends BaseUsersFragment {
	private List<User> following = new ArrayList<User>();
	
	@Override
	public void retrieveUsers() {
		following = getUserServiceInstance().retrieveFollowing(getDefaultAvatar());
	}

	@Override
	public List<User> getUsers() {
		return following;
	}
}
