package com.rushdevo.gitdroid.github.v3.models.event_payloads;

import com.rushdevo.gitdroid.github.v3.models.BaseGithubModel;
import com.rushdevo.gitdroid.github.v3.models.Event;
import com.rushdevo.gitdroid.github.v3.models.Gist;
import com.rushdevo.gitdroid.utils.StringUtils;

/**
 * @author jasonrush
 * Event payload for when a gist is created or updated
 */
public class GistEvent extends BaseGithubModel implements EventPayload {
	// Properties
	private String action;	// "create" or "update"
	private Gist gist;
	
	// Getters and Setters
	public String getAction() {
		return this.action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Gist getGist() {
		return this.gist;
	}
	public void setGist(Gist gist) {
		this.gist = gist;
	}
	@Override
	public String getFullDescription(Event event) {
		if (event == null) return "(unknown gist event)";
		StringBuilder builder = new StringBuilder();
		builder.append(event.getActorName());
		builder.append(" ");
		if (action == null) builder.append("did something to ");
		else {
			builder.append(action);
			builder.append("d ");	// Past tense
		}
		if (gist == null || gist.getId() == null) builder.append("a gist");
		else {
			builder.append("gist: ");
			builder.append(gist.getId());
		}
		return builder.toString();
	}
	@Override
	public String getContent() {
		if (gist == null || gist.getDescription() == null) return "";
		else return StringUtils.getTruncatedString(gist.getDescription());
	}
}
