<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>

	<h1>view02.jsp</h1>
	
	<c:forEach var="p" items="${peolpe}">
		${p.name} <br>
		${p.age} <br>
		${p.hobbies} <br>
	</c:forEach>
</body>
</html>