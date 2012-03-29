package com.rushdevo.gitdroid.github.v3.services.test;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.easymock.EasyMock;

import android.test.AndroidTestCase;

import com.rushdevo.gitdroid.GitDroidApplication;
import com.rushdevo.gitdroid.github.v3.models.Gist;
import com.rushdevo.gitdroid.github.v3.models.User;
import com.rushdevo.gitdroid.github.v3.services.GistService;
import com.rushdevo.gitdroid.test.MockHelper;

/**
 * @author jasonrush
 * Test suite for com.rushdevo.gitdroid.github.v3.services.EventService
 */
public class GistServiceTest extends AndroidTestCase {
	private GistService service;
	private User user;
	
	private HttpClient httpClient;
	
	private static final String LOGIN = "login";
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		user = new User();
		user.setLogin(LOGIN);
		
		((GitDroidApplication)getContext().getApplicationContext()).setCurrentUser(user);
		
		httpClient = EasyMock.createMock(HttpClient.class);
		
		service = new GistService(getContext(), httpClient);
	}
	
	public void testRetrieveGists() throws IllegalStateException, IOException {
		String expectedJson = getJsonForGists();
		
		MockHelper.setupHttpClientMock(httpClient, expectedJson);
		
		List<Gist> gists = service.retrieveGists();
		
		assertEquals(gists.size(), 1);
		
		Gist gist = gists.get(0);
		
		assertEquals(gist.getId(), "1");
		assertEquals(gist.getDescription(), "description of gist");
		assertEquals(gist.getUrl(), "https://api.github.com/gists/1");
		assertEquals(gist.getComments(), new Integer(0));
		assertEquals(gist.getHtmlUrl(), "https://gist.github.com/1");
		assertEquals(gist.getGitPullUrl(), "git://gist.github.com/1.git");
		assertEquals(gist.getGitPushUrl(), "git@gist.github.com:1.git");
		
		User user = gist.getUser();
		assertEquals(user.getLogin(), "octocat");
		assertEquals(user.getId(), new Integer(1));
		assertEquals(user.getAvatarUrl(), "https://github.com/images/error/octocat_happy.gif");
		assertEquals(user.getGravatarId(), "somehexcode");
	}
	
	private String getJsonForGists() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("["																												);
		builder.append("  {"																											);
		builder.append("    \"url\": \"https://api.github.com/gists/1\","																);
		builder.append("    \"id\": \"1\","																								);
		builder.append("    \"description\": \"description of gist\","																	);
		builder.append("    \"public\": true,"																							);
		builder.append("    \"user\": {"																								);
		builder.append("      \"login\": \"octocat\","																					);
		builder.append("      \"id\": 1,"																								);
		builder.append("      \"avatar_url\": \"https://github.com/images/error/octocat_happy.gif\","									);
		builder.append("      \"gravatar_id\": \"somehexcode\","																		);
		builder.append("      \"url\": \"https://api.github.com/users/octocat\""														);
		builder.append("    },"																											);
		builder.append("    \"files\": {"																								);
		builder.append("      \"ring.erl\": {"																							);
		builder.append("        \"size\": 932,"																							);
		builder.append("        \"filename\": \"ring.erl\","																			);
		builder.append("        \"raw_url\": \"https://gist.github.com/raw/365370/8c4d2d43d178df44f4c03a7f2ac0ff512853564e/ring.erl\","	);
		builder.append("        \"content\": \"contents of gist\""																		);
		builder.append("      }"																										);
		builder.append("    },"																											);
		builder.append("    \"comments\": 0,"																							);
		builder.append("    \"html_url\": \"https://gist.github.com/1\","																);
		builder.append("    \"git_pull_url\": \"git://gist.github.com/1.git\","															);
		builder.append("    \"git_push_url\": \"git@gist.github.com:1.git\","															);
		builder.append("    \"created_at\": \"2010-04-14T02:15:15Z\""																	);
		builder.append("  }"																											);
		builder.append("]"																												);
		
		return builder.toString();
	}
}
