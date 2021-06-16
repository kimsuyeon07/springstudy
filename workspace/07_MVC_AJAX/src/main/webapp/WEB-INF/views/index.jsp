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
			
			// 변수 선언
			const f = $('#f');
			const name = $('#name');
			const age = $('#age');
			const btn1 = $('#btn1');
			const btn2 = $('#btn2');
			const btn3 = $('#btn3');
			 
			// 실행
			fn1();
			fn2();
			fn3();
			
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
							console.log(text + ', ' + error);
						}
					})
				})
			} // end fn1()
			
			function fn2() {
				$('#btn2').click(function(){
					$.ajax({
						url: 'v02',	 // @RequestMapping(value="v01")
						type: 'get', // @RequestMapptin(method=RequestMethod.GET)
						data: $('#f').serialize(),  // form의 모든 요소를 한번에 보내는 방법 : $('#f').serialize()
						dataType: 'json',			// @RequestMapping(produces = "text/plain; charset=UTF-8")
						success: function(responseData){ // responseData에 return p로 반환한 String 데이터
							console.log(responseData);
						},
						error: function(xhr, text, error) {
							console.log(text + ", " + error);
						}
					})
				})
			}  // end fn2()
			
			
			
			function fn3() {
				// 서버로 보낼 json 데이터
				
				btn3.click(function(){
					// ↓ 제이쿼리의 "json"내장객체 : JSON  
					// ↓ 'json'의 문자열 : JSON.stringify 
					var obj = JSON.stringify ({ 
								"name": name.val(),
								"age": age.val()
							  });
					$.ajax ({
						url: 'v03',
						type: 'POST', // >> 'GET'방식으로 보낼 수 없다.  >> **@RequestBody는 'POST'타입으로 보내줘야 한다**
									  // >> json 데이터를 보낼 때 반드시 본문에 포함해서 보낸다.(post)
						data: obj,  // >> 이름없이 값만 보낸다. ( 'obj=' + obj : 파라미터로 보내는 것 )
						  // ▲ → 서버로 '보내는' <json데이터>, 파라미터가 아니다. (@RequestParam 대신 @RequestBody 애너테이션을 사용한다.)
						contentType: 'application/json', // << produces="application/json; charset=utf-8"
						  // ▲ → 서버로 '보내는' 데이터의 타입
						dataType: 'json',
						  // ▲ → 서버에서 '받아오는' 데이터의 타입
						success: function(responseData) {
							console.log(responseData);
						},
						error: function(xhr, text, error) {
							console.log(text + ", " + error);
						}	
					}) // end ajax({})
				})// end click(function(){
				
			} // end fn3()
			
			
		})
	</script>
</head>
<body>

	<form id="f">
		<input type="text" name="name" id="name" placeholder="ID">	<br>
		<input type="text" name="age" id="age" placeholder="Age">	<br><br>
		<input type="button" value="전송1" id="btn1">	<br>
		<input type="button" value="전송2" id="btn2">	<br>
		<input type="button" value="전송3" id="btn3">	<br>
	</form>	

</body>
</html>