package com.koreait.contact02.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.koreait.contact02.dto.Contact;

public class ContactDAO {
	
	// con에 필요한 내용을(JdbcTemplate)
	// @Autowired로 꺼내서 사용한다.
	@Autowired
	private JdbcTemplate template;
	private String sql; // sql은 필요하다.
	
	/* 연락처 목록 : list */
	public List<Contact> selectContactList() {
		sql = "SELECT NO, NAME, TEL, ADDR, EMAIL, NOTE FROM CONTACT" ;
		return template.query(sql, new BeanPropertyRowMapper<Contact>(Contact.class));
	}
	
	/* 연락처 조회 : view */
	public Contact selectContactView(long no) {
		sql = "SELECT NO, NAME, TEL, ADDR, EMAIL, NOTE FROM CONTACT WHERE NO = ?" ;
		return template.queryForObject(sql, new BeanPropertyRowMapper<>(Contact.class), no);
	}
	
	
	/* 연락처 수정 : update */
	public int updateContact(Contact contact) {
		sql = "UPDATE CONTACT SET NAME=?, TEL=?, ADDR=?, EMAIL=?, NOTE=? WHERE NO = ?";
		return template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// ps 작업
				ps.setString(1, contact.getName());
				ps.setString(2, contact.getTel());
				ps.setString(3, contact.getAddr());
				ps.setString(4, contact.getEmail());
				ps.setString(5, contact.getNote());
				ps.setLong(6, contact.getNo());
			}
		});
		
		
	}
	
	/* 연락처 삭제 : delete */
	public int deleteContact(long no) {
		sql = "DELETE FROM CONTACT WHERE NO = ?" ;
		return template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setLong(1, no);
			}
		});
	}
	
	/* 연락처 추가 : insert */
	public int insertContact(Contact contact) {
		sql = "INSERT INTO CONTACT VALUES (CONTACT_SEQ.NEXTVAL, ?,?,?,?,?)";
		return template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, contact.getName());
				ps.setString(2, contact.getTel());
				ps.setString(3, contact.getAddr());
				ps.setString(4, contact.getEmail());
				ps.setString(5, contact.getNote());
			}
		});
	}
	
	
	
	
	
	
	
	
	
	
}
