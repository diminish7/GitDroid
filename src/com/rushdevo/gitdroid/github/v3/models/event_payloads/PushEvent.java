package com.rushdevo.gitdroid.github.v3.models.event_payloads;

import com.rushdevo.gitdroid.github.v3.models.BaseGithubModel;
import com.rushdevo.gitdroid.github.v3.models.Commit;

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
}
