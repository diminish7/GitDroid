package com.rushdevo.gitdroid.github.v3.models;

/**
 * @author jasonrush
 * Class representing a collection of links for a pull request
 */
public class Links extends BaseGithubModel {
	// Properties
	private Link self;
	private Link html;
	private Link comments;
	private Link review_comments;
	
	// Getters and Setters
	public Link getSelf() {
		return self;
	}
	public void setSelf(Link self) {
		this.self = self;
	}
	public Link getHtml() {
		return html;
	}
	public void setHtml(Link html) {
		this.html = html;
	}
	public Link getComments() {
		return comments;
	}
	public void setComments(Link comments) {
		this.comments = comments;
	}
	public Link getReviewComments() {
		return review_comments;
	}
	public void setReviewComments(Link reviewComments) {
		this.review_comments = reviewComments;
	}
}
