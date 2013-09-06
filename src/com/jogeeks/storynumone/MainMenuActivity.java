package com.jogeeks.storynumone;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.jogeeks.common.Dialogs;

public class MainMenuActivity extends Activity {

	private Dialogs JoGeeksDialogs;
	private Button readItToMe;
	private Button readItMyself;
	private Button autoPlay;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		JoGeeksDialogs = new Dialogs(this,getApplicationContext());
		
		//Buttons
		readItToMe = (Button) findViewById(R.id.ReadItToMe);
		readItMyself = (Button) findViewById(R.id.ReadItMyself);
		autoPlay = (Button) findViewById(R.id.AutoPlay);
		
		readItToMe.setOnClickListener(mainMenuClickListner);
		readItMyself.setOnClickListener(mainMenuClickListner);
		autoPlay.setOnClickListener(mainMenuClickListner);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(android.view.MenuItem item) {
		switch (item.getItemId()) {
		case R.id.help:
			JoGeeksDialogs.showLongToast("Simple help screen");
			break;
			
		case R.id.about:
			JoGeeksDialogs.showLongToast("About JoGeeks");
			break;

		case R.id.exit:
			JoGeeksDialogs.getNewAlertDialog("Are you sure?", "Are you sure you want to exit?")
			.setPositiveButton("Yes", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					finish();
				}
			}).setNegativeButton("No", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					arg0.dismiss();
				}
			}).create().show();
			break;		
		}
		return true;
    }

	View.OnClickListener mainMenuClickListner = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.ReadItToMe:
				startActivity(new Intent(MainMenuActivity.this,SingleSceneActivity.class)
				.putExtra("Scene", Constants.READ_IT_TO_ME));
				finish();
				break;

			case R.id.ReadItMyself:
				startActivity(new Intent(MainMenuActivity.this,SingleSceneActivity.class)
				.putExtra("Scene", Constants.READ_IT_MYSELF));
				finish();
				break;
				
			case R.id.AutoPlay:
				startActivity(new Intent(MainMenuActivity.this,SingleSceneActivity.class)
				.putExtra("Scene", Constants.AUTO_PLAY));
				finish();
				break;
			default:
				break;
			}
		}
		
	};
}
