package com.rushdevo.gitdroid.ui.fragments;

import java.util.ArrayList;
import java.util.List;

import com.rushdevo.gitdroid.github.v3.models.User;
import com.rushdevo.gitdroid.utils.NonConfigurationChangeData;

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
	
	@Override
	@SuppressWarnings("unchecked")
	public void initializeNonConfigurationChangeData(NonConfigurationChangeData data) {
		if (data != null) {
			Object obj = data.getData(this);
			if (obj != null) {
				following = (List<User>)obj;
			}
		}
	}
}
