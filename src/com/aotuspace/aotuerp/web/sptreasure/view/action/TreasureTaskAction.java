package com.aotuspace.aotuerp.web.sptreasure.view.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aotuspace.aotuerp.web.base.BaseAction;
import com.aotuspace.aotuerp.web.model.EasyTreeData;
import com.aotuspace.aotuerp.web.model.PageBean;
import com.aotuspace.aotuerp.web.sperplogin.hbm.SpEmployeePrivilege;
import com.aotuspace.aotuerp.web.sptreasure.hbm.SpAotuerpTreasureInfo;
import com.aotuspace.aotuerp.web.sptreasure.hbm.SpAotuerpTreasureTask;
import com.aotuspace.aotuerp.web.sptreasure.hbm.SpAotuerpTreasureTaskMode;
import com.aotuspace.aotuerp.web.util.TreeNodeUtil;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * Title:CommodityAction
 * Description:宝贝任务
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-10-21 下午3:22:22
 *
 */
@Controller
@Scope("prototype")
public class TreasureTaskAction extends BaseAction<SpAotuerpTreasureTask> {
	
	private SpAotuerpTreasureTask spAotuerpTreasureTask;
	
	//宝贝任务页面
	public String index() throws Exception {
		//二级导航
		ActionContext.getContext().put("twoNav", twoNav);
		return "index";
	}
	
	//宝贝任务管理功能导航列表
	public String nav()throws Exception{
		//宝贝任务管理功能导航列表
		List<SpEmployeePrivilege> spEmployeePrivileges = iSysPMService.findSpEmployeePrivilegeByParentId(twoNav);
		
		List<EasyTreeData> easyTreeDatas=TreeNodeUtil.getTreeNode(spEmployeePrivileges,1);
		objectMapper.setSerializationInclusion(Include.NON_NULL);  
		resp.getWriter().write(objectMapper.writeValueAsString(easyTreeDatas));
		
		return NONE;
	}
	//发布任务页面
	public String release()throws Exception{
		return "release";
	}
	
	//导入宝贝
	public String importTreasureData() throws Exception{
		//查询已经审核通过的宝贝
		PageBean<SpAotuerpTreasureInfo> spAotuerpTreasureInfoList=iSpAotuerpTreasureService.findSpAotuerpTreasureInfoListByTreasureStatus(rows, page, 2);//审核通过
		//封装json
		Map<String, Object> pageListMap = new HashMap<String, Object>();
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpAotuerpTreasureInfo spAotuerpTreasureInfo : spAotuerpTreasureInfoList.getRecordList()) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("sp_id", spAotuerpTreasureInfo.getSpId());//宝贝编号
			rowMap.put("sp_idShow", spAotuerpTreasureInfo.getSpId());//宝贝编号
			rowMap.put("sp_Treasuretitle", spAotuerpTreasureInfo.getSpTreasuretitle());//宝贝标题
			rowMap.put("sp_TreasureStatus", spAotuerpTreasureInfo.getSpAotuerpTreasureStatus().getSpTreasurestatus());//宝贝状态
			listMaps.add(rowMap);
		}
		//easyui total 总数  rows列表
		pageListMap.put("total", spAotuerpTreasureInfoList.getPageCount());//总条数
		pageListMap.put("rows", listMaps);//列表
		resp.getWriter().write(objectMapper.writeValueAsString(pageListMap));
		return NONE;
	}
	
	//发布宝贝任务表单
	public String releaseForm() throws Exception {
		//获取宝贝信息
		SpAotuerpTreasureInfo spAotuerpTreasureInfo=iSpAotuerpTreasureService.getById(model.getSpAotuerpTreasureInfo().getSpId());
		ActionContext.getContext().put("treasureInfo", spAotuerpTreasureInfo);
		//获取宝贝任务模式信息
		List<SpAotuerpTreasureTaskMode> spAotuerpTreasureTaskModes=iSpAotuerpTreasureTaskModeService.findAll();
		ActionContext.getContext().put("treasureTaskModeList", spAotuerpTreasureTaskModes);
		return "releaseForm";
	}
	
	//发布宝贝任务
	public String releaseSubmit()throws Exception{
		iSpAotuerpTreasureTaskService.save(spAotuerpTreasureTask);
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	public SpAotuerpTreasureTask getSpAotuerpTreasureTask() {
		return spAotuerpTreasureTask;
	}

	@JSON(serialize = true, deserialize = true)
	public void setSpAotuerpTreasureTask(SpAotuerpTreasureTask spAotuerpTreasureTask) {
		this.spAotuerpTreasureTask = spAotuerpTreasureTask;
	}	
}
