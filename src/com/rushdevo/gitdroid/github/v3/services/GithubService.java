package com.rushdevo.gitdroid.github.v3.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.net.Uri;

import com.google.gson.Gson;
import com.rushdevo.gitdroid.GitDroidApplication;
import com.rushdevo.gitdroid.gson.GsonUtils;
import com.rushdevo.gitdroid.utils.ErrorDisplay;

/**
 * @author jasonrush
 * Base Github Service
 */
public abstract class GithubService {
	// Common URLs
	public static final String BASE_URL = "https://api.github.com";
	protected static final String ACCESS_TOKEN_KEY = "access_token";
	
	protected Context ctx;
	protected GitDroidApplication app;
	private HttpClient client;
	private static final Integer SUCCESS = 200;
	
	public GithubService(Context ctx) {
		this(ctx, new DefaultHttpClient());
	}
	
	public GithubService(Context ctx, HttpClient client) {
		this.ctx = ctx;
		this.app = (GitDroidApplication)ctx.getApplicationContext();
		this.client = client;
	}
	
	/**
	 * Get an instance of a Gson parser
	 * @return The gson object
	 */
	protected Gson getGson() {
		return GsonUtils.getGsonInstance();
	}
	
	/**
	 * Get the GitDroid application context
	 * @return the application context
	 */
	public GitDroidApplication getGitDroidApplication() {
		return (GitDroidApplication)ctx.getApplicationContext();
	}
	
	/**
	 * Returns the uri for the given url after appending the oauth access token to it
	 * 
	 * @param url
	 * @return the new uri
	 */
	protected Uri getAuthenticatedUri(String url) {
		return getAuthenticatedUri(Uri.parse(url));
	}
	
	/**
	 * Returns the uri for the given url after appending the oauth access token to it
	 * 
	 * @param uri
	 * @return the new uri
	 */
	protected Uri getAuthenticatedUri(Uri uri) {
		Uri.Builder builder = uri.buildUpon();
		builder.appendQueryParameter(ACCESS_TOKEN_KEY, getAccessToken());
		return builder.build();
	}
	
	protected String getAccessToken() {
		return app.getAccessToken();
	}
	
	/**
	 * Do an HTTP GET request for the given URI
	 * 
	 * @param uri
	 * @param appendAccessToken
	 * @return the HttpResponse
	 */
	protected HttpResponse get(Uri uri, Boolean appendAccessToken) {
		if (appendAccessToken) uri = getAuthenticatedUri(uri);
		return executeRequest(new HttpGet(uri.toString()));
	}
	
	/**
	 * Do an HTTP GET request for the given URI
	 * 
	 * @param uri
	 * @param appendAccessToken
	 * @return the body of the HttpResponse
	 */
	protected String getResponseBody(Uri uri, Boolean appendAccessToken) {
		return responseBody(get(uri, appendAccessToken));
	}
	
	/**
	 * Do an HTTP POST for the given URI
	 * 
	 * @param uri
	 * @param appendAccessToken
	 * @return The HttpResponse
	 */
	protected HttpResponse post(Uri uri, Boolean appendAccessToken) {
		if (appendAccessToken) uri = getAuthenticatedUri(uri);
		return executeRequest(new HttpPost(uri.toString()));
	}
	
	/**
	 * Do an HTTP POST for the given URI
	 * 
	 * @param uri
	 * @param appendAccessToken
	 * @return the body of the HttpResponse
	 */
	protected String postResponseBody(Uri uri, Boolean appendAccessToken) {
		return responseBody(post(uri, appendAccessToken)); 
	}
	
	private String responseBody(HttpResponse response) {
		if (response == null) return null;
		else {
			BufferedReader reader;
			try {
				reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				StringBuffer buffer = new StringBuffer();
				String line;
				while ((line = reader.readLine()) != null) {
					buffer.append(line);
				}
				reader.close();
				return buffer.toString();
			} catch (IOException e) {
				ErrorDisplay.debug(this, e);
				return null;
			}
		}
	}
	
	private HttpResponse executeRequest(HttpUriRequest request) {
		HttpResponse response = null;
		try {
			response = getClient().execute(request);
			Integer status = response.getStatusLine().getStatusCode();
			if (!status.equals(SUCCESS)) {
				throw new Exception("Invalid response from '" + request.getURI().toString() + "' (" + status + ")");
			}
		} catch (Exception e) {
			// Blank out the response and display an error message
			response = null;
			ErrorDisplay.debug(this, e);
		}
		return response;
	}
	
	private HttpClient getClient() {
		return client;
	}
	
	//////// HELPERS ///////////
	
	/**
	 * Helper to build the base user API URL for the current user
	 * https://api.github.com/users/:user
	 * @return The StringBuilder with the URL built
	 */
	protected StringBuilder getBuilderForCurrentUserUrl() {
		String login = getGitDroidApplication().getCurrentUserLogin();
		if (login == null) return null;
		StringBuilder builder = new StringBuilder();
		builder.append(UserService.USERS_URL);
		builder.append("/");
		builder.append(login);
		return builder;
	}
}
