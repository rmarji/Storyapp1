package com.jogeeks.storynumone.objects;

public class Scene {

<<<<<<< HEAD
	Paragraph par = new Paragraph();
=======
>>>>>>> 95ae758745dd7e70d9038b91c5c4319b5969bba4

	//Scene types
	public static final int READ_IT_MYSELF = 1;
	public static final int READ_IT_TO_ME = 2;
	public static final int AUTO_PLAY = 3;
	
	private int id;
	private String name;
	private Paragraph par = new Paragraph();
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Paragraph getParagraph() {
		return par;
	}
	public void setParagraph(Paragraph par) {
		this.par = par;
	}
	
	
	
<<<<<<< HEAD
=======

>>>>>>> 95ae758745dd7e70d9038b91c5c4319b5969bba4
}
