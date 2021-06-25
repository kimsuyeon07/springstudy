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
			fn_leave();
		});
		
		function fn_login() {
			$('#f').submit(function(event){
				if ($('#id').val() == '' || $('#pw').val() == '') {
					alert('아이디와 비밀번호는 필수입니다.');
					event.preventDefault();
					return false;
				}
			})
		}//[END]fn_login()
		function fn_leave() {
			$('#leave_link').click(function(){
				if (confirm('탈퇴하시겠습니까?')) {
					location.href='leave.do';
				}
			})
		}//[END]fn_leave()
		
		
	</script>
	<style>
		#leave_link {color:red;}
		#leave_link:hover {
			text-decoration: underline;
			color: red;
			font-weight: 600;
			cursor: pointer;
		}
	</style>
</head>
<body>

	<h1>홈페이지</h1>
	
	<c:if test="${loginUser == null}">
		<form id="f" action="login.do" method="post">
			<p>아이디</p>
			<input type="text" name="id" id="id">		<br><br>
			
			<p>비밀번호</p>
			<input type="password" name="pw" id="pw">	<br><br>
			
			<button>로그인</button>
		</form>
		<a href="joinPage.do">회원가입</a> &nbsp;&nbsp;&nbsp;
		<a href="findIdPage.do">아이디찾기</a>&nbsp;&nbsp;&nbsp;
		<a href="findPwPage.do">비밀번호찾기</a>&nbsp;&nbsp;&nbsp;
		
	</c:if>
	
	<c:if test="${loginUser != null }">
		<p>회원번호 : ${loginUser.no} </p>
		<p>아이디 : ${loginUser.id} </p>
		<p>비밀번호 : ${loginUser.pw} </p>
		<p>이름 : ${loginUser.name} </p>
		<!-- <script>alert('경고')</script>로 입력한 값도 스크립트가 적용되기 때문에_ "특수믄자"의 기능을 "문자열"타입으로 바꿔주는 작업이 필요하다. -->
		<p>이메일 : ${loginUser.email} </p>
		<p>가입일 : ${loginUser.regdate} </p>
		
		<br><br>
		
		<a href="logout.do">로그아웃</a>	<br>
		<!-- session에 login정보가 들어있기 때문에 따로 넘겨주지 않아도 된다. -->
		<a href="myPage.do">마이페이지</a>	<br>
		<a id="leave_link">회원탈퇴</a>	<br> 
	</c:if>
	
	

</body>
</html>