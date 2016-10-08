package com.aotuspace.aotuerp.web.spcommodity.model;

import java.util.List;

public class Commodity {
	private String sp_spu_brand;//品牌
	private String sp_spu_title;//商品名称
	private List<String> sp_spu;//spu
	private List<sp_sku> sp_sku;//sku
	
	public String getSp_spu_brand() {
		return sp_spu_brand;
	}

	public void setSp_spu_brand(String sp_spu_brand) {
		this.sp_spu_brand = sp_spu_brand;
	}

	public String getSp_spu_title() {
		return sp_spu_title;
	}

	public void setSp_spu_title(String sp_spu_title) {
		this.sp_spu_title = sp_spu_title;
	}

	public List<String> getSp_spu() {
		return sp_spu;
	}

	public void setSp_spu(List<String> sp_spu) {
		this.sp_spu = sp_spu;
	}

	public List<sp_sku> getSp_sku() {
		return sp_sku;
	}

	public void setSp_sku(List<sp_sku> sp_sku) {
		this.sp_sku = sp_sku;
	}
}
