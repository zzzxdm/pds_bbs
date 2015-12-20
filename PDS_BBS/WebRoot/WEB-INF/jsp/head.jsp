<%@ page language="java" import="java.util.*,java.lang.*" pageEncoding="utf-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta lang="zh-CN">
    <meta charset="UTF-8">
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/common.css" rel="stylesheet">
    <script src="js/jquery-2.1.4.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="js/common.js"></script>
</head>
<body>
<div class="container">
    <div class="head">
        <div class="logo"></div>
        <div class="content">
            <div class="welcome">
                <span>&nbsp;&nbsp;&nbsp;欢迎你:&nbsp;</span>
                <span>
                	<strong>
                		<s:if test="#session.user==null">游客 Id:<%=(int)(Math.random()*1000) %>&nbsp;&nbsp;&nbsp;
                		<s:a href="login" style="color:#333">登录</s:a>&nbsp;&nbsp;&nbsp;&nbsp;
                		<s:a href="register" style="color:#333">注册</s:a>
                		</s:if>
                		<s:elseif test="#session.user.name==null||#session.user.name==''">${user.username}</s:elseif>
                		<s:else>${user.name}</s:else>
                	</strong>
                </span>
            </div>
            <div class="infodisplay">
                <span style="margin-left: 80px">&nbsp;&nbsp;当前时间：<span id="nowtime">获取系统时间中......</span></span>
                <span style="float: right;margin-right: 50px">在线人数：<span id="p_number">${application.online}</span>&nbsp;人</span>
            </div>
        </div>
    </div>
    <nav class="navbar navbar-inverse" role="navigation">
        <div class="navbar-header">
            <span class="navbar-brand">论坛导航</span>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li><a href="#">首页</a></li>
                <li><a href="displayArticle">浏览文章</a></li>
                <li><a href="writeArticle">发表文章</a></li>
                <li><a href="#">上传资料</a></li>
                <li><a href="#">资料下载</a></li>
				<li class="dropdown">
				<a href="#" class="dropdown-toggle"
					data-toggle="dropdown"> 个人中心 <b class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						<li><a href="updateInfo">完善个人信息</a></li>
						<li><a href="#">其他</a></li>
					</ul>
				</li>
				<li><a href="Logout">退出系统</a></li>
            </ul>
        </div>
    </nav>
</div>
</body>
</html>