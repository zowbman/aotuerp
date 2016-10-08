package com.aotuspace.aotuerp.web.sptreasure.view.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aotuspace.aotuerp.web.base.BaseAction;
import com.aotuspace.aotuerp.web.model.ImgSpec;
import com.aotuspace.aotuerp.web.model.PageBean;
import com.aotuspace.aotuerp.web.model.UpLoad;
import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductBinfo;
import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductBproperties;
import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductCategory;
import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductSku;
import com.aotuspace.aotuerp.web.sptreasure.hbm.SpAotuerpTreasureImg;
import com.aotuspace.aotuerp.web.sptreasure.hbm.SpAotuerpTreasureInfo;
import com.aotuspace.aotuerp.web.sptreasure.hbm.SpAotuerpTreasureProductSku;
import com.aotuspace.aotuerp.web.sptreasure.hbm.SpAotuerpTreasureStatus;
import com.aotuspace.aotuerp.web.util.sort.ISortSpProductBproperties;
import com.aotuspace.aotuerp.web.util.sort.complarator.SortSpProductBpropertiesImp;
import com.aotuspace.aotuerp.web.util.sort.complarator.SpProductBpropertiesComparator;
import com.aotuspace.aotuerp.web.util.sort.rule.SpProductBpropertiesOrder;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * Title:CommodityAction
 * Description:宝贝管理模块
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-10-21 下午3:22:22
 *
 */
@Controller
@Scope("prototype")
@SuppressWarnings("unchecked")
public class TreasureAction extends BaseAction<SpAotuerpTreasureInfo> {

	private SpAotuerpTreasureInfo spAotuerpTreasureInfo;

	//skuid
	private Integer[] spProductSkuIds;

	//商品id
	private Integer spProductId;

	//宝贝ids
	private Integer[] spAotuerpTreasureIds;

	//宝贝审核
	private boolean treasureOrderCheck;

	private UpLoad spTreasureimg_pic;

	//宝贝上架订单
	public String treasureShelvesOrder() throws Exception {
		return "treasureShelvesOrder";
	}

	//宝贝订单上架表单
	public String treasureShelvesOrderForm() throws Exception {
		SpProductBinfo spProductBinfo = iCommodityService.getById(spProductId);
		//获取
		ActionContext.getContext().put(
				"categoryBrands",
				getParentName(spProductBinfo.getSpProductBrands().getSpProductCategory())
						+ spProductBinfo.getSpProductBrands().getSpBrandn());
		List<SpProductBproperties> spProductBpropertiesList = new ArrayList<SpProductBproperties>();
		for (SpProductBproperties spProductBproperties : spProductBinfo.getSpProductBproperties()) {
			if (spProductBproperties.getSpIssku() == 0) {
				spProductBpropertiesList.add(spProductBproperties);
			}
		}
		ActionContext.getContext().put("spProductBpropertiesList", spProductBpropertiesList);
		ActionContext.getContext().put("spProductId", spProductBinfo.getSpId());
		return "treasureShelvesOrderForm";
	}

	//导入sku商品
	public String importPdSkuData() throws Exception {
		List<SpProductSku> spProductSkus = iCommodityService.getSpProductSkuByIds(spProductSkuIds);
		//封装json
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpProductSku spProductSku : spProductSkus) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("spSkuid", spProductSku.getSpSkuid());//skuid
			rowMap.put("spSkuproperties", spProductSku.getSpSkuproperties());//sku信息值
			rowMap.put("spSkupropertiesname", spProductSku.getSpSkupropertiesname());//sku信息名
			rowMap.put("spPdprice", spProductSku.getSpPdprice());//价格
			rowMap.put("spPdcount", spProductSku.getSpPdcount());//数量

			//排序开始（无问题）
			//把set转换list
			List<SpProductBproperties> spProductBpropertiesList = new ArrayList(
					spProductSku.getSpProductBpropertieses());
			//新建排序规则
			List<SpProductBpropertiesOrder> orders = new ArrayList<SpProductBpropertiesOrder>();
			//设置排序规则
			SpProductBpropertiesOrder spProductBpropertiesOrder = new SpProductBpropertiesOrder(
					"spProductPropertyName", true, SpProductBpropertiesOrder._Integer);
			//添加排序规则
			orders.add(spProductBpropertiesOrder);
			//添加排序规则
			//spProductBpropertiesOrder =new SpProductBpropertiesOrder("spProductPropertyName", true, SpProductBpropertiesOrder._STRING);
			//orders.add(spProductBpropertiesOrder);
			//调接口传入规则
			ISortSpProductBproperties iSortSpProductBproperties = new SortSpProductBpropertiesImp(orders);
			SpProductBpropertiesComparator comparator = new SpProductBpropertiesComparator(iSortSpProductBproperties);
			Collections.sort(spProductBpropertiesList, comparator);

			if (spProductSku.getSpProductBinfo() != null) {
				//商品全部基础属性信息
				for (SpProductBproperties spProductBproperties : spProductSku.getSpProductBinfo()
						.getSpProductBproperties()) {
					System.out.println(spProductBproperties.getSpProductPropertyName());
					System.out.println(spProductBproperties.getSpProductPropertyValue());
				}
			}
			/*
			System.out.println("排序前------");
			for (SpProductBproperties spProductBproperties : spProductSku.getSpProductBpropertieses()) {
				System.out.println("sku信息--->"+spProductBproperties.getSpProductPropertyName().getSpPropertyname());
			}
			
			System.out.println("排序后------");
			for (SpProductBproperties spProductBproperties : spProductBpropertiesList) {
				System.out.println("sku信息--->"+spProductBproperties.getSpProductPropertyName().getSpPropertyname());
			}*/
			//排序结束

			rowMap.put("spProductBpropertieses", spProductBpropertiesList);//sku基础信息

			rowMap.put("spProductBinfo", spProductSku.getSpProductBinfo());//商品信息

			listMaps.add(rowMap);

		}
		objectMapper.registerModule(new Hibernate4Module());
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
		jsonResult.setData(listMaps);
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	private List<ImgSpec> specs = new ArrayList<ImgSpec>();

	public List<ImgSpec> getSpecs() {
		return specs;
	}

	public void setSpecs(List<ImgSpec> specs) {
		this.specs = specs;
	}

	/**
	 * 提交宝贝订单图片
	 * 每次提交一张
	 * @return
	 * data:
	 * @throws Exception
	 */
	public String orderPicUpload() throws Exception {
		//设置上传图片物理路径
		String orderPicPath = "E:\\TOMCAT虚拟目录\\";
		//上传图片
		List<ImgSpec> imgSpecs = upload(spTreasureimg_pic, orderPicPath);
		//只有一张
		//多张有异常
		if (imgSpecs != null && imgSpecs.size() == 1) {
			ImgSpec imgSpec = imgSpecs.get(0);
			SpAotuerpTreasureImg treasureImg = new SpAotuerpTreasureImg();
			treasureImg.setSpImg(imgSpec.getImgPath());//原图片
			treasureImg.setSpImgsize(imgSpec.getImgSize());//大小
			treasureImg.setSpImgwidth(imgSpec.getImgWidth());//宽
			treasureImg.setSpImgheight(imgSpec.getImgHeight());//高
			//创建缩略图
			if (specs != null && specs.size() > 0) {
				List<ImgSpec> thumbnailSpecs = createThumbnailImg(specs, imgSpec.getImgPath(), orderPicPath);
				for (ImgSpec thumbnailSpec : thumbnailSpecs) {
					SpAotuerpTreasureImg thumbnail = new SpAotuerpTreasureImg();
					thumbnail.setSpImg(thumbnailSpec.getImgPath());//缩略图
					thumbnail.setSpImgsize(thumbnailSpec.getImgSize());//大小
					thumbnail.setSpImgwidth(thumbnailSpec.getImgWidth());//宽
					thumbnail.setSpImgheight(thumbnailSpec.getImgHeight());//高
					treasureImg.getThumbnails().add(thumbnail);
				}
				iSpAotuerpTreasureService.saveSpAotuerpTreasureImg(treasureImg);
				imgSpec.setImgId(treasureImg.getSpId());//图片id
				imgSpec.setPostfix(".jpg");//缩略图后缀
				jsonResult.setCode(0);
				jsonResult.setMsg("请求成功");
				jsonResult.setData(imgSpec);
			}
		} else {//多个文件，请求失败
			jsonResult.setCode(10001);
			jsonResult.setMsg("请求失败");
		}
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	//宝贝图片id
	private Integer[] imgIds;
	//主图
	private Integer imgPrimaryId;

	/**
	 * 宝贝提交订单
	 * 状态默认未审核
	 * @return
	 * @throws Exception
	 */
	public String orderSubmit() throws Exception {
		//查询这个商品
		SpProductBinfo spProductBinfo = iCommodityService.getById(spProductId);
		if (spProductBinfo != null) {
			for (SpAotuerpTreasureProductSku spAotuerpTreasureProductSku : spAotuerpTreasureInfo
					.getSpAotuerpTreasureProductSkus()) {
				//相应的sku数量会减少
				for (SpProductSku spProductSku : spProductBinfo.getSpProductSkus()) {
					if (spProductSku.getSpSkuid().equals(spAotuerpTreasureProductSku.getSpProductSku().getSpSkuid())) {
						spProductSku.setSpPdcount(spProductSku.getSpPdcount()
								- spAotuerpTreasureProductSku.getSpSalesquantity());
					}
				}
				//总价
				spAotuerpTreasureProductSku.setSpTotalprice(spAotuerpTreasureProductSku.getSpSalesprice()
						* spAotuerpTreasureProductSku.getSpSalesquantity());
				//订单数量
				spAotuerpTreasureProductSku.setSpSalesorderquantity(spAotuerpTreasureProductSku.getSpSalesquantity());
			}
			//图片
			//持久化图片对象
			List<SpAotuerpTreasureImg> treausreImgs=iSpAotuerpTreasureImgService.getByIds(imgIds);
			Integer i_o=1;//顺序
			//设置为主图
			for (SpAotuerpTreasureImg treasureImg : treausreImgs) {
				if(imgPrimaryId!=null&&imgPrimaryId.equals(treasureImg.getSpId())){
					treasureImg.setSpIsprimary(1);
				}
				treasureImg.setSpImgorder(i_o++);
			}
			spAotuerpTreasureInfo.setSpAotuerpTreasureImgs(new HashSet<SpAotuerpTreasureImg>(treausreImgs));
			spAotuerpTreasureInfo.setSpProductBinfo(spProductBinfo);
			iSpAotuerpTreasureService.save(spAotuerpTreasureInfo);
			//更新商品数量
			iCommodityService.update(spProductBinfo);
			jsonResult.setCode(0);
			jsonResult.setMsg("请求成功");
		} else {
			jsonResult.setCode(10001);
			jsonResult.setMsg("请求失败");
		}
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	//上架宝贝查询
	public String treasureShelvesOrderList() throws Exception {
		return "treasureShelvesOrderList";

	}

	public String treasureShelvesOrderListData() throws Exception {
		PageBean<SpAotuerpTreasureInfo> spAotuerpTreasureInfoList = iSpAotuerpTreasureService
				.findSpAotuerpTreasureInfoList(rows, page);
		//封装json
		Map<String, Object> pageListMap = new HashMap<String, Object>();
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpAotuerpTreasureInfo spAotuerpTreasureInfo : spAotuerpTreasureInfoList.getRecordList()) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("sp_id", spAotuerpTreasureInfo.getSpId());//宝贝编号
			rowMap.put("sp_idShow", spAotuerpTreasureInfo.getSpId());//宝贝编号
			rowMap.put("sp_Treasuretitle", spAotuerpTreasureInfo.getSpTreasuretitle());//宝贝标题
			rowMap.put("sp_TreasureStatus", spAotuerpTreasureInfo.getSpAotuerpTreasureStatus().getSpTreasurestatus());//宝贝状态
			listMaps.add(rowMap);
		}
		//easyui total 总数  rows列表
		pageListMap.put("total", spAotuerpTreasureInfoList.getPageCount());//总条数
		pageListMap.put("rows", listMaps);//列表
		resp.getWriter().write(objectMapper.writeValueAsString(pageListMap));
		return NONE;
	}

	//宝贝审核
	public String treasureShelvesOrderCheck() throws Exception {
		List<SpAotuerpTreasureInfo> spAotuerpTreasureInfos = iSpAotuerpTreasureService.getByIds(spAotuerpTreasureIds);
		if (spAotuerpTreasureInfos != null && spAotuerpTreasureInfos.size() > 0) {
			for (SpAotuerpTreasureInfo spAotuerpTreasureInfo : spAotuerpTreasureInfos) {
				SpAotuerpTreasureStatus spAotuerpTreasureStatus = new SpAotuerpTreasureStatus();
				if (treasureOrderCheck) {
					spAotuerpTreasureStatus.setSpId(2);
					spAotuerpTreasureInfo.setSpAotuerpTreasureStatus(spAotuerpTreasureStatus);
					;//通过
				} else {
					spAotuerpTreasureStatus.setSpId(3);
					spAotuerpTreasureInfo.setSpAotuerpTreasureStatus(spAotuerpTreasureStatus);//失败
				}
				iSpAotuerpTreasureService.update(spAotuerpTreasureInfo);
			}
			jsonResult.setCode(0);
			jsonResult.setMsg("请求成功");
		} else {
			jsonResult.setCode(10001);
			jsonResult.setMsg("请求失败");
		}
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	/** 
	 * 递归得到当前节点的所有父节点 
	 * @param configId 当前节点 
	 * @return 所有父节点 
	 * @throws Exception 抛出的异常 
	 */
	public String getParentName(SpProductCategory spProductCategory) throws Exception {
		//和数据库交互,得到当前节点记录  
		if (spProductCategory != null) {
			String configName = spProductCategory.getSpCategoryn() + ">";
			//参数patrolConfigEntity.getConfigParentId()表示当前节点的父节点ID  
			String returnConfigName = getParentName(spProductCategory.getSpProductCategory());
			return returnConfigName + configName;
		} else {
			return "";
		}
	}

	public Integer[] getSpProductSkuIds() {
		return spProductSkuIds;
	}

	@JSON(serialize = true, deserialize = true)
	public void setSpProductSkuIds(Integer[] spProductSkuIds) {
		this.spProductSkuIds = spProductSkuIds;
	}

	public Integer getSpProductId() {
		return spProductId;
	}

	public void setSpProductId(Integer spProductId) {
		this.spProductId = spProductId;
	}

	public SpAotuerpTreasureInfo getSpAotuerpTreasureInfo() {
		return spAotuerpTreasureInfo;
	}

	@JSON(serialize = true, deserialize = true)
	public void setSpAotuerpTreasureInfo(SpAotuerpTreasureInfo spAotuerpTreasureInfo) {
		this.spAotuerpTreasureInfo = spAotuerpTreasureInfo;
	}

	public Integer[] getSpAotuerpTreasureIds() {
		return spAotuerpTreasureIds;
	}

	@JSON(serialize = true, deserialize = true)
	public void setSpAotuerpTreasureIds(Integer[] spAotuerpTreasureIds) {
		this.spAotuerpTreasureIds = spAotuerpTreasureIds;
	}

	public boolean isTreasureOrderCheck() {
		return treasureOrderCheck;
	}

	@JSON(serialize = true, deserialize = true)
	public void setTreasureOrderCheck(boolean treasureOrderCheck) {
		this.treasureOrderCheck = treasureOrderCheck;
	}

	public UpLoad getSpTreasureimg_pic() {
		return spTreasureimg_pic;
	}

	@JSON(serialize = true, deserialize = true)
	public void setSpTreasureimg_pic(UpLoad spTreasureimg_pic) {
		this.spTreasureimg_pic = spTreasureimg_pic;
	}

	public Integer[] getImgIds() {
		return imgIds;
	}

	public void setImgIds(Integer[] imgIds) {
		this.imgIds = imgIds;
	}

	public Integer getImgPrimaryId() {
		return imgPrimaryId;
	}

	public void setImgPrimaryId(Integer imgPrimaryId) {
		this.imgPrimaryId = imgPrimaryId;
	}

}
