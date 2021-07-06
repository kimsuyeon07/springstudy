<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="  crossorigin="anonymous"> </script>
	<script type="text/javascript">
		$(document).ready(function(){
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
		}
		function fn_leave(){
			$('#leave_link').click(function(){
				if (confirm('탈퇴할까요?')){
					location.href = 'leave.do';
				}
			})
		}
	</script>
	<style>
		#leave_link:hover {
			cursor: pointer;
		}
		a {
			text-decoration: none;
			color: black;
		}
	</style>
</head>
<body>
	
	<%-- <c:if test="${loginUser != null}"> --%>
	<c:if test="${not empty loginUser}">
		${loginUser.id} 님 반갑습니다<br><br>
		<a href="logout.do">로그아웃</a><br>
		<a id="leave_link">회원탈퇴</a><br>
	</c:if>
	
	<%-- <c:if test="${loginUser == null}"> --%>
	<c:if test="${empty loginUser}">
		<form id="f" action="login.do" method="post">
			아이디<br>
			<!-- <input type="text" id="id"><br><br> -->
			<input type="text" name="id" id="id"><br><br>
			비밀번호<br>
			<!-- <input type="password" id="pw"><br><br> -->
			<input type="password" name="pw" id="pw"><br><br>
			<button>로그인</button>
		</form>
		<br>
		<a href="joinPage.do">회원가입</a>
	</c:if>
	
</body>
</html>