<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<link rel="stylesheet" type="text/css" href="css/purchase/aotuerp.aotupurchase.save.css">

<div data-options="fit:true,border:false" class="easyui-layout">
	<div data-options="region:'north',split:true,title:'进货单查询',border:false" style="height:60%;">
		<div id="purchaseStorageInquire-datagrid"></div>
		<div id="purchaseStorageInquire_tool" style="padding:5px;">
			<div style="margin-bottom:5px;">
				<!-- <s:a href="javascript:void(0);" action="sysmm_add" cssClass="easyui-linkbutton" plain="true" onclick="aotucmsmanager_tool.add()">添加</s:a>
				<s:a href="javascript:void(0);" action="sysmm_edit" cssClass="easyui-linkbutton" plain="true" onclick="aotucmsmanager_tool.edit()">修改</s:a>
				<s:a href="javascript:void(0);" action="sysmm_delete" cssClass="easyui-linkbutton" plain="true" onclick="aotucmsmanager_tool.remove()">删除</s:a> -->
				
				<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="purchaseStorageInquire_tool.check()">审核</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="purchaseStorageInquire_tool.reload()">刷新</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="purchaseStorageInquire_tool.redo()">取消选择</a>
			</div>
		</div><!-- aotumanager_tool结束 -->
	</div><!-- 上结束 -->
	<div data-options="region:'center',border:false"  style="height:40%;">
		<div id="purchaseStorageInquirePd-datagrid"></div>
	</div><!-- 中间结束 -->
</div>

<!-- 单据审核  -->
<div id="purchaseStorageInquire-check-dialog">
	<div class="purchaseStorageNum">单号：<span></span></div>
	<div class="explanation">单据审核后将修改库存数量</div>
</div>

<script type="text/javascript" src="js/purchase/aotuerp.aotupurchasestorageinquire.js"></script>

