package com.rushdevo.gitdroid.github.v3.models.event_payloads;

import com.rushdevo.gitdroid.github.v3.models.BaseGithubModel;
import com.rushdevo.gitdroid.github.v3.models.Comment;
import com.rushdevo.gitdroid.github.v3.models.Event;
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
	@Override
	public String getFullDescription(Event event) {
		if (event == null) return "(unknown issue comment event)";
		StringBuilder builder = new StringBuilder();
		builder.append(event.getActorName());
		builder.append(" commented on ");
		if (issue == null || issue.getNumber() == null) builder.append("an issue");
		else {
			builder.append("issue ");
			builder.append(issue.getNumber());
		}
		builder.append(" on ");
		builder.append(event.getRepoName());
		return builder.toString();
	}
	@Override
	public String getContent() {
		if (comment == null || comment.getBody() == null) return "";
		else return comment.getPartialBody();
	}
}
