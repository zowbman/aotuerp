package com.aotuspace.aotuerp.web.spcommodity.hbm;

import java.util.HashSet;
import java.util.Set;


/**
 * 
 * Title:SpProductPropertyValue
 * Description:属性值信息
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-10-22 下午6:21:02
 *
 */
public class SpProductPropertyValue  {
	private Integer spId;//序号
	private String spPropertyvalue;//属性值
	private Integer spPropertynameid;//属性名id
	//private Integer spCategoryid;//所属类目ID
	private SpProductCategory spProductCategory;//类目
	private SpProductPropertyName spProductPropertyName;//属性名
	private Set<SpProductPropertyName> spProductPropertyNames = new HashSet<SpProductPropertyName>();//属性名
	private Set<SpProductBproperties> spProductBpropertieses = new HashSet<SpProductBproperties>();//属性值

	public SpProductPropertyValue(){
		
	}
	
	public SpProductPropertyValue(Integer spId) {
		this.spId=spId;
	}
	
	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public String getSpPropertyvalue() {
		return spPropertyvalue;
	}

	public void setSpPropertyvalue(String spPropertyvalue) {
		this.spPropertyvalue = spPropertyvalue;
	}

	public Integer getSpPropertynameid() {
		return spPropertynameid;
	}

	public void setSpPropertynameid(Integer spPropertynameid) {
		this.spPropertynameid = spPropertynameid;
	}

	public SpProductCategory getSpProductCategory() {
		return spProductCategory;
	}

	public void setSpProductCategory(SpProductCategory spProductCategory) {
		this.spProductCategory = spProductCategory;
	}

	public SpProductPropertyName getSpProductPropertyName() {
		return spProductPropertyName;
	}

	public void setSpProductPropertyName(SpProductPropertyName spProductPropertyName) {
		this.spProductPropertyName = spProductPropertyName;
	}

	public Set<SpProductPropertyName> getSpProductPropertyNames() {
		return spProductPropertyNames;
	}

	public void setSpProductPropertyNames(Set<SpProductPropertyName> spProductPropertyNames) {
		this.spProductPropertyNames = spProductPropertyNames;
	}

	public Set<SpProductBproperties> getSpProductBpropertieses() {
		return spProductBpropertieses;
	}

	public void setSpProductBpropertieses(Set<SpProductBproperties> spProductBpropertieses) {
		this.spProductBpropertieses = spProductBpropertieses;
	}

}
