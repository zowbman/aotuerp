package com.aotuspace.aotuerp.web.sptreasure.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aotuspace.aotuerp.web.base.dao.impl.DaoSupportImpl;
import com.aotuspace.aotuerp.web.model.PageBean;
import com.aotuspace.aotuerp.web.sptreasure.hbm.SpAotuerpTreasureImg;
import com.aotuspace.aotuerp.web.sptreasure.hbm.SpAotuerpTreasureInfo;
import com.aotuspace.aotuerp.web.sptreasure.service.ISpAotuerpTreasureService;

/**
 * 
 * Title:SpAotuerpPurchaseOrdersNumbersImpl
 * Description:宝贝实现类
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-11-12 上午10:34:49
 *
 */
@Service
@Transactional
@SuppressWarnings("unchecked")
public class SpAotuerpTreasureServiceImpl extends DaoSupportImpl<SpAotuerpTreasureInfo> implements ISpAotuerpTreasureService{	
	
	public PageBean<SpAotuerpTreasureInfo> findSpAotuerpTreasureInfoList(int rows, int page) throws Exception {
		//列表数据
		List<SpAotuerpTreasureInfo> spProductSkuList = getSession().createQuery("FROM SpAotuerpTreasureInfo s")
				.setFirstResult(rows * (page - 1)).setMaxResults(rows).list();
		//总记录数
		Long count = (Long) getSession().createQuery("SELECT COUNT(spId) FROM SpAotuerpTreasureInfo").uniqueResult();
		return new PageBean<SpAotuerpTreasureInfo>(count.intValue(), spProductSkuList);
	}

	public PageBean<SpAotuerpTreasureInfo> findSpAotuerpTreasureInfoListByTreasureStatus(int rows, int page,
			Integer treasureStatus) throws Exception {
		
		List<SpAotuerpTreasureInfo> spProductSkuList = getSession().createQuery("FROM SpAotuerpTreasureInfo s WHERE spAotuerpTreasureStatus.spId=:spAotuerpTreasureStatus")
				.setParameter("spAotuerpTreasureStatus", treasureStatus)
				.setFirstResult(rows * (page - 1)).setMaxResults(rows).list();
		//总记录数
		Long count = (Long) getSession().createQuery("SELECT COUNT(spId) FROM SpAotuerpTreasureInfo WHERE spAotuerpTreasureStatus.spId=:spAotuerpTreasureStatus")
				.setParameter("spAotuerpTreasureStatus", treasureStatus)
				.uniqueResult();
		return new PageBean<SpAotuerpTreasureInfo>(count.intValue(), spProductSkuList);
	}

	public void saveSpAotuerpTreasureImg(SpAotuerpTreasureImg spAotuerpTreasureImg) throws Exception {
			getSession().save(spAotuerpTreasureImg);
	}
}
