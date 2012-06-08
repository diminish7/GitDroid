package com.rushdevo.gitdroid.github.v3.models;

import java.util.Date;

import com.rushdevo.gitdroid.utils.DateUtils;
import com.rushdevo.gitdroid.utils.StringUtils;

/**
 * @author jasonrush
 * Class representing a comment on Github
 */
public class Comment extends BaseGithubModel {
	// Properties
	private String url;
	private Integer id;
	private String body;
	private String path;
	private Integer position;
	private String commit_id;
	private User user;
	private Date created_at;
	private Date updated_at;
	
	// Getters and Setters
	public String getPartialCommitId() {
		if (commit_id == null || commit_id.length() < 7) return commit_id;
		else return commit_id.substring(0, 7);
	}
	public String getPartialBody() {
		return StringUtils.getTruncatedString(body);
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public String getCommitId() {
		return commit_id;
	}
	public void setCommitId(String commitId) {
		this.commit_id = commitId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
	
	public String getFormattedDateAndByString() {
		StringBuilder builder = new StringBuilder();
		String formattedDate = DateUtils.getTimestamp(getCreatedAt());
		if (formattedDate != null && formattedDate.length() > 0) {
			builder.append(formattedDate);
			builder.append(" ");
		}
		builder.append("by ");
		builder.append(getUser().getLogin());
		return builder.toString();
	}
}
