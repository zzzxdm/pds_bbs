package com.pds.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.pds.dao.impl.UserDaoImpl;
import com.pds.pojo.User;
import com.pds.util.ConverUtil;
import com.pds.vo.UserVo3;

public class UpdateAction extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private UserVo3 m_user;//表示要更改信息的用户
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		try {
			//先获取用户原来的信息
			User user = (User) session.get("user");
			//把得到的新信息赋值过去
			User _user = (User) ConverUtil.converVo2PojoByInstance(m_user, user);
			System.out.println(_user.getQuestion());
			//更新用户信息
			new UserDaoImpl().updateUserInfo(_user);
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


	public UserVo3 getM_user() {
		return m_user;
	}


	public void setM_user(UserVo3 m_user) {
		this.m_user = m_user;
	}
}
