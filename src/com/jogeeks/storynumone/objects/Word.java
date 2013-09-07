package com.jogeeks.storynumone.objects;

import android.util.Log;

public class Word {
	
	int id;
	private String text;
	private int startIndex;
	private int endIndex;
	
	public Word(int id , String text , int startIndex , int endIndex)
	{
		this.id=id;
		this.setText(text);
		this.setStartIndex(startIndex);
		this.setEndIndex(endIndex);
	}
	
	@Override
	public String toString() {
	Log.d("word",this.getText());
		return "";
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

}
