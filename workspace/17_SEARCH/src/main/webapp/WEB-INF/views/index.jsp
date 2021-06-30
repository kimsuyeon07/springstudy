<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="  crossorigin="anonymous"> </script>
	<script>
		$(function(){
			fn_search();
			fn_init();
			fn_search_all();
			fn_auto_complete();
		}) // 페이지 로드 이벤트 (종료)
		
		/* 함수 생성 */
		function fn_search() {
			$('#search_btn').click(function(){
				if($('#column').val() == '') {
					alert('검색의 카테고리를 선택해주세요!');
					return false;
				}
				$('#f').attr('action', 'search.do');
				$('#f').submit();
			}); //$('#search_btn').click (END)
		} //[END]fn_search()
		
		function fn_init() {
			$('#init_btn').click(function(){				
				$('#column').val('');
				$('#query').val('');
			}); //$('#init_btn').click (END)
		} //[END]fn_init() 
		
		function fn_search_all() {
			$('#search_all_btn').click(function(){
				location.href='searchAll.do';
			}); //$('#search_all_btn').click (END)
		} //[END]fn_search_all()
		
		function fn_auto_complete(){
			/* .keyup : 문자를 한글자씩 특정 이벤트를 (오류나, 자동완성) 확인해준다. */
			$('#query').keyup(function(){
				$('#auto_complete_list').empty(); // 내부의 value 태그 모두 지워준다. [새로운 목록을 만들어 주겠다.]
				var obj = {
					column: $('#column').val(),
					query: $('#query').val() + '%'
				};
				$.ajax({
					url: 'autoComplete.do',
					type: 'post',
					contentType: 'application/json',
					data: JSON.stringify(obj),
					dataType: 'text',
					success: function(resultMap){
						//console.log(resultMap);
						var result = JSON.parse(resultMap);
						if(result.status == 200) {
							// 반복문
							$.each(result.list, function(i, employee) {
								console.log($('#column').find('option[value="'+ obj.column +'"]').data('name'));
								/* 
									<option value="FIRST_NAME" data-name="firstName">
									<option value="FIRST_NAME" data-name="firstName">
									<option value="LAST_NAME"  data-name="lastName" > 의
									↓
									[ data-name="firstName" ]을 빼는 방법
									↓
									$('#column').find('option[value="'+ obj.column +'"]').data('name')
								*/
								$('<option>')
								.val(employee[$('#column').find('option[value="'+ obj.column +'"]').data('name')])
								.text(employee[$('#column').find('option[value="'+ obj.column +'"]').data('name')])
								.appendTo($('#auto_complete_list'));
							}); //$.each() (END)
						}
					},
					error: function(xhr, textStatus, errorThrown) {
						
					}
				}); //$.ajax (END)
			}); //$('#query').keyup (END)
		} //[ENd]fn_auto_complete()
		
	</script>
</head>
<body>

	<br>

	<!-- 검색화면 -->
	<form id="f" method="get">
		<select name="column" id="column">
			<option value="">:::선택:::</option>
			<option value="EMPLOYEE_ID" data-name="employeeId">EMPLOYEE_ID</option>
			<option value="FIRST_NAME" data-name="firstName">FIRST_NAME</option>
			<option value="LAST_NAME" data-name="lastName">LAST_NAME</option>
			<option value="SALARY" data-name="salary">SALARY</option>
		</select>
		<!-- 해당 [input]에 [datalist]를 붙여준다 (한 덩어리로 보이게 한다) -->
		<!-- 입력과 목록 모두 사용 가능하다. -->
		<input list="auto_complete_list" type="text" name="query" id="query">
		<datalist id="auto_complete_list"> <!-- 자동완성 --> </datalist>
		
		<!-- <option value="SALARY"...> 에 필요한 정보  -->
		<input type="number" name="bottom" placeholder="최소연봉">
		<input type="number" name="top" placeholder="최대연봉">
		
		<input type="button" value="검색" id="search_btn">
		<input type="button" value="초기화" id="init_btn">
		<input type="button" value="전체조회" id="search_all_btn"> <!-- 전체 결과를 가지고 오겠다. -->
	</form>
	
	<br>	<hr>	<br>
	
	

</body>
</html>