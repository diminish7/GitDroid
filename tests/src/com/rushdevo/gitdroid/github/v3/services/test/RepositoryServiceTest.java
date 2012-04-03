package com.rushdevo.gitdroid.github.v3.services.test;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.easymock.EasyMock;

import android.test.AndroidTestCase;

import com.rushdevo.gitdroid.GitDroidApplication;
import com.rushdevo.gitdroid.github.v3.models.Repository;
import com.rushdevo.gitdroid.github.v3.models.User;
import com.rushdevo.gitdroid.github.v3.services.RepositoryService;
import com.rushdevo.gitdroid.test.MockHelper;

/**
 * @author jasonrush
 * Test suite for com.rushdevo.gitdroid.github.v3.services.RepositoryService
 */
public class RepositoryServiceTest extends AndroidTestCase {
	private RepositoryService service;
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
		
		service = new RepositoryService(getContext(), httpClient);
	}
	
	public void testRetrieveMyRepositories() throws IllegalStateException, IOException {
		String expectedJson = getJsonForRepositories();
		
		MockHelper.setupHttpClientMock(httpClient, expectedJson);
		
		List<Repository> repos = service.retrieveMyRepositories();
		
		validateRepositoryList(repos);
	}
	
	public void testRetrieveMemberRepositories() throws IllegalStateException, IOException {
		String expectedJson = getJsonForRepositories();
		
		MockHelper.setupHttpClientMock(httpClient, expectedJson);
		
		List<Repository> repos = service.retrieveMemberRepositories();
		
		validateRepositoryList(repos);
	}
	
	public void testRetrieveWatchedRepositories() throws IllegalStateException, IOException {
		String expectedJson = getJsonForRepositories();
		
		MockHelper.setupHttpClientMock(httpClient, expectedJson);
		
		List<Repository> repos = service.retrieveWatchedRepositories();
		
		validateRepositoryList(repos);
	}
	
	/////// HELPERS ////////
	private String getJsonForRepositories() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("[");
		builder.append("  {");
		builder.append("    \"url\": \"https://api.github.com/repos/octocat/Hello-World\","				);
		builder.append("    \"html_url\": \"https://github.com/octocat/Hello-World\","					);
		builder.append("    \"clone_url\": \"https://github.com/octocat/Hello-World.git\","				);
		builder.append("    \"git_url\": \"git://github.com/octocat/Hello-World.git\","					);
		builder.append("    \"ssh_url\": \"git@github.com:octocat/Hello-World.git\","					);
		builder.append("    \"svn_url\": \"https://svn.github.com/octocat/Hello-World\","				);
		builder.append("    \"id\": 1296269,"															);
		builder.append("    \"owner\": {"																);
		builder.append("      \"login\": \"octocat\","													);
		builder.append("      \"id\": 1,"																);
		builder.append("      \"avatar_url\": \"https://github.com/images/error/octocat_happy.gif\","	);
		builder.append("      \"gravatar_id\": \"somehexcode\","										);
		builder.append("      \"url\": \"https://api.github.com/users/octocat\""						);
		builder.append("    },"																			);
		builder.append("    \"name\": \"Hello-World\","													);
		builder.append("    \"description\": \"This your first repo!\","								);
		builder.append("    \"homepage\": \"https://github.com\","										);
		builder.append("    \"language\": null,"														);
		builder.append("    \"private\": false,"														);
		builder.append("    \"fork\": false,"															);
		builder.append("    \"forks\": 9,"																);
		builder.append("    \"watchers\": 80,"															);
		builder.append("    \"size\": 108,"																);
		builder.append("    \"master_branch\": \"master\","												);
		builder.append("    \"open_issues\": 0,"														);
		builder.append("    \"pushed_at\": \"2011-01-26T19:06:43Z\","									);
		builder.append("    \"created_at\": \"2011-01-26T19:01:12Z\""									);
		builder.append("  }"																			);
		builder.append("]"																				);
		
		return builder.toString();
	}
	
	private void validateRepositoryList(List<Repository> repos) {
		assertEquals(repos.size(), 1);
		
		Repository repo = repos.get(0);
		
		assertEquals(repo.getId(), new Integer(1296269));
		assertEquals(repo.getName(), "Hello-World");
		assertEquals(repo.getDescription(), "This your first repo!");
		assertEquals(repo.getHomepage(), "https://github.com");
		assertEquals(repo.getUrl(), "https://api.github.com/repos/octocat/Hello-World");
		assertEquals(repo.getHtmlUrl(), "https://github.com/octocat/Hello-World");
		assertEquals(repo.getCloneUrl(), "https://github.com/octocat/Hello-World.git");
		assertEquals(repo.getGitUrl(), "git://github.com/octocat/Hello-World.git");
		assertEquals(repo.getSshUrl(), "git@github.com:octocat/Hello-World.git");
		assertEquals(repo.getSvnUrl(), "https://svn.github.com/octocat/Hello-World");
		assertNull(repo.getLanguage());
		assertFalse(repo.isFork());
		assertEquals(repo.getForks(), new Integer(9));
		assertEquals(repo.getWatchers(), new Integer(80));
		assertEquals(repo.getSize(), new Integer(108));
		assertEquals(repo.getMasterBranch(), "master");
		assertEquals(repo.getOpenIssues(), new Integer(0));
		
		assertDate(repo.getPushedAt(), 2011, Calendar.JANUARY, 26);
		assertDate(repo.getCreatedAt(), 2011, Calendar.JANUARY, 26);
	}
	
	private void assertDate(Date date, int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dateYear = cal.get(Calendar.YEAR);
		int dateMonth = cal.get(Calendar.MONTH);
		int dateDay = cal.get(Calendar.DATE);
		
		assertEquals(dateYear, year);
		assertEquals(dateMonth, month);
		assertEquals(dateDay, day);
	}
}
