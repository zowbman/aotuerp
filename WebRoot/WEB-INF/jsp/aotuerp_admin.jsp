<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'spat_admin.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="css/index/aotuerp.admin.css">
</head>
<body class="easyui-layout">
	<div data-options="region:'north',title:'header',noheader:true," style="height:60px;background:#666">
		<div class="logo">凹凸空间进销存管理系统</div>
		<div class="logout">您好,admin | <a href="#">退出</a></div>
	</div><!-- 头结束 -->
	<div data-options="region:'center'">
		<div id="tabs">
			<div title="起始页" style="padding:0 10px;display:block;overflow:hidden;">
				Welcome!
			</div>
		</div>
	</div><!-- 中间结束 -->
	<div data-options="region:'west',split:true,title:'导航'," style="width:200px;">
		<div id="nav"></div>
	</div><!-- 左结束 -->
	<div data-options="region:'south',title:'footer',noheader:true," style="height:35px;line-height:30px;text-align:center;">
		&copy2015 aotuspace Web. J2EE and EasyUI
	</div><!-- 下结束 -->
	<script type="text/javascript" src="easyui/jquery.min.js"></script>
	<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/index/aotuerp.admin.js"></script>
	<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="js/formjson.js"></script>
	<script type="text/javascript" src="js/jquery.form.js"></script>
</body>
</html>
