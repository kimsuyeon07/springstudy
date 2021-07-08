<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${loginUser.name}님의 마이페이지</title>
	<link rel="stylesheet" href="resources/asset/css/study.css">
	<link rel="stylesheet" href="resources/asset/css/myPage.css">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="  crossorigin="anonymous"> </script>
	<script>
		$(document).ready(function(){
			fn_deleteMember();
		}) // 페이지 로드 이벤트
		
		/* 함수 생성 */
		function fn_deleteMember() {
			$('#delete_btn').click(function(){
				if(confirm('회원 탈퇴를 진행하시겟습니까?')) {
				location.href='deleteMember.do?no=' + $('#no').val();
				}
			}); // (end) $('#delete_btn').click
		} /* [END]fn_deleteMember() */ 
					
		
	</script>
</head>
<body>

	<div id="myPage" class="container">
		<header>
			<h1 class="B">${loginUser.name}님, 환영합니다  :)</h1>
		</header>
		<section>
			<p class="L">아이디 : <span class="en_B_lt myPage">${loginUser.id}</span></p>
			<p class="L">이름 : <span class="M myPage kor">${loginUser.name}</span></p>
			<p class="L">전화번호 : <span class="en_B_lt myPage">${loginUser.phone}</span></p>
			<p class="L">이메일 : <span class="en_B_lt myPage">${loginUser.email}</span></p>
			<p class="L">주소 : <span class="M myPage kor">${loginUser.address}</span></p>
			<p class="L">가입일 : <span class="en_B_lt myPage">${loginUser.postdate}</span></p>
			<input type="hidden" name="no" id="no" value="${loginUser.no}">
		</section>
		<footer>
			<input type="button" id="delete_btn" value="회원 탈퇴">
			<input type="button" value="메인으로 돌아가기" onclick="location.href='index.do'">
		</footer>
	</div>

</body>
</html>