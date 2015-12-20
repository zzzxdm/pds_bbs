package com.pds.dao.impl;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.pds.dao.UserDao;
import com.pds.pojo.User;
import com.pds.util.HibernateSessionFactory;

public class UserDaoImpl implements UserDao{
	private Session session;
	public UserDaoImpl(){
//		session = HibernateSessionFactory.getSession();
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		Transaction tan =null;
		try {
			session = HibernateSessionFactory.getSession();
			tan = session.beginTransaction();
			session.save(user);
			tan.commit();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			tan.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
	}

	@Override
	public void deleteUserById(Integer userid) {
		// TODO Auto-generated method stub
		Transaction tan =null;
		try {
			String hql = "delete from User where id = ?";
			session = HibernateSessionFactory.getSession();
			tan = session.beginTransaction();
			session.createQuery(hql)
			.setParameter(0, userid)
			.executeUpdate();
			tan.commit();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			tan.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
	}

	@Override
	public void deleteUserByUserName(String username) {
		// TODO Auto-generated method stub
		Transaction tan =null;
		try {
			String hql = "delete from User where username = ?";
			session = HibernateSessionFactory.getSession();
			tan = session.beginTransaction();
			session.createQuery(hql)
			.setParameter(0, username)
			.executeUpdate();
			tan.commit();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			tan.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
	}

	@Override
	public void updateUserInfo(User user) {
		// TODO Auto-generated method stub
		Transaction tan =null;
		try {
			session = HibernateSessionFactory.getSession();
			tan = session.beginTransaction();
			session.update(user);
			tan.commit();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			tan.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
	}

	@Override
	public Boolean judgeUserName(String username) {
		// TODO Auto-generated method stub
		try {
			String hql = "from User where username = ?";
			session = HibernateSessionFactory.getSession();
			@SuppressWarnings("unchecked")
			List<User> list = session.createQuery(hql)
			.setParameter(0, username)
			.list();
			if(!list.isEmpty()){
				return true;
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			session.close();
		}
		return false;
	}

	@Override
	public String getPasswordByUserName(String username) {
		// TODO Auto-generated method stub
		String password = null;
		try {
			String hql = "from User where username = ?";
			session = HibernateSessionFactory.getSession();
			@SuppressWarnings("unchecked")
			List<User> list = session.createQuery(hql)
			.setParameter(0, username)
			.list();
			if(!list.isEmpty()){
				password = list.get(0).getPassword();
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			session.close();
		}
		return password;
	}

	@Override
	public String getPasswordByUserId(Integer userid) {
		// TODO Auto-generated method stub
		String password = null;
		try {
			String hql = "from User where id = ?";
			session = HibernateSessionFactory.getSession();
			@SuppressWarnings("unchecked")
			List<User> list = session.createQuery(hql)
			.setParameter(0, userid)
			.list();
			if(!list.isEmpty()){
				password = list.get(0).getPassword();
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			session.close();
		}
		return password;
	}

	@Override
	public User getUserById(Integer userid) {
		// TODO Auto-generated method stub
		User user = null;
		try {
			String hql = "from User where id = ?";
			session = HibernateSessionFactory.getSession();
			@SuppressWarnings("unchecked")
			List<User> list = session.createQuery(hql)
			.setParameter(0, userid)
			.list();
			if(!list.isEmpty()){
				user = list.get(0);
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			session.close();
		}
		return user;
	}

	@Override
	public User getUserByUserName(String username) {
		// TODO Auto-generated method stub
		User user = null;
		try {
			String hql = "from User where username = ?";
			session = HibernateSessionFactory.getSession();
			@SuppressWarnings("unchecked")
			List<User> list = session.createQuery(hql)
			.setParameter(0, username)
			.list();
			if(!list.isEmpty()){
				user = list.get(0);
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			session.close();
		}
		return user;
	}

	@Override
	public String getAnswerByQuestion(String question) {
		// TODO Auto-generated method stub
		String answer = null;
		try {
			String hql = "from User where question = ?";
			session = HibernateSessionFactory.getSession();
			@SuppressWarnings("unchecked")
			List<User> list = session.createQuery(hql)
			.setParameter(0, question)
			.list();
			if(!list.isEmpty()){
				answer = list.get(0).getAnswer();
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			session.close();
		}
		return answer;
	}

	@Override
	public void setQuestionAndAnswerById(Integer userid, String question,
			String answer) {
		// TODO Auto-generated method stub
		Transaction trs = null;
		try {
			String hql = "update User set question = ? , answer = ? where id = ?";
			session = HibernateSessionFactory.getSession();
			trs = session.beginTransaction();
			session.createQuery(hql)
			.setParameter(0, question)
			.setParameter(1, answer)
			.setParameter(2, userid)
			.executeUpdate();
			trs.commit();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			trs.rollback();
		}finally{
			session.close();
		}
	}

	@Override
	public void setQuestionAndAnswerByUserName(String username,
			String question, String answer) {
		// TODO Auto-generated method stub
		Transaction trs = null;
		try {
			String hql = "update User set question = ? , answer = ? where username = ?";
			session = HibernateSessionFactory.getSession();
			trs = session.beginTransaction();
			session.createQuery(hql)
			.setParameter(0, question)
			.setParameter(1, answer)
			.setParameter(2, username)
			.executeUpdate();
			trs.commit();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			trs.rollback();
		}finally{
			session.close();
		}
	}

	@Override
	public void updateQuestionAndAnswerById(Integer userid, String question,
			String answer) {
		// TODO Auto-generated method stub
		Transaction trs = null;
		try {
			String hql = "update User set question = ? , answer = ? where id = ?";
			session = HibernateSessionFactory.getSession();
			trs = session.beginTransaction();
			session.createQuery(hql)
			.setParameter(0, question)
			.setParameter(1, answer)
			.setParameter(2, userid)
			.executeUpdate();
			trs.commit();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			trs.rollback();
		}finally{
			session.close();
		}
	}

	@Override
	public void updateQuestionAndAnswerByUserName(String username,
			String question, String answer) {
		// TODO Auto-generated method stub
		Transaction trs = null;
		try {
			String hql = "update User set question = ? , answer = ? where username = ?";
			session = HibernateSessionFactory.getSession();
			trs = session.beginTransaction();
			session.createQuery(hql)
			.setParameter(0, question)
			.setParameter(1, answer)
			.setParameter(2, username)
			.executeUpdate();
			trs.commit();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			trs.rollback();
		}finally{
			session.close();
		}
	}

	@Override
	public void updateUserImgById(Integer userid, String pic_path) {
		// TODO Auto-generated method stub
		Transaction trs = null;
		try {
			String hql = "update User set picture = ? where id = ?";
			session = HibernateSessionFactory.getSession();
			trs = session.beginTransaction();
			session.createQuery(hql)
			.setParameter(0, pic_path)
			.setParameter(1, userid)
			.executeUpdate();
			trs.commit();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			trs.rollback();
		}finally{
			session.close();
		}
	}

	@Override
	public void updateUserImgByUserName(String username, String pic_path) {
		// TODO Auto-generated method stub
		Transaction trs = null;
		try {
			String hql = "update User set picture = ? where username = ?";
			session = HibernateSessionFactory.getSession();
			trs = session.beginTransaction();
			session.createQuery(hql)
			.setParameter(0, pic_path)
			.setParameter(1, username)
			.executeUpdate();
			trs.commit();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			trs.rollback();
		}finally{
			session.close();
		}
	}

}
