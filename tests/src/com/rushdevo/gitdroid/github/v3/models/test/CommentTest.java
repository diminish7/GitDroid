package com.rushdevo.gitdroid.github.v3.models.test;

import junit.framework.TestCase;

import com.rushdevo.gitdroid.github.v3.models.Comment;

/**
 * @author jasonrush
 * Test suite for com.rushdevo.gitdroid.github.v3.models.Comment
 */
public class CommentTest extends TestCase {
	private Comment comment;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		comment = new Comment();
	}
	
	///////// getPartialCommitId() ///////////////////
	public void testPartialCommitIdWithNullCommitId() {
		comment.setCommitId(null);
		assertNull(comment.getPartialCommitId());
	}
	
	public void testPartialCommitIdWithSmallCommitId() {
		comment.setCommitId("1234");
		assertEquals("1234", comment.getPartialCommitId());
	}
	
	public void testPartialCommitIdWithLargeCommitId() {
		comment.setCommitId("1234567890");
		assertEquals("1234567", comment.getPartialCommitId());
	}
	
	///////// getPartialBody() ////////////////////////////
	public void testPartialBodyWithNullBody() {
		comment.setBody(null);
		assertNull(comment.getPartialBody());
	}
	
	public void testPartialBodyWithSmallBody() {
		comment.setBody("a small body");
		assertEquals("a small body", comment.getBody());
	}
	
	public void testPartialBodyWithLargeBody() {
		comment.setBody("All work and no play makes jack a dull boy. All work and no play makes jack a dull boy. All work and no play makes jack a dull boy. All work and no play makes jack a dull boy. ");
		String truncated = "All work and no play makes jack a dull boy. All work and no play makes jack a dull boy. All work and no play makes jack a dull boy. All work and no...";
		assertEquals(truncated, comment.getPartialBody());
	}
}
