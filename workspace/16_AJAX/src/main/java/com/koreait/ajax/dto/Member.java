package com.koreait.ajax.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
	lombok (애너테이션) 사용
		1. @NoArgsConstructor : 디폴트 생성자
		2. @AllArgsConstructor : 필드를 가진 생성자
		3. @Data : Getter, Setter, toString
 */

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Member {

	private long no;
	private String id;
	private String name;
	private String address;
	private String gender;
	
	
}
