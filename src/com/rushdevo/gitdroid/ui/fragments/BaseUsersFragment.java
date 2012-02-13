package com.rushdevo.gitdroid.ui.fragments;

import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rushdevo.gitdroid.R;
import com.rushdevo.gitdroid.github.v3.models.User;
import com.rushdevo.gitdroid.github.v3.services.UserService;
import com.rushdevo.gitdroid.ui.UserAdapter;

/**
 * @author jasonrush
 * Base fragment for displaying lists of users (used by FollowersFragment and FollowingFragment, for example)
 */
public abstract class BaseUsersFragment extends BaseFragment {
	private UserService service;
	private UserAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.adapter = new UserAdapter(getActivity(), android.R.layout.simple_list_item_1, getUsers());
		setListAdapter(adapter);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.users, container, false);
	}
	
	@Override
	protected void initializeData() {
		if (viewIsReady()) initializeView();
		else {
			new QueryUsersTask().execute();
		}
	}
	
	@Override
	protected boolean viewIsReady() {
		return getUsers() != null && !getUsers().isEmpty();
	}
	
	@Override
	protected void initializeView() {
		hideSpinner(R.id.users_container);
	}
	
	public abstract void retrieveUsers();
	
	// GETTERS AND SETTERS
	public abstract List<User> getUsers();
	
	public UserService getUserServiceInstance() {
		if (service == null) service = new UserService(getActivity());
		return service;
	}
	
	///////////// INNER CLASSES ////////////////////////
	private class QueryUsersTask extends AsyncTask<Void, Void, Void> {
		protected void onPreExecute() {
			showSpinner(R.id.users_container);
		}
		
	     protected Void doInBackground(Void... args) {
	         retrieveUsers();
	         return null;
	     }

	     protected void onPostExecute(Void arg) {
	    	 adapter.setUsers(getUsers());
	    	 adapter.notifyDataSetChanged();
	    	 hideSpinner(R.id.users_container);
	     }
	 }
}
