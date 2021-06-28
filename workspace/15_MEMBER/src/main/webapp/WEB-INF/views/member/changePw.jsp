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
			fn_changePw();
		}) // 페이지 로드 이벤트 (종료)
		
		/* 함수 */
		function fn_changePw() {
			$('#change_pw_btn').click(function(){
				var regPw = /^[0-9]{1,4}$/;
				if(!regPw.test($('#pw').val())) {
					alert('비밀번호는 1자리에서 4자리 사이의 숫자로 이루어져있습니다.');
					$('#pw').focus();
					return false;
				} else if ($('#pw').val() != $('#pw2').val()) {
					alert('비밀번호가 일치하지 않습니다. 다시 확인해주세요!');
					return false;
				} else {
					$('#f').attr('action', 'changePw.do');
					$('#f').submit();
				}
			}); //$('#change_pw_btn').click (END)
		} //[END]fn_changePw()
		
		
		
		
	</script>
	
</head>
<body>

	<h1>비밀번호 변경</h1>
	
	<form id="f" method="post">
		<p>이메일 확인</p>
		<input type="hidden" name="email" value="${email}">
		
		<p>새 비밀번호</p>
		<input type="password" name="pw" id="pw">
		
		<p>비밀번호 확인</p>
		<input type="password" name="pw2" id="pw2">
		
		<input type="button" value="비밀변호 변경하기" id="change_pw_btn">
		<input type="button" value="돌아가기" onclick="location.href='index.do'">
		
	</form>

</body>
</html>