package com.koreait.search.util;

import com.koreait.search.dto.PageDTO;

public class PagingUtils {

	// field
	/* 메서드 밖에서도 field를 사용할 수 있도록 작업 */
	// 현재 페이지에서 보여주고자 하는 회원 정보 수
	public static int recordPerPage = 10;
	public static int beginRecord;
	public static int endRecord;
	// 이전   1 2 3 4 5   다음   (형태로 출력하겠다.)
	public static int pagePerBlock = 5;
	private static int totalPage;
	private static int beginPage;
	private static int endPage;
	
	
	// ajax 사용 (경로가 필요하지 않다)
	public static PageDTO getPage(int page, int totalRecord) {
		
		// 현재 페이지에서 보여주는 '첫번째' 순서 회원의 번호 : ROWNUM으로 구한 값으로
		beginRecord = (page - 1) * recordPerPage + 1;
		// 현재 페이지에서 보여주는 '마지막' 순서 회원의 번호 : ROWNUM으로 구한 값으로
		endRecord = (beginRecord + recordPerPage - 1) ;
		endRecord = endRecord > totalRecord ? totalRecord : endRecord ;
		
		// 전체 페이지 수 구하기
		totalPage = (totalRecord / recordPerPage) + (totalRecord % recordPerPage > 0 ? 1 : 0) ;
		// 이전   1 2 3 4 5   다음    :  형태로 생성하겠다.
		beginPage = ((page - 1) / pagePerBlock) * pagePerBlock + 1;
		endPage = (beginPage + pagePerBlock - 1);
		endPage = endPage > totalPage ? totalPage : endPage;
				
		PageDTO paging = new PageDTO();
		paging.setPage(page);
		paging.setTotalRecord(totalRecord);
		paging.setRecordPerPage(recordPerPage);
		paging.setBeginRecord(beginRecord);
		paging.setEndRecord(endRecord);
		paging.setTotalPage(totalPage);
		paging.setPagePerBlock(pagePerBlock);
		paging.setBeginPage(beginPage);
		paging.setEndPage(endPage);
		
		return paging;
		
	}
	
	
	// mvc 사용 (경로가 필요 → String path )  ::  "search.do" , "searchAll.do"로 들어올 것이다.  
	// ↓
	// <<  1 2 3 4 5  >> 반환
	public static String getPaging(String path, int page) {
		
		StringBuilder sb = new StringBuilder();
		
		/* 이전("◀") */
		if (beginPage <= pagePerBlock) {
			sb.append("◀&nbsp;");
		} else {
			if(path.indexOf("?") > 0) {
				sb.append("<a href=\"" + path + "&page=" + (beginPage - 1) +"\">◀</a>&nbsp;");
			} else {
				sb.append("<a href=\"" + path + "?page=" + (beginPage - 1) +"\">◀</a>&nbsp;");
			}
		}
				
		/* 1 2 3 4 5 */
		for(int p = beginPage; p <= endPage; p++) {
			if (p == page) {
				sb.append(p + "&nbsp;");
			} else {
				if(path.indexOf("?") > 0) {
					sb.append("<a href=\""+ path + "&page=" + p + "\">" + p + "</a>&nbsp;");
				} else {
					sb.append("<a href=\""+ path + "?page=" + p + "\">" + p + "</a>&nbsp;");
				}
			}
		}
					
		/* 다음("▶") */
		if(endPage == totalPage) {
			sb.append("▶");
		} else {
			if(path.indexOf("?") > 0 ) {
				sb.append("<a href=\"" + path + "&page=" + (endPage + 1) +"\">▶</a>");
			} else {
				sb.append("<a href=\"" + path + "?page=" + (endPage + 1) +"\">▶</a>");
			}
		}
				
			
		
		/* ----------------- */
		return sb.toString();
		/* ----------------- */
		
	}
	
	
	
	
	
	
	
}
