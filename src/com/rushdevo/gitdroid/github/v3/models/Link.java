package com.rushdevo.gitdroid.github.v3.models;

/**
 * @author jasonrush
 * Class representing a Github link
 */
public class Link extends BaseGithubModel {
	// Properties
	private String href;
	
	// Getters and Setters
	public String getHref() {
		return this.href;
	}
	public void setHref(String href) {
		this.href = href;
	}
}
