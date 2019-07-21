package com.mybatis.service;

import java.util.List;
import java.util.Map;

import com.mybatis.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybatis.beans.Department;
import com.mybatis.beans.Employee;
import com.mybatis.dao.DepartmentMapper;
import com.mybatis.dao.EmployeeMapper;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeMapper employeeMapper;

	@Autowired
	private DepartmentMapper deptMapper;
	public List<Employee> getEmps(){
		return employeeMapper.getEmps();
	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub
		employeeMapper.deleteEmpById(id);

	}

	public void update(Employee employee)
	{
		employeeMapper.updateEmp(employee);
	}
	public void save(Employee employee) {
		// TODO Auto-generated method stub
		Department dept = deptMapper.getDeptById(employee.getDept().getId());
		employee.setDept(dept);
		employeeMapper.addEmp(employee);

	}

	public Employee getEmpById(Integer id) {
		// TODO Auto-generated method stub
		return employeeMapper.getEmpById(id);
	}

	public List<Employee> getEmpsByPage(Integer pageIndex,Integer size) {
		// TODO Auto-generated method stub
		return employeeMapper.getEmpsByPage(pageIndex,size);
	}
	public int getLength()
	{
		return employeeMapper.getLength();
	}

	public List<Employee>  query(String condition) {
		// TODO Auto-generated method stub
		return employeeMapper.query(condition);
	}

	public List<Map<String, Object>> getDatas() {
		// TODO Auto-generated method stub
		return employeeMapper.getDatas();
	}
	public List<Map<String, Object>> getPer() {
		// TODO Auto-generated method stub
		return employeeMapper.getPer();
	}

	public User login(User user) {
		User u = employeeMapper.login(user.getUserName(),user.getPassword());
		return u;
	}
	public User getUserInfo(User user) {
		User u = employeeMapper.getUserInfo(user.getUserName(),user.getPassword());
		return u;
	}
}
