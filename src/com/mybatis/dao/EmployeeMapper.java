package com.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.mybatis.beans.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.mybatis.beans.Employee;
import com.mybatis.beans.MapParam;

/**����ӿڱ��
 * �൱��xxxMapper.xml��ʵ��
 * @author lenovo
 *
 */
@Repository
public interface EmployeeMapper {

	/**
	 * @param id
	 * @return
	 */
	public Employee getEmpById(Integer id);
	public void addEmp(Employee employee);
	public boolean updateEmp(Employee employee);
	public void deleteEmpById(Integer id);
//	public Employee queryForOne(@Param("id")Integer id,@Param("lastName")String lastName);
//	public Map<String, Object> getByIdReturnMap(Integer id);
//	@MapKey("id")
//	public Map<Integer, Employee> getEmployeeLikeReturnMap(String lastName);
//	public List<Employee> queryForList(@Param("lastName")String lastName);
	public List<Employee> getEmps();
	public List<Employee> getEmpsByPage(Integer pageIndex, Integer size);
	public int getLength();
	public List<Employee> query(String condition);
/*	MapParam params = new MapParam("deptName","count",MapParam.ValueClass.INTEGER.getCode());
	Map<String,Integer> getDatass(MapParam params);*/
	public List<Map<String, Object>> getDatas();
	public List<Map<String, Object>> getPer();

    public User login(@Param("username") String username, @Param("password")String password);

	public User getUserInfo(@Param("username") String username, @Param("password")String password);
}
