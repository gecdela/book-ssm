package com.mybatis.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mybatis.utils.Jackson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mybatis.beans.Department;
import com.mybatis.beans.Employee;
import com.mybatis.service.DepartmentService;
import com.mybatis.service.EmployeeService;
import com.mybatis.service.ExcleImpl;
/*import com.servlet.JSONObject;*/

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private DepartmentService deptService;

	@RequestMapping("/emps")
	public String getEmps(Map<String,Object> map)
	{
		List<Employee> emps = employeeService.getEmps();
		map.put("employees", emps);
		return "list";
	}

	@RequestMapping(value="/emps/init")
	@ResponseBody
	public Map<String, Object> init() {
		List<Employee> emp_list = employeeService.getEmps();
		List<Department> dept_list = deptService.getDepartments();
		System.out.println(emp_list.size());
		System.out.println(dept_list.size());
		Map<String,Object> map = new HashMap<>();
		map.put("count", emp_list.size());
		map.put("depts", dept_list);
		return map;
	}

	@ResponseBody
	@RequestMapping("/emps/getLength")
	public int getLength() {
		int length = employeeService.getLength();
		return length;
	}
	@ResponseBody
	@RequestMapping("/emps/search")
	public List<Employee> query(@RequestParam("condition") String condition) {
		List<Employee> length = employeeService.query("%"+condition+"%");
		return length;
	}
	@ResponseBody
	@RequestMapping(value="/emps/getByPage",method=RequestMethod.GET)
	public Collection<Employee> getByPage(@RequestParam("pageIndex") Integer pageIndex,@RequestParam("size") Integer size) {
		List<Employee> emps = employeeService.getEmpsByPage((pageIndex-1)*size,size);
		return emps;
	}


	/**ajaxʵ��ɾ��
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/emp/delete",method=RequestMethod.GET)
	@ResponseBody
	public int delEmp(@RequestParam("eid") Integer id) {
		System.out.println(id);
		employeeService.delete(id);
		return id;
	}
	/**ajaxʵ��ɾ��
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/emp/delEmpByBatch",method=RequestMethod.GET)
	@ResponseBody
	public String delEmpByBatch(HttpServletRequest request) {
		String json_str = request.getParameter("para");
		Map<String, Object> map = Jackson.JsonToMap(json_str);
		String ids = (String) map.get("ids");
		String[] id_array = ids.split(",");
		Map<String,Object> resturn_map = new HashMap<>();
		String result="";
		boolean status = true;
		int code=200;
		try
		{
		for(int i = 0;i < id_array.length;i++)
		{
			System.out.println("删除："+id_array[i]);
			employeeService.delete(Integer.parseInt(id_array[i]));
		}
		}
		catch (Exception e)
		{
			code = 500;
			status = false;
		}
		resturn_map.put("data","ok");
		resturn_map.put("code",code);
		resturn_map.put("status",status);
		result = ReturnMap(resturn_map);
		return result;
	}
	@RequestMapping(value="/emp/query",method=RequestMethod.GET)
	@ResponseBody
	public String query(HttpServletRequest request) {
		String json_str = request.getParameter("para");
		Map<String, Object> map = Jackson.JsonToMap(json_str);
		String condition = (String) map.get("condition");
		Map<String,Object> resturn_map = new HashMap<>();
		String result="";
		boolean status = true;
		int code=200;
		List<Employee> list = null;
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		try
		{
			 list = employeeService.query("%"+condition+"%");
		}
		catch (Exception e)
		{
			code = 500;
			status = false;
		}
		resturn_map.put("data",list);
		resturn_map.put("code",code);
		resturn_map.put("status",status);
		result = ReturnMap(resturn_map);
		return result;
	}

	private String ReturnMap(Map<String, Object> map)
	{
		return (String) Jackson.MapToJson(map);
	}


	/**ajax GET��ʽʵ�����
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/emp/addEmpByGet",method=RequestMethod.GET)
	@ResponseBody
		public int addEmp(@RequestParam("employeename") String employeename, @RequestParam("sex") int sex, @RequestParam("email") String email, @RequestParam("did") Integer did) {

		Employee employee = new Employee();
		employee.setEmail(email);
		employee.setLastName(employeename);
		employee.setSex(sex);
		employee.setDept(deptService.getDeptById(did));
		System.out.println(employee);
		employeeService.save(employee);;
		return 1;
	}

	/**ajax POST��ʽʵ�����
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/emp/addEmpByPost",method=RequestMethod.POST)
	@ResponseBody
		public int addEmp(@RequestBody Employee emoloyee) {
		System.out.println(emoloyee);
		employeeService.save(emoloyee);
		return 1;
	}

	/**ajaxʵ���޸ģ�һ��
	 * @param id
	 * @return
	 * @RequestParam:��������GET����ʽ�����Ĳ���
	 * GET���󣺿���ʹ��request��@RequestParam���ܲ���
	 */
	@RequestMapping(value="/emp/editEmpByGet",method=RequestMethod.GET)
	@ResponseBody
//	public int addEmp(@RequestBody String employeename,String sex,String email,Integer did) {
		public int editEmp(HttpServletRequest request/*,@RequestParam("lastName") String lastName, @RequestParam("sex") String sex, @RequestParam("email") String email, @RequestParam("eid") Integer id*/) {
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		System.out.println("get:"+lastName);
		Integer id = Integer.parseInt(request.getParameter("id"));
		Integer did = Integer.parseInt(request.getParameter("did"));
		Integer sex = Integer.parseInt(request.getParameter("sex"));
		Employee employee = new Employee();
		employee.setEmail(email);
		employee.setLastName(lastName);
		employee.setSex(sex);
		employee.setId(id);
		Department dept = new Department();
		dept.setId(did);
		employee.setDept(dept);
		employeeService.update(employee);
		return 1;
	}

	/**ajaxʵ���޸ģ�����
	 * @param id
	 * @return
	 * @RequestBody:����ֱ�ӽ�����ӳ��Ϊjavabean���󣬵��������Ĳ�������Ϊjson�ַ���(���Դ���������)��ֻ����һ��@RequestBody
	 * POST������Ҫ��@RequestBody���ܲ���
	 *
	 */
	@RequestMapping(value="/emp/editEmpByPost",method=RequestMethod.POST)
	@ResponseBody
		public int edit(HttpServletRequest request,@RequestBody Employee emp) {
		System.out.println(emp);
		employeeService.update(emp);
		return 1;
	}

	@RequestMapping(value="/getDatas")
	@ResponseBody
		public Map<String, Object> getDatas() {
		List<Map<String, Object>> json_data1 = employeeService.getDatas();
		List<Map<String, Object>> json_data2 = employeeService.getPer();

		System.out.println(json_data1);
		System.out.println(json_data2);
		Map<String,Object> map = new HashMap<>();
		map.put("count", json_data1);
		map.put("per", json_data2);
		return map;
	}
	@RequestMapping(value="/getPer")
	@ResponseBody
		public List<Map<String, Object>> getPer() {
		List<Map<String, Object>> json_data = employeeService.getPer();
		System.out.println(json_data);
		return json_data;
	}
	@RequestMapping(value="/data")
		public String getData() {
		return "data";
	}
	@RequestMapping(value="/chart")
	public String getChart() {
	return "chart";
}

@RequestMapping(value="/download_excel")
public @ResponseBody String dowm(HttpServletResponse response){
    ExcleImpl  excleImpl=new ExcleImpl();
     response.setContentType("application/binary;charset=UTF-8");
              try{
                  ServletOutputStream out=response.getOutputStream();
                  try {
                      //�����ļ�ͷ�����һ�����������������ļ���(�������ǽУ�����.pdf)
                	   DateFormat format = new java.text.SimpleDateFormat("yyyyMMddhhmmss");
                       String date = format.format(new Date());
                      response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(date+".xls", "UTF-8"));
                  } catch (UnsupportedEncodingException e1) {
                      e1.printStackTrace();
                  }

                  String[] titles = { "�û�id", "�û�����","�û��Ա�", "�û�����" };
                  List<Employee> list = employeeService.getEmps();
                  excleImpl.export(list,titles, out);
                  return "success";
              } catch(Exception e){
                  e.printStackTrace();
                  return "������Ϣʧ��";
              }
          }


}
