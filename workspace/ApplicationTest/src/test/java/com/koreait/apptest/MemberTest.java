package com.koreait.apptest;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.koreait.apptest.config.BeanConfiguration;
import com.koreait.apptest.dao.MemberDAO;
import com.koreait.apptest.dto.Member;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={BeanConfiguration.class})

public class MemberTest {

	@Autowired
	private SqlSession sqlSession;
	
	// Junit 테스트는 스프링을 모두 돌리지 않는다.
	// @AutoWired 동작 안한다.
	// Oracle JDBC도 tomcat에 넣어두면 동작하지 않는다.
	// 현재 프로젝트에 포함되어 있어야 한다. (pom.xml 참고)
	
	// Junit 테스트 시 스프링 모든 기능을 활용하기 위해서 
	// spring-test 디펜던시를 추가한다. (스프링 프레임워크와 같은 버전)
	// [https://mvnrepository.com/] : spring-test(search) : Spring TestContext Framework » 5.1.4.RELEASE .ver
	
	// spring-test 디펜던시가 지원하는 <애너테이션>
	// @RunWith >> 스프링과 같이 동작하도록 ( 이 테스트는 스프링과 함께 돌려달라)
	// @ContextConfiguration >> Bean을 여기서 찾아라
	
	//
	
	@Before // 매서드명은 상관없다. 개발자 마음대로
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
	
	
	// 아이디 중복 체크 테스트
	// admin 아이디가 확인이 되면 검사 실패
	// admin 아이디가 없다면 검사 성공
	@Test
	public void 아이디중복체크테스트() {
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		int count = memberDAO.idCheck("admin");
		assertEquals("중복 체크 실패", 1, count);
	}
	
	
	
	
	
	
	
	
	
	
	

}
