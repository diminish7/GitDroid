package com.rushdevo.gitdroid.github.v3.models;

/**
 * @author jasonrush
 * Class representing a repo download
 */
public class Download extends BaseGithubModel {
	// Properties
	private String url;
	private String html_url;
	private Integer id;
	private String name;
	private String description;
	private Integer size;
	private Integer download_count;
	private String content_type;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Integer getDownloadCount() {
		return download_count;
	}
	public void setDownloadCount(Integer downloadCount) {
		this.download_count = downloadCount;
	}
	public String getContentType() {
		return content_type;
	}
	public void setContentType(String contentType) {
		this.content_type = contentType;
	}
}
