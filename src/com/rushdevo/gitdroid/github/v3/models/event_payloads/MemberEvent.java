package com.rushdevo.gitdroid.github.v3.models.event_payloads;

import com.rushdevo.gitdroid.R;
import com.rushdevo.gitdroid.github.v3.models.BaseGithubModel;
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
	public int getLayoutId() {
		return R.layout.default_event_list_item;
	}
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
}
