package com.aotuspace.aotuerp.web.sperplogin.service;

import java.util.List;

import com.aotuspace.aotuerp.web.base.dao.DaoSupport;
import com.aotuspace.aotuerp.web.sperplogin.hbm.SpEmployeePrivilege;

/**
 * 
 * Title:ISysPMService
 * Description:系统权限管理service接口
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-9-21 下午5:27:00
 *
 */
public interface ISysPMService extends DaoSupport<SpEmployeePrivilege>	{
	/**
	 * 查询所有进销存顶级权限
	 * @return
	 */
	List<SpEmployeePrivilege> findTopERPPrivList();
	
	//根据父节点查询权限
	List<SpEmployeePrivilege> findSpEmployeePrivilegeByParentId(int parentId) throws Exception;
	
	/**
	 * 查询所有权限
	 * @return
	 *//*
	Collection<String> getAllPrivilegeUrls();
*/
}
