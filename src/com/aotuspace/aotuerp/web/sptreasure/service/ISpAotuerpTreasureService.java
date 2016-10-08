package com.aotuspace.aotuerp.web.sptreasure.service;

import java.util.List;

import com.aotuspace.aotuerp.web.base.dao.DaoSupport;
import com.aotuspace.aotuerp.web.model.PageBean;
import com.aotuspace.aotuerp.web.sptreasure.hbm.SpAotuerpTreasureImg;
import com.aotuspace.aotuerp.web.sptreasure.hbm.SpAotuerpTreasureInfo;


/**
 * 
 * Title:ISpSupplierService
 * Description:±¦±´service½Ó¿Ú
 * Company:aotuspace
 * @author    Î°±¦
 * @date      2015-11-10 ÏÂÎç4:58:46
 *
 */

public interface ISpAotuerpTreasureService extends DaoSupport<SpAotuerpTreasureInfo> {	
	
	/**
	 * ²éÑ¯±¦±´ÁÐ±í
	 * @param rows
	 * @param page
	 * @return
	 * @throws Exception
	 */
	PageBean<SpAotuerpTreasureInfo> findSpAotuerpTreasureInfoList(int rows, int page) throws Exception;
	
	/**
	 * ¸ù¾Ý±¦±´×´Ì¬²éÑ¯±¦±´ÁÐ±í
	 * @param rows
	 * @param page
	 * @param treasureStatus
	 * @return
	 * @throws Exception
	 */
	PageBean<SpAotuerpTreasureInfo> findSpAotuerpTreasureInfoListByTreasureStatus(int rows, int page,Integer treasureStatus) throws Exception;
	
	/**
	 * ±£´æ±¦±´Í¼Æ¬
	 * @param spAotuerpTreasureImg
	 * @throws Exception
	 */
	void saveSpAotuerpTreasureImg(SpAotuerpTreasureImg spAotuerpTreasureImg)throws Exception;
	
}
