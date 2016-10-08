package com.aotuspace.aotuerp.web.sperplogin.view.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aotuspace.aotuerp.web.base.BaseAction;
import com.aotuspace.aotuerp.web.model.PageBean;
import com.aotuspace.aotuerp.web.sperplogin.hbm.SpEmployeeBinfo;
import com.aotuspace.aotuerp.web.sperplogin.hbm.SpEmployeeDepart;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * Title:SysMMAction
 * Description:系统管理员管理
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-9-21 下午5:17:02
 *
 */

@Controller
@Scope("prototype")
public class SysMMAction extends BaseAction<SpEmployeeBinfo> {
	//登录功能
	public String login() throws Exception {
		//从数据库中搜索用户名密码
		SpEmployeeBinfo spEmployeeBinfo = iSysMMService.findByEpAccountAndEpPassword(model.getSpEpaccount(),
				model.getSpEppassword());
		if (!spEmployeeBinfo.getSpEmployeeDepart().getSpEpdepartn().equals("采购部")) {//登录失败
			jsonResult.setCode(100001);//code
			jsonResult.setMsg("请求失败");//msg
			jsonResult.setData(null);//data
		}else{
			//登录成功
			//保存Session
			ActionContext.getContext().getSession().put("spEmployeeBinfo", spEmployeeBinfo);
			jsonResult.setCode(0);
			jsonResult.setMsg("请求成功");
			jsonResult.setData(null);
		}
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	//注销
	public String logout() throws Exception {
		ActionContext.getContext().getSession().remove("spEmployeeBInfo");
		return NONE;
	}
	
	//员工信息data
	public String listData() throws Exception {
		PageBean<SpEmployeeBinfo> spEmployeeBinfoList=iSysMMService.findSpEmployeeBinfoList(rows, page);
		//封装json
		Map<String, Object> pageListMap = new HashMap<String, Object>();
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpEmployeeBinfo spEmployeeBinfo : spEmployeeBinfoList.getRecordList()) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("sp_id", spEmployeeBinfo.getSpEmployeeBinfoKey().getSpId());
			rowMap.put("sp_EpId", spEmployeeBinfo.getSpEmployeeBinfoKey().getSpEpid());
			rowMap.put("sp_Eprealname", spEmployeeBinfo.getSpEmployeeDinfo().getSpEprealname());
			if (spEmployeeBinfo.getSpEmployeeDepart() != null) {
				rowMap.put("sp_EpDepartId", spEmployeeBinfo.getSpEmployeeDepart().getSpId());
				rowMap.put("sp_EpDepart", spEmployeeBinfo.getSpEmployeeDepart().getSpEpdepartn());
			}
			rowMap.put("sp_Epsex",spEmployeeBinfo.getSpEmployeeDinfo().getSpEpsex());
			
			listMaps.add(rowMap);
		}
		//easyui total 总数  rows列表
		pageListMap.put("total", spEmployeeBinfoList.getPageCount());//总条数
		pageListMap.put("rows", listMaps);//列表
		resp.getWriter().write(objectMapper.writeValueAsString(pageListMap));
		return NONE;
	}
	//getter、setter
}
