<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<body>

	<div class="container">
		<h1>아이디 찾기</h1>
		<c:if test="${empty email}">
			<h3>${name}님의 아이디는</h3>
			<c:forEach var="id" items="${findId}">
				<h1>${findId.id} &nbsp; 입니다.</h1>
			</c:forEach>
		</c:if>
		<c:if test="${not empty email}">
			<h3>${email}님의 아이디는</h3>
			<c:forEach var="id" items="${findId}">
				<h2>${findId.id}</h2>
			</c:forEach>
			<p>가 있습니다.</p>
		</c:if>
	</div>

</body>
</html>