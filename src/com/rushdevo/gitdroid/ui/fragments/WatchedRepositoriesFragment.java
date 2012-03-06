package com.rushdevo.gitdroid.ui.fragments;

import java.util.ArrayList;
import java.util.List;

import com.rushdevo.gitdroid.github.v3.models.Repository;
import com.rushdevo.gitdroid.utils.NonConfigurationChangeData;

/**
 * @author jasonrush
 * Display fragment for watched repositories content
 */
public class WatchedRepositoriesFragment extends BaseRepositoriesFragment {
	private List<Repository> watchedRepositories = new ArrayList<Repository>();
	
	@Override
	public void retrieveRepositories() {
		watchedRepositories = getRepositoryServiceInstance().retrieveWatchedRepositories();
	}

	@Override
	public List<Repository> getRepositories() {
		return watchedRepositories;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void initializeNonConfigurationChangeData(NonConfigurationChangeData data) {
		if (data != null) {
			Object obj = data.getData(this);
			if (obj != null) {
				watchedRepositories = (List<Repository>)obj;
			}
		}
	}
}
