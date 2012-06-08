package com.rushdevo.gitdroid.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.rushdevo.gitdroid.listeners.ObjectSelectedListener;
import com.rushdevo.gitdroid.utils.NonConfigurationChangeData;

/**
 * @author jasonrush
 * Base fragment class for shared (non-list) fragment behavior
 */
public abstract class BaseFragment extends Fragment implements GitDroidFragment {
	private ObjectSelectedListener objectSelectedListener;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		initializeNonConfigurationChangeData(getLastCustomNonConfigurationInstance());
		super.onCreate(savedInstanceState);
	}
	
	/**
	 * Set the content object for this display fragment
	 */
	public abstract void setContentObject(Object object);
	
	public ObjectSelectedListener getObjectSelectedListener() {
		return this.objectSelectedListener;
	}
	
	public void setObjectSelectedListener(ObjectSelectedListener listener) {
		this.objectSelectedListener = listener;
	}
	
	/**
	 * Delegates to the activity. Returns null if the cached data isn't a NonConfigurationChangeData instance
	 * @return The cached data from the last config change
	 */
	public NonConfigurationChangeData getLastCustomNonConfigurationInstance() {
		if (getActivity() == null) return null;
		Object data = ((FragmentActivity)getActivity()).getLastCustomNonConfigurationInstance();
		if (data instanceof NonConfigurationChangeData) return (NonConfigurationChangeData)data;
		return null;
	}
	
	protected abstract void initializeNonConfigurationChangeData(NonConfigurationChangeData data);
}
