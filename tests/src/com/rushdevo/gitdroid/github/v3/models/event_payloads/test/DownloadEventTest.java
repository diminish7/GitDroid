package com.rushdevo.gitdroid.github.v3.models.event_payloads.test;

import com.rushdevo.gitdroid.github.v3.models.Download;
import com.rushdevo.gitdroid.github.v3.models.Event;
import com.rushdevo.gitdroid.github.v3.models.Repository;
import com.rushdevo.gitdroid.github.v3.models.User;
import com.rushdevo.gitdroid.github.v3.models.event_payloads.DownloadEvent;

import junit.framework.TestCase;

/**
 * @author jasonrush
 * Test suite for com.rushdevo.gitdroid.github.v3.models.event_payloads.DownloadEvent
 */
public class DownloadEventTest extends TestCase {
	private Event event;
	private User user;
	private Repository repo;
	private DownloadEvent payload;
	private Download download;
	
	private static final String DOWNLOAD_NAME = "download";
	private static final String DESCRIPTION = "description";
	private static final String LARGE_DESCRIPTION = "All work and no play makes Jack a dull boy. All work and no play makes Jack a dull boy. All work and no play makes Jack a dull boy. All work and no play makes Jack a dull boy.";
	private static final String PARTIAL_LARGE_DESCRIPTION = "All work and no play makes Jack a dull boy. All work and no play makes Jack a dull boy. All work and no play makes Jack a dull boy. All work and no...";
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
		
		payload = new DownloadEvent();
		download = new Download();
		download.setName(DOWNLOAD_NAME);
		download.setDescription(DESCRIPTION);
		payload.setDownload(download);
		
		event.setPayload(payload);
	}
	
	///////// getContent() ///////////////////
	public void testGetContentWithNoDescription() {
		download.setDescription(null);
		assertNull(payload.getContent());
	}
	
	public void testGetContentWithSmallDescription() {
		assertEquals(DESCRIPTION, payload.getContent());
	}
	
	public void testGetContentWithLargeDescription() {
		download.setDescription(LARGE_DESCRIPTION);
		assertEquals(PARTIAL_LARGE_DESCRIPTION, payload.getContent());
	}
	
	///////// getFullDescription(event) //////
	public void testGetFullDescriptionWithNoEvent() {
		assertEquals("(unknown download event)", payload.getFullDescription(null));
	}
	
	public void testGetFullDescriptionWithNoDownload() {
		payload.setDownload(null);
		StringBuilder builder = new StringBuilder();
		builder.append(LOGIN);
		builder.append(" downloaded something from ");
		builder.append(REPO);
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
	
	public void testGetFullDescriptionWithNoDownloadName() {
		download.setName(null);
		StringBuilder builder = new StringBuilder();
		builder.append(LOGIN);
		builder.append(" downloaded something from ");
		builder.append(REPO);
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
	
	public void testGetFullDescription() {
		StringBuilder builder = new StringBuilder();
		builder.append(LOGIN);
		builder.append(" downloaded ");
		builder.append(DOWNLOAD_NAME);
		builder.append(" from ");
		builder.append(REPO);
		assertEquals(builder.toString(), payload.getFullDescription(event));
	}
}
