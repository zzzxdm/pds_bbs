/**
*直接使用ajax做用户名 密码的验证
*/

	$(document).ready(function(){   
	        $("#userName").blur(function(){ 
			    var text = $("#userName").val();
	        	if(text != "" && text != null){
	        		$.post("ajax/Check!checkUserName?userName=" + text,null,function(response){    
	                 	if(response != "用户名已经存在!"){
	                 		$("#c_notify").html("对不起，你输入的用户名不存在!");
	                 		setTimeout(function(){
	                 			$("#c_notify").html("&nbsp;")
	                 		},1500);
	                 	}else{
	                 		$("#c_notify").html("&nbsp;")
	                 	}
		            });   
		        }
	        });
	        $("#password").blur(function(){
	        	var u_name = $("#userName").val();
	        	var pwd = $("#password").val();
	        	if(pwd != "" && pwd != null){
	        		$.post("ajax/Check!checkPassword?userName="+u_name+"&password="+pwd,null,function(response){    
	        			if(response == "密码错误!"){
	                 		$("#c_notify").html("对不起,密码错误，请重新输入!");
	                 		setTimeout(function(){
	                 			$("#c_notify").html("&nbsp;");
	                 		},1500);
	                 	}else{
	                 		$("#c_notify").html("&nbsp;");
	                 	}
		            });   
		        }
	        });
	    });