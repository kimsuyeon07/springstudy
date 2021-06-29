package com.koreait.search.util;

import com.koreait.search.dto.Page;

public class PagingUtils {

	// field
	public static int recordPerPage = 5;
		// 현재 페이지에서 보여주고자 하는 회원 정보 수
	public static int pagePerBlock = 3;
		// 이전   1 2 3   다음   (형태로 출력하겠다.)
	
	
	// ajax 사용 (경로가 필요하지 않다)
	public static Page getPage(int page, int totalRecord) {
		
		// 현재 페이지에서 보여주고자 하는 회원 정보 수
		int recordPerPage = 5;
		
		// 현재 페이지에서 보여주는 '첫번째' 순서 회원의 번호 : ROWNUM으로 구한 값으로
		int beginRecord = (page - 1) * recordPerPage + 1;
		// 현재 페이지에서 보여주는 '마지막' 순서 회원의 번호 : ROWNUM으로 구한 값으로
		int endRecord = beginRecord + recordPerPage - 1;
		endRecord = endRecord > totalRecord ? totalRecord : endRecord ;
		
		// 전체 페이지 수 구하기
		int totalPage = (totalRecord / recordPerPage) + (totalRecord % recordPerPage > 0 ? 1 : 0) ;
		// 이전   1 2 3   다음    :  형태로 생성하겠다.
		int pagePerBlock = 3;
		int beginPage = ((page - 1) / pagePerBlock) * pagePerBlock + 1;
		int endPage = beginPage + pagePerBlock - 1;
		endPage = endPage > totalPage ? totalPage : endPage;
				
		Page paging = new Page();
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
	
	
	// mvc 사용 (경로가 필요)
	
	
	
	
	
	
	
}
