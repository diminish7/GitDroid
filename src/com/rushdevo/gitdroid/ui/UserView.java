package com.rushdevo.gitdroid.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rushdevo.gitdroid.R;
import com.rushdevo.gitdroid.github.v3.models.User;

/**
 * @author jasonrush
 * View for displaying a Github user in a list
 */
public class UserView extends LinearLayout {
	private User user;
	
	/**
	 * Constructor
	 * @param ctx
	 * @param user
	 */
	public UserView(Context ctx, User user) {
		super(ctx);
		this.user = user;
		addView(inflateView(ctx), new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT));
	}
	
	// Getters and Setters
	public User getUser() {
		return this.user;
	}
	public void setUser(User user) {
		this.user = user;
		updateView(this);
	}
	
	// Helpers
	private View inflateView(Context ctx) {
		LayoutInflater inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view  = inflater.inflate(R.layout.user_list_item, null);
		updateView(view);
		return view;
	}
	
	private void updateView(View view) {
		// Grab the views from the layout
		TextView loginView = (TextView)view.findViewById(R.id.user_login);
		TextView nameView = (TextView)view.findViewById(R.id.user_name);
		ImageView avatarView = (ImageView)view.findViewById(R.id.avatar_view);
		// Set the content of the views
		loginView.setText(user.getLogin());
		if (user.getName() != null && user.getName().length() > 0) {
			StringBuilder nameBuilder = new StringBuilder();
			nameBuilder.append("(");
			nameBuilder.append(user.getName());
			nameBuilder.append(")");
			nameView.setText(nameBuilder.toString());
		} else {
			nameView.setText("");
		}
		avatarView.setImageDrawable(user.getAvatar());
	}
}
