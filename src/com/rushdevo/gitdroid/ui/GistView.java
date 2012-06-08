package com.rushdevo.gitdroid.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rushdevo.gitdroid.R;
import com.rushdevo.gitdroid.github.v3.models.Gist;

/**
 * @author jasonrush
 * View for displaying a Github gist in a list
 */
public class GistView extends BaseListItemView {
private Gist gist;
	
	/**
	 * Constructor
	 * @param ctx
	 * @param gist
	 */
	public GistView(Context ctx, Gist gist) {
		super(ctx);
		init(ctx, gist);
	}
	
	/**
	 * Constructor
	 * @param ctx
	 * @param attrSet
	 */
	public GistView(Context ctx, AttributeSet attrs) {
		super(ctx, attrs);
		init(ctx, null);
	}
	
	/**
	 * Constructor
	 * @param ctx
	 */
	public GistView(Context ctx) {
		this(ctx, (Gist)null);
	}
	
	// Getters and Setters
	public Gist getGist() {
		return this.gist;
	}
	public void setGist(Gist gist) {
		this.gist = gist;
		updateView(this);
	}
	
	// Helpers
	private void init(Context ctx, Gist gist) {
		this.gist = gist;
		addView(inflateView(ctx, R.layout.gist_list_item), new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT));
	}
	
	protected void updateView(View view) {
		// Grab the views from the layout
		if (gist != null) {
			TextView idView = (TextView)view.findViewById(R.id.gist_id);
			TextView descriptionView = (TextView)view.findViewById(R.id.gist_description);
			TextView creatorAndTimestampView = (TextView)view.findViewById(R.id.gist_creator_and_timestamp);
			idView.setText("gist: " + gist.getId());
			descriptionView.setText(gist.getDescription());
			creatorAndTimestampView.setText(gist.getFormattedDateAndByString());
		}
	}
}
