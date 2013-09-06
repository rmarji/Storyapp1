package com.jogeeks.storynumone.objects;

import android.util.Log;

public class TimeStamps{
	private int counter=0;
	public int current=0;
	private int area[] = new int[100];
	private int time[] = new int[100];
	
	public void add(int a, int b){
		area[counter] = a;
		time[counter] = b;
		counter++;
	}
	
	public int getCurrent(){
		return time[current];
	}
	
	public void goToFirst(){
		current = 0;
	}
	
	public int getCurrentArea(){
		Log.d(Integer.toString(counter), Integer.toString(current));
		return area[current]; 	
	}
	
	public void goToNext(){
		if(current<counter){
			current++;
		}else{
			//stay where you are
		}
	}
}