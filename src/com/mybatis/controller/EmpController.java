package com.mybatis.controller;

import java.io.UnsupportedEncodingException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;

import com.mybatis.beans.User;
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


@Controller
public class EmpController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService deptService;

    @RequestMapping(value="/api/login",method=RequestMethod.POST)
    @ResponseBody
    public String login(@RequestBody User user) {
        System.out.println("登陆：" + user);
        User u = employeeService.login(user);
        int code = 200;
        boolean status = true;
        if(u==null)
        {
            code = 500;
            status = false;
            System.out.println("登陆:"+u);
        }
        String result = fill(u,code, status);
        return result;
    }
    @RequestMapping(value="/api/getUserInfo",method=RequestMethod.POST)
    @ResponseBody
    public String getUserInfo(@RequestBody User user) {
        System.out.println("获取用户信息：" + user);
        User u = employeeService.getUserInfo(user);
        int code = 200;
        boolean status = true;
        if(u==null)
        {
            code = 500;
            status = false;
            System.out.println("获取用户信息:"+u);
        }
        String result = fill(u,code, status);
        return result;
    }
    @RequestMapping("/api/emps")
    public String getEmps(Map<String,Object> map)
    {
        List<Employee> emps = employeeService.getEmps();
        map.put("employees", emps);
        int code = 200;
        boolean status = true;
        Map<String,Object> return_map = new HashMap<>();
        return_map.put("data",emps);
        return_map.put("code",code);
        return_map.put("status",status);
        String result = ReturnMaps(return_map);
        return result;
    }

    @RequestMapping(value="/api/emps/init")
    @ResponseBody
    public String init() {
        List<Employee> emp_list = employeeService.getEmps();
        List<Department> dept_list = deptService.getDepartments();
        System.out.println(emp_list.size());
        System.out.println(dept_list.size());
        Map<String,Object> map = new HashMap<>();
        map.put("count", emp_list.size());
        map.put("depts", dept_list);
        Map<String, Object> result_map = new HashMap<>();
        result_map.put("count",emp_list.size());
        result_map.put("depts",dept_list);
        return fill(result_map,200,true);

    }

    @ResponseBody
    @RequestMapping("/api/emps/getLength")
    public int getLength() {
        int length = employeeService.getLength();
        return length;
    }
    @ResponseBody
    @RequestMapping("/api/emps/search")
    public List<Employee> query(@RequestParam("condition") String condition) {
        List<Employee> length = employeeService.query("%"+condition+"%");
        return length;
    }
    @ResponseBody
    @RequestMapping(value="/api/emps/getByPage",method=RequestMethod.GET)
//    public String getByPage(@RequestParam("pageIndex") Integer pageIndex,@RequestParam("size") Integer size) {
    public String getByPage(HttpServletRequest request) {
        String json_str = request.getParameter("params");
        Map<String, Object> map = Jackson.JsonToMap(json_str);
        int pageIndex = (int) map.get("pageIndex");
        int size = (int) map.get("size");
        List<Employee> emps = employeeService.getEmpsByPage((pageIndex-1)*size,size);
        int total = employeeService.getLength();
        int code = 200;
        boolean status = true;
        Map<String,Object> page_map = new HashMap<>();
        page_map.put("pageIndex", pageIndex);
        page_map.put("size", size);
        page_map.put("list", emps);
        page_map.put("total", total);
        String result = fill(page_map,code,status);
        return result;
    }

    @RequestMapping(value="/api/emps/delEmpByGet",method=RequestMethod.GET)
    @ResponseBody
//    public String delEmpByGet(@RequestParam("eid") Integer id) {
    public String delEmpByGet(HttpServletRequest request) {
        String json_str = request.getParameter("params");
        Map<String, Object> map = Jackson.JsonToMap(json_str);
        int eid = (int) map.get("eid");
        employeeService.delete(eid);
        int code = 200;
        boolean status = true;
        String result = fill("ok",code,status);
        return result;
    }

    @RequestMapping(value="/api/emps/delEmpByBatch",method=RequestMethod.GET)
    @ResponseBody
    public String delEmpByBatch(HttpServletRequest request) {
        String json_str = request.getParameter("params");
        Map<String, Object> map = Jackson.JsonToMap(json_str);
        String ids = (String) map.get("ids");
        String[] id_array = ids.split(",");
        String result="";
        boolean status = true;
        int code=200;
        try
        {
            for(int i = 0;i < id_array.length;i++)
            {
                System.out.println("删除emp："+id_array[i]);
                employeeService.delete(Integer.parseInt(id_array[i]));
            }
        }
        catch (Exception e)
        {
            code = 500;
            status = false;
        }
        result = fill("ok", code, status);
        return result;
    }

    @RequestMapping(value="/api/emps/addEmpByGet",method=RequestMethod.GET)
    @ResponseBody
    public String addEmpByGet(@RequestParam("employeename") String employeename, @RequestParam("sex") int sex, @RequestParam("email") String email, @RequestParam("did") Integer did) {
        Employee employee = new Employee();
        employee.setEmail(email);
        employee.setLastName(employeename);
        employee.setSex(sex);
        employee.setDept(deptService.getDeptById(did));
        System.out.println(employee);
        employeeService.save(employee);
        int code = 200;
        boolean status = true;
        String result = fill("ok",code,status);
        return result;
    }

    @RequestMapping(value="/api/emps/addEmpByPost",method=RequestMethod.POST)
    @ResponseBody
    public String addEmpByPost(@RequestBody Employee emp) {
        System.out.println("添加emp：" + emp);
        employeeService.save(emp);
        int code = 200;
        boolean status = true;
        String result = fill("ok",code, status);
        return result;
    }


    @RequestMapping(value="/api/emps/editEmpByGet",method=RequestMethod.GET)
    @ResponseBody
//	public int addEmp(@RequestBody String employeename,String sex,String email,Integer did) {
    public String editEmpByGet(HttpServletRequest request/*,@RequestParam("lastName") String lastName, @RequestParam("sex") String sex, @RequestParam("email") String email, @RequestParam("eid") Integer id*/) {
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
        int code = 200;
        boolean status = true;
        String result = fill("ok",code,status);
        return result;
    }


    /**
     * @param emp
     * @return
     */
    @RequestMapping(value="/api/emps/editEmpByPost",method=RequestMethod.POST)
    @ResponseBody
    public String editEmpByPost(@RequestBody Employee emp) {
        System.out.println("添加emp：" + emp);
        employeeService.update(emp);
        int code = 200;
        boolean status = true;
        String result = fill("ok",code, status);
        return result;
    }
    @RequestMapping(value="/api/emps/query",method=RequestMethod.GET)
    @ResponseBody
    public String query(HttpServletRequest request) {
        String json_str = request.getParameter("params");
        Map<String, Object> map = Jackson.JsonToMap(json_str);
        String condition = (String) map.get("condition");
        Map<String,Object> resturn_map = new HashMap<>();
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
        Map<String,Object> page_map = new HashMap<>();
        page_map.put("list", list);
        page_map.put("total", list.size());
        String result = fill(page_map,code,status);
        return result;
    }

    /**
     * @return
     */
    @RequestMapping(value="/api/emps/getDatas")
    @ResponseBody
    public String getDatas() {
        List<Map<String, Object>> json_data1 = employeeService.getDatas();
        List<Map<String, Object>> json_data2 = employeeService.getPer();

        System.out.println(json_data1);
        System.out.println(json_data2);
        Map<String,Object> map = new HashMap<>();
        map.put("count", json_data1);
        map.put("per", json_data2);
        String result = fill(map,200, true);
        return result;
    }
    private  String fill(Object data, int code, boolean status)
    {
        Map<String,Object> return_map = new HashMap<>();
        return_map.put("data",data);
        return_map.put("code",code);
        return_map.put("status",status);
        String result = ReturnMaps(return_map);
        return result;
    }
    private String ReturnMaps(Map<String, Object> map)
    {
        return  Jackson.MapToJson(map);
    }


}
