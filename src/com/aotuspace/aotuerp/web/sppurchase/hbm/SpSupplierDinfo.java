package com.aotuspace.aotuerp.web.sppurchase.hbm;

import java.io.Serializable;
/**
 * 
 * Title:SpSupplierDinfo
 * Description:供应商详细信息表(sp_supplier_dinfo)sp_supplier_detailinfo					
 * Company:aotuspace
 * @author    sida
 * @date      2015-10-13 下午12:16:51
 *
 */

public class SpSupplierDinfo implements Serializable {
	
	//复合主键
	private SpSupplierBinfoKey spSupplierBinfoKey;
	
	//商家名称
	private String spSusup;
	
	//商家联系人姓名
	private String spSucont;

	//商家联系电话
	private String spSutel;

	//商家手机号码
	private String spSumobie;
	
	//商家所在地
	private String spSudistrict;
	
	//商家详细地址
	private String spSuaddress;
	
	//商家所属行业ID
	private Integer spSutraid;
	
	//商家简介
	private String spSuresume;
	
	//商家logo
	private String spSulogo;

	public SpSupplierBinfoKey getSpSupplierBinfoKey() {
		return spSupplierBinfoKey;
	}

	public void setSpSupplierBinfoKey(SpSupplierBinfoKey spSupplierBinfoKey) {
		this.spSupplierBinfoKey = spSupplierBinfoKey;
	}

	public String getSpSusup() {
		return spSusup;
	}

	public void setSpSusup(String spSusup) {
		this.spSusup = spSusup;
	}

	public String getSpSucont() {
		return spSucont;
	}

	public void setSpSucont(String spSucont) {
		this.spSucont = spSucont;
	}

	public String getSpSutel() {
		return spSutel;
	}

	public void setSpSutel(String spSutel) {
		this.spSutel = spSutel;
	}

	public String getSpSumobie() {
		return spSumobie;
	}

	public void setSpSumobie(String spSumobie) {
		this.spSumobie = spSumobie;
	}

	public String getSpSudistrict() {
		return spSudistrict;
	}

	public void setSpSudistrict(String spSudistrict) {
		this.spSudistrict = spSudistrict;
	}

	public String getSpSuaddress() {
		return spSuaddress;
	}

	public void setSpSuaddress(String spSuaddress) {
		this.spSuaddress = spSuaddress;
	}

	public Integer getSpSutraid() {
		return spSutraid;
	}

	public void setSpSutraid(Integer spSutraid) {
		this.spSutraid = spSutraid;
	}

	public String getSpSuresume() {
		return spSuresume;
	}

	public void setSpSuresume(String spSuresume) {
		this.spSuresume = spSuresume;
	}

	public String getSpSulogo() {
		return spSulogo;
	}

	public void setSpSulogo(String spSulogo) {
		this.spSulogo = spSulogo;
	}
	
	
}
