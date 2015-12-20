<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<title>论坛登录页面</title>
	<meta charset="UTF-8">
	<link href="css/login.css" rel='stylesheet' type='text/css' />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/login.js"></script>
	<script type="text/javascript">
		function change_code(img){
			img.src +="?s" + new Date().getTime();
		}
	</script>
</head>
<body>
	 <!-----start-main---->
	 <div class="main">
		<div class="login-form"align="center">
			<h1 align="center">平顶山学院学习交流论坛</h1>
					<div class="head">
						<img src="image/user.png" alt="头像图片"/>
					</div>
				<s:form action="Login" method="post" id="loginform" onsubmit="return CheckCode(this)">
						<input type="text" class="text" id="userName" name="userVo.name" placeholder="用户名" required>
						<input type="password" id="password" name="userVo.password" placeholder="密码" required>
						<input type="text" name="check_code" id="check_code" placeholder="验证码" required>
						<img alt="验证码" src="ValidateCode" width=120 height=38 id="code" 
						onclick="change_code(this)" style="vertical-align:middle;cursor:hand;">
						<s:token/>
						<div class="submit">
							<input type="submit" value="登录" >
							<span id="c_notify" style="color:red;font-size:15px;margin:0 auto">&nbsp;</span>
						</div>	
						<div style="margin:5px 0px -5px 0px;">
							<a href="" style="float:left">忘记密码？</a>
							<a href="register" style="float:right">还没账号？</a>
						</div>
				</s:form>
			</div>
  				<div class="copy-right">
					<p>Copyright 2015,Java 1 - Six Group All rights reserved.</p>
				</div>
		</div>	
</body>
</html>
