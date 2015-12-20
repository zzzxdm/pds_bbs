package com.pds.test;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.json.JsonArray;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import com.pds.dao.impl.ArticleDAO;
import com.pds.dao.impl.UserDAO;
import com.pds.pojo.Article;
import com.pds.pojo.User;
import com.pds.util.ConverUtil;
import com.pds.util.JsonDateValueProcessor;
import com.pds.vo.ArticleVo1;

public class Test {

	@org.junit.Test
	public void test1(){
		String str = "D:\\Program Files\\Apache Software Foundation\\Tomcat 7.0\\webapps\\PDS_BBS\\upload\\zzz\\image\\8424e117-f9d0-478f-a406-802417721445.jpg";
		System.out.println(str.indexOf("upload"));
		System.out.println(str.substring(str.indexOf("upload")));
	}
	@org.junit.Test
	public void test2(){
//		List<Article> list = new ArticleDaoImpl().getArticlesByType("心情驿站");
		List<Article> list = new ArticleDAO().findAll();
		int pageSize = 10;
		int pageCount = 1;
		int currentPage = 1;
		try {
			/**
			 * 对数据按时间的先后顺序排序  时间早的在前面
			 */
			Collections.sort(list);
			int num = list.size() / pageSize;
			pageCount = list.size() % pageSize == 0 ? num : num + 1;
			Map<String, Object> articles = new LinkedHashMap<String, Object>();
			//存一个map和一些其他的数据
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("pageSize", pageSize);
			data.put("pageCount", pageCount);
			data.put("articlesCount", list.size());
			//这里 需要注意大小问题
			pageSize = pageSize < list.size() ? pageSize : list.size();
			for(int i = 0;i< pageSize;i++){
				int index = (currentPage - 1) * pageSize + i;
				ArticleVo1 article = new ArticleVo1();
				article = (ArticleVo1) ConverUtil.converVo2PojoByInstance(list.get(index), article);
				article.setAuthor_id(list.get(index).getUser().getId());
				articles.put(article.getTitle(), article);
			}
			JSONObject jobj1 = new JSONObject();
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
			jobj1 = JSONObject.fromObject(articles, jsonConfig);
			JSONObject jobj2 = new JSONObject();
			jobj2 = JSONObject.fromObject(data);
			JSONArray jarray = new JSONArray();
			jarray.add(0,jobj2);
			jarray.add(1,jobj1);
			System.out.println(jarray.toString());
		}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	}
	@org.junit.Test
	public void test3(){
		List<Article> list = new ArticleDAO().findAll();
		int pageSize = 10;
		int pageCount = 1;
		int currentPage = 1;
		try {
			/**
			 * 对数据按时间的先后顺序排序  时间早的在前面
			 */
			Collections.sort(list);
			int num = list.size() / pageSize;
			pageCount = list.size() % pageSize == 0 ? num : num + 1;
			Map<String, Object> articles = new LinkedHashMap<String, Object>();
			//这里 需要注意大小问题
			pageSize = pageSize < list.size() ? pageSize : list.size();
			for(int i = 0;i< pageSize;i++){
				int index = (currentPage - 1) * pageSize + i;
				ArticleVo1 article = new ArticleVo1();
				article = (ArticleVo1) ConverUtil.converVo2PojoByInstance(list.get(index), article);
				article.setAuthor_id(list.get(index).getUser().getId());
				articles.put(article.getTitle(), article);
			}
			//存一个map和一些其他的数据
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("_pageSize", pageSize);
			data.put("_pageCount", pageCount);
			data.put("_articlesCount", list.size());
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
			jobj.putAll(map1);
			jobj.putAll(map2);
			System.out.println(jobj.toString());
		}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
		}
	}
	@org.junit.Test
	public void connect(){
		String str = "<li class="+"\"list-group-item\""+" id="+"\"value.id\""+" style="+"\"background-color:#dff0d8\">"+"\n"+
                        "<div"+ " class="+"\"article\""+">"+"\n"+
                            "<div"+" class="+"\"picture\""+">"+"\n"+
                                "<img"+" src="+"\"image/head.png\""+" style="+"\"width:"+" 40px;"+"height:"+" 40px;border-radius: 20px\">"+"\n"+
                            "</div>"+"\n"+
                            "<div"+" class="+"\"title\""+">"+"\n"+
                                "<nobr>"+"\n"+
                                    "<h4>"+"\n"+
                                        "<a"+" href="+"\"articleDetail\">"+"1.发表一表文章试试发表一表文章试试发表一表文章试试发表一表文章试试发表一表文章试试发表一表文章试试发表一表文章试试发表一表文章试试"+"</a>"+"\n"+
                                    "</h4>"+"\n"+
                                "</nobr>"+"\n"+
                            "</div>"+"\n"+
                            "<div"+" class="+"\"info\""+">"+"\n"+
                                "<div"+" class="+"\"info-left\""+">"+"\n"+
                                    "<h5"+" style="+"\"text-align: left;margin-left: 55px\""+">"+" <span>本文由：</span>"+"  zzz "+"<span>"+"发表于："+" 2015年12月12日20:12:10"+"</span>"+"</h5>"+"\n"+
                                "</div>"+"\n"+
                                "<div"+" class="+"\"info-right\""+">"+"\n"+
                                    "<h5"+" style="+"\"text-align: right;margin-right: 30px\""+">"+"<span>回复:"+"</span>"+"<span>("+12+")</span></h5>"+"\n"+
                                "</div>"+"\n"+
                            "</div>"+"\n"+
                        "</div>"+"\n"+
                    "</li>";
		System.out.println(str);
	}
	@org.junit.Test
	public void connect1(){
		String str1 = "<li><a href=\"\">"+"</a></li>";
		String str2 = "<li><a href=\"\""+">&raquo;</a></li>";
		String str3 = "<p>总条数:<span>"+100+"</span>条 "+" 当前页数：<span>"+2+"</span> 页</p>";
		System.out.println(str1);
		System.out.println(str2);
		System.out.println(str3);
	}
}

