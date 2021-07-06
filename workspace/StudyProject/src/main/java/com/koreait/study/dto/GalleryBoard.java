package com.koreait.study.dto;

import java.sql.Date;

public class GalleryBoard {

	// field
	private long no;
	private String id;
	private String title;
	private String content;
	private Date posdate;
	private Date lastdate;
	private String ip;
	private int hit;
	private String imagename;

	
	
	// Getter and Setter
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public Date getPosdate() {
		return posdate;
	}
	public void setPosdate(Date posdate) {
		this.posdate = posdate;
	}
	public Date getLastdate() {
		return lastdate;
	}
	public void setLastdate(Date lastdate) {
		this.lastdate = lastdate;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getImagename() {
		return imagename;
	}
	public void setImagename(String imagename) {
		this.imagename = imagename;
	}
	
	
	@Override
	public String toString() {
		return "GalleryBoard [no=" + no + ", id=" + id + ", title=" + title + ", content=" + content + ", posdate="
				+ posdate + ", lastdate=" + lastdate + ", ip=" + ip + ", hit=" + hit + ", imagename=" + imagename + "]";
	}
	
	
	
	
	
	
	
	
}
