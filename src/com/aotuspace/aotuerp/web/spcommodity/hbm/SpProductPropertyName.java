package com.aotuspace.aotuerp.web.spcommodity.hbm;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * Title:SpProductPropertyName
 * Description:属性名信息
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-10-22 下午6:18:20
 *
 */
public class SpProductPropertyName {
	private Integer spId;//序号
	//private Integer spParentpvalue;//属性值id
	private SpProductPropertyValue spProductPropertyValue;//属性值
	private String spPropertyname;//属性名
	private String spAlias;//别名
	//private Integer spCategoryid;//所属类目ID
	private SpProductCategory spProductCategory;
	private Integer spIscolorpro;//是否颜色属性
	private Integer spIsenumpro;//是否枚举属性
	private Integer spIsimportpro;//是否输入属性
	private Integer spIskeypro;//是否关键属性
	private Integer spIssearchpro;//是否搜索属性
	private Integer spIsrequirepro;//是否必填属性
	private Integer spIsmultiselectpro;//是否多选属性
	private Integer spIssalepro;//是否销售属性
	private  Integer spSort;//排序
	//private Integer spParentid;//父属性
	private SpProductPropertyName spProductPropertyName;//属性名
	private Set<SpProductPropertyValue> spProductPropertyValues = new HashSet<SpProductPropertyValue>();//属性值
	private Set<SpProductPropertyName> spProductPropertyNames = new HashSet<SpProductPropertyName>();//属性名
	private Set<SpProductBproperties> spProductBpropertieses = new HashSet<SpProductBproperties>();//基础属性
	
	public SpProductPropertyName(){
		
	}
	
	public SpProductPropertyName(Integer spId) {
		this.spId=spId;
	}


	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public SpProductPropertyValue getSpProductPropertyValue() {
		return spProductPropertyValue;
	}

	public void setSpProductPropertyValue(SpProductPropertyValue spProductPropertyValue) {
		this.spProductPropertyValue = spProductPropertyValue;
	}

	public String getSpPropertyname() {
		return spPropertyname;
	}

	public void setSpPropertyname(String spPropertyname) {
		this.spPropertyname = spPropertyname;
	}

	public SpProductCategory getSpProductCategory() {
		return spProductCategory;
	}

	public void setSpProductCategory(SpProductCategory spProductCategory) {
		this.spProductCategory = spProductCategory;
	}

	public Integer getSpIssalepro() {
		return spIssalepro;
	}

	public void setSpIssalepro(Integer spIssalepro) {
		this.spIssalepro = spIssalepro;
	}

	public SpProductPropertyName getSpProductPropertyName() {
		return spProductPropertyName;
	}

	public void setSpProductPropertyName(SpProductPropertyName spProductPropertyName) {
		this.spProductPropertyName = spProductPropertyName;
	}

	public Set<SpProductPropertyValue> getSpProductPropertyValues() {
		return spProductPropertyValues;
	}

	public void setSpProductPropertyValues(Set<SpProductPropertyValue> spProductPropertyValues) {
		this.spProductPropertyValues = spProductPropertyValues;
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

	public Integer getSpIscolorpro() {
		return spIscolorpro;
	}

	public void setSpIscolorpro(Integer spIscolorpro) {
		this.spIscolorpro = spIscolorpro;
	}

	public Integer getSpIsenumpro() {
		return spIsenumpro;
	}

	public void setSpIsenumpro(Integer spIsenumpro) {
		this.spIsenumpro = spIsenumpro;
	}

	public Integer getSpIsimportpro() {
		return spIsimportpro;
	}

	public void setSpIsimportpro(Integer spIsimportpro) {
		this.spIsimportpro = spIsimportpro;
	}

	public Integer getSpIskeypro() {
		return spIskeypro;
	}

	public void setSpIskeypro(Integer spIskeypro) {
		this.spIskeypro = spIskeypro;
	}

	public Integer getSpIssearchpro() {
		return spIssearchpro;
	}

	public void setSpIssearchpro(Integer spIssearchpro) {
		this.spIssearchpro = spIssearchpro;
	}

	public Integer getSpIsrequirepro() {
		return spIsrequirepro;
	}

	public void setSpIsrequirepro(Integer spIsrequirepro) {
		this.spIsrequirepro = spIsrequirepro;
	}

	public Integer getSpIsmultiselectpro() {
		return spIsmultiselectpro;
	}

	public void setSpIsmultiselectpro(Integer spIsmultiselectpro) {
		this.spIsmultiselectpro = spIsmultiselectpro;
	}

	public Integer getSpSort() {
		return spSort;
	}

	public void setSpSort(Integer spSort) {
		this.spSort = spSort;
	}

	public String getSpAlias() {
		return spAlias;
	}

	public void setSpAlias(String spAlias) {
		this.spAlias = spAlias;
	}

}
