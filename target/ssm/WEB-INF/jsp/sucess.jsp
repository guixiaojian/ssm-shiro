<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="${ctx }/resource/js/jquery-1.9.1.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<h1>清明又见雨</h1>
	<h1>雨下又清明</h1>
</body>
<script type="text/javascript">
$(function(){
	window.location = "/sys/index";
})
	
</script>
</html>