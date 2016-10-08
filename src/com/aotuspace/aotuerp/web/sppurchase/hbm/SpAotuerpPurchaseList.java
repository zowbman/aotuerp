package com.aotuspace.aotuerp.web.sppurchase.hbm;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

import com.aotuspace.aotuerp.web.sperplogin.hbm.SpEmployeeBinfo;
import com.aotuspace.aotuerp.web.spwarehouse.hbm.SpAotuerpProductStocks;
import com.aotuspace.aotuerp.web.spwarehouse.hbm.SpAotuerpWarehouseInfo;

/**
 * 
 * Title:SpAotuerpPurchaseList
 * Description:进货单
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-11-9 上午11:39:09
 *
 */
public class SpAotuerpPurchaseList {

	private Integer spId;//序号
	private SpEmployeeBinfo spEmployeeBinfo;//员工
	private SpAotuerpWarehouseInfo spAotuerpWarehouseInfo;//仓库
	private SpSupplierBinfo spSupplierBinfo;
	private SpAotuerpPurchaseOrders spAotuerpPurchaseOrders;//进货订单
	private String spPurchaseId;//进货单
	private Date spPaymentdate;//付款日期
	private Integer spIsinvoice;//是否开发票
	private long spPaymentamount;//付款金额
	private Date spCreatedate;//创建日期
	private Date spFinishdate;//完成日期
	private String spRemark;//备注
	private Set<SpAotuerpPurchaseListPd> spAotuerpPurchaseListPds = new HashSet<SpAotuerpPurchaseListPd>();//进货订单商品
	private Set<SpAotuerpProductStocks> spAotuerpProductStocks = new HashSet<SpAotuerpProductStocks>();

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

	public SpAotuerpPurchaseOrders getSpAotuerpPurchaseOrders() {
		return spAotuerpPurchaseOrders;
	}

	public void setSpAotuerpPurchaseOrders(SpAotuerpPurchaseOrders spAotuerpPurchaseOrders) {
		this.spAotuerpPurchaseOrders = spAotuerpPurchaseOrders;
	}

	public Set<SpAotuerpPurchaseListPd> getSpAotuerpPurchaseListPds() {
		return spAotuerpPurchaseListPds;
	}

	public void setSpAotuerpPurchaseListPds(Set<SpAotuerpPurchaseListPd> spAotuerpPurchaseListPds) {
		this.spAotuerpPurchaseListPds = spAotuerpPurchaseListPds;
	}

	public String getSpPurchaseId() {
		return spPurchaseId;
	}

	public void setSpPurchaseId(String spPurchaseId) {
		this.spPurchaseId = spPurchaseId;
	}

	public Date getSpPaymentdate() {
		return spPaymentdate;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public void setSpPaymentdate(Date spPaymentdate) {
		this.spPaymentdate = spPaymentdate;
	}

	public Integer getSpIsinvoice() {
		return spIsinvoice;
	}

	public void setSpIsinvoice(Integer spIsinvoice) {
		this.spIsinvoice = spIsinvoice;
	}

	public long getSpPaymentamount() {
		return spPaymentamount;
	}

	public void setSpPaymentamount(long spPaymentamount) {
		this.spPaymentamount = spPaymentamount;
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

	public Date getSpCreatedate() {
		return spCreatedate;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public void setSpCreatedate(Date spCreatedate) {
		this.spCreatedate = spCreatedate;
	}

	public Set<SpAotuerpProductStocks> getSpAotuerpProductStocks() {
		return spAotuerpProductStocks;
	}

	public void setSpAotuerpProductStocks(Set<SpAotuerpProductStocks> spAotuerpProductStocks) {
		this.spAotuerpProductStocks = spAotuerpProductStocks;
	}

}