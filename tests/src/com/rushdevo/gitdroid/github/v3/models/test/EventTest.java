package com.rushdevo.gitdroid.github.v3.models.test;

import java.util.Calendar;

import junit.framework.TestCase;

import com.rushdevo.gitdroid.github.v3.models.Event;
import com.rushdevo.gitdroid.github.v3.models.Repository;
import com.rushdevo.gitdroid.github.v3.models.User;
import com.rushdevo.gitdroid.github.v3.models.event_payloads.WatchEvent;

/**
 * @author jasonrush
 * Test suite for com.rushdevo.gitdroid.github.v3.models.Commit
 */
public class EventTest extends TestCase {
	private Event event;
	private Repository repo;
	private User user;
	private WatchEvent payload;
	
	private static final String REPO = "My New Repo";
	private static final String LOGIN = "my_login";
	private static final String AVATAR_URL = "http://localhost:3000/avatar.png";
	private static final String PAYLOAD_CONTENT = "";
	private static final String PAYLOAD_DESCRIPTION = LOGIN + " started watching " + REPO;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		repo = new Repository();
		repo.setName(REPO);
		
		user = new User();
		user.setLogin(LOGIN);
		user.setAvatarUrl(AVATAR_URL);
		
		payload = new WatchEvent();
		
		event = new Event();
		event.setActor(user);
		event.setRepo(repo);
		event.setPayload(payload);
	}
	
	///////// getRepoName() ////////////
	public void testGetRepoNameWithRepo() {
		assertEquals(REPO, event.getRepoName());
	}
	
	public void testGetRepoNameWithNullRepo() {
		event.setRepo(null);
		assertEquals("some repo", event.getRepoName());
	}
	
	///////// getActorName() ////////////
	public void testGetActorNameWithActor() {
		assertEquals(LOGIN, event.getActorName());
	}
	
	public void testGetActorNameWithNullActor() {
		event.setActor(null);
		assertEquals("someone", event.getActorName());
	}
	
	///////// getActorAvatarUrl() ////////////
	public void testGetActorAvatarUrlWithActor() {
		assertEquals(AVATAR_URL, event.getActorAvatarUrl());
	}
	
	public void testGetActorAvatarUrlWithNullActor() {
		event.setActor(null);
		assertEquals("", event.getActorAvatarUrl());
	}
	
	///////// getContent() ////////////
	public void testGetContentWithPayload() {
		assertEquals(PAYLOAD_CONTENT, event.getContent());
	}
	
	public void testGetContentWithNullPayload() {
		event.setPayload(null);
		assertEquals("", event.getContent());
	}
	
	///////// getFullDescription() ////////////
	public void testGetFullDescriptionWithPayload() {
		assertEquals(PAYLOAD_DESCRIPTION, event.getFullDescription());
	}
	
	public void testGetFullDescriptionWithNullPayload() {
		event.setPayload(null);
		assertEquals("(unknown event)", event.getFullDescription());
	}
	
	///////// getTimestamp() /////////////////////
	public void testGetTimestamp() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR, cal.get(Calendar.HOUR)-1);
		event.setCreatedAt(cal.getTime());
		assertEquals("About an hour ago", event.getTimestamp());
	}
}
