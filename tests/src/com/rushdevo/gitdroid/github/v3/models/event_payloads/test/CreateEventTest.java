package com.rushdevo.gitdroid.github.v3.models.event_payloads.test;

import junit.framework.TestCase;

import com.rushdevo.gitdroid.github.v3.models.Event;
import com.rushdevo.gitdroid.github.v3.models.Repository;
import com.rushdevo.gitdroid.github.v3.models.User;
import com.rushdevo.gitdroid.github.v3.models.event_payloads.CreateEvent;

/**
 * @author jasonrush
 * Test suite for com.rushdevo.gitdroid.github.v3.models.event_payloads.CreateEvent
 */
public class CreateEventTest extends TestCase {
	private Event event;
	private User user;
	private Repository repo;
	private CreateEvent payload;
	
	private static final String DESCRIPTION = "description";
	private static final String LARGE_DESCRIPTION = "All work and no play makes Jack a dull boy. All work and no play makes Jack a dull boy. All work and no play makes Jack a dull boy. All work and no play makes Jack a dull boy.";
	private static final String PARTIAL_LARGE_DESCRIPTION = "All work and no play makes Jack a dull boy. All work and no play makes Jack a dull boy. All work and no play makes Jack a dull boy. All work and no...";
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
		
		payload = new CreateEvent();
		payload.setDescription(DESCRIPTION);
		payload.setRefType(REF_TYPE);
		
		event.setPayload(payload);
	}
	
	///////// getContent() ///////////////////
	public void testGetContentWithNoDescription() {
		payload.setDescription(null);
		assertNull(payload.getContent());
	}
	
	public void testGetContentWithSmallDescription() {
		assertEquals(DESCRIPTION, payload.getContent());
	}
	
	public void testGetContentWithLargeDescription() {
		payload.setDescription(LARGE_DESCRIPTION);
		assertEquals(PARTIAL_LARGE_DESCRIPTION, payload.getContent());
	}
	
	///////// getFullDescription(event) //////
	public void testGetFullDescriptionWithNoEvent() {
		assertEquals("(unknown create event)", payload.getFullDescription(null));
	}
	
	public void testGetFullDescriptionWithNoRefType() {
		payload.setRefType(null);
		StringBuilder builder = new StringBuilder();
		builder.append(LOGIN);
		builder.append(" created something on ");
		builder.append(REPO);
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
	
	public void testGetFullDescription() {
		StringBuilder builder = new StringBuilder();
		builder.append(LOGIN);
		builder.append(" created ");
		builder.append(REF_TYPE);
		builder.append(" on ");
		builder.append(REPO);
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
}
