<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="assets/bootstrap-3.3.5-dist/css/bootstrap.min.css">
<script src="assets/bootstrap-3.3.5-dist/js/jquery.min.js"></script>
<script src="assets/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
</head>
<body>
<form action=" " method="POST">
<input type="hidden" name="_method" value="DELETE">
</form>
<c:if test="${empty requestScope.employees}">
没有信息
</c:if>
<c:if test="${!empty requestScope.employees}">
<a href="emp">添加</a>
<table id="book_table" class="table table-striped table-bordered table-hover">
		<thead>
			<tr>
				<td colspan="7"><button type="button" class="btn btn-default"
						id="btn_add" data-toggle="modal" data-target="#addModal">
						<span class="glyphicon glyphicon-plus"></span>新增
					</button></td>
			</tr>
			<tr>
				<th>员工ID</th>
				<th>姓名</th>
				<th>邮箱</th>
				<th>性别</th>
				<th>部门</th>
				<th>操作</th>
				<th colspan="2">操作</th>
						
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.employees}" var="emp">
			<tr>
			<td>${emp.id }</td>
			<td>${emp.lastName }</td>
			<td>${emp.email }</td>
			<td>${emp.sex }</td>
			<td>${emp.dept.deptName }</td>
			<td><a class="deletebtn" href="emp/${emp.id}">Delete</a></td>
			<td><a href="emp/${emp.id}">Edit</a></td>
			</tr>
			</c:forEach>
			</tbody>
		</table>
</c:if>
<!-- Springmvc处理静态资源 
1.原因：springmvc拦截一切URL
2.解决：在配置文件中配置jquery-1.7.1.min.js
-->
<script type="text/javascript" src="assets/scripts/jquery-1.7.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$(".deletebtn").click(function(){
			var href= $(this).attr("href");
			$("form").attr("action",href).submit();
			return false;
		});
	})
</script>
</body>
</html>