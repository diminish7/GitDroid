/**
 * 
 */
package com.rushdevo.gitdroid.ui;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.rushdevo.gitdroid.github.v3.models.User;

/**
 * @author jasonrush
 * Custom adapter for showing users in a list
 */
public class UserAdapter extends ArrayAdapter<User> {
	private List<User> users;
	private Context ctx;
	
	/**
	 * Constructor
	 * @param ctx
	 * @param textViewResourceId
	 * @param users
	 */
	public UserAdapter(Context context, int textViewResourceId, List<User> users) {
		super(context, textViewResourceId, users);
		this.users = users;
		this.ctx = context;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		UserView view;
		User user = users.get(position);
		if (convertView == null) {
			view = new UserView(ctx, user);
		} else {
			view = (UserView)convertView;
			if (view.getUser() != user) {
				view.setUser(user);
				view.invalidate();
			}
		}
		return view;
	};
	
	// Getters and Setters
	public void setUsers(List<User> users) {
		this.users = users;
		clear();
		for (User user : users) {
			add(user);
		}
	}
}
