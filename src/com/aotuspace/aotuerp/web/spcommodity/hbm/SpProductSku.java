package com.aotuspace.aotuerp.web.spcommodity.hbm;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.aotuspace.aotuerp.web.spwarehouse.hbm.SpAotuerpProductStocks;

/**
 * 
 * Title:SpProductSku
 * Description:商品SkuS
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-10-22 下午6:29:01
 *
 */

public class SpProductSku {
	private Integer spSkuid;//skuid
	private SpProductBinfo spProductBinfo;
	private Integer spPdcount;//数量
	private Long spPdprice;//价格
	//private Integer spPdstatus;//商品状态
	private SpProductStatus spProductStatus;
	private String spSkupropertiesname;//sku属性字符串
	private String spSkuproperties;//sku属性
	private Date spPdcredate;//创建日期
	private Set<SpProductBproperties> spProductBpropertieses = new HashSet<SpProductBproperties>();

	public Integer getSpSkuid() {
		return spSkuid;
	}

	public void setSpSkuid(Integer spSkuid) {
		this.spSkuid = spSkuid;
	}

	public SpProductBinfo getSpProductBinfo() {
		return spProductBinfo;
	}

	public void setSpProductBinfo(SpProductBinfo spProductBinfo) {
		this.spProductBinfo = spProductBinfo;
	}

	public Integer getSpPdcount() {
		return spPdcount;
	}

	public void setSpPdcount(Integer spPdcount) {
		this.spPdcount = spPdcount;
	}

	public Long getSpPdprice() {
		return spPdprice;
	}

	public void setSpPdprice(Long spPdprice) {
		this.spPdprice = spPdprice;
	}

	public SpProductStatus getSpProductStatus() {
		return spProductStatus;
	}

	public void setSpProductStatus(SpProductStatus spProductStatus) {
		this.spProductStatus = spProductStatus;
	}

	public String getSpSkupropertiesname() {
		return spSkupropertiesname;
	}

	public void setSpSkupropertiesname(String spSkupropertiesname) {
		this.spSkupropertiesname = spSkupropertiesname;
	}

	public String getSpSkuproperties() {
		return spSkuproperties;
	}

	public void setSpSkuproperties(String spSkuproperties) {
		this.spSkuproperties = spSkuproperties;
	}

	public Date getSpPdcredate() {
		return spPdcredate;
	}

	public void setSpPdcredate(Date spPdcredate) {
		this.spPdcredate = spPdcredate;
	}

	public Set<SpProductBproperties> getSpProductBpropertieses() {
		return spProductBpropertieses;
	}

	public void setSpProductBpropertieses(Set<SpProductBproperties> spProductBpropertieses) {
		this.spProductBpropertieses = spProductBpropertieses;
	}

}
