/**
 * 
 */
package com.rushdevo.gitdroid.github.v3.services;

import java.util.Set;
import java.util.TreeSet;

import android.content.Context;
import android.net.Uri;

import com.rushdevo.gitdroid.utils.ErrorDisplay;

/**
 * @author jasonrush
 * 
 * Github OAuth calls service (http://developer.github.com/v3/oauth/)
 */
public class OAuthService extends GithubService {
	// Auth URL consts
	public static final String OAUTH_URL = "https://github.com/login/oauth/authorize";
	public static final String ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token";
	public static final String CLIENT_ID_KEY = "client_id";
	public static final String CLIENT_SECRET_KEY = "client_secret";
	public static final String REDIRECT_URI_KEY = "redirect_uri";
	public static final String CODE_KEY = "code";
	public static final String SCOPE_KEY = "scope";
	
	
	// Auth scope consts
	public static final String USER_SCOPE = "user";
	public static final String PUBLIC_REPO_SCOPE = "public_repo";
	public static final String REPO_SCOPE = "repo";
	public static final String GIST_SCOPE = "gist";
	public static final Set<String> ALL_SCOPES = new TreeSet<String>();
	static {
		ALL_SCOPES.add(USER_SCOPE);
		ALL_SCOPES.add(PUBLIC_REPO_SCOPE);
		ALL_SCOPES.add(REPO_SCOPE);
		ALL_SCOPES.add(GIST_SCOPE);
	}
	
	// Properties
	private String clientId;
	private String clientSecret;
	private String redirectUri;
	private Set<String> scopes;
	
	/**
	 * Initialize the oauth service with client id, redirect uri and scopes
	 * 
	 * @param clientId
	 * @param redirectUri
	 * @param scopes
	 */
	public OAuthService(Context ctx, String clientId, String redirectUri, String clientSecret, Set<String> scopes) {
		super(ctx);
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.redirectUri = redirectUri;
		this.scopes = scopes;
	}
	
	/**
	 * Get the URI for requesting an OAuth code
	 * @return the uri
	 */
	public Uri getAuthUri() {
		Uri uri = Uri.parse(OAUTH_URL);
		Uri.Builder builder = uri.buildUpon();
		builder.appendQueryParameter(CLIENT_ID_KEY, getClientId());
		builder.appendQueryParameter(REDIRECT_URI_KEY, getRedirectUri());
		builder.appendQueryParameter(SCOPE_KEY, getScopeString());
		return builder.build();
	}
	
	/**
	 * Get the URI for retrieving the access token for a given code
	 * @param code
	 * @return the uri
	 */
	public Uri getAccessTokenUri(String code) {
		Uri uri = Uri.parse(ACCESS_TOKEN_URL);
		Uri.Builder builder = uri.buildUpon();
		builder.appendQueryParameter(CLIENT_ID_KEY, getClientId());
		builder.appendQueryParameter(CLIENT_SECRET_KEY, getClientSecret());
		builder.appendQueryParameter(REDIRECT_URI_KEY, getRedirectUri());
		builder.appendQueryParameter(CODE_KEY, code);
		return builder.build();
	}
	
	// Getters and setters
	public String getClientId() {
		return this.clientId;
	}
	
	public String getClientSecret() {
		return this.clientSecret;
	}
	
	public String getRedirectUri() {
		return this.redirectUri;
	}
	
	public Set<String> getScopes() {
		return this.scopes;
	}
	
	// Helpers
	
	/**
	 * Call to Github and request the access token for the given code
	 * @param The uri containing the temporary oauth code
	 * @return the access token
	 */
	public String retrievAccessToken(Uri uri) {
		if (uri != null && uri.toString().startsWith(app.getCallbackUrl())) {
			try {
				// We got here from an auth call
				// Grab the code
				String code = uri.getQueryParameter("code");
				// Then grab the access accessToken
				return retrieveAccessToken(code);
			} catch (Exception e) {
				ErrorDisplay.debug(this, "An error occurred retrieving the access code: " + e.getMessage());
				return "";
			}
		}
		return null;
	}
	
	private String retrieveAccessToken(String code) {
		String responseBody = postResponseBody(getAccessTokenUri(code), false);
		if (responseBody != null) {
			String[] parts = responseBody.split("&");
			for (String part : parts) {
				String[] keyVal = part.split("=");
				if (keyVal.length == 2 && keyVal[0].equals("access_token")) {
					return keyVal[1];
				}
			}
			ErrorDisplay.debug(this, "**** COULDN'T RETRIEVE ACCESS CODE: INVALID RESPONSE -- " + responseBody + " ****");
		} else {
			ErrorDisplay.debug(this, "**** COULDN'T RETRIEVE ACCESS CODE: NO RESPONSE ****");
		}
		return "";
	}
	
	private String getScopeString() {
		StringBuilder builder = new StringBuilder();
		String delimiter = "";
		for (String scope : getScopes()) {
			builder.append(delimiter);
			builder.append(scope);
			delimiter = ",";
		}
		return builder.toString();
	}
}
