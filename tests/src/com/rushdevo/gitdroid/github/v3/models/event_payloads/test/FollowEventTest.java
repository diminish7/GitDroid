package com.rushdevo.gitdroid.github.v3.models.event_payloads.test;

import junit.framework.TestCase;

import com.rushdevo.gitdroid.github.v3.models.Event;
import com.rushdevo.gitdroid.github.v3.models.User;
import com.rushdevo.gitdroid.github.v3.models.event_payloads.FollowEvent;

/**
 * @author jasonrush
 * Test suite for com.rushdevo.gitdroid.github.v3.models.event_payloads.FollowEvent
 */
public class FollowEventTest extends TestCase {
	private Event event;
	private User user;
	private User target;
	private FollowEvent payload;
	
	private static final String USER_LOGIN = "user";
	private static final String TARGET_NAME = "target";
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		event = new Event();
		
		user = new User();
		user.setLogin(USER_LOGIN);
		event.setActor(user);
		
		payload = new FollowEvent();
		target = new User();
		target.setName(TARGET_NAME);
		payload.setTarget(target);
	}
	
	///////// getContent() ///////////////////
	public void testGetContent() {
		// Always empty string
		assertEquals("", payload.getContent());
	}
	
	//////// getFullDescription(Event event) ///////////
	public void testGetFullDescriptionWithNullEvent() {
		assertEquals("(unknown follow event)", payload.getFullDescription(null));
	}
	
	public void testGetFullDescriptionWithNoTarget() {
		payload.setTarget(null);
		StringBuilder builder = new StringBuilder();
		builder.append(USER_LOGIN);
		builder.append(" started following someone");
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
	
	public void testGetFullDescriptionWithATargetWithNoName() {
		target.setName(null);
		StringBuilder builder = new StringBuilder();
		builder.append(USER_LOGIN);
		builder.append(" started following someone");
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
	
	public void testGetFullDescription() {
		StringBuilder builder = new StringBuilder();
		builder.append(USER_LOGIN);
		builder.append(" started following ");
		builder.append(TARGET_NAME);
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
}
