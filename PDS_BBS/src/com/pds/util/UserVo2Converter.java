package com.pds.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.pds.vo.UserVo2;

public class UserVo2Converter extends StrutsTypeConverter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("deprecation")
	@Override
	public Object convertFromString(Map context, String[] values, Class type) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		UserVo2 user = new UserVo2();
		String []userValues = values[0].split(",");
		user.setUsername(userValues[0]);
		user.setPassword(userValues[1]);
		user.setPassword1(userValues[2]);
		user.setName(userValues[3]);
		user.setGender(userValues[4]);
		System.err.println(sdf.format(userValues[5]));
		user.setBirthday(new Date(userValues[5]));
		user.setEmail(userValues[6]);
		return user;
	}

	@Override
	public String convertToString(Map context, Object _user) {
		// TODO Auto-generated method stub
		UserVo2 user = (UserVo2) _user;
		String str = "<"+user.getUsername()+","+user.getPassword()+","+user.getPassword1()+","
		+user.getName()+","+user.getGender()+","+user.getBirthday()+","+user.getEmail()+">";
		return str;
	}
}
