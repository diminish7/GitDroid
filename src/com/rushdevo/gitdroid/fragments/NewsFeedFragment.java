package com.rushdevo.gitdroid.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.api.v2.schema.Feed;
import com.github.api.v2.services.FeedService;
import com.rushdevo.gitdroid.R;

/**
 * @author jasonrush
 * Display fragment for news feed content
 */
public class NewsFeedFragment extends BaseFragment {
	private FeedService feedService;
	private Feed feed;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.news_feed, container, false);
	}
	
	@Override
	protected void initializeData() {
		if (viewIsReady()) initializeView(); 
		else new RetrieveFeedTask().execute();
	}
	
	@Override
	protected boolean viewIsReady() {
		return getCurrentUser() != null && feed != null;
	}
	
	@Override
	protected void initializeView() {
		hideSpinner(R.id.news_feed_todo);
	}
	
	///////////// INNER CLASSES ////////////////////////
	/**
	 * Async task for passing the temp code to github to get the access token back
	 */
	private class RetrieveFeedTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			showSpinner(R.id.news_feed_todo);
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			feedService = getGithub().getFactoryInstance().createFeedService();
			feedService.setAuthentication(getAuthentication());
			feed = feedService.getPrivateUserFeed(getCurrentUser().getLogin(), 20);
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// Initialize the view
			getInitHandler().sendEmptyMessage(INIT_VIEW_MESSAGE);
		}
		
	}
}
