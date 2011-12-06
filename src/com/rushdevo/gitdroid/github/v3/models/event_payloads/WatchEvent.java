package com.rushdevo.gitdroid.github.v3.models.event_payloads;

import com.rushdevo.gitdroid.github.v3.models.BaseGithubModel;

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
}
