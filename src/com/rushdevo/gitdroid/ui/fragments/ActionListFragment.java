package com.rushdevo.gitdroid.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.rushdevo.gitdroid.R;
import com.rushdevo.gitdroid.listeners.ActionSelectedListener;
import com.rushdevo.gitdroid.utils.NonConfigurationChangeData;

/**
 * @author jasonrush
 * Main list fragment for displaying left-hand side menu
 */
public class ActionListFragment extends BaseListFragment {
	private ActionSelectedListener actionSelectedListener;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.action_list, null);
	}
	
	@Override
	protected void initializeData() {
		initializeAdapter();
	}

	@Override
	protected void initializeView() {
		initializeData();
	}

	@Override
	protected boolean viewIsReady() {
		return true;
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		String action = l.getItemAtPosition(position).toString(); 
		actionSelectedListener.OnActionSelected(action); 
	}
	
	@Override
	public Object onRetainCustomNonConfigurationInstance() {
		// No data to cache during config change
		return null;
	}
	
	@Override
	public void initializeNonConfigurationChangeData(NonConfigurationChangeData data) {
		// NOOP - No data to cache during config change
	}
	
	@Override
	protected Boolean hasMenu() {
		// This fragment never contributes menu items to the action bar
		return false;
	}
	
	@Override
	protected void handleRefresh() {
		// NOOP - Another fragment will deal with this
	}
	
	public void setActionSelected(ActionSelectedListener actionSelectedListener) {
		this.actionSelectedListener = actionSelectedListener;
	}
	
	private void initializeAdapter() {
		String[] actions = new String[] {
			getString(R.string.received_events),
			getString(R.string.public_activity),
			getString(R.string.repositories),
			getString(R.string.member_repositories),
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
