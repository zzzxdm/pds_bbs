package com.pds.vo;

import java.util.Date;

public class UserVo2 {
	private String username;
	private String password;
	private String password1;
	private String name;
	private String gender;
	private Date birthday;
	private String email;
	
	
	public UserVo2() {
		super();
	}
	public UserVo2(String username, String password, String password1,
			String name, String gender, Date birthday, String email) {
		super();
		this.username = username;
		this.password = password;
		this.password1 = password1;
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword1() {
		return password1;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
