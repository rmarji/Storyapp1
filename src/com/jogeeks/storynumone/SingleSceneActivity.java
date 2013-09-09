package com.jogeeks.storynumone;

import java.io.IOException;
import java.util.StringTokenizer;

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
import android.text.style.ScaleXSpan;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.jogeeks.common.Dialogs;
<<<<<<< HEAD
import com.jogeeks.common.ImageMap;
import com.jogeeks.storynumone.objects.Paragraph;
import com.jogeeks.storynumone.objects.Scene;
import com.jogeeks.storynumone.objects.StoryPlayer;
import com.jogeeks.storynumone.objects.TimeStamp;
=======
import com.jogeeks.storynumone.objects.Paragraph;
import com.jogeeks.storynumone.objects.Scene;
>>>>>>> 3af1ebc64036d3ca50969ad237fac9992ef18b12
import com.jogeeks.storynumone.objects.Word;

public class SingleSceneActivity extends Activity {

	private int SceneType;
	private Dialogs JoGeeksDialogs;
	public static TextView tv2;

	private ImageMap imageMap;

<<<<<<< HEAD
	public StoryPlayer play;
	public Scene scene;
	private Spannable sp;
	
=======
>>>>>>> 3af1ebc64036d3ca50969ad237fac9992ef18b12
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_shot);

		tv2 = (TextView) findViewById(R.id.textView2);
<<<<<<< HEAD
		
		scene = new Scene(this);
		
		tv2.setText(scene.getParagraph().getText());
		tv2.setText(applySpans(tv2));
		
		//must be set here
		scene.setSpannableText(sp);



		play = new StoryPlayer(this, scene);

		try {
			play.play();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	//	applySpans(s,tv2);

=======

		applySpans(s, tv);
>>>>>>> 3af1ebc64036d3ca50969ad237fac9992ef18b12
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

<<<<<<< HEAD
	public Spannable applySpans(TextView TempTV) {
		
		String textviewString = TempTV.getText().toString();
		sp = new SpannableString(textviewString);
		
		String[] words = textviewString.split(" ");
		int counter = 0;
		Word[] wordwords = new Word[100];

		int startIndex, endIndex =0;
=======
	public void applySpans(String s, TextView TempTV) {
		Spannable sp = new SpannableString(s);
		String[] words = s.split(" ");

		int startIndex, endIndex;
>>>>>>> 3af1ebc64036d3ca50969ad237fac9992ef18b12
		for (String word : words) {
			startIndex = textviewString.indexOf(word, endIndex);
			endIndex = startIndex + word.length();
<<<<<<< HEAD
			
			Log.d(word, Integer.toString(startIndex)+", " + Integer.toString(endIndex));
			wordwords[counter] = new Word(counter, word, startIndex, endIndex) ;
			
					sp.setSpan(new IndexedClickableSpan(sp, textviewString, startIndex , endIndex) {
			} ,  startIndex, endIndex , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

			//sp.setSpan(new ForegroundColorSpan(Color.YELLOW), startIndex, endIndex , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
=======

			sp.setSpan(new IndexedClickableSpan(startIndex, endIndex) {
			}, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

			sp.setSpan(new ForegroundColorSpan(Color.YELLOW), startIndex,
					endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
>>>>>>> 3af1ebc64036d3ca50969ad237fac9992ef18b12

		}

		// apply the modifications to the textview we are workin on
		return sp;
}

	private class IndexedClickableSpan extends ClickableSpan {

		int startIndex, endIndex;
<<<<<<< HEAD
		String string;
		Spannable sp;
		public IndexedClickableSpan(Spannable s, String string, int startIndex, int endIndex){
=======

		public IndexedClickableSpan(int startIndex, int endIndex) {
>>>>>>> 3af1ebc64036d3ca50969ad237fac9992ef18b12
			this.startIndex = startIndex;
			this.endIndex = endIndex;
			this.string = string;
			sp = s;

		}

		@Override
		public void onClick(View arg0) {
<<<<<<< HEAD

			String word = string.substring(startIndex, endIndex);

			Log.d(word, Integer.toString(play.getCurrentMilliseconds()));
					
			tv2.setText(changeWordColor(changeWordSize(sp, startIndex, endIndex, 2), startIndex, endIndex, Color.GREEN));
			
		}
=======
			changeWordColor(s, 0, 1);

			sp.setSpan(new ForegroundColorSpan(Color.GREEN), startIndex,
					endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			tv.setText(sp);

		}

>>>>>>> 3af1ebc64036d3ca50969ad237fac9992ef18b12
	}

	public Spannable changeWordColor(Spannable sp, int start, int end, int color) {
		sp.setSpan(new ForegroundColorSpan(color), start, end,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		return sp;
	}
	
	public Spannable changeWordSize(Spannable sp, int start, int end, float prop) {
		sp.setSpan(new ScaleXSpan(prop), start, end,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
<<<<<<< HEAD
		return sp;
=======

		tv2.setText(sp);
>>>>>>> 3af1ebc64036d3ca50969ad237fac9992ef18b12
	}
}
