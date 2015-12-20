package com.pds.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.pds.dao.impl.UserDaoImpl;
import com.pds.pojo.User;
import com.pds.vo.UserVo1;

public class LoginAction extends ActionSupport implements SessionAware{
	
	private UserVo1 userVo;
	private Map<String, Object> session;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String username = userVo.getName();
		String password = null;
		//如果用户注册完就来登录
		User user = null,temp_user = (User) session.get("reg_user");
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		if(temp_user != null){
			//先看看当前要登录的用户是不是在session中 有的话直接取出密码  不用访问数据库
			if(username.equals(temp_user.getUsername())){
				password = temp_user.getPassword();
				user = temp_user;
			}
		}else{
				user = userDaoImpl.getUserByUserName(username);
				if(user != null){
					password = user.getPassword();
				}
		}
		if(userVo.getPassword().equals(password)){
			/*if(temp_user != null){
				//先看看当前要登录的用户是不是在session中 有的话直接取出密码  不用访问数据库
				if(!username.equals(temp_user.getUsername())){
					//这是没办法 必须从数据库拿user了
					user = userDaoImpl.getUserByUserName(username);
				}
			}*/
			//把用户的信息存起来  方便使用
			session.put("user", user);
			//把校验时存的数据干掉
			session.remove("uname");
			session.remove("vCode");
			session.remove(user.getUsername());
			session.remove(user.getUsername()+":");
			return SUCCESS;
		}else{
			return ERROR;
		}
	}

	public UserVo1 getUserVo() {
		return userVo;
	}

	public void setUserVo(UserVo1 uservo) {
		this.userVo = uservo;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}
}
