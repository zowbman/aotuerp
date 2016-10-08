package com.aotuspace.aotuerp.web.spcommodity.hbm;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * Title:SpProductCategory
 * Description:类目信息
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-10-22 下午6:16:29
 *
 */
public class SpProductCategory  {
	private Integer spId;//序号
	private String spCategoryn;//类目名称
	//private String spCategorypid;//父类目ID
	private SpProductCategory spProductCategory;//父类目
	private Set<SpProductCategory> spProductCategories = new HashSet<SpProductCategory>();//父类目
	private Set<SpProductPropertyName> spProductPropertyNames = new HashSet<SpProductPropertyName>();//属性名
	private Set<SpProductPropertyValue> spProductPropertyValues = new HashSet<SpProductPropertyValue>();//属性值
	private Set<SpProductBrands> spProductBrandses = new HashSet<SpProductBrands>();//品牌

	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public String getSpCategoryn() {
		return spCategoryn;
	}

	public void setSpCategoryn(String spCategoryn) {
		this.spCategoryn = spCategoryn;
	}

	public SpProductCategory getSpProductCategory() {
		return spProductCategory;
	}

	public void setSpProductCategory(SpProductCategory spProductCategory) {
		this.spProductCategory = spProductCategory;
	}

	public Set<SpProductCategory> getSpProductCategories() {
		return spProductCategories;
	}

	public void setSpProductCategories(Set<SpProductCategory> spProductCategories) {
		this.spProductCategories = spProductCategories;
	}

	public Set<SpProductPropertyName> getSpProductPropertyNames() {
		return spProductPropertyNames;
	}

	public void setSpProductPropertyNames(Set<SpProductPropertyName> spProductPropertyNames) {
		this.spProductPropertyNames = spProductPropertyNames;
	}

	public Set<SpProductPropertyValue> getSpProductPropertyValues() {
		return spProductPropertyValues;
	}

	public void setSpProductPropertyValues(Set<SpProductPropertyValue> spProductPropertyValues) {
		this.spProductPropertyValues = spProductPropertyValues;
	}

	public Set<SpProductBrands> getSpProductBrandses() {
		return spProductBrandses;
	}

	public void setSpProductBrandses(Set<SpProductBrands> spProductBrandses) {
		this.spProductBrandses = spProductBrandses;
	}

}
