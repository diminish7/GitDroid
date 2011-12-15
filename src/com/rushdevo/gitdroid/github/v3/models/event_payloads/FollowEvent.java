package com.rushdevo.gitdroid.github.v3.models.event_payloads;

import com.rushdevo.gitdroid.github.v3.models.BaseGithubModel;
import com.rushdevo.gitdroid.github.v3.models.Event;
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
	@Override
	public String getFullDescription(Event event) {
		if (event == null) return "(unknown follow event)";
		StringBuilder builder = new StringBuilder();
		builder.append(event.getActorName());
		builder.append(" started following ");
		if (target == null || target.getName() == null) builder.append("someone");
		else builder.append(target.getName());
		return builder.toString();
	}
	@Override
	public String getContent() {
		return "";
	}
}
