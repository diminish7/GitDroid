package com.rushdevo.gitdroid.github.v3.models.event_payloads.test;

import junit.framework.TestCase;

import com.rushdevo.gitdroid.github.v3.models.Comment;
import com.rushdevo.gitdroid.github.v3.models.Event;
import com.rushdevo.gitdroid.github.v3.models.Issue;
import com.rushdevo.gitdroid.github.v3.models.Repository;
import com.rushdevo.gitdroid.github.v3.models.User;
import com.rushdevo.gitdroid.github.v3.models.event_payloads.IssueCommentEvent;

/**
 * @author jasonrush
 * Test suite for com.rushdevo.gitdroid.github.v3.models.event_payloads.IssueCommentEvent
 */
public class IssueCommentEventTest extends TestCase {
	private Event event;
	private User user;
	private Repository repo;
	private IssueCommentEvent payload;
	private Comment comment;
	private Issue issue;
	
	private static final String BODY = "body";
	private static final String LARGE_BODY = "All work and no play makes Jack a dull boy. All work and no play makes Jack a dull boy. All work and no play makes Jack a dull boy. All work and no play makes Jack a dull boy.";
	private static final String PARTIAL_LARGE_BODY = "All work and no play makes Jack a dull boy. All work and no play makes Jack a dull boy. All work and no play makes Jack a dull boy. All work and no...";
	private static final Integer ISSUE_NUMBER = 1234;
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
		
		payload = new IssueCommentEvent();
		
		comment = new Comment();
		comment.setBody(BODY);
		payload.setComment(comment);
		issue = new Issue();
		issue.setNumber(ISSUE_NUMBER);
		payload.setIssue(issue);
		
		event.setPayload(payload);
	}
	
	///////// getContent() ///////////////////
	public void testGetContentWithNoComment() {
		payload.setComment(null);
		assertEquals(payload.getContent(), "");
	}
	
	public void testGetContentWithSmallComment() {
		assertEquals(BODY, payload.getContent());
	}
	
	public void testGetContentWithLargeComment() {
		payload.getComment().setBody(LARGE_BODY);
		assertEquals(PARTIAL_LARGE_BODY, payload.getContent());
	}
	
	///////// getFullDescription(event) //////
	public void testGetFullDescriptionWithNoEvent() {
		assertEquals("(unknown issue comment event)", payload.getFullDescription(null));
	}
	
	public void testGetFullDescriptionWithNoIssue() {
		payload.setIssue(null);
		StringBuilder builder = new StringBuilder();
		builder.append(LOGIN);
		builder.append(" commented on an issue on ");
		builder.append(REPO);
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
	
	public void testGetFullDescriptionWithAnIssueWithNoNumber() {
		issue.setNumber(null);
		StringBuilder builder = new StringBuilder();
		builder.append(LOGIN);
		builder.append(" commented on an issue on ");
		builder.append(REPO);
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
	
	public void testGetFullDescription() {
		StringBuilder builder = new StringBuilder();
		builder.append(LOGIN);
		builder.append(" commented on issue ");
		builder.append(ISSUE_NUMBER);
		builder.append(" on ");
		builder.append(REPO);
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
}
