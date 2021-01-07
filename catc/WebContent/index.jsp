<%@page import="main.App"%>
<%@page import="data.ConstantData"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>catc</title>
	<script language=javascript src="draw.js"></script>
</head>
<body>
	Welcome to catc.<br>
	Version: <%=ConstantData.str_Version %><br>
	Tracks: <%=App.getApp().getTrackPoList().getTrackPos().size() %><br>
	Stracks: <%=App.getApp().getSurfaceTrackPoList().getTrackPos().size() %><br>
	Fpls: <%=App.getApp().getFplList().getFpls().size() %><br>
</body>
</html>