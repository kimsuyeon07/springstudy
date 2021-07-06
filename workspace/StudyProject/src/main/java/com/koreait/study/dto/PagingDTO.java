package com.koreait.study.dto;

public class PagingDTO {

	// field
	private int page;
	private int totalRecord;
	private int recordPerPage;
	private int beginRecord;
	private int endRecord;
	private int totalPage;
	private int pagePerBlock;
	private int beginPage;
	private int endPage;
	
	// Getter and Setter
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getRecordPerPage() {
		return recordPerPage;
	}
	public void setRecordPerPage(int recordPerPage) {
		this.recordPerPage = recordPerPage;
	}
	public int getBeginRecord() {
		return beginRecord;
	}
	public void setBeginRecord(int beginRecord) {
		this.beginRecord = beginRecord;
	}
	public int getEndRecord() {
		return endRecord;
	}
	public void setEndRecord(int endRecord) {
		this.endRecord = endRecord;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPagePerBlock() {
		return pagePerBlock;
	}
	public void setPagePerBlock(int pagePerBlock) {
		this.pagePerBlock = pagePerBlock;
	}
	public int getBeginPage() {
		return beginPage;
	}
	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	
	@Override
	public String toString() {
		return "PagingDTO [page=" + page + ", totalRecord=" + totalRecord + ", recordPerPage=" + recordPerPage
				+ ", beginRecord=" + beginRecord + ", endRecord=" + endRecord + ", totalPage=" + totalPage
				+ ", pagePerBlock=" + pagePerBlock + ", beginPage=" + beginPage + ", endPage=" + endPage + "]";
	}
	
	
	
	
}
