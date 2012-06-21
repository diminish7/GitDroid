package com.rushdevo.gitdroid.github.v3.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;

import com.rushdevo.gitdroid.github.v3.services.GistService;
import com.rushdevo.gitdroid.utils.DateUtils;

/**
 * @author jasonrush
 * Class representing a Github Gist
 */
public class Gist extends BaseGithubModel implements Commentable {
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
	private List<Comment> allComments;
	
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
	public List<Comment> getAllComments() {
		if (allComments == null) allComments = new ArrayList<Comment>();
		return allComments;
	}
	public void setAllComments(List<Comment> comments) {
		this.allComments = comments;
	}
	public String getDisplayName() {
		StringBuilder builder = new StringBuilder();
		builder.append("Gist ");
		builder.append(getId());
		return builder.toString();
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
	
	public void retrieveComments(Context context) {
		setAllComments(new GistService(context).retrieveComments(this));
	}
}
