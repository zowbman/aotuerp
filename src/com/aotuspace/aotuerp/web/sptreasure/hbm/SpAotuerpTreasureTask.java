package com.aotuspace.aotuerp.web.sptreasure.hbm;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * Title:SpAotuerpTreasureTask
 * Description:±¦±´ÈÎÎñ
 * Company:aotuspace
 * @author    Î°±¦
 * @date      2015-11-24 ÏÂÎç3:01:04
 *
 */

public class SpAotuerpTreasureTask {

	// Fields    

	private Integer spId;
	private SpAotuerpTreasureTaskStatus spAotuerpTreasureTaskStatus;
	private SpAotuerpTreasureTaskMode spAotuerpTreasureTaskMode;
	//private SpAnchorBinfo spAnchorBinfo;
	private SpAotuerpTreasureInfo spAotuerpTreasureInfo;
	private Integer spTaskaging;
	private Date spTaskuploadtime;
	private Date spTaskunloadtime;
	private String spTaskremark;
	private Set<SpAotuerpTreasureTaskLevel> spAotuerpTreasureTaskLevels = new HashSet<SpAotuerpTreasureTaskLevel>();

	//private Set<SpAotuerpTreasureTaskLevelProfit> spAotuerpTreasureTaskLevelProfits = new HashSet(0);
	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public SpAotuerpTreasureTaskStatus getSpAotuerpTreasureTaskStatus() {
		return spAotuerpTreasureTaskStatus;
	}

	public void setSpAotuerpTreasureTaskStatus(SpAotuerpTreasureTaskStatus spAotuerpTreasureTaskStatus) {
		this.spAotuerpTreasureTaskStatus = spAotuerpTreasureTaskStatus;
	}

	public SpAotuerpTreasureInfo getSpAotuerpTreasureInfo() {
		return spAotuerpTreasureInfo;
	}

	public void setSpAotuerpTreasureInfo(SpAotuerpTreasureInfo spAotuerpTreasureInfo) {
		this.spAotuerpTreasureInfo = spAotuerpTreasureInfo;
	}

	public Date getSpTaskuploadtime() {
		return spTaskuploadtime;
	}

	public void setSpTaskuploadtime(Date spTaskuploadtime) {
		this.spTaskuploadtime = spTaskuploadtime;
	}

	public Date getSpTaskunloadtime() {
		return spTaskunloadtime;
	}

	public void setSpTaskunloadtime(Date spTaskunloadtime) {
		this.spTaskunloadtime = spTaskunloadtime;
	}

	public String getSpTaskremark() {
		return spTaskremark;
	}

	public void setSpTaskremark(String spTaskremark) {
		this.spTaskremark = spTaskremark;
	}

	public Set<SpAotuerpTreasureTaskLevel> getSpAotuerpTreasureTaskLevels() {
		return spAotuerpTreasureTaskLevels;
	}

	public void setSpAotuerpTreasureTaskLevels(Set<SpAotuerpTreasureTaskLevel> spAotuerpTreasureTaskLevels) {
		this.spAotuerpTreasureTaskLevels = spAotuerpTreasureTaskLevels;
	}

	public SpAotuerpTreasureTaskMode getSpAotuerpTreasureTaskMode() {
		return spAotuerpTreasureTaskMode;
	}

	public void setSpAotuerpTreasureTaskMode(SpAotuerpTreasureTaskMode spAotuerpTreasureTaskMode) {
		this.spAotuerpTreasureTaskMode = spAotuerpTreasureTaskMode;
	}

	public Integer getSpTaskaging() {
		return spTaskaging;
	}

	public void setSpTaskaging(Integer spTaskaging) {
		this.spTaskaging = spTaskaging;
	}

}