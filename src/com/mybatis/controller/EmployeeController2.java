//package com.mybatis.controller;
//
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//import java.text.DateFormat;
//import java.util.Collection;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.mybatis.beans.Employee;
//import com.mybatis.service.DepartmentService;
//import com.mybatis.service.EmployeeService;
//import com.mybatis.service.ExcleImpl;
//
//@Controller
//public class EmployeeController2 {
//	
//	@Autowired
//	private EmployeeService employeeService; 
//	
//	@Autowired
//	private DepartmentService deptService; 
//	
//	@RequestMapping("/emps")
//	public String getEmps(Map<String,Object> map)
//	{
//		List<Employee> emps = employeeService.getEmps();
//		map.put("employees", emps);
//		return "list";
//	}
//	
//	@RequestMapping(value="/emp/{id}",method=RequestMethod.DELETE)
//	public String delete(@PathVariable("id")Integer id)
//	{
//		employeeService.delete(id);
//		return "redirect:/emps";
//	}
//	/**
//	 * @param employee
//	 * @param BindingResult:指定数据格式化出错返回消息
//	 * @return
//	 */
//	@RequestMapping(value="/emp",method=RequestMethod.POST)
//	public String save(Employee employee,BindingResult result) {
//		System.out.println(employee);
//		if(result.getErrorCount()>0)
//		{
//			System.out.println("出错了！");
//			for(FieldError error :result.getFieldErrors())
//			{
//				System.out.println(error.getField()+":"+error.getDefaultMessage());	
//			}
//		}
//		employeeService.save(employee);
//		return "redirect:/emps";
//	}
//	@RequestMapping(value="/emp",method=RequestMethod.GET)
//	public String input(Map<String,Object> map) {
//		map.put("departments", deptService.getDepartments());
//		
//		System.out.println(deptService.getDepartments());
//		map.put("employee", new Employee());
//		return "input";
//	}
//	@ResponseBody
//	@RequestMapping("/testJson")
//	public Collection<Employee> testJson() {
//		List<Employee> emps = employeeService.getEmps();
//		return emps;
//	}
//	
//	
//	/**ajax实现删除
//	 * @param id
//	 * @return
//	 */
//	@RequestMapping(value="/emp/delete",method=RequestMethod.GET)
//	@ResponseBody
//	public int delEmp(@RequestParam("eid") Integer id) {
//		System.out.println(id);
//		employeeService.delete(id);
//		return id;
//	}
//	
//	/**ajax实现删除
//	 * @param id
//	 * @return
//	 */
//	@RequestMapping(value="/emp/addEmp",method=RequestMethod.GET)
//	@ResponseBody
////	public int addEmp(@RequestBody String employeename,String sex,String email,Integer did) {
//		public int addEmp(@RequestParam("employeename") String employeename, @RequestParam("sex") String sex, @RequestParam("email") String email, @RequestParam("did") Integer did) {
//
//		Employee employee = new Employee();
//		employee.setEmail(email);
//		employee.setLastName(employeename);
//		employee.setSex(sex);
//		employee.setDept(deptService.getDeptById(did));
//		System.out.println(employee);
//		employeeService.save(employee);;
//		return 1;
//	}
//	
//	/**ajax实现删除
//	 * @param id
//	 * @return
//	 */
//	@RequestMapping(value="/emp/editEmp",method=RequestMethod.GET)
//	@ResponseBody
////	public int addEmp(@RequestBody String employeename,String sex,String email,Integer did) {
//		public int editEmp(HttpServletRequest request/*,@RequestParam("lastName") String lastName, @RequestParam("sex") String sex, @RequestParam("email") String email, @RequestParam("eid") Integer id*/) {
//		String lastName = request.getParameter("lastName");
//		String email = request.getParameter("email");
//		String sex = request.getParameter("sex");
//		Integer id = Integer.parseInt(request.getParameter("id"));
//		Employee employee = new Employee();
//		employee.setEmail(email);
//		employee.setLastName(lastName);
//		employee.setSex(sex);
//		employee.setId(id);
//		System.out.println("get:"+lastName);
//		employeeService.update(employee);
//		return 1;
//	}
//	
//	/**ajax实现删除
//	 * @param id
//	 * @return
//	 * @RequestBody:可以直接将参数映射为javabean对象，但传过来的参数必须为json字符串
//	 */
//	@RequestMapping(value="/emp/edit",method=RequestMethod.POST)
//	@ResponseBody
//		public int edit(HttpServletRequest request,@RequestBody Employee emp) {
////		Employee employee = new Employee();
////		employee.setEmail(email);
////		employee.setLastName(employeename);
////		employee.setSex(sex);
////		employee.setId(id);
//		System.out.println(emp);
//		employeeService.update(emp);
//		return 1;
//	}
//	
//	@ModelAttribute
//	public void getEmployee(@RequestParam(value="id",required=false)Integer id,Map<String,Object> map)
//	{
//		if(id!=null)
//		{
//			map.put("employee", employeeService.getEmpById(id));
//		}
//	}
//	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
//	public String input(@PathVariable("id")Integer id,Map<String,Object> map)
//	{
//		map.put("departments", deptService.getDepartments());
//		map.put("employee", employeeService.getEmpById(id));
//		return "input";
//
//	}
//	@RequestMapping(value="/emp",method=RequestMethod.PUT)
//	public String update(Employee employee)
//	{
//		employeeService.save(employee);
//		return "redirect:/emps";
//	}
//
//
//    
//@RequestMapping(value="/download_excel")    
//public @ResponseBody String dowm(HttpServletResponse response){
//    ExcleImpl  excleImpl=new ExcleImpl();
//     response.setContentType("application/binary;charset=UTF-8");
//              try{
//                  ServletOutputStream out=response.getOutputStream();
//                  try {
//                      //设置文件头：最后一个参数是设置下载文件名(这里我们叫：张三.pdf)
//                	   DateFormat format = new java.text.SimpleDateFormat("yyyyMMddhhmmss");
//                       String date = format.format(new Date());
//                      response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(date+".xls", "UTF-8"));
//                  } catch (UnsupportedEncodingException e1) {
//                      e1.printStackTrace();
//                  }
//               
//                  String[] titles = { "用户id", "用户姓名","用户性别", "用户邮箱" }; 
//                  List<Employee> list = employeeService.getEmps();
//                  excleImpl.export(list,titles, out);      
//                  return "success";
//              } catch(Exception e){
//                  e.printStackTrace();
//                  return "导出信息失败";
//              }
//          }
//
//
//}
