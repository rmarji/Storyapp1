package com.jogeeks.storynumone;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;

import com.jogeeks.common.ImageMap;
import com.jogeeks.storynumone.objects.Paragraph;
import com.jogeeks.storynumone.objects.Scene;
import com.jogeeks.storynumone.objects.TimeStamp;

public class Constants {
	public static ArrayList<Paragraph> Paragraphs = new ArrayList<Paragraph>();
	public static ArrayList<ImageMap> Maps = new ArrayList<ImageMap>();
	public static ArrayList<TimeStamp> Tags = new ArrayList<TimeStamp>();
	public static ArrayList<Integer> ImageResource = new ArrayList<Integer>();

	
	public static int Story; //Full story, to be used in 
																		// a MediaPlayer
	@SuppressLint("UseSparseArrays")
	public static HashMap<String, Integer> WordsSounds = new HashMap<String, Integer>(); //Words, to be used in a SoundPool

	public Constants(){
		Story = R.raw.test_scene_p1;

		WordsSounds.put("building",R.raw.building);
		WordsSounds.put("dad",R.raw.dad);
		WordsSounds.put("dancer",R.raw.dancer);
		WordsSounds.put("floor",R.raw.floor);
		WordsSounds.put("mom",R.raw.mom);
		WordsSounds.put("once",R.raw.once);
		WordsSounds.put("taken",R.raw.taken);
		WordsSounds.put("to",R.raw.to);
		WordsSounds.put("the",R.raw.the);
		WordsSounds.put("ballet",R.raw.ballet);
		WordsSounds.put("people",R.raw.people);

		//ImageResources
		ImageResource.add(R.drawable.intro1);
		
		//Scene's paragraghs 
		Paragraphs.add(new Paragraph(0, "Once taken to the ballet"));

		
		//Scene's tags
		
		Tags.add(new TimeStamp(Paragraphs.get(0)));
		Tags.get(0).add(0, 070);
		Tags.get(0).add(1, 600);
		Tags.get(0).add(2, 1000);

		
	}
}
