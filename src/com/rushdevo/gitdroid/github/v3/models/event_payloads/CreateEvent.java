package com.rushdevo.gitdroid.github.v3.models.event_payloads;

import com.rushdevo.gitdroid.github.v3.models.BaseGithubModel;

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
	public String getActionVerb() {
		// TODO Auto-generated method stub
		return "";
	}
	@Override
	public String getActionSubject() {
		// TODO Auto-generated method stub
		return "";
	}
	@Override
	public String getContent() {
		// TODO Auto-generated method stub
		return "";
	}
}
