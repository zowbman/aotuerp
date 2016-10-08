package com.aotuspace.aotuerp.web.sppurchase.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aotuspace.aotuerp.web.base.dao.impl.DaoSupportImpl;
import com.aotuspace.aotuerp.web.model.PageBean;
import com.aotuspace.aotuerp.web.sppurchase.hbm.SpSupplierBinfo;
import com.aotuspace.aotuerp.web.sppurchase.service.ISpSupplierService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class SpSupplierServiceImpl extends DaoSupportImpl<SpSupplierBinfo> implements ISpSupplierService {
	
	//供应商列表
	public PageBean<SpSupplierBinfo> findSpSupplierBinfoList(int rows, int page) throws Exception {
		//列表数据
		List<SpSupplierBinfo> spSupplierBinfoList = getSession().createQuery("FROM SpSupplierBinfo s")
				.setFirstResult(rows * (page - 1)).setMaxResults(rows).list();

		//总记录数
		Long count = (Long) getSession().createQuery("SELECT COUNT(spSupplierBinfoKey.spId) FROM SpSupplierBinfo")
				.uniqueResult();
		return new PageBean<SpSupplierBinfo>(count.intValue(), spSupplierBinfoList);
		//return null;
	}

}
