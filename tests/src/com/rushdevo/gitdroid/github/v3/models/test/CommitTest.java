package com.rushdevo.gitdroid.github.v3.models.test;

import junit.framework.TestCase;

import com.rushdevo.gitdroid.github.v3.models.Commit;

/**
 * @author jasonrush
 * Test suite for com.rushdevo.gitdroid.github.v3.models.Commit
 */
public class CommitTest extends TestCase {
	private Commit commit;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		commit = new Commit();
	}
	
	///////// getPartialSha() ///////////////////
	public void testPartialShaWithNullCommitId() {
		commit.setSha(null);
		assertNull(commit.getPartialSha());
	}
	
	public void testPartialShaWithSmallCommitId() {
		commit.setSha("1234");
		assertEquals("1234", commit.getPartialSha());
	}
	
	public void testPartialShaWithLargeCommitId() {
		commit.setSha("1234567890");
		assertEquals("1234567", commit.getPartialSha());
	}
}
