<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("UTF-8");%> 
<!DOCTYPE html>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- 	<form action="${contextPath }/test/login.do" method="POST"> --%>
<%-- <form action="${contextPath }/test/login2.do" method="POST"> --%>
<%-- 	<form action="${contextPath }/test/login3.do" method="POST"> --%>
<%-- 	<form action="${contextPath }/test/login4.do" method="POST"> --%>
	<form action="${contextPath }/test/login5.do" method="POST">
		<input type="hidden" name="email" value="hong@test.com">
		<table width='400px'>
			<tr>
				<td>아이디<input type="text" size="10" name="userId"></td>
			</tr>
			<tr>
				<td>이름<input type="text" size="10" name="userName"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="로그인">
					<input type="reset" value="다시입력">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>