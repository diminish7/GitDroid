package com.rushdevo.gitdroid.github.v3.models.event_payloads.test;

import junit.framework.TestCase;

import com.rushdevo.gitdroid.github.v3.models.Event;
import com.rushdevo.gitdroid.github.v3.models.PullRequest;
import com.rushdevo.gitdroid.github.v3.models.Repository;
import com.rushdevo.gitdroid.github.v3.models.User;
import com.rushdevo.gitdroid.github.v3.models.event_payloads.PullRequestEvent;

/**
 * @author jasonrush
 *
 */
public class PullRequestEventTest extends TestCase {
	private Event event;
	private User user;
	private Repository repo;
	private PullRequestEvent payload;
	
	private PullRequest request;
	
	private static final String LOGIN = "login";
	private static final String REPO = "repo";
	private static final String TITLE = "title";
	private static final String LARGE_TITLE = "All work and no play makes Jack a dull boy. All work and no play makes Jack a dull boy. All work and no play makes Jack a dull boy. All work and no play makes Jack a dull boy.";
	private static final String PARTIAL_LARGE_TITLE = "All work and no play makes Jack a dull boy. All work and no play makes Jack a dull boy. All work and no play makes Jack a dull boy. All work and no...";
	private static final String ACTION = "opened";
	private static final Integer NUMBER = 123;
	
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
		
		payload = new PullRequestEvent();
		payload.setAction(ACTION);
		payload.setNumber(NUMBER);
		
		request = new PullRequest();
		request.setTitle(TITLE);
		payload.setPullRequest(request);
		
		event.setPayload(payload);
	}
	
	////////// getContent() ///////////////////
	public void testGetContentWithNoPullRequest() {
		payload.setPullRequest(null);
		assertEquals("", payload.getContent());
	}
	
	public void testGetContentWithAPullRequestWithNoTitle() {
		request.setTitle(null);
		assertEquals("", payload.getContent());
	}
	
	public void testGetContentWithAPullRequestWithALongTitle() {
		request.setTitle(LARGE_TITLE);
		assertEquals(PARTIAL_LARGE_TITLE, payload.getContent());
	}
	
	public void testGetContent() {
		assertEquals(TITLE, payload.getContent());
	}
	
	/////////// getFullDescription(Event event) /////////
	public void testGetFullDescriptionWithNoEvent() {
		assertEquals("(unknown pull request event)", payload.getFullDescription(null));
	}
	
	public void testGetFullDescriptionWithNoAction() {
		payload.setAction(null);
		StringBuilder builder = new StringBuilder();
		builder.append(LOGIN);
		builder.append(" did something to pull request ");
		builder.append(NUMBER);
		builder.append(" on ");
		builder.append(REPO);
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
	
	public void testGetFullDescriptionWithNoNumber() {
		payload.setNumber(null);
		StringBuilder builder = new StringBuilder();
		builder.append(LOGIN);
		builder.append(" ");
		builder.append(ACTION);
		builder.append(" a pull request on ");
		builder.append(REPO);
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
	
	public void testGetFullDescription() {
		StringBuilder builder = new StringBuilder();
		builder.append(LOGIN);
		builder.append(" ");
		builder.append(ACTION);
		builder.append(" pull request ");
		builder.append(NUMBER);
		builder.append(" on ");
		builder.append(REPO);
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
}
