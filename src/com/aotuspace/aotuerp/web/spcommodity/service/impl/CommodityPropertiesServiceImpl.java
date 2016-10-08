package com.aotuspace.aotuerp.web.spcommodity.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aotuspace.aotuerp.web.base.dao.impl.DaoSupportImpl;
import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductPropertyName;
import com.aotuspace.aotuerp.web.spcommodity.service.ICommodityPropertiesService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class CommodityPropertiesServiceImpl extends DaoSupportImpl<SpProductPropertyName> implements ICommodityPropertiesService {

	//根据类目查询属性
	public List<SpProductPropertyName> findPropertiesByCategoryId(int thirdCategoryId) throws Exception {
		return getSession().createQuery("FROM SpProductPropertyName WHERE spProductCategory.spId =:thirdCategoryId")
				.setParameter("thirdCategoryId", thirdCategoryId)
				.list();
	}
}
