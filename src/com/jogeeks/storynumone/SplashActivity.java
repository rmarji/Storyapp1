package com.jogeeks.storynumone;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import com.jogeeks.common.Splash;
import com.jogeeks.storynumone.R;

public class SplashActivity extends Activity {

	private Splash StoryAppSplash;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		StoryAppSplash = new Splash(this);
		StoryAppSplash.setFullscreen();
		
		setContentView(R.layout.activity_splash);
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}

}
