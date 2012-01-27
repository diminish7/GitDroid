package com.rushdevo.gitdroid.ui.activities;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;
import com.rushdevo.gitdroid.GitDroidApplication;
import com.rushdevo.gitdroid.R;
import com.rushdevo.gitdroid.ui.fragments.CollaboratorRepositoriesFragment;
import com.rushdevo.gitdroid.ui.fragments.EventsFragment;
import com.rushdevo.gitdroid.ui.fragments.FollowersFragment;
import com.rushdevo.gitdroid.ui.fragments.FollowingFragment;
import com.rushdevo.gitdroid.ui.fragments.GistsFragment;
import com.rushdevo.gitdroid.ui.fragments.OrganizationsFragment;
import com.rushdevo.gitdroid.ui.fragments.PublicActivityFragment;
import com.rushdevo.gitdroid.ui.fragments.RepositoriesFragment;
import com.rushdevo.gitdroid.ui.fragments.WatchedRepositoriesFragment;

/**
 * @author jasonrush
 * Base activity for shared functionality
 */
public abstract class BaseActivity extends FragmentActivity {
	/** The key in intent's extras for the action selected */
	public static final String SELECTED_ACTION = "SELECTED_ACTION";
	
	protected GoogleAnalyticsTracker analyticsTracker;
	protected Map<String, Fragment> contentFragmentMap;
	protected Map<String, String> reverseContentFragmentMap;
	
	public GitDroidApplication getGitDroidApplication() {
		return (GitDroidApplication)getApplication();
	}
	
	/**
	 * Set the title from an action
	 * e.g. GitDroid - News Feed
	 * @param action
	 */
	public void setTitleFromAction(String action) {
		StringBuilder title = new StringBuilder(getString(R.string.app_name));
    	title.append(" - ");
    	title.append(action);
    	setTitle(title.toString());
	}
	
	/**
	 * Track a page view with Google Analytics
	 * @param className - The name of the activity or fragment we are tracking
	 */
	public void trackPageView(String className) {
		GoogleAnalyticsTracker tracker = getAnalyticsTracker();
		if (tracker != null) {
			tracker.trackPageView("/"+className);
		}
	}
	
	/**
	 * @return the instance of shared preferences
	 */
	public SharedPreferences getSharedPrefs() {
		return PreferenceManager.getDefaultSharedPreferences(this.getBaseContext());
	}
	
	/**
	 * If the analytics tracker is running, stop it
	 */
	protected void stopAnalyticsTracking() {
		if (analyticsTracker != null) {
			analyticsTracker.stopSession();
			analyticsTracker = null;
		}
	}
	
	/**
	 * @return The lazy-loaded Google Analytics Tracker
	 */
	protected GoogleAnalyticsTracker getAnalyticsTracker() {
		if (analyticsTracker == null) {
			analyticsTracker = GoogleAnalyticsTracker.getInstance();
			Properties analyticsProperties = new Properties();
			try {
				analyticsProperties.load(this.getResources().getAssets().open("analytics.properties"));
				analyticsTracker.startNewSession(analyticsProperties.getProperty("account"), 30, this);
			} catch (IOException e) {
				e.printStackTrace();
				analyticsTracker = null;
			}
		}
		return analyticsTracker;
	}
	
	/**
	 * @return The lazy-loaded content fragment map
	 */
	protected Map<String, Fragment> getContentFragmentMap() {
		if (contentFragmentMap == null) {
	    	contentFragmentMap = new TreeMap<String, Fragment>();
			contentFragmentMap.put(getString(R.string.events), new EventsFragment());
			contentFragmentMap.put(getString(R.string.public_activity), new PublicActivityFragment());
			contentFragmentMap.put(getString(R.string.repositories), new RepositoriesFragment());
			contentFragmentMap.put(getString(R.string.collaborator_repositories), new CollaboratorRepositoriesFragment());
			contentFragmentMap.put(getString(R.string.watched_repositories), new WatchedRepositoriesFragment());
			contentFragmentMap.put(getString(R.string.followers), new FollowersFragment());
			contentFragmentMap.put(getString(R.string.following), new FollowingFragment());
			contentFragmentMap.put(getString(R.string.organizations), new OrganizationsFragment());
			contentFragmentMap.put(getString(R.string.gists), new GistsFragment());
		}
		return contentFragmentMap;
    }
	
	/**
	 * Clear the selected action from preferences
	 */
	protected void clearSelectedAction() {
        Editor prefEditor = getSharedPrefs().edit();
        prefEditor.remove(SELECTED_ACTION);
        prefEditor.commit();
	}
}
