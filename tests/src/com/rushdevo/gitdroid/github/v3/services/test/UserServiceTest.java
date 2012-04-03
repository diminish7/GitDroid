package com.rushdevo.gitdroid.github.v3.services.test;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.HttpClient;

import android.graphics.drawable.Drawable;
import android.test.AndroidTestCase;

import com.rushdevo.gitdroid.GitDroidApplication;
import com.rushdevo.gitdroid.R;
import com.rushdevo.gitdroid.github.v3.models.User;
import com.rushdevo.gitdroid.github.v3.services.UserService;
import com.rushdevo.gitdroid.test.MockHelper;
import com.rushdevo.gitdroid.utils.UserAvatarHelper;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

/**
 * @author jasonrush
 * Test suite for com.rushdevo.gitdroid.github.v3.services.UserService
 */
public class UserServiceTest extends AndroidTestCase {
	private UserService service;
	private User user;
	private Drawable defaultAvatar;
	
	private HttpClient httpClient;
	private UserAvatarHelper userAvatarHelper;
	
	private static final String LOGIN = "login";
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		user = new User();
		user.setLogin(LOGIN);
		
		((GitDroidApplication)getContext().getApplicationContext()).setCurrentUser(user);
		
		defaultAvatar = getContext().getResources().getDrawable(R.drawable.default_avatar);
		
		httpClient = createMock(HttpClient.class);
		userAvatarHelper = createMock(UserAvatarHelper.class);
		expect(userAvatarHelper.getAvatarForUserAsDrawable((User)anyObject(), (Drawable)anyObject())).andStubReturn(defaultAvatar);
		replay(userAvatarHelper);
		
		service = new UserService(getContext(), httpClient, userAvatarHelper);
	}
	
	public void testRetrieveCurrentUser() throws IllegalStateException, IOException {
		String expectedJson = getJsonForUser();
		
		MockHelper.setupHttpClientMock(httpClient, expectedJson);
		
		User user = service.retrieveCurrentUser();
		
		validateUser(user);
	}
	
	public void testRetrieveFollowers() throws IllegalStateException, IOException {
		String expectedJson = getJsonForUsers();
		
		MockHelper.setupHttpClientMock(httpClient, expectedJson);
		
		List<User> users = service.retrieveFollowers(defaultAvatar);
		
		validateUsers(users);
	}
	
	public void testRetrieveFollowing() throws IllegalStateException, IOException {
		String expectedJson = getJsonForUsers();
		
		MockHelper.setupHttpClientMock(httpClient, expectedJson);
		
		List<User> users = service.retrieveFollowing(defaultAvatar);
		
		validateUsers(users);
	}
	
	public void testRetrieveOrganizations() throws IllegalStateException, IOException {
		String expectedJson = getJsonForUsers();
		
		MockHelper.setupHttpClientMock(httpClient, expectedJson);
		
		List<User> users = service.retrieveOrganizations(defaultAvatar);
		
		validateUsers(users);
	}
	
	////// HELPERS /////////
	private String getJsonForUser() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("{");
		builder.append("  \"login\": \"octocat\",");
		builder.append("  \"id\": 1,");
		builder.append("  \"avatar_url\": \"https://github.com/images/error/octocat_happy.gif\",");
		builder.append("  \"gravatar_id\": \"somehexcode\",");
		builder.append("  \"url\": \"https://api.github.com/users/octocat\",");
		builder.append("  \"name\": \"monalisa octocat\",");
		builder.append("  \"company\": \"GitHub\",");
		builder.append("  \"blog\": \"https://github.com/blog\",");
		builder.append("  \"location\": \"San Francisco\",");
		builder.append("  \"email\": \"octocat@github.com\",");
		builder.append("  \"hireable\": false,");
		builder.append("  \"bio\": \"There once was...\",");
		builder.append("  \"public_repos\": 2,");
		builder.append("  \"public_gists\": 1,");
		builder.append("  \"followers\": 20,");
		builder.append("  \"following\": 0,");
		builder.append("  \"html_url\": \"https://github.com/octocat\",");
		builder.append("  \"created_at\": \"2008-01-14T04:33:35Z\",");
		builder.append("  \"type\": \"User\"");
		builder.append("}");
		
		return builder.toString();
	}
	
	private String getJsonForUsers() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("[");
		builder.append(getJsonForUser());
		builder.append("]");
		
		return builder.toString();
	}
	
	private void validateUser(User user) {
		assertEquals(user.getLogin(), "octocat");
		assertEquals(user.getId(), new Integer(1));
		assertEquals(user.getType(), "User");
		assertEquals(user.getAvatarUrl(), "https://github.com/images/error/octocat_happy.gif");
		assertEquals(user.getGravatarId(), "somehexcode");
		assertEquals(user.getUrl(), "https://api.github.com/users/octocat");
		assertEquals(user.getHtmlUrl(), "https://github.com/octocat");
		assertEquals(user.getName(), "monalisa octocat");
		assertEquals(user.getCompany(), "GitHub");
		assertEquals(user.getBlog(), "https://github.com/blog");
		assertEquals(user.getLocation(), "San Francisco");
		assertEquals(user.getEmail(), "octocat@github.com");
		assertFalse(user.isHireable());
		assertEquals(user.getBio(), "There once was...");
		assertEquals(user.getPublicRepos(), new Integer(2));
		assertEquals(user.getPublicGists(), new Integer(1));
		assertEquals(user.getFollowers(), new Integer(20));
		assertEquals(user.getFollowing(), new Integer(0));
		
	}
	
	private void validateUsers(List<User> users) {
		assertEquals(users.size(), 1);
		validateUser(users.get(0));
	}
}
