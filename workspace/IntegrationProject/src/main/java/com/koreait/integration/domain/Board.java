package com.koreait.integration.domain;

import java.sql.Date;

public class Board {

	// field
	private long no;
	private String writer;
	private String title;
	private String content;
	private Date postdate;
	
	
	// constructor
	public Board() {};
	public Board(long no, String writer, String title, String content, Date postdate) {
		super();
		this.no = no;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.postdate = postdate;
	}

	
	// Getter and Setter
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
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
	public Date getPostdate() {
		return postdate;
	}
	public void setPostdate(Date postdate) {
		this.postdate = postdate;
	}
	
	
	
	
}
