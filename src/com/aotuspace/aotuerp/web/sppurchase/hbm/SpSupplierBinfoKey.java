package com.aotuspace.aotuerp.web.sppurchase.hbm;

import java.io.Serializable;

/**
 * 
 * Title:SpSupperlierBinfoKey
 * Description:供应商基本信息表复合主键
 * Company:aotuspace
 * @author    sida
 * @date      2015-10-12 下午2:52:09
 *
 */

public class SpSupplierBinfoKey implements Serializable {
	
	// id
	private Integer spId;
	// 员工id
	private Integer spSuid;

	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public Integer getSpSuid() {
		return spSuid;
	}

	public void setSpSuid(Integer spSuid) {
		this.spSuid = spSuid;
	}


}
