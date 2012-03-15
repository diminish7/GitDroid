package com.rushdevo.gitdroid.github.v3.models.test;

import com.rushdevo.gitdroid.github.v3.models.User;

import junit.framework.TestCase;

/**
 * @author jasonrush
 * Test suite for com.rushdevo.gitdroid.github.v3.models.User
 */
public class UserTest extends TestCase {
	private User user;
	
	private static final String AVATAR_URL = "http://localhost:3000/avatar.png";
	private static final String GRAVATAR_ID = "123";
	private static final String GRAVATAR_URL = User.GRAVATAR_URL+GRAVATAR_ID;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		user = new User();
		user.setAvatarUrl(AVATAR_URL);
		user.setGravatarId(GRAVATAR_ID);
	}
	
	/////// getAvatarOrGravatarUrl() /////////
	public void testGetAvatarOrGravatarUrlWithNulls() {
		user.setAvatarUrl(null);
		user.setGravatarId(null);
		assertNull(user.getAvatarOrGravatarUrl());
	}
	
	public void testGetAvatarOrGravatarUrlWithAvatarUrl() {
		user.setGravatarId(null);
		assertEquals(AVATAR_URL, user.getAvatarOrGravatarUrl());
	}
	
	public void testGetAvatarOrGravatarUrlWithGravatarId() {
		user.setAvatarUrl(null);
		assertEquals(GRAVATAR_URL, user.getAvatarOrGravatarUrl());
	}
	
	public void testGetAvatarOrGravatarUrlWithAvatarUrlAndGravatarId() {
		assertEquals(AVATAR_URL, user.getAvatarOrGravatarUrl());
	}
}
