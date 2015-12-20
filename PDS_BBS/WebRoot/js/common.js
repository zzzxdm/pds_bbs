/**
 * 
 */
	function passwd(){
            var pass = document.getElementById("password").value;
            var tip = document.getElementById("pwdtip");
            if (pass.length < 4) {
                document.getElementById("meter").value = pass.length;
                tip.innerHTML = "差";
            }
            else
            if (pass.length <= 8) {
                document.getElementById("meter").value = pass.length;
                tip.innerHTML = "中";
            }
            else {
                document.getElementById("meter").value = pass.length;
                tip.innerHTML = "高";
            }
        }
        
        function check_pwd(){
            var pwd1 = document.getElementById("password");
            var pwd2 = document.getElementById("password1");
            var notify = document.getElementById("notify");

            if(pwd1.value != pwd2.value){
                notify.innerHTML = "两次输入的密码不一致!";
                setTimeout(function(){
                    if(notify != ""){
                    	notify.innerHTML = "";
                    }
                },1500);
            }else{
                notify.innerHTML = "";
            }
        }
 
        $(document).ready(function(){   
	        $("#username").blur(function(){ 
			    var text = $("#username").val();
	        	if(text != "" && text != null){
	        		$.post("ajax/Check!checkUserName?userName=" + text,null,function(response){    
	                 	if(response == "用户名已经存在!"){
	                 		$("#nametip").css("color","red");
	                 		$("#nametip").text(response);
		                 	/*setTimeout(function(){
		                        if(text != ""){
		                        	$("#nametip").text("");
		                        }
		                    },1500);*/
	                 	}else{
	                 		$("#nametip").css("color","green");
		            		$("#nametip").text(response);
	                 	}
		            });   
		        }
	        });
	    });
        
        
        function CheckCode(form)
        {
            var flag = false;
            var code = $("#check_code").val();//获得text的值
            var notify = $("#c_notify").val();//获得提示框的值
            if(code != "" && code != null){
        		$.post("ajax/Check!checkValidateCode?vcode=" + code,null,function(response){    
        			if(response == "输入正确"){
        				flag = true;
        				form.submit();
                 	}else if(response == "验证码错误,请重新输入!"){
                 		$("#c_notify").text(response);
	                 	setTimeout(function(){
	                        $("#c_notify").html("&nbsp;");
	                    },1500);
	                 	$("#code").attr("src",$("#code").attr("src") + "?s" + new Date().getTime());
	                 	flag = false;
                 	}
	            });
	        }
	        return flag;
        }
        /**
         * Created by 朱振振 on 2015/12/7 0007.
         */
        function setTime(){ 
			var now=new Date(); //创建Date对象 
			var year=now.getFullYear(); //获取年份 
			var month=now.getMonth(); //获取月份 
			var date=now.getDate(); //获取日期 
			var day=now.getDay(); //获取星期 
			var hour=now.getHours(); //获取小时 
			var minu=now.getMinutes(); //获取分钟 
			var sec=now.getSeconds(); //获取秒钟 
			if(sec < 10){
				sec = "0"+sec;
			}
			if(minu<10){
				minu = "0"+minu;
			}
			month=month+1; 
			var arr_week=new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六"); 
			var week=arr_week[day]; //获取中文的星期 
			var time=year+"年"+month+"月"+date+"日   "+week+"  "+hour+":"+minu+":"+sec; //组合系统时间 
			$("#nowtime").text(time); //显示系统时间 
			} 
			window.onload=function(){ 
			window.setInterval("setTime()",1000); //实时获取并显示系统时间 
	} 