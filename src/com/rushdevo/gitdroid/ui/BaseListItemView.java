package com.rushdevo.gitdroid.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

/**
 * @author jasonrush
 * The base class for shared behavior between custom list item views
 */
public abstract class BaseListItemView extends LinearLayout {
	
	/**
	 * Base constructor
	 * @param ctx
	 */
	public BaseListItemView(Context ctx) { super(ctx); }
	
	/**
	 * Base constructor
	 * @param ctx
	 * @param attrs
	 */
	public BaseListItemView(Context ctx, AttributeSet attrs) { super(ctx, attrs); }
	
	
	///////// HELPERS ////////////
	protected View inflateView(Context ctx, int listItemViewId) {
		LayoutInflater inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view  = inflater.inflate(listItemViewId, null);
		updateView(view);
		return view;
	}
	
	protected abstract void updateView(View view);
}
