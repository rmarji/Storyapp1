package com.jogeeks.storynumone;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.jogeeks.common.Splash;

public class SplashActivity extends Activity {

	private Splash StoryAppSplash;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		StoryAppSplash = new Splash(this);
		StoryAppSplash.setFullscreen();
		
		setContentView(R.layout.activity_splash);
		
		//parms are the destination activity and the time out in milliseconds
		StoryAppSplash.start(MainMenuActivity.class,2000);
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}

}
