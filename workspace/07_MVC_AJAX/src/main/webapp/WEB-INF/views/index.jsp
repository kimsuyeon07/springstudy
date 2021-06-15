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
			const btn = $('#btn');
			
			// 함수
			function fn1() {
				btn.click(function(){
					$.ajax({
						url: 'v01',  // @RequestMapping(value="v01")
						type: 'get', // @RequestMapptin(method=RequestMethod.GET)
						data: 'name='+name.val() + '&age=' + age.val(), // @RequestParam("name") String name, @RequestParam("age") int age
						dataType: 'json', // @RequestMapping(produces = "application/json; charset=UTF-8")
						success: function(responseData) { // responseData에 return p로 반환한 json 데이터
							console.log(responseData);
						}, 
						error: function(xhr, text, error){
							console.log(xhr.responseText + ', ' + error);
						}
					})
				})
			} // end fn1()
			
		})
	</script>
</head>
<body>

	<form id="f">
		<input type="text" name="name" id="name" placeholder="ID">	<br>
		<input type="text" name="age" id="age" placeholder="Age">	<br><br>
		<input type="button" value="전송" id="btn">	<br>
	</form>	

</body>
</html>