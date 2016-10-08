package com.aotuspace.aotuerp.web.sptreasure.hbm;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * Title:SpAotuerpTreasureStatus
 * Description:±¦±´×´Ì¬
 * Company:aotuspace
 * @author    Î°±¦
 * @date      2015-11-18 ÏÂÎç6:22:55
 *
 */

public class SpAotuerpTreasureStatus {

	// Fields    

	private Integer spId;
	private String spTreasurestatus;
	private Set<SpAotuerpTreasureInfo> spAotuerpTreasureInfos = new HashSet<SpAotuerpTreasureInfo>();
	
	public SpAotuerpTreasureStatus() {
	}
	
	/**
	 * 
	 * @param spId´úÂë
	 */
	public SpAotuerpTreasureStatus(Integer spId) {
		this.spId = spId;
	}

	public Integer getSpId() {
		return this.spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public String getSpTreasurestatus() {
		return spTreasurestatus;
	}

	public void setSpTreasurestatus(String spTreasurestatus) {
		this.spTreasurestatus = spTreasurestatus;
	}

	public Set<SpAotuerpTreasureInfo> getSpAotuerpTreasureInfos() {
		return spAotuerpTreasureInfos;
	}

	public void setSpAotuerpTreasureInfos(Set<SpAotuerpTreasureInfo> spAotuerpTreasureInfos) {
		this.spAotuerpTreasureInfos = spAotuerpTreasureInfos;
	}

}