package com.rushdevo.gitdroid.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.drawable.Drawable;

import com.rushdevo.gitdroid.github.v3.models.User;

/**
 * @author jasonrush
 * Helper methods for dealing with user avatars
 */
public class UserAvatarHelperImpl implements UserAvatarHelper {
	
	public Drawable getAvatarForUserAsDrawable(User user, Drawable defaultAvatar) {
		String avatarUrl = user.getAvatarOrGravatarUrl();
		if (avatarUrl == null) return null; // Shouldn't happen
		Drawable avatar = null;
		URL url;
		InputStream is;
		try {
			url = new URL(avatarUrl);
			is = (InputStream)url.getContent();
			avatar = Drawable.createFromStream(is, user.getName());
		} catch (MalformedURLException e) {
			ErrorDisplay.debug(user, e);
		} catch (IOException e) {
			ErrorDisplay.debug(user, e);
		}
		if (avatar == null) avatar = defaultAvatar;
		return avatar;
	}
	
}
