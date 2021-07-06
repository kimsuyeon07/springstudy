package com.koreait.apptest.dao;

import com.koreait.apptest.dto.Member;

public interface MemberDAO {
	public Member login(Member member);
	public int idCheck(String id);
	public int join(Member member);
	public int leaveHome(long no);
}
