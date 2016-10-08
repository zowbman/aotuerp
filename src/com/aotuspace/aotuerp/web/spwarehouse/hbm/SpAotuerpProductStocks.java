package com.aotuspace.aotuerp.web.spwarehouse.hbm;

import java.util.HashSet;
import java.util.Set;

import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductSku;

/**
 * 
 * Title:SpProductStocks
 * Description:商品库存信息
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-11-5 下午2:49:01
 *
 */
public class SpAotuerpProductStocks {
	private Integer spId;//序号
	private SpProductSku spProductSku;

	//private SpAotuerpWarehouseInfo spAotuerpWarehouseInfo;
	/*private Integer spPdskuid;//商品sku
	private Integer spWarehouseid;//仓库
	*/

	private long spQuantity;//库存量
	private long spQuantitymin;//最小库存量
	private long spQuantitymax;//最大库存量

	private Set<SpAotuerpWarehouseInfo> spAotuerpWarehouseInfos = new HashSet<SpAotuerpWarehouseInfo>();

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

	public Set<SpAotuerpWarehouseInfo> getSpAotuerpWarehouseInfos() {
		return spAotuerpWarehouseInfos;
	}

	public void setSpAotuerpWarehouseInfos(Set<SpAotuerpWarehouseInfo> spAotuerpWarehouseInfos) {
		this.spAotuerpWarehouseInfos = spAotuerpWarehouseInfos;
	}

	public long getSpQuantity() {
		return spQuantity;
	}

	public void setSpQuantity(long spQuantity) {
		this.spQuantity = spQuantity;
	}

	public long getSpQuantitymin() {
		return spQuantitymin;
	}

	public void setSpQuantitymin(long spQuantitymin) {
		this.spQuantitymin = spQuantitymin;
	}

	public long getSpQuantitymax() {
		return spQuantitymax;
	}

	public void setSpQuantitymax(long spQuantitymax) {
		this.spQuantitymax = spQuantitymax;
	}

}
