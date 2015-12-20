<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
    <title>完善更改信息页面</title>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="完善更改信息页面">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <!-- <link rel="stylesheet" type="text/css" href="css/register.css" /> -->
    <script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="js/common.js"></script>
    <script>
        function show(){
            var file = document.getElementById("pic").files[0];
            var fileReader = new FileReader();
            fileReader.readAsDataURL(file);
            fileReader.onload = function(){
                document.getElementById("img").src = fileReader.result;
            }
        }
        function change_code(img){
            img.src = img.src + "?" + new Date().getTime();
        }
    </script>
</head>
<body>
<jsp:include page="head.jsp"></jsp:include>
<div class="container">
	<div style="width:50%;height:inherit;margin:0 auto">
		<div>
			<h3 align="center"><strong>完善更改信息</strong></h3>
		</div>
		<div class="container">
			<table align="center" class="table">
				<tbody>
					<form action="Upload" method="post" enctype="multipart/form-data">
                        <tr>
                            <td align="right">
                              <p>头像：</p>  
                            </td>
                                <td align="left">
                                    <input type="file" id="pic" name="pic" onchange="show()" style="float:left"/>
                                    <span>
                                    	<img id="img" src="${user.picture}" width="60" height="60" style="vertical-align: middle;float:left">
                                    </span>
                                    <input type="submit" value="上传" class="btn btn-info" style="margin:15px 0 0 10px"/>
                                </td>
                        </tr>
                        <s:token/>
                      </form>
				</tbody>
			</table>
		</div>
		<div>
			<table align="center" class="table">
                      <form action="Update" id="update_form" name="update_form" method="post" >
                        <tr>
	                        <td align="right">
	                            账 号：
	                        </td>
	                        <td align="left">
	                            <input type="text" name="m_user.username" id="username" value="${user.username}" required disabled/>
	                            <input type="hidden" name="m_user.username" value="${user.username}">
	                        <td> <p style="margin-left:-160px;color:red;font-size:12px"></p>
	                        </td>
                        </tr>
                        <tr>
                            <td align="right">
                                密 码：
                            </td>
                            <td align="left">
                                <input type="password" name="m_user.password" id="password" value="${user.password}" placeholder="密码" required onkeyup="passwd()"/>
                                <span style="color:red">*</span>
                                <meter min="1" max="10" low="5" high="8" value="0" id="meter"> </meter>
                                <span id="tip"></span>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
                                密码确认：
                            </td>
                            <td align="left">
                                <input type="password" name="m_user.password1" id="password1" value="${user.password}" placeholder="确认密码" required onblur="check_pwd()"/>
                                <span style="color:red">*</span>
                                <span id="notify" style="color:red;font-size:12px;"></span>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
                                姓名：
                            </td>
                            <td align="left">
                                <input type="text" name="m_user.name" value="${user.name}" placeholder="姓名""/>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
                                性 别：
                            </td>
                            <td align="left">
                            <s:if test="#session.user.gender=='男'">
                                <input type="radio" name="m_user.gender" value="男" checked/>男&nbsp;&nbsp;&nbsp;
                                <input type="radio" name="m_user.gender" value="女"/>女
                             </s:if>
                             <s:else>
                             	<input type="radio" name="m_user.gender" value="男" />男&nbsp;&nbsp;&nbsp;
                                <input type="radio" name="m_user.gender" value="女" checked/>女
                             </s:else>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
                                生 日：
                            </td>
                            <td align="left">
                                <input type="date" value="${user.birthday.toString().substring(0,10)}" name="m_user.birthday" />
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
                                邮 箱：
                            </td>
                            <td align="left">
                                <input type="email" name="m_user.email" value="${user.email}" id="email" required disabled/>
                                <input type="hidden" name="m_user.email" value="${user.email}">
                            </td>
                        </tr><tr>
                            <td align="right">
                                密保问题：
                            </td>
                            <td align="left">
                                <input type="text" name="m_user.question" value="${user.question}" id="question" placeholder="密保问题"/>
                            </td>
                        </tr><tr>
                            <td align="right">
                                答 案：
                            </td>
                            <td align="left">
                                <input type="password" name="m_user.answer" value="${user.answer}" id="answer" placeholder="密保问题答案"/>
                            </td>
                        </tr>
                        
                        <tr height="60px">
                            <td align="center" colspan="2">
                                <input type="submit" value="确定" accesskey="enter" class="btn btn-success"/>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="button" value="取消" accesskey="enter" class="btn btn-danger" onclick="window.location.replace('index')"/>
                            </td>
                        </tr>
                        <s:token/>
                    </form>
             </table>
		</div>
	</div>
	<jsp:include page="foot.jsp"></jsp:include>
</div>
</body>
</html> 