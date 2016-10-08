<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<link rel="stylesheet" type="text/css" href="css/purchase/aotuerp.aotupurchase.save.css">
<form id="purchaseOrders">
	<!-- 工具栏 -->
	<div id="purchaseOrders_tool">
		<a id="addPdSku" href="javascript:void(0);">添加商品</a><!-- addPdSku结束 -->
		<a id="removePdSku" href="javascript:void(0);">移除商品</a><!-- addPdSku结束 -->
		<a href="javascript:void(0);" id="purchaseOrdersSubmit">提交订单</a>
		<a id="savePdSku" href="javascript:void(0);" style="display:none;">保存</a><!-- addPdSku结束 -->
		<a id="redoPdSku" href="javascript:void(0);" style="display:none;">取消编辑</a><!-- addPdSku结束 -->
	</div><!-- purchaseOrders_tool结束 -->
	
	<div id="purchaseOrders-body" style="height:100px;margin-left:20px;">
		<div style="margin:16 0;">
			<h1 style="display: inline;">进货订单</h1><h3 style="padding:0 0 0 30px;display: inline;">进货订单编号：${PurchaseOrdersNum.spPurchaseOrderId}</h3>
			<input type="hidden" id="purchaseOrdersNum-hidden" value="${PurchaseOrdersNum.spId}">
			<h3 style="padding:0 0 0 30px;display: inline;">开单日期：${CreateDate }</h3>
		</div>
		<ul id="baseInfo">
			<li><span><font color="red">(*)</font>供货商：</span><input id="spSupplier" type="text"><input id="spSupplier-hidden" type="hidden"></li>
			<li><span><font color="red">(*)</font>经办人：</span><input id="empl" value="${spEmployeeBinfo.spEmployeeDinfo.spEprealname }" type="text"><input value="${spEmployeeBinfo.spEmployeeBinfoKey.spId }-${spEmployeeBinfo.spEmployeeBinfoKey.spEpid }" id="emplId-hidden"  type="hidden"></li>
			<li><span><font color="red">(*)</font>部门：</span><input id="depart" value="${spEmployeeBinfo.spEmployeeDepart.spEpdepartn }" class="textbox disabled" type="text" readonly="readonly"></li>
			<li><span><font color="red">(*)</font>订单合同号：</span><input id="orderContractId" type="text"  class="textbox"></li>
			<li><span><font color="red">(*)</font>仓库：</span><input id="warehouse" type="text"><input id="warehouse-hidden"  type="hidden"></li>
			<li><span><font color="red">(*)</font>预计到货：</span><input id="planArrivalDate" type="text" class="textbox"><div id="planAD"></div></li>
			<li><span>备注：</span><input id="remark" type="text" class="textbox"></li>
		</ul>
	</div>
	<div style="width:99%;top:205px;bottom:0;left:1;position:absolute;">
		<div id="purchaseOrdersPd" style="height:100%;position:absolute;" ></div>
	</div>
</form><!-- purchaseOrders结束 -->

<!-- 添加商品 -->
<div id="addPdSku-body">
	<div class="hint">
		<span>温馨提示:双击选择。</span>
	</div>
	<div style="width:98%;top:70px;bottom:43;left:10;position:absolute;">
		<div id="addPdSku-datagrid"></div>
	</div>
</div>

<div id="purchaseOrdersPd_tool" style="padding:5px;">
	<div style="margin-bottom:5px;">
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="purchaseOrdersPd_tool.reload()">刷新</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="purchaseOrdersPd_tool.redo()">取消选择</a>
	</div>
	<div style="padding: 0 0 0 7px;color:#333;">
		<!-- 查询帐号:<input type="text" class="textbox" name="user" style="width:110px"> -->
	</div>
</div><!-- purchaseOrdersPd_tool结束 -->

<!-- 供货商dialog -->
<div id="purchaseOrders-supplier">
	<div id="purchaseOrders-supplier-datagrid"></div>
</div>

<!-- 部门dialog -->
<div id="purchaseOrders-depart"></div>

<!-- 仓库dialog -->
<div id="purchaseOrders-warehouse">
	<div id="purchaseOrders-warehouse-datagrid"></div>
</div>

<!-- 经办人dialog -->
<div id="purchaseOrders-empl">
	<div id="purchaseOrders-empl-datagrid"></div>
</div>

<script type="text/javascript" src="js/purchase/aotuerp.aotupurchaseorders.js"></script>

