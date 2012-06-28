package com.rushdevo.gitdroid.ui.fragments;

import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.rushdevo.gitdroid.R;
import com.rushdevo.gitdroid.github.v3.models.Comment;
import com.rushdevo.gitdroid.github.v3.models.Commentable;
import com.rushdevo.gitdroid.ui.CommentsAdapter;
import com.rushdevo.gitdroid.utils.NonConfigurationChangeData;

/**
 * @author jasonrush
 *
 */
public class CommentsFragment extends BaseFragment implements OnClickListener {
	private CommentsAdapter adapter;
	private Commentable commentable;
	
	private View commentsContainer;
	private ProgressBar commentSpinner;
	private ListView commentsList;
	private TextView commentsLabel;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.comments, container, false);
		
		commentsList = (ListView)view.findViewById(android.R.id.list);
		View commentButtonContainer = inflater.inflate(R.layout.comment_button, commentsList, false);
		Button commentButton = (Button)commentButtonContainer.findViewById(R.id.add_comment_button);
		commentButton.setOnClickListener(this);
		
		commentsList.addFooterView(commentButtonContainer);
		
		commentSpinner = (ProgressBar)view.findViewById(R.id.comment_progress);
		commentsContainer = view.findViewById(R.id.comments_container);
		
		commentsLabel = (TextView)view.findViewById(R.id.comments_label);
		commentsLabel.setText(getLabel());
		
		commentsList.setEmptyView(view.findViewById(android.R.id.empty));
		this.adapter = new CommentsAdapter(getActivity(), android.R.layout.simple_list_item_1, getComments());
		commentsList.setAdapter(adapter);
		
		if (getComments() == null || getComments().isEmpty()) {
			new QueryCommentsTask(getCommentable()).execute();
		} else {
			hideCommentSpinner();
		}

		return view;
	}
	
	@Override
	public void onClick(View v) {
		getObjectSelectedListener().OnObjectSelected(getCommentable(), new CommentFormFragment());
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
	
	public void setCommentable(Commentable commentable) {
		this.commentable = commentable;
	}
	
	public Commentable getCommentable() {
		return this.commentable;
	}
	
	public List<Comment> getComments() {
		return getCommentable().getAllComments();
	}
	
	public String getLabel() {
		StringBuilder builder = new StringBuilder();
		builder.append(getString(R.string.comments));
		if (getCommentable() != null) {
			builder.append(" for ");
			builder.append(getCommentable().getDisplayName());
		}
		return builder.toString();
	}
	
	private void showCommentSpinner() {
		commentSpinner.setVisibility(View.VISIBLE);
		commentsContainer.setVisibility(View.GONE);
	}

	private void hideCommentSpinner() {
		commentSpinner.setVisibility(View.GONE);
		commentsContainer.setVisibility(View.VISIBLE);
	}

	
	////////// INNER CLASSES ///////////
	private class QueryCommentsTask extends AsyncTask<Void, Void, Void> {
		private Commentable commentable;
		
		public QueryCommentsTask(Commentable commentable) {
			this.commentable = commentable;
		}
		
		protected void onPreExecute() {
			showCommentSpinner();
		}

		protected Void doInBackground(Void... args) {
			commentable.retrieveComments(getActivity());
			return null;
		}

		protected void onPostExecute(Void arg) {
			adapter.setComments(getComments());
			adapter.notifyDataSetChanged();
			hideCommentSpinner();
		}
	}
}
