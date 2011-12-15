package com.rushdevo.gitdroid.github.v3.models.event_payloads;

import com.rushdevo.gitdroid.github.v3.models.BaseGithubModel;
import com.rushdevo.gitdroid.github.v3.models.Download;
import com.rushdevo.gitdroid.github.v3.models.Event;
import com.rushdevo.gitdroid.utils.StringUtils;

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
	@Override
	public String getFullDescription(Event event) {
		if (event == null) return "(unknown download event)";
		StringBuilder builder = new StringBuilder();
		builder.append(event.getActorName());
		builder.append(" downloaded ");
		if (download == null || download.getName() == null) builder.append("something");
		else builder.append(download.getName());
		builder.append(" from ");
		builder.append(event.getRepoName());
		return builder.toString();
	}
	@Override
	public String getContent() {
		return StringUtils.getTruncatedString(download.getDescription());
	}
}
