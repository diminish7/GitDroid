package com.rushdevo.gitdroid.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.rushdevo.gitdroid.R;

/**
 * @author jasonrush
 *	Utility class for displaying error messages
 */
public class ErrorDisplay {
	/**
	 * Posts a toast message with the error
	 * 
	 * @param e - The exception being thrown
	 */
	public static void errorToast(Context ctx, Exception e) {
		errorToast(ctx, e.getMessage());
	}
	
	/**
	 * Posts a toast message with the error
	 * 
	 * @param message - The exception message to display
	 */
	public static void errorToast(Context ctx, String message) {
		Log.d(ctx.getClass().getName(), message);
		Toast.makeText(ctx, String.format(ctx.getString(R.string.exception_message), message), Toast.LENGTH_LONG).show();
	}
	
	/**
	 * Write a debug message to the log
	 * 
	 * @param obj - The object the debug message is coming from (its class will be the tag)
	 * @param message - The message to write to the log
	 */
	public static void debug(Object obj, String message) {
		Log.d(obj.getClass().getSimpleName(), message);
	}
	
	/**
	 * Write a debug message to the log
	 * 
	 * @param obj - The object the debug message is coming from (its class will be the tag)
	 * @param e - The exception who's message will be written to the log
	 */
	public static void debug(Object obj, Exception e) {
		debug(obj, e.getMessage());
	}
}
