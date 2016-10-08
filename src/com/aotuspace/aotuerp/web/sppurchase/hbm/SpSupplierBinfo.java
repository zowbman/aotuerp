package com.aotuspace.aotuerp.web.sppurchase.hbm;

import java.io.Serializable;

/**
 * 
 * Title:SpSupperlierBinfo
 * Description:供应商基本信息表
 * Company:aotuspace
 * @author    sida
 * @date      2015-10-12 下午2:52:43
 *
 */

public class SpSupplierBinfo  implements Serializable {
	
	//复合主键
	private SpSupplierBinfoKey spSupplierBinfoKey;
	
	//供应商帐号
	private String spSuaccount;

	//供应商密码
	private String spSupassword;

	private SpSupplierDinfo spSupplierDinfo;

	public SpSupplierBinfoKey getSpSupplierBinfoKey() {
		return spSupplierBinfoKey;
	}

	public void setSpSupplierBinfoKey(SpSupplierBinfoKey spSupplierBinfoKey) {
		this.spSupplierBinfoKey = spSupplierBinfoKey;
	}

	public String getSpSuaccount() {
		return spSuaccount;
	}

	public void setSpSuaccount(String spSuaccount) {
		this.spSuaccount = spSuaccount;
	}

	public String getSpSupassword() {
		return spSupassword;
	}

	public void setSpSupassword(String spSupassword) {
		this.spSupassword = spSupassword;
	}

	public SpSupplierDinfo getSpSupplierDinfo() {
		return spSupplierDinfo;
	}

	public void setSpSupplierDinfo(SpSupplierDinfo spSupplierDinfo) {
		this.spSupplierDinfo = spSupplierDinfo;
	}
	

}
