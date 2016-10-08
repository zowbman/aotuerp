package com.aotuspace.aotuerp.web.sperplogin.hbm;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * Title:EmployeeBinfo Description:员工基本信息表 Company:aotuspace
 * 
 * @author sida
 * @date 2015-9-2 下午5:32:53
 * 
 */

public class SpEmployeeDinfo implements Serializable{
	
	//复合主键
	private SpEmployeeBinfoKey spEmployeeBinfoKey;
	
	//员工真实姓名
	private String spEprealname;
	
	//员工头像
	private String spEpicon;
	
	//员工出生日期
	private Date spEpbirth;
	
	//员工星座
	private Integer spEpcon;
	
	//员工手机号码
	private String spEpmobie;
	
	//员工身份证号码
	private String spEpidnum;
	
	//员工生肖
	private Integer spEpanimal;
	
	//员工年龄
	private Integer spEpage;
	
	//员工省市
	private String spEmpdistrict;
	
	//员工详细地址
	private String spEpaddress;
	
	//性别
	private String spEpsex;
	
	public SpEmployeeBinfoKey getSpEmployeeBinfoKey() {
		return spEmployeeBinfoKey;
	}

	public void setSpEmployeeBinfoKey(SpEmployeeBinfoKey spEmployeeBinfoKey) {
		this.spEmployeeBinfoKey = spEmployeeBinfoKey;
	}

	public String getSpEprealname() {
		return spEprealname;
	}

	public void setSpEprealname(String spEprealname) {
		this.spEprealname = spEprealname;
	}

	public String getSpEpicon() {
		return spEpicon;
	}

	public void setSpEpicon(String spEpicon) {
		this.spEpicon = spEpicon;
	}

	public Integer getSpEpcon() {
		return spEpcon;
	}

	public void setSpEpcon(Integer spEpcon) {
		this.spEpcon = spEpcon;
	}

	public String getSpEpmobie() {
		return spEpmobie;
	}

	public void setSpEpmobie(String spEpmobie) {
		this.spEpmobie = spEpmobie;
	}

	public String getSpEpidnum() {
		return spEpidnum;
	}

	public void setSpEpidnum(String spEpidnum) {
		this.spEpidnum = spEpidnum;
	}

	public Integer getSpEpanimal() {
		return spEpanimal;
	}

	public void setSpEpanimal(Integer spEpanimal) {
		this.spEpanimal = spEpanimal;
	}

	public Integer getSpEpage() {
		return spEpage;
	}

	public void setSpEpage(Integer spEpage) {
		this.spEpage = spEpage;
	}

	public String getSpEpaddress() {
		return spEpaddress;
	}

	public void setSpEpaddress(String spEpaddress) {
		this.spEpaddress = spEpaddress;
	}

	public Date getSpEpbirth() {
		return spEpbirth;
	}

	public void setSpEpbirth(Date spEpbirth) {
		this.spEpbirth = spEpbirth;
	}

	public String getSpEmpdistrict() {
		return spEmpdistrict;
	}

	public void setSpEmpdistrict(String spEmpdistrict) {
		this.spEmpdistrict = spEmpdistrict;
	}

	public String getSpEpsex() {
		return spEpsex;
	}

	public void setSpEpsex(String spEpsex) {
		this.spEpsex = spEpsex;
	}
}
