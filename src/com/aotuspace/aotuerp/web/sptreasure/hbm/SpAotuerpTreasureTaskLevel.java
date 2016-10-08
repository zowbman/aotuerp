package com.aotuspace.aotuerp.web.sptreasure.hbm;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * Title:SpAotuerpTreasureTaskLevel
 * Description:±¦±´ÈÎÎñ½±Àø¼¶
 * Company:aotuspace
 * @author    Î°±¦
 * @date      2015-11-24 ÏÂÎç3:02:31
 *
 */

public class SpAotuerpTreasureTaskLevel {

	// Fields    

	private Integer spId;
	private SpAotuerpTreasureTask spAotuerpTreasureTask;
	private String spRewardlevel;
	private Set<SpAotuerpTreasureTaskLevelProfit> spAotuerpTreasureTaskLevelProfits = new HashSet<SpAotuerpTreasureTaskLevelProfit>();

	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public SpAotuerpTreasureTask getSpAotuerpTreasureTask() {
		return spAotuerpTreasureTask;
	}

	public void setSpAotuerpTreasureTask(SpAotuerpTreasureTask spAotuerpTreasureTask) {
		this.spAotuerpTreasureTask = spAotuerpTreasureTask;
	}


	public String getSpRewardlevel() {
		return spRewardlevel;
	}

	public void setSpRewardlevel(String spRewardlevel) {
		this.spRewardlevel = spRewardlevel;
	}

	public Set<SpAotuerpTreasureTaskLevelProfit> getSpAotuerpTreasureTaskLevelProfits() {
		return spAotuerpTreasureTaskLevelProfits;
	}

	public void setSpAotuerpTreasureTaskLevelProfits(
			Set<SpAotuerpTreasureTaskLevelProfit> spAotuerpTreasureTaskLevelProfits) {
		this.spAotuerpTreasureTaskLevelProfits = spAotuerpTreasureTaskLevelProfits;
	}
	
	

}