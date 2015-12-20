package com.pds.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pds.bean.Page;
import com.pds.pojo.Article;

/**
 * A data access object (DAO) providing persistence and search support for
 * Article entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.pds.pojo.Article
 * @author MyEclipse Persistence Tools
 */
public class ArticleDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(ArticleDAO.class);
	// property constants
	public static final String PID = "pid";
	public static final String TITLE = "title";
	public static final String CONTENT = "content";
	public static final String ISLEAF = "isleaf";

	public void save(Article transientInstance) {
		log.debug("saving Article instance");
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

	public void delete(Article persistentInstance) {
		log.debug("deleting Article instance");
		try {
			getSession().delete(persistentInstance);
			getTransaction().commit();
			closeSession();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			rollbackTransaction();
			throw re;
		}
	}

	/**
	 * 
	 *@Author 朱振振
	 *Name:
	 *Function:
	 * 这个方法需要用到spring 框架 先放这吧.......
	 * @param id
	 * @return
	 */
	/*public List<Article> queryByPage(final String username, final Page page) {  
        return this.getHibernateTemplate().executeFind(new HibernateCallback() {  
            public Object doInHibernate(Session session)  
                    throws HibernateException, SQLException {  
                Query query = session.createQuery("select art from Article art where art.username = ?");  
                //设置参数  
                query.setParameter(0, username);  
                //设置每页显示多少个，设置多大结果。  
                query.setMaxResults(page.getEveryPage());  
                //设置起点  
                query.setFirstResult(page.getBeginIndex());  
                return query.list();  
            }  
        }); 
	}*/
        
	public Integer countArticles(){
		log.debug("getting Articles Count");
		try {
			Query query = getSession().createQuery("select count(*) from Article");
			return query.list().size();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			log.error("get failed", e);
			e.printStackTrace();
		}
		return 0;
	}
	
	public List findByPage(final Page page) {
		log.debug("getting Articles By Page");
		try {
			Query query = getSession().createQuery("from Article");
			// 设置每页显示多少个，设置多大结果。
			query.setMaxResults(page.getEveryPage());
			// 设置起点
			query.setFirstResult(page.getBeginIndex());
			return query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			log.error("get failed", e);
			e.printStackTrace();
		}
		return null;
	}
	
	public List findByPageWithType(String articleType, final Page page) {
		log.debug("getting Articles By Page with articleType");
		try {
			Query query = getSession().createQuery("from Article where type = ?");
			//设置参数
			query.setParameter(0, articleType);
			// 设置每页显示多少个，设置多大结果。
			query.setMaxResults(page.getEveryPage());
			// 设置起点
			query.setFirstResult(page.getBeginIndex());
			return query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			log.error("get failed", e);
			e.printStackTrace();
		}
		return null;
	}
	
	public Article findById(java.lang.Integer id) {
		log.debug("getting Article instance with id: " + id);
		try {
			Article instance = (Article) getSession()
					.get("com.pds.pojo.Article", id);
			closeSession();
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Article instance) {
		log.debug("finding Article instance by example");
		try {
			List results = getSession().createCriteria("com.pds.pojo.Article")
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
		log.debug("finding Article instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Article as model where model."
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

	public List findByPid(Object pid) {
		return findByProperty(PID, pid);
	}

	public List findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	public List findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List findByIsleaf(Object isleaf) {
		return findByProperty(ISLEAF, isleaf);
	}

	public List findAll() {
		log.debug("finding all Article instances");
		try {
			String queryString = "from Article";
			Query queryObject = getSession().createQuery(queryString);
			closeSession();
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Article merge(Article detachedInstance) {
		log.debug("merging Article instance");
		try {
			Article result = (Article) getSession().merge(detachedInstance);
			closeSession();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Article instance) {
		log.debug("attaching dirty Article instance");
		try {
			getSession().saveOrUpdate(instance);
			closeSession();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Article instance) {
		log.debug("attaching clean Article instance");
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