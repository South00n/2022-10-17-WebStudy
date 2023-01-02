package com.sist.dao;
/*
PENO    NOT NULL NUMBER        
POSTER  NOT NULL VARCHAR2(260) 
TITLE   NOT NULL VARCHAR2(150) 
TITLE2           VARCHAR2(150) 
KIND             VARCHAR2(60)  
PERIOD  NOT NULL VARCHAR2(100) 
LOC     NOT NULL VARCHAR2(60)  
LOC2             VARCHAR2(100) 
AREA             VARCHAR2(200) 
AREA2            VARCHAR2(200) 
ITEM             CLOB          
HOST             VARCHAR2(450) 
URL              VARCHAR2(200) 
PRICE            CLOB          
TIME             VARCHAR2(400) 
HASHTAG          CLOB          
GOOD             NUMBER        
CONTENT          CLOB 
 */
public class ExhibitionVO {
	private int peno, good;
	private String poster, title, title2, kind, period, loc, loc2, area, area2,
					item, host, url, price, time, hashtag, content;
	public int getPeno() {
		return peno;
	}
	public void setPeno(int peno) {
		this.peno = peno;
	}
	public int getGood() {
		return good;
	}
	public void setGood(int good) {
		this.good = good;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle2() {
		return title2;
	}
	public void setTitle2(String title2) {
		this.title2 = title2;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public String getLoc2() {
		return loc2;
	}
	public void setLoc2(String loc2) {
		this.loc2 = loc2;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getArea2() {
		return area2;
	}
	public void setArea2(String area2) {
		this.area2 = area2;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getHashtag() {
		return hashtag;
	}
	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
