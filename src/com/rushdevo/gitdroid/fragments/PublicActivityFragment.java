/**
 * 
 */
package com.rushdevo.gitdroid.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rushdevo.gitdroid.R;

/**
 * @author jasonrush
 * Display fragment for public activity
 */
public class PublicActivityFragment extends BaseFragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.public_activity, container, false);
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
		hideSpinner(R.id.public_activity_todo);
	}
	
}
