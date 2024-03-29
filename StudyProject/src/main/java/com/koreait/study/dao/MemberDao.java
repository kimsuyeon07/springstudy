package com.koreait.study.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.study.dto.FindQueryDTO;
import com.koreait.study.dto.Member;

@Repository
public class MemberDao {

	@Autowired
	private SqlSession sqlSession;
	
	/* method */
	public int idCheck(String id) {
		return sqlSession.selectOne("com.koreait.study.dao.member.idCheck", id);
	}
	public int join(Member member) {
		return sqlSession.insert("com.koreait.study.dao.member.join", member);
	}
	public Member login(Member member) {
		return sqlSession.selectOne("com.koreait.study.dao.member.login", member);
	}
	public List<Member> findId(FindQueryDTO findQueryDTO) {
		return sqlSession.selectList("com.koreait.study.dao.member.findId", findQueryDTO);
	}
	public Member findPw(FindQueryDTO findQueryDTO) {
		return sqlSession.selectOne("com.koreait.study.dao.member.findPw", findQueryDTO);
	}
	public int deleteMember(long no) {
		return sqlSession.update("com.koreait.study.dao.member.deleteMember", no);
	}
	
}
