<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<link rel="stylesheet" type="text/css" href="css/commodity/aotuerp.aotucommodity.save.css">

<input id="aotucommodity_spu" type="hidden"><!-- aotucommodity_spu结束 -->
<input id="aotucommodity_sku" type="hidden"><!-- aotucommodity_sku结束 -->

<table id="aotucommodity">
</table><!-- aotucommodity结束 -->

<div id="aotucommodity_tool" style="padding:5px;">
	<div style="margin-bottom:5px;">
		<s:a href="javascript:void(0);" action="sysmm_add" cssClass="easyui-linkbutton" plain="true" onclick="aotucommodity_tool.add()">添加</s:a>
		<s:a href="javascript:void(0);" action="sysmm_edit" cssClass="easyui-linkbutton" plain="true" onclick="aotucommodity_tool.edit()">修改</s:a>
		<s:a href="javascript:void(0);" action="sysmm_delete" cssClass="easyui-linkbutton" plain="true" onclick="aotucommodity_tool.remove()">删除</s:a>
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="aotucommodity_tool.reload()">刷新</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="aotucommodity_tool.redo()">取消选择</a>
	</div>
<div style="padding: 0 0 0 7px;color:#333;">
		<!-- 查询帐号:<input type="text" class="textbox" name="user" style="width:110px"> -->
	</div>
</div><!-- aotucommodity_tool结束 -->

<!-- 添加商品页面 -->
<form id="aotucommodity_add" style="margin:0;color:#333;">
	<div class="content easyui-accordion">
		<div title="分类以及品牌选择" data-options="collapsed:true,collapsible:false" style="overflow:auto;padding:10px;height:599px;">
			<div id="category_brands">
				<div id="category01"></div><!-- category01结束 -->
				<div id="category02"></div><!-- category02结束 -->
				<div id="brands"></div><!-- brands结束 -->
				<div id="agreement">
					<textarea class="textarea"></textarea>
					<input type="button" id="agreementBtn" value="我已阅读以下规则，现添加发布宝贝" >
				</div>
			</div><!-- category_brands结束 -->
		</div>
		<div title="商品基本属性" data-options="collapsed:true,collapsible:false"
			style="padding:10px;overflow:auto;height:599px;">
			<div class="sp_spu_brand">
				<span>品牌：</span>
				<input id="sp_spu_brand" type="text">
			</div><!-- sp_spu_brand结束 -->
			<div id="sp_spu_pro">
				<span class="title">商品属性：</span>
				<div id="sp_spu_pro_tbodyAppend" class="skin"></div><!-- sp_spu_pro_tbodyAppend结束 -->
			</div><!-- sp_spu_pro结束 -->
			<ul class="sp_ul">
				<li><span>商品标题：</span><input name="spPdspu" class="textbox" type="text">
				</li>
				<li><span>一口价：</span><input id="sp_price" class="textbox" type="text">
				</li>
			</ul>
			<div id="sp_sku_pro">
				<span class="title">商品规格：</span>
				<div id="sp_sku_pro_tbodyAppend" class="skin"></div>
				<div id="createTable"></div>
			</div><!-- sp_sku_pro结束 -->
			<ul class="sp_ul">
				<li><span>数量：</span><input id="sp_quantity"  class="textbox" type="text"></li>
			</ul>
		</div>
	</div>
</form>

<!-- 修改商品页面 -->
<form id="aotucommodity_edit" style="margin:0;color:#333;">
	<div id="category_brands_edit">
		<span>类别：</span>
	</div>

	<div id="sp_spu_pro_edit">
		<span class="title">商品属性：</span>
		<div id="sp_spu_pro_tbodyAppend_edit" class="skin"></div><!-- sp_spu_pro_tbodyAppend_edit结束 -->
	</div><!-- sp_spu_pro_edit -->
	<div id="sp_sku_pro_edit">
		<span class="title">商品规格：</span>
		<div id="sp_sku_pro_tbodyAppend_edit" class="skin"></div><!-- sp_sku_pro_tbodyAppend_edit结束 -->
	</div><!-- sp_sku_pro_edit结束 -->
</form>

<script type="text/javascript" src="js/commodity/aotuerp.aotucommodity.js"></script>

