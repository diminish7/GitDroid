package com.rushdevo.gitdroid.activity;

import roboguice.activity.RoboActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.google.inject.Inject;
import com.rushdevo.gitdroid.github.Github;

/**
 * @author jasonrush
 * Base activity for shared functionality
 */
public class BaseActivity extends RoboActivity {
	@Inject Github github;
	
	/**
	 * Performs OAuth authentication
	 */
	protected void authenticate() {
		String authUrl = github.getAuthUrl();
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(authUrl)));
		finish();
	}
	
	/**
	 * Check to see if this user has authenticated
	 * 
	 * @return true iff we have an oath token
	 */
	protected boolean isAuthenticated() {
		return github.getToken() != null;
	}
	
	/**
	 * If we just got back from a call to the github auth url, grab the oath code and call for a token
	 * Runs asynchronously
	 */
	protected void tryToSetToken() {
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
			return github.retrievAccessToken(uri);
		}
		
		@Override
		protected void onPostExecute(String result) {
			// If we didn't get anything back - not auth'd yet
			if (result == null) authenticate();
			else github.updateAccessToken(result);
		}
		
	}
}
