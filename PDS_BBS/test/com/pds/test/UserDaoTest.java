package com.pds.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.struts2.jasper.tagplugins.jstl.core.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;

import com.pds.dao.impl.UserDAO;
import com.pds.dao.impl.UserDaoImpl;
import com.pds.pojo.Article;
import com.pds.pojo.User;

public class UserDaoTest {
	private static Session session;
	@BeforeClass
	public static void createSession(){
		SessionFactory snFty = new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
		session = snFty.openSession();
//		session = HibernateSessionFactory.getSession();
	}
	@Test
	public void insertUser(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		User user = new User();
		user.setUsername("zzz");
		user.setPassword("xfwan");
		user.setGender("男");
		user.setEmail("771668756@qq.com");
		try {
			user.setBirthday(sdf.parse("1992-12-01"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setRegDate(new Timestamp(new Date().getTime()));
//		new UserDaoImpl().addUser(user);
		new UserDAO().save(user);
//		new UserDAO().merge(user);
//		new UserDAO().attachDirty(user);
//		new UserDAO().attachClean(user);
	}
	@Test
	public void updatetUser(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		User user = new User();
		user.setId(1);
		user.setUsername("zzz");
		user.setPassword("admin");
		user.setGender("男");
		user.setEmail("771668756@qq.com");
		try {
			user.setBirthday(sdf.parse("1992-12-01"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setRegDate(new Timestamp(new Date().getTime()));
		Transaction trs = session.beginTransaction();
//		session.save(user);
		session.update(user);
		trs.commit();
		session.close();
	}
	@Test
	public void userNameTest(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss",Locale.UK);
//		System.out.println(new UserDaoImpl().judgeUserName("lxq"));
		User user = (User) new UserDAO().findByUsername("zzz").get(0);
//		User user = new UserDAO().findById(1);
		System.out.println(sdf.format(user.getRegDate()));
	}
	@Test
	public void queryTest(){
		User user = (User) new UserDAO().findByUsername("spf").get(0);
	}
}
