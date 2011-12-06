package com.rushdevo.gitdroid.github.v3.models;

/**
 * @author jasonrush
 * Class representing a label on an issue
 */
public class Label extends BaseGithubModel {
	// Properties
	private String url;
	private String name;
	private String color;
	
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
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
}
