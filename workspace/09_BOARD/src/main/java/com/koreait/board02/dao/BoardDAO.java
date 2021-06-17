package com.koreait.board02.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.koreait.board02.dto.Board;

public class BoardDAO {

	// BoardDAO baordDAO bean은 스프링에 의해서 singleton으로 생성된다.
	
	// field
	@Autowired
	private JdbcTemplate template;
	private String sql;
	
	
	/* 게시글 목록 : list */
	public List<Board> selectBoardList() {
		sql = "SELECT NO, WRITER, TITLE, CONTENT, POSTDATE FROM BOARD";	
		return template.query(sql, new BeanPropertyRowMapper<Board>(Board.class));
	}
	
	/* 2. 게시글 한개 : view */
	public Board selectBoardByNo(long no) {
		sql = "SELECT NO, WRITER, TITLE, CONTENT, POSTDATE FROM BOARD WHERE NO = ?";
		return template.queryForObject(sql, new BeanPropertyRowMapper<Board>(Board.class), no);
	}
	
	/* 3. 게시글 수정 : update */
	public void updateBoard(Board board) {
		sql = "UPDATE BOARD SET TITLE=?, CONTENT=? WHERE NO = ?";
		template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, board.getTitle());
				ps.setString(2, board.getContent());
				ps.setLong(3, board.getNo());
			}
		});
	}
	
	
	/* 4. 게시글 삭제 : delete */
	public void deleteBoard(long no) {
		sql = "DELETE FROM BOARD WHERE NO = ?" ;
		template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setLong(1, no);
			}
		});
	}
	
	/* 5. 게시글 추가 : insert */
	public int insertBoard(Board board) {
		return template.update(new PreparedStatementCreator() {
									// new PreparedStatementSetter()와 결과 동일 << 이방법이 조금 더 낫다
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// 매개변수 con으로 ps를 만들어서 반환
				String sql = "INSERT INTO BOARD VALUES (BOARD_SEQ.NEXTVAL, ?, ?, ?, SYSDATE)";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, board.getWriter());
				ps.setString(2, board.getTitle());
				ps.setString(3, board.getContent());
				return ps;
			}
		});
	}
	
	
	
	
	
	
	
	
	
	
}
