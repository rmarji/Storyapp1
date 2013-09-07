package com.jogeeks.storynumone.objects;

import android.util.Log;

public class TimeStamps{
	private int counter=0;
	public int current=0;
	private int area[];
	private int time[];
	private int size;
	
	public TimeStamps(int s){
		size = s;
		area = new int[size];
		time = new int[size];
	}
	
	public void add(int a, int b){
		area[counter] = a;
		time[counter] = b;
		counter++;
	}
	
	public void add(int a[], int b[]){
		boolean outOfbBound = false;
		int i = 0;
		while(!outOfbBound){
			try{
				add(a[i],b[i]);
				i++;
			}catch(ArrayIndexOutOfBoundsException s){
				
			}
		}
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