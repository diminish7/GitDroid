package com.rushdevo.gitdroid.github.v3.models;

/**
 * @author jasonrush
 * Class representing a Github commit
 */
public class Commit extends BaseGithubModel {
	// Properties
	private String sha;
	private String message;
	private Author author;
	private String url;
	
	// Getters and Setters
	public String getSha() {
		return sha;
	}
	public void setSha(String sha) {
		this.sha = sha;
	}
	public String getPartialSha() {
		if (sha == null || sha.length() <= 7) { 
			return sha;
		} else {
			return sha.substring(0, 7);
		}
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPartialMessage() {
		if (message == null || message.length() <= 50) {
			return message;
		} else {
			return message.substring(0, 50) + "...";
		}
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
