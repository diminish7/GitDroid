package com.rushdevo.gitdroid.activity;

import android.os.Bundle;
import com.rushdevo.gitdroid.R;

public class MainActivity extends BaseActivity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        tryToSetToken();
    }
}