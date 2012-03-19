package com.rushdevo.gitdroid.github.v3.models.event_payloads.test;

import junit.framework.TestCase;

import com.rushdevo.gitdroid.github.v3.models.Event;
import com.rushdevo.gitdroid.github.v3.models.Issue;
import com.rushdevo.gitdroid.github.v3.models.Repository;
import com.rushdevo.gitdroid.github.v3.models.User;
import com.rushdevo.gitdroid.github.v3.models.event_payloads.IssuesEvent;

/**
 * @author jasonrush
 * Test suite for com.rushdevo.gitdroid.github.v3.models.event_payloads.IssueEvent
 */
public class IssuesEventTest extends TestCase {
	private Event event;
	private User user;
	private Repository repo;
	private IssuesEvent payload;
	private Issue issue;
	
	private static final String LOGIN = "login";
	private static final String REPO = "repo";
	private static final String TITLE = "title";
	private static final Integer NUMBER = 1234;
	private static final String ACTION = "opened";
	
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
		
		payload = new IssuesEvent();
		payload.setAction(ACTION);
		issue = new Issue();
		issue.setTitle(TITLE);
		issue.setNumber(NUMBER);
		payload.setIssue(issue);
	}
	
	//////// getContent() ////////////////////
	public void testGetContentWithNoIssue() {
		payload.setIssue(null);
		assertEquals("", payload.getContent());
	}
	
	public void testGetContentWithAnIssueWithNoTitle() {
		issue.setTitle(null);
		assertEquals("", payload.getContent());
	}
	
	public void testGetContent() {
		assertEquals(TITLE, payload.getContent());
	}
	
	//////// getFullDescription(Event event) /////
	public void testGetFullDescriptionWithNoEvent() {
		assertEquals("(unknown issue event)", payload.getFullDescription(null));
	}
	
	public void testGetFullDescriptionWithNoAction() {
		payload.setAction(null);
		StringBuilder builder = new StringBuilder();
		builder.append(LOGIN);
		builder.append(" did something to ");
		builder.append("issue ");
		builder.append(NUMBER);
		builder.append(" on ");
		builder.append(REPO);
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
	
	public void testGetFullDescriptionWithNoIssue() {
		payload.setIssue(null);
		StringBuilder builder = new StringBuilder();
		builder.append(LOGIN);
		builder.append(" ");
		builder.append(ACTION);
		builder.append(" an issue");
		builder.append(" on ");
		builder.append(REPO);
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
	
	public void testGetFullDescriptionWithAnIssueWithNoNumber() {
		issue.setNumber(null);
		StringBuilder builder = new StringBuilder();
		builder.append(LOGIN);
		builder.append(" ");
		builder.append(ACTION);
		builder.append(" an issue");
		builder.append(" on ");
		builder.append(REPO);
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
	
	public void testGetFullDescription() {
		StringBuilder builder = new StringBuilder();
		builder.append(LOGIN);
		builder.append(" ");
		builder.append(ACTION);
		builder.append(" issue ");
		builder.append(NUMBER);
		builder.append(" on ");
		builder.append(REPO);
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
}
