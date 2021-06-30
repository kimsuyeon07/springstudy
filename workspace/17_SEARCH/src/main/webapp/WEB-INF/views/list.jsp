<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="  crossorigin="anonymous"> </script>
	<script>
	
	</script>
</head>
<body>

	<!-- 결과 목록 화면 -->
	<h1>사원 목록</h1>
	
	<table border="1">
		<thead>
			<tr>
				<td>EMPLOYEE_ID</td>
				<td>FIRST_NAME</td>
				<td>LAST_NAME</td>
				<td>HIRE_DATE</td>
				<td>SALARY</td>
				<td>DEPARTMENT_ID</td>
			</tr>
		</thead>
		
		<tbody>
			<c:if test="${empty list}">
				<tr>
					<td colspan="6">검색된 사원 정보가 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${not empty list}">
				<c:forEach var="emp" items="${list}">
					<tr>
						<td>${emp.employeeId}</td>
						<td>${emp.firstName}</td>
						<td>${emp.lastName}</td>
						<td>${emp.hireDate}</td>
						<td>${emp.salary}</td>
						<td>${emp.departmentId}</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
		
		<tfoot>
			<tr>
				<td colspan="6">
					${paging}
				</td>
			</tr>
			<tr>
				<td colspan="6">
					<input type="button" value="검색하러가기" onclick="location.href='index.do'">
				</td>
			</tr>
		</tfoot>
		
	</table>

</body>
</html>