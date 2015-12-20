package com.pds.dao;

import java.util.List;

import com.pds.pojo.Article;

	public interface ArticleDao {
	/**
	 * 存储文章
	 */
	public void saveArticle(Article article);
	
	/**
	 * 通过文章id删除文章
	 */
	public void deleteArticle(Integer art_id);
	
	/**
	 * 修改文章信息(适合更改字段较多时使用)
	 * 注意：要操作的对象必须已经在数据库中存在，否则发生异常
	 * 此方法慎用，容易导致字段丢失（一定要认真设置完每一个字段值）
	 */
	public void updateArticleInfo(Article article);
	
	/**
	 * 根据文章id更改文章标题和内容
	 */
	public void updateArticleTitleAndContentById(Integer article_id, String title, String content);
	
	/**
	 * 根据文章id获取文章信息
	 */
	public Article getArticleById(Integer art_id);
	
	/**
	 * 根据文章作者id获取该作者发表的所有文章
	 */
	public List<Article> getArticlesByAuthorId(Integer author_id);
	/**
	 * 通过类型过去文章
	 */
	public List<Article> getArticlesByType(String articleType);
}
