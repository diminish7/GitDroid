package com.rushdevo.gitdroid.github.v3.models.event_payloads;

import com.rushdevo.gitdroid.github.v3.models.BaseGithubModel;
import com.rushdevo.gitdroid.github.v3.models.Event;
import com.rushdevo.gitdroid.github.v3.models.User;

/**
 * @author jasonrush
 * Event payload for when a user is added as a collaborator to a repo
 */
public class MemberEvent extends BaseGithubModel implements EventPayload {
	// Properties
	private User member;
	private String action; 	// Always "added"
	
	// Getters and Setters
	public User getMember() {
		return member;
	}
	public void setMember(User member) {
		this.member = member;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	@Override
	public String getFullDescription(Event event) {
		if (event == null) return "(unknown member event)";
		StringBuilder builder = new StringBuilder();
		builder.append(event.getActorName());
		if (action == null) builder.append(" added ");
		else {
			builder.append(" ");
			builder.append(action);
			builder.append(" ");
		}
		if (member == null || member.getName() == null) builder.append("someone");
		else builder.append(member.getName());
		builder.append(" to ");
		builder.append(event.getRepoName());
		return builder.toString();
	}
	@Override
	public String getContent() {
		return "";
	}
}
