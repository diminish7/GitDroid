/**
 * 
 */
package com.rushdevo.gitdroid.github;

import android.net.Uri;

import com.github.api.v2.schema.User;
import com.github.api.v2.services.GitHubServiceFactory;
import com.github.api.v2.services.OAuthService;
import com.github.api.v2.services.auth.OAuthAuthentication;

/**
 * @author jasonrush
 *
 */
public interface Github {
	/**
	 * @return The client id from the github properties file
	 */
	public abstract String getClientId();
	
	/**
	 * @return The client secret from the github properties file
	 */
	public abstract String getClientSecret();
	
	/**
	 * @return The callback URL from the github properties file
	 */
	public abstract String getCallbackUrl();
	
	/**
	 * @return The singleton auth accessToken (pulled from the db if we've saved it)
	 */
	public abstract String getToken();
	
	/**
	 * @return The singleton github factory
	 */
	public abstract GitHubServiceFactory getFactoryInstance();
	
	/**
	 * @return The singleton github OAuth service
	 */
	public abstract OAuthService getOAuthServiceInstance();
	
	/**
	 * @return The OAuth authentication object
	 */
	public abstract OAuthAuthentication getOAuthAuthentication();
	
	/**
	 * @return The Github auth URL to which the user should be redirected for external auth
	 */
	public abstract String getAuthUrl();
	
	/**
	 * Checks the URI to see if it contains the OAuth code in it's params
	 * If so, uses the code to retrieve the access accessToken from Github
	 * 
	 * @param uri - The URI from the incoming intent
	 */
	public abstract String retrievAccessToken(Uri uri);
	
	/**
	 * Set the oauth access token aside in this.accessToken and put it in default shared prefs
	 * 
	 * @param token - The Github access token
	 */
	public abstract void updateAccessToken(String token);
	
	/**
	 * Set aside the authenticated user
	 */
	public abstract void updateCurrentUser();
	
	/**
	 * Set the authenticated user name in this.userName and put it in default shared prefs
	 * 
	 * return The authenticated user
	 */
	public abstract User getCurrentUser();
}
