package com.rushdevo.gitdroid.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.View;

import com.rushdevo.gitdroid.GitDroidApplication;
import com.rushdevo.gitdroid.R;
import com.rushdevo.gitdroid.activities.BaseActivity;
import com.rushdevo.gitdroid.github.v3.models.User;
import com.rushdevo.gitdroid.github.v3.services.OAuthService;
import com.rushdevo.gitdroid.github.v3.services.UserService;
import com.rushdevo.gitdroid.utils.ErrorDisplay;

/**
 * @author jasonrush
 * Base fragment class for shared fragment behavior
 */
public abstract class BaseFragment extends Fragment {
	protected static final int INIT_DATA_MESSAGE = 0;
	protected static final int INIT_VIEW_MESSAGE = 1;
	protected static final int NO_ACCESS_CODE_MESSAGE = 2;
	protected Handler initHandler;
	protected UserService userService;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initHandler = new InitHandler(this.getActivity());
        ((BaseActivity)getActivity()).trackPageView(getClass().getSimpleName());
        initializeCommonData();
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (viewIsReady()) initializeView();
	}
	
	public GitDroidApplication getGitDroidApplication() {
		return (GitDroidApplication)getActivity().getApplication();
	}
	
	public OAuthService getOAuthServiceInstance() {
		return getGitDroidApplication().getOAuthServiceInstance();
	}
	
	public UserService getUserServiceInstance() {
		if (userService == null) {
			userService = new UserService(this.getActivity());
		}
		return userService;
	}
	
	protected Uri getOAuthUri() {
		return getOAuthServiceInstance().getAuthUri();
	}
	
	protected User getCurrentUser() {
		return getGitDroidApplication().getCurrentUser();
	}
	
	protected String getAccessToken() {
		return getGitDroidApplication().getAccessToken();
	}
	
	/**
	 * Performs OAuth authentication
	 */
	protected void authenticate() {
		startActivity(new Intent(Intent.ACTION_VIEW, getOAuthUri()));
		getActivity().finish();
	}
	
	/**
	 * Check to see if this user has authenticated
	 * 
	 * @return true iff we have an oath token
	 */
	protected boolean isAuthenticated() {
		return getGitDroidApplication().getAccessToken() != null;
	}
	
	/**
	 * If we just got back from a call to the github auth url, grab the oath code and call for a token
	 * Then grab the current user and set that aside
	 * Runs asynchronously
	 */
	protected void initializeCommonData() {
		if (getAccessToken() == null) {
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
			return getOAuthServiceInstance().retrievAccessToken(uri);
		}
		
		@Override
		protected void onPostExecute(String result) {
			if (result == null) {
				// If we didn't get anything back - not auth'd yet
				authenticate();
			} else if (result == "") {
				// If we get an empty access code, that means auth failed
				getGitDroidApplication().updateAccessToken(null);
				getInitHandler().sendEmptyMessage(NO_ACCESS_CODE_MESSAGE);
			} else {
				// Update the access token
				getGitDroidApplication().updateAccessToken(result);
				// And retrieve the current user
				new RetrieveCurrentUserTask().execute();
			}
		}
		
	}
	
	/**
	 * Async task for getting the user object for the authenticated user
	 */
	private class RetrieveCurrentUserTask extends AsyncTask<Void, Void, User> {
		@Override
		protected User doInBackground(Void... params) {
			// Get the current auth'd user
			return getUserServiceInstance().retrieveCurrentUser();
		}
		
		@Override
		protected void onPostExecute(User currentUser) {
			getGitDroidApplication().setCurrentUser(currentUser);
			getInitHandler().sendEmptyMessage(INIT_DATA_MESSAGE);
		}
		
	}
	
	/**
	 * Initialization handler
	 */
	private class InitHandler extends Handler {
		private Context ctx;
		
		public InitHandler(Context ctx) {
			this.ctx = ctx;
		}
		
		@Override
    	public void handleMessage(Message message) {
			switch(message.what) {
			case INIT_DATA_MESSAGE:
				initializeData();
				break;
			case INIT_VIEW_MESSAGE:
				initializeView();
				break;
			case NO_ACCESS_CODE_MESSAGE:
				ErrorDisplay.errorToast(ctx, "Unable to authenticate with Github");
				break;
			}
		}
	}
}
