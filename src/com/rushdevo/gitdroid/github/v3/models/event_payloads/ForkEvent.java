package com.rushdevo.gitdroid.github.v3.models.event_payloads;

import com.rushdevo.gitdroid.github.v3.models.BaseGithubModel;
import com.rushdevo.gitdroid.github.v3.models.Event;
import com.rushdevo.gitdroid.github.v3.models.Repository;

/**
 * @author jasonrush
 * Event payload for when a fork is created
 */
public class ForkEvent extends BaseGithubModel implements EventPayload {
	// Properties
	private Repository forkee;
	
	// Getters and Setters
	public Repository getForkee() {
		return this.forkee;
	}
	public void setForkee(Repository forkee) {
		this.forkee = forkee;
	}
	@Override
	public String getFullDescription(Event event) {
		if (event == null) return "(unknown fork event)";
		StringBuilder builder = new StringBuilder();
		builder.append(event.getActorName());
		builder.append(" forked ");
		builder.append(event.getRepoName());
		return builder.toString();
	}
	@Override
	public String getContent() {
		return "";
	}
}
