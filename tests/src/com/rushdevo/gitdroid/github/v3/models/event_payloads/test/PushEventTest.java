package com.rushdevo.gitdroid.github.v3.models.event_payloads.test;

import junit.framework.TestCase;

import com.rushdevo.gitdroid.github.v3.models.Commit;
import com.rushdevo.gitdroid.github.v3.models.Event;
import com.rushdevo.gitdroid.github.v3.models.Repository;
import com.rushdevo.gitdroid.github.v3.models.User;
import com.rushdevo.gitdroid.github.v3.models.event_payloads.PushEvent;

/**
 * @author jasonrush
 * Test suite for com.rushdevo.gitdroid.github.v3.models.event_payloads.PushEvent
 */
public class PushEventTest extends TestCase {
	private Event event;
	private User user;
	private Repository repo;
	private PushEvent payload;
	
	private Commit commit1;
	private Commit commit2;
	
	private static final String LOGIN = "login";
	private static final String REPO = "repo";
	private static final String SHA1 = "0f8d15734f279c0f3a45e78068b42a128a181ddb";
	private static final String PARTIAL_SHA1 = "0f8d157";
	private static final String MESSAGE1 = "message 1";
	private static final String LONG_MESSAGE = "All work and no play makes Jack a dull boy. All work and no play makes Jack a dull boy.";
	private static final String PARTIAL_LONG_MESSAGE = "All work and no play makes Jack a dull boy. All wo...";
	private static final String SHA2 = "592b2400287e0b3221ff4958e5866cd4c9cab8b9";
	private static final String MESSAGE2 = "message 2";
	private static final String REF = "refs/heads/master";
	private static final String SHORT_REF = "master";
	
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
		
		payload = new PushEvent();
		payload.setRef(REF);
		
		commit1 = new Commit();
		commit1.setSha(SHA1);
		commit1.setMessage(MESSAGE1);
		commit2 = new Commit();
		commit2.setSha(SHA2);
		commit2.setMessage(MESSAGE2);
		Commit[] commits = { commit1, commit2 };
		payload.setCommits(commits);
		
		event.setPayload(payload);
	}
	
	///////// getContent() ///////////////////
	public void testGetContentWithNoCommits() {
		payload.setCommits(null);
		assertEquals("", payload.getContent());
	}
	
	public void testGetContentWithEmptyCommits() {
		Commit[] blank = {};
		payload.setCommits(blank);
		assertEquals("", payload.getContent());
	}
	
	public void testGetContentWithOneCommit() {
		Commit[] one = { commit1 };
		payload.setCommits(one);
		StringBuilder builder = new StringBuilder();
		builder.append(PARTIAL_SHA1);
		builder.append(" ");
		builder.append(MESSAGE1);
		assertEquals(builder.toString(), payload.getContent());
	}
	
	public void testGetContentWithTwoCommits() {
		StringBuilder builder = new StringBuilder();
		builder.append(PARTIAL_SHA1);
		builder.append(" ");
		builder.append(MESSAGE1);
		builder.append(" (and 1 other commit)");
		assertEquals(builder.toString(), payload.getContent());
	}
	
	public void testGetContentWithALongCommitMessage() {
		commit1.setMessage(LONG_MESSAGE);
		StringBuilder builder = new StringBuilder();
		builder.append(PARTIAL_SHA1);
		builder.append(" ");
		builder.append(PARTIAL_LONG_MESSAGE);
		builder.append(" (and 1 other commit)");
		assertEquals(builder.toString(), payload.getContent());
	}
	
	/////////// getFullDescription(Event event) ///////////
	public void testGetFullDescriptionWithNoEvent() {
		assertEquals("(unknown push event)", payload.getFullDescription(null));
	}
	
	public void testGetFullDescriptionWithNoRef() {
		payload.setRef(null);
		StringBuilder builder = new StringBuilder();
		builder.append(LOGIN);
		builder.append(" pushed to ");
		builder.append(REPO);
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
	
	public void testGetFullDescription() {
		StringBuilder builder = new StringBuilder();
		builder.append(LOGIN);
		builder.append(" pushed to ");
		builder.append(SHORT_REF);
		builder.append(" at ");
		builder.append(REPO);
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
}
