package com.jogeeks.common;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.Window;
import android.view.WindowManager;

public class Splash {

	private Activity splashActivity;
	private int time;
	private Class destinationActivity;
	
	public Splash(Activity splash){
		splashActivity = splash;
	}
	
	public void setFullscreen(){
		splashActivity.requestWindowFeature(Window.FEATURE_NO_TITLE);
		splashActivity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}
	
	public void setTimeOut(int ms){
		time = ms;
	}
	
	public void setDestinationActivity(Class whereTo){
		destinationActivity = whereTo;
	}
	
	public void start(){
		new TimeOut().execute();
	}
	
	public void start(Class whereTo, int timeout){
		time = timeout;
		destinationActivity = whereTo;
		new TimeOut().execute();
	}
	
	
	private class TimeOut extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
                  try {
                      Thread.sleep(time);
                  } catch (InterruptedException e) {
                      // TODO Auto-generated catch block
                      e.printStackTrace();
                  }finally{
                	  splashActivity.startActivity(new Intent(splashActivity, destinationActivity));
                	  splashActivity.finish();
                  }
				return null;
        }      
  }   
	
}