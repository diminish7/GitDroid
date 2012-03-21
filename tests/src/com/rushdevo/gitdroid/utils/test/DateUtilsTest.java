package com.rushdevo.gitdroid.utils.test;

import java.util.Calendar;

import junit.framework.TestCase;

import com.rushdevo.gitdroid.utils.DateUtils;

/**
 * @author jasonrush
 * Test suite for com.rushdevo.gitdroid.utils.DateUtils
 */
public class DateUtilsTest extends TestCase {
	private Calendar cal;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		cal = Calendar.getInstance();
	}
	
	//////////// getTimestamp(Date date) ////////////
	public void testGetTimestampWithNoDate() {
		assertEquals("", DateUtils.getTimestamp(null));
	}
	
	public void testGetTimestampLessThanAMinuteAgo() {
		cal.add(Calendar.SECOND, -1);
		assertEquals("Just now", DateUtils.getTimestamp(cal.getTime()));
	}
	
	public void testGetTimestampAMinuteAgo() {
		cal.add(Calendar.MINUTE, -1);
		assertEquals("A minute ago", DateUtils.getTimestamp(cal.getTime()));
	}
	
	public void testGetTimestampLessThanAnHourAgo() {
		cal.add(Calendar.MINUTE, -5);
		assertEquals("5 minutes ago", DateUtils.getTimestamp(cal.getTime()));
	}
	
	public void testGetTimestampAnHourAgo() {
		cal.add(Calendar.HOUR, -1);
		assertEquals("About an hour ago", DateUtils.getTimestamp(cal.getTime()));
	}
	
	public void testGetTimestampLessThanADayAgo() {
		cal.add(Calendar.HOUR, -10);
		assertEquals("About 10 hours ago", DateUtils.getTimestamp(cal.getTime()));
	}
	
	public void testGetTimestampMoreThanADayAgo() {
		cal.set(2012, Calendar.MARCH, 20);
		assertEquals("March 20, 2012", DateUtils.getTimestamp(cal.getTime()));
	}
}
