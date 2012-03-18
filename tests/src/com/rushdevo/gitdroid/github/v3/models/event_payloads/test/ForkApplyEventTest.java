package com.rushdevo.gitdroid.github.v3.models.event_payloads.test;

import com.rushdevo.gitdroid.github.v3.models.Event;
import com.rushdevo.gitdroid.github.v3.models.Repository;
import com.rushdevo.gitdroid.github.v3.models.User;
import com.rushdevo.gitdroid.github.v3.models.event_payloads.ForkApplyEvent;

import junit.framework.TestCase;

/**
 * @author jasonrush
 * Test suite for com.rushdevo.gitdroid.github.v3.models.event_payloads.ForkApplyEvent
 */
public class ForkApplyEventTest extends TestCase {
	private Event event;
	private User user;
	private Repository repo;
	private ForkApplyEvent payload;
	
	private static final String LOGIN = "user";
	private static final String REPO = "repo";
	private static final String HEAD = "head";
	
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
		
		payload = new ForkApplyEvent();
		payload.setHead(HEAD);
		event.setPayload(payload);
	}
	
	/////////// getContent() ////////////////////
	public void testGetContent() {
		// Always empty string
		assertEquals("", payload.getContent());
	}
	
	
	/////////// getFullDescription(Event event) /////
	public void testGetFullDescriptionWithNullEvent() {
		assertEquals("(unknown fork-applied event)", payload.getFullDescription(null));
	}
	
	public void testGetFullDescriptionWithNoHead() {
		payload.setHead(null);
		StringBuilder builder = new StringBuilder();
		builder.append(LOGIN);
		builder.append(" applied fork to ");
		builder.append(REPO);
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
	
	public void testGetFullDescription() {
		StringBuilder builder = new StringBuilder();
		builder.append(LOGIN);
		builder.append(" applied fork ");
		builder.append(HEAD);
		builder.append(" to ");
		builder.append(REPO);
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
}
