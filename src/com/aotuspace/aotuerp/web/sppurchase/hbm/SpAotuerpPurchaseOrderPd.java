package com.aotuspace.aotuerp.web.sppurchase.hbm;

import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductSku;

/**
 * 
 * Title:SpAotuerpPurchaseOrderPd
 * Description:进货订单商品信息
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-11-9 上午11:43:52
 *
 */

public class SpAotuerpPurchaseOrderPd {

	private Integer spId;//序号
	private SpProductSku spProductSku;//sku商品信息
	private Integer spQuantity;//数量
	private long spUnitprice;//单价
	private long spTotalprice;//总价

	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public SpProductSku getSpProductSku() {
		return spProductSku;
	}

	public void setSpProductSku(SpProductSku spProductSku) {
		this.spProductSku = spProductSku;
	}

	public Integer getSpQuantity() {
		return spQuantity;
	}

	public void setSpQuantity(Integer spQuantity) {
		this.spQuantity = spQuantity;
	}

	public long getSpUnitprice() {
		return spUnitprice;
	}

	public void setSpUnitprice(long spUnitprice) {
		this.spUnitprice = spUnitprice;
	}

	public long getSpTotalprice() {
		return spTotalprice;
	}

	public void setSpTotalprice(long spTotalprice) {
		this.spTotalprice = spTotalprice;
	}

}