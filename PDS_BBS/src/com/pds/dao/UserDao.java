package com.pds.dao;

import com.pds.pojo.User;

public interface UserDao {
	/**
	 * 添加用户
	 */
	public void addUser(User user);
	
	/**
	 * 通过id删除用户
	 */
	public void deleteUserById(Integer userid);
	
	/**
	 * 通过用户名删除用户
	 */
	public void deleteUserByUserName(String username);
	
	/**
	 * 通过用户id更改用户信息
	 * 注意（此方法操作的对象必须在数据库中存在）
	 * 此方法慎用，容易导致字段丢失（一定要认真设置完每一个字段值）
	 */
	public void updateUserInfo(User user);
	
	/**
	 * 根据用户名判断用户是否存在
	 */	
	public Boolean judgeUserName(String username);
	
	/**
	 * 根据用户名获取用户密码
	 */
	public String getPasswordByUserName(String username);
	
	/**
	 * 根据用户id获取用户密码
	 */
	public String getPasswordByUserId(Integer userid);
	
	/**
	 * 根据用户id获取用户信息
	 */
	public User getUserById(Integer userid);
	
	/**
	 * 通过用户名获取用户信息
	 */
	public User getUserByUserName(String username);
	
	/**
	 * 根据用户密保问题获取用户密保答案
	 */
	public String getAnswerByQuestion(String question);
	
	/**
	 * 根据用户id为用户设置密保问题和答案
	 */
	public void setQuestionAndAnswerById(Integer userid, String question, String answer);
	
	/**
	 * 根据用户名为用户设置密保问题和答案
	 */
	public void setQuestionAndAnswerByUserName(String username, String question, String answer);
	
	/**
	 * 根据用户id更改用户的密保问题和答案
	 */
	public void updateQuestionAndAnswerById(Integer userid, String question, String answer);
	
	/**
	 * 根据用户名更改用户的密保问题和答案
	 */
	public void updateQuestionAndAnswerByUserName(String username, String question, String answer);
	
	/**
	 * 根据用户id更改用户头像
	 */
	public void updateUserImgById(Integer userid, String pic_path);
	
	/**
	 * 根据用户名更改用户头像
	 */
	public void updateUserImgByUserName(String username, String pic_path);
	
}
