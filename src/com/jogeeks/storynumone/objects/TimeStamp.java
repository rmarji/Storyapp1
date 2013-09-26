package com.jogeeks.storynumone.objects;


public class TimeStamp{
	public int current=0;
	public int counter=0;
	
	private Paragraph text;
	private int words[];
	private int time[];
	
	public TimeStamp(Paragraph t){
		text = t;
		words = new int[text.words.size()];
		time = new int[text.words.size()];
	}
	public String getOriginalText(){
		return text.getText();
	}
	public void add(int index, int t){
		words[counter] = index;
		time[counter]= t;
		counter++;
	}

	public int getCurrent(){
		return time[current];
	}
	
	public void goToFirst(){
		current = 0;
	}
	
	public String getCurrentText(){
		return text.words.get(current).getText(); 	
	}
	
	public Word getCurrentWord(){
		return text.words.get(words[current]);
	}
	
	public void goToNext(){
			current++;
	}
}