package com.rushdevo.gitdroid.github.v3.models.event_payloads;

import com.rushdevo.gitdroid.github.v3.models.BaseGithubModel;
import com.rushdevo.gitdroid.github.v3.models.Comment;

/**
 * @author jasonrush
 * Event payload for a commit comment
 */
public class CommitComment extends BaseGithubModel implements EventPayload {
	// Properties
	private Comment comment;
	
	// Getters and Setters
	public Comment getComment() {
		return this.comment;
	}
	
	public void setComment(Comment comment) {
		this.comment = comment;
	}
}
