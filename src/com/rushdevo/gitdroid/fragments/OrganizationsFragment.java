package com.rushdevo.gitdroid.fragments;

import com.rushdevo.gitdroid.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author jasonrush
 * Display fragment for organizations content
 */
public class OrganizationsFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.organizations, container, false);
	}
}
