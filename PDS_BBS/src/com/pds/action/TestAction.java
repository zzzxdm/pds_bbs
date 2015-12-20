package com.pds.action;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.pds.dao.impl.UserDAO;
import com.pds.pojo.User;
import com.pds.util.JsonDateValueProcessor;

public class TestAction extends ActionSupport {

	private String first;
	private String operator;
	private String second;
	private String result;
	private int rel;
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub	
		HttpServletResponse res = ServletActionContext.getResponse();   
        res.setContentType("application/json;charset=utf-8");
        PrintWriter pw = res.getWriter();
		/*switch(operator){
			case "+":rel = Integer.parseInt(first)+Integer.parseInt(second);break;
			case "-":rel = Integer.parseInt(first)-Integer.parseInt(second);break;
			case "*":rel = Integer.parseInt(first)*Integer.parseInt(second);break;
			case "/":rel = Integer.parseInt(first)/Integer.parseInt(second);break;
			default: rel = Integer.parseInt(first)+Integer.parseInt(second);break;
		}
		result = rel + "";*/
		JSONObject jobj = new JSONObject();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("first",1);
		map.put("second",2);
		map.put("result",3);
		User user = (User) new UserDAO().findByUsername("zzz").get(0);
		map.put("user", user);
		JsonConfig jsonConfig = new JsonConfig();
		//birthday和regDate 这两个值得处理一下再存进去 否则到前台解析很麻烦
		jsonConfig.registerJsonValueProcessor(Date.class , new JsonDateValueProcessor());
		jsonConfig.registerJsonValueProcessor(Timestamp.class , new JsonDateValueProcessor());
		jobj = JSONObject.fromObject(map, jsonConfig);
//		System.out.println(jobj.toString());
		try {
			pw.write(jobj.toString());
			pw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.execute();
	}
	
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getSecond() {
		return second;
	}
	public void setSecond(String second) {
		this.second = second;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}	
}
