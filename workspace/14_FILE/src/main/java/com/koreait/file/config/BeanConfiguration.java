package com.koreait.file.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.koreait.file.command.DeleteBoardCommand;
import com.koreait.file.command.DownloadCommand;
import com.koreait.file.command.InsertBoardCommand;
import com.koreait.file.command.SelectBoardListCommand;
import com.koreait.file.command.UpdateBoardCommand;

import selectBoardByNo.SelectboardViewCommand;

@Configuration
public class BeanConfiguration {

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
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource());
		sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/koreait/file/dao/*.xml"));
		/* 
		 * ... ( new PathMatchingResourcePatternResolver()
		 * 			.getResources("classpath:com/koreait/contact03/dao/*.xml"));
		 */
		return sqlSessionFactory.getObject();
	}
	@Bean
	public SqlSessionTemplate sqlSession() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("UTF-8");
		multipartResolver.setMaxUploadSize(1024 * 1024 * 10); // 바이트 단위 (10MB)
		return multipartResolver;
	}
	
	
	
	// Command "Bean"으로 만들기
	@Bean
	public SelectBoardListCommand selectBoardListCommand() {
		return new SelectBoardListCommand();
	}
	@Bean
	public InsertBoardCommand insertBoardCommand() {
		return new InsertBoardCommand();
	}
	@Bean
	public DownloadCommand downloadCommand() {
		return new DownloadCommand();
	}
	@Bean
	public SelectboardViewCommand selectBoardViewCommand() {
		return new SelectboardViewCommand();
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
