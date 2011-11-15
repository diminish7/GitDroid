package com.rushdevo.gitdroid.fragments;

import com.rushdevo.gitdroid.activities.BaseActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * @author jasonrush
 * Base fragment class for shared fragment behavior
 */
public class BaseFragment extends Fragment {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseActivity)getActivity()).trackPageView(getClass().getSimpleName());
	}
}
