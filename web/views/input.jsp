<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ page isELIgnored="false"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 表单path属性对应html中的name属性 -->
	<form:form action="${pageContext.request.contextPath}/emp" method="POST"
		modelAttribute="employee">
		<c:if test="${employee.id==null }">
LastName:<form:input path="lastName" />
			<br>
			<br>
		</c:if>
		<c:if test="${employee.id!=null }">
			<form:hidden path="id" />
			<input type="hidden" name="_method" value="PUT">
		</c:if>
Email:<form:input path="email" />
		<br>
		<br>
		<%
			Map<String, String> sexs = new HashMap();
				sexs.put("1", "男");
				sexs.put("0", "女");
				request.setAttribute("sexs", sexs);
		%>
Sex:<form:radiobuttons path="sex" items="${sexs}" />
		<br>
		<br>
Department:<form:select path="dept.id" items="${departments}"
			itemLabel="deptName" itemValue="id" />
		<br>
		<br>
		<br>
		<input type="submit" value="Submit">
	</form:form>
</body>
</html>