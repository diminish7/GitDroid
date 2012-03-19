package com.rushdevo.gitdroid.github.v3.models.event_payloads.test;

import junit.framework.TestCase;

import com.rushdevo.gitdroid.github.v3.models.Event;
import com.rushdevo.gitdroid.github.v3.models.Gist;
import com.rushdevo.gitdroid.github.v3.models.Repository;
import com.rushdevo.gitdroid.github.v3.models.User;
import com.rushdevo.gitdroid.github.v3.models.event_payloads.GistEvent;

/**
 * @author jasonrush
 * Test suite for com.rushdevo.gitdroid.github.v3.models.event_payloads.GistEvent
 */
public class GistEventTest extends TestCase {
	private Event event;
	private User user;
	private Repository repo;
	private Gist gist;
	private GistEvent payload;
	
	private static final String LOGIN = "login";
	private static final String REPO = "repo";
	private static final String DESCRIPTION = "a gist";
	private static final String LARGE_DESCRIPTION = "All work and no play makes Jack a dull boy. All work and no play makes Jack a dull boy. All work and no play makes Jack a dull boy. All work and no play makes Jack a dull boy.";
	private static final String PARTIAL_LARGE_DESCRIPTION = "All work and no play makes Jack a dull boy. All work and no play makes Jack a dull boy. All work and no play makes Jack a dull boy. All work and no...";
	private static final String ACTION = "create";
	private static final String GIST_ID = "123456";
	
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
		
		payload = new GistEvent();
		payload.setAction(ACTION);
		gist = new Gist();
		gist.setDescription(DESCRIPTION);
		gist.setId(GIST_ID);
		payload.setGist(gist);
	}
	
	//////// getContent() //////////////////
	public void testGetContentWithNoGist() {
		payload.setGist(null);
		assertEquals("", payload.getContent());
	}
	
	public void testGetContentWithNoGistDescription() {
		gist.setDescription(null);
		assertEquals("", payload.getContent());
	}
	
	public void testGetContentWithShortDescription() {
		assertEquals(DESCRIPTION, payload.getContent());
	}
	
	public void testGetContentWithLongDescription() {
		gist.setDescription(LARGE_DESCRIPTION);
		assertEquals(PARTIAL_LARGE_DESCRIPTION, payload.getContent());
	}
	
	///////// getFullDescription(Event event) ///////
	public void testGetFullDescriptionWithNullEvent() {
		assertEquals("(unknown gist event)", payload.getFullDescription(null));
	}
	
	public void testGetFullDescriptionWithNoAction() {
		payload.setAction(null);
		StringBuilder builder = new StringBuilder();
		builder.append(LOGIN);
		builder.append(" did something to gist: ");
		builder.append(GIST_ID);
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
	
	public void testGetFullDescriptionWithNoGist() {
		payload.setGist(null);
		StringBuilder builder = new StringBuilder();
		builder.append(LOGIN);
		builder.append(" ");
		builder.append(ACTION);
		builder.append("d a gist");
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
	
	public void testGetFullDescriptionWithAGistWithNoId() {
		gist.setId(null);
		StringBuilder builder = new StringBuilder();
		builder.append(LOGIN);
		builder.append(" ");
		builder.append(ACTION);
		builder.append("d a gist");
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
	
	public void testGetFullDescription() {
		StringBuilder builder = new StringBuilder();
		builder.append(LOGIN);
		builder.append(" ");
		builder.append(ACTION);
		builder.append("d gist: ");
		builder.append(GIST_ID);
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
}
