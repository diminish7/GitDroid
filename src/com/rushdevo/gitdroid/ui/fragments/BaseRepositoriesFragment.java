package com.rushdevo.gitdroid.ui.fragments;

import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rushdevo.gitdroid.R;
import com.rushdevo.gitdroid.github.v3.models.Repository;
import com.rushdevo.gitdroid.github.v3.services.RepositoryService;
import com.rushdevo.gitdroid.ui.RepositoryAdapter;
import com.rushdevo.gitdroid.utils.NonConfigurationChangeData;

/**
 * @author jasonrush
 * Base fragment for viewing repositories
 */
public abstract class BaseRepositoriesFragment extends BaseFragment {
	private RepositoryService service;
	private RepositoryAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.adapter = new RepositoryAdapter(getActivity(), android.R.layout.simple_list_item_1, getRepositories());
		setListAdapter(adapter);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.repositories, container, false);
	}
	
	@Override
	protected void initializeData() {
		if (viewIsReady()) initializeView();
		else {
			new QueryRepositoriesTask().execute();
		}
	}
	
	@Override
	protected boolean viewIsReady() {
		return getRepositories() != null && !getRepositories().isEmpty();
	}
	
	@Override
	protected void initializeView() {
		hideSpinner(R.id.repositories_container);
	}
	
	@Override
	public Object onRetainCustomNonConfigurationInstance() {
		return new NonConfigurationChangeData(this, getRepositories());
	}
	
	@Override
	protected void handleRefresh() {
		new QueryRepositoriesTask().execute();
	}
	
	public abstract void retrieveRepositories();
	
	// GETTERS AND SETTERS
	public abstract List<Repository> getRepositories();
	
	public RepositoryService getRepositoryServiceInstance() {
		if (service == null) service = new RepositoryService(getActivity());
		return service;
	}
	
	///////////// INNER CLASSES ////////////////////////
	private class QueryRepositoriesTask extends AsyncTask<Void, Void, Void> {
		protected void onPreExecute() {
			showSpinner(R.id.repositories_container);
		}
		
	     protected Void doInBackground(Void... args) {
	         retrieveRepositories();
	         return null;
	     }

	     protected void onPostExecute(Void arg) {
	    	 adapter.setRepositories(getRepositories());
	    	 adapter.notifyDataSetChanged();
	    	 hideSpinner(R.id.repositories_container);
	     }
	 }
}
