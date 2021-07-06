package com.koreait.apptest;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.koreait.apptest.dao.MemberDAO;
import com.koreait.apptest.dto.Member;

public class MemberTest {

	@Autowired
	private SqlSession sqlSession;
	
	@Test // 매서드명은 상관없다. 개발자 마음대로
	public void joinTest() {

		Member member = new Member();
		member.setId("test");
		member.setPw("1111");
		member.setName("테스트");
		member.setEmail("test@web.com");
		
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		int joinCount = memberDAO.join(member);
		
		assertEquals("가입 실패", 1, joinCount);
		// 1  ==  joinCount  >>>  검사 통과
		// 1  !=  joinCount  >>>  검사 실패 (가입 실패) 
		
		
	}

}
