<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="  crossorigin="anonymous"> </script>
	<script>
		$(document).ready(function(){
			fn_verify_num();
		})// 페이지 로드 이벤트 (종료)
		
		/* 함수 */
		function fn_verify_num(){
			// 이메일의 인증번호 받아오기
			$('#verify_num_btn').click(function(){
				if ($('#email').val() == '') {
					alert('이메일은 반드시 입력해야 합니다.');
					$('#email').focus();
					return false;
				}
				$.ajax({
					url: 'verifyNum.do',
					type: 'get',
					data: 'email=' + $('#email').val(),
					dataType: 'json',
					success: function(resultMap) {
						alert('인증번호가 발송되었습니다.');
						fn_verify(resultMap.authCode);
					}, 
					error: function(xhr, textStatus, errorThrown) {
						
					}
				}) //$.ajax({}) (END)
			}) //$('#verify_num_btn').click (END)
		} //[END]vfn_verify_num()
		
		function fn_verify(authCode) {
			// 인증 확인
			$('#verify_btn').click(function(){
				if (authCode == $('#user_key').val()) {
					alert('인증되었습니다. 비밀번호 변경 페이지로 이동합니다.');
					$('#f').attr('action', 'changePwPage.do');
					$('#f').submit();
				} else {
					alert('인증이 실패했습니다.');
					history.back();
				}
			}) //$('#verify_btn').click (END)
		} //[END]fn_verify() 
		
		
	</script>
	
</head>
<body>

	<h1>비밀번호 찾기</h1>
	
	<form id="f">
		<p>가입 당시 이메일</p>
		<input type="text" name="email" id="email">	<br><br>
		<input type="button" value="인증번호받기" id="verify_num_btn"> <br>
		<input type="text" name="user_key" id="user_key">
		<input type="button" value="인증하기" id="verify_btn"> <br>
	</form>
	
	
	
</body>
</html>