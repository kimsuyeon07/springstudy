<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			fn_idCheck();
			fn_pwCheck();
			fn_join();
		});
		var idPass = false;
		function fn_idCheck() {
			$('#id').keyup(function(){
				$.ajax({
					url: 'idCheck.do',
					type: 'get',
					data: 'id=' + $('#id').val(),
					dataType: 'json',
					success: function(res){
						if (res.count == 0) {
							idPass = true;
						} else {
							idPass = false;
						}
					}
				});
			})
		}
		var pwPass = false;
		function fn_pwCheck(){
			$('#pw2').keyup(function(){
				if ($('#pw').val() != '' && $('#pw').val() == $('#pw2').val()){
					pwPass = true;
				} else {
					pwPass = false;
				}
			});
		}
		function fn_join(){
			$('#join_btn').click(function(){
				if ( !idPass ) {
					alert('아이디를 확인하세요.');
					return false;
				} else if ( !pwPass ) {
					alert('비밀번호를 확인하세요.');
					return false;
				} else {
					location.href = 'join.do';
				}
			});
		}
	</script>
</head>
<body>

	<h1>회원가입</h1>
	<form id="f" method="post">
		아이디<br>
		<input type="text" name="id" id="id"><br><br>
		비밀번호<br>
		<input type="password" name="pw" id="pw"><br><br>
		비밀번호 확인<br>
		<input type="password" name="pw2" id="pw2"><br><br>
		이름<br>
		<input type="text" name="name" id="name"><br><br>
		이메일<br>
		<input type="text" name="email" id="email"><br><br>
		<input type="button" value="가입하기" id="join_btn">
	</form>

</body>
</html>