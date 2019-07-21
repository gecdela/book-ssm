package com.mybatis.beans;

import java.io.Serializable;

public class Department implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Department(Integer id) {
		super();
		this.id = id;
	}
	private Integer id;
	private String deptName;
@Override
	public String toString() {
		return "Department [id=" + id + ", deptName=" + deptName + "]";
	}
	//	private List<Employee> emps;
//	public List<Employee> getEmps() {
//		return emps;
//	}
//	public void setEmps(List<Employee> emps) {
//		this.emps = emps;
//	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Department() {
		super();
	}
	public Department(Integer id, String deptName) {
		super();
		this.id = id;
		this.deptName = deptName;
	}
//	@Override
//	public String toString() {
//		return "Department [id=" + id + ", deptName=" + deptName + ", emps=" + emps + "]";
//	}


}
