package com.pds.listener;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ApplicationAware;

public class CountOnlineListener implements HttpSessionListener{

	private int count = 0;
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		// TODO Auto-generated method stub
		System.out.println("Session 创建了.....");
		ServletContext sc = event.getSession().getServletContext();
		count = sc.getAttribute("online") == null ? 0 : (Integer)sc.getAttribute("online");
		sc.setAttribute("online", count + 1);
		System.out.println(sc.getAttribute("online"));
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		// TODO Auto-generated method stub
		System.out.println("Session 销毁了.....");
		ServletContext sc = event.getSession().getServletContext();
		sc.setAttribute("online", (Integer)sc.getAttribute("online")-1);
		System.out.println(sc.getAttribute("online"));
	}
	
}
