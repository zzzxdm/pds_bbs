package com.pds.pojo;

import java.sql.Timestamp;

/**
 * Article entity. @author MyEclipse Persistence Tools
 */

public class Comment implements java.io.Serializable, Comparable<Comment>{

	// Fields

	private Integer id;
	private User user;
	private Integer pid;
//	private String title;
	private String content;
	private Timestamp writeTime;
	private Timestamp lastUpdateTime;
//	private Boolean isleaf = false;

	// Constructors

	/** default constructor */
	public Comment() {
	}

	/** minimal constructor */
	public Comment(User user, String title, String content) {
		this.user = user;
//		this.title = title;
		this.content = content;
	}

	/** full constructor */
	public Comment(User user, Integer pid, String title, String content,
			Timestamp writeTime, Timestamp lastUpdateTime, Boolean isleaf) {
		this.user = user;
		this.pid = pid;
//		this.title = title;
		this.content = content;
		this.writeTime = writeTime;
		this.lastUpdateTime = lastUpdateTime;
//		this.isleaf = isleaf;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	/*public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}*/

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getWriteTime() {
		return this.writeTime;
	}

	public void setWriteTime(Timestamp writeTime) {
		this.writeTime = writeTime;
	}
	
	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	@Override
	public int compareTo(Comment o) {
		// TODO Auto-generated method stub
		return o.getWriteTime().compareTo(this.writeTime);
	}

	/*public Boolean getIsleaf() {
		return this.isleaf;
	}

	public void setIsleaf(Boolean isleaf) {
		this.isleaf = isleaf;
	}*/

}