package com.rushdevo.gitdroid.github.v3.models.event_payloads.test;

import com.rushdevo.gitdroid.github.v3.models.Event;
import com.rushdevo.gitdroid.github.v3.models.Repository;
import com.rushdevo.gitdroid.github.v3.models.User;
import com.rushdevo.gitdroid.github.v3.models.event_payloads.PublicEvent;

import junit.framework.TestCase;

/**
 * @author jasonrush
 * Test suite for com.rushdevo.gitdroid.github.v3.models.event_payloads.PublicEvent
 */
public class PublicEventTets extends TestCase {
	private Event event;
	private User user;
	private Repository repo;
	private PublicEvent payload;
	
	private static final String LOGIN = "login";
	private static final String REPO = "repo";
	
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
		
		payload = new PublicEvent();
		event.setPayload(payload);
	}
	
	///////// getContent() ////////////////
	public void testGetContent() {
		// Always blank
		assertEquals("", payload.getContent());
	}
	
	///////// getFullDescription(Event event) ///////
	public void testGetFullDescriptionWithNoEvent() {
		assertEquals("(unknown public event)", payload.getFullDescription(null));
	}
	
	public void testGetFullDescription() {
		StringBuilder builder = new StringBuilder();
		builder.append(LOGIN);
		builder.append(" made ");
		builder.append(REPO);
		builder.append(" public");
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
}
