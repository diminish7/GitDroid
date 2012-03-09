package com.rushdevo.gitdroid.ui;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.rushdevo.gitdroid.github.v3.models.Gist;

/**
 * @author jasonrush
 * Custom adapter for showing gists in a list
 */
public class GistAdapter extends ArrayAdapter<Gist> {
	private List<Gist> gists;
	private Context ctx;
	
	/**
	 * Constructor
	 * @param ctx
	 * @param textViewResourceId
	 * @param gists
	 */
	public GistAdapter(Context context, int textViewResourceId, List<Gist> gists) {
		super(context, textViewResourceId, gists);
		this.gists = gists;
		this.ctx = context;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		GistView view;
		Gist gist = gists.get(position);
		if (convertView == null) {
			view = new GistView(ctx, gist);
		} else {
			view = (GistView)convertView;
			if (view.getGist() != gist) {
				view.setGist(gist);
				view.invalidate();
			}
		}
		return view;
	};
	
	// Getters and Setters
	public void setGists(List<Gist> gists) {
		this.gists = gists;
		clear();
		for (Gist gist : gists) {
			add(gist);
		}
	}
}
