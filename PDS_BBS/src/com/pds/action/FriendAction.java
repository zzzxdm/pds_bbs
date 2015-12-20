package com.pds.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.pds.dao.impl.FriendDAO;
import com.pds.dao.impl.UserDAO;
import com.pds.pojo.Friend;
import com.pds.pojo.User;

public class FriendAction extends ActionSupport {
	
	private String friendId;
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	/**
	 * 关注后添加好友
	 */
	public String attention(){
		Integer fId = Integer.parseInt(friendId);
		FriendDAO friendDao = new FriendDAO();
		PrintWriter pw = null;
		try {
			HttpServletResponse rep = ServletActionContext.getResponse();
			rep.setContentType("text/html;charset=utf-8");
			pw = rep.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpSession session = ServletActionContext.getRequest().getSession();
		User user = (User) session.getAttribute("user");
		if(user == null){
			User temp_user = new User();
			temp_user.setId(fId);
			//我擦 原来这个findByProperty 所指的property是pojo 里面的啊 席八...
			@SuppressWarnings("unchecked")
			List<Friend> list = friendDao.findByProperty("userByFriendId", temp_user);
			if(list == null || list.isEmpty()){
				Friend friend = new Friend();
				User fUser = new UserDAO().findById(fId);
				friend.setUserByFriendId(fUser);
				friend.setFriendUname(fUser.getUsername());
				friend.setUserByUserId(user);
				friend.setUserUname(user.getUsername());
				friend.setAddTime(new Timestamp(new Date().getTime()));
				friendDao.save(friend);
				pw.write("true");
				pw.close();
			}else{
				pw.write("false");
				pw.close();
			}
		}else{
			pw.write("null");
			pw.close();
		}
		return SUCCESS;
	}

	public String getFriendId() {
		return friendId;
	}

	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}
}
