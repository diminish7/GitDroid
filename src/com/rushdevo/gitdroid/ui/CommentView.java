package com.rushdevo.gitdroid.ui;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rushdevo.gitdroid.R;
import com.rushdevo.gitdroid.github.v3.models.Comment;

/**
 * @author jasonrush
 * View for displaying a Github comment in a list
 */
public class CommentView extends BaseListItemView {
	private Comment comment;
	
	/**
	 * Constructor
	 * @param ctx
	 * @param gist
	 */
	public CommentView(Context ctx, Comment comment) {
		super(ctx);
		this.comment = comment;
		addView(inflateView(ctx, R.layout.comment_list_item), new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT));
	}

	//////// GETTERS AND SETTERS ////////////
	public void setComment(Comment comment) {
		this.comment = comment;
		updateView(this);
	}

	public Comment getComment() {
		return comment;
	}
	
	///////// HELPERS /////////////////
	protected void updateView(View view) {
		// Grab the views from the layout
		TextView creatorAndTimestampView = (TextView)view.findViewById(R.id.comment_creator_and_timestamp);
		TextView bodyView = (TextView)view.findViewById(R.id.comment_body);
		
		creatorAndTimestampView.setText(comment.getFormattedDateAndByString());
		bodyView.setText(comment.getBody());
	}
}
