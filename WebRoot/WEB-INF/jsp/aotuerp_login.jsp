<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>凹凸空间进销存管理系统</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css"
	href="easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="css/index/aotuerp.login.css">

</head>

<body>
	<div id="aotuerpLogin">
		<p>
			凹凸管理员帐号：<input type="text" id="aotumanagerAccount" class="textbox">
		</p>
		<p>
			凹凸管理员密码：<input type="password" id="aotumanagerPassword"
				class="textbox">
		</p>
	</div><!-- atcmsLogin结束 -->
	<div id="aotuerpLoginBtn">
		<a href="javascript:void(0);"  class="easyui-linkbutton">登录</a>
	</div><!-- aotuerpLoginBtn 结束 -->
	<script type="text/javascript" src="easyui/jquery.min.js"></script>
	<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/index/aotuerp.login.js"></script>
</body>
</html>
