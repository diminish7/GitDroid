package com.rushdevo.gitdroid.github.v3.models.event_payloads;

import com.rushdevo.gitdroid.github.v3.models.BaseGithubModel;
import com.rushdevo.gitdroid.github.v3.models.Event;
import com.rushdevo.gitdroid.github.v3.models.PullRequest;
import com.rushdevo.gitdroid.utils.StringUtils;

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
	@Override
	public String getFullDescription(Event event) {
		if (event == null) return "(unknown pull request event)";
		StringBuilder builder = new StringBuilder();
		builder.append(event.getActorName());
		builder.append(" ");
		if (action == null) builder.append("did something to");
		else builder.append(action);
		builder.append(" ");
		if (number == null) builder.append("a pull request");
		else {
			builder.append("pull request ");
			builder.append(number);
		}
		builder.append(" on ");
		builder.append(event.getRepoName());
		return builder.toString();
	}
	@Override
	public String getContent() {
		if (pull_request == null || pull_request.getTitle() == null) return "";
		else return StringUtils.getTruncatedString(pull_request.getTitle());
	}
}
