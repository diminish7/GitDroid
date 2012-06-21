package com.rushdevo.gitdroid.ui.activities;

import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;

import com.rushdevo.gitdroid.R;
import com.rushdevo.gitdroid.listeners.ActionSelectedListener;
import com.rushdevo.gitdroid.listeners.ObjectSelectedListener;
import com.rushdevo.gitdroid.ui.fragments.ActionListFragment;
import com.rushdevo.gitdroid.ui.fragments.BaseFragment;
import com.rushdevo.gitdroid.ui.fragments.GitDroidFragment;

public class MainActivity extends BaseActivity implements ActionSelectedListener, ObjectSelectedListener {
	private ActionListFragment actionListFragment;
	private GitDroidFragment currentContentFragment;
	private int contentDepth = 0;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        actionListFragment = getOrAddActionListFragment();
    	actionListFragment.setActionSelected(this);
    	OnActionSelected(getSharedPrefs().getString(SELECTED_ACTION, null));
    }
    
    @Override
    public Object onRetainCustomNonConfigurationInstance() {
    	// Delegate to the current content fragment
    	if (currentContentFragment == null) return null;
    	else return currentContentFragment.onRetainCustomNonConfigurationInstance();
    }
    
    public ActionListFragment getOrAddActionListFragment() {
    	actionListFragment = (ActionListFragment)getSupportFragmentManager().findFragmentById(R.id.action_list_fragment);
    	if (isSinglePanelLayout()) {
            initializeActionListFragment();
    	}
    	return actionListFragment;
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	stopAnalyticsTracking();
    }
    
    @Override
	public void OnActionSelected(String action) {
    	boolean updatePrefs = (action != null);
    	contentDepth = 0; // Zero out the screen depth counter
    	if (isMultiPanelLayout() && action == null) action = getString(R.string.received_events);
    	if (action != null && action != "") {
    		// If we're just launching the app, add the menu fragment if needed for the backstack
    		currentContentFragment = getContentFragmentMap().get(action);
    		currentContentFragment.setObjectSelectedListener(this);
	    	displayFragment((Fragment)currentContentFragment, isSinglePanelLayout());
	    	setTitleFromAction(action);
    	}
    	if (updatePrefs) {
    		// Update the selected action in preferences
	    	Editor prefEditor = getSharedPrefs().edit();
	    	prefEditor.putString(SELECTED_ACTION, action);
	    	prefEditor.commit();
    	}
	}
    
    @Override
    public void OnObjectSelected(Object object, BaseFragment fragment) {
    	currentContentFragment = (GitDroidFragment)fragment;
    	currentContentFragment.setObjectSelectedListener(this);
    	fragment.setContentObject(object);
    	displayFragment(fragment, true);
    	contentDepth += 1;
    }
    
    @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if ((keyCode == KeyEvent.KEYCODE_BACK)) {
	    	// Clear the cached selected action
	    	if (contentDepth == 0) clearSelectedAction();
	    	if (isMultiPanelLayout())
	    		if (contentDepth == 0 ) {
	    			// We are on a top-level screen, so back is equal to bailing out of the app
	    			getSupportFragmentManager().popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
	    		} else {
	    			// We are on a detail screen, so let the back stack handle this and just decrement the counter
	    			contentDepth -= 1;
	    		}
	    }
	    return super.onKeyDown(keyCode, event);
	}
    
    ////////// GETTERS AND SETTERS /////////////
    /**
     * Determine if the current configuration is set to a multi-panel layout (xlarge screen and landscape)
     * @return true if we are in panel layout
     */
    public Boolean isMultiPanelLayout() {
        return isMultiPanelLayout(getResources().getConfiguration());
    }
    
    /**
     * Determine if the current configuration is set to single panel layout (not xlarge screen or not landscape)
     * @return true if we are in a single panel layout
     */
    public Boolean isSinglePanelLayout() {
    	return isSinglePanelLayout(getResources().getConfiguration());
    }
    
    ////////// HELPERS //////////////
    /**
     * Determine if the configuration is set to a multi-panel layout (xlarge screen and landscape)
     * @param config - The configuration to check
     * @return true if we are in panel layout
     */
    private Boolean isMultiPanelLayout(Configuration config) {
    	return config.orientation == Configuration.ORIENTATION_LANDSCAPE && config.isLayoutSizeAtLeast(Configuration.SCREENLAYOUT_SIZE_XLARGE);
    }
    
    /**
     * Determine if the configuration is set to single panel layout (not xlarge screen or not landscape)
     * @param config - The configuration to check
     * @return true if we are in a single panel layout
     */
    private Boolean isSinglePanelLayout(Configuration config) {
    	return !isMultiPanelLayout(config);
    }
    /**
     * Initializes the main action list fragment
     */
    private void initializeActionListFragment() {
    	actionListFragment = new ActionListFragment();
    	displayFragment(actionListFragment, false);
    }
    
    /**
     * Initializes and displays a fragment
     * @param fragment - The fragment to display
     * @param addToBackStack - True if this fragment transaction should be added to the back stack
     */
    private void displayFragment(Fragment fragment, boolean addToBackStack) {
    	FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
    	fragmentTransaction.replace(R.id.content_containter, fragment);
    	if (addToBackStack) fragmentTransaction.addToBackStack(null);
    	fragmentTransaction.commit();
    }
}