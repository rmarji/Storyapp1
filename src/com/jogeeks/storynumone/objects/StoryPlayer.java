package com.jogeeks.storynumone.objects;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;

import com.jogeeks.common.ImageMap;
import com.jogeeks.storynumone.Constants;
import com.jogeeks.storynumone.SingleSceneActivity;

public class StoryPlayer {
	private Scene scene;
	private Context context;
	private Activity activity;
	private ImageMap map;
	private Spannable spaned;
	
	private int RESOURCE;
	private MediaPlayer player;
	
	private int duration;
	
	private TimeStamp timings;
	
	public StoryPlayer(Activity act, Scene sce){
		scene = sce;
		activity = act;
		RESOURCE = Constants.Resources.get(scene.getId());
		
		player = MediaPlayer.create(act.getApplicationContext(), Constants.Resources.get(scene.getId()));
		duration = player.getDuration();
		timings = scene.getTags();
		spaned = scene.getSpanableText();
	}
	
	public void play() throws IllegalStateException, IOException{
		player.start();
		
		start();
	}
	
	public void playAt(int time){
		time = time * 1000;
		player.seekTo(time);
		player.start();
		start();
	}
	
	public int getCurrentMilliseconds(){
		return player.getCurrentPosition();
	} 

	public int getCurrentSeconds(){
		return player.getCurrentPosition()/1000;
	} 
	
	
	public int getMilliseconds(){
		return duration;
	}

	public float getSeconds(){
		return duration/1000;
	}
	
	public double getMinutes(){
		double d = duration/1000;
		
		d = d/60;
		double integerPart = (long) d;
		double fractionPart = d - integerPart;
		
		return (integerPart)+((fractionPart*60)/100);
	}
	
	private void start(){
		Timer timer = new Timer();
	    timer.scheduleAtFixedRate(new TimerTask()
	        {
	            public void run()
	            {
			   			activity.runOnUiThread(new Runnable() {
							public void run() {
								//Log.d(Integer.toString(getCurrentSeconds()), Integer.toString(timings.getCurrent()));
								if(getCurrentSeconds() == timings.getCurrent()){
									//Log.d("Yay",timings.getCurrentWord().getText().toString());
									spaned.setSpan(new ForegroundColorSpan(Color.GREEN),
											timings.getCurrentWord().getStartIndex(), timings.getCurrentWord().getEndIndex(),
											Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
									SingleSceneActivity.tv2.setText(spaned);
									timings.goToNext();
								}
							}
						});
	            }
	        }, 1000, 1000);
	}
	
}
