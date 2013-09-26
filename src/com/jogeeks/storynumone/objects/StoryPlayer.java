package com.jogeeks.storynumone.objects;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;

import com.jogeeks.storynumone.Constants;
import com.jogeeks.storynumone.SingleSceneActivity;

public class StoryPlayer {
	private Scene scene;
	private Activity activity;
	private Spannable spaned;
	
	private int Stroy;
	private MediaPlayer player;
    private SoundPool wordsPool;
	private int duration;
	
	private TimeStamp timings;
	
	public StoryPlayer(Activity act, Scene sce){
		scene = sce;
		activity = act;
		Stroy = Constants.Story;
		wordsPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
		player = MediaPlayer.create(act.getApplicationContext(), Stroy);
		duration = player.getDuration();
		timings = scene.getTags();
		spaned = scene.getSpanableText();
	}
	
	public void playWord(String name){

	       final int loadedWord = wordsPool.load(activity.getApplicationContext(), Constants.WordsSounds.get(name), 1);

	        wordsPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
				
				@Override
				public void onLoadComplete(SoundPool arg0, int arg1, int arg2) {
				       wordsPool.play(loadedWord, 1, 1, 0, 0, 1f);
				}
			});
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
								Log.d(Integer.toString(getCurrentMilliseconds()), Integer.toString(timings.getCurrent()));
								if(getCurrentMilliseconds() >= timings.getCurrent()){
									Log.d("Yay",timings.getCurrentWord().getText().toString());
									spaned.setSpan(new ForegroundColorSpan(Color.GREEN),
											timings.getCurrentWord().getStartIndex(), timings.getCurrentWord().getEndIndex(),
											Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
									SingleSceneActivity.TopSubtitle.setText(spaned);
									timings.goToNext();
								}
							}
						});
	            }
	        }, 1, 100);
	}
	
}
