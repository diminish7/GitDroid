package com.rushdevo.gitdroid.github.v3.models.event_payloads;

import com.rushdevo.gitdroid.R;
import com.rushdevo.gitdroid.github.v3.models.BaseGithubModel;
import com.rushdevo.gitdroid.github.v3.models.Repository;

/**
 * @author jasonrush
 * Event payload for when a fork is created
 */
public class ForkEvent extends BaseGithubModel implements EventPayload {
	// Properties
	private Repository forkee;
	
	// Getters and Setters
	public int getLayoutId() {
		return R.layout.default_event_list_item;
	}
	public Repository getForkee() {
		return this.forkee;
	}
	public void setForkee(Repository forkee) {
		this.forkee = forkee;
	}
}
