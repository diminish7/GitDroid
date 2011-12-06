package com.rushdevo.gitdroid.github.v3.models;

/**
 * @author jasonrush
 * Class representing a Github organization's team
 */
public class Team extends BaseGithubModel {
	// Properties
	private String url;
	private String name;
	private Integer id;
	private String permission;
	private Integer member_count;
	private Integer repos_count;
	
	// Getters and Setters
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public Integer getMemberCount() {
		return member_count;
	}
	public void setMemberCount(Integer memberCount) {
		this.member_count = memberCount;
	}
	public Integer getReposCount() {
		return repos_count;
	}
	public void setReposCount(Integer reposCount) {
		this.repos_count = reposCount;
	}
}
