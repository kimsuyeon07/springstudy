package com.koreait.ajax.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DuplicateKeyException;

import com.koreait.ajax.dto.Member;
import com.koreait.ajax.dto.Page;

public interface MemberDAO {

	// 인터페이스 방식의 DAO : 따로 Bean처리하지 않아도 된다.
	// Mybaits를 사용하는 방식  >> member.xml과 연결시킨 뒤, 사용!
	
	/* 회원 추가 */
	public int insertMember(Member member) throws DuplicateKeyException ;
	
	/* 회원 목록 반환을 위한 메서드 */
	public int getTotalMemberCount();
	public List<Member> selectMemberList(Page paging);
									  // : 페이지 처리를 위한 (beginRecord, endRecord)
	/* 회원 조회 */
	public Member selectMemberByNo(long no);
	
	/* 회원 수정 */
	public int updateMember(Member member);
	
	/* 회원 삭제 */
	public int deleteMember(long no);
	
	
}
