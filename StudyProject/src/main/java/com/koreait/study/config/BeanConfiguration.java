package com.koreait.study.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.koreait.study.command.galleryBoard.GalleryBoardListCommand;
import com.koreait.study.command.galleryBoard.GalleryBoardViewCommand;
import com.koreait.study.command.galleryBoard.InsertGalleryCommand;
import com.koreait.study.command.galleryBoard.UpdateGalleryCommand;
import com.koreait.study.command.member.DeleteMemberCommand;
import com.koreait.study.command.member.EmailAuthCodeCommand;
import com.koreait.study.command.member.FindIdCommand;
import com.koreait.study.command.member.FindPwCommand;
import com.koreait.study.command.member.IdCheckCommand;
import com.koreait.study.command.member.JoinCommand;
import com.koreait.study.command.member.LoginCommand;
import com.koreait.study.command.member.LogoutCommand;
import com.koreait.study.dao.GalleryBoardDAO;
import com.koreait.study.dao.MemberDao;
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
		sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/koreait/study/dao/*.xml"));
		return sqlSessionFactory.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSession() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	
	/* file 추가를 위한 디펜던시 추가 후 Bean 추가 */
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("UTF-8");
		multipartResolver.setMaxUploadSize(1024 * 1024 * 10); // 바이트 단위 (10MB)
		return multipartResolver;
	}
	
	
	/*DAO - Bean생성  */
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	@Bean
	public GalleryBoardDAO galleryBoardDAO() {
		return new GalleryBoardDAO();
	}
	
	
	/* Command - (Member) Bean생성  */
	@Bean
	public IdCheckCommand idCheckCommand() {
		return new IdCheckCommand();
	}
	@Bean
	public EmailAuthCodeCommand emailAuthCodeCommand() {
		return new EmailAuthCodeCommand();
	}
	@Bean
	public JoinCommand joinCommand() {
		return new JoinCommand();
	}
	@Bean
	public LoginCommand loginCommand() {
		return new LoginCommand();
	}
	@Bean
	public LogoutCommand logoutCommand() {
		return new LogoutCommand();
	}
	@Bean
	public FindIdCommand findIdCommand() {
		return new FindIdCommand();
	}
	@Bean
	public FindPwCommand findPwCommand() {
		return new FindPwCommand();
	}
	@Bean
	public DeleteMemberCommand deleteMemberCommand() {
		return new DeleteMemberCommand();
	}
	
	/* Command - (GalleryBoard) Bean생성  */
	@Bean
	public GalleryBoardListCommand galleryBoardListCommand() {
		return new GalleryBoardListCommand();
	}
	@Bean
	public InsertGalleryCommand insertGalleryCommand() {
		return new InsertGalleryCommand();
	}
	@Bean
	public GalleryBoardViewCommand galleryBoardViewCommand() {
		return new GalleryBoardViewCommand();
	}
	@Bean
	public UpdateGalleryCommand updateGalleryCommand() {
		return new UpdateGalleryCommand();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
