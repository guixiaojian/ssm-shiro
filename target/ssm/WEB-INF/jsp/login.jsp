<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>登录页面</title>
    <link href="${ctx}/resource/css/login.css" rel="stylesheet">
</head>
<body>



	<form class="val-form panel-body" method="post" action="doLogin">
		<div id="span">
			<%-- <img src="${APP_PATH }/resource/images/logo4.png"> --%>
			<span>天行企业人事管理系统登录</span>
		</div>
        <div>
            <input type="text" name="account" class="username-box" maxlength="11" placeholder="请输入登录名" autocomplete="off">
        </div>
        <div>
            <input type="password" name="passWord" class="password-box" maxlength="16" placeholder="请输入登录密码">
        </div>
        <div>
            <label><input type="checkbox" style="width: 15px;vertical-align: middle;margin-top: 3px;" name="isRememberMe">记住我</label>
            <button type="submit" name="submit" class="ladda-button" data-style="zoom-out" style="width: 200px;">
                <span class="ladda-label">立即登录</span>
            </button> 
            <!-- <span id="forgetPassword">忘记密码 ？</span> -->
        </div>
    </form>
<script type="text/javascript"></script>

</body>
</html>
