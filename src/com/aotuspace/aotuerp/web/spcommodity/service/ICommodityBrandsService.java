package com.aotuspace.aotuerp.web.spcommodity.service;

import java.util.List;

import com.aotuspace.aotuerp.web.base.dao.DaoSupport;
import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductBrands;

/**
 * 
 * Title:ICommodityService
 * Description:品牌接口
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-10-23 上午10:58:19
 *
 */
public interface ICommodityBrandsService extends DaoSupport<SpProductBrands> {
	
	//根据类目Id查询品牌
	List<SpProductBrands> findBrandsByCategoryId(int secondCategoryId)throws Exception;
}
