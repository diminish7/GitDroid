package com.rushdevo.gitdroid.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rushdevo.gitdroid.R;
import com.rushdevo.gitdroid.utils.NonConfigurationChangeData;

/**
 * @author jasonrush
 * Display fragment for organizations content
 */
public class OrganizationsFragment extends BaseFragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.organizations, container, false);
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
		hideSpinner(R.id.organizations_todo);
	}
	
	@Override
	public Object onRetainCustomNonConfigurationInstance() {
		// TODO: Implement once org data is fleshed out
		return null;
	}
	
	@Override
	public void initializeNonConfigurationChangeData(NonConfigurationChangeData data) {
		// TODO: Implement once org data is fleshed out
	}
}
