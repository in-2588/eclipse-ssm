<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body align="center">
<a href="${pageContext.request.contextPath }/test.do">into....</a>
<form action="${pageContext.request.contextPath }/test.do" method="post">
<input type="text" name="shu" value=""><br>
<input type="button" value="搜索">
</form>
</body>
</html>