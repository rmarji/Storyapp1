package com.jogeeks.storynumone;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.speech.RecognizerIntent;
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
import com.jogeeks.common.ImageMap;
import com.jogeeks.common.Splash;
import com.jogeeks.storynumone.objects.Scene;
import com.jogeeks.storynumone.objects.StoryPlayer;
import com.jogeeks.storynumone.objects.Word;

import com.jogeeks.pagecurl.CurlView;

import com.jogeeks.pagecurl.CurlPage;
import com.jogeeks.storynumone.R;


public class SingleSceneActivity extends Activity {

	private CurlView mCurlView;

	private int SceneType;
	private Dialogs JoGeeksDialogs;
	public static TextView TopSubtitle;
	private TextView BottomSubtitle;
	
	private ImageMap imageMap;
	
	public StoryPlayer play;
	public Scene scene;
	private Spannable sp;
	private Splash SingleScene;
	
	private static final int REQUEST_CODE = 1234;

	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		SingleScene = new Splash(this);
		SingleScene.setFullscreen();
		
		setContentView(R.layout.activity_single_shot);
		
		int index = 0;
		if (getLastNonConfigurationInstance() != null) {
			index = (Integer) getLastNonConfigurationInstance();
		}
		
		mCurlView = (CurlView) findViewById(R.id.curl);
		mCurlView.setPageProvider(new PageProvider());
		mCurlView.setSizeChangedObserver(new SizeChangedObserver());
		mCurlView.setCurrentIndex(index);
		mCurlView.setBackgroundColor(0xFF202830);
		
		mCurlView.setEnableTouchPressure(true);
		
		TopSubtitle = (TextView) findViewById(R.id.topsubtitle);
		BottomSubtitle = (TextView) findViewById(R.id.bottomsubtitle);

        imageMap = (ImageMap)findViewById(R.id.map);
		scene = new Scene(this, imageMap, R.drawable.map);
		scene.setName("intro1");
		
        imageMap.setNewScene(scene);
        
		//imageMap
        TopSubtitle.setText(scene.getParagraph().getText());
        TopSubtitle.setText(applySpans(TopSubtitle));
		
		//must be set here
		scene.setSpannableText(sp);


		applySpans(TopSubtitle);
		//findViewById(R.id.map).setVisibility(4);

		TopSubtitle.setMovementMethod(LinkMovementMethod.getInstance());
		
        imageMap.addOnImageMapClickedHandler(new ImageMap.OnImageMapClickedHandler() {
            @Override
            public void onImageMapClicked(int id) {
            	Log.d("qqqqqqqqqq", "qqqqqqqq");
            	imageMap.showBubble(id);
            	play.playWord(imageMap.getName(id));
            }

            @Override
            public void onBubbleClicked(int id) {
                // react to info bubble for area being tapped
            }
        });
    

		
		play = new StoryPlayer(this, scene);
//
//		try {
//			play.play();
//		} catch (IllegalStateException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		

	

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

	
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
	    if (requestCode == REQUEST_CODE && resultCode == RESULT_OK)
	    {
	        // Populate the wordsList with the String values the recognition engine thought it heard
	        ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
	        Log.d(matches.get(0), "test");
	    }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.single_shot, menu);
		return true;

	}

	public Spannable applySpans(TextView TempTV) {
		
		String textviewString = TempTV.getText().toString();
		sp = new SpannableString(textviewString);
		
		String[] words = textviewString.split(" ");
		int counter = 0;
		Word[] wordwords = new Word[100];

		int startIndex, endIndex =0;
		for (String word : words) {
			startIndex = textviewString.indexOf(word, endIndex);
			endIndex = startIndex + word.length();
			
			Log.d(word, Integer.toString(startIndex)+", " + Integer.toString(endIndex));
			wordwords[counter] = new Word(counter, word, startIndex, endIndex) ;
			
					sp.setSpan(new IndexedClickableSpan(sp, textviewString, startIndex , endIndex) {
			} ,  startIndex, endIndex , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

			//sp.setSpan(new ForegroundColorSpan(Color.YELLOW), startIndex, endIndex , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

		}

		// apply the modifications to the textview we are workin on
		return sp;
}

	private class IndexedClickableSpan extends ClickableSpan {

		int startIndex, endIndex;
		String string;
		Spannable sp;
		public IndexedClickableSpan(Spannable s, String string, int startIndex, int endIndex){
			this.startIndex = startIndex;
			this.endIndex = endIndex;
			this.string = string;
			sp = s;

		}

		@Override
		public void onClick(View arg0) {

			String word = string.substring(startIndex, endIndex);

			Log.d(word, Integer.toString(play.getCurrentMilliseconds()));
			play.playWord(word.toLowerCase());
			TopSubtitle.setText(changeWordColor(changeWordSize(sp, startIndex, endIndex, 2), startIndex, endIndex, Color.GREEN));
			
			changeWordColor(sp, startIndex, endIndex, Color.GREEN);

			sp.setSpan(new ForegroundColorSpan(Color.GREEN), startIndex,
					endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			TopSubtitle.setText(sp);

		}

	}

	public Spannable changeWordColor(Spannable sp, int start, int end, int color) {
		sp.setSpan(new ForegroundColorSpan(color), start, end,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		return sp;
	}
	
	public Spannable changeWordSize(Spannable sp, int start, int end, float prop) {
		sp.setSpan(new ScaleXSpan(prop), start, end,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		return sp;
	}


	@Override
	public void onPause() {
		super.onPause();
		mCurlView.onPause();
	}

	@Override
	public void onResume() {
		super.onResume();
		mCurlView.onResume();
	}

	@Override
	public Object onRetainNonConfigurationInstance() {
		return mCurlView.getCurrentIndex();
	}

	/**
	 * Bitmap provider.
	 */
	private class PageProvider implements CurlView.PageProvider {

		// Bitmap resources.
		private int[] mBitmapIds = {R.drawable.intro1, R.drawable.intro2, R.drawable.intro3, 
				R.drawable.intro4, R.drawable.intro5, R.drawable.intro6, R.drawable.intro7};

		@Override
		public int getPageCount() {
			return 6;
		}

		private Bitmap loadBitmap(int width, int height, int index) {
			Bitmap b = Bitmap.createBitmap(width, height,
					Bitmap.Config.ARGB_8888);
			b.eraseColor(0xFFFFFFFF);
			Canvas c = new Canvas(b);
			Drawable d = getResources().getDrawable(mBitmapIds[index]);

			int margin = 0;
			int border = 0;
	
			Rect r = new Rect(margin, margin, width - margin, height - margin);

			int imageWidth = r.width();
			int imageHeight = imageWidth * d.getIntrinsicHeight();
					
			if (imageHeight > r.height()) {
				imageHeight = r.height();
				imageWidth = imageHeight * d.getIntrinsicWidth();
						
			}

//			r.left += ((r.width() - imageWidth) / 2) - border;
//			r.right = r.left + imageWidth + border + border;
//			r.top += ((r.height() - imageHeight) / 2) - border;
//			r.bottom = r.top + imageHeight + border + border;

//			Paint p = new Paint();
//			p.setColor(0xFFC0C0C0);
//			c.drawRect(r, p);
//			r.left += border;
//			r.right -= border;
//			r.top += border;
//			r.bottom -= border;

			d.setBounds(r);
			d.draw(c);

			return BitmapFactory.decodeResource(getResources(),mBitmapIds[index]);
		}

		@Override
		public void updatePage(CurlPage page, int width, int height, int index) {

			switch (index) {
			// First case is image on front side, solid colored back.
			case 0: {
				Bitmap front = loadBitmap(width, height, 0);
				page.setTexture(front, CurlPage.SIDE_FRONT);
				page.setColor(Color.rgb(255,255,255), CurlPage.SIDE_BACK);
				break;
			}
			// Second case is image on back side, solid colored front.
			case 1: {
				Bitmap back = loadBitmap(width, height, 2);
				page.setTexture(back, CurlPage.SIDE_BACK);
				page.setColor(Color.rgb(255,255,255), CurlPage.SIDE_FRONT);
				break;
			}
			// Third case is images on both sides.
			case 2: {
				Bitmap front = loadBitmap(width, height, 1);
				Bitmap back = loadBitmap(width, height, 3);
				page.setTexture(front, CurlPage.SIDE_FRONT);
				page.setTexture(back, CurlPage.SIDE_BACK);
				break;
			}
			// Fourth case is images on both sides - plus they are blend against
			// separate colors.
			case 3: {
				Bitmap front = loadBitmap(width, height, 2);
				Bitmap back = loadBitmap(width, height, 1);
				page.setTexture(front, CurlPage.SIDE_FRONT);
				page.setTexture(back, CurlPage.SIDE_BACK);
				page.setColor(Color.argb(127, 170, 130, 255),
						CurlPage.SIDE_FRONT);
				page.setColor(Color.rgb(255,255,255), CurlPage.SIDE_BACK);
				break;
			}
			// Fifth case is same image is assigned to front and back. In this
			// scenario only one texture is used and shared for both sides.
			case 4:
				Bitmap front = loadBitmap(width, height, 0);
				page.setTexture(front, CurlPage.SIDE_BOTH);
				page.setColor(Color.argb(127, 255, 255, 255),
						CurlPage.SIDE_BACK);
				break;
			}
		}

	}

	/**
	 * CurlView size changed observer.
	 */
	private class SizeChangedObserver implements CurlView.SizeChangedObserver {
		@Override
		public void onSizeChanged(int w, int h) {
			if (w > h) {
				mCurlView.setViewMode(CurlView.SHOW_TWO_PAGES);
				mCurlView.setMargins(.1f, .05f, .1f, .05f);
			} else {
				mCurlView.setViewMode(CurlView.SHOW_ONE_PAGE);
				mCurlView.setMargins(.1f, .1f, .1f, .1f);
			}
		}
	}
}
