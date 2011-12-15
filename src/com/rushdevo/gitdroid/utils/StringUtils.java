package com.rushdevo.gitdroid.utils;

/**
 * @author jasonrush
 * String helpers
 */
public class StringUtils {
	public static final int DEFAULT_TRUNCATION_LENGTH = 150;
	/**
	 * Truncate a string to at most length chars, breaking at a word boundary
	 * 
	 * @param orig The original string
	 * @params length The max length of the truncated string
	 * @return The truncated string
	 */
	public static String getTruncatedString(String orig, int length) {
		if (orig == null || orig.length() <= length) {
			return orig;
		} else {
			String[] words = orig.split("\\s+");
			StringBuilder builder = new StringBuilder();
			String delimiter = "";
			for (String word : words) {
				if ((builder.length() + word.length() + delimiter.length()) <= length) {
					builder.append(delimiter);
					builder.append(word);
					delimiter = " ";
				} else {
					builder.append("...");
					break;
				}
			}
			return builder.toString();
		}
	}
	
	/**
	 * Truncate a string to at most 150 chars, breaking at a word boundary
	 * 
	 * @param orig The original string
	 * @return The truncated string
	 */
	public static String getTruncatedString(String orig) {
		return getTruncatedString(orig, DEFAULT_TRUNCATION_LENGTH);
	}
}
