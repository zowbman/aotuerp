package com.aotuspace.aotuerp.web.spcommodity.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aotuspace.aotuerp.web.base.dao.impl.DaoSupportImpl;
import com.aotuspace.aotuerp.web.model.PageBean;
import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductBinfo;
import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductSku;
import com.aotuspace.aotuerp.web.spcommodity.service.ICommodityService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class CommodityServiceImpl extends DaoSupportImpl<SpProductBinfo> implements ICommodityService {

	//分页查询
	public PageBean<SpProductSku> findSpProductSkuList(int rows, int page) throws Exception {
		//列表数据
		List<SpProductSku> spProductSkuList = getSession().createQuery("FROM SpProductSku s")
				.setFirstResult(rows * (page - 1)).setMaxResults(rows).list();
		//总记录数
		Long count = (Long) getSession().createQuery("SELECT COUNT(spSkuid) FROM SpProductSku").uniqueResult();
		return new PageBean<SpProductSku>(count.intValue(), spProductSkuList);
	}

	//分页查询
	public PageBean<SpProductBinfo> findSpProductBinfoList(int rows, int page) throws Exception {
		//列表数据
		List<SpProductBinfo> spProductBinfoList = getSession().createQuery("FROM SpProductBinfo s")
				.setFirstResult(rows * (page - 1)).setMaxResults(rows).list();
		//总记录数
		Long count = (Long) getSession().createQuery("SELECT COUNT(spId) FROM SpProductBinfo").uniqueResult();
		return new PageBean<SpProductBinfo>(count.intValue(), spProductBinfoList);
	}
	
	//删除sku商品
	public Set<Integer> deleteSpProductSku(Integer[] commoditySKUIds) throws Exception {
		List<SpProductSku> list = getSpProductSkuByIds(commoditySKUIds);
		if(list!=null){
			List<Integer> spProductIdsTemp=new ArrayList<Integer>();//获取删除sku商品的商品id
			for (SpProductSku spProductSku : list) {
				spProductIdsTemp.add(spProductSku.getSpProductBinfo().getSpId());
				getSession().delete(spProductSku);
			}
			Set<Integer> spProductIds = new HashSet<Integer>();//通过集合转换，删除重复
			spProductIds.addAll(spProductIdsTemp);
			return spProductIds;
		}
		return null;
	}
	
	//根据Ids查询sku商品
	public List<SpProductSku> getSpProductSkuByIds(Integer[] ids)throws Exception{
		if (ids == null || ids.length == 0) {
			return Collections.EMPTY_LIST;
		} else {
			return getSession().createQuery("From SpProductSku Where spSkuid In(:ids)")
					.setParameterList("ids", ids)
					.list();
		}
	}
	
	

}
