package com.rushdevo.gitdroid.github.v3.models.event_payloads;

import com.rushdevo.gitdroid.github.v3.models.BaseGithubModel;
import com.rushdevo.gitdroid.github.v3.models.Repository;
import com.rushdevo.gitdroid.github.v3.models.Team;
import com.rushdevo.gitdroid.github.v3.models.User;

/**
 * @author jasonrush
 * Event payload for when someone is added to a team
 */
public class TeamAddEvent extends BaseGithubModel implements EventPayload {
	// Properties
	private Team team;
	private User user;
	private Repository repo;
	
	// Getters and Setters
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
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
	@Override
	public String getActionVerb() {
		// TODO Auto-generated method stub
		return "";
	}
	@Override
	public String getActionSubject() {
		// TODO Auto-generated method stub
		return "";
	}
	@Override
	public String getContent() {
		// TODO Auto-generated method stub
		return "";
	}
}
