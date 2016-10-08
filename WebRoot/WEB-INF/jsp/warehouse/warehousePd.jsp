<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<div data-options="fit:true" class="easyui-layout">
	<div data-options="region:'west',split:true,title:'仓库'," style="width:250px;padding:10px;">
		<ul id="warehouseNav"></ul><!-- warehouseNav结束 -->
	</div>
	<!-- 左结束 -->
	<div data-options="region:'center',title:'商品信息'">
		<table id="aotuerpSkupd">
		</table><!-- aotuerpSkupd结束 -->
		<div id="aotuerpSkupd_tool" style="padding:5px;">
			<div style="margin-bottom:5px;">
				<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="aotuerpSkupd_tool.reload()">刷新</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="aotuerpSkupd_tool.redo()">取消选择</a>
			</div>
			<div style="padding: 0 0 0 7px;color:#333;">
				<!-- 查询:<input type="text" class="textbox" name="user" style="width:110px"> -->
			</div>
		</div><!-- aotuerpSkupd_tool结束 -->
	</div><!-- 中间结束 -->
</div>
<script type="text/javascript" src="js/warehouse/aotuerp.aotuwarehousepd.js"></script>

