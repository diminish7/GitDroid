package com.rushdevo.gitdroid.github.v3.models;

import java.util.Date;

import com.rushdevo.gitdroid.R;
import com.rushdevo.gitdroid.github.v3.models.event_payloads.EventPayload;

/**
 * @author jasonrush
 * Class representing a Github event
 */
public class Event extends BaseGithubModel {
	// Properties
	private String type;
	private Boolean isPublic;
	private EventPayload payload;
	private Date created_at;
	private Repository repo;
	private User actor;
	private Organization org;
	
	public int getLayoutId() {
		if (payload == null) {
			return R.layout.default_event_list_item;
		} else {
			return payload.getLayoutId();
		}
	}
	
	// Getters and setters
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Boolean getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}
	public EventPayload getPayload() {
		return payload;
	}
	public void setPayload(EventPayload payload) {
		this.payload = payload;
	}
	public Date getCreatedAt() {
		return created_at;
	}
	public void setCreatedAt(Date createdAt) {
		this.created_at = createdAt;
	}
	public Repository getRepo() {
		return repo;
	}
	public void setRepo(Repository repo) {
		this.repo = repo;
	}
	public User getActor() {
		return actor;
	}
	public void setActor(User actor) {
		this.actor = actor;
	}
	public Organization getOrg() {
		return org;
	}
	public void setOrg(Organization org) {
		this.org = org;
	}
}
