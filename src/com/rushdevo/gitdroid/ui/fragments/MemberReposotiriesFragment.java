package com.rushdevo.gitdroid.ui.fragments;

import java.util.ArrayList;
import java.util.List;

import com.rushdevo.gitdroid.github.v3.models.Repository;
import com.rushdevo.gitdroid.utils.NonConfigurationChangeData;

/**
 * @author jasonrush
 * Display fragment for authenticated user's member (collaborator) repositories content
 */
public class MemberReposotiriesFragment extends BaseRepositoriesFragment {
	private List<Repository> memberRepositories = new ArrayList<Repository>();
	
	@Override
	public void retrieveRepositories() {
		memberRepositories = getRepositoryServiceInstance().retrieveMemberRepositories();
	}

	@Override
	public List<Repository> getRepositories() {
		return memberRepositories;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void initializeNonConfigurationChangeData(NonConfigurationChangeData data) {
		if (data != null) {
			Object obj = data.getData(this);
			if (obj != null) {
				memberRepositories = (List<Repository>)obj;
			}
		}
	}
}
