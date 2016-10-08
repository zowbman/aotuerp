package com.aotuspace.aotuerp.web.util.sort.rule;

/**
 * 
 * Title:SpProductBpropertiesOrder
 * Description:≈≈–ÚπÊ‘Ú
 * Company:aotuspace
 * @author    Œ∞±¶
 * @date      2015-11-20 œ¬ŒÁ5:17:18
 *
 */
public class SpProductBpropertiesOrder {
	public final static int _LONG = 0;
	public final static int _STRING = 1;
	public final static int _DATE = 2;
	public final static int _BIGDECIMAL = 3;
	public final static int _Integer=4;

	private String propertyName;

	private boolean isAsc;

	private int dataType;

	public SpProductBpropertiesOrder(String propertyName, boolean isAsc, int dataType) {
		this.propertyName = propertyName;
		this.isAsc = isAsc;
		this.dataType = dataType;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public boolean isAsc() {
		return isAsc;
	}

	public void setAsc(boolean isAsc) {
		this.isAsc = isAsc;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public static int getLong() {
		return _LONG;
	}

	public static int getString() {
		return _STRING;
	}

	public static int getDate() {
		return _DATE;
	}

	public static int getBigdecimal() {
		return _BIGDECIMAL;
	}

}
