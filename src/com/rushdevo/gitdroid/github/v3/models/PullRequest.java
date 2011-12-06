package com.rushdevo.gitdroid.github.v3.models;

import java.util.Date;

/**
 * @author jasonrush
 * Class representing a Github pull request
 */
public class PullRequest extends BaseGithubModel {
	// Properties
	private String url;
	private String html_url;
	private String diff_url;
	private String patch_url;
	private String issue_url;
	private Integer number;
	private String state;
	private String title;
	private String body;
	private Date created_at;
	private Date updated_at;
	private Date closed_at;
	private Date merged_at;
	private Links links;
	private Boolean merged;
	private Boolean mergeable;
	private User merged_by;
	private Integer comments;
	private Integer commits;
	private Integer additions;
	private Integer deletions;
	private Integer changed_files;
	private NamedReference head;
	private NamedReference base;
	
	// Getters and Setters
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
	public String getDiffUrl() {
		return diff_url;
	}
	public void setDiffUrl(String diffUrl) {
		this.diff_url = diffUrl;
	}
	public String getPatchUrl() {
		return patch_url;
	}
	public void setPatchUrl(String patchUrl) {
		this.patch_url = patchUrl;
	}
	public String getIssueUrl() {
		return issue_url;
	}
	public void setIssueUrl(String issueUrl) {
		this.issue_url = issueUrl;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Date getCreatedAt() {
		return created_at;
	}
	public void setCreatedAt(Date createdAt) {
		this.created_at = createdAt;
	}
	public Date getUpdatedAt() {
		return updated_at;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updated_at = updatedAt;
	}
	public Date getClosedAt() {
		return closed_at;
	}
	public void setClosedAt(Date closedAt) {
		this.closed_at = closedAt;
	}
	public Date getMergedAt() {
		return merged_at;
	}
	public void setMergedAt(Date mergedAt) {
		this.merged_at = mergedAt;
	}
	public Links getLinks() {
		return this.links;
	}
	public void setLinks(Links links) {
		this.links = links;
	}
	public Boolean getMerged() {
		return merged;
	}
	public void setMerged(Boolean merged) {
		this.merged = merged;
	}
	public Boolean getMergeable() {
		return mergeable;
	}
	public void setMergeable(Boolean mergeable) {
		this.mergeable = mergeable;
	}
	public User getMergedBy() {
		return merged_by;
	}
	public void setMergedBy(User mergedBy) {
		this.merged_by = mergedBy;
	}
	public Integer getComments() {
		return comments;
	}
	public void setComments(Integer comments) {
		this.comments = comments;
	}
	public Integer getCommits() {
		return commits;
	}
	public void setCommits(Integer commits) {
		this.commits = commits;
	}
	public Integer getAdditions() {
		return additions;
	}
	public void setAdditions(Integer additions) {
		this.additions = additions;
	}
	public Integer getDeletions() {
		return deletions;
	}
	public void setDeletions(Integer deletions) {
		this.deletions = deletions;
	}
	public Integer getChangedFiles() {
		return changed_files;
	}
	public void setChangedFiles(Integer changedFiles) {
		this.changed_files = changedFiles;
	}
	public NamedReference getHead() {
		return head;
	}
	public void setHead(NamedReference head) {
		this.head = head;
	}
	public NamedReference getBase() {
		return base;
	}
	public void setBase(NamedReference base) {
		this.base = base;
	}
}
