package com.rushdevo.gitdroid.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rushdevo.gitdroid.R;

/**
 * @author jasonrush
 * Display fragment for watched repositories content
 */
public class WatchedRepositoriesFragment extends BaseFragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.watched_repositories, container, false);
	}
	
	@Override
	protected void initializeData() {
		// TODO Auto-generated method stub
		initializeView();
	}
	
	@Override
	protected boolean viewIsReady() {
		return true;
	}
	
	@Override
	protected void initializeView() {
		hideSpinner(R.id.watched_repositories_todo);
	}
}