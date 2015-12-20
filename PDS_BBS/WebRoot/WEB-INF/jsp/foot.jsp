<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>底部页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
		*{ 
		   margin:0 auto;
		   color:#999;
		  }
		.buttom{ 
		  	height:120px;
		  	width:100%x;
		  	margin-top:20px;
		 }
		a{ 
		   color:#999;
		   text-decoration:none;
		   font-size:15px;
		 }
		.friend,.famous,.footer{
		 	margin-bottom:0px;
		 	text-align:center;
		 }
		.lun{ 
			 font-family:新宋体;
			 font-size:15px;
			}
		.lun a{ 
			margin-left:20px;				
		}		
	</style>
  </head>
  
  <body>
     <div class="buttom">
		<div class="friend">
			<span class="lun">友情链接：</span>
			<a href="http://www.pdsu.edu.cn/" title="平顶山学院">平顶山学院</a>
			<a href="http://jwc.pdsu.edu.cn/" title="平顶山学院教务处">平顶山学院教务处</a>
			<a href="http://software.pdsu.edu.cn/" title="平顶山学院软件学院">平顶山学院软件学院</a>
			<a href="http://tieba.baidu.com/f?kw=%C6%BD%B6%A5%C9%BD%D1%A7%D4%BA&fr=ala0&tpl=5" title="平顶山学院贴吧">平顶山学院贴吧</a>
			<a href="http://jsjxy.pdsu.edu.cn/" title="平顶山学院计算机科学与技术学院">平顶山学院计算机科学与技术学院</a>  
			<a href="https://www.baidu.com/?tn=94446779_hao_pg" title="百度">百度</a>
			<a href="http://www.sina.com.cn/" title="新浪">新浪</a>
		</div>
		<div class="famous">
			<span class="lun">知名论坛：</span>
			<a href="http://bbs.tianya.cn/" title="天涯社区">天涯社区</a>
			<a href="http://www.renren.com/" title="人人网">人人网</a>
			<a href="http://www.mop.com/" title="猫扑">猫扑</a>
			 <a href="http://www.ifeng.com/" title="凤凰论坛">凤凰论坛</a>
			 <a href="http://hongdou.gxnews.com.cn/" title="红豆论坛">红豆论坛</a>
			 <a href="http://weibo.com/" title="新浪微博">新浪微博</a>
			 <a href="http://t.qq.com/" title="腾讯微博">腾讯微博</a>
			 <a href="http://club.sohu.com/" title="搜狐论坛">搜狐论坛</a>
			 <a href="http://bbs.huanqiu.com/" title="环球论坛">环球论坛</a>
		</div>
		<div class="footer">
			平顶山学院学院学习论坛客服留言，联系电话：0375-6666666<br/>
			<a href="http://www.fmprc.gov.cn/zalt/chn/gylt/"><span>关于论坛</span></a>
			<span>|</span>
			<a href="http://www.jxnews.com.cn/oldnews/n1094/ca750416.htm"><span>论坛简介</span></a>
			<span>|</span>
			<a href="#"><span>联系我们</span></a>
			<span>|</span>
			<a href="#"><span>隐私与版权</span></a>
			<span>|</span>
			<a href="#"><span>加入我们</span></a>
			<br/>
			Copyright@平顶山学院学习论坛
		</div>	
	 </div>
  </body>
</html>
