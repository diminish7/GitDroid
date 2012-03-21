package com.rushdevo.gitdroid.github.v3.models.event_payloads.test;

import junit.framework.TestCase;

import com.rushdevo.gitdroid.github.v3.models.Event;
import com.rushdevo.gitdroid.github.v3.models.Repository;
import com.rushdevo.gitdroid.github.v3.models.Team;
import com.rushdevo.gitdroid.github.v3.models.User;
import com.rushdevo.gitdroid.github.v3.models.event_payloads.TeamAddEvent;

/**
 * @author jasonrush
 * Test suite for com.rushdevo.gitdroid.github.v3.models.event_payloads.TeamAddEvent
 */
public class TeamAddEventTest extends TestCase {
	private Event event;
	private User user;
	private Repository repo;
	private TeamAddEvent payload;
	private User addedUser;
	private Team team;
	
	private static final String LOGIN = "login";
	private static final String REPO = "repo";
	private static final String NAME = "name";
	private static final String TEAM = "team";
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		event = new Event();
		user = new User();
		user.setLogin(LOGIN);
		event.setActor(user);
		repo = new Repository();
		repo.setName(REPO);
		event.setRepo(repo);
		
		payload = new TeamAddEvent();
		
		addedUser = new User();
		addedUser.setName(NAME);
		payload.setUser(addedUser);
		
		team = new Team();
		team.setName(TEAM);
		payload.setTeam(team);
	}
	
	////////// getContent() /////////////////////
	public void testGetContent() {
		// Always blank
		assertEquals("", payload.getContent());
	}
	
	////////// getFullDescription(Event event) //////////
	public void testGetFullDescriptionWithNoEvent() {
		assertEquals("(unknown team add event)", payload.getFullDescription(null));
	}
	
	public void testGetFullDescriptionWithNoUser() {
		payload.setUser(null);
		StringBuilder builder = new StringBuilder();
		builder.append(LOGIN);
		builder.append(" added someone to team ");
		builder.append(TEAM);
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
	
	public void testGetFullDescriptionWithAUserWithNoName() {
		addedUser.setName(null);
		StringBuilder builder = new StringBuilder();
		builder.append(LOGIN);
		builder.append(" added someone to team ");
		builder.append(TEAM);
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
	
	public void testGetFullDescriptionWithNoTeam() {
		payload.setTeam(null);
		StringBuilder builder = new StringBuilder();
		builder.append(LOGIN);
		builder.append(" added ");
		builder.append(NAME);
		builder.append(" to a team");
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
	
	public void testGetFullDescriptionWithATeamWithNoName() {
		team.setName(null);
		StringBuilder builder = new StringBuilder();
		builder.append(LOGIN);
		builder.append(" added ");
		builder.append(NAME);
		builder.append(" to a team");
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
	
	public void testGetFullDescription() {
		StringBuilder builder = new StringBuilder();
		builder.append(LOGIN);
		builder.append(" added ");
		builder.append(NAME);
		builder.append(" to team ");
		builder.append(TEAM);
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
}
