package com.rushdevo.gitdroid.github.v3.models;

import java.util.Date;

/**
 * @author jasonrush
 * Class representing a Github repository
 */
public class Repository extends BaseGithubModel implements Comparable<Repository>{
	// Properties
	private Integer id;
	private String name;
	private String url;
	private String html_url;
	private String clone_url;
	private String git_url;
	private String ssh_url;
	private String svn_url;
	private User owner;
	private String description;
	private String homepage;
	private String language;
	private Boolean isPrivate; // TODO: this is 'private' in JSON, need to do custom Gson deserializer
	private Boolean fork;
	private Integer forks;
	private Integer watchers;
	private Integer size;
	private String master_branch;
	private Integer open_issues;
	private Date pushed_at;
	private Date created_at;
	
	// Getters and Setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getHtmlUrl() {
		return html_url;
	}
	public void setHtmlUrl(String htmlUrl) {
		this.html_url = htmlUrl;
	}
	public String getCloneUrl() {
		return clone_url;
	}
	public void setCloneUrl(String cloneUrl) {
		this.clone_url = cloneUrl;
	}
	public String getGitUrl() {
		return git_url;
	}
	public void setGitUrl(String gitUrl) {
		this.git_url = gitUrl;
	}
	public String getSshUrl() {
		return ssh_url;
	}
	public void setSshUrl(String sshUrl) {
		this.ssh_url = sshUrl;
	}
	public String getSvnUrl() {
		return svn_url;
	}
	public void setSvnUrl(String svnUrl) {
		this.svn_url = svnUrl;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public Boolean isPrivate() {
		return isPrivate;
	}
	public void setPrivate(Boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	public Boolean isFork() {
		return fork;
	}
	public void setFork(Boolean fork) {
		this.fork = fork;
	}
	public Integer getForks() {
		return forks;
	}
	public void setForks(Integer forks) {
		this.forks = forks;
	}
	public Integer getWatchers() {
		return watchers;
	}
	public void setWatchers(Integer watchers) {
		this.watchers = watchers;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public String getMasterBranch() {
		return master_branch;
	}
	public void setMasterBranch(String masterBranch) {
		this.master_branch = masterBranch;
	}
	public Integer getOpenIssues() {
		return open_issues;
	}
	public void setOpenIssues(Integer openIssues) {
		this.open_issues = openIssues;
	}
	public Date getPushedAt() {
		return pushed_at;
	}
	public void setPushedAt(Date pushedAt) {
		this.pushed_at = pushedAt;
	}
	public Date getCreatedAt() {
		return created_at;
	}
	public void setCreatedAt(Date createdAt) {
		this.created_at = createdAt;
	}
	
	@Override
	public int compareTo(Repository another) {
		// Sort by created_at date
		return another.getPushedAt().compareTo(getPushedAt());
	}
}
