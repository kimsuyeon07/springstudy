package com.koreait.ajax.dao;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;

import com.koreait.ajax.dto.Member;

public interface MemberDAO {

	// 인터페이스 방식의 DAO : 따로 Bean처리하지 않아도 된다.
	// Mybaits를 사용하는 방식  >> member.xml과 연결시킨 뒤, 사용!
	
	/* 회원 추가 */
	public int insertMember(Member member) throws SQLIntegrityConstraintViolationException ;
	
	/* 회원 목록 반환을 위한 메서드 */
	public int getTotalMemberCount();
	public List<Member> selectMemberList(Map<String, Integer> map);
	// 									 : 페이지 처리를 위한 (beginRecord, endRecord)
	
	/*  */
	
	
}
