package com.jogeeks.storynumone;

import java.util.StringTokenizer;

import com.jogeeks.common.Dialogs;
import com.jogeeks.common.text;
import com.jogeeks.storynumone.objects.Scene;

import android.os.Bundle;
import android.app.Activity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;
import android.widget.Toast;

public class SingleSceneActivity extends Activity implements OnTouchListener {

	private int SceneType;
	private Dialogs JoGeeksDialogs;
	TextView tv, tv2;

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {

		text txt = new text();
		tv2.setText(Integer.toString(txt.getOffsetForPosition(tv, arg1.getX(),
				arg1.getY())));

		return false;
	}

	String s = "Hello world ya man";

	public Spannable applySpans(String s) {
		Spannable sp = new SpannableString(s);

		StringTokenizer st = new StringTokenizer(s);
		//String tkn =  st.nextToken().toString();
		String [] words = s.split(" ");
		
		for(String word : words)
		{
			int start = s.indexOf(word);
			sp.setSpan(new IndexedClickableSpan(start , start + word.length()), start, start + word.length(),
					Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			
			//tkn =  st.nextToken(" ").toString();
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
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_shot);

		tv = (TextView) findViewById(R.id.textView1);
		tv2 = (TextView) findViewById(R.id.textView2);
		tv2.setText(applySpans(s));
		
		tv.setOnTouchListener(this);
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
