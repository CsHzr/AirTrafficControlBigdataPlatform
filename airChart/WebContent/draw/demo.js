
function demo1() {
	var url = 'draw?type=demo1';
	    
    var req;
    if (window.XMLHttpRequest) {
        req = new XMLHttpRequest();
    }else{
		// code for IE6, IE5
		req = new ActiveXObject("Microsoft.XMLHTTP");
    }
    
    req.open("GET", url, true);
    //req.responseType = "json";

    req.onreadystatechange = function() {
    	if (this.readyState == 4 && this.status == 200) {
			var ret = req.responseText
			console.log(ret)
			var symbol = eval('(' + ret + ')')
			//销毁之前画的图
			echarts.dispose(document.getElementById('main')); 
			// 基于准备好的dom，初始化echarts实例
			var myChart = echarts.init(document.getElementById('main'),'vintage');

			// 指定图表的配置项和数据
			var option = {
			    title: {
			        text: '飞行计划数统计'
			    },
			    tooltip: {},
			    legend: {
			        data:['飞行计划数', '航班数']
			    },
			    xAxis: {
			        data: ["2019-02-02","2019-02-03","2019-02-04","2019-02-05","2019-02-06","2019-02-07"]
			    },
			    yAxis: {},
			    series: [{
			        name: '飞行计划数',
			        type: 'bar',
			        data: [5, 20, 36, 10, 10, 20]
			    },
			    {
			        name: '航班数',
			        type: 'line',
			        data: [15, 25, 40, 15, 15, 25]
			    },
			    {
			    	//name: 'value',
			    	type: 'pie',
			    	center: ['65%',60],
			    	radius: 35,
			    	data: [
			    		{name: '正常', value: 20},
			    		{name: '异常', value: 54},
			    		{name: '错误', value: 32}
			    	]
			    }]
			};
			option.xAxis.data = symbol.xAxis;
			option.series[0].data = symbol.data;
	        // 使用刚指定的配置项和数据显示图表。
	        myChart.setOption(option);
    	}
	};
	req.send();
}

function demo2() {
	var url = 'draw?type=demo2';
	    
    var req;
    if (window.XMLHttpRequest) {
        req = new XMLHttpRequest();
    }else{
		// code for IE6, IE5
		req = new ActiveXObject("Microsoft.XMLHTTP");
    }
    
    req.open("GET", url, true);
    //req.responseType = "json";

    req.onreadystatechange = function() {
    	if (this.readyState == 4 && this.status == 200) {
			var ret = req.responseText
			console.log(ret)
			var symbol = eval('(' + ret + ')')
			//销毁之前画的图
			echarts.dispose(document.getElementById('main')); 
			// 基于准备好的dom，初始化echarts实例
			var myChart = echarts.init(document.getElementById('main'),'vintage');

			// 指定图表的配置项和数据
			var option = {
				color: ['#003366', '#006699', '#4cabce'],
			    title: {
			        text: '起飞机场统计'
			    },
			    tooltip: {},
			    legend: {
			        data:['北京', '上海', '广州']
			    },
			    xAxis: {
			        data: ["2019-02-02","2019-02-03","2019-02-04","2019-02-05","2019-02-06","2019-02-07"]
			    },
			    yAxis: {},
			    series: [{
			        name: '北京',
			        type: 'bar',
			        label: {
		                show: true,
		                position: 'top'
		            },
			        data: [5, 20, 36, 10, 10, 20]
			    },
			    {
			        name: '上海',
			        type: 'bar',
			        label: {
		                show: true,
		                position: 'top'
		            },
			        data: [15, 25, 40, 15, 15, 25]
			    },
			    {
			        name: '广州',
			        type: 'bar',
			        label: {
		                show: true,
		                position: 'top'
		            },
			        data: [15, 25, 40, 15, 15, 25]
			    }]
			};
			option.xAxis.data = symbol.xAxis;
			option.series[0].data = symbol.data[0];
			option.series[1].data = symbol.data[1];
			option.series[2].data = symbol.data[2];
	        // 使用刚指定的配置项和数据显示图表。
	        myChart.setOption(option);
    	}
	};
	req.send();
}