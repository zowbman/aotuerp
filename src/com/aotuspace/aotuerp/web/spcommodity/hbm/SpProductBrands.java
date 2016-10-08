package com.aotuspace.aotuerp.web.spcommodity.hbm;

/**
 * 
 * Title:SpProductBrands
 * Description:品牌信息
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-10-22 下午6:13:25
 *
 */
public class SpProductBrands  {
	private Integer spId;//序号
	//private int spCategoryid;//类目
	private SpProductCategory spProductCategory;
	private String spBrandn;//品牌名称
	private String spBrandlogo;//品牌logo
	private String spBrandpresent;//品牌介绍
	
	public SpProductBrands() {

	}
	
	public SpProductBrands(Integer spId) {
		this.spId=spId;
	}
	


	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public SpProductCategory getSpProductCategory() {
		return spProductCategory;
	}

	public void setSpProductCategory(SpProductCategory spProductCategory) {
		this.spProductCategory = spProductCategory;
	}

	public String getSpBrandn() {
		return spBrandn;
	}

	public void setSpBrandn(String spBrandn) {
		this.spBrandn = spBrandn;
	}

	public String getSpBrandlogo() {
		return spBrandlogo;
	}

	public void setSpBrandlogo(String spBrandlogo) {
		this.spBrandlogo = spBrandlogo;
	}

	public String getSpBrandpresent() {
		return spBrandpresent;
	}

	public void setSpBrandpresent(String spBrandpresent) {
		this.spBrandpresent = spBrandpresent;
	}

}
