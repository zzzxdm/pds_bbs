<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%-- <%
	String content = request.getParameter("editor");
	if (content != null && !content.equals("")) {
		out.println(content);
	}
%> --%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>论坛发表文章页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
        	CKEDITOR.replace('editor',{ height: '400px', width: 'inherit' });
        });
    </script>

  </head>
  
  <body>
	<!-- 引入头部文件 -->
  	<jsp:include page="head.jsp"></jsp:include>
  	<div class="container">
  	<s:form action="WriteArticle" method="post">
        <div class="notify" style="margin-top:-10px">
        	<div style="width:inherit;height:48px">
        		<h4>请在下面标题框中输入文章标题!</h4>
            	<h4 style="float: left;margin-top: 0px;line-height: 15px">标题： </h4>
            	<input style="float: left;margin-top: -10px" type="text" id="title" name="articleVo.title">
        	</div>
        	<div style="width:inherit;height:30px">
        		<h4 style="float: left;margin-left:-54px">请选择文章类型：</h4>
        		<select name="articleVo.type" style="float: left;margin:8px auto">
        			<optgroup label="文章类型">
        				<option value="心情驿站">1.心情驿站</option>
        				<option value="糗事百科">2.糗事百科</option>
        				<option value="生活窍门">3.生活窍门</option>
        				<option value="原创文章">4.原创文章</option>
        				<option value="技术分享">5.技术分享</option>
        				<option value="经验交流">6.经验交流</option>
        			</optgroup>
        		</select><br>
        	</div>
        	<div style="width:inherit;height:30px">
            	<h4>请在下面编辑文章内容!</h4>
        	</div>
        </div>
        
        	<div class="text">
            <textarea name="articleVo.content" id="editor"></textarea>
	        </div>
	        <div class="btn_group">
	            <div class="btns">
	                <input class="btn btn-success" id="submit" type="submit" value="发表"/>
	                <input class="btn btn-danger" id="cancel" type="button" value="取消" onclick="window.location.replace('index')"/>
	            </div>
	        </div>
	      <s:token/>
      </s:form>
    </div>
    <jsp:include page="foot.jsp"></jsp:include>
  </body>
</html>
