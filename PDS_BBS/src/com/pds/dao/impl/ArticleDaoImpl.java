package com.pds.dao.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.pds.dao.ArticleDao;
import com.pds.pojo.Article;
import com.pds.pojo.User;
import com.pds.util.HibernateSessionFactory;

public class ArticleDaoImpl implements ArticleDao{

	private Session session;
	public ArticleDaoImpl(){
		
	}
	@Override
	public void saveArticle(Article article) {
		// TODO Auto-generated method stub
		Transaction trs = null;
		try {
			session = HibernateSessionFactory.getSession();
			trs = session.beginTransaction();
			session.save(article);
			trs.commit();
		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
			trs.rollback();
		}finally{
			session.close();
		}
	}

	@Override
	public void deleteArticle(Integer art_id) {
		// TODO Auto-generated method stub
		Transaction tan =null;
		try {
			String hql = "delete from Article where id = ?";
			session = HibernateSessionFactory.getSession();
			tan = session.beginTransaction();
			session.createQuery(hql)
			.setParameter(1, art_id)
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
	public void updateArticleInfo(Article article) {
		// TODO Auto-generated method stub
		Transaction trs = null;
		try {
			session = HibernateSessionFactory.getSession();
			trs = session.beginTransaction();
			session.update(article);
			trs.commit();
		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
			trs.rollback();
		}finally{
			session.close();
		}
	}

	@Override
	public void updateArticleTitleAndContentById(Integer article_id, String title, String content) {
		// TODO Auto-generated method stub
		Transaction trs = null;
		try {
			String hql = "update Article set title = ? , content = ? ,last_update_time = ? where id = ?";
			session = HibernateSessionFactory.getSession();
			trs = session.beginTransaction();
			session.createQuery(hql)
			.setParameter(0, title)
			.setParameter(1, content)
			.setParameter(2, new Timestamp(new Date().getTime()))
			.setParameter(3, article_id)
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
	public Article getArticleById(Integer art_id) {
		// TODO Auto-generated method stub
		Article article = null;
		try {
			String hql = "from Article where id = ?";
			session = HibernateSessionFactory.getSession();
			@SuppressWarnings("unchecked")
			List<Article> list = session.createQuery(hql)
			.setParameter(0, art_id)
			.list();
			if(!list.isEmpty()){
				article = list.get(0);
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			session.close();
		}
		return article;
	}
	@Override
	public List<Article> getArticlesByAuthorId(Integer author_id) {
		// TODO Auto-generated method stub
		List<Article> articles = null;
		try {
			String hql = "from Article where author_id = ?";
			session = HibernateSessionFactory.getSession();
			@SuppressWarnings("unchecked")
			List<Article> list = session.createQuery(hql)
			.setParameter(0, author_id)
			.list();
			if(!list.isEmpty()){
				articles = list;
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			session.close();
		}
		return articles;
	}
	@Override
	public List<Article> getArticlesByType(String articleType) {
		// TODO Auto-generated method stub
		List<Article> articles = null;
		try {
			String hql = "from Article where type = ?";
			session = HibernateSessionFactory.getSession();
			@SuppressWarnings("unchecked")
			List<Article> list = session.createQuery(hql)
			.setParameter(0, articleType)
			.list();
			if(!list.isEmpty()){
				articles = list;
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			session.close();
		}
		return articles;
	}

}
