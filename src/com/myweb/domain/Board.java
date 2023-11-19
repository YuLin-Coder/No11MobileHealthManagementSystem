package com.myweb.domain;

public class Board {
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String newsid;
	
	private String username;
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	private String state;
	
	private String createtime;
	
	private String content;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNewsid() {
		return newsid;
	}

	public void setNewsid(String newsid) {
		this.newsid = newsid;
	}
}
