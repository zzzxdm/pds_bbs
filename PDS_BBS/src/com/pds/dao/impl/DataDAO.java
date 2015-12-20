package com.pds.dao.impl;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pds.pojo.Data;

/**
 * A data access object (DAO) providing persistence and search support for Data
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.pds.pojo.Data
 * @author MyEclipse Persistence Tools
 */
public class DataDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(DataDAO.class);
	// property constants
	public static final String FILENAME = "filename";
	public static final String FILETYPE = "filetype";
	public static final String FILEDESC = "filedesc";
	public static final String FILEPATH = "filepath";

	public void save(Data transientInstance) {
		log.debug("saving Data instance");
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

	public void delete(Data persistentInstance) {
		log.debug("deleting Data instance");
		try {
			getSession().delete(persistentInstance);
			getTransaction().commit();
			closeSession();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			rollbackTransaction();
			closeSession();
			throw re;
		}
	}

	public Data findById(java.lang.Integer id) {
		log.debug("getting Data instance with id: " + id);
		try {
			Data instance = (Data) getSession().get("com.pds.pojo.Data", id);
			closeSession();
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Data instance) {
		log.debug("finding Data instance by example");
		try {
			List results = getSession().createCriteria("com.pds.pojo.Data")
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
		log.debug("finding Data instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Data as model where model."
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

	public List findByFilename(Object filename) {
		return findByProperty(FILENAME, filename);
	}

	public List findByFiletype(Object filetype) {
		return findByProperty(FILETYPE, filetype);
	}

	public List findByFiledesc(Object filedesc) {
		return findByProperty(FILEDESC, filedesc);
	}

	public List findByFilepath(Object filepath) {
		return findByProperty(FILEPATH, filepath);
	}

	public List findAll() {
		log.debug("finding all Data instances");
		try {
			String queryString = "from Data";
			Query queryObject = getSession().createQuery(queryString);
			closeSession();
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Data merge(Data detachedInstance) {
		log.debug("merging Data instance");
		try {
			Data result = (Data) getSession().merge(detachedInstance);
			closeSession();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Data instance) {
		log.debug("attaching dirty Data instance");
		try {
			getSession().saveOrUpdate(instance);
			closeSession();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Data instance) {
		log.debug("attaching clean Data instance");
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