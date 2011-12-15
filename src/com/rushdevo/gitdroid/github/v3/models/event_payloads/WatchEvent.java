package com.rushdevo.gitdroid.github.v3.models.event_payloads;

import com.rushdevo.gitdroid.github.v3.models.BaseGithubModel;
import com.rushdevo.gitdroid.github.v3.models.Event;

/**
 * @author jasonrush
 * Event payload for when a repo is watched
 */
public class WatchEvent extends BaseGithubModel implements EventPayload {
	// Properties
	private String action;
	
	// Getters and Setters
	public String getAction() {
		return this.action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	@Override
	public String getFullDescription(Event event) {
		if (event == null) return "(unknown watch event)";
		StringBuilder builder = new StringBuilder();
		builder.append(event.getActorName());
		builder.append(" started watching ");
		builder.append(event.getRepoName());
		return builder.toString();
	}
	@Override
	public String getContent() {
		return "";
	}
}
