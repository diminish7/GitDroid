package com.rushdevo.gitdroid.github.v3.models.event_payloads;

import com.rushdevo.gitdroid.github.v3.models.BaseGithubModel;
import com.rushdevo.gitdroid.github.v3.models.Event;
import com.rushdevo.gitdroid.github.v3.models.Issue;

/**
 * @author jasonrush
 * Event payload for when an issue is opened, closed or reopened
 */
public class IssuesEvent extends BaseGithubModel implements EventPayload {
	// Properties
	private String action;	// "opened", "closed" or "reopened"
	private Issue issue;
	
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
	@Override
	public String getFullDescription(Event event) {
		if (event == null) return "(unknown issue event)";
		StringBuilder builder = new StringBuilder();
		builder.append(event.getActorName());
		builder.append(" ");
		if (action == null) builder.append("did something to");
		else builder.append(action);
		builder.append(" ");
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
		if (issue == null || issue.getTitle() == null) return "";
		else return issue.getTitle();
	}
}
