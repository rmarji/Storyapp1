package com.jogeeks.storynumone;

import java.util.ArrayList;
import java.util.HashSet;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.jogeeks.common.Dialogs;
import com.jogeeks.storynumone.objects.Paragraph;
import com.jogeeks.storynumone.objects.Scene;
import com.jogeeks.storynumone.objects.Word;

public class SingleSceneActivity extends Activity {

	private int SceneType;
	private Dialogs JoGeeksDialogs;
	TextView tv, tv2;

	// TODO: delete later
	private Paragraph par = new Paragraph(0, "hello world from jordan to hello");

	String s = "Hello world ya man";
	Spannable sp = new SpannableString(s);

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_shot);

		tv = (TextView) findViewById(R.id.textView1);
		tv2 = (TextView) findViewById(R.id.textView2);
		
		applySpans(s,tv);

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

	public void applySpans(String s, TextView TempTV) {
		Spannable sp = new SpannableString(s);
		String[] words = s.split(" ");

		int startIndex, endIndex;
		for (String word : words) {
			startIndex = s.indexOf(word);
			endIndex = startIndex + word.length();

			sp.setSpan(new IndexedClickableSpan(startIndex, endIndex) {
			}, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

			sp.setSpan(new ForegroundColorSpan(Color.YELLOW), startIndex,
					endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

		}

		// apply the modifications to the textview we are workin on
		TempTV.setText(sp);
	}

	private class IndexedClickableSpan extends ClickableSpan {

		int startIndex, endIndex;

		public IndexedClickableSpan(int startIndex, int endIndex) {
			this.startIndex = startIndex;
			this.endIndex = endIndex;
		}

		@Override
		public void onClick(View arg0) {
			changeWordColor(s, 0, 1);

		}

	}

	// TODO: custom textview that has the proprites and onclick listners we have
	// their
	public void changeWordColor(Paragraph p, int Wordid) {

		Word wordObj = p.words.get(Wordid);
		int startIndex = wordObj.getStartIndex();
		int endIndex = wordObj.getEndIndex();

		sp.setSpan(new ForegroundColorSpan(Color.GREEN), startIndex, endIndex,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		tv2.setText(sp);
	}

	public void changeWordColor(String s, int start, int end) {
		Spannable sp = new SpannableString(s);

		sp.setSpan(new ForegroundColorSpan(Color.GREEN), start, end,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

		tv2.setText(sp);
	}

}
