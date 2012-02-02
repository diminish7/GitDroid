package com.rushdevo.gitdroid.ui.fragments;

import java.util.ArrayList;
import java.util.List;

import com.rushdevo.gitdroid.github.v3.models.Repository;


/**
 * @author jasonrush
 * Display fragment for repositories content
 */
public class MyRepositoriesFragment extends BaseRepositoriesFragment {
	private static List<Repository> myRepositories = new ArrayList<Repository>();
	
	@Override
	public void retrieveRepositories() {
		myRepositories = getRepositoryServiceInstance().retrieveMyRepositories();
	}

	@Override
	public List<Repository> getRepositories() {
		return myRepositories;
	}
	
}
