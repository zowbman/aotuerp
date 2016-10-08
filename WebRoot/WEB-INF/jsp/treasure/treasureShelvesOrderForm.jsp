<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://cksource.com/ckfinder" prefix="ckf"%>
<%@ taglib uri="http://ckeditor.com" prefix="ck"%>
<link rel="stylesheet" type="text/css" href="css/treasure/aotuerp.aotutreasure.form.css">
<input id="treasureSkuId" type="hidden">
<input id="spProductBinfoId" value="${spProductId }" type="hidden">
<a id="dd" href="javascript:void(0)">Click here</a>
<!-- 类别/品牌 -->
<div id="sp_treasure_category_brands">
	<span class="title">类别/品牌：</span>
	<div class="skin">${categoryBrands}</div>
</div>
<!-- sp_spu_pro结束 -->

<!-- 宝贝标题 -->
<div class="sp_treasure_title">
	<span>宝贝标题：</span> <input type="text" id="spTreasuretitle"
		class="textbox">
</div>
<!-- sp_treasure_title结束 -->

<!-- 宝贝卖点 -->
<div class="sp_treasure_selling_points">
	<span>宝贝卖点：</span> <input type="text" id="spTreasuresellingpoints"
		class="textbox">
</div>
<!-- "sp_treasure_selling_points"结束 -->

<!-- 基础属性 -->
<div id="sp_treasure_spu_pro">
	<span class="title">商品属性：</span>
	<div class="skin">
		<s:iterator value="spProductBpropertiesList">
			<div class="sp_treasure_spu_pro_title">
				<span><s:property
						value="spProductPropertyName.spPropertyname" />：</span> <input
					type="text" class="textbox disabled" readonly="readonly"
					value="<s:property value="spProductPropertyValue.spPropertyvalue"/>">
			</div>
		</s:iterator>
	</div>
	<!-- skin结束 -->
</div>
<!-- sp_treasure_spu_pro结束 -->

<!-- Treasuresku -->
<div id="sp_treasure_sku_pro">
	<span class="title">商品规格：</span>
	<div id="createTreasureTable"></div>
</div>
<!-- "createTreasureTable"结束 -->

<!-- 宝贝图片 -->
<div id="sp_treasure_img">
	<span class="title">宝贝图片：</span>
	<div id="multimage-tabs" style="height:100px;">
		<div title="本地上传" style="display:block;overflow:hidden;">
			<form id="imgForm">
				选择本地图片：<input type="file" id="spTreasureImg" name="spTreasureimg_pic.file">
			</form>
			<p>提示：</p>
				<p>本地上传图片大小不能超过3M。</p>
				<p>本类目下您最多可以上传 5 张图片。</p>
		</div>
		<div title="图片空间" style="display:block;overflow:hidden;"></div>
	</div>
	<div class="multimage-info">
		<div class="info-wrapper">
			<div class="msg">
				<span class="bright">700*700</span> 以上的图片可以在宝贝详情页主图提供图片放大功能
			</div>
			<div class="multimage-gallery">
				<ul>
					<li class="primary" data-index="1">
					<input name="image_pos" type="hidden" value="1">
						<div class="preview">
							<input type="hidden" class="hideimageurl" name="picUrl1">
						</div>
						<div class="info">
							<span class="bright">*</span> 主图
						</div>
					</li>
					<li class="" data-index="2"><input name="image_pos"
						type="hidden" value="2">
						<div class="preview">
							<input type="hidden" class="hideimageurl" name="picUrl2">
						</div>
					</li>
					<li class="" data-index="3"><input name="image_pos"
						type="hidden" value="3">
						<div class="preview">
							<input type="hidden" class="hideimageurl" name="picUrl3">
						</div>
					</li>
					<li class="" data-index="4"><input name="image_pos"
						type="hidden" value="4">
						<div class="preview">
							<input type="hidden" class="hideimageurl" name="picUrl4">
						</div></li>
					<li class="" data-index="5"><input name="image_pos"
						type="hidden" value="5">
						<div class="preview">
							<input type="hidden" class="hideimageurl" name="picUrl5">
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<div id="err_nav_multiPicError" style="display:none;">
		<p>对不起，你必须上传至少一张主图片</p>
	</div>

</div>
<!-- sp_treasure_img结束 -->

<!-- 宝贝描述（富文本） -->
<div id="sp_treasure_description">
	<span class="title">宝贝描述：</span> <input id="treasure_description_ck"
		type="text">
	<ckf:setupCKEditor basePath="/aotuerp/ckfinder/"
		editor="treasure_description_ck" />
	<ck:replace replace="treasure_description_ck"
		basePath="${path}/aotuerp/ckeditor"></ck:replace>
</div>
<!-- sp_spu_brand结束 -->
<!-- 底部按钮 -->
<div id="sp_treasure_submit">
	<input type="button" id="treasureBtn" value="提交上架订单">
</div>
