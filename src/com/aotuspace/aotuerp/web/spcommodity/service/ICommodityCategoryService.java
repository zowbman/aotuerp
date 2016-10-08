package com.aotuspace.aotuerp.web.spcommodity.service;

import java.util.List;
import java.util.Set;

import com.aotuspace.aotuerp.web.base.dao.DaoSupport;
import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductCategory;

/**
 * 
 * Title:ICommodityService
 * Description:类目接口
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-10-23 上午10:58:19
 *
 */
public interface ICommodityCategoryService extends DaoSupport<SpProductCategory> {
	//查询顶级类目
	List<SpProductCategory> findCategoryTopList()throws Exception;
	//根据顶级类目查询子类目
	List<SpProductCategory> findCategoryByTopList(int topCategoryId)throws Exception;
}
