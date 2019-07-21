package com.mybatis.beans;

public class User {

	private String userName;
	private String password;
	private String per;
	private String tel;
	private String email;
	private int userId;
	private String head;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPer() {
		return per;
	}
	public void setPer(String per) {
		this.per = per;
	}
	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password
				+ ", per=" + per + ", userId=" + userId + ", head=" + head
				+ ", tel=" + tel + ", email=" + email + "]";
	}




}
