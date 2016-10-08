package com.aotuspace.aotuerp.web.sperplogin.hbm;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * Title:SpEmployeePrivilege
 * Description:权限表
 * Company:aotuspace
 * @author    sida
 * @date      2015-9-14 下午2:37:59
 *
 */
public class SpEmployeePrivilege implements Serializable {
	
	private Integer spId;
	
	private String spEpname; //权限名称
	
	private String spEpurl;//权限地址
	
	private String spIconcls;//权限图标
	
	private String spState;//权限展开状态
	
	private Set<SpEmployeeStation> spEmployeeStations = new TreeSet<SpEmployeeStation>();
	
	private SpEmployeePrivilege spEpparent; //上级权限
	
	@JsonIgnore
	private Set<SpEmployeePrivilege> spEpchildren = new TreeSet<SpEmployeePrivilege>(); //下级权限

	public SpEmployeePrivilege(){
			
	}
	
	public SpEmployeePrivilege(String spEpname, String spEpurl, String spIconcls,String spState,
			SpEmployeePrivilege spEpparent) {
		super();
		this.spEpname = spEpname;//权限名称
		this.spEpurl = spEpurl;//权限地址
		this.spIconcls=spIconcls;//权限图标
		this.spState=spState;//权限展开状态
		this.spEpparent = spEpparent;//父权限
	}

	
	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public String getSpEpname() {
		return spEpname;
	}

	public void setSpEpname(String spEpname) {
		this.spEpname = spEpname;
	}

	public String getSpEpurl() {
		return spEpurl;
	}

	public void setSpEpurl(String spEpurl) {
		this.spEpurl = spEpurl;
	}

	public Set<SpEmployeeStation> getSpEmployeeStations() {
		return spEmployeeStations;
	}

	public void setSpEmployeeStations(Set<SpEmployeeStation> spEmployeeStations) {
		this.spEmployeeStations = spEmployeeStations;
	}

	public SpEmployeePrivilege getSpEpparent() {
		return spEpparent;
	}

	public void setSpEpparent(SpEmployeePrivilege spEpparent) {
		this.spEpparent = spEpparent;
	}

	public Set<SpEmployeePrivilege> getSpEpchildren() {
		return spEpchildren;
	}

	public void setSpEpchildren(Set<SpEmployeePrivilege> spEpchildren) {
		this.spEpchildren = spEpchildren;
	}

	public String getSpIconcls() {
		return spIconcls;
	}

	public void setSpIconcls(String spIconcls) {
		this.spIconcls = spIconcls;
	}

	public String getSpState() {
		return spState;
	}

	public void setSpState(String spState) {
		this.spState = spState;
	}
	
	
}
