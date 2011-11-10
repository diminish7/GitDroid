package com.rushdevo.gitdroid;

import java.util.List;

import com.google.inject.Module;

import roboguice.application.RoboApplication;

/**
 * @author jasonrush
 * Main application class
 */
public class GitDroidApplication extends RoboApplication {
	@Override
	protected void addApplicationModules(List<Module> modules) {
		modules.add(new GitDroidModule());
	}
}
