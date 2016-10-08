<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<link rel="stylesheet" type="text/css" href="css/warehouse/aotuerp.aotuwarehouse.save.css">
<table id="aotuwarehouse">
</table><!-- aotuwarehouse结束 -->
<div id="aotuwarehouse_tool" style="padding:5px;">
	<div style="margin-bottom:5px;">
		<s:a href="javascript:void(0);" action="sysmm_add" cssClass="easyui-linkbutton" plain="true" onclick="aotuwarehouse_tool.add()">添加</s:a>
		<s:a href="javascript:void(0);" action="sysmm_edit" cssClass="easyui-linkbutton" plain="true" onclick="aotuwarehouse_tool.edit()">修改</s:a>
		<s:a href="javascript:void(0);" action="sysmm_delete" cssClass="easyui-linkbutton" plain="true" onclick="aotuwarehouse_tool.remove()">删除</s:a>
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="aotuwarehouse_tool.reload()">刷新</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="aotuwarehouse_tool.redo()">取消选择</a>
	</div>
<div style="padding: 0 0 0 7px;color:#333;">
		<!-- 查询帐号:<input type="text" class="textbox" name="user" style="width:110px"> -->
	</div>
</div><!-- aotuwarehouse_tool结束 -->

<!-- 仓库添加 -->
<form id="aotuwarehouse_add" style="margin:0;padding:25px;color:#333;">
	<table class="save_table" cellspacing="0" cellpadding="0" style="width:100%;">
		<tr>
			<th>仓库名称:</th>
			<td colspan="3"><input type="text" name="spWarehousename" class="textbox"></td>
		</tr>
		<tr>
			<th>仓库管理员:</th>
			<td >
				<input type="text" id="empwarehouse" class="textbox">
				<input type="hidden" name="empSpId" >
				<input type="hidden" name="empSpEpId" >
			</td>
			<th>拼音码:</th>
			<td ><input type="text" name="spPinyin" class="textbox disabled" readonly="readonly"></td>
		</tr>

		<tr>
			<th>仓库地址:</th>
			<td colspan="3"><input type="text" name="spAddress" class="textbox"></td>
		</tr>
		
		
		<tr >
			<th>备注:</th>
			<td colspan="3"><textarea name="spRemark" class="textbox textarea"></textarea></td>
		</tr>
	</table>
</form>

<!-- 仓库更新 -->
<form id="aotuwarehouse_edit" style="margin:0;padding:25px;color:#333;">
	<input type="hidden" name="spId" class="textbox">
	<table class="save_table" cellspacing="0" cellpadding="0" style="width:100%;">
		<tr>
			<th>仓库名称:</th>
			<td colspan="3"><input type="text" name="spWarehousename" class="textbox"></td>
		</tr>
		<tr>
			<th>仓库管理员:</th>
			<td >
				<input type="text" id="empwarehouse_edit" class="textbox">
				<input type="hidden" name="empSpId" >
				<input type="hidden" name="empSpEpId" >
			</td>
			<th>拼音码:</th>
			<td ><input type="text" name="spPinyin" class="textbox disabled" readonly="readonly"></td>
		</tr>

		<tr>
			<th>仓库地址:</th>
			<td colspan="3"><input type="text" name="spAddress" class="textbox"></td>
		</tr>
		
		
		<tr >
			<th>备注:</th>
			<td colspan="3"><textarea name="spRemark" class="textbox textarea"></textarea></td>
		</tr>
	</table>
</form>

<script type="text/javascript" src="js/warehouse/aotuerp.aotuwarehouse.js"></script>
<script type="text/javascript" src="js/util/util.pinyin.js"></script>

