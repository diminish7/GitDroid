package com.rushdevo.gitdroid.github.v3.models.event_payloads;

import com.rushdevo.gitdroid.R;
import com.rushdevo.gitdroid.github.v3.models.BaseGithubModel;

/**
 * @author jasonrush
 * Event payload for when a private repo is open-sourced
 * This is an empty payload
 */
public class PublicEvent extends BaseGithubModel implements EventPayload {
	public int getLayoutId() {
		return R.layout.default_event_list_item;
	}
}
