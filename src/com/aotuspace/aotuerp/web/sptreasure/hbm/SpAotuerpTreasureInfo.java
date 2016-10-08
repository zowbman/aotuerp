package com.aotuspace.aotuerp.web.sptreasure.hbm;

import java.util.HashSet;
import java.util.Set;

import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductBinfo;

/**
 * 
 * Title:SpAotuerpTreasureInfo
 * Description:宝贝信息
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-11-18 下午6:13:53
 *
 */

public class SpAotuerpTreasureInfo {

	// Fields    

	private Integer spId;
	private SpAotuerpTreasureStatus spAotuerpTreasureStatus;//状态
	private String spTreasuretitle;//标题
	private String spTreasuresellingpoints;//卖点
	private String spTreasuredescription;//描述，富文本
	private Set<SpAotuerpTreasureProductSku> spAotuerpTreasureProductSkus = new HashSet<SpAotuerpTreasureProductSku>();//宝贝sku
	private Set<SpAotuerpTreasureImg> spAotuerpTreasureImgs = new HashSet<SpAotuerpTreasureImg>();//图片
	private SpProductBinfo spProductBinfo;//商品

	public Integer getSpId() {
		return this.spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public SpAotuerpTreasureStatus getSpAotuerpTreasureStatus() {
		return this.spAotuerpTreasureStatus;
	}

	public void setSpAotuerpTreasureStatus(SpAotuerpTreasureStatus spAotuerpTreasureStatus) {
		this.spAotuerpTreasureStatus = spAotuerpTreasureStatus;
	}

	public String getSpTreasuretitle() {
		return spTreasuretitle;
	}

	public void setSpTreasuretitle(String spTreasuretitle) {
		this.spTreasuretitle = spTreasuretitle;
	}

	public String getSpTreasuresellingpoints() {
		return spTreasuresellingpoints;
	}

	public void setSpTreasuresellingpoints(String spTreasuresellingpoints) {
		this.spTreasuresellingpoints = spTreasuresellingpoints;
	}

	public String getSpTreasuredescription() {
		return spTreasuredescription;
	}

	public void setSpTreasuredescription(String spTreasuredescription) {
		this.spTreasuredescription = spTreasuredescription;
	}

	public Set<SpAotuerpTreasureProductSku> getSpAotuerpTreasureProductSkus() {
		return spAotuerpTreasureProductSkus;
	}

	public void setSpAotuerpTreasureProductSkus(Set<SpAotuerpTreasureProductSku> spAotuerpTreasureProductSkus) {
		this.spAotuerpTreasureProductSkus = spAotuerpTreasureProductSkus;
	}

	public Set<SpAotuerpTreasureImg> getSpAotuerpTreasureImgs() {
		return spAotuerpTreasureImgs;
	}

	public void setSpAotuerpTreasureImgs(Set<SpAotuerpTreasureImg> spAotuerpTreasureImgs) {
		this.spAotuerpTreasureImgs = spAotuerpTreasureImgs;
	}

	public SpProductBinfo getSpProductBinfo() {
		return spProductBinfo;
	}

	public void setSpProductBinfo(SpProductBinfo spProductBinfo) {
		this.spProductBinfo = spProductBinfo;
	}

}