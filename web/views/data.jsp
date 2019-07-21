<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>

<meta charset="utf-8" />
</head>
 <link rel="stylesheet" href="assets/bootstrap-3.3.5-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/morris/morris-0.4.3.min.css">
<!--
<script type="text/javascript" src="assets/morris/raphael-2.1.0.min.js"></script>
<script type="text/javascript" src="assets/scripts/jquery-1.10.2.js"></script>
<script type="text/javascript" src="assets/morris/morris.js"></script>

<script src="assets/bootstrap-3.3.5-dist/js/jquery.min.js"></script>
<script src="assets/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
 -->
     <!-- JS Scripts-->
    <!-- jQuery Js -->
    <script src="assets/scripts/jquery-1.10.2.js"></script>
      <!-- Bootstrap Js -->
    <script src="assets/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
     <!-- Morris Chart Js -->
     <script src="assets/morris/raphael-2.1.0.min.js"></script>
          <script src="assets/morris/morris.js"></script>

<ul id="myTab" class="nav nav-tabs">
		<li class="active"><a href="#bar" data-toggle="tab">柱状图</a></li>
		<li><a href="#line" data-toggle="tab">折线图</a></li>
		<li><a href="#circle" data-toggle="tab">圆饼图</a></li>
		<li><a href="#curve" data-toggle="tab">曲线图</a></li>
</ul>
<div id="myTabContent" class="tab-content">
	<div class="tab-pane fade in active" id="bar">
					<div class="row">
						<div class="col-sm-4">
							<div class="panel panel-default">
								<div class="panel-heading">部门统计图</div>
								<div class="panel-body">
								<!-- <div id="employee-bar"></div> -->
								</div>
							</div>
						</div>

					</div>
	</div>
<div class="tab-pane fade" id="line">
						<div class="col-sm-4">
							<div class="panel panel-default">
								<div class="panel-heading">bathroom-chart</div>
								<div class="panel-body">
								<div id="employee-circle"></div>			
								</div>
							</div>
						</div>
	</div>
		<!-- 
	<div class="tab-pane fade" id="circle">
					<div class="row">
						<div class="col-md-6 col-sm-12 col-xs-12">
							<div class="panel panel-default">
								<div class="panel-heading">BarChart</div>
								<div class="panel-body">
								<div id="employee-bar"></div>
								</div>
							</div>
						</div>
					</div>
	</div>
	<div class="tab-pane fade" id="curve">
						<div class="col-md-6 col-sm-12 col-xs-12">
							<div class="panel panel-default">
								<div class="panel-heading">hero-area</div>
								<div class="panel-body">
								</div>
							</div>
						</div>

	</div> -->
</div>
<script type="text/javascript">
$(document).ready(function(){
	$.ajax({
		url:"getDatas",
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		success:function(result){
			if(result.length!=0)
				{
				Morris.Bar({
			         element: "employee-bar",
			         data: result,
			         xkey: "deptName",
			         ykeys: ["count"],
			         labels: ["人数"],
			         barRatio: 0.4,
			         xLabelAngle: 35,
			         hideHover: "auto",
			         barColors: ["#5bc0de"]
			       });
				for(var i = 0; i < result.length;i++)

		        Morris.Line({
		            element: "employee-chart",
		            data: result,
		            xkey: ["deptName"],
		            ykeys: ["count"],
		            labels: ["人数"]
		        });
				}
		}
	});

});
$(document).ready(function(){
	$.ajax({
		url:"getPer",
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		success:function(result){
			if(result.length!=0)
				{
		        Morris.Donut({
		            element: "employee-circle",
		            data: result,
		            colors: ["#f0ad4e"],
		            formatter: function(y) {
		              return y + "%";
		            }
		          });
				}
		}
	});

});
</script>
<script type="text/javascript">
        var bathroomData = [
            { time: "2014-06-17T10:54:01", r0: 4, r1: 79, r2: 7, r3: 180},
            { time: "2014-06-17T11:09:01", r0: 4, r1: 79, r2: 7, r3: 79 },
            { time: "2014-06-17T11:24:01", r0: 11, r1: 88, r2: 13, r3: 100 },
            { time: "2014-06-17T11:39:01", r0: 11, r1: 69, r2: 12, r3: 73 }
        ];
        var bathroomIDs = [ "r0", "r1", "r2", "r3" ];
        var bathroomNames = [ "t1(boy)", "t1(girl)", "t2(boy)", "t2(girl)" ];
        
        Morris.Line({
            element: "bathroom-chart",
            data: bathroomData,
            xkey: "time",
            ykeys: bathroomIDs,
            labels: bathroomNames
        });
        
        Morris.Donut({
            element: "hero-donut",
            data: [
              {
                label: "Development",
                value: 25
              }, {
                label: "Sales & Marketing",
                value: 40
              }, {
                label: "User Experience",
                value: 25
              }, {
                label: "Human Resources",
                value: 10
              }
            ],
            colors: ["#f0ad4e"],
            formatter: function(y) {
              return y + "%";
            }
          });
        Morris.Area({
            element: "hero-area",
            data: [
              {
                period: "2010 Q1",
                iphone: 2666,
                ipad: null,
                itouch: 2647
              }, {
                period: "2010 Q2",
                iphone: 2778,
                ipad: 2294,
                itouch: 2441
              }, {
                period: "2010 Q3",
                iphone: 4912,
                ipad: 1969,
                itouch: 2501
              }, {
                period: "2010 Q4",
                iphone: 3767,
                ipad: 3597,
                itouch: 5689
              }, {
                period: "2011 Q1",
                iphone: 6810,
                ipad: 1914,
                itouch: 2293
              }, {
                period: "2011 Q2",
                iphone: 5670,
                ipad: 4293,
                itouch: 1881
              }, {
                period: "2011 Q3",
                iphone: 4820,
                ipad: 3795,
                itouch: 1588
              }, {
                period: "2011 Q4",
                iphone: 15073,
                ipad: 5967,
                itouch: 5175
              }, {
                period: "2012 Q1",
                iphone: 10687,
                ipad: 4460,
                itouch: 2028
              }, {
                period: "2012 Q2",
                iphone: 8432,
                ipad: 5713,
                itouch: 1791
              }
            ],
            xkey: "period",
            ykeys: ["iphone", "ipad", "itouch"],
            labels: ["iPhone", "iPad", "iPod Touch"],
            hideHover: "auto",
            lineWidth: 2,
            pointSize: 4,
            lineColors: ["#a0dcee", "#f1c88e", "#a0e2a0"],
            fillOpacity: 0.5,
            smooth: true
          });
    </script>
</body>
</html>