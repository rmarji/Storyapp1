package com.jogeeks.storynumone;

import java.util.StringTokenizer;

import com.jogeeks.common.Dialogs;
import com.jogeeks.common.text;
import com.jogeeks.storynumone.objects.Scene;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;
import android.widget.Toast;

public class SingleSceneActivity extends Activity {

	private int SceneType;
	private Dialogs JoGeeksDialogs;
	TextView tv, tv2;

	String s = "Hello world ya man";
	Spannable sp = new SpannableString(s);

	public Spannable applySpans(String s, Spannable sp) {
		String[] words = s.split(" ");

		for (String word : words) {
			int start = s.indexOf(word);
			sp.setSpan(new IndexedClickableSpan(start, start + word.length()),
					start, start + word.length(),
					Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			sp.setSpan(new ForegroundColorSpan(Color.YELLOW), start, start
					+ word.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

		}
		return sp;
	}

	private final class IndexedClickableSpan extends ClickableSpan {

		int startIndex, endIndex;

		public IndexedClickableSpan(int startIndex, int endIndex) {
			this.startIndex = startIndex;
			this.endIndex = endIndex;
		}

		@Override
		public void onClick(View widget) {
			String word = s.substring(startIndex, endIndex);
			Toast.makeText(getApplicationContext(), "You clicked on " + word,
					Toast.LENGTH_SHORT).show();
			// s = Html.fromHtml(s.replace(word, "<font color=\"red\">" + word+
			// "</font>")).toString();
			// tv.setText(s);
			sp.setSpan(new ForegroundColorSpan(Color.GREEN), startIndex,endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			tv2.setText(sp);

		}
	}

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
