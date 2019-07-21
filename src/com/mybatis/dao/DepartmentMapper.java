package com.mybatis.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mybatis.beans.Department;
import com.mybatis.beans.Employee;

@Repository
public interface DepartmentMapper {
	public List<Department> getDepts();
	public Department getDeptById(Integer id);
//	public Department getEmpstByDeptId(Integer id);
//	public Department getEmpstByDeptIdStep(Integer id);


}
