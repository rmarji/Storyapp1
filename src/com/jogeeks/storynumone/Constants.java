package com.jogeeks.storynumone;

import java.util.ArrayList;

import com.jogeeks.common.ImageMap;
import com.jogeeks.storynumone.objects.Paragraph;
import com.jogeeks.storynumone.objects.TimeStamp;

public class Constants {
	public static ArrayList<Paragraph> Paragraphs = new ArrayList<Paragraph>();
	public static ArrayList<ImageMap> Maps = new ArrayList<ImageMap>();
	public static ArrayList<TimeStamp> Tags = new ArrayList<TimeStamp>();

	public static ArrayList<Integer> Resources = new ArrayList<Integer>();

	public Constants(){
		Paragraphs.add(new Paragraph(0, "Mom wanted to go to a big store for dresses and stuff like that, yuck!"));
		Tags.add(new TimeStamp(Paragraphs.get(0)));
		
	Tags.get(0).add(0, 60);

	
	Resources.add(R.raw.test_scene_p1);

	}
}
