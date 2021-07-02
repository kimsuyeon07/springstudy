<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>아이디 찾기</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="  crossorigin="anonymous"> </script>
	<script>
		$(document).ready(function(){
			fn_findLeft();
			fn_findRight();
		}) //페이지 로드 이벤트 (종료)
		
		/* 함수 생성 */
		
		function fn_findLeft(){
			$('#f1').submit(function(event){
				if ($('#name').val() == '' || $('#phone').val() == '') {
					alert('이름과 전화번호를 입력해주세요.');
					event.preventDefault();
					return false;
				}
			});
		} /* [END]fn_findLeft() */
		
		
		var emailCheck = false;
		function fn_findRight() {
			fn_email();
			$('#f2').submit(function(){
				if (!emailCheck) {
					alert('이메일을 인증하세요.');
					return false;
				}
			});
		} /* [END]fn_findRgiht() */
		// 이메일 인증
		function fn_email() {
			// 인증번호 이메일로 보내기
			$('#email_authCode_btn').click(function(){
				
				if($('#email').val() == '' || $('#site').val() == '') {
					alert('이메일을 입력한 뒤, 인증번호 받기를 눌러주세요.');
					$('#email').focus();
					return false;
				} 
				
				$.ajax({
					url: 'authCode.do',
					type: 'get',
					data: 'email=' + $('#email').val() + '&site=' + $('#site').val(),
					dataType: 'json',
					success: function(resultMap) {
						if (resultMap.authCode != null) {
							alert('인증번호를 보냈습니다. 이메일을 확인해주세요.');
							fn_emailCheck(resultMap.authCode);
						} else {
							alert('이메일 오류!');
							$('#email').focus();
						}
					}
				}) // (end) $.ajax
				
			}); // (end) $('#email_certification').click
		} /* [END]function fn_email() */
		// ↕(연결)
		var authCode  = null;
		function fn_emailCheck(authCode) {
			/* 인증번호 확인 */
			$('#email_authCode_check_btn').click(function(){
				authCode  = authCode;
				if ($('#email_check').val() != '') {
					if ( $('#email_check').val() == authCode ) {
						// 인증 완료 
						emailCheck = true;
						alert('이메일 인증이 완료되었습니다.');
						$('#email_message')
						.text('이메일 인증 확인')
						.addClass('green');
					} else {
						// 인증 오류
						emailCheck = false;
						alert('이메일 인증에 실패했습니다.');
						$('#email_message')
						.text('이메일 인증 코드가 일치하지 않습니다.')
						.removeClass('green');
						$('#email_check').focus();
					} 
				}
			}); // (end) $('#email_authCode_check_btn').click
		} /*[END]fn_emailCheck()*/
		
		
	</script>
</head>
<body>

	<div class="container">
	
		<header>
			<h1>Find ID</h1>
		</header>
		
		<section>
			<div id="left">
				<h3>이름 + 전화번호로 찾기</h3>
				<form id="f1" action="findId.do" method="post">
					<input type="text" name="name" id="name" placeholder="Name">
					<br>
					<input type="text" name="phone" id="phone" placeholder="Phone (010-0000-0000) ">
					<br>
					<button>아이디 찾기</button>
				</form>
			</div>
			
			<div id="right">
				<h3>이메일로 찾기</h3>
				<form id="f2" action="findId.do" method="post">
					<input type="text" name="email" id="email" placeholder="이메일 입력">
					<span> @ </span>
					<select name="site" id="site">
						<option value="">:::선택:::</option>
						<option value="@naver.com">naver.com</option>
						<option value="@gamil.com">gamil.com</option>
						<option value="@daum.net">daum.net</option>
						<option value="@nate.com">nate.com</option>
					</select> 
					<input type="button" value="인증번호 받기" id="email_authCode_btn">
					<br>
					<input type="text" name="email_check" id="email_check" placeholder="인증번호 입력">
					<input type="button" value="인증하기" id="email_authCode_check_btn">
					<button>아이디 찾기</button>
				</form>
			</div>
		</section>
		
		<hr>
		
		<footer>
			<div class="back_page">
				<a href="index.do">로그인화면으로 돌아가기</a>
			</div>
		</footer>
			
	</div>

	

</body>
</html>