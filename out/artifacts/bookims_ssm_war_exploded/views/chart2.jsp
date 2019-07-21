<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>无标题文档</title>

<script type="text/javascript" src="assets/echart/echarts.js"></script>
 <link rel="stylesheet" href="assets/bootstrap-3.3.5-dist/css/bootstrap.min.css">
 <script src="assets/bootstrap-3.3.5-dist/js/jquery.min.js"></script>
<script src="assets/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
</head>

<!-- <div class="container">
<div class="row">
<ul id="myTab" class="nav nav-tabs">
		<li class="active"><a href="#bar" data-toggle="tab">柱状图</a></li>
		<li><a href="#line" data-toggle="tab">折线图</a></li>
		<li><a href="#circle" data-toggle="tab">圆饼图</a></li>
		<li><a href="#curve" data-toggle="tab">曲线图</a></li>
</ul>
<div id="myTabContent" class="tab-content">
	<div class="tab-pane fade in active" id="bar">
					<div class="row">
						<div class="col-md-4">
							<div class="panel panel-default">
								<div class="panel-heading">部门统计图</div>
								<div class="panel-body">
								<div id="chartmain1" class="col-md-4" style="height:250px"></div> 
								</div>
							</div>
						</div>

					</div>
	</div>
<div class="tab-pane fade" id="line">
						<div class="col-md-4">
							<div class="panel panel-default">
								<div class="panel-heading">bathroom-chart</div>
								<div class="panel-body">
								<div id="chartmain3"class="col-md-4" style="height:250px"></div> 
								<div id="employee-bar"class="col-md-4" style="height:250px"></div> 
								</div>
							</div>
						</div>
	</div>
</div>
</div> 

 </div> -->
								<div id="chartmain3" class="col-md-4" style="width:250px;height:250px"></div> 

 <script type="text/javascript">
/*  $(document).ready(function(){
	$.ajax({
		url:"getDatas",
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		success:function(result)
		{
			var xaxis=[];
			var yaxis=[];
			if(result.length!=0)
		   {
			for (var i = 0; i < result.length; i++) 
			{
				xaxis.push(result[i].deptName);
				yaxis.push(result[i].count);
			}

							//指定图标的配置和数据
							var option0 = {
								title : {
									text : 'ECharts 数据统计'
								},
								tooltip : {},
								legend : {
									data : [ '用户来源' ]
								},

								xAxis : {
									data : xaxis,
								},
								yAxis : {
									data : [],
								},
								series : [ {
									name : '人数',
									type : 'line',
									data:  yaxis,

								} ]
							};

							//初始化echarts实例
							var myChart0 = echarts.init(document.getElementById('employee-bar'));
							//使用制定的配置项和数据显示图表
							myChart0.setOption(option0);
						}

		  },

		});
	}); */
	
	//指定图标的配置和数据
	var option1 = {
		title : {
			text : 'ECharts 数据统计'
		},

		tooltip : {},
		legend : {
			data : [ '用户来源' ]
		},
		xAxis : {
			data : [ "Android", "IOS", "PC", "Ohter" ]
		},
		yAxis : {

		},
		series : [ {
			name : '访问量',
			type : 'bar',
			data : [ 500, 200, 360, 100 ]
		} ]
	};
	var option2 = {
		title : {
			text : 'ECharts 数据统计'
		},
		tooltip : {},
		legend : {
			data : [ '用户来源' ]
		},
		xAxis : {
			data : [ "Android", "IOS", "PC", "Ohter" ]
		},
		yAxis : {

		},
		series : [ {
			name : '访问量',
			type : 'line',
			data : [ 500, 200, 360, 100 ]
		} ]
	};

	var option3 = {
		title : {
			text : 'ECharts 数据统计'
		},
		series : [ {
			name : '访问量',
			type : 'pie',
			radius : '60%',
			data : [ {
				value : 500,
				name : 'Android'
			}, {
				value : 200,
				name : 'IOS'
			}, {
				value : 360,
				name : 'PC'
			}, {
				value : 100,
				name : 'Ohter'
			} ]
		} ]
	};
	//初始化echarts实例
	var myChart1 = echarts.init(this.getElementById('chartmain1'));
	var myChart2 = echarts.init(this.getElementById('chartmain2'));
	var myChart3 = echarts.init(this.getElementById('chartmain3'));
	//使用制定的配置项和数据显示图表
	myChart1.setOption(option1);
	myChart2.setOption(option2);
	myChart3.setOption(option3);
	
 

 </script>


</body>
</html>
