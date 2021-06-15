<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<body>
		
	<% request.setCharacterEncoding("UTF-8"); %>
	<h1>Quiz_view03.jsp</h1>
	
	제목 :	${board.title}		<br>
	조회수 : 	${board.hit}		<br>
	작성일 : 	<fmt:formatDate value="${board.data}" pattern="yyyy-MM-dd" />				<br>
	
</body>
</html>