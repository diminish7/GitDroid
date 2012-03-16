package com.rushdevo.gitdroid.github.v3.models.event_payloads.test;

import com.rushdevo.gitdroid.github.v3.models.Event;
import com.rushdevo.gitdroid.github.v3.models.Repository;
import com.rushdevo.gitdroid.github.v3.models.User;
import com.rushdevo.gitdroid.github.v3.models.event_payloads.DeleteEvent;

import junit.framework.TestCase;

/**
 * @author jasonrush
 * Test suite for com.rushdevo.gitdroid.github.v3.models.event_payloads.DeleteEvent
 */
public class DeleteEventTest extends TestCase {
	private Event event;
	private User user;
	private Repository repo;
	private DeleteEvent payload;
	
	private static final String REF_TYPE = "abcd";
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
		
		payload = new DeleteEvent();
		payload.setRefType(REF_TYPE);
		
		event.setPayload(payload);
	}
	
	///////// getContent() ///////////////////
	public void testGetContent() {
		assertEquals(payload.getContent(), "");
	}
	
	///////// getFullDescription(event) //////
	public void testGetFullDescriptionWithNoEvent() {
		assertEquals("(unknown delete event)", payload.getFullDescription(null));
	}
	
	public void testGetFullDescriptionWithNoRefType() {
		payload.setRefType(null);
		StringBuilder builder = new StringBuilder();
		builder.append(LOGIN);
		builder.append(" deleted something on ");
		builder.append(REPO);
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
	
	public void testGetFullDescription() {
		StringBuilder builder = new StringBuilder();
		builder.append(LOGIN);
		builder.append(" deleted ");
		builder.append(REF_TYPE);
		builder.append(" on ");
		builder.append(REPO);
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
}
