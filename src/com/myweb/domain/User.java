package com.myweb.domain;

public class User {
	private static final long serialVersionUID = 1L;

	private int id;
	private String loginname;
	private String loginpsw;
	private String username;
	private String job;
	private String sex;
	private String qq;
	private String email;
	private String address;
	private String tel;
	private String examstate;
	private String createtime;
	private String isdel;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getLoginpsw() {
		return loginpsw;
	}

	public void setLoginpsw(String loginpsw) {
		this.loginpsw = loginpsw;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getExamstate() {
		return examstate;
	}

	public void setExamstate(String examstate) {
		this.examstate = examstate;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getIsdel() {
		return isdel;
	}

	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}
}
