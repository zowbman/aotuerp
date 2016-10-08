package com.aotuspace.aotuerp.web.spcommodity.hbm;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * 
 * Title:SpProductBproperties
 * Description:商品属性基础信息
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-10-22 下午6:35:56
 *
 */
public class SpProductBproperties {
	private Integer spId;//序号
	//private String spPdid;//商品id
	private SpProductBinfo spProductBinfo;
	//private int spPropertynameid;//属性名id
	private SpProductPropertyName spProductPropertyName;
	//private int spPropertyvalueid;//属性值id
	private SpProductPropertyValue spProductPropertyValue;
	
	private Integer spIssku;//是否sku属性
	
	private SpProductSku spProductSku;

	//private Integer spSkuid;//skuid

	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public SpProductBinfo getSpProductBinfo() {
		return spProductBinfo;
	}

	@JsonIgnore
	public void setSpProductBinfo(SpProductBinfo spProductBinfo) {
		this.spProductBinfo = spProductBinfo;
	}

	public SpProductPropertyName getSpProductPropertyName() {
		return spProductPropertyName;
	}

	public void setSpProductPropertyName(SpProductPropertyName spProductPropertyName) {
		this.spProductPropertyName = spProductPropertyName;
	}

	public SpProductPropertyValue getSpProductPropertyValue() {
		return spProductPropertyValue;
	}

	public void setSpProductPropertyValue(SpProductPropertyValue spProductPropertyValue) {
		this.spProductPropertyValue = spProductPropertyValue;
	}

	public Integer getSpIssku() {
		return spIssku;
	}

	public void setSpIssku(Integer spIssku) {
		this.spIssku = spIssku;
	}

	public SpProductSku getSpProductSku() {
		return spProductSku;
	}
	
	@JsonIgnore
	public void setSpProductSku(SpProductSku spProductSku) {
		this.spProductSku = spProductSku;
	}

}
