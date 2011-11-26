package com.rushdevo.gitdroid.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.View;

import com.github.api.v2.schema.User;
import com.github.api.v2.services.OAuthService;
import com.github.api.v2.services.auth.OAuthAuthentication;
import com.rushdevo.gitdroid.R;
import com.rushdevo.gitdroid.activities.BaseActivity;
import com.rushdevo.gitdroid.github.Github;

/**
 * @author jasonrush
 * Base fragment class for shared fragment behavior
 */
public abstract class BaseFragment extends Fragment {
	protected static final int INIT_DATA_MESSAGE = 0;
	protected static final int INIT_VIEW_MESSAGE = 1;
	protected Handler initHandler;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initHandler = new InitHandler();
        ((BaseActivity)getActivity()).trackPageView(getClass().getSimpleName());
        initializeCommonData();
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (viewIsReady()) initializeView();
	}
	
	protected Github getGithub() {
		return ((BaseActivity)getActivity()).getGithub();
	}
	
	protected OAuthService getOAuthServiceInstance() {
		return getGithub().getOAuthServiceInstance();
	}
	
	/**
	 * @return the authentication instance 
	 */
	protected OAuthAuthentication getAuthentication() {
		return ((BaseActivity)getActivity()).getAuthentication();
	}
	
	/**
	 * @return the currently authenticated user
	 */
	protected User getCurrentUser() {
		return ((BaseActivity)getActivity()).getCurrentUser();
	}
	
	/**
	 * Performs OAuth authentication
	 */
	protected void authenticate() {
		String authUrl = getGithub().getAuthUrl();
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(authUrl)));
		getActivity().finish();
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
	 * Then grab the current user and set that aside
	 * Runs asynchronously
	 */
	protected void initializeCommonData() {
		if (getGithub().getToken() == null) {
			new RetrieveAccessTokenTask().execute(getActivity().getIntent().getData());
		} else if (getCurrentUser() == null) {
			new RetrieveCurrentUserTask().execute();
		} else {
			// The common stuff's initialized already, go ahead and initialize fragment-specific data
			initializeData();
		}
	}
	
	/**
	 * Show the progress spinner
	 * 
	 * @param mainViewId: The id of the main view that the spinner will toggle with
	 */
	protected void showSpinner(int mainViewId) {
		View main = getActivity().findViewById(mainViewId);
		View spinner = getActivity().findViewById(R.id.spinner_container);
		if (main != null && spinner != null) {
			main.setVisibility(View.GONE);
			spinner.setVisibility(View.VISIBLE);
		}
	}
	
	/**
	 * Hide the progress spinner
	 * 
	 * @param mainViewId: The id of the main view that the spinner will toggle with
	 */
	protected void hideSpinner(int mainViewId) {
		View main = getActivity().findViewById(mainViewId);
		View spinner = getActivity().findViewById(R.id.spinner_container);
		if (main != null && spinner != null) {
			spinner.setVisibility(View.GONE);
			main.setVisibility(View.VISIBLE);
		}
	}
	
	/**
	 * @return Handler responsible for initializing data
	 */
	protected Handler getInitHandler() {
		return initHandler;
	}
	
	/**
	 * Initialize data specific to this particular fragment
	 */
	protected abstract void initializeData();
	
	/**
	 * Initialize view once data is loaded
	 */
	protected abstract void initializeView();
	
	/**
	 * Returns true if the data is loaded and ready to initialize the view
	 */
	protected abstract boolean viewIsReady();
	
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
			if (result == null) {
				authenticate();
			} else {
				// Update the access token and current user
				getGithub().updateAccessToken(result);
				getGithub().updateCurrentUser();
				// Initialize the fragment-specific data
				getInitHandler().sendEmptyMessage(INIT_DATA_MESSAGE);
			}
		}
		
	}
	
	/**
	 * Async task for getting the user name of the authenticated user
	 */
	private class RetrieveCurrentUserTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			// Update the current user
			getGithub().updateCurrentUser();
			// Initialize the fragment-specific data
			getInitHandler().sendEmptyMessage(INIT_DATA_MESSAGE);
			return null;
		}
		
	}
	
	/**
	 * Initialization handler
	 */
	private class InitHandler extends Handler {
		@Override
    	public void handleMessage(Message message) {
			switch(message.what) {
			case INIT_DATA_MESSAGE:
				initializeData();
				break;
			case INIT_VIEW_MESSAGE:
				initializeView();
				break;
			}
		}
	}
}
