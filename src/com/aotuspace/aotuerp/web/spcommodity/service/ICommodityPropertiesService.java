package com.aotuspace.aotuerp.web.spcommodity.service;

import java.util.List;

import com.aotuspace.aotuerp.web.base.dao.DaoSupport;
import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductPropertyName;

/**
 * 
 * Title:ICommodityService
 * Description:类目接口
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-10-23 上午10:58:19
 *
 */
public interface ICommodityPropertiesService extends DaoSupport<SpProductPropertyName> {
	//根据类目查询属性
	List<SpProductPropertyName> findPropertiesByCategoryId(int thirdCategoryId)throws Exception;
	
}
