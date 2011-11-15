package com.rushdevo.gitdroid.activities;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;
import com.rushdevo.gitdroid.R;
import com.rushdevo.gitdroid.fragments.CollaboratorRepositoriesFragment;
import com.rushdevo.gitdroid.fragments.FollowersFragment;
import com.rushdevo.gitdroid.fragments.FollowingFragment;
import com.rushdevo.gitdroid.fragments.GistsFragment;
import com.rushdevo.gitdroid.fragments.NewsFeedFragment;
import com.rushdevo.gitdroid.fragments.OrganizationsFragment;
import com.rushdevo.gitdroid.fragments.PublicActivityFragment;
import com.rushdevo.gitdroid.fragments.RepositoriesFragment;
import com.rushdevo.gitdroid.fragments.WatchedRepositoriesFragment;
import com.rushdevo.gitdroid.github.Github;
import com.rushdevo.gitdroid.github.GithubImpl;

/**
 * @author jasonrush
 * Base activity for shared functionality
 */
public abstract class BaseActivity extends FragmentActivity {
	/** The key in intent's extras for the action selected */
	public static final String SELECTED_ACTION = "SELECTED_ACTION";
	
	protected GoogleAnalyticsTracker analyticsTracker;
	protected Github github;
	protected Map<String, Fragment> contentFragmentMap;
	
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
			contentFragmentMap.put(getString(R.string.news_feed), new NewsFeedFragment());
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
	 * Lazy-load the github delegate
	 */
	protected Github getGithub() {
		if (github == null) {
			github = new GithubImpl(this, getSharedPrefs());
		}
		return github;
	}
	
	/**
	 * Get the instance of shared preferences
	 */
	protected SharedPreferences getSharedPrefs() {
		return PreferenceManager.getDefaultSharedPreferences(this);
	}
	
	/**
	 * Performs OAuth authentication
	 */
	protected void authenticate() {
		String authUrl = getGithub().getAuthUrl();
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(authUrl)));
		finish();
	}
	
	/**
	 * Check to see if this user has authenticated
	 * 
	 * @return true iff we have an oath token
	 */
	protected boolean isAuthenticated() {
		return getGithub().getToken() != null;
	}
	
	/**
	 * If we just got back from a call to the github auth url, grab the oath code and call for a token
	 * Runs asynchronously
	 */
	protected void tryToSetToken() {
		if (getGithub().getToken() == null)
			new RetrieveAccessTokenTask().execute(this.getIntent().getData());
	}
	
	///////////// INNER CLASSES ////////////////////////
	/**
	 * Async task for passing the temp code to github to get the access token back
	 */
	private class RetrieveAccessTokenTask extends AsyncTask<Uri, Void, String> {
		@Override
		protected String doInBackground(Uri... params) {
			Uri uri = (params.length > 0) ? params[0] : null;
			return getGithub().retrievAccessToken(uri);
		}
		
		@Override
		protected void onPostExecute(String result) {
			// If we didn't get anything back - not auth'd yet
			if (result == null) authenticate();
			else getGithub().updateAccessToken(result);
		}
		
	}
}
