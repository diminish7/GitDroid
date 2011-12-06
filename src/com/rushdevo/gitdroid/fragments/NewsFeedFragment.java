package com.rushdevo.gitdroid.fragments;

import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rushdevo.gitdroid.R;
import com.rushdevo.gitdroid.github.v3.models.Event;
import com.rushdevo.gitdroid.github.v3.services.EventService;

/**
 * @author jasonrush
 * Display fragment for news feed content
 */
public class NewsFeedFragment extends BaseFragment {
	private EventService service;
	private List<Event> receivedEvents;
	private Integer page;
	
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
		return (getReceivedEvents() != null);
	}
	
	@Override
	protected void initializeView() {
		hideSpinner(R.id.news_feed_todo);
	}
	
	// Getters and Setters
	public List<Event> getReceivedEvents() {
		return this.receivedEvents;
	}
	
	public Integer getPage() {
		if (page == null) page = 1;
		return page;
	}
	
	public EventService getEventServiceInstance() {
		if (service == null) service = new EventService(getActivity());
		return service;
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
			receivedEvents = getEventServiceInstance().retrieveReceivedEvents(getPage());
			for (Event event : receivedEvents) {
				event.toString();
			}
//			feedService = getGithub().getFactoryInstance().createFeedService();
//			feedService.setAuthentication(getAuthentication());
//			feed = feedService.getPrivateUserFeed(getCurrentUser().getLogin(), 20);
//			logd("**** Feed Info *****");
//			logd("Title: " + feed.getTitle());
//			logd("Auther: " + feed.getAuthor());
//			logd("Link: " + feed.getLink());
//			logd("Desc: " + feed.getDescription());
//			logd("**** Entries *****");
//			for (FeedEntry entry : feed.getEntries()) {
//				logd("Title: " + entry.getTitle());
//				logd("Auther: " + entry.getAuthor());
//				logd("Link: " + entry.getLink());
//				logd("Date: " + entry.getPublishedDate());
//				logd("Content: " + entry.getContent());
//				for (String cat : entry.getCategories()) {
//					logd("   Category: " + cat);
//				}
//			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// Initialize the view
			getInitHandler().sendEmptyMessage(INIT_VIEW_MESSAGE);
		}
		
	}
}
