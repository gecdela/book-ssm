<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="assets/bootstrap-3.3.5-dist/css/bootstrap.min.css">
<script src="assets/bootstrap-3.3.5-dist/js/jquery.min.js"></script>
<script src="assets/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script src="assets/paginator/jqPaginator.js"></script>
<!-- <link href="assets/bootstrap-4.1.3-dist/css/bootstrap.min.css" rel="stylesheet" />
<script src="assets/bootstrap-4.1.3-dist/js/jquery.min.js"></script>
<script src="assets/bootstrap-4.1.3-dist/js/bootstrap.min.js"></script> -->
</head>
<body>
				<div class="row">
					<div class="margin-top-20">
						<ul class="breadcrumb">
							<li><a href="#">当前位置</a></li>
							<li><a href="#">首页</a></li>
							<li class="active"><a href="#">员工管理</a></li>
						</ul>
					</div>
				</div>
	<div class="container">
		<div class="row">

				<div class="row">
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane fade in active" id="Student">
							<div class="row">
								<div class="btn-group">
									<div class="btn-toolbar" role="toolbar">
										<button type="button" class="btn btn-default btn-sm" id="btn_add"
											data-toggle="modal" data-target="#addModal">
											<span class="glyphicon glyphicon-plus"></span>新增
										</button>
										<button type="button" class="btn btn-default btn-sm"
											id="btn_download">
											<span class="glyphicon glyphicon-download-alt"><a href="download_excel">导出</a></span>
										</button>
									</div>
								</div>
							</div>
							<br>
							<div class="row">
								搜索:<input type="text" placeholder="请输入你要查询的条件" id="condition"
									name="condition" />
								<button id="btn_search" class="btn btn-primary">搜索</button>
							</div>
						</div>
					</div>

					<div class="row">
							<table id="book_table"
								class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>员工ID</th>
										<th>姓名</th>
										<th>性别</th>
										<th>邮箱</th>
										<th>部门</th>
										<th>操作</th>
										<th colspan="2">操作</th>
									</tr>
								</thead>
								<tbody id="ItemList"></tbody>
							</table>

						<div class="text-center margin-top-20">
							<ul class="pagination jq-pagination" id="pagination">
							</ul>
						</div>
					</div>
					</div>
				</div>
			</div>
		<div class="row">
			<!-- 查看模态框 -->
			<div class="modal fade" id="editModal">
				<div class="modal-dialog modal-md">
					<div class="modal-content">
						<div class="modal-header">
							<h4>查看信息</h4>
							<button type="button" class="close" data-dismiss="modal">x</button>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<div class="row">
									<label for="EID" class=" col-sm-2 control-label">EID</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="edit_id" disabled>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="row">
									<label for="edit_name" class="col-sm-2 control-label">员工名</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" value=""
											id="edit_name" name="edit_name" placeholder="请输入名字">
									</div>

								</div>
							</div>
							<div class="form-group">
								<div class="row">
									<label for="sex" class="col-sm-2 control-label">性别</label>
									<div class="col-sm-4">
										<label class="radio-inline"> <input type="radio"
											name="edit_sex" id="optionsRadios3" value="0"><span>女</span>
										</label> <label class="radio-inline"> <input type="radio"
											name="edit_sex" id="optionsRadios4" value="1"> <span>男</span>
										</label>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="row">
									<label for="permit" class="col-sm-2 control-label">邮箱</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="edit_email"
											name="edit_email">
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="row">
									<label for="" class="col-sm-2 control-label">角色</label>
									<div class="col-sm-9">
										<select class="form-control" name="edit_sel" id="edit_sel">
											<option value="0">=====请选择部门=====</option>
											<option value="1">宣传部</option>
											<option value="2">组织部</option>
										</select>
									</div>
								</div>
							</div>

							<div class="modal-footer">
								<button type="button" class="btn btn-default" onClick="update()">保存</button>
								<!--     <input type="submit" class="btn btn-default"  onClick="return update()"> -->
								<button type="button" class="btn btn-primary"
									data-dismiss="modal">关闭</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 查看模态框结束 -->
			<!--  删除模态框-->
			<div class="modal" id="deleteModal">
				<div class="modal-dialog modal-md">
					<div class="modal-content">
						<div class="modal-header">
							<h4>确定删除该条信息吗？</h4>
							<button type="button" class="close" data-dismiss="modal">x</button>
						</div>
						<div class="modal-body"></div>
						<div class="modal-footer">
							<input type="submit" class="btn btn-default" onClick="delInfo()">
							<button class="btn btn-primary" data-dismiss="modal">取消</button>
						</div>
					</div>
				</div>
			</div>
			<!--  删除模态框结束-->

			<!-- 添加模态框开始 -->
			<div class="modal fade" id="addModal">
				<div class="modal-dialog modal-md">
					<div class="modal-content">
						<div class="modal-header">
							<h4>添加信息</h4>
							<button type="button" class="close" data-dismiss="modal">x</button>
						</div>
						<div class="modal-body">
							<!-- <form class="form-horizontal" id="addform"> -->
							<div class="form-group">
								<div class="row">
									<label for="username" class="col-sm-2 control-label">员工名</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" value="" id="add_name"
											name="add_name" placeholder="请输入用户名">
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="row">
									<label for="username" class="col-sm-2 control-label">性别</label>
									<div class="col-sm-4">
										<label class="radio-inline"> <input type="radio"
											name="sex" id="optionsRadios3" value="0" checked><span>女</span>
										</label> <label class="radio-inline"> <input type="radio"
											name="sex" id="optionsRadios4" value="1"> <span>男</span>
										</label>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="row">
									<label for="" class="col-sm-2 control-label">角色</label>
									<div class="col-sm-9">
										<select class="form-control" name="s_per" id="sel1">
											<option value="">=====请选择部门=====</option>
											<option value="1">宣传部</option>
											<option value="2">组织部</option>
										</select>
									</div>
								</div>
							</div>

							<div class="form-group">
								<div class="row">
									<label for="email" class="col-sm-2 control-label">邮箱</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" name="add_email"
											id="add_email" placeholder="请输入邮箱">

									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									onClick="addInfo()">保存</button>
								<button type="button" class="btn btn-primary"
									data-dismiss="modal">关闭</button>
							</div>
							<!-- </form> -->

						</div>

					</div>
				</div>
			</div>
			<!-- 添加模态框结束 -->
		</div>
	</div>

	
	<!-- Springmvc处理静态资源 
1.原因：springmvc拦截一切URL
2.解决：在配置文件中配置jquery-1.7.1.min.js
-->
<script type="text/javascript">
/* init();
 */$.jqPaginator("#pagination",{
	totalPages:5,
	visiblePages:5,
	currentPage: 1,
	first: '<li class="first"><a href="javascript:void(0);">首页</a></li>',
    prev: '<li class="prev"><a href="javascript:void(0);">上一页</a></li>',
    next: '<li class="next"><a href="javascript:void(0);">下一页</a></li>',
    last: '<li class="last"><a href="javascript:void(0);">末页</a></li>',
    page: '<li class="page"><a href="javascript:void(0);">{{page}}</a></li>',
    onPageChange:function(num,type){
      search(num); 
    },
});
function search(num)
{
	$.ajax({
        url:"emps/getByPage",
        data:"pageIndex="+num+"&size=8",
		dataType:"json",
		type:"get",
        success: function (data) {
            var str = '';
            var sex;
            if(data.length>0)
            {
	            for (var i = 0; i < data.length; i++) {
	                str += '<tr><td id="eid">' + data[i].id + '</td>';
	                str += '<td>' + data[i].lastName + '</td>';
	                if(data[i].sex==1) sex="男";
	                else sex = "女";
	                str += '<td>' + sex + '</td>';
	                str += '<td>' + data[i].email + '</td>';
	                str += '<td>' + data[i].dept.deptName + '</td>';
	                str +='<td width="5%"><button type="button" class="btn btn-danger btn-sm" onClick="deleteInfo(this)"><span class="glyphicon glyphicon-remove"></span>删除</button></td>';
	                str +='<td width="5%"><button type="button" class="btn btn-warning btn-sm" onClick="editInfo(this)"><span class="glyphicon glyphicon-pencil"></span>编辑</button></td>';
	            }
	            $("#ItemList").html(str);
            }
        else
        {
            $("#plansItemList").html("暂无数据……");
            $('#pagination').jqPaginator('option', {
                totalPages: 1
            });
        }
	}
        
	});
}
	$(function(){
		$(".deletebtn").click(function(){
			var href= $(this).attr("href");
			$("form").attr("action",href).submit();
			return false;
		});
	})
	

function init()
    {

		$.ajax({
            url:"emps/getLength",
			dataType:"json",
			type:"get",
            success: function (data) {
		        total= Math.ceil(data/8);
                $('#pagination').jqPaginator('option', {
                    totalPages: total
                /* data = result.data; */
/*                 var str = '';
                var sex;
                for (var i = 0; i < data.length; i++) {
                    str += '<tr><td id="eid">' + data[i].id + '</td>';
                    str += '<td>' + data[i].lastName + '</td>';
                    if(data[i].sex==1) sex="男";
                    else sex = "女";
                    str += '<td>' + sex + '</td>';
                    str += '<td>' + data[i].email + '</td>';
                    str += '<td>' + data[i].dept.deptName + '</td>';
                    str +='<td width="5%"><button type="button" class="btn btn-danger btn-sm" onClick="deleteInfo(this)"><span class="glyphicon glyphicon-remove"></span>删除</button></td>';
                    str +='<td width="5%"><button type="button" class="btn btn-warning btn-sm" onClick="editInfo(this)"><span class="glyphicon glyphicon-pencil"></span>编辑</button></td>';
                }
                $("#ItemList").html(str); */
            });
            }
		});
    }
	
	init();
	
 	var eid=0; 
    function deleteInfo(obj)
    {
     	var tds= $(obj).parent().parent().find("td");
    	 eid = tds.eq(0).text();	      	    	    	      	 	    	    	
    	$("#deleteModal").modal("show");
    	
    }	
	function delInfo()
    {
    	$.ajax({
    	type:"get",
    	url:"emp/delete",
    	data:"eid="+eid,
    	dataType:"json",
    	contentType:'application/json;charset=utf-8',
    	success: function(result) {  
	            location.reload();  
	        }  
    	});
    	$("#deleteModal").modal("hide");
    	    	
    }
	/* GET方式 */
/* 	function addInfo() {  
	    //获取模态框数据  
	    var employeename1 = $("#add_name").val(); 
	    var email1 = $("#add_email").val();  
		var selectedIndex1=$("#sel1").get(0).selectedIndex - 1;
		var sex1 = $("input[name='sex']:checked").val();
		var did1=0;
		if(selectedIndex1==1)
		{
		   did1="1";
		}
		else 
		   did1 ="2";
	    $.ajax({  
	        type: "get",  
	        url: "emp/addEmpByGet",  
	        data: {
	        		employeename: employeename1,
		        	sex: sex1,
		        	email: email1,
		        	did: did1,
	        	},  
	    	dataType:"json",
	    	contentType:'application/json;charset=utf-8',  
	        success: function(result) {  
	            location.reload();  
	        }  
	    }); 
		$("#addModal").modal("hide");
    } */
    
    /* POST方式 */
	function addInfo() {  
	    //获取模态框数据  
	    var lastName = $("#add_name").val(); 
	    var email = $("#add_email").val();  
		var selectedIndex1=$("#sel1").get(0).selectedIndex - 1;
		var sex = $("input[name='sex']:checked").val();
		var did=0;
		if(selectedIndex1==1)
		{
		   did="1";
		}
		else 
		   did ="2";
		
		var json_data={
				"lastName":lastName,
				"sex":sex,
				"email":email,
				"dept":{
				"id":did	
				}
		}
	    $.ajax({  
	        type: "post",  
	        url: "emp/addEmpByPost",  
	        data: JSON.stringify(json_data),  
	    	dataType:"json",
	    	contentType:'application/json;charset=utf-8',  
	        success: function(result) {  
	            location.reload();  
	        }  
	    }); 
		$("#addModal").modal("hide");
    }
function editInfo(obj)
{

	    var tds = $(obj).parent().parent().find("td");
		$("#edit_id").val(tds.eq(0).text());
		$("#edit_name").val(tds.eq(1).text());
		var sex = tds.eq(2).text();
	    $("#edit_email").val(tds.eq(3).text()); 
		var dept = tds.eq(4).text();
	    var did = 0;
	    $("#edit_sel option:selected").attr("selected",false); 
/* 	    $("#edit_sel option:selected").attr("selected",false); */
		if(dept=="宣传部") 
		{
			did=1;

 			$("#edit_sel").find("option[value='"+did+"']").attr("selected",true);
		}
		if(dept=="组织部")
		{
			did=2;
/* 			$("#edit_sel option:selected").attr("selected",false); 
 */			$("#edit_sel").find("option[value='"+did+"']").attr("selected",true);
		}
		/*$("#edit_sel option:eq("+did+")").attr('selected','selected'); */

		if(sex=="女")
		{
			$("input[name='edit_sex']").get(0).checked=true; 
		}
		else
		{
			$("input[name='edit_sex']").get(1).checked=true; 			
		}
        $("#editModal").modal("show");
}

    /*POST方式修改  */
 function update() {  		
    //获取模态框数据  
    var lastName = $("#edit_name").val();  
    var email = $("#edit_email").val(); 	      
    var id = $("#edit_id").val();
    var sex = $('input:radio[name="edit_sex"]:checked').val();  
    var did = $("#edit_sel").find("option:selected").val();
    var json_data = {
    	"lastName":lastName,
    	"email":email,
    	"id":id,
    	"sex":sex,
    	"dept":{
    		"id":did
    	}
    };
    $.ajax({  
        type: "post",  
        url: "emp/editEmpByPost",  
        data: JSON.stringify(json_data),  
    	dataType:"json",
    	contentType:'application/json;charset=utf-8',   
        success: function(result) {  
            location.reload();  
        }  
    });  
	$("#editModal").modal("hide");
}
/*GET方式修改  */
/*  function update() {  		
	    //获取模态框数据  
	    var lastName = $("#edit_name").val();  
	    var email = $("#edit_email").val(); 	      
	    var id = $("#edit_id").val();
	    var sex = $('input:radio[name="edit_sex"]:checked').val();  
	    var did = $("#edit_sel").find("option:selected").val();
	    $.ajax({  
	        type: "get",  
	        url: "emp/editEmpByGet",  
	        data: "lastName=" + lastName + "&email=" + email+"&id="+id+"&sex="+sex+"&did="+did,  
	    	dataType:"json",
	    	contentType:'application/json;charset=utf-8',   
	        success: function(result) {  
	            location.reload();  
	        }  
	    }); 

		$("#editModal").modal("hide");
    }  */
    
     $("#btn_download").click(function(){
   		$.ajax({
   			url:"download_excel",
   			contentType:"application/binary;charset=UTF-8",
   			success:function(result)
   			{
   				
   			}
   		})
    });
    $("#btn_search").click(function(){
    	var condition = $("#condition").val();
   		$.ajax({
   			url:"emps/search",
   			data:"condition="+condition,
   			dataType:"json",
   			type:"get",
   			contentType:"application/json;charset=UTF-8",
            success: function (data) {
	            var str = '';
	            var sex;
	            if(data.length>0)
	            {
		            for (var i = 0; i < data.length; i++) {
		                str += '<tr><td id="eid">' + data[i].id + '</td>';
		                str += '<td>' + data[i].lastName + '</td>';
		                if(data[i].sex==1) sex="男";
		                else sex = "女";
		                str += '<td>' + sex + '</td>';
		                str += '<td>' + data[i].email + '</td>';
		                str += '<td>' + data[i].dept.deptName + '</td>';
		                str +='<td width="5%"><button type="button" class="btn btn-danger btn-sm" onClick="deleteInfo(this)"><span class="glyphicon glyphicon-remove"></span>删除</button></td>';
		                str +='<td width="5%"><button type="button" class="btn btn-warning btn-sm" onClick="editInfo(this)"><span class="glyphicon glyphicon-pencil"></span>编辑</button></td>';
		            }
		            $("#ItemList").html(str);
	            }
		        else
		        {
		            $("#plansItemList").html("暂无数据……");
		            $('#pagination').jqPaginator('option', {
		                totalPages: 1
		            });
		        }
		}
	   		});
    });
</script>
</body>
</html>