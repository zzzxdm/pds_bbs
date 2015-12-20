package com.pds.vo;

import java.sql.Timestamp;

public class ArticleVo {

	private String title;
	private String type;
	private String content;
	private Timestamp writeTime;
	
	
	public ArticleVo() {
		super();
	}
	public ArticleVo(String title, String type, String content,
			Timestamp writeTime) {
		super();
		this.title = title;
		this.type = type;
		this.content = content;
		this.writeTime = writeTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getWriteTime() {
		return writeTime;
	}
	public void setWriteTime(Timestamp writeTime) {
		this.writeTime = writeTime;
	}
}
