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
			
		}); // 페이지로드이벤트
		
		function fn_join() {
			$('#f').submit(function(event){
				if ( fn_idCheck() ) {
					// alert('가입된 아이디입니다.');
					event.preventDefault();
					return false;
				}
				
			}); // submit (end)
		}
		
		function fn_idCheck() {
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
			})
		}
		
	</script>
</head>
<body>

	<h1>회원가입</h1>
	
	<form id="f" action="join.do" method="post">
		<p>아이디</p>
		<input type="text" name="id" id="id">		<br><br>
		
		<p>비밀번호</p>
		<input type="password" name="pw" id="pw">	<br><br>

		<p>비밀번호 확인</p>
		<input type="password" name="pw2" id="pw2">	<br><br>
		
		<p>이름</p>
		<input type="text" name="name" id="name">	<br><br>
		
		<button>회원가입</button>
	</form>

</body>
</html>