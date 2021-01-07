<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <style>
        #header {
            background-color:black;
            color:white;
            text-align:center;
            padding:5px;
        }
        #nav1 {
            line-height:30px;
            background-color:#eeeeee;
            /* height:400px; */
            width:100px;
            float:left;
            padding:10px;	      
        }
        #nav2 {
            line-height:30px;
            background-color:#eeeeee;
            /* height:400px; */
            width:100px;
            float:left;
            padding:10px;	      
        }
        #section {
            width:350px;
            float:left;
            padding:20px;	 	 
        }
        #footer {
            background-color:black;
            color:white;
            clear:both;
            text-align:center;
            padding:5px;	 	 
        }
        </style>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>echarts_test</title>
</head>

<body>

    <div id="header">
        <h1>Welcome to echarts</h1>
    </div>
    
    <div id="nav1">
        <input type="file" id="files"/>
        <button type="button" onclick="demo1()">demo1</button><br/>
        <button type="button" onclick="demo2()">demo2</button><br/>
        <button type="button" onclick="line1()">折线图1</button><br/>
        <button type="button" onclick="line2()">折线图2</button><br/>
        <button type="button" onclick="line3()">折线图3</button><br/>
        <button type="button" onclick="line4()">折线图4</button><br/>
        <button type="button" onclick="bar1()">柱状图1</button><br/>
        <button type="button" onclick="bar2()">柱状图2</button><br/>
        <button type="button" onclick="bar3()">柱状图3</button><br/>
        <button type="button" onclick="bar4()">柱状图4</button><br/>
        <button type="button" onclick="bar5()">柱状图5</button><br/>
        <button type="button" onclick="bar6()">柱状图6</button><br/>
        <button type="button" onclick="pie1()">饼图1</button><br/>
        <button type="button" onclick="pie2()">饼图2</button><br/>
        <button type="button" onclick="scatter1()">散点图1</button><br/>
        <button type="button" onclick="scatter2()">散点图2</button><br/>
        <button type="button" onclick="scatter3()">散点图3</button><br/>
        <button type="button" onclick="scatter4()">散点图4</button><br/>
        <button type="button" onclick="scatter5()">散点图5</button><br/>
        <button type="button" onclick="geo1()">地理图1（香港）</button><br/>
        <button type="button" onclick="geo2()">地理图2（中国）</button><br/>
        <button type="button" onclick="geo3()">地理图3（中国）</button><br/>
        <button type="button" onclick="geo4()">地理图4（中国）</button><br/>
        <button type="button" onclick="geo5()">地理图5（张家港）</button><br/>
        <button type="button" onclick="geo6()">地理图6（中国）</button><br/>
        <button type="button" onclick="comm1()">社区1</button><br/>
        <button type="button" onclick="comm2()">社区2</button><br/>
        <button type="button" onclick="comm3()">社区3</button><br/>
        <button type="button" onclick="comm4()">社区4</button><br/> 
    </div>
    <div id="nav2">
        <button type="button" onclick="kline1()">k线图1</button><br/>
        <button type="button" onclick="kline2()">k线图2</button><br/>
        <button type="button" onclick="kline3()">k线图3</button><br/>
        <button type="button" onclick="radar1()">雷达图1</button><br/>
        <button type="button" onclick="radar2()">雷达图2</button><br/>
        <button type="button" onclick="radar3()">雷达图3</button><br/>
        <button type="button" onclick="heat1()">热力图1</button><br/>
        <button type="button" onclick="heat2()">热力图2</button><br/>
        <button type="button" onclick="graph1()">关系图1</button><br/>
        <button type="button" onclick="graph2()">关系图2</button><br/>
        <button type="button" onclick="tree1()">树图1</button><br/>
        <button type="button" onclick="tree2()">树图2</button><br/>
        <button type="button" onclick="tree3()">矩形树图</button><br/>
        <button type="button" onclick="sun1()">旭日图1</button><br/>
        <button type="button" onclick="sun2()">旭日图2</button><br/>
        <button type="button" onclick="others1()">桑基图</button><br/>
        <button type="button" onclick="others2()">漏斗图</button><br/>
        <button type="button" onclick="others3()">仪表盘</button><br/>
        <button type="button" onclick="others4()">象形柱状图</button><br/>
        
    </div>
    
    <div id="section">
        <div id="main" style="width: 1200px;height:750px;"></div>
    </div>
    
    <div id="footer">
        ECharts
    </div>
    <script language=javascript src="${pageContext.request.contextPath}/draw/input.js"></script>
    
    <script language=javascript src="${pageContext.request.contextPath}/draw/jquery-3.4.1.min.js"></script>
    <script language=javascript src="${pageContext.request.contextPath}/draw/echarts/echarts.js"></script>
    <script language=javascript src="${pageContext.request.contextPath}/draw/echarts/extension/dataTool.js"></script>
    <script language=javascript src="${pageContext.request.contextPath}/draw/vintage.js"></script>
    <script language=javascript src="${pageContext.request.contextPath}/draw/demo.js"></script>
    <script language=javascript src="${pageContext.request.contextPath}/draw/line.js"></script>
    <script language=javascript src="${pageContext.request.contextPath}/draw/bar.js"></script>
    <script language=javascript src="${pageContext.request.contextPath}/draw/pie.js"></script>
    <script language=javascript src="${pageContext.request.contextPath}/draw/scatter.js"></script>
    <script language=javascript src="${pageContext.request.contextPath}/draw/geo.js"></script>
    <script language=javascript src="${pageContext.request.contextPath}/draw/community.js"></script>
    <script language=javascript src="${pageContext.request.contextPath}/draw/kline.js"></script>
    <script language=javascript src="${pageContext.request.contextPath}/draw/radar.js"></script>
    <script language=javascript src="${pageContext.request.contextPath}/draw/heat.js"></script>
    <script language=javascript src="${pageContext.request.contextPath}/draw/graph.js"></script>
    <script language=javascript src="${pageContext.request.contextPath}/draw/tree.js"></script>
    <script language=javascript src="${pageContext.request.contextPath}/draw/sun.js"></script>
    <script language=javascript src="${pageContext.request.contextPath}/draw/others.js"></script>
</body>
</body>
</html>





