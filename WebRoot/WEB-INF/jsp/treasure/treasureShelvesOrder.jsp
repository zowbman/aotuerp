<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<link rel="stylesheet" type="text/css" href="css/treasure/aotuerp.aotutreasure.save.css">

<div id="treasureShelvesOrder-panel">
	<!-- 工具栏 -->
	<div id="treasureShelvesOrder_tool">
		<a id="importPdForTreasure" href="javascript:void(0);">导入商品</a><!-- importPdSku结束 -->
		<a id="removePdSku" href="javascript:void(0);">移除商品</a><!-- addPdSku结束 -->
	</div><!-- treasureShelvesOrder_tool结束 -->
	
	<!-- 宝贝上架订单 -->
	<form id="treasureShelvesOrder_form" style="margin:0;color:#333;">
	</form>
</div><!-- treasureShelvesOrder-panel结束 -->

<!-- 导入商品dialog -->
<div id="importPdForTreasure-dialog">
	<div id="importPdForTreasure-datagrid"></div>
</div>

<!-- 导入商品skudialog -->
<div id="importPdSkuForTreasure-dialog">
	<div id="importPdSkuForTreasure-datagrid"></div>
</div>

<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="js/treasure/aotuerp.aotutreasureorder.js"></script>

