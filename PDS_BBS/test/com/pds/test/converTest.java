package com.pds.test;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.beans.PropertyDescriptor;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Test;

import com.pds.pojo.User;
import com.pds.util.ConverUtil;
import com.pds.vo.UserVo2;

public class converTest {

	@Test
	public void test1(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		UserVo2 user = new UserVo2();
		user.setUsername("zzz");
		user.setPassword("admin");
		user.setName("朱振振");
		user.setGender("男");
		try {
			user.setBirthday(sdf.parse("1992-12-01"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setEmail("771668756@qq.com");
		User usr = new User();
		ConverUtil.converVo2PojoByInstance1(user, usr);
		System.out.println(usr.getName());
		System.out.println(usr.getGender());
		User usr1 = (User) ConverUtil.converVo2PojoByClass1(user, User.class);
		System.out.println(usr1.getBirthday());
		System.out.println(usr1.getUsername());
	}
	@Test
	public void test2(){
		String str[] = new String[2];
		str[0] = "test";
		str[1] = "haha";
		String str1[] = new String[3];
		str1[0] = "test";
		str1[1] = "haha";
		str1[2] = "fuck";
		List<String> list = ConverUtil.intersection(str, str1);
		for (Object string : list) {
			System.out.println(string);
		}
	}
	@Test
	public void test3(){
		Field []Fields = null;
		Field []source_instanceFields = UserVo2.class.getDeclaredFields();
		for (Field field : source_instanceFields) {
//			System.out.println(field);
		}
		Field []target_instanceFields = User.class.getDeclaredFields();
		for (Field field : target_instanceFields) {
//			System.err.println(field);
		}
		List<Field> list = ConverUtil.intersection(source_instanceFields, target_instanceFields);
		System.out.println(list.size());
		for (Field field : list) {
			System.out.println(field);
		}
	}
	
	@Test
	public void test4(){
		String str = "Tue Dec 08 00:00:00 CST 2015";
		String s = "Tue Dec 01 00:00:00 CST 1992";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			System.out.println(sdf.format(new Date(str)));
			System.out.println(sdf.format(new Date(s)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void test5(){
		User user = new User();
		Field []fields = user.getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				if(field.getType().getName().equals("java.util.Date")){
					BeanUtils.setProperty(user, field.getName(), new Date());
				}else if(field.getType().getName().equals("java.sql.Timestamp")){
					BeanUtils.setProperty(user, field.getName(), new Timestamp(new Date().getTime()));
				}else if(field.getType().getName().equals("java.util.Set")){
					BeanUtils.setProperty(user, field.getName(), new HashSet());
				}else{
					BeanUtils.setProperty(user, field.getName(), field.getName());
				}
				System.err.println(BeanUtils.getProperty(user, field.getName()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}
	@Test
	public void test6(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		UserVo2 user = new UserVo2();
		user.setUsername("zzz");
		user.setPassword("admin");
		user.setName("朱振振");
		user.setGender("男");
		try {
			user.setBirthday(sdf.parse("1992-12-01"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setEmail("771668756@qq.com");
		User _user = new User();
		try {
			/*Map<String, String> map = BeanUtils.describe(user);
			for (Map.Entry<String, String> entry : map.entrySet()) {
				System.out.println(entry.getKey()+"\t"+entry.getValue());
			}*/
			BeanInfo beaninfo = Introspector.getBeanInfo(user.getClass());
			PropertyDescriptor []proDe = (PropertyDescriptor[]) beaninfo.getPropertyDescriptors();
			Field []fields = user.getClass().getDeclaredFields();
			/*for (PropertyDescriptor p : proDe) {
				p = new PropertyDescriptor("username",UserVo2.class);
				Method get = p.getReadMethod();
				Method set = p.getWriteMethod();
				System.out.println(get.invoke(user));
			}*/
			for(int i =0;i<fields.length;i++){
				proDe[i] = new PropertyDescriptor(fields[i].getName(),UserVo2.class);
				Method get = proDe[i].getReadMethod();
				System.out.println(get.invoke(user));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
