package com.aotuspace.aotuerp.web.spcommodity.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aotuspace.aotuerp.web.base.dao.impl.DaoSupportImpl;
import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductBrands;
import com.aotuspace.aotuerp.web.spcommodity.service.ICommodityBrandsService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class CommodityBrandsServiceImpl extends DaoSupportImpl<SpProductBrands> implements ICommodityBrandsService {

	//根据类目id查询品牌
	public List<SpProductBrands> findBrandsByCategoryId(int secondCategoryId) throws Exception {
		return getSession().createQuery("FROM SpProductBrands WHERE spProductCategory.spId =:secondCategoryId")
				.setParameter("secondCategoryId", secondCategoryId)
				.list();
	}
}
