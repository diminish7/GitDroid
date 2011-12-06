package com.rushdevo.gitdroid.github.v3.models;

import java.util.Date;

/**
 * @author jasonrush
 * Class representing a Github Gist
 */
public class Gist extends BaseGithubModel {
	// TODO: This needs a custom deserializer to deal with the property 'public'
	// 		 Also, there are several other objects associated with this
	
	// Properties
	private String url;
	private String id;
	private String description;
	private Boolean isPublic;	// TODO: This is just 'public' in the JSON
	private User user;
	private Integer comments;
	private String html_url;
	private String git_pull_url;
	private String git_push_url;
	private Date created_at;
	
	// Getters and Setters
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getComments() {
		return comments;
	}
	public void setComments(Integer comments) {
		this.comments = comments;
	}
	public String getHtmlUrl() {
		return html_url;
	}
	public void setHtmlUrl(String htmlUrl) {
		this.html_url = htmlUrl;
	}
	public String getGitPullUrl() {
		return git_pull_url;
	}
	public void setGitPullUrl(String gitPullUrl) {
		this.git_pull_url = gitPullUrl;
	}
	public String getGitPushUrl() {
		return git_push_url;
	}
	public void setGitPushUrl(String gitPushUrl) {
		this.git_push_url = gitPushUrl;
	}
	public Date getCreatedAt() {
		return created_at;
	}
	public void setCreatedAt(Date createdAt) {
		this.created_at = createdAt;
	}
}
