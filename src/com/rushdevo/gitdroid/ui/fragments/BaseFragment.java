package com.rushdevo.gitdroid.ui.fragments;

import android.support.v4.app.Fragment;

/**
 * @author jasonrush
 * Base fragment class for shared (non-list) fragment behavior
 */
public abstract class BaseFragment extends Fragment {
	/**
	 * Set the content object for this display fragment
	 */
	public abstract void setContentObject(Object object);
}
