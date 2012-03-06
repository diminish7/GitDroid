package com.rushdevo.gitdroid.utils;

import android.support.v4.app.Fragment;

/**
 * @author jasonrush
 * Used by onRetainCustomNonConfigurationInstance() to cache data between config changes
 */
public class NonConfigurationChangeData {
	private Class<?> fragmentClass;
	private Object data;
	
	/**
	 * Constructor
	 * 
	 * @param fragment - The fragment caching the data
	 * @param data - The data it's caching
	 */
	public NonConfigurationChangeData(Fragment fragment, Object data) {
		this.fragmentClass = fragment.getClass();
		this.data = data;
	}
	
	/**
	 * Get the cached data
	 * @param fragment - The fragment that cached it
	 * @return The cached data if the fragment matches (or null if it does not)
	 */
	public Object getData(Fragment fragment) {
		if (isDataForFragment(fragment)) return data;
		else return null;
	}
	
	private boolean isDataForFragment(Fragment fragment) {
		return fragment.getClass() == fragmentClass;
	}
}
