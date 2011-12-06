package com.rushdevo.gitdroid.github.v3.models.event_payloads;

import com.rushdevo.gitdroid.github.v3.models.BaseGithubModel;
import com.rushdevo.gitdroid.github.v3.models.Comment;
import com.rushdevo.gitdroid.github.v3.models.Issue;

/**
 * @author jasonrush
 * Event payload for issue comments
 */
public class IssueCommentEvent extends BaseGithubModel implements EventPayload {
	// Properties
	private String action;
	private Issue issue;
	private Comment comment;
	
	// Getters and Setters
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Issue getIssue() {
		return issue;
	}
	public void setIssue(Issue issue) {
		this.issue = issue;
	}
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
}
