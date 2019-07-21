package com.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mybatis.beans.Department;
import com.mybatis.dao.DepartmentMapper;

@Repository
public class DepartmentService {

	@Autowired
	DepartmentMapper deptMapper;
	public List<Department> getDepartments() {
		// TODO Auto-generated method stub
		return deptMapper.getDepts();
	}
	public Department getDeptById(Integer did) {
		// TODO Auto-generated method stub
		return deptMapper.getDeptById(did);
	}

}
