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
import android.util.Log;

import com.rushdevo.gitdroid.utils.ErrorDisplay;

/**
 * @author jasonrush
 * Base Github Service
 */
public abstract class GithubService {
	protected Context ctx;
	private HttpClient client;
	private static final Integer SUCCESS = 200;
	
	public GithubService(Context ctx) {
		this.ctx = ctx;
	}
	
	protected HttpResponse get(Uri uri) {
		return executeRequest(new HttpGet(uri.toString()));
	}
	
	protected String getResponseBody(Uri uri) {
		return responseBody(get(uri));
	}
	
	protected HttpResponse post(Uri uri) {
		return executeRequest(new HttpPost(uri.toString()));
	}
	
	protected String postResponseBody(Uri uri) {
		return responseBody(post(uri)); 
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
				ErrorDisplay.errorToast(ctx, e);
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
			ErrorDisplay.errorToast(ctx, e);
		}
		return response;
	}
	
	private HttpClient getClient() {
		if (client == null) {
			client = new DefaultHttpClient();
		}
		return client;
	}
}
