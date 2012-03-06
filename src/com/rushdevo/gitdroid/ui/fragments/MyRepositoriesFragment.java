package com.rushdevo.gitdroid.ui.fragments;

import java.util.ArrayList;
import java.util.List;

import com.rushdevo.gitdroid.github.v3.models.Repository;
import com.rushdevo.gitdroid.utils.NonConfigurationChangeData;


/**
 * @author jasonrush
 * Display fragment for authenticated user's repositories content
 */
public class MyRepositoriesFragment extends BaseRepositoriesFragment {
	private List<Repository> myRepositories = new ArrayList<Repository>();
	
	@Override
	public void retrieveRepositories() {
		myRepositories = getRepositoryServiceInstance().retrieveMyRepositories();
	}

	@Override
	public List<Repository> getRepositories() {
		return myRepositories;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void initializeNonConfigurationChangeData(NonConfigurationChangeData data) {
		if (data != null) {
			Object obj = data.getData(this);
			if (obj != null) {
				myRepositories = (List<Repository>)obj;
			}
		}
	}
	
}
