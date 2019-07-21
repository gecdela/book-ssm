	//指定图标的配置和数据

//折线图
	var line = {
			title : {
				text : 'ECharts 数据统计'
			},
			tooltip : {},
			legend : {
				data : [ '用户来源' ]
			},

			xAxis : {
				data : [],
			},
			yAxis : {
				data : [],
			},
			series : [ {
				name : '人数',
				type : 'line',
				data:  [],

			} ]
		}; 
//	柱状图
		var bar = {
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

//	圆饼图
	var pie = {
		title : {
			text : 'ECharts 数据统计'
		},
		tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)",
		},
		series : [ {
			name : '访问量',
			type : 'pie',
			radius : '60%',
			data : []
		} ],
		
	};
	
	//area图
	var area = {
		    title:{
				text : 'ECharts 数据统计'
		    },
		    tooltip:{},
		    xAxis: {
		        type: 'category',
		        boundaryGap: false,
		        data: []
		    },
		    yAxis: {
		        type: 'value'
		    },
		    series: [{
		        data: [],
		        type: 'line',
		        areaStyle: {}
		    }]
		};
