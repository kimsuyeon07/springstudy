package com.koreait.board02.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.koreait.board02.command.DeleteBoardCommand;
import com.koreait.board02.command.InsertBoardCommand;
import com.koreait.board02.command.SelectBoardListCommand;
import com.koreait.board02.command.SelectBoardViewCommand;
import com.koreait.board02.command.UpdateBoardCommand;
import com.koreait.board02.dao.BoardDAO;

@Configuration // CGLIB_Maven
public class BeanConfiguration {

	// DataSource, JdbcTemplate
	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:xe");
		dataSource.setUsername("spring");
		dataSource.setPassword("1111");
		return dataSource;
	}
	@Bean
	public JdbcTemplate template() {
		JdbcTemplate template = new JdbcTemplate();
		template.setDataSource(dataSource());
		return template;
	}
	
	// DAO
	@Bean
	public BoardDAO boardDAO() {
		return new BoardDAO(); 
	}
	
	// Command
	@Bean
	public SelectBoardListCommand listCommand() {
		return new SelectBoardListCommand();
	}
	@Bean
	public SelectBoardViewCommand viewCommand() {
		return new SelectBoardViewCommand();
	}
	@Bean 
	public UpdateBoardCommand updateCommand() {
		return new UpdateBoardCommand();
	}
	@Bean
	public DeleteBoardCommand delectCommand() {
		return new DeleteBoardCommand();
	}
	@Bean
	public InsertBoardCommand insertCommand() {
		return new InsertBoardCommand();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
