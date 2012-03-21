package com.rushdevo.gitdroid.utils.test;

import com.rushdevo.gitdroid.utils.StringUtils;

import junit.framework.TestCase;

/**
 * @author jasonrush
 * Test suite for com.rushdevo.gitdroid.utils.StringUtils
 */
public class StringUtilsTest extends TestCase {

	////// getTruncatedString(String orig, int length) //////
	public void testGetTruncatedStringWithLengthAndWithNoOrig() {
		assertNull(StringUtils.getTruncatedString(null, 50));
	}
	
	public void testGetTruncatedStringWithLengthAndWithAShortOrig() {
		String str = "short string";
		assertEquals(str, StringUtils.getTruncatedString(str, 50));
	}
	
	public void testGetTruncatedStringWithLengthAndWithALongOrig() {
		String str = "long string";
		String truncated = "long...";
		assertEquals(truncated, StringUtils.getTruncatedString(str, 7));
	}
	
	/////// getTruncatedString(String orig) //////////////////////
	public void testGetTruncatedStringWithNoOrig() {
		assertNull(StringUtils.getTruncatedString(null));
	}
	
	public void testGetTruncatedStringWithAShortOrig() {
		String str = "short string";
		assertEquals(str, StringUtils.getTruncatedString(str));
	}
	
	public void testGetTruncatedStringWithALongOrig() {
		String str = "All work and no play makes Jack a dull boy. All work and no play makes Jack a dull boy. All work and no play makes Jack a dull boy. All work and no play makes Jack a dull boy.";
		String truncated = "All work and no play makes Jack a dull boy. All work and no play makes Jack a dull boy. All work and no play makes Jack a dull boy. All work and no...";
		assertEquals(truncated, StringUtils.getTruncatedString(str));
	}
}
