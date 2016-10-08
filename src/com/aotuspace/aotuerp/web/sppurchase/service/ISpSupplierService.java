package com.aotuspace.aotuerp.web.sppurchase.service;

import com.aotuspace.aotuerp.web.base.dao.DaoSupport;
import com.aotuspace.aotuerp.web.model.PageBean;
import com.aotuspace.aotuerp.web.sppurchase.hbm.SpSupplierBinfo;


/**
 * 
 * Title:ISpSupplierService
 * Description:供应商service接口
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-11-10 下午4:58:46
 *
 */

public interface ISpSupplierService extends DaoSupport<SpSupplierBinfo> {
	//查询供应商列表（分页）
	PageBean<SpSupplierBinfo> findSpSupplierBinfoList(int rows, int page) throws Exception;

}
