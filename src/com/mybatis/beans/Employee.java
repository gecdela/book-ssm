package com.mybatis.beans;

import java.io.Serializable;

public class Employee implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String lastName;
	private int sex;
	private String email;
	private Department dept;


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}


	@Override
	public String toString() {
		return "Employee [id=" + id + ", lastName=" + lastName + ", sex=" + sex + ", email=" + email + ", dept=" + dept
				+ "]";
	}
	public Employee(Integer id, String lastName, int sex, String email, Department dept) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.sex = sex;
		this.email = email;
		this.dept = dept;
	}
	public Department getDept() {
		return dept;
	}
	public void setDept(Department dept) {
		this.dept = dept;
	}
	public Employee(Integer id, String lastName, int sex, String email) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.sex = sex;
		this.email = email;
	}
	public Employee() {
		super();
	}

}
