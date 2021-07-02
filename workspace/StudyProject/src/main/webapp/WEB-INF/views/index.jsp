<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Home</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="  crossorigin="anonymous"> </script>
	<script>
	
		$(document).ready(function(){

		}); //페이지 로드 이벤트 (종료)
		
		/* 함수 생성 */
		
		
	</script>
</head>
<body>

	<div class="container">
	
		<header></header>
		
		<section>
			
			<c:if test="${empty loginUser}">
				<a href="loginPage.do">로그인</a>
			</c:if>
			<c:if test="${not empty loginUser}">
				<p> ${loginUser.name}님  환영합니다!  :)</p>
				<a href="logout.do">로그아웃</a>
				<a href="myPage.do">마이페이지</a>
			</c:if>
			
		</section>
		
		<footer></footer>
		
	</div>

</body>
</html>