package com.aotuspace.aotuerp.web.sperplogin.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aotuspace.aotuerp.web.base.dao.impl.DaoSupportImpl;
import com.aotuspace.aotuerp.web.model.PageBean;
import com.aotuspace.aotuerp.web.sperplogin.hbm.SpEmployeeBinfo;
import com.aotuspace.aotuerp.web.sperplogin.service.ISysMMService;

/**
 * 
 * Title:SysMMServiceImpl
 * Description:系统管理员管理service实现类
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-9-21 下午5:27:49
 *
 */

@Service
@Transactional
@SuppressWarnings("unchecked")
public class SysMMServiceImpl extends DaoSupportImpl<SpEmployeeBinfo> implements ISysMMService {
	//登录
	public SpEmployeeBinfo findByEpAccountAndEpPassword(String spEpaccount, String spEppassword) throws Exception {
		return (SpEmployeeBinfo) getSession()
				.createQuery("FROM SpEmployeeBinfo s WHERE s.spEpaccount=? AND s.spEppassword=? ")
				.setParameter(0, spEpaccount).setParameter(1, spEppassword).uniqueResult();
	}

	//获取管理员信息的Datatree
	public List<SpEmployeeBinfo> findSpEmployeeBinfoTreeData() throws Exception {
		return getSession().createQuery("FROM SpEmployeeBinfo").list();
	}
	
	public PageBean<SpEmployeeBinfo> findSpEmployeeBinfoList(int rows, int page) throws Exception {
		//列表数据
		List<SpEmployeeBinfo> spEmployeeBinfoList = getSession()
				.createQuery("FROM SpEmployeeBinfo s")
				.setFirstResult(rows*(page-1))
				.setMaxResults(rows)
				.list();
		//总记录数
		Long count = (Long) getSession().
				createQuery("SELECT COUNT(spEmployeeBinfoKey.spId) FROM SpEmployeeBinfo")
				.uniqueResult();
		return new PageBean<SpEmployeeBinfo>(count.intValue(), spEmployeeBinfoList);
	}
}
