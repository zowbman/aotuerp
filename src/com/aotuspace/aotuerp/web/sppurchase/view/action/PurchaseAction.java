package com.aotuspace.aotuerp.web.sppurchase.view.action;

import java.util.ArrayList;
import java.util.Date;
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
import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductSku;
import com.aotuspace.aotuerp.web.sppurchase.hbm.SpAotuerpPurchaseList;
import com.aotuspace.aotuerp.web.sppurchase.hbm.SpAotuerpPurchaseListPd;
import com.aotuspace.aotuerp.web.sppurchase.hbm.SpAotuerpPurchaseOrderPd;
import com.aotuspace.aotuerp.web.sppurchase.hbm.SpAotuerpPurchaseOrders;
import com.aotuspace.aotuerp.web.sppurchase.hbm.SpAotuerpPurchaseOrdersNumbers;
import com.aotuspace.aotuerp.web.sppurchase.hbm.SpAotuerpPurchaseOrdersStatus;
import com.aotuspace.aotuerp.web.spwarehouse.hbm.SpAotuerpProductStocks;
import com.aotuspace.aotuerp.web.util.CustomUtil;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.opensymphony.xwork2.ActionContext;

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
public class PurchaseAction extends BaseAction<SpAotuerpPurchaseOrders> {

	//进货订单
	private SpAotuerpPurchaseOrders spAotuerpPurchaseOrders = new SpAotuerpPurchaseOrders();

	//进货单
	private SpAotuerpPurchaseList spAotuerpPurchaseList = new SpAotuerpPurchaseList();

	private String spPurchaseId;

	//信息页面
	public String list() throws Exception {
		return "list";
	}

	//进货订单
	public String purchaseOrdersAdd() throws Exception {
		//订单编号
		Integer poNum = iSpAotuerpPurchaseOrdersService.findByMaxPurchaseOrdersNum();
		SpAotuerpPurchaseOrdersNumbers spAotuerpPurchaseOrdersNumbers = new SpAotuerpPurchaseOrdersNumbers();
		if (poNum != null) {
			spAotuerpPurchaseOrdersNumbers.setSpPurchaseOrderId("JHDD" + CustomUtil.getCurrCalendar("yyyyMMdd") + "00"
					+ (poNum + 1));
			iSpAotuerpPurchaseOrdersService.save(spAotuerpPurchaseOrdersNumbers);
			ActionContext.getContext().put("PurchaseOrdersNum", spAotuerpPurchaseOrdersNumbers);
		} else {
			spAotuerpPurchaseOrdersNumbers
					.setSpPurchaseOrderId("JHDD" + CustomUtil.getCurrCalendar("yyyyMMdd") + "001");
			iSpAotuerpPurchaseOrdersService.save(spAotuerpPurchaseOrdersNumbers);
			ActionContext.getContext().put("PurchaseOrdersNum", spAotuerpPurchaseOrdersNumbers);
		}
		//开单日期
		ActionContext.getContext().put("CreateDate", CustomUtil.getCurrCalendar("yyyy-MM-dd"));
		return "purchaseOrdersAdd";
	}

	//进货订单(提交)
	public String purchaseOrdersSubmit() throws Exception {
		Long totalOrder = (long) 0;
		Date curentDate = new Date();
		//System.out.println(model);
		//订单编号
		SpAotuerpPurchaseOrdersNumbers spAotuerpPurchaseOrdersNumbers = iSpAotuerpPurchaseOrdersService
				.getById(spAotuerpPurchaseOrders.getSpAotuerpPurchaseOrdersNumbers().getSpId());
		if (spAotuerpPurchaseOrdersNumbers != null) {
			//订单合同号
			//供货单位
			//经办人
			//仓库
			//开单日期
			spAotuerpPurchaseOrders.setSpCreatedate(new Date(curentDate.getTime()));
			//预计到货日期
			//进货订单商品id
			//订单金额
			for (SpAotuerpPurchaseOrderPd spAotuerpPurchaseOrderPd : spAotuerpPurchaseOrders
					.getSpAotuerpPurchaseOrderPds()) {
				spAotuerpPurchaseOrderPd.setSpTotalprice(spAotuerpPurchaseOrderPd.getSpUnitprice()
						* spAotuerpPurchaseOrderPd.getSpQuantity());
				totalOrder += spAotuerpPurchaseOrderPd.getSpTotalprice();
			}
			//System.out.println(totalOrder);
			spAotuerpPurchaseOrders.setSpOrderamount(totalOrder);
			//完成时间
			//进度
			//备注
			/*Set<SpAotuerpPurchaseOrders> spAotuerpPurchaseOrdersSet=new HashSet<SpAotuerpPurchaseOrders>();
			spAotuerpPurchaseOrdersSet.add(spAotuerpPurchaseOrders);
			spAotuerpPurchaseOrdersNumbers.setSpAotuerpPurchaseOrderses(spAotuerpPurchaseOrdersSet);*/
			spAotuerpPurchaseOrdersNumbers.setSpAotuerpPurchaseOrders(spAotuerpPurchaseOrders);
			iSpAotuerpPurchaseOrdersService.update(spAotuerpPurchaseOrdersNumbers);
			jsonResult.setCode(0);
			jsonResult.setMsg("请求成功");
			resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		}
		return NONE;
	}

	//进货入库
	public String purchaseStorageOrdersAdd() throws Exception {
		ActionContext.getContext().put("CreateDate", CustomUtil.getCurrCalendar("yyyy-MM-dd"));
		return "purchaseStorageOrdersAdd";
	}

	//进货单(提交)
	public String purchaseStorageOrdersSubmit() throws Exception {
		Long totalOrder = (long) 0;
		//单个sku商品总价
		for (SpAotuerpPurchaseListPd spAotuerpPurchaseListPd : spAotuerpPurchaseList.getSpAotuerpPurchaseListPds()) {
			spAotuerpPurchaseListPd.setSpTotalprice(spAotuerpPurchaseListPd.getSpUnitprice()
					* spAotuerpPurchaseListPd.getSpQuantity());
			totalOrder += spAotuerpPurchaseListPd.getSpTotalprice();
		}
		//付款金额
		spAotuerpPurchaseList.setSpPaymentamount(totalOrder);
		//是否开发票（后面不要）
		spAotuerpPurchaseList.setSpIsinvoice(0);
		spAotuerpPurchaseList.setSpAotuerpPurchaseOrders(iSpAotuerpPurchaseOrdersService
				.getSpAotuerpPurchaseOrdersById(spAotuerpPurchaseList.getSpAotuerpPurchaseOrders().getSpId()));
		iSpAotuerpPurchaseStorageOrdersService.save(spAotuerpPurchaseList);
		//订单改为已执行
		SpAotuerpPurchaseOrdersStatus spAotuerpPurchaseOrdersStatus = new SpAotuerpPurchaseOrdersStatus();
		spAotuerpPurchaseOrdersStatus.setSpId(2);
		spAotuerpPurchaseList.getSpAotuerpPurchaseOrders().setSpAotuerpPurchaseOrdersStatus(
				spAotuerpPurchaseOrdersStatus);
		iSpAotuerpPurchaseStorageOrdersService.update(spAotuerpPurchaseList);
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	//进货订单信息
	public String purchaseOrdersListData() throws Exception {
		PageBean<SpAotuerpPurchaseOrdersNumbers> spAotuerpPurchaseOrdersNumbersList = iSpAotuerpPurchaseOrdersService
				.findSpAotuerpPurchaseOrdersNumbersListNotFinish(rows, page);
		objectMapper.registerModule(new Hibernate4Module());
		//封装json
		Map<String, Object> pageListMap = new HashMap<String, Object>();
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpAotuerpPurchaseOrdersNumbers spAotuerpPurchaseOrdersNumbers : spAotuerpPurchaseOrdersNumbersList
				.getRecordList()) {
			if (spAotuerpPurchaseOrdersNumbers.getSpAotuerpPurchaseOrders() != null) {
				Map<String, Object> rowMap = new HashMap<String, Object>();
				rowMap.put("sp_PurchaseOrderNumber_Id", spAotuerpPurchaseOrdersNumbers.getSpId());//订单编号序号
				rowMap.put("sp_PurchaseOrderId", spAotuerpPurchaseOrdersNumbers.getSpPurchaseOrderId());//订单编号
				rowMap.put("sp_PurchaseOrder_Id", spAotuerpPurchaseOrdersNumbers.getSpAotuerpPurchaseOrders().getSpId());//订单序号
				rowMap.put("sp_Createdate", spAotuerpPurchaseOrdersNumbers.getSpAotuerpPurchaseOrders()
						.getSpCreatedate());//进货订单创建日期
				rowMap.put("sp_Planarrivaldate", spAotuerpPurchaseOrdersNumbers.getSpAotuerpPurchaseOrders()
						.getSpPlanarrivaldate());//预计到货日期
				rowMap.put("sp_Supplier", spAotuerpPurchaseOrdersNumbers.getSpAotuerpPurchaseOrders()
						.getSpSupplierBinfo().getSpSupplierDinfo().getSpSusup());//供货单位
				rowMap.put("sp_Supplier_Id", spAotuerpPurchaseOrdersNumbers.getSpAotuerpPurchaseOrders()
						.getSpSupplierBinfo().getSpSupplierBinfoKey().getSpId()
						+ "-"
						+ spAotuerpPurchaseOrdersNumbers.getSpAotuerpPurchaseOrders().getSpSupplierBinfo()
								.getSpSupplierBinfoKey().getSpSuid());//供货单位
				rowMap.put("sp_Warehousename", spAotuerpPurchaseOrdersNumbers.getSpAotuerpPurchaseOrders()
						.getSpAotuerpWarehouseInfo().getSpWarehousename());
				rowMap.put("sp_Warehousename_Id", spAotuerpPurchaseOrdersNumbers.getSpAotuerpPurchaseOrders()
						.getSpAotuerpWarehouseInfo().getSpId());
				rowMap.put("sp_Employee", spAotuerpPurchaseOrdersNumbers.getSpAotuerpPurchaseOrders()
						.getSpEmployeeBinfo().getSpEmployeeDinfo().getSpEprealname());//经办人
				rowMap.put("sp_Employee_Id", spAotuerpPurchaseOrdersNumbers.getSpAotuerpPurchaseOrders()
						.getSpEmployeeBinfo().getSpEmployeeBinfoKey().getSpId()
						+ "-"
						+ spAotuerpPurchaseOrdersNumbers.getSpAotuerpPurchaseOrders().getSpEmployeeBinfo()
								.getSpEmployeeBinfoKey().getSpEpid());//经办人
				rowMap.put("sp_EmployeeDepart", spAotuerpPurchaseOrdersNumbers.getSpAotuerpPurchaseOrders()
						.getSpEmployeeBinfo().getSpEmployeeDepart().getSpEpdepartn());//部门
				rowMap.put("sp_Orderamount", spAotuerpPurchaseOrdersNumbers.getSpAotuerpPurchaseOrders()
						.getSpOrderamount());//金额
				rowMap.put("sp_PurchaseOrdersStatus", spAotuerpPurchaseOrdersNumbers.getSpAotuerpPurchaseOrders()
						.getSpAotuerpPurchaseOrdersStatus().getSpStatusn());//进度
				rowMap.put("sp_Remark", spAotuerpPurchaseOrdersNumbers.getSpAotuerpPurchaseOrders().getSpRemark());//备注
				for (SpAotuerpPurchaseOrderPd spAotuerpPurchaseOrderPd : spAotuerpPurchaseOrdersNumbers
						.getSpAotuerpPurchaseOrders().getSpAotuerpPurchaseOrderPds()) {
					System.out.println(spAotuerpPurchaseOrderPd.getSpProductSku().getSpProductBinfo()
							.getSpProductBrands().getSpProductCategory());
				}
				rowMap.put("sp_PurchaseOrderPds", spAotuerpPurchaseOrdersNumbers.getSpAotuerpPurchaseOrders()
						.getSpAotuerpPurchaseOrderPds());//商品
				listMaps.add(rowMap);
			}
		}
		//easyui total 总数  rows列表
		pageListMap.put("total", spAotuerpPurchaseOrdersNumbersList.getPageCount());//总条数
		pageListMap.put("rows", listMaps);//列表
		resp.getWriter().write(objectMapper.writeValueAsString(pageListMap));
		return NONE;
	}

	//商品信息listData数据
	public String listData() throws Exception {
		PageBean<SpProductSku> spProductSkuList = iCommodityService.findSpProductSkuList(rows, page);
		//封装json
		Map<String, Object> pageListMap = new HashMap<String, Object>();
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpProductSku spProductSku : spProductSkuList.getRecordList()) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("sp_id", spProductSku.getSpSkuid());//商品skuid
			rowMap.put("sp_idShow", spProductSku.getSpSkuid());//商品skuid
			rowMap.put("sp_Pdspu", spProductSku.getSpProductBinfo().getSpPdspu());//商品名称
			rowMap.put("sp_Pdproname", spProductSku.getSpSkupropertiesname());//规格、型号
			rowMap.put("sp_Pdbrand", spProductSku.getSpProductBinfo().getSpProductBrands().getSpBrandn());//品牌
			rowMap.put("sp_PdCategory", spProductSku.getSpProductBinfo().getSpProductBrands().getSpProductCategory()
					.getSpCategoryn());//类目名
			rowMap.put("sp_UnitPrice", spProductSku.getSpPdprice());//数量
			rowMap.put("sp_Quantity", spProductSku.getSpPdcount());//数量
			listMaps.add(rowMap);
		}
		//easyui total 总数  rows列表
		pageListMap.put("total", spProductSkuList.getPageCount());//总条数
		pageListMap.put("rows", listMaps);//列表
		resp.getWriter().write(objectMapper.writeValueAsString(pageListMap));
		return NONE;
	}

	//进货查询页面
	public String purchaseStorageInquire() throws Exception {
		return "purchaseStorageInquire";
	}

	//进货单信息
	public String purchaseStorageOrdersListData() throws Exception {
		PageBean<SpAotuerpPurchaseList> spAotuerpPurchaseList = iSpAotuerpPurchaseOrdersService
				.findSpAotuerpPurchaseList(rows, page);
		objectMapper.registerModule(new Hibernate4Module());

		//封装json
		Map<String, Object> pageListMap = new HashMap<String, Object>();
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpAotuerpPurchaseList spAotuerpPurchaseList2 : spAotuerpPurchaseList.getRecordList()) {
			if (spAotuerpPurchaseList2.getSpAotuerpPurchaseOrders() != null) {
				Map<String, Object> rowMap = new HashMap<String, Object>();
				rowMap.put("sp_PurchaseStorageOrderId", spAotuerpPurchaseList2.getSpPurchaseId());//进货单编号
				rowMap.put("sp_PurchaseOrderId", spAotuerpPurchaseList2.getSpAotuerpPurchaseOrders()
						.getSpAotuerpPurchaseOrdersNumbers().getSpPurchaseOrderId());//订单编号
				rowMap.put("sp_Createdate", spAotuerpPurchaseList2.getSpCreatedate());//进货单创建日期
				rowMap.put("sp_Supplier", spAotuerpPurchaseList2.getSpSupplierBinfo().getSpSupplierDinfo().getSpSusup());//供货单位
				rowMap.put("sp_Warehousename", spAotuerpPurchaseList2.getSpAotuerpWarehouseInfo().getSpWarehousename());//仓库
				rowMap.put("sp_Employee", spAotuerpPurchaseList2.getSpEmployeeBinfo().getSpEmployeeDinfo()
						.getSpEprealname());//经办人
				rowMap.put("sp_EmployeeDepart", spAotuerpPurchaseList2.getSpEmployeeBinfo().getSpEmployeeDepart()
						.getSpEpdepartn());//部门
				rowMap.put("sp_Orderamount", spAotuerpPurchaseList2.getSpPaymentamount());//金额
				for (SpAotuerpPurchaseListPd spAotuerpPurchaseListPd : spAotuerpPurchaseList2
						.getSpAotuerpPurchaseListPds()) {
					System.out.println(spAotuerpPurchaseListPd.getSpProductSku().getSpProductBinfo()
							.getSpProductBrands().getSpProductCategory());
				}
				rowMap.put("sp_PurchaseOrderPds", spAotuerpPurchaseList2.getSpAotuerpPurchaseListPds());//商品
				rowMap.put("sp_Remark", spAotuerpPurchaseList2.getSpRemark());//备注
				listMaps.add(rowMap);
			}
		}
		//easyui total 总数  rows列表
		pageListMap.put("total", spAotuerpPurchaseList.getPageCount());//总条数
		pageListMap.put("rows", listMaps);//列表
		resp.getWriter().write(objectMapper.writeValueAsString(pageListMap));
		return NONE;
	}

	//进货订单审核
	public String purchaseStorageOrdersCheck() throws Exception {
		//查询进货单原对象
		if(spPurchaseId!=null&&spPurchaseId!=""){
			SpAotuerpPurchaseList spAotuerpPurchaseList = iSpAotuerpPurchaseStorageOrdersService
					.findSpAotuerpPurchaseListByPurchaseId(spPurchaseId);			
			for (SpAotuerpPurchaseListPd spAotuerpPurchaseListPd : spAotuerpPurchaseList.getSpAotuerpPurchaseListPds()) {
				//订单的sku商品数量增加(本身加上订单上的数量)
				spAotuerpPurchaseListPd.getSpProductSku().setSpPdcount(spAotuerpPurchaseListPd.getSpProductSku().getSpPdcount()+spAotuerpPurchaseListPd.getSpQuantity());
				//制定库存中的sku商品数量增加
				//1,指定库存中有sku商品信息
				//2,指定库存中没有sku商品信息
				boolean flag=true;
				for (SpAotuerpProductStocks spAotuerpProductStocks : spAotuerpPurchaseList.getSpAotuerpWarehouseInfo().getSpAotuerpProductStockses()) {
					//有sku商品id
					//判断进货单sku商品是否等于库存有的商品
					if(spAotuerpPurchaseListPd.getSpProductSku().getSpSkuid().equals(spAotuerpProductStocks.getSpProductSku().getSpSkuid())){
						//如果有，就把库存中的sku商品数量增加
						spAotuerpProductStocks.setSpQuantity(spAotuerpProductStocks.getSpQuantity()+spAotuerpPurchaseListPd.getSpQuantity());
						//不执行仓库新增sku商品
						flag=false;
					}
				}
				//如果没有sku商品
				if(flag){
					//新的仓库sku商品
					SpAotuerpProductStocks spAotuerpProductStocks=new SpAotuerpProductStocks();
					spAotuerpProductStocks.setSpProductSku(spAotuerpPurchaseListPd.getSpProductSku());
					spAotuerpProductStocks.setSpQuantity(spAotuerpPurchaseListPd.getSpQuantity());
					spAotuerpPurchaseList.getSpAotuerpWarehouseInfo().getSpAotuerpProductStockses().add(spAotuerpProductStocks);
				}
			}
			
			//进货订单设置执行完毕
			SpAotuerpPurchaseOrdersStatus spAotuerpPurchaseOrdersStatus=new SpAotuerpPurchaseOrdersStatus();
			spAotuerpPurchaseOrdersStatus.setSpId(3);
			spAotuerpPurchaseList.getSpAotuerpPurchaseOrders().setSpAotuerpPurchaseOrdersStatus(spAotuerpPurchaseOrdersStatus);
			
			iSpAotuerpPurchaseStorageOrdersService.update(spAotuerpPurchaseList);
			
			jsonResult.setCode(0);
			jsonResult.setMsg("请求成功");
			resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		}else{
			jsonResult.setCode(100001);
			jsonResult.setMsg("请求失败，参数错误");
			resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		}
		return NONE;
	}

	//getter setter
	public SpAotuerpPurchaseOrders getSpAotuerpPurchaseOrders() {
		return spAotuerpPurchaseOrders;
	}

	@JSON(serialize = true, deserialize = true)
	public void setSpAotuerpPurchaseOrders(SpAotuerpPurchaseOrders spAotuerpPurchaseOrders) {
		this.spAotuerpPurchaseOrders = spAotuerpPurchaseOrders;
	}

	public SpAotuerpPurchaseList getSpAotuerpPurchaseList() {
		return spAotuerpPurchaseList;
	}

	@JSON(serialize = true, deserialize = true)
	public void setSpAotuerpPurchaseList(SpAotuerpPurchaseList spAotuerpPurchaseList) {
		this.spAotuerpPurchaseList = spAotuerpPurchaseList;
	}

	public String getSpPurchaseId() {
		return spPurchaseId;
	}

	public void setSpPurchaseId(String spPurchaseId) {
		this.spPurchaseId = spPurchaseId;
	}

}
