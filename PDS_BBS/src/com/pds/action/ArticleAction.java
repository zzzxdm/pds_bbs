package com.pds.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.pds.dao.impl.ArticleDaoImpl;
import com.pds.dao.impl.CommentDAO;
import com.pds.dao.impl.UserDaoImpl;
import com.pds.pojo.Article;
import com.pds.pojo.Comment;
import com.pds.pojo.User;
import com.pds.util.ConverUtil;
import com.pds.vo.ArticleVo;

public class ArticleAction extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private String articleType;
	private ArticleVo articleVo;
	private int article_id;
	private int comment_pid;
	private String comment_content;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
	
	/**
	 * 
	 *@Author 朱振振
	 *Name:
	 *Function:
	 *发表文章
	 * @return
	 */
	public String writeArticle(){
		//变量数据传递
		Article _article = (Article) ConverUtil.converVo2PojoByClass1(articleVo, Article.class);
		_article.setWriteTime(new Timestamp(new Date().getTime()));
		_article.setUser((User)session.get("user"));
		//存数据库
		if(_article.getType().length()>0&&_article.getTitle().length()>0&&_article.getContent().length()>0){
			new ArticleDaoImpl().saveArticle(_article);
			return SUCCESS;
		}else{
			return ERROR;
		}
	}
	/**
	 * 
	 *@Author 朱振振
	 *Name:
	 *Function:
	 *评论文章
	 * @return
	 */
	public String CommentArticle(){
		Comment comment = new Comment();
		comment.setPid(comment_pid);
		comment.setContent(comment_content);
		comment.setUser((User)session.get("user"));
		comment.setWriteTime(new Timestamp(new Date().getTime()));
		if(comment_content.length() > 0&&comment.getPid() != null){
			new CommentDAO().save(comment);
			return SUCCESS;
		}else{
			return 	ERROR;
		}
	}
	
	public String getArticleType() {
		return articleType;
	}

	/**
	 * 
	 *@Author 朱振振
	 *Name:
	 *Function:
	 * 回去每篇文章的具体信息
	 * @param articleType
	 */
	public String getArticleDetail(){
		Article article = new Article();
		@SuppressWarnings("unchecked")
		List<Article> articles = (List<Article>) session.get(articleType);
		if(articles.size() > 0 && articles != null){
			for (Article art : articles) {
				if(art.getId() == article_id){
					article = art;
				}
			}
		}else{
			// session 中取不到  就去数据库拿值
			article = new ArticleDaoImpl().getArticleById(article_id);
		}
		User tmp_user = article.getUser();
		User user = new UserDaoImpl().getUserById(tmp_user.getId());
		article.setUser(user);	
		@SuppressWarnings("unchecked")
		List<Comment> comments = (List<Comment>) new CommentDAO().findByPid(article_id);
		if(comments.size() > 0){
			for (Comment comment : comments) {
				comment.setUser(new UserDaoImpl().getUserById(comment.getUser().getId()));
			}
		}
		session.put("article", article);
		session.put("comments", comments);
		return SUCCESS;
	}

	
	/**
	 * 刷新列表
	 */
	public String refresh(){
		String array[] = {"mood","baike","life","yuan","technology","experience"};
		for (String string : array) {
			session.remove(string);
		}
		return SUCCESS;
	}
	
	public ArticleVo getArticleVo() {
		return articleVo;
	}

	public void setArticleVo(ArticleVo articleVo) {
		this.articleVo = articleVo;
	}

	public int getArticle_id() {
		return article_id;
	}

	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}

	public void setArticleType(String articleType) {
		this.articleType = articleType;
	}

	public void setComment_pid(int comment_pid) {
		this.comment_pid = comment_pid;
	}
	
	public Integer getComment_pid(){
		return comment_pid;
	}

	public String getComment_content() {
		return comment_content;
	}

	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}
}
