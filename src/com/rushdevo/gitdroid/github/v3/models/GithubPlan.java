package com.rushdevo.gitdroid.github.v3.models;

/**
 * @author jasonrush
 * Class representing a Github plan
 */
public class GithubPlan extends BaseGithubModel {
	// Properties
	private String name;
	private Integer space;
	private Integer collaborators;
	private Integer private_repos;
	
	// Constructors
	public GithubPlan(String json) {
		super(json);
	}
	
	// Getters and Setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSpace() {
		return space;
	}
	public void setSpace(Integer space) {
		this.space = space;
	}
	public Integer getCollaborators() {
		return collaborators;
	}
	public void setCollaborators(Integer collaborators) {
		this.collaborators = collaborators;
	}
	public Integer getPrivateRepos() {
		return private_repos;
	}
	public void setPrivateRepos(Integer private_repos) {
		this.private_repos = private_repos;
	}
}
