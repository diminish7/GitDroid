package com.rushdevo.gitdroid.github.v3.models;

import java.util.Date;

/**
 * @author jasonrush
 * Class representing a release milestone
 */
public class Milestone extends BaseGithubModel {
	// Properties
	private String url;
	private Integer number;
	private String state;
	private String title;
	private String description;
	private User creator;
	private Integer open_issues;
	private Integer closed_issues;
	private Date created_at;
	private Date due_on;
	
	// Getters and Setters
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	public Integer getOpenIssues() {
		return open_issues;
	}
	public void setOpenIssues(Integer openIssues) {
		this.open_issues = openIssues;
	}
	public Integer getClosedIssues() {
		return closed_issues;
	}
	public void setClosedIssues(Integer closedIssues) {
		this.closed_issues = closedIssues;
	}
	public Date getCreatedAt() {
		return created_at;
	}
	public void setCreatedAt(Date createdAt) {
		this.created_at = createdAt;
	}
	public Date getDueOn() {
		return due_on;
	}
	public void setDueOn(Date dueOn) {
		this.due_on = dueOn;
	}
}
