package com.rushdevo.gitdroid.github.v3.models.event_payloads;

import com.rushdevo.gitdroid.github.v3.models.BaseGithubModel;
import com.rushdevo.gitdroid.github.v3.models.Event;

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
	@Override
	public String getFullDescription(Event event) {
		if (event == null) return "(unknown fork-applied event)";
		StringBuilder builder = new StringBuilder();
		builder.append(event.getActorName());
		builder.append(" applied fork ");
		if (head != null) {
			builder.append(head);
			builder.append(" ");
		}
		builder.append("to ");
		builder.append(event.getRepoName());
		return builder.toString();
	}
	@Override
	public String getContent() {
		return "";
	}
}
