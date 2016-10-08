package com.aotuspace.aotuerp.web.spcommodity.model;

import java.util.List;

public class sp_sku {
	private List<String> sp_sku;//sku
	private String sp_sku_name;//sku
	private String sp_PdCount;//数量
	private String sp_PdPrice;//价格

	public List<String> getSp_sku() {
		return sp_sku;
	}

	public void setSp_sku(List<String> sp_sku) {
		this.sp_sku = sp_sku;
	}

	public String getSp_sku_name() {
		return sp_sku_name;
	}

	public void setSp_sku_name(String sp_sku_name) {
		this.sp_sku_name = sp_sku_name;
	}

	public String getSp_PdCount() {
		return sp_PdCount;
	}

	public void setSp_PdCount(String sp_PdCount) {
		this.sp_PdCount = sp_PdCount;
	}

	public String getSp_PdPrice() {
		return sp_PdPrice;
	}

	public void setSp_PdPrice(String sp_PdPrice) {
		this.sp_PdPrice = sp_PdPrice;
	}

}
