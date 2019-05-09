<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<shiro:hasPermission name="select">
		<h1>123123213122222qqq</h1>
		<a href="/sys/doLogout">退出</a>
	</shiro:hasPermission>

	<shiro:hasPermission name="create">
		<h1>123123213wwww</h1>
	</shiro:hasPermission>

	<shiro:hasPermission name="create">
		<h1>123123213eeeee</h1>
	</shiro:hasPermission>

</body>
</html>