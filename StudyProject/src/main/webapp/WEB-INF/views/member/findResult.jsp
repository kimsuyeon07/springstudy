<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="resources/asset/css/study.css">
</head>
<body>

	<div id="findResult" class="container">
		<h1 class="B title">아이디 찾기</h1>
		<c:if test="${empty findId and empty findPw}">
			<h3>아이디를 찾을 수 없습니다.</h3>
			<a href="findIdPage.do" class="find_id">아이디 찾기</a>
		</c:if>
		<c:if test="${not empty findId}">
			<c:if test="${empty email}">
				<h3>${name}님의 아이디는</h3>
				<c:forEach var="findId" items="${findId}">
					<h2 class="en_B_lt">${findId.id}</h2>
				</c:forEach>
				<p class="L">가 있습니다.</p>
			</c:if>
			<c:if test="${not empty email}">
				<h3>${email}님의 아이디는</h3>
				<c:forEach var="findId" items="${findId}">
					<h2 class="en_B_lt">${findId.id}</h2>
				</c:forEach>
				<p class="T">가 있습니다.</p>
			</c:if>
		</c:if>
		<c:if test="${not empty findPw}">
			<h3>${email}님의 비밀번호는</h3>
			<h2>${findPw} &nbsp;입니다.</h2>
		</c:if>
		
		<a href="loginPage.do">로그인 하러 가기</a>
		
	</div>

</body>
</html>