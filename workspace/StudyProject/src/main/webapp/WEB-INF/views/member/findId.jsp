<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>아이디 찾기</title>
	<link rel="stylesheet" href="resources/asset/css/study.css">
	<link rel="stylesheet" href="resources/asset/css/find_Id_Pw.css">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="  crossorigin="anonymous"> </script>
	<script>
		$(document).ready(function(){
			fn_tapChange();
			fn_email();
			fn_findId();
		}) //페이지 로드 이벤트 (종료)
		
		/* 함수 생성 */
		
		function fn_tapChange() {
			$('#tap1').click(function(){
				$('input:radio[name="tap"]').attr('checked', false); // 초기화
				$('input:radio[name="tap"][value="'+ $('#tap1').val() +'"]').attr('checked', true);
				$('#tap1').next().addClass('tap_click');
				$('#tap2').next().removeClass('tap_click');
				$('#tap_box1').removeClass('none');
				$('#tap_box2').addClass('none');
			});
			$('#tap2').click(function(){
				$('input:radio[name="tap"]').attr('checked', false); // 초기화
				$('input:radio[name="tap"][value="'+ $('#tap2').val() +'"]').attr('checked', true); 
				$('#tap2').next().addClass('tap_click');
				$('#tap1').next().removeClass('tap_click');
				$('#tap_box2').removeClass('none');
				$('#tap_box1').addClass('none');
			});
		} /* [END]fn_tapChange() */
		 
		
		var emailCheck = false;
		function fn_findId(){
			$('#f').submit(function(event){
				if ( $('input:radio[name="tap"]:checked').val() == $('#tap1').val() ) {
					if ($('#name').val() == '' || $('#phone').val() == '') {
						alert('이름과 전화번호를 입력해주세요.');
						event.preventDefault();
						return false;
					}
				} else if($('input:radio[name="tap"]:checked').val() == $('#tap2').val()) {
					if ( $('#email').val() == '' || !emailCheck ) {
						alert('이메일을 인증하세요.');
						event.preventDefault();
						return false;
					}
				}
			});
		} /* [END]fn_findId() */
		
		
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

	<div id="findId" class="container con find">
	
		<header>
			<h1 class="en_B_lt">FIND ID</h1>
		</header>
		
		<section>
			<form id="f" action="findId.do" method="post">
				
				<div class="tap_radio">
					<input type="radio" name="tap" id="tap1" value="findId_name_phone" checked>
					<label for="tap1" class="tap_tit tap_click">이름 + 전화번호로 찾기</label>
					<input type="radio" name="tap" id="tap2" value="findId_email">
					<label for="tap2" class="tap_tit">이메일로 찾기</label>
				</div>
				
				<div id="tap_box1" class="box">
					<input type="text" name="name" id="name" placeholder="Name" class="en_R">
					<br>
					<input type="text" name="phone" id="phone" placeholder="Phone (010-0000-0000) " class="en_R">
					<br>
					<button>아이디 찾기</button>
				</div>
				
				<div id="tap_box2" class="box none">
						<div class="email_box box1">
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
						</div>

						<div class="email_box box2">
							<input type="text" name="email_check" id="email_check" placeholder="인증번호 입력">
							<input type="button" value="인증하기" id="email_authCode_check_btn">
						</div>
						<button>아이디 찾기</button>
				</div>
				
				<div class="back_page">
					<a href="loginPage.do" class="L">로그인화면으로 돌아가기</a>
				</div>	
				
			</form>
		</section>
		
		
		<footer></footer>
			
	</div>

	

</body>
</html>