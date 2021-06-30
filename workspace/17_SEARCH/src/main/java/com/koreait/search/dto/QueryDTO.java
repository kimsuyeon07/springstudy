package com.koreait.search.dto;

import lombok.Data;

@Data
public class QueryDTO {
	
	/* 검색에 필요한 파라미터를 저장해두고, 이를 Command에서 사용한다. */
	
	// field
	private String column;
	private String query;

	/* SALARY 검색결과에 팔요한 파라미터 */ 
	private String top;
	private String bottom;
	
	/* 검색결과의 리스트 출력을 위해서 작업한 필드 */
	private int beginRecord;
	private int endRecord;
	
	
	
	
}
