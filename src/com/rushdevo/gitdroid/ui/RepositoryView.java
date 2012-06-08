package com.rushdevo.gitdroid.ui;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rushdevo.gitdroid.R;
import com.rushdevo.gitdroid.github.v3.models.Repository;

/**
 * @author jasonrush
 * View for displaying a Github repository
 */
public class RepositoryView extends BaseListItemView {
	private Repository repository;
	
	/**
	 * Constructor
	 * @param ctx
	 * @param repository
	 */
	public RepositoryView(Context ctx, Repository repository) {
		super(ctx);
		this.repository = repository;
		addView(inflateView(ctx, R.layout.repository_list_item), new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT));
	}
	
	// Getters and Setters
	public Repository getRepository() {
		return this.repository;
	}
	public void setRepository(Repository repository) {
		this.repository = repository;
		updateView(this);
	}
	
	// Helpers
	protected void updateView(View view) {
		// Grab the views from the layout
		TextView nameView = (TextView)view.findViewById(R.id.repository_name);
		TextView descriptionView = (TextView)view.findViewById(R.id.repository_description);
		TextView urlView = (TextView)view.findViewById(R.id.repository_homepage);
		// Set the content of the views
		nameView.setText(repository.getName());
		descriptionView.setText(repository.getDescription());
		urlView.setText(repository.getHomepage());
	}
}
