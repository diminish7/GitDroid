package com.rushdevo.gitdroid.github.v3.models;

import java.util.Date;

import com.rushdevo.gitdroid.github.v3.models.event_payloads.EventPayload;
import com.rushdevo.gitdroid.utils.DateUtils;

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
	
	// Getters and setters
	public String getRepoName() {
		if (repo == null) return "some repo";
		else return repo.getName();
		
	}
	public String getActorName() {
		if (actor == null) return "someone";
		else return actor.getLogin();
	}
	public String getActorAvatarUrl() {
		if (actor == null) return "";
		else return actor.getAvatarOrGravatarUrl();
	}
	public String getContent() {
		if (payload == null) return "";
		else return payload.getContent();
	}
	public String getFullDescription() {
		if (payload == null) return "(unknown event)";
		else return payload.getFullDescription(this);
	}
	/**
	 * Returns a formatted time for the created at date of the event
	 * If it was less than an hour ago, it will be 'n minute(s) ago'
	 * If it was less than a day ago, it will be 'n hour(s) ago'
	 * Otherwise it will be 'Month dd, yyyy'
	 * 
	 * @return The formatted date expression
	 */
	public String getTimestamp() {
		return DateUtils.getTimestamp(getCreatedAt());
	}
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
