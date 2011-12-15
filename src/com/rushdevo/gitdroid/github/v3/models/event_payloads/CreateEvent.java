package com.rushdevo.gitdroid.github.v3.models.event_payloads;

import com.rushdevo.gitdroid.github.v3.models.BaseGithubModel;
import com.rushdevo.gitdroid.github.v3.models.Event;
import com.rushdevo.gitdroid.utils.StringUtils;

/**
 * @author jasonrush
 * Event payload for when a repo, branch or tag is created
 */
public class CreateEvent extends BaseGithubModel implements EventPayload {
	// Properties
	private String ref_type;	// "repository", "branch" or "tag"
	private String ref;			// The git ref
	private String master_branch;
	private String description;
	
	// Getters and Setter
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
	public String getMasterBranch() {
		return master_branch;
	}
	public void setMasterBranch(String masterBranch) {
		this.master_branch = masterBranch;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String getFullDescription(Event event) {
		if (event == null) return "(unknown create event)";
		StringBuilder builder = new StringBuilder();
		builder.append(event.getActorName());
		builder.append(" created ");
		if (ref_type == null) builder.append("something");
		else builder.append(ref_type);
		if (ref_type != "repository") builder.append(" on ");
		builder.append(event.getRepoName());
		return builder.toString();
	}
	@Override
	public String getContent() {
		return StringUtils.getTruncatedString(getDescription());
	}
}
