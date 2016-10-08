package com.aotuspace.aotuerp.web.model;

/**
 * 
 * Title:ProductPro
 * Description:商品属性
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-10-26 下午12:35:32
 *
 */
public class ProductPro {
	private String sp_DisplayName;//属性名（显示）
	private String sp_FieldName;//属性名（值）
	private String sp_InputType;//输入类型
	private String sp_DefaultValue;//默认值
	private Integer sp_IsRequired;//是否必填
	private String sp_ProType;//属性类型（spu，还是sku）

	public ProductPro() {

	}

	/**
	 * 
	 * @param sp_DisplayName
	 * @param sp_FieldName
	 * @param sp_InputType
	 * @param sp_DefaultValue
	 * @param sp_IsRequired
	 */
	public ProductPro(String sp_DisplayName, String sp_FieldName, String sp_InputType, String sp_DefaultValue,
			Integer sp_IsRequired,String sp_ProType) {
		this.sp_DisplayName = sp_DisplayName;
		this.sp_FieldName = sp_FieldName;
		this.sp_InputType = sp_InputType;
		this.sp_DefaultValue = sp_DefaultValue;
		this.sp_IsRequired = sp_IsRequired;
		this.sp_ProType=sp_ProType;

	}

	public String getSp_DisplayName() {
		return sp_DisplayName;
	}

	public void setSp_DisplayName(String sp_DisplayName) {
		this.sp_DisplayName = sp_DisplayName;
	}

	public String getSp_FieldName() {
		return sp_FieldName;
	}

	public void setSp_FieldName(String sp_FieldName) {
		this.sp_FieldName = sp_FieldName;
	}

	public String getSp_InputType() {
		return sp_InputType;
	}

	public void setSp_InputType(String sp_InputType) {
		this.sp_InputType = sp_InputType;
	}

	public String getSp_DefaultValue() {
		return sp_DefaultValue;
	}

	public void setSp_DefaultValue(String sp_DefaultValue) {
		this.sp_DefaultValue = sp_DefaultValue;
	}

	public Integer getSp_IsRequired() {
		return sp_IsRequired;
	}

	public void setSp_IsRequired(Integer sp_IsRequired) {
		this.sp_IsRequired = sp_IsRequired;
	}

	public String getSp_ProType() {
		return sp_ProType;
	}

	public void setSp_ProType(String sp_ProType) {
		this.sp_ProType = sp_ProType;
	}

}
