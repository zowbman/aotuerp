<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<link rel="stylesheet" type="text/css" href="css/purchase/aotuerp.aotupurchase.save.css">
<form id="purchaseStorageOrders">
	<!-- 工具栏 -->
	<div id="purchaseStorageOrders_tool">
		<!-- <a id="addPdSku" href="javascript:void(0);">添加商品</a> --><!-- addPdSku结束 -->
		<!-- <a id="removePdSku" href="javascript:void(0);">移除商品</a> --><!-- addPdSku结束 -->
		<a id="importPurchaseOrders" href="javascript:void(0);">导入订单</a><!-- importPurchaseOrders结束 -->
		<a href="javascript:void(0);" id="purchaseStorageOrdersSubmit">提交订单</a>
		<a id="savePdSku-pso" href="javascript:void(0);" style="display:none;">保存</a><!-- addPdSku结束 -->
		<a id="redoPdSku-pso" href="javascript:void(0);" style="display:none;">取消编辑</a><!-- addPdSku结束 -->
	</div><!-- purchaseOrders_tool结束 -->
	
	<div id="purchaseStorageOrders-body" style="height:100px;margin-left:20px;">
		<div style="margin:16 0;">
			<h1 style="display: inline;">进货单</h1>
			<h3 style="padding:0 0 0 30px;display: inline;">
			进货单号：
			</h3>
			<input type="hidden" id="purchaseStorageOrdersNum-hidden">
			<input type="hidden" id="purchaseOrdersNum-pso-hidden">
			<h3 style="padding:0 0 0 30px;display: inline;">开单日期：${CreateDate }</h3>
		</div>
		<ul id="baseInfo-pso">
			<li><span><font color="red">(*)</font>供货商：</span><input id="spSupplier-pso" type="text"><input id="spSupplier-pso-hidden" type="hidden"></li>
			<li><span><font color="red">(*)</font>经办人：</span><input id="empl-pso" type="text"><input id="emplId-pso-hidden"  type="hidden"></li>
			<li><span><font color="red">(*)</font>部门：</span><input id="depart-pso" class="textbox disabled" type="text" readonly="readonly"></li>
			<li><span><font color="red">(*)</font>仓库：</span><input id="warehouse-pso" type="text"><input id="warehouse-pso-hidden"  type="hidden"></li>
			<li><span><font color="red">(*)</font>付款日期：</span><input id="paymentDate" type="text" class="textbox"><div id="paymentD"></div></li>
			<li><span>备注：</span><input id="remark-pso" type="text" class="textbox"></li>
		</ul>
	</div>
	<div style="width:99%;top:205px;bottom:0;left:1;position:absolute;">
		<div id="purchaseStorageOrdersPd" style="height:100%;position:absolute;" ></div>
	</div>
</form><!-- purchaseStorageOrders结束 -->

<!-- 导入订单 -->
<div id="importPurchaseOrders-body">
	<div id="importPurchaseOrders-datagrid" style="height:100%"></div>
</div>


<!-- 添加商品 -->
<!-- <div id="addPdSku-body">
	<div class="hint">
		<span>温馨提示:双击选择。</span>
	</div>
	<div style="width:98%;top:70px;bottom:43;left:10;position:absolute;">
		<div id="addPdSku-datagrid"></div>
	</div>
</div>
 -->
<!-- <div id="purchaseOrdersPd_tool" style="padding:5px;">
	<div style="margin-bottom:5px;">
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="purchaseOrdersPd_tool.reload()">刷新</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="purchaseOrdersPd_tool.redo()">取消选择</a>
	</div>
	<div style="padding: 0 0 0 7px;color:#333;">
		查询帐号:<input type="text" class="textbox" name="user" style="width:110px">
	</div>
</div> --><!-- purchaseOrdersPd_tool结束 -->

<!-- 供货商dialog -->
<div id="purchaseOrders-supplier-pso">
	<div id="purchaseOrders-supplier-pso-datagrid"></div>
</div>

<!-- 部门dialog -->
<!-- <div id="purchaseOrders-depart"></div> -->

<!-- 仓库dialog -->
<div id="purchaseOrders-warehouse-pso">
	<div id="purchaseOrders-warehouse-pso-datagrid"></div>
</div>

<!-- 经办人dialog -->
<div id="purchaseOrders-empl-pso">
	<div id="purchaseOrders-empl-pso-datagrid"></div>
</div>

<script type="text/javascript" src="js/purchase/aotuerp.aotupurchasestorageorders.js"></script>

