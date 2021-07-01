package com.koreait.integration.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.koreait.integration.repository.BoardRepository;
import com.koreait.integration.service.BoardServiceImpl;
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
	
	@Bean(destroyMethod="close")
	public HikariDataSource hikariDataSource() {
		return new HikariDataSource(hikariConfig());
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(hikariDataSource());
		sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/koreait/integration/repository/*.xml"));
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
	
	
	/* Repository(DAO) _Bean 생성 : singleton으로 생성된다. */
	@Bean
	public BoardRepository repository() {
		return new BoardRepository();
	}
	
	/* BoardService (Interface => Command): 인터페이스클래스를 상속받는 Command를 Bean처리 */
	/* BoardServiceImpl(Command) _Bean 생성 : 인터페이스클래스를 상속받는 Command(Impl)파일이다. */
	@Bean
	public BoardServiceImpl boardService() {
		return new BoardServiceImpl();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
