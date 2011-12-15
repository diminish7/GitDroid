package com.rushdevo.gitdroid.github.v3.models.event_payloads;

import com.rushdevo.gitdroid.github.v3.models.BaseGithubModel;
import com.rushdevo.gitdroid.github.v3.models.Event;

/**
 * @author jasonrush
 * Event payload for when a private repo is open-sourced
 * This is an empty payload
 */
public class PublicEvent extends BaseGithubModel implements EventPayload {
	@Override
	public String getFullDescription(Event event) {
		if (event == null) return "(unknown public event)";
		StringBuilder builder = new StringBuilder();
		builder.append(event.getActorName());
		builder.append(" made ");
		builder.append(event.getRepoName());
		builder.append(" public");
		return builder.toString();
	}
	// Getters and Setters
	@Override
	public String getContent() {
		return "";
	}
}
