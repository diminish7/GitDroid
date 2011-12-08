package com.rushdevo.gitdroid.github.v3.models.event_payloads;

import com.rushdevo.gitdroid.R;
import com.rushdevo.gitdroid.github.v3.models.BaseGithubModel;

/**
 * @author jasonrush
 * Event payload for when a branch or tag is deleted
 */
public class DeleteEvent extends BaseGithubModel implements EventPayload {
	// Properties
	private String ref_type;	// "branch" or "tag"
	private String ref;			// The git ref
	
	// Getters and Setters
	public int getLayoutId() {
		return R.layout.default_event_list_item;
	}
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
}
