<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>LOGIN</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="  crossorigin="anonymous"> </script>
	<script>
	
		$(function(){
			fn_login();	
		});
		
		function fn_login() {
			$('#f').submit(function(event){
				if ($('#id').val() == '' || $('#pw').val() == '') {
					alert('아이디와 비밀번호는 필수입니다.');
					event.preventDefault();
					return false;
				}
			})
		}
		
		
	</script>
</head>
<body>

	<h1>홈페이지</h1>
	
	<form id="f" action="login.do" method="post">
		<p>아이디</p>
		<input type="text" name="id" id="id">		<br><br>
		
		<p>비밀번호</p>
		<input type="password" name="pw" id="pw">	<br><br>
		
		<button>로그인</button>
	</form>
	
	<a href="joinPage.do">회원가입</a>

</body>
</html>