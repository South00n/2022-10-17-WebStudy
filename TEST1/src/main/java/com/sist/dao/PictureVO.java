package com.sist.dao;
/*
	PPNO    NOT NULL NUMBER        
	IMAGE   NOT NULL VARCHAR2(260) 
	TITLE   NOT NULL VARCHAR2(150) 
	CONTENT          CLOB           
 */
public class PictureVO {
	private int gpno;
	private String image, title, name, content;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGpno() {
		return gpno;
	}
	public void setGpno(int gpno) {
		this.gpno = gpno;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
