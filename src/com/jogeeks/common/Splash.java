package com.jogeeks.common;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.Window;
import android.view.WindowManager;

public class Splash {

	private Activity splashActivity;
	
	public Splash(Activity splash){
		splashActivity = splash;
	}
	
	public void setFullscreen(){
		splashActivity.requestWindowFeature(Window.FEATURE_NO_TITLE);
		splashActivity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}
	
	public void setTimeOut(int ms, Class dest){
		
	}
	
	
	private class TimeOut extends AsyncTask<Parms, Void, Intent> {

        @Override
        protected Intent doInBackground(Parms... params) {
                  try {
                      Thread.sleep(1000);
                  } catch (InterruptedException e) {
                      // TODO Auto-generated catch block
                      e.printStackTrace();
                  }
				return null;
        }      

        @Override
        protected void onPostExecute(Intent result) {

        }

  }   
	
}