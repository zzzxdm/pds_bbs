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
    <title>论坛系统账号注册页面</title>
    <meta http-equiv="description" content="账号注册页面">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/register.css" />
    <script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript">
		function change_code(img){
			img.src +="?s" + new Date().getTime();
		}
	</script>
</head>
<body>
<div id="3" style="position: relative; top:60px; z-index: 3;">
    <s:form action="Register" id="reg_form" onsubmit="return CheckCode(this)" method="post" >
        <table align="center" cellspacing="0" class="table">
            <tbody>
            <tr class="thead">
                <td align="center">
                    论坛账号注册
                </td>
            </tr>
            <tr>
                <td>
                    <table id="registertable" border="0px" align="center" cellspacing="0" cellpadding="5px">
                        <td align="right">
                            账 号：
                        </td>
                        <td align="left">
                            <input type="text" name="user.username" id="username" placeholder="用户名" required/>
                            <span style="color:red">*</span>
                            <span id="nametip" style="color:red;font-size:12px;"></span>
                        </td> <p style="margin-left:-160px;color:red;font-size:12px"></p> </td>
                        <tr>
                            <td align="right">
                                密 码：
                            </td>
                            <td align="left">
                                <input type="password" name="user.password" id="password" placeholder="密码" onkeyup="passwd()" required/>
                                <span style="color:red">*</span>
                                <meter min="1" max="10" low="5" high="8" value="0" id="meter"> </meter>
                                <span id="pwdtip"></span>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
                                密码确认：
                            </td>
                            <td align="left">
                                <input type="password" name="user.password1" id="password1" placeholder="确认密码" onblur="check_pwd()" required/>
                                <span style="color:red">*</span>
                                <span id="notify" style="color:red;font-size:12px;"></span>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
                                姓名：
                            </td>
                            <td align="left">
                                <input type="name" name="user.name" placeholder="姓名" onclick="check_pwd()" />
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
                                性 别：
                            </td>
                            <td align="left">
                                <input type="radio" name="user.gender" value="男" checked/>男
                                <input type="radio" name="user.gender" value="女"/>女
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
                                生 日：
                            </td>
                            <td align="left">
                                <input type="date" name="user.birthday" />
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
                                邮 箱：
                            </td>
                            <td align="left">
                                <input type="email" name="user.email" placeholder="邮箱" id="email" />
                                <span style="color:red">*</span>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
                                验证码：
                            </td>
                            <td align="left">
                                <input type="text" name="check_code" id="check_code" placeholder="验证码" required>
                                <img alt="验证码" src="ValidateCode" width=120 height=36 id="code"
                                     onclick="change_code(this)" style="cursor:hand;vertical-align:middle">
                            </td>
                        </tr>
                        <tr>
                        	<td align="right">
                        	</td>
                        	<td align="left">
                        		<span id="c_notify" style="color:red;font-size:12px;padding:0">&nbsp;</span>
                        	</td>
                        </tr>
	                    <tr height="60px">
	                        <td align="center" colspan="2">
	                            <input type="button" value="转到登录" onclick="window.location.replace('login')" id="login" class="submit" />
	                            &nbsp;
							    <s:token/>
	                            <input type="submit" accesskey="enter" value="注册" id="register" class="submit" />
	                        </td>
	                    </tr>
                    </table>
                    </td>
                 <tr>
            </tbody>
        </table>
    </s:form>
</div>
</body>
</html> 
