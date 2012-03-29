package com.rushdevo.gitdroid.github.v3.services.test;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.easymock.EasyMock;

import android.test.AndroidTestCase;

import com.rushdevo.gitdroid.GitDroidApplication;
import com.rushdevo.gitdroid.github.v3.models.Event;
import com.rushdevo.gitdroid.github.v3.models.Organization;
import com.rushdevo.gitdroid.github.v3.models.Repository;
import com.rushdevo.gitdroid.github.v3.models.User;
import com.rushdevo.gitdroid.github.v3.services.EventService;
import com.rushdevo.gitdroid.test.MockHelper;

/**
 * @author jasonrush
 * Test suite for com.rushdevo.gitdroid.github.v3.services.EventService
 */
public class EventServiceTest extends AndroidTestCase {
	private EventService service;
	private User user;
	
	private HttpClient httpClient;
	
	private static final String LOGIN = "login";
	private static final Integer PAGE = 1;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		user = new User();
		user.setLogin(LOGIN);
		
		((GitDroidApplication)getContext().getApplicationContext()).setCurrentUser(user);
		
		httpClient = EasyMock.createMock(HttpClient.class);
		
		service = new EventService(getContext(), httpClient);
	}
	
	public void testGetReceivedEventsUrl() {
		StringBuilder builder = new StringBuilder();
		builder.append("https://api.github.com/users/");
		builder.append(LOGIN);
		builder.append("/received_events?page=");
		builder.append(PAGE);
		assertEquals(builder.toString(), service.getReceivedEventsUrl(PAGE));
	}
	
	public void testGetPublicEventsUrl() {
		StringBuilder builder = new StringBuilder();
		builder.append("https://api.github.com/users/");
		builder.append(LOGIN);
		builder.append("/events/public?page=");
		builder.append(PAGE);
		assertEquals(builder.toString(), service.getPublicEventsUrl(PAGE));
	}
	
	public void testRetrieveReceivedEvents() throws IllegalStateException, IOException {
		String expectedJson = getJsonForEvents();
		
		MockHelper.setupHttpClientMock(httpClient, expectedJson);
		
		List<Event> events = service.retrieveReceivedEvents(1);
		
		assertEquals(events.size(), 1);
		
		Event event = events.get(0);
		
		Repository repo = event.getRepo();
		assertEquals(repo.getName(), "octocat/Hello-World");
		
		User actor = event.getActor();
		assertEquals(actor.getLogin(), "octocat");
		
		Organization org = event.getOrg();
		assertEquals(org.getLogin(), "octocat");
	}
	
	public void testRetrievePublicEvents() throws IllegalStateException, IOException {
		String expectedJson = getJsonForEvents();
		
		MockHelper.setupHttpClientMock(httpClient, expectedJson);
		
		List<Event> events = service.retrievePublicEvents(1);
		
		assertEquals(events.size(), 1);
		
		Event event = events.get(0);
		
		Repository repo = event.getRepo();
		assertEquals(repo.getName(), "octocat/Hello-World");
		
		User actor = event.getActor();
		assertEquals(actor.getLogin(), "octocat");
		
		Organization org = event.getOrg();
		assertEquals(org.getLogin(), "octocat");
	}
	
	private String getJsonForEvents() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("["																				);
		builder.append("  {"																			);
		builder.append("    \"type\": \"Event\","														);
		builder.append("    \"public\": true,"															);
		builder.append("    \"payload\": {"																);
		builder.append("    },"																			);
		builder.append("    \"repo\": {"																);
		builder.append("      \"id\": 3,"																);
		builder.append("      \"name\": \"octocat/Hello-World\","										);
		builder.append("      \"url\": \"https://api.github.com/repos/octocat/Hello-World\""			);
		builder.append("    },"																			);
		builder.append("    \"actor\": {"																);
		builder.append("      \"login\": \"octocat\","													);
		builder.append("      \"id\": 1,"																);
		builder.append("      \"avatar_url\": \"https://github.com/images/error/octocat_happy.gif\","	);
		builder.append("      \"gravatar_id\": \"somehexcode\","										);
		builder.append("      \"url\": \"https://api.github.com/users/octocat\""						);
		builder.append("    },"																			);
		builder.append("    \"org\": {"																	);
		builder.append("      \"login\": \"octocat\","													);
		builder.append("      \"id\": 1,"																);
		builder.append("      \"avatar_url\": \"https://github.com/images/error/octocat_happy.gif\","	);
		builder.append("      \"gravatar_id\": \"somehexcode\","										);
		builder.append("      \"url\": \"https://api.github.com/users/octocat\""						);
		builder.append("    },"																			);
		builder.append("    \"created_at\": \"2011-09-06T17:26:27Z\""									);
		builder.append("  }"																			);
		builder.append("]"																				);
		
		return builder.toString();
	}
}
