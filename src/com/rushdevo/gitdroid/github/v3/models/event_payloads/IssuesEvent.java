package com.rushdevo.gitdroid.github.v3.models.event_payloads;

import com.rushdevo.gitdroid.github.v3.models.BaseGithubModel;
import com.rushdevo.gitdroid.github.v3.models.Issue;

/**
 * @author jasonrush
 * Event payload for when an issue is opened, closed or reopened
 */
public class IssuesEvent extends BaseGithubModel implements EventPayload {
	// Properties
	private String action;	// "opened", "closed" or "reopened"
	private Issue issue;
	
	// Getters and 
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
	
}
