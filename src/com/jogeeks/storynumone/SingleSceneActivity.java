package com.jogeeks.storynumone;

import com.jogeeks.common.Dialogs;
import com.jogeeks.storynumone.objects.Scene;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SingleSceneActivity extends Activity {

	private int SceneType;
	private Dialogs JoGeeksDialogs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_shot);
		
		JoGeeksDialogs = new Dialogs(getApplicationContext());
		
		SceneType = getIntent().getExtras().getInt("Scene");
		
		switch (SceneType) {
		case Scene.READ_IT_MYSELF:
			JoGeeksDialogs.showLongToast("Read it myself");
			break;

		case Scene.READ_IT_TO_ME:
			JoGeeksDialogs.showLongToast("Read it to me");
			break;

		case Scene.AUTO_PLAY:
			JoGeeksDialogs.showLongToast("Auto Play");
			break;

		default:
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.single_shot, menu);
		return true;
	}

}
