package com.aotuspace.aotuerp.web.model;

/**
 * ËõÂÔÍ¼
 * Title:ThumbnailImgSpec
 * Description:
 * Company:aotuspace
 * @author    Î°±¦
 * @date      2015-12-10 ÉÏÎç11:35:27
 *
 */
public class ImgSpec {

	private Integer imgId;//Í¼Æ¬id

	private String imgPath;//Í¼Æ¬Â·¾¶

	private Integer imgWidth;//Í¼Æ¬¿í

	private Integer imgHeight;//Í¼Æ¬¸ß

	private Long imgSize;

	private String postfix;//ºó×º

	public Integer getImgId() {
		return imgId;
	}

	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}

	public String getPostfix() {
		return postfix;
	}

	public void setPostfix(String postfix) {
		this.postfix = postfix;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Long getImgSize() {
		return imgSize;
	}

	public void setImgSize(Long imgSize) {
		this.imgSize = imgSize;
	}

	public Integer getImgWidth() {
		return imgWidth;
	}

	public void setImgWidth(Integer imgWidth) {
		this.imgWidth = imgWidth;
	}

	public Integer getImgHeight() {
		return imgHeight;
	}

	public void setImgHeight(Integer imgHeight) {
		this.imgHeight = imgHeight;
	}
}
