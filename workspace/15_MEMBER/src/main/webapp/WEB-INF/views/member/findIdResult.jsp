<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="  crossorigin="anonymous"> </script>
	<script>
		$(document).ready(function(){
			
		});//[END]페이지로드이벤트
		
		/*함수*/
		
		
	</script>
</head>
<body>

	<h1>아이디 찾기 결과</h1>
	
	<c:if test="${findUser == null}">
		일치하는 회원 정보가 없습니다. <br><br>
		<a href="findIdPage.do">아이디 찾기</a>	
	</c:if>
	
	<c:if test="${findUser != null}">
		아이디 : ${findUser.id} 				<br>
		가입일 : ${findUser.regdate} 			<br><br>
		<input type="button" value="로그인하러 가기" onclick="location.href='index.do'">
	</c:if>
	

</body>
</html>