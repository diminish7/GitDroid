package com.rushdevo.gitdroid.ui;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.rushdevo.gitdroid.github.v3.models.Comment;

/**
 * @author jasonrush
 * Custom adapter for showing comments in a list
 */
public class CommentsAdapter extends ArrayAdapter<Comment> {
	private List<Comment> comments;
	private Context ctx;
	
	/**
	 * Constructor
	 * @param context
	 * @param textViewResourceId
	 * @param comments
	 */
	public CommentsAdapter(Context context, int textViewResourceId, List<Comment> comments) {
		super(context, textViewResourceId, comments);
		this.comments = comments;
		this.ctx = context;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		CommentView view;
		Comment comment = comments.get(position);
		if (convertView == null) {
			view = new CommentView(ctx, comment);
		} else {
			view = (CommentView)convertView;
			if (view.getComment() != comment) {
				view.setComment(comment);
				view.invalidate();
			}
		}
		return view;
	};
	
	// Getters and Setters
	public void setComments(List<Comment> comments) {
		this.comments = comments;
		clear();
		for (Comment comment : comments) {
			add(comment);
		}
	}
}
