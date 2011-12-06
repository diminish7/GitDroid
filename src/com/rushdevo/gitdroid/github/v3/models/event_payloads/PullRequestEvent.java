package com.rushdevo.gitdroid.github.v3.models.event_payloads;

import com.rushdevo.gitdroid.github.v3.models.BaseGithubModel;
import com.rushdevo.gitdroid.github.v3.models.PullRequest;

/**
 * @author jasonrush
 * Event payload for when a pull request is opened, closed, synchronized or reopened
 */
public class PullRequestEvent extends BaseGithubModel implements EventPayload {
	// Properties
	private String action; 	// "opened", "closed", "synchronized" or "reopened"
	private Integer number;
	private PullRequest pull_request;
	
	// Getters and Setters
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public PullRequest getPullRequest() {
		return pull_request;
	}
	public void setPullRequest(PullRequest pullRequest) {
		this.pull_request = pullRequest;
	}
}
