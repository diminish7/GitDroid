package com.rushdevo.gitdroid.github.v3.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.rushdevo.gitdroid.github.v3.models.event_payloads.EventPayload;

/**
 * @author jasonrush
 * Class representing a Github event
 */
public class Event extends BaseGithubModel {
	// Consts
	private static final int HOUR_IN_SECONDS = 60*60;
	private static final int DAY_IN_SECONDS = HOUR_IN_SECONDS*24;
	private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("MMMM d, yyyy");
	
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
	 * Returns a formatted time for the event
	 * If it was less than an hour ago, it will be 'n minute(s) ago'
	 * If it was less than a day ago, it will be 'n hour(s) ago'
	 * Otherwise it will be 'Month dd, yyyy'
	 * 
	 * @return The formatted date expression
	 */
	public String getTimestamp() {
		if (getCreatedAt() == null) return "";
		long now = System.currentTimeMillis();
		Calendar cal = Calendar.getInstance();
		cal.setTime(getCreatedAt());
		long created = cal.getTimeInMillis();
		int diff = (int)((now-created)/1000);
		StringBuilder builder = new StringBuilder();
		if (diff < HOUR_IN_SECONDS) {
			// If less than an hour ago: 'n minutes ago'
			int diffMinutes = diff / 60;
			builder.append(diffMinutes);
			if (diffMinutes == 1) builder.append(" minute ago");
			else builder.append(" minutes ago");
		} else if (diff < DAY_IN_SECONDS) {
			// If less than a day ago: 'n hours ago'
			long diffHours = Math.round(diff / 60.0 / 60.0);
			builder.append("About ");
			if (diffHours == 1) builder.append("an hour ago");
			else {
				builder.append(diffHours);
				builder.append(" hours ago");
			}
		} else {
			// If more than a day ago: 'Month dd, yyyy'
			builder.append(dateFormatter.format(getCreatedAt()));
		}
		return builder.toString();
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
