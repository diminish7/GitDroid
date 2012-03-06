package com.rushdevo.gitdroid.ui.fragments;

import java.util.ArrayList;
import java.util.List;

import com.rushdevo.gitdroid.github.v3.models.User;
import com.rushdevo.gitdroid.utils.NonConfigurationChangeData;

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
	
	@Override
	@SuppressWarnings("unchecked")
	public void initializeNonConfigurationChangeData(NonConfigurationChangeData data) {
		if (data != null) {
			Object obj = data.getData(this);
			if (obj != null) {
				followers = (List<User>)obj;
			}
		}
	}
}
