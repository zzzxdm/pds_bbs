package com.pds.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 * Data access interface for domain model
 * @author MyEclipse Persistence Tools
 */
public interface IBaseHibernateDAO {
	public Session getSession();
	public void closeSession();
	public Transaction getTransaction();
	public void commitTransaction();
	public void rollbackTransaction();
}