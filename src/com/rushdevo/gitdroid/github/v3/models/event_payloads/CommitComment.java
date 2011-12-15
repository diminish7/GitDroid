package com.rushdevo.gitdroid.github.v3.models.event_payloads;

import com.rushdevo.gitdroid.github.v3.models.BaseGithubModel;
import com.rushdevo.gitdroid.github.v3.models.Comment;
import com.rushdevo.gitdroid.github.v3.models.Event;

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
	@Override
	public String getFullDescription(Event event) {
		if (event == null) return "(unknown commit comment event)";
		StringBuilder builder = new StringBuilder();
		builder.append(event.getActorName());
		builder.append(" commented on ");
		if (comment == null || comment.getCommitId() == null) builder.append("a commit");
		else {
			builder.append("commit ");
			builder.append(comment.getPartialCommitId());
		}
		builder.append(" on ");
		builder.append(event.getRepoName());
		return builder.toString();
	}
	@Override
	public String getContent() {
		if (comment == null) return "";
		else return comment.getPartialBody();
	}
}
