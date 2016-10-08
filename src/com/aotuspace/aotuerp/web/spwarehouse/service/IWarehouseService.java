package com.aotuspace.aotuerp.web.spwarehouse.service;

import com.aotuspace.aotuerp.web.base.dao.DaoSupport;
import com.aotuspace.aotuerp.web.model.PageBean;
import com.aotuspace.aotuerp.web.spwarehouse.hbm.SpAotuerpProductStocks;
import com.aotuspace.aotuerp.web.spwarehouse.hbm.SpAotuerpWarehouseInfo;

/**
 * 
 * Title:ICommodityService
 * Description:库存接口
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-10-23 上午10:58:19
 *
 */
public interface IWarehouseService extends DaoSupport<SpAotuerpWarehouseInfo> {
	//查询仓库列表（分页）
	PageBean<SpAotuerpWarehouseInfo> findSpAotuerpWarehouseInfoList(int rows, int page) throws Exception;
	
	//根据仓库查询sku商品列表
	PageBean<SpAotuerpProductStocks> findSpProductSkuListByWarehouse(int rows, int page,Integer spWarehouseId) throws Exception;
}
