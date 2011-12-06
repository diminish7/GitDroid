package com.rushdevo.gitdroid.github.v3.models;

/**
 * @author jasonrush
 * Class representing a Github Gollum wiki page
 */
public class GollumPage extends BaseGithubModel {
	// Properties
	private String page_name;
	private String title;
	private String action;
	private String sha;
	private String html_url;
	
	// Getters and Setters
	public String getPage_name() {
		return page_name;
	}
	public void setPage_name(String pageName) {
		this.page_name = pageName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getSha() {
		return sha;
	}
	public void setSha(String sha) {
		this.sha = sha;
	}
	public String getHtmlUrl() {
		return html_url;
	}
	public void setHtmlUrl(String htmlUrl) {
		this.html_url = htmlUrl;
	}
}
