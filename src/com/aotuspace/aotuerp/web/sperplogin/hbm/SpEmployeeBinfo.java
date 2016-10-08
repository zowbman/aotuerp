package com.aotuspace.aotuerp.web.sperplogin.hbm;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * Title:EmployeeBinfo 
 * Description:员工基本信息表
 * Company:aotuspace
 * 
 * @author sida
 * @date 2015-9-2 下午5:32:53
 * 
 */

@SuppressWarnings("unchecked")
public class SpEmployeeBinfo  implements Serializable {
	
	//复合主键
	private SpEmployeeBinfoKey spEmployeeBinfoKey;
	
	// 员工帐号
	private String spEpaccount;

	// 员工密码
	private String spEppassword;

	// 员工部门
	
	public SpEmployeeDepart spEmployeeDepart;

	/*private Set<SpEmployeeStation> spEmployeeStations = new HashSet<SpEmployeeStation>();*/
	
	private Set<SpEmployeeStation> spEmployeeStations = new TreeSet<SpEmployeeStation>();

	private SpEmployeeDinfo spEmployeeDinfo;
	
	/**
	 * 判断本用户是否有指定名称的权限
	 * @return
	 */
	public boolean hasPrivilegeByName(String name){
		//超级管理员有所有的权限
		if(isAdmin()){
			return true;
		}
		
		//普通用户判断是否含有这个权限
		for (SpEmployeeStation spEmployeeStation : spEmployeeStations) {
			for (SpEmployeePrivilege priv : spEmployeeStation.getSpEmployeePrivileges()) {
				if(priv.getSpEpname().equals(name)){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 判断本用户是否还有指定URL的权限
	 * @return
	 */
	public boolean hasPrivilegeByUrl(String privUrl){
		//超级管理员有所有权限
		if(isAdmin()){
			return true;
		}
		
		// >>去掉后面的参数
		int pos = privUrl.indexOf("?");
		if(pos > -1){
			privUrl = privUrl.substring(0,pos);
		}
		
		// >>去掉UI后缀
		if(privUrl.endsWith("Submit")){
			privUrl = privUrl.substring(0, privUrl.length() - 6);
		}
		
		// 如果本URL不需要控制，则登陆用户就可以使用
		Collection<String> allPrivilegeUrls = (Collection<String>) ActionContext.getContext().getApplication()
				.get("allPrivilegeUrls");
		if (!allPrivilegeUrls.contains(privUrl)) {
			return true;
		}else{
			//普通用户判断是否含有这个权限
			for (SpEmployeeStation spEmployeeStation : spEmployeeStations) {
				for(SpEmployeePrivilege  priv : spEmployeeStation.getSpEmployeePrivileges()){
					if(privUrl.equals(priv.getSpEpurl())){
						return true;
					}
				}
			}
			return false;
		}
	}

	
	/**
	 * 判断本用户是不是超级管理员
	 * @return
	 */
	public boolean isAdmin(){
		return "admin".equals(spEpaccount);
	}
	
	public SpEmployeeBinfoKey getSpEmployeeBinfoKey() {
		return spEmployeeBinfoKey;
	}

	public void setSpEmployeeBinfoKey(SpEmployeeBinfoKey spEmployeeBinfoKey) {
		this.spEmployeeBinfoKey = spEmployeeBinfoKey;
	}

	public String getSpEpaccount() {
		return spEpaccount;
	}

	public void setSpEpaccount(String spEpaccount) {
		this.spEpaccount = spEpaccount;
	}

	public String getSpEppassword() {
		return spEppassword;
	}

	public void setSpEppassword(String spEppassword) {
		this.spEppassword = spEppassword;
	}

	public SpEmployeeDepart getSpEmployeeDepart() {
		return spEmployeeDepart;
	}

	public void setSpEmployeeDepart(SpEmployeeDepart spEmployeeDepart) {
		this.spEmployeeDepart = spEmployeeDepart;
	}

	public Set<SpEmployeeStation> getSpEmployeeStations() {
		return spEmployeeStations;
	}

	public void setSpEmployeeStations(Set<SpEmployeeStation> spEmployeeStations) {
		this.spEmployeeStations = spEmployeeStations;
	}

	public SpEmployeeDinfo getSpEmployeeDinfo() {
		return spEmployeeDinfo;
	}

	public void setSpEmployeeDinfo(SpEmployeeDinfo spEmployeeDinfo) {
		this.spEmployeeDinfo = spEmployeeDinfo;
	}
}
