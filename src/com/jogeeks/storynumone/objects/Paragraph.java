package com.jogeeks.storynumone.objects;

import java.util.ArrayList;

import android.text.Spannable;
import android.util.Log;
import android.widget.TextView;

public class Paragraph {

	int id;
	String text;
	public ArrayList<Word> words = new ArrayList<Word>();
	public Spannable sp;
	
	public Paragraph(int id, String text) {
		this.id = id;
		this.text = text;

		String[] wordsArray = text.split(" ");

		int startIndex, endIndex = 0, wID = 0;
		for (String word : wordsArray) {
			// the endindex paramter solves the problem of repeated words.
			startIndex = text.indexOf(word, endIndex);
			endIndex = startIndex + word.length();

			words.add(new Word(wID, word, startIndex, endIndex));

			Log.d("word", word + " Wid: " + wID + " start: " + startIndex
					+ " end: " + endIndex);
			for (Word wordss : words) {
			//	Log.d("wordlist", wordss.getText());
			}

			wID++;
		}

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;

	}

	TextView tv;

}
