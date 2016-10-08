package com.aotuspace.aotuerp.web.sppurchase.hbm;

/**
 * 
 * Title:SpAotuerpPurchaseOrdersStatus
 * Description:½ø»õ¶©µ¥×´Ì¬
 * Company:aotuspace
 * @author    Î°±¦
 * @date      2015-11-9 ÉÏÎç11:49:44
 *
 */

public class SpAotuerpPurchaseOrdersStatus {

	private Integer spId;//ÐòºÅ
	private String spStatusn;//ÐÕÃû

	//private Set spAotuerpPurchaseOrderses = new HashSet(0);
	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public String getSpStatusn() {
		return spStatusn;
	}

	public void setSpStatusn(String spStatusn) {
		this.spStatusn = spStatusn;
	}

}