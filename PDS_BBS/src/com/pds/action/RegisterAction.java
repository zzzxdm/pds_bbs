package com.pds.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.pds.dao.impl.UserDaoImpl;
import com.pds.pojo.User;
import com.pds.util.ConverUtil;
import com.pds.vo.UserVo2;

public class RegisterAction extends ActionSupport implements SessionAware{

	private Map<String,Object> session;
	private UserVo2 user;
	
	@SuppressWarnings("deprecation")
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		try {
			User _user = (User) ConverUtil.converVo2PojoByClass(user, User.class);
			/**
			 * 格式化用户的生日
			 * 这个是我想多了 完全不需要格式化 只是取出来的值需要显示第格式化一下
			 */
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			_user.setBirthday(sdf.parse(sdf.format(new Date(user.getBirthday().toString()))));
			//设置用户的注册时间
			_user.setRegDate(new Timestamp(new Date().getTime()));
			//存数据库
			new UserDaoImpl().addUser(_user);
			//存起来在登录时可以做验证
			session.put("reg_user", _user);
			return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}
	public UserVo2 getUser() {
		return user;
	}
	public void setUser(UserVo2 user) {
		this.user = user;
	}
	
}
