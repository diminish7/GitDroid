/**
 * 
 */
package com.rushdevo.gitdroid;

import com.rushdevo.gitdroid.github.Github;
import com.rushdevo.gitdroid.github.GithubImpl;

import roboguice.config.AbstractAndroidModule;

/**
 * @author jasonrush
 * Main module class
 */
public class GitDroidModule extends AbstractAndroidModule {

	/* (non-Javadoc)
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {
		bind(Github.class).to(GithubImpl.class);
	}
}
