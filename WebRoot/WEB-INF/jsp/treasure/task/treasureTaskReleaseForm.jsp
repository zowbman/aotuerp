<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<input id="treasureId" value="${treasureInfo.spId }" type="hidden">
<!--  宝贝信息 -->

<div id="sp_task_treasure_info">
<span class="title">宝贝信息</span>
<div class="skin">
	宝贝编号：${treasureInfo.spId }
</div>
</div><!-- sp_task_treasure_info结束 -->

<!-- 任务信息 -->
<div id="sp_task_info">
	<span class="title">任务信息：</span>
	<div class="skin">
		<span class="title">提成奖励：</span>
		<div id="sp_task_mode-tabs" class="easyui-tabs" style="padding:10px;">
			<s:iterator value="#treasureTaskModeList">
				<div title="${spMode}" style="padding:20px;">
					<input type="hidden" id="taskModeId" value="${spId}">
					<input type="hidden" id="isInterval" value="${spIsinterval}">
		        	<table id="rewards-table${spId}" class="rewards-table" style="width:100%" border="0" cellspacing="0" cellpadding="0">
	        			<tr>
	          				<th>销售数量</th>
					        <th>利润率(百分比)</th>
					        <th>利润(元)</th>
					        <th>操作</th>
	        			</tr>
				        <tr>
				          <td>
				          <s:if test="spIsinterval==0">
				          	<input type="text" id="task${spId}_sale_qantity_0">	  件
				          </s:if>
				          <s:else>
				          	<input type="text" id="task${spId}_sale_qantity_min_0"> 至 <input type="text" id="task${spId}_sale_qantity_max_0">	  件 
				          </s:else>
				          </td>
				          <td><input type="text" id="task${spId}_sale_profit_percentage_0">  %</td>
				          <td><input type="text" id="task${spId}_sale_profit_yuan_0">  元</td>
				          <td align="center"></td>
				        </tr>
	  				</table>
					<p><a onclick="addReward(${spId},${spIsinterval})" style="color:#333;" href="javascript:;">点击添加</a></p>	
	        	</div>   
			</s:iterator>
	</div>
	<span>任务时效：</span>
	<input type="text" id="taskAging" class="textbox"> 天
</div><!-- "createTreasureTable"结束 -->	



<!-- 底部按钮 -->
<div id="sp_task_submit">
	<input type="button" id="treasureTaskBtn" value="发布任务">
</div>
