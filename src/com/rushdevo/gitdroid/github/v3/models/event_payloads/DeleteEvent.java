package com.rushdevo.gitdroid.github.v3.models.event_payloads;

import com.rushdevo.gitdroid.github.v3.models.BaseGithubModel;
import com.rushdevo.gitdroid.github.v3.models.Event;

/**
 * @author jasonrush
 * Event payload for when a branch or tag is deleted
 */
public class DeleteEvent extends BaseGithubModel implements EventPayload {
	// Properties
	private String ref_type;	// "branch" or "tag"
	private String ref;			// The git ref
	
	// Getters and Setters
	public String getRefType() {
		return ref_type;
	}
	public void setRefType(String refType) {
		this.ref_type = refType;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	@Override
	public String getFullDescription(Event event) {
		if (event == null) return "(unknown delete event)";
		StringBuilder builder = new StringBuilder();
		builder.append(event.getActorName());
		builder.append(" deleted ");
		if (ref_type == null) builder.append("something");
		else builder.append(ref_type);
		if (ref_type != "repository") builder.append(" on ");
		builder.append(event.getRepoName());
		return builder.toString();
	}
	@Override
	public String getContent() {
		return "";
	}
}
