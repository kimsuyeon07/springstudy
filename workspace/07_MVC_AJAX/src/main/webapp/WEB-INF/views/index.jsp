<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		$(document).ready(function(){
			
			
			const f = $('#f');
			const name = $('#name');
			const age = $('#age');
			const btn1 = $('#btn1');
			const btn2 = $('#btn2');
			 
			fn1();
			fn2();
			
			// 함수
			function fn1() {
				btn1.on('click', function(){
					$.ajax({
						url: 'v01',  // @RequestMapping(value="v01")
						type: 'get', // @RequestMapptin(method=RequestMethod.GET)
						data: 'name='+name.val() + '&age=' + age.val(), // @RequestParam("name") String name, @RequestParam("age") int age
						dataType: 'text', // @RequestMapping(produces = "text/plain; charset=UTF-8")
						success: function(responseData) { // responseData에 return p로 반환한 String 데이터
							console.log(responseData);
						}, 
						error: function(xhr, text, error){
							console.log(xhr.responseText + ', ' + error);
						}
					})
				})
			} // end fn1()
			
			function fn2() {
				btn2.on('click', function(){
					$.ajax({
						url: 'v02',  // @RequestMapping(value="v01")
						type: 'get', // @RequestMapptin(method=RequestMethod.GET)
						data: f.serialize(), // form의 모든 요소를 한번에 보내는 방법 : $('#f').serialize()
						dataType: 'json', // @RequestMapping(produces = "text/plain; charset=UTF-8")
						success: function(responseData) { // responseData에 return p로 반환한 String 데이터
							console.log(responseData);
						}, 
						error: function(xhr, text, error){
							console.log(xhr.responseText + ', ' + error);
						}
					})
				})
			} // end fn2()
			
		})
	</script>
</head>
<body>

	<% request.setCharacterEncoding("UTF-8"); %>
	<form id="f">
		<input type="text" name="name" id="name" placeholder="ID">	<br>
		<input type="text" name="age" id="age" placeholder="Age">	<br><br>
		<input type="button" value="전송" id="btn1">	<br>
		<input type="button" value="전송" id="btn2">	<br>
	</form>	

</body>
</html>