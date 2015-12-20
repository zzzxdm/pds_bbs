package com.pds.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.pds.dao.impl.UserDaoImpl;
import com.pds.pojo.User;

public class CheckAction extends ActionSupport implements SessionAware{

	private String userName;
	private String password;
	private String vcode;
	private Map<String ,Object> session;

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String passWord) {
		this.password = passWord;
	}
	public String getVcode() {
		return vcode;
	}
	public void setVcode(String vcode) {
		this.vcode = vcode;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
	//校验用户名
	public String checkUserName(){
        ActionContext AC = ActionContext.getContext();   
        HttpServletResponse res = (HttpServletResponse)AC.get(ServletActionContext.HTTP_RESPONSE);   
        res.setContentType("text/html;charset=UTF-8");    
		try {
			PrintWriter pw = res.getWriter();
			Boolean flag = false;
			if(!userName.equals(session.get("uname"))){
				flag = new UserDaoImpl().judgeUserName(userName);
				session.put("uname", userName);
				session.put(userName,flag);
			}else{
				flag = (Boolean) session.get(userName);
			}
	        if(flag == true){   
	            pw.write("用户名已经存在!"); 
//	            session.remove(userName);
	        }else{   
	            pw.write("可以使用!");   
	        }   
	        pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		return SUCCESS;
	}
	
	//校验密码
	public String checkPassword(){
		ActionContext AC = ActionContext.getContext();   
        HttpServletResponse res = (HttpServletResponse)AC.get(ServletActionContext.HTTP_RESPONSE);   
        res.setContentType("text/html;charset=UTF-8");    
		try {
			String currentPwd = (String) session.get(userName+":");
			if(currentPwd == null){
				//先把用户的密码查出来
				User user = new UserDaoImpl().getUserByUserName(userName);
				currentPwd = user.getPassword();
				//把用户名和对应得密码存起来
				session.put(userName+":", currentPwd);
			}
			PrintWriter pw = res.getWriter();
	        if(currentPwd.equals(password)){   
	            pw.write("密码正确!");   
	            session.remove(userName);
	        }else{   
	            pw.write("密码错误!");   
	        }   
	        pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		return SUCCESS;
	}
	//校验验证码
	public String checkValidateCode(){
        HttpServletResponse res = ServletActionContext.getResponse();
        HttpSession session = ServletActionContext.getRequest().getSession();
        String str = (String) session.getAttribute("vCode");
        res.setContentType("text/html;charset=UTF-8");    
		try {
			PrintWriter pw = res.getWriter();
	        if(str.equals(vcode)){
	        	pw.write("输入正确");
	        }else{
	        	pw.write("验证码错误,请重新输入!");
	        }
	        pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		return SUCCESS;
	}
	@Override
	public void setSession(Map<String, Object> arg) {
		// TODO Auto-generated method stub
		this.session = arg;
	}
}
