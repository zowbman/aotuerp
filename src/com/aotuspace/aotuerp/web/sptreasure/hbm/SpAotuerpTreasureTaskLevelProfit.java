package com.aotuspace.aotuerp.web.sptreasure.hbm;

/**
 * 
 * Title:SpAotuerpTreasureTaskLevelProfit
 * Description:任务奖励利润
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-11-24 下午3:05:52
 *
 */
public class SpAotuerpTreasureTaskLevelProfit {

	// Fields    

	private Integer spId;
	private SpAotuerpTreasureTaskLevel spAotuerpTreasureTaskLevel;
	private long spProfitpercentage;
	private long spProfityuan;

	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public SpAotuerpTreasureTaskLevel getSpAotuerpTreasureTaskLevel() {
		return spAotuerpTreasureTaskLevel;
	}

	public void setSpAotuerpTreasureTaskLevel(SpAotuerpTreasureTaskLevel spAotuerpTreasureTaskLevel) {
		this.spAotuerpTreasureTaskLevel = spAotuerpTreasureTaskLevel;
	}

	public long getSpProfitpercentage() {
		return spProfitpercentage;
	}

	public void setSpProfitpercentage(long spProfitpercentage) {
		this.spProfitpercentage = spProfitpercentage;
	}

	public long getSpProfityuan() {
		return spProfityuan;
	}

	public void setSpProfityuan(long spProfityuan) {
		this.spProfityuan = spProfityuan;
	}

}