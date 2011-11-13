package com.rushdevo.gitdroid.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

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
}
