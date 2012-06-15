package com.rushdevo.gitdroid.ui;

import android.widget.ListView;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

/**
 * @author jasonrush
 * Class for creating a fully-expanded listview
 * Lets you use a listview inside of a scrollview, which is
 * normally somewhat frowned upon, but has its uses
 */
public class ExpandedListView extends ListView {
	private android.view.ViewGroup.LayoutParams params;
    private int old_count = 0;

    public ExpandedListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getCount() != old_count) {
            old_count = getCount();
            params = getLayoutParams();
            int height = 0;
            for (int i=0; i<getChildCount(); i++) {
            	height += getChildAt(i).getHeight();
            }
            params.height = height;
            setLayoutParams(params);
        }

        super.onDraw(canvas);
    }
}
