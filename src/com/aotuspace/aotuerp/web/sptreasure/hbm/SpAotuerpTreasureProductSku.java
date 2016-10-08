package com.aotuspace.aotuerp.web.sptreasure.hbm;

import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductSku;

/**
 * 
 * Title:SpAotuerpTreasureProductSku
 * Description:宝贝商品sku
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-11-18 下午6:21:47
 *
 */

public class SpAotuerpTreasureProductSku {

	// Fields    

	private Integer spId;
	private SpProductSku spProductSku;//sku商品
	private SpAotuerpTreasureInfo spAotuerpTreasureInfo;//宝贝
	private long spSalesprice;//销售价格
	private Integer spSalesquantity;//库存数量
	private Integer spSalesorderquantity;//订单数量
	private long spTotalprice;//总价格

	public Integer getSpId() {
		return this.spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public SpProductSku getSpProductSku() {
		return this.spProductSku;
	}

	public void setSpProductSku(SpProductSku spProductSku) {
		this.spProductSku = spProductSku;
	}

	public SpAotuerpTreasureInfo getSpAotuerpTreasureInfo() {
		return this.spAotuerpTreasureInfo;
	}

	public void setSpAotuerpTreasureInfo(SpAotuerpTreasureInfo spAotuerpTreasureInfo) {
		this.spAotuerpTreasureInfo = spAotuerpTreasureInfo;
	}

	public long getSpSalesprice() {
		return spSalesprice;
	}

	public void setSpSalesprice(long spSalesprice) {
		this.spSalesprice = spSalesprice;
	}

	public Integer getSpSalesquantity() {
		return spSalesquantity;
	}

	public void setSpSalesquantity(Integer spSalesquantity) {
		this.spSalesquantity = spSalesquantity;
	}

	public Integer getSpSalesorderquantity() {
		return spSalesorderquantity;
	}

	public void setSpSalesorderquantity(Integer spSalesorderquantity) {
		this.spSalesorderquantity = spSalesorderquantity;
	}

	public long getSpTotalprice() {
		return spTotalprice;
	}

	public void setSpTotalprice(long spTotalprice) {
		this.spTotalprice = spTotalprice;
	}

}