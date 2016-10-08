package com.aotuspace.aotuerp.web.spcommodity.service;

import java.util.List;
import java.util.Set;

import com.aotuspace.aotuerp.web.base.dao.DaoSupport;
import com.aotuspace.aotuerp.web.model.PageBean;
import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductBinfo;
import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductSku;

/**
 * 
 * Title:ICommodityService
 * Description:商品接口
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-10-23 上午10:58:19
 *
 */

public interface ICommodityService extends DaoSupport<SpProductBinfo> {
	//查询sku商品列表（分页）
	PageBean<SpProductSku> findSpProductSkuList(int rows, int page) throws Exception;

	//查询商品列表（分页）
	PageBean<SpProductBinfo> findSpProductBinfoList(int rows, int page) throws Exception;
	
	//删除sku商品
	Set<Integer> deleteSpProductSku(Integer[] commoditySKUIds) throws Exception;
	
	//根据Ids查询sku商品
	List<SpProductSku> getSpProductSkuByIds(Integer[] ids)throws Exception;
}
