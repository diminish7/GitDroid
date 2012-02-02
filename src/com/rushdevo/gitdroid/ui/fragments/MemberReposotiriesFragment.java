package com.rushdevo.gitdroid.ui.fragments;

import java.util.ArrayList;
import java.util.List;

import com.rushdevo.gitdroid.github.v3.models.Repository;

/**
 * @author jasonrush
 * Display fragment for authenticated user's member (collaborator) repositories content
 */
public class MemberReposotiriesFragment extends BaseRepositoriesFragment {
	private static List<Repository> memberRepositories = new ArrayList<Repository>();
	
	@Override
	public void retrieveRepositories() {
		memberRepositories = getRepositoryServiceInstance().retrieveMemberRepositories();
	}

	@Override
	public List<Repository> getRepositories() {
		return memberRepositories;
	}
}
