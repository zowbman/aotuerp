package com.aotuspace.aotuerp.web.sppurchase.service;

import com.aotuspace.aotuerp.web.base.dao.DaoSupport;
import com.aotuspace.aotuerp.web.model.PageBean;
import com.aotuspace.aotuerp.web.sppurchase.hbm.SpAotuerpPurchaseList;
import com.aotuspace.aotuerp.web.sppurchase.hbm.SpAotuerpPurchaseOrders;
import com.aotuspace.aotuerp.web.sppurchase.hbm.SpAotuerpPurchaseOrdersNumbers;


/**
 * 
 * Title:ISpSupplierService
 * Description:进货订单service接口
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-11-10 下午4:58:46
 *
 */

public interface ISpAotuerpPurchaseOrdersService extends DaoSupport<SpAotuerpPurchaseOrdersNumbers> {
	//查询订单id最大值
	Integer findByMaxPurchaseOrdersNum()throws Exception;
	//查询未完成的进货订单列表（分页）
	PageBean<SpAotuerpPurchaseOrdersNumbers> findSpAotuerpPurchaseOrdersNumbersListNotFinish(int rows, int page) throws Exception;
	//查询进货单列表（分页）
	PageBean<SpAotuerpPurchaseList> findSpAotuerpPurchaseList(int rows, int page) throws Exception;
	
	
	//根据id查询进货订单（非订单编号序号查询）
	SpAotuerpPurchaseOrders getSpAotuerpPurchaseOrdersById (Integer id)throws Exception;
	
	
}
