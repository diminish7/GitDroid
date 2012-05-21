package com.rushdevo.gitdroid.ui.fragments;

import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.rushdevo.gitdroid.R;
import com.rushdevo.gitdroid.github.v3.models.Gist;
import com.rushdevo.gitdroid.github.v3.services.GistService;
import com.rushdevo.gitdroid.ui.GistAdapter;
import com.rushdevo.gitdroid.utils.NonConfigurationChangeData;

/**
 * @author jasonrush
 * Display fragment for gist content
 */
public class GistsFragment extends BaseListFragment {
	private GistService service;
	private GistAdapter adapter;
	private List<Gist> gists = new ArrayList<Gist>();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.adapter = new GistAdapter(getActivity(), android.R.layout.simple_list_item_1, getGists());
		setListAdapter(adapter);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.gists, container, false);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Gist gist = (Gist)l.getItemAtPosition(position);
		getObjectSelectedListener().OnObjectSelected(gist, new GistFragment());
	}
	
	@Override
	protected void initializeData() {
		if (viewIsReady()) initializeView();
		else {
			new QueryGistsTask().execute();
		}
	}
	
	@Override
	protected boolean viewIsReady() {
		return getGists() != null && !getGists().isEmpty();
	}
	
	@Override
	protected void initializeView() {
		hideSpinner(R.id.gists_container);
	}
	
	@Override
	public Object onRetainCustomNonConfigurationInstance() {
		return new NonConfigurationChangeData(this, getGists());
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void initializeNonConfigurationChangeData(NonConfigurationChangeData data) {
		if (data != null) {
			Object obj = data.getData(this);
			if (obj != null) {
				gists = (List<Gist>)obj;
			}
		}
	}
	
	@Override
	protected void handleRefresh() {
		new QueryGistsTask().execute();
	}
	
	public void retrieveGists() {
		gists = getGistServiceInstance().retrieveGists();
	}
	
	// Getters and Setters
	public List<Gist> getGists() {
		return this.gists;
	}
	
	public GistService getGistServiceInstance() {
		if (service == null) service = new GistService(getActivity());
		return service;
	}
	
	///////////// INNER CLASSES ////////////////////////
	private class QueryGistsTask extends AsyncTask<Void, Void, Void> {
		protected void onPreExecute() {
			showSpinner(R.id.gists_container);
		}
		
	     protected Void doInBackground(Void... args) {
	         retrieveGists();
	         return null;
	     }

	     protected void onPostExecute(Void arg) {
	    	 adapter.setGists(getGists());
	    	 adapter.notifyDataSetChanged();
	    	 hideSpinner(R.id.gists_container);
	     }
	 }
}
