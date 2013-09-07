package com.jogeeks.storynumone.objects;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import com.jogeeks.common.ImageMap;
import com.jogeeks.storynumone.SingleSceneActivity;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

public class StoryPlayer {
	private Context context;
	private Activity activity;
	private ImageMap map;
	
	private int RESOURCE;
	private MediaPlayer player;
	
	private int duration;
	
	private TimeStamps timings;
	
	public StoryPlayer(Context con, Activity act, int file, ImageMap map, TimeStamps timestamps){
		context = con;
		activity = act;
		RESOURCE = file;
		player = MediaPlayer.create(context, RESOURCE);
		duration = player.getDuration();
		timings = timestamps;
	}
	
	public void play() throws IllegalStateException, IOException{
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
								Log.d("dddddddddddd", "ddddddddddddddd");
								Log.d(Integer.toString(getCurrentSeconds()), Integer.toString(timings.getCurrent()));
								if(getCurrentSeconds() == timings.getCurrent()){
									Log.d("ssssssssssssss", "sssssssssssssss");
									//SingleSceneActivity.mImageMap.showBubble(timings.getCurrentArea());
									timings.goToNext();
								}
							}
						});
			   		
			
			   		
	            }
	        }, 1000, 1000);
	}
	
}
