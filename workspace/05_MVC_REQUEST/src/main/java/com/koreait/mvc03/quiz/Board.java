package com.koreait.mvc03.quiz;

import java.util.Date;

public class Board {

	// field
	private String title;
	private int hit;
	private Date data;
	
	
	
	public Board() {}
	public Board(String title, int hit, Date data) {
		super();
		this.title = title;
		this.hit = hit;
		this.data = data;
	}
	
	
	// Getter and Setter
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	
	
}
