package com.rushdevo.gitdroid.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.rushdevo.gitdroid.R;
import com.rushdevo.gitdroid.github.v3.models.Commentable;
import com.rushdevo.gitdroid.utils.NonConfigurationChangeData;

/**
 * @author jasonrush
 * Fragment with a form for adding/editing comments
 */
public class CommentFormFragment extends BaseFragment implements OnClickListener {
	Commentable commentable;
	Button saveCommentButton;
	ProgressBar spinner;
	EditText commentField;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.comment_form, container, false);
		saveCommentButton = (Button)view.findViewById(R.id.save_comment_button);
		saveCommentButton.setOnClickListener(this);
		spinner = (ProgressBar)view.findViewById(R.id.save_comment_spinner);
		commentField = (EditText)view.findViewById(R.id.comment_field);
		
		return view;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		commentField.requestFocus(); 
		InputMethodManager mgr = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
		mgr.showSoftInput(commentField, InputMethodManager.SHOW_IMPLICIT);

	}
	
	@Override
	public void onClick(View v) {
		showSpinner();
		// TODO:
		// 1. In an async task, delegate to commentable to save the comment
		// 2. Hide the spinner and return to the previous fragment
	}

	@Override
	public Object onRetainCustomNonConfigurationInstance() {
		return new NonConfigurationChangeData(this, getCommentable());
	}

	@Override
	public void setContentObject(Object object) {
		setCommentable((Commentable)object);
	}

	@Override
	protected void initializeNonConfigurationChangeData(NonConfigurationChangeData data) {
		if (data != null) {
			Object obj = data.getData(this);
			if (obj != null) commentable = (Commentable)obj;
		}
	}
	
	public void showSpinner() {
		saveCommentButton.setVisibility(View.GONE);
		spinner.setVisibility(View.VISIBLE);
	}
	
	public void hideSpinner() {
		saveCommentButton.setVisibility(View.VISIBLE);
		spinner.setVisibility(View.GONE);
	}
	
	///////// GETTERS AND SETTERS //////////
	public Commentable getCommentable() {
		return this.commentable;
	}
	
	public void setCommentable(Commentable commentable) {
		this.commentable = commentable;
	}
}
