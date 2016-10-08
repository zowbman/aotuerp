package com.aotuspace.aotuerp.web.sperplogin.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aotuspace.aotuerp.web.base.dao.impl.DaoSupportImpl;
import com.aotuspace.aotuerp.web.sperplogin.hbm.SpEmployeePrivilege;
import com.aotuspace.aotuerp.web.sperplogin.service.ISysPMService;

/**
 * 
 * Title:SysPMServiceImpl
 * Description:系统权限管理service实现类
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-9-21 下午5:28:06
 *
 */
@Service
@Transactional
@SuppressWarnings("unchecked")
public class SysPMServiceImpl extends DaoSupportImpl<SpEmployeePrivilege> implements ISysPMService {

	public List<SpEmployeePrivilege> findTopERPPrivList() {
		return getSession().createQuery("From SpEmployeePrivilege p Where p.spEpurl Is Null AND p.spEpparent.spEpname='凹凸空间进销存管理中心'").list();
	}

	/*public Collection<String> getAllPrivilegeUrls() {
		return getSession().createQuery("SELECT DISTINCT p.spEpurl From SpEmployeePrivilege p Where p.spEpurl Is not Null").list();
	}*/
	
	//根据父节点查询权限
	public List<SpEmployeePrivilege> findSpEmployeePrivilegeByParentId(int parentId) throws Exception {
		return getSession().createQuery("FROM SpEmployeePrivilege s WHERE s.spEpparent.spId=:parentId")
				.setParameter("parentId", parentId).list();
	}
}
