package com.rushdevo.gitdroid.github.v3.models.event_payloads;

import com.rushdevo.gitdroid.github.v3.models.BaseGithubModel;
import com.rushdevo.gitdroid.github.v3.models.Gist;

/**
 * @author jasonrush
 * Event payload for when a gist is created or updated
 */
public class GistEvent extends BaseGithubModel implements EventPayload {
	// Properties
	private String action;	// "create" or "update"
	private Gist gist;
	
	// Getters and Setters
	public String getAction() {
		return this.action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Gist getGist() {
		return this.gist;
	}
	public void setGist(Gist gist) {
		this.gist = gist;
	}
}
