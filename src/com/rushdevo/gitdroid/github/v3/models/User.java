package com.rushdevo.gitdroid.github.v3.models;

import java.util.Date;

import android.graphics.drawable.Drawable;

import com.rushdevo.gitdroid.utils.UserAvatarHelper;

/**
 * @author jasonrush
 * Class representing a Github User
 */
public class User extends BaseGithubModel {
	// Consts
	public static final String GRAVATAR_URL = "http://www.gravatar.com/avatar/";
	
	// Properties
	private String login;
	private Integer id;
	private String avatar_url;
	private String gravatar_id;
	private Drawable avatar;
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
	public void setAvatarUrl(String avatarUrl) {
		this.avatar_url = avatarUrl;
	}
	public String getGravatarId() {
		return gravatar_id;
	}
	public void setGravatarId(String gravatarId) {
		this.gravatar_id = gravatarId;
	}
	public String getAvatarOrGravatarUrl() {
		if (avatar_url != null) return avatar_url;
		else if (gravatar_id != null) {
			return GRAVATAR_URL + gravatar_id;
		} else {
			return null;
		}
	}
	public Drawable getAvatar() {
		return this.avatar;
	}
	public void setAvatar(Drawable avatar) {
		this.avatar = avatar;
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
	public Boolean isHireable() {
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
	public void setPublicRepos(Integer publicRepos) {
		this.public_repos = publicRepos;
	}
	public Integer getPublicGists() {
		return public_gists;
	}
	public void setPublicGists(Integer publicGists) {
		this.public_gists = publicGists;
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
	public void setHtmlUrl(String htmlUrl) {
		this.html_url = htmlUrl;
	}
	public Date getCreatedAt() {
		return created_at;
	}
	public void setCreatedAt(Date createdAt) {
		this.created_at = createdAt;
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
	public void setTotalPrivateRepos(Integer totalPrivateRepos) {
		this.total_private_repos = totalPrivateRepos;
	}
	public Integer getOwnedPrivateRepos() {
		return owned_private_repos;
	}
	public void setOwnedPrivateRepos(Integer ownedPrivateRepos) {
		this.owned_private_repos = ownedPrivateRepos;
	}
	public Integer getPrivateGists() {
		return private_gists;
	}
	public void setPrivateGists(Integer privateGists) {
		this.private_gists = privateGists;
	}
	public Integer getDiskUsage() {
		return disk_usage;
	}
	public void setDiskUsage(Integer diskUsage) {
		this.disk_usage = diskUsage;
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
	
	// HELPERS //
	public void retrieveAvatarAsDrawable(UserAvatarHelper userAvatarHelper, Drawable defaultAvatar) {
		Drawable avatar = userAvatarHelper.getAvatarForUserAsDrawable(this, defaultAvatar);
		setAvatar(avatar);
	}
}
