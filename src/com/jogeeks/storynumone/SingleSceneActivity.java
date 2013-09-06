package com.jogeeks.storynumone;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import com.jogeeks.common.ImageMap;
import com.jogeeks.storynumone.R;
import com.jogeeks.storynumone.objects.StoryPlayer;
import com.jogeeks.storynumone.objects.TimeStamps;
import com.jogeeks.common.Dialogs;
import com.jogeeks.storynumone.objects.Scene;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.MediaController;

public class SingleSceneActivity extends Activity {

	private int SceneType;
	private Dialogs JoGeeksDialogs;
	public static ImageMap mImageMap;
	private boolean stopReading = true;
	
	public static int counter = 0;
	int[] timeStamps = new int[] { R.id.area1, R.id.area2, R.id.area3, R.id.area4, R.id.area5, 
			R.id.area6, R.id.area7, R.id.area8, R.id.area10, R.id.area11};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_shot);
		
		mImageMap = (ImageMap)findViewById(R.id.map);
        
		JoGeeksDialogs = new Dialogs(getApplicationContext());
		
		SceneType = getIntent().getExtras().getInt("Scene");
		
		TimeStamps ss = new TimeStamps();
		ss.add(R.id.area1, 5);
		ss.add(R.id.area2, 7);
		ss.add(R.id.area3, 9);
		ss.add(R.id.area4, 11);
		
		StoryPlayer s = new StoryPlayer(getApplicationContext(), this, R.raw.aa,mImageMap,ss);
		try {
			s.play();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.d(Float.toString(s.getSeconds()), Double.toString(s.getMinutes()));
		
		switch (SceneType) {
<<<<<<< HEAD
		case 1:
			stopReading = false;
			JoGeeksDialogs.showLongToast("Read it to me");
			
			 
			    
//new TimeOut().execute();
=======
		case Scene.READ_IT_MYSELF:
			JoGeeksDialogs.showLongToast("Read it myself");
>>>>>>> 060e73f17d8b24fbf34ef5ba0109d713491b051c
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
		
        
        
        // add a click handler to react when areas are tapped
        mImageMap.addOnImageMapClickedHandler(new ImageMap.OnImageMapClickedHandler() {
			@Override
			public void onImageMapClicked(int id) {
				// when the area is tapped, show the name in a 
				// text bubble
				mImageMap.showBubble(id);
			}

			@Override
			public void onBubbleClicked(int id) {
				// react to info bubble for area being tapped
			}
		});
        
        
//        AnimationSet animSet = new AnimationSet(false);
//
//        ScaleAnimation zoom = new ScaleAnimation(20,5, 20, 5);
//        animSet.addAnimation(zoom);
//        animSet.setRepeatCount(0); 
//        animSet.setDuration(5000); 
//        animSet.setFillAfter(true); 
//        animSet.setInterpolator(new AccelerateDecelerateInterpolator()); 
//        
//        mImageMap.setAnimation(animSet);
//
//        animSet.start();
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.single_shot, menu);

		return true;
	}
	
	
	private class TimeOut extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
			runOnUiThread(new Runnable() {  
			    public void run() {
					 

			    }
			});
				return null;
        }      
  }   
	
	
}
