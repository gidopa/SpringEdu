<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("UTF-8");%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- <h1>입력한 아이디 : ${loginVO.userId }</h1>
	<h1>입력한 이름 : ${loginVO.userName }</h1>
	<h1>이메일 : ${email }</h1> --%>
	<h1>입력한 아이디 : ${userId }</h1>
	<h1>입력한 이름 : ${userName }</h1>
	<h1>이메일 : ${email }</h1>
</body>
</html>