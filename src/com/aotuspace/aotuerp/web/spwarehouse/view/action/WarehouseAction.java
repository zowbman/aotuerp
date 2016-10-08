package com.aotuspace.aotuerp.web.spwarehouse.view.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aotuspace.aotuerp.web.base.BaseAction;
import com.aotuspace.aotuerp.web.model.PageBean;
import com.aotuspace.aotuerp.web.sperplogin.hbm.SpEmployeeBinfo;
import com.aotuspace.aotuerp.web.sperplogin.hbm.SpEmployeeBinfoKey;
import com.aotuspace.aotuerp.web.spwarehouse.hbm.SpAotuerpProductStocks;
import com.aotuspace.aotuerp.web.spwarehouse.hbm.SpAotuerpWarehouseInfo;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

/**
 * 
 * Title:CommodityAction
 * Description:商品管理模块
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-10-21 下午3:22:22
 *
 */
@Controller
@Scope("prototype")
public class WarehouseAction extends BaseAction<SpAotuerpWarehouseInfo> {

	public Integer empSpId;
	public Integer empSpEpId;
	//仓库Ids
	public Integer[] warehouseIds;

	//商品信息页面
	public String list() throws Exception {
		return "list";
	}

	//商品信息listData数据
	public String listData() throws Exception {
		PageBean<SpAotuerpWarehouseInfo> spAotuerpWarehouseInfoList = iWarehouseService.findSpAotuerpWarehouseInfoList(
				rows, page);
		//封装json
		Map<String, Object> pageListMap = new HashMap<String, Object>();
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpAotuerpWarehouseInfo spAotuerpWarehouseInfo : spAotuerpWarehouseInfoList.getRecordList()) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("sp_id", spAotuerpWarehouseInfo.getSpId());//仓库编号
			rowMap.put("sp_idShow", spAotuerpWarehouseInfo.getSpId());//仓库编号
			rowMap.put("sp_warehousename", spAotuerpWarehouseInfo.getSpWarehousename());//仓库名称
			String sp_empwarehouse="";
			for (SpEmployeeBinfo spEmployeeBinfo : spAotuerpWarehouseInfo.getSpEmployeeBinfos()) {
				sp_empwarehouse=spEmployeeBinfo.getSpEmployeeDinfo().getSpEprealname();
			}
			rowMap.put("sp_empwarehouse",sp_empwarehouse);//仓库管理员名称
			rowMap.put("sp_pinyin", spAotuerpWarehouseInfo.getSpPinyin());//仓库拼音码
			rowMap.put("sp_remark", spAotuerpWarehouseInfo.getSpRemark());//备注
			listMaps.add(rowMap);
		}
		//easyui total 总数  rows列表
		pageListMap.put("total", spAotuerpWarehouseInfoList.getPageCount());//总条数
		pageListMap.put("rows", listMaps);//列表
		resp.getWriter().write(objectMapper.writeValueAsString(pageListMap));
		return NONE;
	}

	//仓库管理员
	public String empWarehouseTreeData() throws Exception {
		boolean flag = true;
		List<SpEmployeeBinfo> spEmployeeBinfoList = iSysMMService.findSpEmployeeBinfoTreeData();
		//封装json
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpEmployeeBinfo spEmployeeBinfo : spEmployeeBinfoList) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("id", spEmployeeBinfo.getSpEmployeeBinfoKey().getSpId() + "-"
					+ spEmployeeBinfo.getSpEmployeeBinfoKey().getSpEpid());
			rowMap.put("text", spEmployeeBinfo.getSpEmployeeDinfo().getSpEprealname());
			if (flag) {
				//默认选项
				Map<String, Object> defaultrowMap = new HashMap<String, Object>();
				defaultrowMap.put("id", 0);
				defaultrowMap.put("text", "请选择仓库管理员");
				listMaps.add(defaultrowMap);
				flag = false;
			}
			listMaps.add(rowMap);
		}
		//easyui total总数列表
		resp.getWriter().write(objectMapper.writeValueAsString(listMaps));
		return NONE;
	}

	//添加仓库
	public String add() throws Exception {
		SpEmployeeBinfoKey spEmployeeBinfoKey = new SpEmployeeBinfoKey();
		spEmployeeBinfoKey.setSpId(empSpId);
		spEmployeeBinfoKey.setSpEpid(empSpEpId);
		//查询员工
		SpEmployeeBinfo spEmployeeBinfo = iSysMMService.getByObject(spEmployeeBinfoKey);
		Set<SpEmployeeBinfo> spEmployeeBinfos = new HashSet<SpEmployeeBinfo>();
		spEmployeeBinfos.add(spEmployeeBinfo);
		model.setSpEmployeeBinfos(spEmployeeBinfos);
		//保存仓库
		iWarehouseService.save(model);
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	//更新仓库
	public String edit()throws Exception{
		SpAotuerpWarehouseInfo spAotuerpWarehouseInfo=iWarehouseService.getById(model.getSpId());
		// 如果对象不为空，则
		if (spAotuerpWarehouseInfo != null) {
			System.out.println(spAotuerpWarehouseInfo.getSpEmployeeBinfos());//jackson注册了Hibernate4Module所以一些属性不会序列化出来，所以要打印一下出来
			objectMapper.registerModule(new Hibernate4Module());
			jsonResult.setCode(0);
			jsonResult.setMsg("请求成功");
			jsonResult.setData("[" + objectMapper.writeValueAsString(spAotuerpWarehouseInfo) + "]");
		} else { // 否则
			jsonResult.setCode(10001);
			jsonResult.setMsg("请求失败");
		}
		System.out.println(objectMapper.writeValueAsString(jsonResult));
		resp.getWriter().write("["+objectMapper.writeValueAsString(jsonResult)+"]");
		return NONE;
	}
	
	public String editSubmit() throws Exception{
		//获取仓库信息
		SpAotuerpWarehouseInfo spAotuerpWarehouseInfo=iWarehouseService.getById(model.getSpId());
		spAotuerpWarehouseInfo.setSpWarehousename(model.getSpWarehousename());//仓库名称
		spAotuerpWarehouseInfo.setSpPinyin(model.getSpPinyin());//拼音码
		spAotuerpWarehouseInfo.setSpAddress(model.getSpAddress());//仓库地址
		spAotuerpWarehouseInfo.setSpRemark(model.getSpRemark());//备注
		SpEmployeeBinfoKey spEmployeeBinfoKey = new SpEmployeeBinfoKey();
		spEmployeeBinfoKey.setSpId(empSpId);
		spEmployeeBinfoKey.setSpEpid(empSpEpId);
		//查询员工
		SpEmployeeBinfo spEmployeeBinfo = iSysMMService.getByObject(spEmployeeBinfoKey);
		Set<SpEmployeeBinfo> spEmployeeBinfos = new HashSet<SpEmployeeBinfo>();
		spEmployeeBinfos.add(spEmployeeBinfo);
		
		spAotuerpWarehouseInfo.setSpEmployeeBinfos(spEmployeeBinfos);//仓库管理员
		
		iWarehouseService.update(spAotuerpWarehouseInfo);
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//删除仓库
	public String delete() throws Exception{
		iWarehouseService.delete(warehouseIds);
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
		
	}
	
	//仓库商品信息列表
	public String pdList()throws Exception{
		return "pdList";
	}
	
	//仓库分类tree
	public String warehouseTree() throws Exception {
		//查询仓库数据
		List<SpAotuerpWarehouseInfo> spAotuerpWarehouseInfoList = iWarehouseService.findAll();
		//封装json
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpAotuerpWarehouseInfo spAotuerpWarehouseInfo : spAotuerpWarehouseInfoList) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("id", spAotuerpWarehouseInfo.getSpId());
			rowMap.put("text", spAotuerpWarehouseInfo.getSpWarehousename()+"(编号:"+spAotuerpWarehouseInfo.getSpId()+")");
			listMaps.add(rowMap);
		}
		//easyui
		resp.getWriter().write(objectMapper.writeValueAsString(listMaps));
		return NONE;
	}
	
	//库存商品数量
	public String warehousePdListData()throws Exception{
		PageBean<SpAotuerpProductStocks> spAotuerpProductStocksList;
		if(model.getSpId()!=null && model.getSpId()!=0){
			spAotuerpProductStocksList = iWarehouseService.findSpProductSkuListByWarehouse(rows, page, model.getSpId());
		}else{
			//查询sku商品列表数据
			spAotuerpProductStocksList=new PageBean<SpAotuerpProductStocks>();
		}
		//封装json
		Map<String, Object> pageListMap = new HashMap<String, Object>();
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpAotuerpProductStocks spAotuerpProductStocks : spAotuerpProductStocksList.getRecordList()) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("sp_id", spAotuerpProductStocks.getSpProductSku().getSpSkuid());//商品skuid
			rowMap.put("sp_Pdspu", spAotuerpProductStocks.getSpProductSku().getSpProductBinfo().getSpPdspu());//商品名称
			rowMap.put("sp_Pdproname", spAotuerpProductStocks.getSpProductSku().getSpSkupropertiesname());//规格、型号
			rowMap.put("sp_Pdbrand", spAotuerpProductStocks.getSpProductSku().getSpProductBinfo().getSpProductBrands().getSpBrandn());//品牌
			rowMap.put("sp_PdCategory", spAotuerpProductStocks.getSpProductSku().getSpProductBinfo().getSpProductBrands().getSpProductCategory().getSpCategoryn());//类目名
			rowMap.put("sp_Quantity", spAotuerpProductStocks.getSpQuantity());//数量
			listMaps.add(rowMap);
		}
		//easyui total 总数  rows列表
		pageListMap.put("total", spAotuerpProductStocksList.getPageCount());//总条数
		pageListMap.put("rows", listMaps);//列表
		resp.getWriter().write(objectMapper.writeValueAsString(pageListMap));
		return NONE;
	}
	

	public Integer getEmpSpId() {
		return empSpId;
	}

	public void setEmpSpId(Integer empSpId) {
		this.empSpId = empSpId;
	}

	public Integer getEmpSpEpId() {
		return empSpEpId;
	}

	public void setEmpSpEpId(Integer empSpEpId) {
		this.empSpEpId = empSpEpId;
	}

	public Integer[] getWarehouseIds() {
		return warehouseIds;
	}

	@JSON(serialize = true, deserialize = true)
	public void setWarehouseIds(Integer[] warehouseIds) {
		this.warehouseIds = warehouseIds;
	}

}
