package com.pds.dao.impl;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pds.pojo.Friend;

/**
 * A data access object (DAO) providing persistence and search support for
 * Friend entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.pds.pojo.Friend
 * @author MyEclipse Persistence Tools
 */
public class FriendDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(FriendDAO.class);
	// property constants
	public static final String USER_UNAME = "userUname";
	public static final String FRIEND_UNAME = "friendUname";

	public void save(Friend transientInstance) {
		log.debug("saving Friend instance");
		try {
			getSession().save(transientInstance);
			getTransaction().commit();
			closeSession();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			rollbackTransaction();
			throw re;
		}
	}

	public void delete(Friend persistentInstance) {
		log.debug("deleting Friend instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Friend findById(java.lang.Integer id) {
		log.debug("getting Friend instance with id: " + id);
		try {
			Friend instance = (Friend) getSession().get("com.pds.pojo.Friend",id);
			closeSession();
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Friend instance) {
		log.debug("finding Friend instance by example");
		try {
			List results = getSession().createCriteria("com.pds.pojo.Friend")
					.add(Example.create(instance)).list();
			closeSession();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Friend instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Friend as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			closeSession();
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUserUname(Object userUname) {
		return findByProperty(USER_UNAME, userUname);
	}

	public List findByFriendUname(Object friendUname) {
		return findByProperty(FRIEND_UNAME, friendUname);
	}

	public List findAll() {
		log.debug("finding all Friend instances");
		try {
			String queryString = "from Friend";
			Query queryObject = getSession().createQuery(queryString);
			closeSession();
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Friend merge(Friend detachedInstance) {
		log.debug("merging Friend instance");
		try {
			Friend result = (Friend) getSession().merge(detachedInstance);
			closeSession();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Friend instance) {
		log.debug("attaching dirty Friend instance");
		try {
			getSession().saveOrUpdate(instance);
			closeSession();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Friend instance) {
		log.debug("attaching clean Friend instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			closeSession();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}