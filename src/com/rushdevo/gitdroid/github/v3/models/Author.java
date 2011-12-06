package com.rushdevo.gitdroid.github.v3.models;

/**
 * @author jasonrush
 * Class representing a Github commit's author
 */
public class Author extends BaseGithubModel {
	// Properties
	private String name;
	private String email;
	
	// Getters and Setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
