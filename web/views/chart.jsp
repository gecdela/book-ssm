<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>无标题文档</title>

<script type="text/javascript" src="assets/echart/echarts.js"></script>
<link rel="stylesheet"
	href="assets/bootstrap-3.3.5-dist/css/bootstrap.min.css">
<script src="assets/bootstrap-3.3.5-dist/js/jquery.min.js"></script>
<script src="assets/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>

</head>
<body>
				<div class="row">
					<div class="margin-top-20">
						<ul class="breadcrumb">
							<li><a href="#">当前位置</a></li>
							<li><a href="#">首页</a></li>
							<li class="active"><a href="#">数据管理</a></li>
						</ul>
					</div>
				</div>
	<ul id="myTab" class="nav nav-tabs">
		<li class="active"><a href="#bar" data-toggle="tab">折线图</a></li>
		<li><a href="#line" data-toggle="tab">柱状图</a></li>
		<li><a href="#circle" data-toggle="tab">区域图</a></li>
		<li><a href="#curve" data-toggle="tab">圆饼图</a></li>
	</ul>
	<div id="myTabContent" class="tab-content">
		<div class="tab-pane fade in active" id="bar">
			<div class="row">
				<div class="col-md-4">
					<div class="panel panel-default">
						<div class="panel-heading">部门统计图</div>
						<div class="panel-body">
							<div id="employee-bar" class="col-md-4" style="height: 250px"></div>
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
						<div id="chartmain1" class="col-md-4" style="height: 250px"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="tab-pane fade" id="circle">
			<div class="col-md-4">
				<div class="panel panel-default">
					<div class="panel-heading">bathroom-chart</div>
					<div class="panel-body">
 						<div id="chartmain2" class="col-md-4" style="height: 250px"></div> 
					</div>
				</div>
			</div>
		</div>
		<div class="tab-pane fade" id="curve">
			<div class="col-md-4">
				<div class="panel panel-default">
					<div class="panel-heading">bathroom-chart</div>
					<div class="panel-body">
						<div id="chartmain3" class="col-md-4" style="height: 250px"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 指定图标的配置和数据格式的js-->
	<script type="text/javascript" src="assets/scripts/CharData.js"></script>
	<script type="text/javascript">
	var charts = [];
	var myChart0;
	var myChart1;
	var myChart2;
	var myChart3;
    //初始化echarts实例
	myChart0 = echarts.init(document.getElementById('employee-bar'));
	myChart1 = echarts.init(document.getElementById('chartmain1'));
	myChart2 = echarts.init(document.getElementById('chartmain2'));
	myChart3 = echarts.init(document.getElementById('chartmain3'));

	//使用制定的配置项和数据显示图表
	myChart0.setOption(line);
	myChart1.setOption(bar);
	myChart2.setOption(area);
	myChart3.setOption(pie);
	myChart0.showLoading();//延时加载
$(document).ready(function(){
$.ajax({
	url:"getDatas",
	dataType:"json",
	contentType:"application/json;charset=utf-8",
	success:function(result)
	{
		var data = result.count;
		var per = result.per;
	   if(data.length!=0)
	   {
		var xaxis=[]; //装填横坐标
		var yaxis=[];  //装填纵坐标
		for (var i = 0; i < data.length; i++) 
		{
			xaxis.push(data[i].deptName);
			yaxis.push(data[i].count);
		}
		/* 隐藏加载动画 */
		myChart0.hideLoading(); 
		myChart0.setOption({
					xAxis : {
						data : xaxis,
					},
					yAxis : {
						type : 'value',
					},
					series : [ {
						data:  yaxis,
					} ]
				});
		myChart1.setOption({
			xAxis : {
				data : xaxis,
			},
			series : [ {
				data:  yaxis,
			} ]
		});
		myChart2.setOption({
		    xAxis: {
		        data: xaxis
		    },

		    series: [{
		        data: yaxis,

		    }]
		});
 		myChart3.setOption({
			series : [ {
				data: per,
			} ],
		    legend: {
		        orient: 'vertical',
		        x: 'right',
		        data:xaxis
		    },
		}); 
 		

		
	  }
	}
	});

			
			
			/* 设置在Bootstrap标签页tab中也能正常加载chart */
			charts.push(myChart0);
			charts.push(myChart1);
			charts.push(myChart2);
			charts.push(myChart3);
			$('a[data-toggle="tab"]').on('shown.bs.tab', function(e) {
				for (var i = 0; i < charts.length; i++) {

					charts[i].resize();
				}
			});

});
</script>

</body>
</html>
