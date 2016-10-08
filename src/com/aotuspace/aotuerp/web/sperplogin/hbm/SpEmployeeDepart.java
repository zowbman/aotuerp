package com.aotuspace.aotuerp.web.sperplogin.hbm;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * Title:EmployeeBinfo
 * Description:部门信息表
 * Company:aotuspace
 * @author    sida
 * @date      2015-9-2 下午5:32:53
 *
 */

public class SpEmployeeDepart implements Serializable {
	
	//id
	private Integer spId;
	
	public Set<SpEmployeeBinfo> spEmployeeBinfos = new TreeSet<SpEmployeeBinfo>();
	
	//部门名称
	private String spEpdepartn;

	private SpEmployeeDepart spEpdeparent; //上级部门
	
	private Set<SpEmployeeDepart> spEpdechildren = new TreeSet<SpEmployeeDepart>();//下级部门
	
	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public Set<SpEmployeeBinfo> getSpEmployeeBinfos() {
		return spEmployeeBinfos;
	}

	public void setSpEmployeeBinfos(Set<SpEmployeeBinfo> spEmployeeBinfos) {
		this.spEmployeeBinfos = spEmployeeBinfos;
	}

	public String getSpEpdepartn() {
		return spEpdepartn;
	}

	public void setSpEpdepartn(String spEpdepartn) {
		this.spEpdepartn = spEpdepartn;
	}

	public SpEmployeeDepart getSpEpdeparent() {
		return spEpdeparent;
	}

	public void setSpEpdeparent(SpEmployeeDepart spEpdeparent) {
		this.spEpdeparent = spEpdeparent;
	}

	public Set<SpEmployeeDepart> getSpEpdechildren() {
		return spEpdechildren;
	}

	public void setSpEpdechildren(Set<SpEmployeeDepart> spEpdechildren) {
		this.spEpdechildren = spEpdechildren;
	}
	
	
}
