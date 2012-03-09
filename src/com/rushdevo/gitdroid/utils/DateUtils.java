package com.rushdevo.gitdroid.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author jasonrush
 * Utility class for Date helpers
 */
public class DateUtils {
	// Consts
	private static final int HOUR_IN_SECONDS = 60*60;
	private static final int DAY_IN_SECONDS = HOUR_IN_SECONDS*24;
	private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("MMMM d, yyyy");
	
	/**
	 * Returns a formatted time for the given date object
	 * If it was less than an hour ago, it will be 'n minute(s) ago'
	 * If it was less than a day ago, it will be 'n hour(s) ago'
	 * Otherwise it will be 'Month dd, yyyy'
	 * 
	 * @param date - The date to format
	 * @return The formatted date expression
	 */
	public static String getTimestamp(Date date) {
		if (date == null) return "";
		long now = System.currentTimeMillis();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		long created = cal.getTimeInMillis();
		int diff = (int)((now-created)/1000);
		StringBuilder builder = new StringBuilder();
		if (diff < HOUR_IN_SECONDS) {
			// If less than an hour ago: 'n minutes ago'
			int diffMinutes = diff / 60;
			builder.append(diffMinutes);
			if (diffMinutes == 1) builder.append(" minute ago");
			else builder.append(" minutes ago");
		} else if (diff < DAY_IN_SECONDS) {
			// If less than a day ago: 'n hours ago'
			long diffHours = Math.round(diff / 60.0 / 60.0);
			builder.append("About ");
			if (diffHours == 1) builder.append("an hour ago");
			else {
				builder.append(diffHours);
				builder.append(" hours ago");
			}
		} else {
			// If more than a day ago: 'Month dd, yyyy'
			builder.append(dateFormatter.format(date));
		}
		return builder.toString();
	}
}
