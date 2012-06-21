package com.rushdevo.gitdroid.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.rushdevo.gitdroid.R;
import com.rushdevo.gitdroid.github.v3.models.Gist;
import com.rushdevo.gitdroid.github.v3.services.GistService;
import com.rushdevo.gitdroid.ui.GistView;
import com.rushdevo.gitdroid.utils.NonConfigurationChangeData;

/**
 * @author jasonrush
 * Fragment for displaying a single gist
 */
public class GistFragment extends BaseFragment implements OnClickListener {
	private Gist gist;
	
	private GistView gistHeader;
	private GistService service;
	private ProgressBar gistSpinner;
	private WebView webView;
	private View commentsButton;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.gist, container, false);
		
		commentsButton = view.findViewById(R.id.comments_button);
		commentsButton.setOnClickListener(this);
		
		gistSpinner = (ProgressBar)view.findViewById(R.id.gist_progress);
		setupWebView(view);
		
		gistHeader = (GistView)view.findViewById(R.id.gist_header);
		gistHeader.setGist(gist);
		displayGistFiles(gist, view);
		
		return view;
	}
	
	@Override
	public void onClick(View v) {
		getObjectSelectedListener().OnObjectSelected(gist, new CommentsFragment());
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
	
	public GistService getGistServiceInstance() {
		if (service == null) service = new GistService(getActivity());
		return service;
	}
	
	////////// HELPERS /////////////
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
				commentsButton.setVisibility(View.VISIBLE);
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
}
