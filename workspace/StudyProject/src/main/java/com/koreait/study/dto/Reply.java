package com.koreait.study.dto;

import java.sql.Date;

public class Reply {

	// field
	private long no;
	private long galleryNo;
	private String id;
	private String content;
	private Date postdate;
	private String ip;
	
	// Getter and Setter
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public long getGalleryNo() {
		return galleryNo;
	}
	public void setGalleryNo(long galleryNo) {
		this.galleryNo = galleryNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	// toString()
	@Override
	public String toString() {
		return "Reply [no=" + no + ", galleryNo=" + galleryNo + ", id=" + id + ", content=" + content + ", postdate="
				+ postdate + ", ip=" + ip + "]";
	}
	
	
	
}
