package com.rushdevo.gitdroid.github.v3.models;

import java.util.Date;

/**
 * @author jasonrush
 * Class representing a Github User
 */
public class User extends BaseGithubModel {
	// Properties
	private String login;
	private Integer id;
	private String avatar_url;
	private String gravatar_id;
	private String url;
	private String name;
	private String company;
	private String blog;
	private String location;
	private String email;
	private Boolean hireable;
	private String bio;
	private Integer public_repos;
	private Integer public_gists;
	private Integer followers;
	private Integer following;
	private String html_url;
	private Date created_at;
	private String type;
	private Integer total_private_repos;
	private Integer owned_private_repos;
	private Integer private_gists;
	private Integer disk_usage;
	private Integer collaborators;
	private GithubPlan plan;
	
	// Constructors
	public User(String json) {
		super(json);
	}
	
	// Getters and Setters
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAvatarUrl() {
		return avatar_url;
	}
	public void setAvatarUrl(String avatar_url) {
		this.avatar_url = avatar_url;
	}
	public String getGravatarId() {
		return gravatar_id;
	}
	public void setGravatarId(String gravatar_id) {
		this.gravatar_id = gravatar_id;
	}
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
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getBlog() {
		return blog;
	}
	public void setBlog(String blog) {
		this.blog = blog;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getHireable() {
		return hireable;
	}
	public void setHireable(Boolean hireable) {
		this.hireable = hireable;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public Integer getPublicRepos() {
		return public_repos;
	}
	public void setPublicRepos(Integer public_repos) {
		this.public_repos = public_repos;
	}
	public Integer getPublicGists() {
		return public_gists;
	}
	public void setPublicGists(Integer public_gists) {
		this.public_gists = public_gists;
	}
	public Integer getFollowers() {
		return followers;
	}
	public void setFollowers(Integer followers) {
		this.followers = followers;
	}
	public Integer getFollowing() {
		return following;
	}
	public void setFollowing(Integer following) {
		this.following = following;
	}
	public String getHtmlUrl() {
		return html_url;
	}
	public void setHtmlUrl(String html_url) {
		this.html_url = html_url;
	}
	public Date getCreatedAt() {
		return created_at;
	}
	public void setCreatedAt(Date created_at) {
		this.created_at = created_at;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getTotalPrivateRepos() {
		return total_private_repos;
	}
	public void setTotalPrivateRepos(Integer total_private_repos) {
		this.total_private_repos = total_private_repos;
	}
	public Integer getOwnedPrivateRepos() {
		return owned_private_repos;
	}
	public void setOwnedPrivateRepos(Integer owned_private_repos) {
		this.owned_private_repos = owned_private_repos;
	}
	public Integer getPrivateGists() {
		return private_gists;
	}
	public void setPrivateGists(Integer private_gists) {
		this.private_gists = private_gists;
	}
	public Integer getDiskUsage() {
		return disk_usage;
	}
	public void setDiskUsage(Integer disk_usage) {
		this.disk_usage = disk_usage;
	}
	public Integer getCollaborators() {
		return collaborators;
	}
	public void setCollaborators(Integer collaborators) {
		this.collaborators = collaborators;
	}
	public GithubPlan getPlan() {
		return plan;
	}
	public void setPlan(GithubPlan plan) {
		this.plan = plan;
	}
}
