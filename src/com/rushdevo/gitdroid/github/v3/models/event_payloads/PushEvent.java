package com.rushdevo.gitdroid.github.v3.models.event_payloads;

import com.rushdevo.gitdroid.github.v3.models.BaseGithubModel;
import com.rushdevo.gitdroid.github.v3.models.Commit;
import com.rushdevo.gitdroid.github.v3.models.Event;

/**
 * @author jasonrush
 * Event payload for a push
 */
public class PushEvent extends BaseGithubModel implements EventPayload {
	// Properties
	private String head;
	private String ref;
	private Integer size;
	private Commit[] commits;
	
	// Getters and Setters
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getShortRef() {
		if (ref == null) return null;
		else {
			String[] parts = ref.split("/");
			return parts[parts.length-1];
		}
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Commit[] getCommits() {
		return commits;
	}
	public void setCommits(Commit[] commits) {
		this.commits = commits;
	}
	@Override
	public String getFullDescription(Event event) {
		if (event == null) return "(unknown push event)";
		StringBuilder builder = new StringBuilder();
		builder.append(event.getActorName());
		builder.append(" pushed to ");
		if (ref != null) {
			builder.append(getShortRef());
			builder.append(" at ");
		}
		builder.append(event.getRepoName());
		return builder.toString();
	}
	@Override
	public String getContent() {
		StringBuilder builder = new StringBuilder();
		if (getCommits() != null && getCommits().length > 0) {
			Commit commit = getCommits()[0];
			builder.append(commit.getPartialSha());
			builder.append(" ");
			builder.append(commit.getPartialMessage());
			if (getCommits().length > 1) {
				int additionalCommits = getCommits().length - 1;
				String pluralizedCommit = (additionalCommits == 1) ? "commit" : "commits";
				builder.append(" (and ");
				builder.append(additionalCommits);
				builder.append(" other ");
				builder.append(pluralizedCommit);
				builder.append(")");
			}
		}
		return builder.toString();
	}
}
