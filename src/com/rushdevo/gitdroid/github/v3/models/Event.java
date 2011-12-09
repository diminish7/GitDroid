package com.rushdevo.gitdroid.github.v3.models;

import java.util.Date;

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
	
	// Getters and setters
	public String getActionVerb() {
		if (payload == null) return "did something";
		else return payload.getActionVerb();
	}
	public String getActionSubject() {
		if (payload == null) return "something";
		else return payload.getActionSubject();
	}
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
		else return actor.getAvatarUrl();
	}
	public String getContent() {
		if (payload == null) return "";
		else return payload.getContent();
	}
	public String getFullDescription() {
		StringBuilder builder = new StringBuilder();
		builder.append(getActionSubject());
		builder.append(" ");
		builder.append(getActionVerb());
		builder.append(" on ");
		builder.append(getActionSubject());
		builder.append(" on ");
		builder.append(getRepoName());
		return builder.toString();
	}
	public String getTimestamp() {
		// TODO: If less than an hour ago: 'n minutes ago'
		//		 If less than a day ago: 'n hours ago'
		// 		 If more than a day ago: 'Month dd, yyyy'
		return "";
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
