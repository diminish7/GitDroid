package com.rushdevo.gitdroid.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rushdevo.gitdroid.R;

/**
 * @author jasonrush
 * Display fragment for collaborator repositories content
 */
public class CollaboratorRepositoriesFragment extends BaseFragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.collaborator_repositories, container, false);
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
		hideSpinner(R.id.collaborator_repository_todo);
	}
}
