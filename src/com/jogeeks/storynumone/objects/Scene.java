package com.jogeeks.storynumone.objects;

import android.app.Activity;
import android.text.Spannable;

import com.jogeeks.common.ImageMap;
import com.jogeeks.storynumone.Constants;

public class Scene {

	//Scene types
	public static final int READ_IT_MYSELF = 1;
	public static final int READ_IT_TO_ME = 2;
	public static final int AUTO_PLAY = 3;
	
	private int id;
	private String name;
	
	private Paragraph par;
	private ImageMap map;
	private TimeStamp tags;
	private Spannable spaned;
	
	public Scene(Activity act){
		Constants s = new Constants();
		
		map = new ImageMap(act.getApplicationContext());
		Constants.Maps.add(map);
		
		par = Constants.Paragraphs.get(getId());
		map = Constants.Maps.get(getId());
		tags = Constants.Tags.get(getId());
	}
	
	
	public int getId() {
		return id;
	}
	
	public TimeStamp getTags() {
		return tags;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setSpannableText(Spannable sp){
		spaned = sp;
	}
	public Spannable getSpanableText(){
		return spaned;
	}
	
	public ImageMap getMap(){
		return map;
	}
	
	public Paragraph getParagraph(){
		return par;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}