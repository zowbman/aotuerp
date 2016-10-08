<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<div data-options="fit:true,border:false," class="easyui-layout">
	<div data-options="region:'west',split:true,title:'任务管理',border:false," style="width:250px;padding:10px;">
		<input type="hidden" name="treasureTaskNav" value="${twoNav}">
		<ul id="treasureTaskNav"></ul><!-- treasureTaskNav结束 -->
	</div>
	<!-- 左结束 -->
	<div data-options="region:'center',title:'管理信息',border:false,">
		<div id="treasureTaskMtabs">
			<div title="起始页" style="padding:0 10px;display:block;overflow:hidden;">
				Welcome!
			</div>
		</div>
	</div><!-- 中间结束 -->
</div>

<script type="text/javascript" src="js/treasure/task/aotuerp.aotutreasuretask.js"></script>

