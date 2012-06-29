package com.rushdevo.gitdroid.listeners;

import com.rushdevo.gitdroid.ui.fragments.BaseFragment;

/**
 * @author jasonrush
 * Interface for passing objects back and forth between fragments
 */
public interface ObjectSelectedListener {
	/**
	 * Called when an object has been selected from one of the list fragment
	 * @param object - The object that was selected
	 * @param fragment - The fragment waiting to load the object
	 */
	public void OnObjectSelected(Object object, BaseFragment fragment);
	
	/**
	 * Called when it is time to deselect the selected object (hide the view)
	 * @param fragment - The fragment to remove
	 */
	public void OnObjectDeselected(BaseFragment fragment);
}
