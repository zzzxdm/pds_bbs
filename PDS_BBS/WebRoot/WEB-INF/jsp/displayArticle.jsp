<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>浏览所有文章页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
    <jsp:include page="head.jsp"></jsp:include>
	<script type="text/javascript" src="js/Article.js"></script>
    <div class="container" style="min-height:800px">
       <div class="left">
        <div class="top">
            <ul class="nav nav-list bs-docs-sidenav">
            	<li class="list-group-item" style="background-color:#74E05A">
                    <span><strong>文章类型</strong></span>
                </li>
                <li class="list-group-item-success">
                    <a href="javascript:void(0)" name="mood" id="1" onclick="getArticle(this)" > 1.心情驿站</a>
                </li>
                <li class="list-group-item-danger">
                    <a href="javascript:void(0)" name="baike" id="1" onclick="getArticle(this)" > 2.糗事百科</a>
                </li>
                <li class="list-group-item-warning">
                    <a href="javascript:void(0)" name="life" id="1" onclick="getArticle(this)" > 3.生活窍门</a>
                </li>
                <li class="list-group-item-info">
                    <a href="javascript:void(0)" name="yuan" id="1" onclick="getArticle(this)" > 4.原创文章</a>
                </li>
                <li class="list-group-item-danger">
                    <a href="javascript:void(0)" name="technology" id="1" onclick="getArticle(this)" > 5.技术分享</a>
                </li>
                <li class="list-group-item-success">
                    <a href="javascript:void(0)" name="experience" id="1" onclick="getArticle(this)" > 6.经验交流</a>
                </li>
            </ul>
        </div>
        <div class="bottom">
        	<ul class="nav nav-list bs-docs-sidenav">
            	<li class="list-group-item-info" style="background-color:transparent">
                    <input class="btn btn-success" onclick="window.location.replace('Refresh')" value="刷新列表" style="width:185px">
                    <input class="btn btn-primary" onclick="window.location.replace('writeArticle')" value="我来发表" style="width:185px"/>
                </li>
            </ul>
       </div>
    </div>
    <div class="right">
            <div class="content" style="height:750px;overflow:auto">
                <ul class="list-group" id="articles">
                    
                </ul>
            </div>
            <div class="right-bottom">
            	<div class="pagelist" style="height:50px" align="center">
            		<ul class="pagination pagination-sm" id="pages" style="height:30px;margin:10px auto">
	            		<li><span>正在加载文章列表，请稍后.....</span></li>
            		</ul>
	            		<p>总条数：<span id="articlesCount"></span> 条  当前页数：<span id="pageCount"></span> 页</p>
            	</div>
            </div>
         </div>
    </div>
    <jsp:include page="foot.jsp"></jsp:include>
  </body>
</html>
