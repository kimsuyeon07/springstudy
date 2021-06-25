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
			fn_update();
			fn_presentPwCheck();
			fn_updatePw();
		}) //[END]페이지로드이벤트
		
		/* 함수 작업 */
		function fn_update() {
			$('#update_btn').click(function(){
				if ($('#name').val() == '${loginUser.name}' && $('#email').val() == '${loginUser.email}' ) {
					alert('수정할 내용이 없습니다.');
					return false;
				}
				if (confirm('수정을 진행하시겠습니까?')) {
					$('#f').attr('action', 'updateMember.do');
					$('#f').submit();					
				}
			});//$('#update_btn').click (END)
		}//[END]fn_update()
		
		/* 현재 비밀번호 검사 */
		var presentPwPass = false;
		function fn_presentPwCheck(){
			$('#pw0').keyup(function(){
				// JSON객체
				var obj = {
						"pw": $('#pw0').val()
				};
				$.ajax({
					url: 'presentPwCheck.do',
					type: 'post',
					contentType: 'application/json',
					data: JSON.stringify(obj),
					dataType: 'json',
					success: function(resultMap){
						if (resultMap.isCorrect) {
							presentPwPass = true;
						} else {
							presentPwPass = false;
						}
					},
					error: function(xhr, textStatus, errorThrown) {
						
					}
				});//ajax(END)
			})//$('#pw0').keyup(END)
		}//[END]fn_presentPwCheck()
		
		function fn_updatePw() {
			$('#pw_btn').click(function(){
				if (!presentPwPass) {
					alert('현재 비밀번호가 틀립니다!');
					$('#pw0').focus();
					return false;
				} else if ($('#pw').val() == '') {
					alert('비밀번호 입력을 확인하세요!');
					$('#pw').focus();
					return false;
				} else if ($('#pw').val() != $('#pw2').val()) {
					alert('비밀변호 확인이 일치하지 않습니다!');
					return false;
				} else {
					$('#f').attr('action', 'updatePw.do');
					$('#f').submit();
				}
			});//$('#pw_btn').click (END)
		}//[END]fn_updatePw()
		
	</script>
</head>
<body>

	<h1>마이 페이지</h1>
	
	<form id="f" method="post">
		<p>회원번호</p>
		<p>${loginUser.no}</p>
		
		<br>
		
		<p>아이디</p>
		<p>${loginUser.id}</p>
		
		<br>
		
		<p>현재 비밀번호</p>
		<input type="password" name="pw0" id="pw0">	<br>
		<p>새 비밀번호</p>
		<input type="password" name="pw" id="pw">	<br>
		<p>비밀번호 확인</p>
		<input type="password" name="pw2" id="pw2">
		<input type="button" value="비밀번호변경하기" id="pw_btn">
		
		<br>
		
		<p>이름</p>
		<input type="text" name="name" id="name" value="${loginUser.name}">
		
		<br>
		
		<p>이메일</p>
		<input type="text" name="email" id="email" value="${loginUser.email}">
		
		<br>
		
		<p>가입일</p>
		<p>${loginUser.regdate}</p>
		
		<input type="hidden" name="no" value="${loginUser.no}" >
		
		<br><br>
		
		<input type="button" value="개인정보수정" id="update_btn">
		<input type="button" value="돌아가기" onclick="location.href='index.do'" >
	</form>
	
	
</body>
</html>