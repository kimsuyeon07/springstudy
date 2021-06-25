<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>JOIN</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="  crossorigin="anonymous"> </script>
	<script>
		$(document).ready(function(){
			fn_verify_num();
			fn_idCheck();
			fn_pwCheck();
			fn_pwCheck2();
			fn_join();
		}); // 페이지로드이벤트
		
		function fn_verify_num() { // 인증번호 보내기
			$('#verify_num_btn').click(function(){
				if ($('#email').val() == '') {
					alert('이메일을 입력하세요!');
					return false;
				}
				$.ajax({
					url: 'verifyNum.do',
					type: 'get',
					data: 'email=' + $('#email').val(),
					dataType: 'json',
					success: function(resultMap) {
						alert('인증번호를 보냈습니다. 이메일을 확인해주세요!  ' + resultMap.authCode);
						fn_verify(resultMap.authCode);
					},
					error: function(xhr, textStatus, errorThrown) {
					}
				}) // ajax (END)	
			});
		}//[END]fn_verify_num()	
		
		/* 이메일 인증번호 검증 함수 */
		var authPass = false; // 인증번호 통과여부
		function fn_verify(authCode) { 
			$('#verify_btn').click(function(){
				if(authCode == $('#user_key').val()){
					alert('인증되었습니다.');
					authPass = true;
				} else {
					alert('인증에 실패했습니다.');
					authPass = false;
				}
			})
		}//[END]fn_verify(authCode)
		
		/* 아이디 중복체크 함수 */
		var idPass = false;
		function fn_idCheck() {
			$('#id').blur(function(){
				if($('#id').val() == '') {
					alert('아이디는 필수입니다.');
					return false;
				}
				// 정규식 검사
				var regID = /^[a-z]{1,5}$/;
				if (!regID.test($('#id').val())) {
					alert('아이디의 조건: ');
					return false;
				}
				$.ajax({
					url: 'idCheck.do',
					type: 'get',
					data: 'id=' + $('#id').val(),
					dataType: 'json',
					success: function(res){
						if(res.count == 0) {
						alert('가입 가능한 아이디입니다.');
						idPass = true;
						} else {
						alert('이미 사용 중인 아이디입니다.');	
						idPass = false;
						}	
					},
					error: function(xhr, textStatus, errorThrown) {
					}
				}) // ajax(END)
			});//$('#id').blur(END)
		}//[END]fn_idCheck() 
		
		/* 비밀번호 검증 함수 */
		var pwPass = false;
		function fn_pwCheck() {
			$('#pw').blur(function(){
				if($('#pw').val() == '') {
					alert('비밀번호는 필수입니다.');
					return false;
				}
				// 정규식 검사
				var regPW = /^[0-9]{4}$/;
				if (regPW.test($('#pw').val())) {
					alert('사용 가능한 비밀번호입니다.');
					pwPass = true;
				} else {
					alert('비밀번호의 조건');
					pwPass = false;
				}
			});//$('#pw').blur(END)
		}//[END]fn_pwCheck()
		
		/* 비밀번호 입력 확인 함수 */
		var pwPass2 = false;
		function fn_pwCheck2() {
			$('#pw2').blur(function(){
				if($('#pw2').val() == '') {
					alert('비밀번호 확인은 필수입니다.');
					return false;
				}
				if ($('#pw').val() == $('#pw2').val()) {
					alert('비밀번호 확인 완료');
					pwPass2 = true;
				} else {
					alert('비밀번호가 일치하지 않습니다.');
					pwPass2 = false;
				}
			});//$('#pw2').blur(END)
		}//[END]fn_pwCheck2()
		
		/* 회원가입 함수 */
		function fn_join() {
			$('#join_btn').click(function(){
				if(!idPass) {
					alert('아이디를 확인하세요!');
					return false;
				} else if (!pwPass || !pwPass2) {
					alert('비밀번호를 확인하세요!');
					return false;
				} else if (!authPass) {
					alert('이메일 인증을 받아야 합니다!');
					return false;
				} else {
					$('#f').attr('action', 'join.do');
					$('#f').submit();
				}
			});//$('#join_btn').click(END)
		}//[END]fn_join() 
		
		
		
	</script>
</head>
<body>

	<h1>회원가입</h1>
	
	<form id="f" method="post">
		<p>아이디</p>
		<input type="text" name="id" id="id">		<br><br>
		
		<p>비밀번호</p>
		<input type="password" name="pw" id="pw">	<br><br>

		<p>비밀번호 확인</p>
		<input type="password" name="pw2" id="pw2">	<br><br>
		
		<p>이름</p>
		<input type="text" name="name" id="name">	<br><br>

		<p>이메일</p>
		<input type="text" name="email" id="email">	<br><br>
		<input type="button" value="인증번호받기" id="verify_num_btn"> <br>
		<input type="text" name="user_key" id="user_key">
		<input type="button" value="인증하기" id="verify_btn"> <br>
		
		<input type="button" value="가입하기" id="join_btn">
	</form>

</body>
</html>