package com.rushdevo.gitdroid.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;

import com.rushdevo.gitdroid.R;

public class ContentActivity extends BaseActivity {
	Fragment fragment;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_container);
        String action = getIntent().getExtras().getString(SELECTED_ACTION);
        // Shouldn't get here without an action, but just in case, default to news feed
        if (action == null) action = getString(R.string.news_feed);
        fragment = getContentFragmentMap().get(action);
    	FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
    	fragmentTransaction.replace(R.id.standalone_content_container, fragment);
    	fragmentTransaction.commit();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);

	    // Checks the orientation of the screen
	    if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE && newConfig.isLayoutSizeAtLeast(Configuration.SCREENLAYOUT_SIZE_XLARGE)) {
	    	// Going to landscape and on a tablet, pop this view and go back to the main.xml panel layout
	        finish();
	    }
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if ((keyCode == KeyEvent.KEYCODE_BACK)) {
	    	// Clicking back from here means going to the main menu
	    	clearSelectedAction();
	    }
	    return super.onKeyDown(keyCode, event);
	}
}
