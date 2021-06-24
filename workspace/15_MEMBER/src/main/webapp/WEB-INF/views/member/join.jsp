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
			fn_join();
			fn_verify_num();
		}); // 페이지로드이벤트
		
		var authCode;
		function fn_verify_num() { // 인증번호 보내기
			$('#verify_num_btn').click(function(){
				if ($('#email').val() != '') {
					$.ajax({
						url: 'verifyNum.do',
						type: 'get',
						data: 'email=' + $('#email').val(),
						dataType: 'json',
						success: function(resultMap) {
							authCode = resultMap.authCode;
							alert('인증번호를 보냈습니다. 이메일을 확인해주세요!  ' + resultMap.authCode);
							fn_verify();
						},
						error: function(xhr, textStatus, errorThrown) {
							
						}
					})
				} else {
					alert('이메일을 입력하세요!');
				}
					
			});
		}
		function fn_verify() { // 인증번호 확인
			$('#verify_btn').click(function(){
				console.log(authCode);
				if(authCode == $('#user_key').val()){
					alert('인증되었습니다.');
				} else {
					alert('인증에 실패했습니다.');
				}
			})
		}
		
		function fn_join() {
			$('#join_btn').click(function(){
				fn_idCheck();
				
			}); // $('#join_btn').click (end)
		}
		
		function fn_idCheck() {
			if($('#id').val() != '') {
				$.ajax({
					url: 'idCheck.do',
					type: 'get',
					data: 'id=' + $('#id').val(),
					dataType: 'json',
					success: function(res){
						if(res.count == 0) {
						alert('가입 가능한 아이디입니다.');
						} else {
						alert('가입된 아이디입니다.');						
						}
							
					},
					error: function(xhr, textStatus, errorThrown) {
						
					}
				}) // ajax(END)
			} else {
				alert('아이디를 입력하세요!');
			}
				
		}
		
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