package com.rushdevo.gitdroid.listeners;

/**
 * @author jasonrush
 * Interface for passing actions back and forth between fragments
 */
public interface ActionSelectedListener {
	/**
	 * Called when an action has been selected from the action list fragment
	 * @param action - The action name that was selected
	 */
	public void OnActionSelected(String action);
}
