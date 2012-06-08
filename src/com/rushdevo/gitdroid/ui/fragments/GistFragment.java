package com.rushdevo.gitdroid.ui.fragments;

import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.rushdevo.gitdroid.R;
import com.rushdevo.gitdroid.github.v3.models.Comment;
import com.rushdevo.gitdroid.github.v3.models.Gist;
import com.rushdevo.gitdroid.github.v3.services.GistService;
import com.rushdevo.gitdroid.ui.CommentsAdapter;
import com.rushdevo.gitdroid.ui.GistView;
import com.rushdevo.gitdroid.utils.NonConfigurationChangeData;

/**
 * @author jasonrush
 * Fragment for displaying a single gist
 */
public class GistFragment extends BaseFragment {
	private Gist gist;
	
	private GistView gistHeader;
	private GistService service;
	private ProgressBar gistSpinner;
	private ProgressBar commentSpinner;
	private WebView webView;
	private View commentsContainer;
	private ListView commentsList;
	
	private CommentsAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.gist, container, false);
		
		commentsContainer = view.findViewById(R.id.comments_container);
		commentsList = (ListView)view.findViewById(R.id.comments_list);
		this.adapter = new CommentsAdapter(getActivity(), android.R.layout.simple_list_item_1, getComments());
		commentsList.setAdapter(adapter);
		
		gistSpinner = (ProgressBar)view.findViewById(R.id.gist_progress);
		commentSpinner = (ProgressBar)view.findViewById(R.id.comment_progress);
		setupWebView(view);
		
		gistHeader = (GistView)view.findViewById(R.id.gist_header);
		gistHeader.setGist(gist);
		displayGistFiles(gist, view);
		
		return view;
	}
	
	@Override
	public void setContentObject(Object object) {
		setGist((Gist)object);
	}
	
	@Override
	public Object onRetainCustomNonConfigurationInstance() {
		return new NonConfigurationChangeData(this, getGist());
	}
	
	@Override
	public void initializeNonConfigurationChangeData(NonConfigurationChangeData data) {
		if (data != null) {
			Object obj = data.getData(this);
			if (obj != null) gist = (Gist)obj;
		}
	}

	///////// GETTERS AND SETTERS ///////////
	public void setGist(Gist gist) {
		this.gist = gist;
	}

	public Gist getGist() {
		return gist;
	}
	
	public void setComments(List<Comment> comments) {
		if (this.gist != null) this.gist.setAllComments(comments);
	}
	
	public List<Comment> getComments() {
		if (this.gist == null) return new ArrayList<Comment>();
		else return this.gist.getAllComments();
	}
	
	public GistService getGistServiceInstance() {
		if (service == null) service = new GistService(getActivity());
		return service;
	}
	
	////////// HELPERS /////////////
	public void retrieveComments(Gist gist) {
		setComments(getGistServiceInstance().retrieveComments(gist));
	}
	
	private void setupWebView(View container) {
		webView = (WebView)container.findViewById(R.id.gist_container);
		WebSettings settings = webView.getSettings();
		
		settings.setJavaScriptEnabled(true);
		settings.setSupportZoom(true);
		settings.setBuiltInZoomControls(true);
		settings.setLightTouchEnabled(true);
		settings.setLoadsImagesAutomatically(true);
		
		webView.setWebViewClient(new WebViewClient() {
			// Hide the spinner after the page loads
			public void onPageFinished(WebView view, String url) {
				gistSpinner.setVisibility(View.GONE);
				loadComments(gist);
			}
		});
	}
	
	private void displayGistFiles(Gist gist, View container) {
		if (gist != null) {
			gistSpinner.setVisibility(View.VISIBLE);
			
			StringBuilder builder = new StringBuilder();
			builder.append("<html>");
			builder.append("<body>");
			builder.append("<script src=\"");
			builder.append(getGistServiceInstance().getGistDisplayScriptUrl(gist));
			builder.append("\"></script>");
			builder.append("</body>");
			builder.append("</html>");
			
			webView.loadData(builder.toString(), "text/html", "UTF-8");
		}
	}
	
	private void showCommentSpinner() {
		commentSpinner.setVisibility(View.VISIBLE);
		commentsContainer.setVisibility(View.GONE);
	}
	
	private void hideCommentSpinner() {
		commentSpinner.setVisibility(View.GONE);
		commentsContainer.setVisibility(View.VISIBLE);
	}
	
	private void loadComments(Gist gist) {
		if (gist != null) new QueryGistCommentsTask(gist).execute();
	}
	
	///////////// INNER CLASSES ////////////////////////
	private class QueryGistCommentsTask extends AsyncTask<Void, Void, Void> {
		private Gist gist;
		
		public QueryGistCommentsTask(Gist gist) {
			this.gist = gist;
		}
		
		protected void onPreExecute() {
			showCommentSpinner();
		}
		
	     protected Void doInBackground(Void... args) {
	    	 retrieveComments(gist);
	         return null;
	     }

	     protected void onPostExecute(Void arg) {
	    	 adapter.setComments(getComments());
	    	 adapter.notifyDataSetChanged();
	    	 hideCommentSpinner();
	     }
	 }
}
