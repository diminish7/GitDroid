package com.rushdevo.gitdroid.github.v3.models.event_payloads.test;

import junit.framework.TestCase;

import com.rushdevo.gitdroid.github.v3.models.Event;
import com.rushdevo.gitdroid.github.v3.models.Repository;
import com.rushdevo.gitdroid.github.v3.models.User;
import com.rushdevo.gitdroid.github.v3.models.event_payloads.MemberEvent;

/**
 * @author jasonrush
 * Test suite for com.rushdevo.gitdroid.github.v3.models.event_payloads.MemberEvent
 */
public class MemberEventTest extends TestCase {
	private Event event;
	private User user;
	private Repository repo;
	private MemberEvent payload;
	private User member;
	
	private static final String LOGIN = "login";
	private static final String REPO = "repo";
	private static final String NAME = "name";
	private static final String ACTION = "added";
	
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
		
		payload = new MemberEvent();
		member = new User();
		member.setName(NAME);
		payload.setMember(member);
		payload.setAction(ACTION);
		event.setPayload(payload);
	}
	
	/////////// getContent() ////////////
	public void testGetContent() {
		// Always returns blank
		assertEquals("", payload.getContent());
	}
	
	/////////// getFullDescription(Event event) //////
	public void testGetFullDescriptionWithNoEvent() {
		assertEquals("(unknown member event)", payload.getFullDescription(null));
	}
	
	public void testGetFullDescriptionWithNoAction() {
		payload.setAction(null);
		StringBuilder builder = new StringBuilder();
		builder.append(LOGIN);
		builder.append(" ");
		builder.append(ACTION);
		builder.append(" ");
		builder.append(member.getName());
		builder.append(" to ");
		builder.append(repo.getName());
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
	
	public void testGetFullDescriptionWithNoMember() {
		payload.setMember(null);
		StringBuilder builder = new StringBuilder();
		builder.append(LOGIN);
		builder.append(" ");
		builder.append(ACTION);
		builder.append(" someone");
		builder.append(" to ");
		builder.append(repo.getName());
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
	
	public void testGetFullDescriptionWithAMemberWithNoName() {
		member.setName(null);
		StringBuilder builder = new StringBuilder();
		builder.append(LOGIN);
		builder.append(" ");
		builder.append(ACTION);
		builder.append(" someone");
		builder.append(" to ");
		builder.append(repo.getName());
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
	
	public void testGetFullDescription() {
		StringBuilder builder = new StringBuilder();
		builder.append(LOGIN);
		builder.append(" ");
		builder.append(ACTION);
		builder.append(" ");
		builder.append(member.getName());
		builder.append(" to ");
		builder.append(repo.getName());
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
}
