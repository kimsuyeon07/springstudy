package com.koreait.member.dao;

import com.koreait.member.dto.Member;

public interface MemberDAO {

	public int idCheck(String id);
	public int join(Member member);
	public Member login(Member member);
	public int leave(long no);
	public int updateMember(Member member);
	public int updatePw(Member member);
	public Member findId(String email);
}
