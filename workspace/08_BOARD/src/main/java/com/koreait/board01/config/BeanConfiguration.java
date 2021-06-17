package com.koreait.board01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.koreait.board01.command.BoardListCommand;
import com.koreait.board01.command.DeleteBoardCommand;
import com.koreait.board01.command.InsertBoardCommand;
import com.koreait.board01.command.SelectBoardByNoCommand;
import com.koreait.board01.command.UpdateBoardCommand;

@Configuration
public class BeanConfiguration {

	@Bean
	public BoardListCommand boardListCommand() {
		return new BoardListCommand();
	}
	
	@Bean
	public SelectBoardByNoCommand selectBoardByNoCommand() {
		return new SelectBoardByNoCommand();
	}
	
	@Bean
	public InsertBoardCommand insertBoardCommand() {
		return new InsertBoardCommand();
	}
	
	@Bean
	public UpdateBoardCommand updateBoardCommand() {
		return new UpdateBoardCommand();
	}
	
	@Bean
	public DeleteBoardCommand deleteBoardCommand() {
		return new DeleteBoardCommand();
	}
	
}
