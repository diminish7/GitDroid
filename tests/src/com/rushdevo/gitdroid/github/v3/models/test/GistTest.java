package com.rushdevo.gitdroid.github.v3.models.test;

import java.util.Calendar;

import junit.framework.TestCase;

import com.rushdevo.gitdroid.github.v3.models.Gist;
import com.rushdevo.gitdroid.github.v3.models.User;

/**
 * @author jasonrush
 * Test suite for com.rushdevo.gitdroid.github.v3.models.Gist
 */
public class GistTest extends TestCase {
	private Gist gist;
	private User user;
	
	private static final String LOGIN = "my_login";
	private static final String FORMATTED_DATE_AND_BY_STRING = "About an hour ago by " + LOGIN;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		user = new User();
		user.setLogin(LOGIN);
		gist = new Gist();
		gist.setUser(user);
	}
	
	//////////// getFormattedDateAndByString ////////////
	public void testGetFormattedDateAndByString() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR, cal.get(Calendar.HOUR)-1);
		gist.setCreatedAt(cal.getTime());
		assertEquals(FORMATTED_DATE_AND_BY_STRING, gist.getFormattedDateAndByString());
	}
}
