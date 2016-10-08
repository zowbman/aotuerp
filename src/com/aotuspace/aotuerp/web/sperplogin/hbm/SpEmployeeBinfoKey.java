package com.aotuspace.aotuerp.web.sperplogin.hbm;

import java.io.Serializable;

/**
 * 
 * Title:SpEmployeeBinfoKey
 * Description:SpEmplyeeBinfoKey复合主键
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-9-24 下午6:04:46
 *
 */
public class SpEmployeeBinfoKey implements Serializable {
	
	// id
	private Integer spId;
	// 员工id
	private Integer spEpid;

	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public Integer getSpEpid() {
		return spEpid;
	}

	public void setSpEpid(Integer spEpid) {
		this.spEpid = spEpid;
	}
}
