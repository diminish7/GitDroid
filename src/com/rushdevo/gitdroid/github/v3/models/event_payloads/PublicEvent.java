package com.rushdevo.gitdroid.github.v3.models.event_payloads;

import com.rushdevo.gitdroid.github.v3.models.BaseGithubModel;

/**
 * @author jasonrush
 * Event payload for when a private repo is open-sourced
 * This is an empty payload
 */
public class PublicEvent extends BaseGithubModel implements EventPayload {
	
	// Getters and Setters
	@Override
	public String getActionVerb() {
		// TODO Auto-generated method stub
		return "";
	}
	@Override
	public String getActionSubject() {
		// TODO Auto-generated method stub
		return "";
	}
	@Override
	public String getContent() {
		// TODO Auto-generated method stub
		return "";
	}
}
