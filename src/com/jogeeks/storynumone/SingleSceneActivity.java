package com.jogeeks.storynumone;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jogeeks.common.Dialogs;
import com.jogeeks.storynumone.objects.Scene;

public class SingleSceneActivity extends Activity {

	private int SceneType;
	private Dialogs JoGeeksDialogs;
	TextView tv, tv2;

	String s = "Hello world ya man";
	Spannable sp = new SpannableString(s);

	

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_shot);

		tv = (TextView) findViewById(R.id.textView1);
		tv2 = (TextView) findViewById(R.id.textView2);
		tv2.setText(applySpans(s, sp));
		// tv2.set
		findViewById(R.id.map).setVisibility(4);

		tv2.setMovementMethod(LinkMovementMethod.getInstance());

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
