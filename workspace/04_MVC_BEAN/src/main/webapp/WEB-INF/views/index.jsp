<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="com.koreait.mvc02.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<%
		// 생성된 bean들은 application에 저장된다.
		// " application에서 가져와라_ 라는 코드를 짜야한다. "
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
		
		Member m1 = ctx.getBean("member1", Member.class);
		Member m2 = ctx.getBean("member2", Member.class);
		
		// EL 사용 하기 위해서    :  ${m1}
		pageContext.setAttribute("m1", m1);
		pageContext.setAttribute("m2", m2);
		
		/* model.addAttribute("m3", m3); 이 넘어 왔다 */
	
	%>
	<!-- root-context.xml로 생성했다. -->
	<h1>사용자1.</h1>
	<br>
	아이디: ${m1.id} 
	<br>
	전화번호: ${m1.contact.phone}
	<br>
	주소: ${m1.contact.address}
	<br><br><br>
	<h1>사용자2.</h1>
	아이디: ${m2.id} 
	<br>
	전화번호: ${m2.contact.phone}
	<br>
	주소: ${m2.contact.address}
	
	<br><br><br>
	
	<!-- BeanConfiguration.java로 생성했다. -->
	<h1>사용자3.</h1>
	아이디: ${m3.id} 
	<br>
	전화번호: ${m3.contact.phone}
	<br>
	주소: ${m3.contact.address}
	
</body>
</html>