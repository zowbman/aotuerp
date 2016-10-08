package com.aotuspace.aotuerp.web.sppurchase.view.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aotuspace.aotuerp.web.base.BaseAction;
import com.aotuspace.aotuerp.web.model.PageBean;
import com.aotuspace.aotuerp.web.sppurchase.hbm.SpSupplierBinfo;

/**
 * 
 * Title:CommodityAction
 * Description:供应商
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-10-21 下午3:22:22
 *
 */
@Controller
@Scope("prototype")
public class SpSupplierAction extends BaseAction<SpSupplierBinfo> {

	//供应商信息data
	public String listData() throws Exception {
		PageBean<SpSupplierBinfo> spSupplierBinfoList = iSpSupplierService.findSpSupplierBinfoList(rows, page);
		//封装json
		Map<String, Object> pageListMap = new HashMap<String, Object>();
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpSupplierBinfo spSupplierBinfo : spSupplierBinfoList.getRecordList()) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("sp_id", spSupplierBinfo.getSpSupplierBinfoKey().getSpId());//序号id
			rowMap.put("sp_SuId", spSupplierBinfo.getSpSupplierBinfoKey().getSpSuid());//供应商id
			rowMap.put("sp_SuSup", spSupplierBinfo.getSpSupplierDinfo().getSpSusup());//商家名称
			rowMap.put("sp_SuCont", spSupplierBinfo.getSpSupplierDinfo().getSpSucont());//商家联系人姓名
			rowMap.put("sp_SuMobie", spSupplierBinfo.getSpSupplierDinfo().getSpSumobie());//商家手机号码
			rowMap.put("sp_SuTel", spSupplierBinfo.getSpSupplierDinfo().getSpSutel());//商家联系电话
			rowMap.put("sp_SuDistrict", spSupplierBinfo.getSpSupplierDinfo().getSpSudistrict());//商家所在地
			listMaps.add(rowMap);
		}
		//easyui total 总数rows列表
		pageListMap.put("total", spSupplierBinfoList.getPageCount());//总条数
		pageListMap.put("rows", listMaps);//列表
		resp.getWriter().write(objectMapper.writeValueAsString(pageListMap));
		return NONE;
	}
}
