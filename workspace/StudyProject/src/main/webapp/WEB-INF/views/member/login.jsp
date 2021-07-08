<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login</title>
	<link rel="stylesheet" href="resources/asset/css/study.css">
	<link rel="stylesheet" href="resources/asset/css/login.css">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="  crossorigin="anonymous"> </script>
	<script>
	
		$(document).ready(function(){
			fn_login();
		}); //페이지 로드 이벤트 (종료)
		
		/* 함수 생성 */
		function fn_login() {
			$('#f').submit(function(event){
				if($('#id').val() == '' || $('#pw').val() == '') {
					alert('아이디와 비밀번호 입력은 필수입니다.');
					event.preventDefault();
					$('#id').focus();
					return false;
				}
			}) // (end)$('#f').click
		} //[END]fn_login() 
		
	</script>
</head>
<body>

	<div id="login" class="container con">
	
		<header>
			<h1><a href="index.do" class="en_B_lt">LOGIN</a></h1>
		</header>
		
		<section>
			<form id="f" action="login.do" method="post">
				<input type="text" name="id" id="id" placeholder="ID"  class="en_R">
				<br>
				<input type="password" name="pw" id="pw" placeholder="PassWord"  class="en_R">
				<br>
				<button>로그인</button>
			</form>
		</section>
		
		
		<footer>
			<div class="position_a">
				<a href="findIdPage.do">아이디 찾기</a>
				<span> | </span>
				<a href="findPwPage.do">비밀번호 찾기</a>
				<span> | </span>
				<a href="joinPage.do">회원가입</a>
			</div>
		</footer>
			
	</div>

</body>
</html>