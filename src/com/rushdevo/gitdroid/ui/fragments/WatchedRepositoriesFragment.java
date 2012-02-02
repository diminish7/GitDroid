package com.rushdevo.gitdroid.ui.fragments;

import java.util.ArrayList;
import java.util.List;

import com.rushdevo.gitdroid.github.v3.models.Repository;

/**
 * @author jasonrush
 * Display fragment for watched repositories content
 */
public class WatchedRepositoriesFragment extends BaseRepositoriesFragment {
private static List<Repository> watchedRepositories = new ArrayList<Repository>();
	
	@Override
	public void retrieveRepositories() {
		watchedRepositories = getRepositoryServiceInstance().retrieveWatchedRepositories();
	}

	@Override
	public List<Repository> getRepositories() {
		return watchedRepositories;
	}
}
