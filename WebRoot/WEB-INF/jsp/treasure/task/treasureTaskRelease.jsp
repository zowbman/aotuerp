<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<link rel="stylesheet" type="text/css" href="css/treasure/task/aotuerp.aotutreasuretask.save.css">

<div id="treasureTaskRelease-panel">
	<!-- 工具栏 -->
	<div id="treasureTaskRelease_tool">
		<a id="importTreasureForTask" href="javascript:void(0);">选择宝贝</a><!-- importPdSku结束 -->
		<a id="removePdSku" href="javascript:void(0);">移除商品</a><!-- addPdSku结束 -->
	</div><!-- treasureTaskRelease-panel结束 -->
	
	<!-- 宝贝上架订单 -->
	<form id="treasureTaskRelease_form" style="margin:0;color:#333;">
	</form>
</div><!-- treasureTaskRelease_form结束 -->

<!-- 选择宝贝dialog -->
<div id="importTreasureForTask-dialog">
	<div id="importTreasureForTask-datagrid"></div>
</div>



<script type="text/javascript" src="js/treasure/task/aotuerp.aotutreasurerelease.js"></script>

