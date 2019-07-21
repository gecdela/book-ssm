<%@ page language="java" import="java.util.*"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" href="assets/bootstrap-3.3.5-dist/css/bootstrap.min.css">
<script src="assets/bootstrap-3.3.5-dist/js/jquery.min.js"></script>
<script src="assets/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script src="assets/paginator/jqPaginator.js"></script>

</head>

<body>
	<div class="container">
		<div class="row">
			<div class="row">
				<nav class="nav navbar-inverse navbar-default">
				<div class="navbar-header">
					<p class="navbar-brand">图书管理系统</p>
				</div>
				<ul class="nav navbar-nav navbar-right">
					<c:if test="${empty requestScope.employees}">
						<li><a href="user/register.jsp"><span
								class="glyphicon glyphicon-user"></span> 注册</a></li>
						<li><a href="/User/Login"><span
								class="glyphicon glyphicon-log-in"></span> 登录</a></li>
					</c:if>
					<c:if test="${!empty requestScope.employees}">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"> <span
								class="glyphicon glyphicon-user"></span>[username]<b
								class="caret"></b>
						</a>
							<ul class="dropdown-menu">
								<li><a href="/User/UserCenter?userId=@Session.Contents["userId"]"><i
										class="fa fa-user fa-fw"></i>个人中心</a></li>
								<li class="divider"></li>
								<li><a href="/User/LogOff"><i
										class="fa fa-sign-out fa-fw"></i>退出</a></li>
							</ul></li>
						<li><a href="/User/LogOff"><span
								class="glyphicon glyphicon-log-in"></span> [退出]</a></li>								</
					</c:if>
				</ul>
				<hr />
				</nav>
			</div>
			<div class="row margin-top 20">
				<div class="col-md-2">
					<nav class="navbar-default navbar-side" role="navigation">
					<div id="sideNav" href="">
						<i class="fa fa-caret-right"></i>
					</div>
					<div class="sidebar-collapse">
						<ul class="nav" id="main-menu">
							<li><a class="active-menu" href="index.jsp"><i
									class="fa fa-dashboard"></i> 首页</a></li>
							<li><a href="emps" target="innerFrame"><i
									class="fa fa-desktop"></i>员工管理</a></li>
							<li><a href="data" target="innerFrame"><i
									class="fa fa-desktop"></i>数据管理</a></li>
							<li><a href="chart" target="innerFrame"><i
									class="fa fa-desktop"></i>数据管理</a></li>
						</ul>
					</div>
					</nav>
				</div>

			<!-- /. NAV SIDE  -->
			<div class="col-md-10">
				<div class="row">
					<div id="page-wrapper">
						<div id="page-inner">
							<div class="row">
								<div class="col-md-12 col-sm-12 col-xs-12">
									<iframe scrolling="no" width="950px" height="670px" src="emps"
										class="innerFrame" name="innerFrame"></iframe>
								</div>
							</div>
						</div>
						<!-- /. PAGE INNER  -->
					</div>
				</div>
			</div>
			<!-- /. PAGE WRAPPER  -->
		</div>
	</div>
	</div>
</body>
</html>
