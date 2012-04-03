package com.rushdevo.gitdroid.utils;

import android.graphics.drawable.Drawable;

import com.rushdevo.gitdroid.github.v3.models.User;

/**
 * @author jasonrush
 * Helper methods for dealing with user avatars
 */
public interface UserAvatarHelper {
	public Drawable getAvatarForUserAsDrawable(User user, Drawable defaultAvatar);
}
