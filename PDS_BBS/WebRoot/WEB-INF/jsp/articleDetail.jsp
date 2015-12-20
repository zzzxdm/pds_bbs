<%@page language="java" import="java.util.*,com.pds.pojo.User" pageEncoding="utf-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    User user = (User)request.getSession().getAttribute("user");
    int age = 0;
    if(user != null){
	 	int year = Integer.parseInt(user.getBirthday().toString().substring(0,4));
	 	age = Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date())) - year;
    }
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>文章详情页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="js/jquery-2.1.4.min.js"></script>
	<script src="ckeditor/ckeditor.js"></script>
	<script type="text/javascript">
        $(document).ready(function(){
            CKEDITOR.replace('editor',{ height: '200px', width: 'inherit' });
        });
    </script>
  </head>
  
  <body>
	<!-- 引入头部文件 -->
  	<jsp:include page="head.jsp"></jsp:include>
  	<script type="text/javascript">
  		function attention(data){
  			var friendId = $(data).attr("id");
  			$.post("AttentionAction!attention?friendId=" + friendId,null,function(response){    
             	if(response == "true"){
             		alert("关注成功!");
             	}else if(response == "false"){
             		alert("你已经关注过该用户，无需再次关注!");
             	}else if(response == "null"){
             		alert("你可能还没有登录呢，去登录吧...");
             	}
            }); 
  		}
  	</script>
  	<div class="container">
    <div class="contents">
        <h3 align="center" style="margin-bottom: 20px"><strong>${article.title}</strong></h3>
        <div class="article">
            <div class="article-left" style="float:left;width:16%;">
                <div class="img" style="height: 20%">
                    <div style="margin-top: 30px"  align="center">
                        <img src="${article.user.picture == null ? 'image/default.png' : article.user.picture}" width="100px";height="100px" style="border-radius: 5px">
                    </div>
                    <p align="center"><span>用户头像</span></p>
                </div>
                <div class="text" style="height: 80%;margin-top: 10px">
                    <ul class="list" style="list-style: none">
                        <li class="list-item">ID:<strong>${article.user.username}</strong></li>
                        <li class="list-item">年龄:<%=age %></li>
                        <li class="list-item">注册时间:${article.user.regDate.toString().substring(0,10)}</li>
                        <li class="list-item"><button class="btn btn-sm btn-danger" id="${article.user.id}" onclick="attention(this)">关注</button></li>
                    </ul>
                </div>
            </div>
            <div class="article-right" style="float:left;width:84%">
                <div class="top" style="height: 94%;overflow: auto;word-wrap: break-word;word-break: normal;">
                    <div class="container" style="width:inherit;padding-top:20px;">
                    	${article.content}
                    </div>
                </div>
                <div class="bottom" style="height: 6%;">
                    <span style="float: left;padding-left: 20px;margin-top: 6px">发表于：<span>${article.writeTime.toString().substring(0,19)}</span></span>
                    <div style="width: 13%;float: right;margin-top: 6px">
                    	<button class="btn btn-xs btn-danger" >删除</button>
                        <span style="float: right;margin-right: 10px"># <span>1</span> 楼</span>
                    </div>
                </div>
            </div>
        </div>
        <ul style="list-style: none;margin-left: -40px" id="comments">
        <c:choose>
		<c:when test="${empty comments}">
			<h4>暂时还没有人评论呢</h4>
		</c:when>
		<c:otherwise>
			<c:forEach var="c" items="${comments}" varStatus="s">
				<li>
			        <div class="comments">
			            <div class="comment-left" style="float:left;width:16%;">
			                <div class="img" style="height: 50%">
			                    <div style="margin-top: 30px"  align="center">
			                        <img src="${c.user.picture == null ? 'image/default.png' : c.user.picture}" width="100px";height="100px" style="border-radius: 5px">
			                    </div>
			                    <p align="center"><span>用户头像</span></p>
			                </div>
			                <div class="text" style="height: 50%;margin-top: 10px">
			                    <ul class="list" style="list-style: none">
			                        <li class="list-item">ID:<strong>${c.user.username}</strong></li>
			                        <li class="list-item">注册时间:${c.user.regDate.toString().substring(0,10)}
			                        <li class="list-item"><button class="btn btn-sm btn-danger" id="${c.user.id}" onclick="attention(this)">关注</button></li>
			                    </ul>
			                </div>
			            </div>
			            <div class="comment-right" style="float:left;width:84%">
			                <div class="top" style="height: 88%">
			                    <div class="container content" style="padding-top:20px;width:inherit;overflow: auto;word-wrap: break-word;word-break: normal;">
									${c.content}
			                    </div>
			                </div>
			                <div class="bottom" style="height: 12%;">
			                    <span style="float: left;padding-left: 20px;margin-top: 3px">回复 <strong>${article.user.username}</strong> 于：${c.writeTime.toString().substring(0,19)}</span>
			                    <div style="width: 18%;float: right;margin-top: 3px">
			                        <button class="btn btn-xs btn-success" >回复</button>&nbsp;
			                        <button class="btn btn-xs btn-danger" >删除</button>
			                        <span style="float: right;margin-right: 10px"># <span>${s.count+1}</span> 楼</span>
			                    </div>
			                </div>
			            </div>
			        </div>
        		</li>
			</c:forEach>
		</c:otherwise>
		</c:choose>
        </ul>
    </div>
    <div class="reply" style="margin-top: 20px">
        <form accept-charset="utf-8" action="CommentArticle" method="post" id="reply-form">
            <h4>在下方评论框中发表评论内容</h4>
            <div class="editor">
            	<input type="hidden" name="comment_pid" value="${article.id}">
              <textarea cols="40" id="editor" name="comment_content" rows="20" style="width:100%;height:200px;">
              </textarea>
                <div class="btngroup" align="center" style="margin-top: 15px">
                    <input class="btn btn-success" id="submit" type="submit" value="提交回复"/>
                    <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                    <input class="btn btn-default" id="back" type="button" value="返回列表" onclick="window.location.replace('displayArticle')"/>
                </div>
            </div>
            <s:token/>
        </form>
    </div>
</div>
<jsp:include page="foot.jsp"></jsp:include>
  </body>
</html>
