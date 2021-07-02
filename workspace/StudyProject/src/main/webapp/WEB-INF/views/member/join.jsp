<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Join</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="  crossorigin="anonymous"> </script>
	<script>
		$(document).ready(function(){
			fn_id();
			fn_pw();
			fn_phoneCheck();
			fn_email();
			fn_join();
		}) // 페이지 로드 이벤트 (종료)
		
		/* 함수 생성 */
		var idCheck = false; 	// 아이디 중복체크 값:확인
		var pwCheck = false; 	// 비밀번호확인 값:확인
		var emailCheck = false; // 이메일인증 값:확인
		
		// 회원가입 클릭 ------------
		function fn_join() {
			$('#join_btn').click(function(event){
				if ( !idCheck || !pwCheck || !emailCheck ) {
					alert('아이디, 비밀번호, 이메일 인증은 필수입니다.');
					event.preventDefault();
					return false;
				}
				$('#f').attr('action', 'join.do');
				$('#f').submit();
			}) // (end) $('#join_btn').click
		} /* [END]fn_join() */
		
		
		
		// 아이디 정규식 및 중복체크 ------
		function fn_id() {
			$('#id').blur(function(){
				
				// 정규식_아이디 (영문과 숫자만 사용할 수 있는_ 5~10자리)
				var regID = /^[a-z][a-z0-9]{4,9}$/;
				
				// 정규식 확인
				if(regID.test( $('#id').val() )) {
					//정규식에 맞는 아이디를 만들었다.
					$('#id_message')
					.text('아이디 중복을 확인하세요.')
					.addClass('green');
					
					/* 중복체크 버튼이 눌러지는 시점. */ 
					fn_idCheck();
					/* -------------------- */ 
					
				} else {
					//정규식에 맞지 않는 아이디를 만들었다.
					$('#id_message')
					.text('아이디 조건 : 영문으로 시작, 영문과 숫자의 조합(5~10자리)');
				}
				
			}); //(end) $('#id').blur
		} /*[END]fn_id()*/ 
		// ↕(연결)
		function fn_idCheck() {
			$('#id_check_btn').click(function(){
				$.ajax({
					url: 'idCheck.do',
					type: 'post',
					data: 'id=' + $('#id').val(),
					dataType: 'json',
					success: function(resultMap) {
						if (resultMap.result) {
							idCheck = true;
							$('#id_message')
							.text('사용 가능한 아이디입니다.')
							.addClass('green');
						} else {
							idCheck = false;
							$('#id_message')
							.text('사용 중인 아이디입니다.')
							.removeClass('green');
							$('#id').focus();
						}
						// console.log(idCheck);
					}
				});//(end) $.ajax
			}); // (end)$('#id_check_btn').click
		} /*[END]fn_idCheck()*/
		
		
		// 비밀번호 정규식 및 확인 ------
		function fn_pw() {
			$('#pw').blur(function(){
				
				// 정규식_비밀번호 (대/소영문,숫자,~!@#?만 사용할 수 있는_영문과숫자는 무조건 포함하고 있는_ 8~16자리)
				var regPW = /(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9~!@#?]{8,16}$/;
				
				// 정규식 확인
				if(!regPW.test( $('#pw').val() )) {
					//정규식에 맞지 않는 비밀번호를 만들었다.
					$('#pw_message')
					.text('비밀번호 조건 : 대문자와 소문자의 영문과 숫자, 특수문자(~,!,@,#,?)의 조합. 영문과 숫자를 반드시 포함(8~16자리)')
					.removeClass('green');
					return false;
				} else {
					//정규식에 맞는 비밀번호를 만들었다.
					$('#pw_message')
					.text('사용할 수 있는 비밀번호입니다.')
					.addClass('green');
				}  /* -------------------- */ 
				
				/* 비밀번호 이중 체크 시점. */ 
				fn_pwCheck();
				/* -------------------- */
				
			}); //(end) $('#id').blur
		} /*[END]fn_pw()*/ 
		// ↕(연결)
		function fn_pwCheck() {
			/* 비밀번호 이중 확인 */
			$('#pw2').blur(function(){
				if ($('#pw2').val() != '') {
					if ( $('#pw').val() == $('#pw2').val() ) {
						// 이중확인 완료 
						pwCheck = true;
						$('#pw2_message')
						.text('비밀번호가 일치합니다.')
						.addClass('green');
					} else {
						// 이중확인 오류
						pwCheck = false;
						$('#pw2_message')
						.text('비밀번호가 일치하지 않습니다.')
						.removeClass('green');
						$('#pw').focus();
					} 
				}
			}); // (end) $('#pw2').blur
		} /*[END]fn_pwCheck()*/
		
		
		// 전화번호 정규식 확인 -------
		function fn_phoneCheck() {
			$('#phone').blur(function(){
				
				var regPHONE = /^010-[0-9]{3,4}-[0-9]{4}$/;
				if(!regPHONE.test($('#phone').val())) {
					// 전화번호 확인 (오류) : 초기화
					$('#phone').val('');
					$('#phone_message')
					.text('전화번호 표기방법: 010-0000-0000');
				}
			}); //(end) $('#phone').blur
		} /* [END]fn_phoneCheck() */
		
		
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
	
	<style>
		.message{color:red; font-size:12px;}
		.green{color:green;}
	</style>
	
</head>
<body>

	<div class="container">
	
		<header>
			<h1>JOIN</h1>
		</header>
		
		<section>
			<form id="f" method="post">
				
				<label for="id">아이디</label><br>
				<input type="text" name="id" id="id" placeholder="ID">
				<input type="button" value="중복확인" id="id_check_btn">
				<br>
				<span id="id_message" class="message"></span>
				
				<br>
				
				<label for="pw">비밀번호</label><br>
				<input type="password" name="pw" id="pw" placeholder="PassWord">
				<br>
				<span id="pw_message" class="message"></span>
				<br>
				<label for="pw2">비밀번호 확인</label><br>
				<input type="password" name="pw2" id="pw2" placeholder="PassWord">
				<br>
				<span id="pw2_message" class="message"></span>
					
				<br>
							
				<label for="name">이름</label><br>
				<input type="text" name="name" id="name" placeholder="Name">
				<br>
							
				<br>
					
				<label for="phone">전화번호</label><br>
				<input type="text" name="phone" id="phone" placeholder=" - 를 붙여서 입력하세요.">
				<br>
				<span id="phone_message" class="message"></span>
				
				<br>
				
				<label for="address">주소</label><br>
				<input type="text" name="address" id="address" placeholder="Address">
				<br>
				
				<br>
				
				<label for="email">이메일</label><br>
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
				<br>
				<span id="email_message" class="message"></span>
								
				<br>
				
				<input type="button" value="회원가입" id="join_btn">
			</form>
		</section>
		
		<footer>
			<div class="back_page">
				<a href="index.do">로그인화면으로 돌아가기</a>
			</div>
		</footer>
			
	</div>
	

</body>
</html>