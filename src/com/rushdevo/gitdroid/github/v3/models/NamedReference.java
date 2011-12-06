package com.rushdevo.gitdroid.github.v3.models;

/**
 * @author jasonrush
 * Class representing a named reference
 */
public class NamedReference extends BaseGithubModel {
	// Properties
	private String label;
	private String ref;
	private String sha;
	private User user;
	private Repository repo;
	
	// Getters and Setters
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getSha() {
		return sha;
	}
	public void setSha(String sha) {
		this.sha = sha;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Repository getRepo() {
		return repo;
	}
	public void setRepo(Repository repo) {
		this.repo = repo;
	}
}
