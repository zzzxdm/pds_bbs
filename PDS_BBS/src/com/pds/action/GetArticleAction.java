package com.pds.action;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.pds.dao.impl.ArticleDaoImpl;
import com.pds.dao.impl.CommentDAO;
import com.pds.dao.impl.UserDaoImpl;
import com.pds.pojo.Article;
import com.pds.pojo.User;
import com.pds.util.ConverUtil;
import com.pds.util.JsonDateValueProcessor;
import com.pds.vo.ArticleVo1;

public class GetArticleAction extends ActionSupport implements SessionAware{
	
	private Map<String, Object> session;
	private String articleType;
	private static int pageSize = 10;
	private int pageCount = 1;
	private int currentPage = 1;
	
	@SuppressWarnings("unchecked")
	public String getArticles(){
		try {
			ServletActionContext.getRequest().setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HttpServletResponse res = ServletActionContext.getResponse();   
		res.setContentType("application/json;charset=utf-8");
        PrintWriter pw = null;
        try {
			pw = res.getWriter();
			//获取指定类型的文章
			List<Article> list = null;
			list = (List<Article>) session.get(articleType);
			if(list == null || list.size() == 0){
				//从数据库中
				 list = new ArticleDaoImpl().getArticlesByType(transType(articleType));
				 session.put(articleType, list);
			}
			/**
			 * 对数据按时间的先后顺序排序  时间早的在前面
			 */
			if(list != null){
				Collections.sort(list);
				int num = list.size() / pageSize;
				pageCount = list.size() % pageSize == 0 ? num : num + 1;
				Map<String, Object> articles = new LinkedHashMap<String, Object>();
				//这里 需要注意大小问题  可能会出现总条数小于pageSize的情况
				int t_pageSize = pageSize < list.size() ? pageSize : list.size();
				for(int i = 0;i< t_pageSize;i++){
					int index = (currentPage - 1) * pageSize + i;
					ArticleVo1 article = new ArticleVo1();
					article = (ArticleVo1) ConverUtil.converVo2PojoByInstance(list.get(index), article);
					User tmp_user = list.get(index).getUser();
					article.setAuthor_id(tmp_user.getId());
					User user = new UserDaoImpl().getUserById(tmp_user.getId());
					article.setImage(user.getPicture());
					article.setAuthor_username(user.getUsername());
					article.setReplyCount(new CommentDAO().findByPid(article.getId()).size());
					articles.put(article.getTitle(), article);
				}
				//存一个map和一些其他的数据
				Map<String, Object> data = new HashMap<String, Object>();
				data.put(articleType+"_pageCount", pageCount);
				data.put(articleType+"_articlesCount", list.size());
				/**
				 * 配置json
				 */
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
					public boolean apply(Object source, String name, Object value) {
						if (name.equals("articles") || name.equals("user")) {
							return true;
						} else {
							return false;
						}
					}
				});
				jsonConfig.registerJsonValueProcessor(Date.class , new JsonDateValueProcessor());
				jsonConfig.registerJsonValueProcessor(Timestamp.class , new JsonDateValueProcessor());
				/**
				 * 封装json对象
				 */
				JSONObject jobj1 = JSONObject.fromObject(articles, jsonConfig);
				JSONObject jobj2 = JSONObject.fromObject(data);
				/**
				 * 定义一个json数组 装json对象
				 */
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("data", jobj2);
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("articles", jobj1);
				/**
				 * 把json对象装入json目标对象中
				 */
				JSONObject jobj = new JSONObject();
				jobj.putAll(map2);
				jobj.putAll(map1);
				System.out.println(jobj.toString());
				pw.write(jobj.toString());
				pw.close();
			}
        } catch (Exception e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        }
        return SUCCESS;
	}

	/**
	 * 
	 *@Author 朱振振
	 *Name:
	 *Function:
	 *由于页面传来的中文会乱码 
	 *懒得搞了  反正不多 直接转化一下得了
	 * @param articleType
	 * @return
	 */
	public String transType(String articleType){
		switch(articleType){
			case "mood" : articleType = "心情驿站";break;
			case "baike" : articleType = "糗事百科";break;
			case "life" : articleType = "生活窍门";break;
			case "yuan" : articleType = "原创文章";break;
			case "technology" : articleType = "技术分享";break;
			case "experience" : articleType = "经验交流";break;
		}
		return articleType;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}

	public String getArticleType() {
		return articleType;
	}

	public void setArticleType(String articleType) {
		this.articleType = articleType;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
}
