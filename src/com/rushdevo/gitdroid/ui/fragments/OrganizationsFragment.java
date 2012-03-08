package com.rushdevo.gitdroid.ui.fragments;

import java.util.ArrayList;
import java.util.List;

import com.rushdevo.gitdroid.github.v3.models.User;
import com.rushdevo.gitdroid.utils.NonConfigurationChangeData;


/**
 * @author jasonrush
 * Display fragment for organizations content
 */
public class OrganizationsFragment extends BaseUsersFragment {
	private List<User> orgs = new ArrayList<User>();
	
	@Override
	public void retrieveUsers() {
		orgs = getUserServiceInstance().retrieveOrganizations(getDefaultAvatar());
	}

	@Override
	public List<User> getUsers() {
		return orgs;
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void initializeNonConfigurationChangeData(NonConfigurationChangeData data) {
		if (data != null) {
			Object obj = data.getData(this);
			if (obj != null) {
				orgs = (List<User>)obj;
			}
		}
	}
	
}
