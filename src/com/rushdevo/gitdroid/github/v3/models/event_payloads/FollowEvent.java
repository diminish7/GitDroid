package com.rushdevo.gitdroid.github.v3.models.event_payloads;

import com.rushdevo.gitdroid.github.v3.models.BaseGithubModel;
import com.rushdevo.gitdroid.github.v3.models.User;

/**
 * @author jasonrush
 * Event payload for when a user was followed
 */
public class FollowEvent extends BaseGithubModel implements EventPayload {
	// Properties
	private User target;
	
	// Getters and Setters
	public User getTarget() {
		return this.target;
	}
	
	public void setTarget(User target) {
		this.target = target;
	}
}
