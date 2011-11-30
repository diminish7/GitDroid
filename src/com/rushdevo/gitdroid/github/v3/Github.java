package com.rushdevo.gitdroid.github.v3;

import java.io.IOException;
import java.util.Properties;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.util.Log;

import com.rushdevo.gitdroid.github.v3.services.OAuthService;
import com.rushdevo.gitdroid.utils.ErrorDisplay;

/**
 * @author jasonrush
 *	Contains shared github logic
 */
public class Github {
	/** Name of the field in shared properties that stores the oauth access token */
	private static final String TOKEN_FIELD_NAME = "token";
	
	private Context ctx;
	private SharedPreferences sharedPrefs;
	
	private String accessToken;
	private String clientId;
	private String clientSecret;
	private String callbackUrl;
	
	private OAuthService oAuthService;
	
	/**
	 * Basic constructor
	 */
	public Github(Context ctx, SharedPreferences sharedPrefs) {
		this.ctx = ctx;
		this.sharedPrefs = sharedPrefs;
		Properties githubProperties = new Properties();
		try {
			githubProperties.load(this.ctx.getResources().getAssets().open("github.properties"));
			clientId = githubProperties.getProperty("client_id");
			clientSecret = githubProperties.getProperty("client_secret");
			callbackUrl = githubProperties.getProperty("callback_url");
			accessToken = sharedPrefs.getString(TOKEN_FIELD_NAME, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getClientId() {
		return clientId;
	}
	
	public String getClientSecret() {
		return clientSecret;
	}
	
	public Uri getOAuthUri() {
		return getOAuthServiceInstance().getAuthUri();
	}
	
	public String getCallbackUrl() {
		return callbackUrl;
	}
	
	public String getAccessToken() {
		return accessToken;
	}
	
	
	/* (non-Javadoc)
	 * @see com.rushdevo.gitdroid.github.v3.Github#retrieveAccessToken(Uri uri)
	 */
	public String retrievAccessToken(Uri uri) {
		if (uri != null && uri.toString().startsWith(getCallbackUrl())) {
			try {
				// We got here from an auth call
				// Grab the code
				String code = uri.getQueryParameter("code");
				// Then grab the access accessToken
				return getOAuthServiceInstance().retrieveAccessToken(code);
			} catch (Exception e) {
				ErrorDisplay.debug(this, "An error occurred retrieving the access code: " + e.getMessage());
				return "";
			}
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.rushdevo.gitdroid.github.v3.Github#updateAccessToken(String token)
	 */
	public void updateAccessToken(String token) {
		// Set the access token aside
		accessToken = token;
		// Save the access token to shared prefs
		Editor editor = sharedPrefs.edit();
		editor.putString(TOKEN_FIELD_NAME, accessToken);
		editor.commit();
	}
	
	protected OAuthService getOAuthServiceInstance() {
		if (oAuthService == null && clientId != null && callbackUrl != null && clientSecret != null) {
			oAuthService = new OAuthService(ctx, clientId, callbackUrl, clientSecret, OAuthService.ALL_SCOPES);
		}
		return oAuthService;
	}
}