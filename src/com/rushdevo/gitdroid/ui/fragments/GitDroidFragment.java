package com.rushdevo.gitdroid.ui.fragments;

import com.rushdevo.gitdroid.listeners.ObjectSelectedListener;

public interface GitDroidFragment {
	/**
	 * Called by the surrounding parent activity's onRetainCustomNonConfigurationInstance()
	 * @return The object that this fragment would like to retain during config change
	 */
	public abstract Object onRetainCustomNonConfigurationInstance();
	
	public abstract ObjectSelectedListener getObjectSelectedListener();
	
	public abstract void setObjectSelectedListener(ObjectSelectedListener listener);
}
