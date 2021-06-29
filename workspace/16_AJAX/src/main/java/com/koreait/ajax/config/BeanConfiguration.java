package com.koreait.ajax.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.koreait.ajax.command.DeleteMemberCommand;
import com.koreait.ajax.command.InsertMemberCommand;
import com.koreait.ajax.command.SelectMemberListCommand;
import com.koreait.ajax.command.SelectMemberViewCommand;
import com.koreait.ajax.command.UpdateMemberCommand;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class BeanConfiguration {

	@Bean
	public HikariConfig hikariConfig() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName("oracle.jdbc.OracleDriver");
		hikariConfig.setJdbcUrl("jdbc:oracle:thin:@127.0.0.1:1521:xe");
		hikariConfig.setUsername("spring");
		hikariConfig.setPassword("1111");
		return hikariConfig;
	}
	
	@Bean
	public HikariDataSource hikariDataSource() {
		return new HikariDataSource(hikariConfig());
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(hikariDataSource());
		sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/koreait/ajax/dao/*.xml"));
		/* 
		 * ... ( new PathMatchingResourcePatternResolver()
		 * 			.getResources("classpath:com/koreait/"수정영역"/dao/*.xml"));
		 */
		return sqlSessionFactory.getObject();
	}
	@Bean
	public SqlSessionTemplate sqlSession() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	
	
	/* Command _Bean 생성 */
	@Bean
	public InsertMemberCommand insertMemberCommand() {
		return new InsertMemberCommand();
	}
	@Bean
	public SelectMemberListCommand selectMemberListCommand() {
		return new SelectMemberListCommand();
	}
	@Bean
	public SelectMemberViewCommand selectMemberViewCommand() {
		return new SelectMemberViewCommand();
	}
	@Bean
	public UpdateMemberCommand updateMemberCommand() {
		return new UpdateMemberCommand();
	}
	@Bean
	public DeleteMemberCommand deleteMemberCommand() {
		return new DeleteMemberCommand();
	}
}
