package com.aotuspace.aotuerp.web.sperplogin.service;

import java.util.List;

import com.aotuspace.aotuerp.web.base.dao.DaoSupport;
import com.aotuspace.aotuerp.web.model.PageBean;
import com.aotuspace.aotuerp.web.sperplogin.hbm.SpEmployeeBinfo;

/**
 * 
 * Title:ISysMMService
 * Description:系统管理员管理service接口
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-9-21 下午5:26:40
 *
 */

public interface ISysMMService extends DaoSupport<SpEmployeeBinfo> {

	//根据登录名与密码进行查询用户(登录)--只允许采购部的员工进行登录
	SpEmployeeBinfo findByEpAccountAndEpPassword(String spEpaccount,String spEppassword) throws Exception;
	
	//获取管理员信息的Datatree
	List<SpEmployeeBinfo> findSpEmployeeBinfoTreeData() throws Exception;
	
	//查询系统管理员列表（分页）
	PageBean<SpEmployeeBinfo> findSpEmployeeBinfoList(int rows, int page) throws Exception;
}
