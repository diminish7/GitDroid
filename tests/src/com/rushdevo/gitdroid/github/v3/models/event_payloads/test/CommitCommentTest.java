package com.rushdevo.gitdroid.github.v3.models.event_payloads.test;

import com.rushdevo.gitdroid.github.v3.models.Comment;
import com.rushdevo.gitdroid.github.v3.models.Event;
import com.rushdevo.gitdroid.github.v3.models.Repository;
import com.rushdevo.gitdroid.github.v3.models.User;
import com.rushdevo.gitdroid.github.v3.models.event_payloads.CommitComment;

import junit.framework.TestCase;

/**
 * @author jasonrush
 * Test suite for com.rushdevo.gitdroid.github.v3.models.event_payloads.CommitComment
 */
public class CommitCommentTest extends TestCase {
	private Event event;
	private User user;
	private Repository repo;
	private CommitComment payload;
	private Comment comment;
	
	private static final String BODY = "body";
	private static final String LARGE_BODY = "All work and no play makes Jack a dull boy. All work and no play makes Jack a dull boy. All work and no play makes Jack a dull boy. All work and no play makes Jack a dull boy.";
	private static final String PARTIAL_LARGE_BODY = "All work and no play makes Jack a dull boy. All work and no play makes Jack a dull boy. All work and no play makes Jack a dull boy. All work and no...";
	private static final String COMMIT_ID = "abcd";
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
		
		payload = new CommitComment();
		
		comment = new Comment();
		comment.setBody(BODY);
		comment.setCommitId(COMMIT_ID);
		payload.setComment(comment);
		
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
		assertEquals("(unknown commit comment event)", payload.getFullDescription(null));
	}
	
	public void testGetFullDescriptionWithNoComment() {
		payload.setComment(null);
		StringBuilder builder = new StringBuilder();
		builder.append(LOGIN);
		builder.append(" commented on a commit on ");
		builder.append(REPO);
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
	
	public void testGetFullDescriptionWithACommentWithNoCommit() {
		comment.setCommitId(null);
		StringBuilder builder = new StringBuilder();
		builder.append(LOGIN);
		builder.append(" commented on a commit on ");
		builder.append(REPO);
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
	
	public void testGetFullDescription() {
		StringBuilder builder = new StringBuilder();
		builder.append(LOGIN);
		builder.append(" commented on commit ");
		builder.append(COMMIT_ID);
		builder.append(" on ");
		builder.append(REPO);
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
}
