package com.rushdevo.gitdroid.ui;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.rushdevo.gitdroid.github.v3.models.Repository;

/**
 * @author jasonrush
 * Custom adapter for showing repositories in a list
 */
public class RepositoryAdapter extends ArrayAdapter<Repository> {
	private List<Repository> repositories;
	private Context ctx;
	
	/**
	 * Constructor
	 * @param ctx
	 * @param textViewResourceId
	 * @param repositories
	 */
	public RepositoryAdapter(Context context, int textViewResourceId, List<Repository> repositories) {
		super(context, textViewResourceId, repositories);
		this.repositories = repositories;
		this.ctx = context;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		RepositoryView view;
		Repository repo = repositories.get(position);
		if (convertView == null) {
			view = new RepositoryView(ctx, repo);
		} else {
			view = (RepositoryView)convertView;
			if (view.getRepository() != repo) {
				view.setRepository(repo);
				view.invalidate();
			}
		}
		return view;
	};
	
	// Getters and Setters
	public void setRepositories(List<Repository> repositories) {
		this.repositories = repositories;
		clear();
		for (Repository repo : repositories) {
			add(repo);
		}
	}
}
