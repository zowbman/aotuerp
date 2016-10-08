<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<link rel="stylesheet" type="text/css" href="css/treasure/aotuerp.aotutreasure.save.css">

<table id="aotutreasure-datagrid">
</table><!-- aotutreasure结束 -->

<div id="aotutreasure_tool" style="padding:5px;">
	<div style="margin-bottom:5px;">
		<s:a href="javascript:void(0);" cssClass="easyui-linkbutton" plain="true" onclick="aotutreasure_tool.check()">审核</s:a>
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="aotutreasure_tool.reload()">刷新</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="aotutreasure_tool.redo()">取消选择</a>
	</div>
	<div style="padding: 0 0 0 7px;color:#333;">
	</div>
</div><!-- aotutreasure_tool结束 -->

<!-- 宝贝详情 -->
<div id="aotutreasure_detail-dialog">
</div>
<!-- 宝贝上架审核操作 -->
<div id="aotutreasure_check-dialog">
</div>

<script type="text/javascript" src="js/treasure/aotuerp.aotutreasure.js"></script>

