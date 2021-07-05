<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${loginUser.name}님의 마이페이지</title>
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

	<div class="container">
		<h1>${loginUser.name}님, 환영합니다  :)</h1>
		<p>아이디 : <span class="myPage">${loginUser.id}</span></p>
		<p>이름 : <span class="myPage">${loginUser.name}</span></p>
		<p>전화번호 : <span class="myPage">${loginUser.phone}</span></p>
		<p>이메일 : <span class="myPage">${loginUser.email}</span></p>
		<p>주소 : <span class="myPage">${loginUser.address}</span></p>
		<p>가입일 : <span class="myPage">${loginUser.postdate}</span></p>
		<input type="hidden" name="no" id="no" value="${loginUser.no}">
		<input type="button" id="delete_btn" value="회원 탈퇴">
		<input type="button" value="메인으로 돌아가기" onclick="location.href='index.do'">
	</div>

</body>
</html>