package com.rushdevo.gitdroid.github.v3.models;

import java.util.List;

import android.content.Context;

/**
 * @author jasonrush
 * Interface for things that have comments associated with them
 */
public interface Commentable {
	public String getDisplayName();
	public List<Comment> getAllComments();
	public void retrieveComments(Context context);
	public void addComment(String commentBody, Context context);
}
