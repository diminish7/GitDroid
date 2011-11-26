package com.rushdevo.gitdroid.activities;

import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.rushdevo.gitdroid.R;
import com.rushdevo.gitdroid.fragments.ActionListFragment;
import com.rushdevo.gitdroid.listeners.ActionSelected;

public class MainActivity extends BaseActivity implements ActionSelected {
	private Boolean panelLayout;
	private ActionListFragment actionListFragment;
	private Fragment currentContentFragment;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        panelLayout = (findViewById(R.id.content_containter) != null);
        actionListFragment = (ActionListFragment)getSupportFragmentManager().findFragmentById(R.id.action_list_fragment);
    	actionListFragment.setActionSelected(this);
    	OnActionSelected(getSharedPrefs().getString(SELECTED_ACTION, null));
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	stopAnalyticsTracking();
    }
    
    @Override
	public void OnActionSelected(String action) {
    	boolean updatePrefs = (action != null);
    	if (panelLayout && action == null) action = getString(R.string.news_feed);
    	if (action != null && action != "") {
	    	if (panelLayout) {
	    		// Multi-panel layout, just change the selected fragment
				currentContentFragment = getContentFragmentMap().get(action);
		    	FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		    	fragmentTransaction.replace(R.id.content_containter, currentContentFragment);
		    	fragmentTransaction.commit();
	    	} else {
	    		// Single-panel layout, start the content activity
	    		Intent intent = new Intent(this, ContentActivity.class);
	    		intent.putExtra(SELECTED_ACTION, action);
	    		startActivity(intent);
	    	}
    	}
    	if (updatePrefs) {
    		// Update the selected action in preferences
	    	Editor prefEditor = getSharedPrefs().edit();
	    	prefEditor.putString(SELECTED_ACTION, action);
	    	prefEditor.commit();
    	}
	}
}