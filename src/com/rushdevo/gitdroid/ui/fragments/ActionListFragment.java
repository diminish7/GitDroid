package com.rushdevo.gitdroid.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.rushdevo.gitdroid.R;
import com.rushdevo.gitdroid.listeners.ActionSelected;

/**
 * @author jasonrush
 * Main list fragment for displaying left-hand side menu
 */
public class ActionListFragment extends ListFragment {
	private ActionSelected actionSelected;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initializeAdapter();
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		String action = l.getItemAtPosition(position).toString(); 
		actionSelected.OnActionSelected(action); 
	}
	
	public void setActionSelected(ActionSelected actionSelected) {
		this.actionSelected = actionSelected;
	}
	
	private void initializeAdapter() {
		String[] actions = new String[] {
			getString(R.string.events),
			getString(R.string.public_activity),
			getString(R.string.repositories),
			getString(R.string.collaborator_repositories),
			getString(R.string.watched_repositories),
			getString(R.string.followers),
			getString(R.string.following),
			getString(R.string.organizations),
			getString(R.string.gists)
		};
		
		ArrayAdapter<String> actionsAdapter = new ArrayAdapter<String>(getActivity(), R.layout.simple_list_item, actions);
		setListAdapter(actionsAdapter);
	}
}
