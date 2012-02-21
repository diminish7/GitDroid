package com.rushdevo.gitdroid.ui.activities;

import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.rushdevo.gitdroid.R;
import com.rushdevo.gitdroid.listeners.ActionSelected;
import com.rushdevo.gitdroid.ui.fragments.ActionListFragment;

public class MainActivity extends BaseActivity implements ActionSelected {
	private Boolean panelLayout;
	private ActionListFragment actionListFragment;
	private Fragment currentContentFragment;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Configuration config = getResources().getConfiguration();
        // We are in a panel layout iff the orientation is landscape and the screen is xlarge
        panelLayout = config.orientation == Configuration.ORIENTATION_LANDSCAPE && config.isLayoutSizeAtLeast(Configuration.SCREENLAYOUT_SIZE_XLARGE);
        actionListFragment = getOrAddActionListFragment();
    	actionListFragment.setActionSelected(this);
    	OnActionSelected(getSharedPrefs().getString(SELECTED_ACTION, null));
    }
    
    public ActionListFragment getOrAddActionListFragment() {
    	ActionListFragment fragment = (ActionListFragment)getSupportFragmentManager().findFragmentById(R.id.action_list_fragment);
    	if (isSinglePanelLayout() && fragment == null) {
            fragment = new ActionListFragment();
        	FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        	fragmentTransaction.add(R.id.content_containter, fragment);
        	fragmentTransaction.commit();
    	}
    	return fragment;
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	stopAnalyticsTracking();
    }
    
    @Override
	public void OnActionSelected(String action) {
    	boolean updatePrefs = (action != null);
    	if (isPanelLayout() && action == null) action = getString(R.string.received_events);
    	if (action != null && action != "") {
			currentContentFragment = getContentFragmentMap().get(action);
	    	FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
	    	fragmentTransaction.replace(R.id.content_containter, currentContentFragment);
	    	// If this is a single-panel layout, add this to the back stack
	    	if (isSinglePanelLayout()) fragmentTransaction.addToBackStack(null);
	    	fragmentTransaction.commit();
	    	setTitleFromAction(action);
    	}
    	if (updatePrefs) {
    		// Update the selected action in preferences
	    	Editor prefEditor = getSharedPrefs().edit();
	    	prefEditor.putString(SELECTED_ACTION, action);
	    	prefEditor.commit();
    	}
	}
    
    ////////// GETTERS AND SETTERS /////////////
    public Boolean isPanelLayout() {
    	return panelLayout;
    }
    
    public Boolean isSinglePanelLayout() {
    	return !panelLayout;
    }
}