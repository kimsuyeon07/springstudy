package com.koreait.contact03.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.koreait.contact03.command.DeleteContactCommand;
import com.koreait.contact03.command.InsertContactCommand;
import com.koreait.contact03.command.SelectContactListCommand;
import com.koreait.contact03.command.SelectContactViewCommand;
import com.koreait.contact03.command.UpdateContactCommand;

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
		sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/koreait/contact03/dao/*.xml"));
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
	
	
	
	// Command "Bean"으로 만들기
	@Bean
	public SelectContactListCommand selectContactListCommand() {
		return new SelectContactListCommand();
	}
	@Bean
	public InsertContactCommand insertContactCommand() {
		return new InsertContactCommand();
	}
	@Bean
	public SelectContactViewCommand selectContactViewCommand() {
		return new SelectContactViewCommand();
	}
	@Bean
	public UpdateContactCommand updateContactCommand() {
		return new UpdateContactCommand();
	}
	@Bean
	public DeleteContactCommand deleteContactCommand() {
		return new DeleteContactCommand();
	}
	
	
}
