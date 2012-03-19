package com.rushdevo.gitdroid.github.v3.models.event_payloads;

import com.rushdevo.gitdroid.github.v3.models.BaseGithubModel;
import com.rushdevo.gitdroid.github.v3.models.Event;
import com.rushdevo.gitdroid.github.v3.models.GollumPage;

/**
 * @author jasonrush
 * Event payload for a gollum event (wiki page created/updated/deleted)
 */
public class GollumEvent extends BaseGithubModel implements EventPayload {
	// Properties
	private GollumPage[] pages;
	
	// Getters and Setters
	public GollumPage[] getPages() {
		return this.pages;
	}
	public void setPages(GollumPage[] pages) {
		this.pages = pages;
	}
	@Override
	public String getFullDescription(Event event) {
		if (event == null) return "(unknown wiki event)";
		StringBuilder builder = new StringBuilder();
		builder.append(event.getActorName());
		builder.append(" modified wiki pages on ");
		builder.append(event.getRepoName());
		return builder.toString();
	}
	@Override
	public String getContent() {
		return "";
	}
}
