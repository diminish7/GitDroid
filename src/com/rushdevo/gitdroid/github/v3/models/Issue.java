package com.rushdevo.gitdroid.github.v3.models;

import java.util.Date;

/**
 * @author jasonrush
 * Class representing a Github issue
 */
public class Issue extends BaseGithubModel {
	// Properties
	private String url;
	private String html_url;
	private Integer number;
	private String state;
	private String title;
	private String body;
	private User user;
	private Label[] labels;
	private User assignee;
	private Milestone milestone;
	private Integer comments;
	private PullRequest pull_request;
	private Date closed_at;
	private Date created_at;
	private Date updated_at;
	
	// Getters and Setters
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getHtmlUrl() {
		return html_url;
	}
	public void setHtmlUrl(String htmlUrl) {
		this.html_url = htmlUrl;
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
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Label[] getLabels() {
		return labels;
	}
	public void setLabels(Label[] labels) {
		this.labels = labels;
	}
	public User getAssignee() {
		return assignee;
	}
	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}
	public Milestone getMilestone() {
		return milestone;
	}
	public void setMilestone(Milestone milestone) {
		this.milestone = milestone;
	}
	public Integer getComments() {
		return comments;
	}
	public void setComments(Integer comments) {
		this.comments = comments;
	}
	public PullRequest getPullRequest() {
		return pull_request;
	}
	public void setPullRequest(PullRequest pullRequest) {
		this.pull_request = pullRequest;
	}
	public Date getClosedAt() {
		return closed_at;
	}
	public void setClosedAt(Date closedAt) {
		this.closed_at = closedAt;
	}
	public Date getCreatedAt() {
		return created_at;
	}
	public void setCreatedAt(Date createdAt) {
		this.created_at = createdAt;
	}
	public Date getUpdatedAt() {
		return updated_at;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updated_at = updatedAt;
	}
}
