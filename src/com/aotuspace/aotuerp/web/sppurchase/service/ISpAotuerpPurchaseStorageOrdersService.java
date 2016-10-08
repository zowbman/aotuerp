package com.aotuspace.aotuerp.web.sppurchase.service;

import com.aotuspace.aotuerp.web.base.dao.DaoSupport;
import com.aotuspace.aotuerp.web.sppurchase.hbm.SpAotuerpPurchaseList;


/**
 * 
 * Title:ISpSupplierService
 * Description:进货单service接口
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-11-10 下午4:58:46
 *
 */

public interface ISpAotuerpPurchaseStorageOrdersService extends DaoSupport<SpAotuerpPurchaseList> {
	
	//根据进货单号查询进货单
	SpAotuerpPurchaseList findSpAotuerpPurchaseListByPurchaseId(String PurchaseId)throws Exception;
	
}
