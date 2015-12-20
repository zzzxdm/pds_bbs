package com.pds.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;

import com.pds.bean.Page;
import com.pds.dao.impl.ArticleDAO;
import com.pds.dao.impl.ArticleDaoImpl;
import com.pds.pojo.Article;
import com.pds.pojo.User;
import com.pds.util.PageUtil;

public class ArticleDaoTest {

	private static Session session;
	@BeforeClass
	public static void createSession(){
		SessionFactory snFty = new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
		session = snFty.openSession();
//		session = HibernateSessionFactory.getSession();
	}
	@Test
	public void insertArticle(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		User user = new User();
		user.setId(1);
		Article article = new Article();
		article.setUser(user);
		article.setContent("This is content!");
		article.setTitle("This is a title!");
		article.setWriteTime(new Timestamp(new Date().getTime()));
		new ArticleDaoImpl().saveArticle(article);
	}
	@Test
	public void deleteArticle(){
		String hql = "delete from Article where id = 6";
		Transaction trs = session.beginTransaction();
		session.createQuery(hql).executeUpdate();
		trs.commit();
	}
	@Test
	public void queryArticle(){
		/*String hql = "from Article";
		List<Article> list =session.createQuery(hql).list();
		for (Article art : list) {
			System.out.println(art.getId()+"\n"+art.getPid()+"\n"+art.getTitle()+"\n"+art.getContent());
		}*/
		Article art = (Article) session.load(Article.class, 2);
		System.out.println(art.getId()+"\n"+"\n"+art.getTitle()+"\n"+art.getContent());
	}
	@Test
	public void updateArticle(){
		/*String hql = "update Article a set a.title = 'update test' , a.content = 'content update test' where a.id = 1";
//		String hql = "update Article set title = 'update test2' where id = 4";
		Transaction trs = session.beginTransaction();
		session.createQuery(hql).executeUpdate();
		trs.commit();*/
		User user = new User();
		user.setId(1);
		Article art = new Article();
		art.setId(1);
		art.setUser(user);
		art.setTitle("update title test!");
		art.setContent("update content test!");
		new ArticleDaoImpl().updateArticleInfo(art);
	}
	@Test
	public void queryTest(){
		String date = "2015-12-16";
		String hql = "from Article where write_time like ?";
		List<Article> list = session.createQuery(hql)
				.setParameter(0, date+"%")
				.list();
		if(list.size()>0){
			for (Article article : list) {
				System.out.println(article.getContent());
			}
		}
	}
	@Test
	public void queryByPageTest(){
		ArticleDAO artilecDao = new ArticleDAO();
		Page page = PageUtil.createPage(2, artilecDao.countArticles(), 1);
		/*List<Article> list = artilecDao.findByPage(page);
		for (Article article : list) {
			System.out.println(article.getTitle()+article.getWriteTime());
		}*/
		List<Article> list = artilecDao.findByPageWithType("技术分享",page);
		for (Article article : list) {
			System.out.println(article.getTitle()+article.getWriteTime());
		}
	}
}
