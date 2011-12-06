package com.rushdevo.gitdroid.github.v3.models.event_payloads;

import com.rushdevo.gitdroid.github.v3.models.BaseGithubModel;

/**
 * @author jasonrush
 * Event payload for when a fork is applied
 */
public class ForkApplyEvent extends BaseGithubModel implements EventPayload {
	// Properties
	private String head;
	private String before;
	private String after;
	
	// Getters and Setters
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getBefore() {
		return before;
	}
	public void setBefore(String before) {
		this.before = before;
	}
	public String getAfter() {
		return after;
	}
	public void setAfter(String after) {
		this.after = after;
	}
}
