package com.rushdevo.gitdroid;

import java.io.IOException;
import java.util.Properties;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.rushdevo.gitdroid.github.v3.models.User;
import com.rushdevo.gitdroid.github.v3.services.OAuthService;

/**
 * @author jasonrush
 *
 */
public class GitDroidApplication extends Application {
	// Consts
	public static final String GLOBAL_PREFS_NAME = "GLOBAL_GITDROID_PREFS";
	private static final String TOKEN_FIELD_NAME = "token";
	
	// Github Auth Properties
	private String accessToken;
	private User currentUser;
	private String clientId;
	private String clientSecret;
	private String callbackUrl;
	private OAuthService oAuthService;
	
	// Hooks and overrides
	@Override
	public void onCreate() {
		super.onCreate();
		Properties githubProperties = new Properties();
		try {
			githubProperties.load(getResources().getAssets().open("github.properties"));
			clientId = githubProperties.getProperty("client_id");
			clientSecret = githubProperties.getProperty("client_secret");
			callbackUrl = githubProperties.getProperty("callback_url");
			accessToken = getSharedPreferences(GLOBAL_PREFS_NAME, MODE_PRIVATE).getString(TOKEN_FIELD_NAME, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Getters and Setters
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public User getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	public String getCurrentUserLogin() {
		if (currentUser == null) {
			return null;
		} else {
			return currentUser.getLogin();
		}
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	public String getCallbackUrl() {
		return callbackUrl;
	}
	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}
	public OAuthService getOAuthServiceInstance() {
		if (oAuthService == null && clientId != null && callbackUrl != null && clientSecret != null) {
			oAuthService = new OAuthService(this, clientId, callbackUrl, clientSecret, OAuthService.ALL_SCOPES);
		}
		return oAuthService;
	}
	
	// Helpers
	public void updateAccessToken(String token) {
		// Set the access token aside
		accessToken = token;
		// Save the access token to shared prefs
		SharedPreferences sharedPrefs = getSharedPreferences(GLOBAL_PREFS_NAME, MODE_PRIVATE);
		Editor editor = sharedPrefs.edit();
		editor.putString(TOKEN_FIELD_NAME, accessToken);
		editor.commit();
	}
}
