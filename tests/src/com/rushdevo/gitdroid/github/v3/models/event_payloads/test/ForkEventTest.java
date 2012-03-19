package com.rushdevo.gitdroid.github.v3.models.event_payloads.test;

import junit.framework.TestCase;

import com.rushdevo.gitdroid.github.v3.models.Event;
import com.rushdevo.gitdroid.github.v3.models.Repository;
import com.rushdevo.gitdroid.github.v3.models.User;
import com.rushdevo.gitdroid.github.v3.models.event_payloads.ForkEvent;

/**
 * @author jasonrush
 * Test suite for com.rushdevo.gitdroid.github.v3.models.event_payloads.ForkEvent
 */
public class ForkEventTest extends TestCase {
	private Event event;
	private User user;
	private Repository repo;
	private ForkEvent payload;
	
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
		
		payload = new ForkEvent();
	}
	
	///////// getContent() ///////////////////
	public void testGetContent() {
		// Always empty string
		assertEquals("", payload.getContent());
	}
	
	///////// getFullDescription(Event event) //////////
	public void testGetFullDescriptionWithNoEvent() {
		assertEquals("(unknown fork event)", payload.getFullDescription(null));
	}
	
	public void testGetFullDescription() {
		StringBuilder builder = new StringBuilder();
		builder.append(LOGIN);
		builder.append(" forked ");
		builder.append(REPO);
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
}
