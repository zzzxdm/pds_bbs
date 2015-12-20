package com.pds.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.pds.util.HibernateSessionFactory;


/**
 * Data access object (DAO) for domain model
 * @author MyEclipse Persistence Tools
 */
public class BaseHibernateDAO implements IBaseHibernateDAO {
	public Session getSession() {
		return HibernateSessionFactory.getSession();
	}
	
	@Override
	public void closeSession() {
		// TODO Auto-generated method stub
//		HibernateSessionFactory.closeSession();
	}

	@Override
	public Transaction getTransaction() {
		// TODO Auto-generated method stub
		return HibernateSessionFactory.getSession().beginTransaction();
	}


	@Override
	public void commitTransaction() {
		// TODO Auto-generated method stub
		HibernateSessionFactory.getSession().beginTransaction().commit();
	}

	@Override
	public void rollbackTransaction() {
		// TODO Auto-generated method stub
		HibernateSessionFactory.getSession().beginTransaction().rollback();
	}
}