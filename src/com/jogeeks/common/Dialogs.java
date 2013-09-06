package com.jogeeks.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;

public class Dialogs {
	private Context context;
	private Activity activity;
	
	public Dialogs(Activity act){
		activity = act;
	}
	
	public Dialogs(Activity act, Context con){
		activity = act;
		context = con;
	}
	
	public Dialogs(Context con){
		context = con;
	}
	
	public void showLongToast(String msg){
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
	}
	
	public void showShortToast(String msg){
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}
	
	public AlertDialog.Builder getNewAlertDialog(String title, String msg){
		AlertDialog.Builder AlertDialog = new AlertDialog.Builder(
				activity);
		AlertDialog.setTitle(title);
		AlertDialog.setMessage(msg);
		return AlertDialog;
		
	}
}
