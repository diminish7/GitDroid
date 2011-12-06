package com.rushdevo.gitdroid.github.v3.models.event_payloads;

import com.rushdevo.gitdroid.github.v3.models.BaseGithubModel;
import com.rushdevo.gitdroid.github.v3.models.Download;

/**
 * @author jasonrush
 * Event payload for when a download is created
 */
public class DownloadEvent extends BaseGithubModel implements EventPayload {
	// Properties
	private Download download;
	
	// Getters and Setters
	public Download getDownload() {
		return this.download;
	}
	
	public void setDownload(Download download) {
		this.download = download;
	}
}
