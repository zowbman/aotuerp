package com.aotuspace.aotuerp.web.sppurchase.hbm;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

import com.aotuspace.aotuerp.web.sperplogin.hbm.SpEmployeeBinfo;
import com.aotuspace.aotuerp.web.spwarehouse.hbm.SpAotuerpWarehouseInfo;

/**
 * 
 * Title:SpAotuerpPurchaseOrders
 * Description:进货订单
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-11-9 上午11:44:44
 *
 */

public class SpAotuerpPurchaseOrders {
	private Integer spId;//序号
	private SpEmployeeBinfo spEmployeeBinfo;//员工
	private SpAotuerpWarehouseInfo spAotuerpWarehouseInfo;//仓库
	private SpSupplierBinfo spSupplierBinfo;
	private SpAotuerpPurchaseOrdersStatus spAotuerpPurchaseOrdersStatus;//进货订单状态
	//private SpAotuerpPurchaseOrderPd spAotuerpPurchaseOrderPd;//进货订单商品
	private SpAotuerpPurchaseOrdersNumbers spAotuerpPurchaseOrdersNumbers;
	private Integer spPurchaseorderid;
	private Integer spOrdercontractid;//合同号
	private Date spCreatedate;//创建日期
	private Date spPlanarrivaldate;//计划到货日期
	private long spOrderamount;//订单总计
	private Date spFinishdate;//完成日期
	private String spRemark;//备注
	private SpAotuerpPurchaseList spAotuerpPurchaseList;//进货单
	private Set<SpAotuerpPurchaseOrderPd> spAotuerpPurchaseOrderPds = new HashSet<SpAotuerpPurchaseOrderPd>();

	
	
	public Integer getSpPurchaseorderid() {
		return spPurchaseorderid;
	}

	public void setSpPurchaseorderid(Integer spPurchaseorderid) {
		this.spPurchaseorderid = spPurchaseorderid;
	}

	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public SpEmployeeBinfo getSpEmployeeBinfo() {
		return spEmployeeBinfo;
	}

	public void setSpEmployeeBinfo(SpEmployeeBinfo spEmployeeBinfo) {
		this.spEmployeeBinfo = spEmployeeBinfo;
	}

	public SpAotuerpWarehouseInfo getSpAotuerpWarehouseInfo() {
		return spAotuerpWarehouseInfo;
	}

	public void setSpAotuerpWarehouseInfo(SpAotuerpWarehouseInfo spAotuerpWarehouseInfo) {
		this.spAotuerpWarehouseInfo = spAotuerpWarehouseInfo;
	}

	public SpSupplierBinfo getSpSupplierBinfo() {
		return spSupplierBinfo;
	}

	public void setSpSupplierBinfo(SpSupplierBinfo spSupplierBinfo) {
		this.spSupplierBinfo = spSupplierBinfo;
	}

	public SpAotuerpPurchaseOrdersStatus getSpAotuerpPurchaseOrdersStatus() {
		return spAotuerpPurchaseOrdersStatus;
	}

	public void setSpAotuerpPurchaseOrdersStatus(SpAotuerpPurchaseOrdersStatus spAotuerpPurchaseOrdersStatus) {
		this.spAotuerpPurchaseOrdersStatus = spAotuerpPurchaseOrdersStatus;
	}

	public Set<SpAotuerpPurchaseOrderPd> getSpAotuerpPurchaseOrderPds() {
		return spAotuerpPurchaseOrderPds;
	}

	public void setSpAotuerpPurchaseOrderPds(Set<SpAotuerpPurchaseOrderPd> spAotuerpPurchaseOrderPds) {
		this.spAotuerpPurchaseOrderPds = spAotuerpPurchaseOrderPds;
	}

	public SpAotuerpPurchaseOrdersNumbers getSpAotuerpPurchaseOrdersNumbers() {
		return spAotuerpPurchaseOrdersNumbers;
	}

	public void setSpAotuerpPurchaseOrdersNumbers(SpAotuerpPurchaseOrdersNumbers spAotuerpPurchaseOrdersNumbers) {
		this.spAotuerpPurchaseOrdersNumbers = spAotuerpPurchaseOrdersNumbers;
	}

	public Integer getSpOrdercontractid() {
		return spOrdercontractid;
	}

	public void setSpOrdercontractid(Integer spOrdercontractid) {
		this.spOrdercontractid = spOrdercontractid;
	}

	public Date getSpCreatedate() {
		return spCreatedate;
	}

	public void setSpCreatedate(Date spCreatedate) {
		this.spCreatedate = spCreatedate;
	}

	public Date getSpPlanarrivaldate() {
		return spPlanarrivaldate;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public void setSpPlanarrivaldate(Date spPlanarrivaldate) {
		this.spPlanarrivaldate = spPlanarrivaldate;
	}

	public long getSpOrderamount() {
		return spOrderamount;
	}

	public void setSpOrderamount(long spOrderamount) {
		this.spOrderamount = spOrderamount;
	}

	public Date getSpFinishdate() {
		return spFinishdate;
	}

	public void setSpFinishdate(Date spFinishdate) {
		this.spFinishdate = spFinishdate;
	}

	public String getSpRemark() {
		return spRemark;
	}

	public void setSpRemark(String spRemark) {
		this.spRemark = spRemark;
	}

	public SpAotuerpPurchaseList getSpAotuerpPurchaseList() {
		return spAotuerpPurchaseList;
	}

	public void setSpAotuerpPurchaseList(SpAotuerpPurchaseList spAotuerpPurchaseList) {
		this.spAotuerpPurchaseList = spAotuerpPurchaseList;
	}

}