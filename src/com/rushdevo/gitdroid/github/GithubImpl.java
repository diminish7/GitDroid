package com.rushdevo.gitdroid.github;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;

import com.github.api.v2.services.GitHubException;
import com.github.api.v2.services.GitHubServiceFactory;
import com.github.api.v2.services.OAuthService;
import com.github.api.v2.services.OAuthService.Scope;
import com.google.inject.Inject;
import com.rushdevo.gitdroid.util.ErrorDisplay;

/**
 * @author jasonrush
 *	Contains shared github logic
 */
public class GithubImpl implements Github {
	/** Name of the field in shared properties that stores the oauth access token */
	private static final String TOKEN_FIELD_NAME = "token";
	/** Github authorization scopes - Include all possible scopes */
	public static final Set<Scope> SCOPES = new TreeSet<Scope>();
	static {
		SCOPES.add(OAuthService.Scope.USER);
		SCOPES.add(OAuthService.Scope.PUBLIC_REPO);
		SCOPES.add(OAuthService.Scope.REPOSITORY);
		SCOPES.add(OAuthService.Scope.GIST);
	}
	
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
	@Inject
	public GithubImpl(Context ctx, SharedPreferences sharedPrefs) {
		this.ctx = ctx;
		this.sharedPrefs = sharedPrefs;
		Properties githubProperties = new Properties();
		try {
			githubProperties.load(ctx.getResources().getAssets().open("github.properties"));
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
	
	public String getCallbackUrl() {
		return callbackUrl;
	}
	
	public String getToken() {
		return accessToken;
	}
	
	public OAuthService getOAuthServiceInstance() {
		if (oAuthService == null) {
			oAuthService = GitHubServiceFactory.newInstance().createOAuthService(clientId, clientSecret);
		}
		return oAuthService;
	}
	
	public String getAuthUrl() {
		OAuthService oAuthService = getOAuthServiceInstance();
		return oAuthService.getAuthorizationUrl(getCallbackUrl(), SCOPES);
	}
	
	/* (non-Javadoc)
	 * @see com.rushdevo.gitdroid.github.Github#retrieveAccessToken(Uri uri)
	 */
	public String retrievAccessToken(Uri uri) {
		try {
			if (uri != null && uri.toString().startsWith(getCallbackUrl())) {
				// We got here from an auth call
				// Grab the code
				String code = uri.getQueryParameter("code");
				// Then grab the access accessToken
				return getOAuthServiceInstance().getAccessToken(getCallbackUrl(), code);
			}
		} catch (GitHubException e) {
			ErrorDisplay.errorToast(ctx, e);
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.rushdevo.gitdroid.github.Github#updateAccessToken(String token)
	 */
	public void updateAccessToken(String token) {
		// Set the access token aside
		accessToken = token;
		// Save the access token to shared prefs
		Editor editor = sharedPrefs.edit();
		editor.putString(TOKEN_FIELD_NAME, accessToken);
		editor.commit();
	}
}