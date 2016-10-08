package com.aotuspace.aotuerp.web.spcommodity.view.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aotuspace.aotuerp.web.base.BaseAction;
import com.aotuspace.aotuerp.web.model.PageBean;
import com.aotuspace.aotuerp.web.model.ProductPro;
import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductBinfo;
import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductBproperties;
import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductBrands;
import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductCategory;
import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductPropertyName;
import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductPropertyValue;
import com.aotuspace.aotuerp.web.spcommodity.hbm.SpProductSku;
import com.aotuspace.aotuerp.web.spcommodity.model.Commodity;
import com.aotuspace.aotuerp.web.spcommodity.model.sp_sku;
import com.aotuspace.aotuerp.web.util.CustomUtil;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

/**
 * 
 * Title:CommodityAction
 * Description:商品管理模块
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-10-21 下午3:22:22
 *
 */
@Controller
@Scope("prototype")
public class CommodityAction extends BaseAction<SpProductBinfo> {

	public Integer topCategoryId;//顶级类目ID

	public Integer secondCategoryId;//二级类目ID

	public Integer thirdCategoryId;//三级类目ID（表明为品牌，实际是根据类别选择相应的属性）
	
	public Commodity commodity;
	
	public Integer[] commodityIds;//商品ids
	

	//商品信息页面
	public String list() throws Exception {
		return "list";
	}

	//商品信息listData数据
	public String listData() throws Exception {
		PageBean<SpProductBinfo> spProductBinfoList = iCommodityService.findSpProductBinfoList(rows, page);
		//封装json
		Map<String, Object> pageListMap = new HashMap<String, Object>();
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpProductBinfo spProductBinfo : spProductBinfoList.getRecordList()) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("sp_id", spProductBinfo.getSpId());
			rowMap.put("sp_idShow", spProductBinfo.getSpId());
			rowMap.put("sp_Pdspu", spProductBinfo.getSpPdspu());//商品名称
			rowMap.put("sp_Brandn", spProductBinfo.getSpProductBrands().getSpBrandn());//品牌
			rowMap.put("sp_Categoryn", spProductBinfo.getSpProductBrands().getSpProductCategory()
					.getSpCategoryn());//类别
			rowMap.put("sp_Pdcredate", spProductBinfo.getSpPdcredate());//创建日期
			listMaps.add(rowMap);
		}
		/*PageBean<SpProductSku> spProductSkuList = iCommodityService.findSpProductSkuList(rows, page);
		//封装json
		Map<String, Object> pageListMap = new HashMap<String, Object>();
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpProductSku spProductSku : spProductSkuList.getRecordList()) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("sp_Skuid", spProductSku.getSpSkuid());
			rowMap.put("sp_SkuidShow", spProductSku.getSpSkuid());
			rowMap.put("sp_Pdspu", spProductSku.getSpProductBinfo().getSpPdspu());//商品名称
			rowMap.put("sp_Brandn", spProductSku.getSpProductBinfo().getSpProductBrands().getSpBrandn());//品牌
			rowMap.put("sp_Categoryn", spProductSku.getSpProductBinfo().getSpProductBrands().getSpProductCategory()
					.getSpCategoryn());//类别
			rowMap.put("sp_Pdcredate", spProductSku.getSpPdcredate());//创建日期
			rowMap.put("sp_Statusn", spProductSku.getSpProductStatus().getSpStatusn());//商品状态
			listMaps.add(rowMap);
		}*/
		//easyui total 总数  rows列表
		pageListMap.put("total", spProductBinfoList.getPageCount());//总条数
		pageListMap.put("rows", listMaps);//列表
		resp.getWriter().write(objectMapper.writeValueAsString(pageListMap));
		return NONE;
	}
	
	//根据商品id查询sku信息
	public String skuListData()throws Exception{
		SpProductBinfo spProductBinfo = iCommodityService.getById(model.getSpId());
		//封装json
		Map<String, Object> pageListMap = new HashMap<String, Object>();
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpProductSku spProductSku : spProductBinfo.getSpProductSkus()) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("sp_Skuid", spProductSku.getSpSkuid());
			rowMap.put("sp_SkuidShow", spProductSku.getSpSkuid());
			rowMap.put("sp_Pdspu", spProductSku.getSpProductBinfo().getSpPdspu());//商品名称
			rowMap.put("sp_Pdproname", spProductSku.getSpSkupropertiesname());
			rowMap.put("sp_Brandn", spProductSku.getSpProductBinfo().getSpProductBrands().getSpBrandn());//品牌
			rowMap.put("sp_Categoryn", spProductSku.getSpProductBinfo().getSpProductBrands().getSpProductCategory()
					.getSpCategoryn());//类别
			rowMap.put("sp_Quantity", spProductSku.getSpPdcount());//数量
			listMaps.add(rowMap);
		}
		pageListMap.put("total", spProductBinfo.getSpProductSkus().size());//总条数
		pageListMap.put("rows", listMaps);//列表
		resp.getWriter().write(objectMapper.writeValueAsString(pageListMap));
		return NONE;
	}
	
	

	//顶级类目
	public String categoryTopData() throws Exception {
		List<SpProductCategory> spProductCategories = iCommodityCategoryService.findCategoryTopList();
		objectMapper.registerModule(new Hibernate4Module());//父子关系无法序列化
		objectMapper.setSerializationInclusion(Include.NON_EMPTY);//不为空
		resp.getWriter().write(objectMapper.writeValueAsString(spProductCategories));
		return NONE;
	}

	//二级类目
	public String categorySecondData() throws Exception {
		List<SpProductCategory> spProductCategories = iCommodityCategoryService.findCategoryByTopList(topCategoryId);
		objectMapper.registerModule(new Hibernate4Module());//父子关系无法序列化
		objectMapper.setSerializationInclusion(Include.NON_EMPTY);//不为空
		resp.getWriter().write(objectMapper.writeValueAsString(spProductCategories));
		return NONE;
	}

	//品牌查询
	public String brandsData() throws Exception {
		List<SpProductBrands> spProductBrands = iCommodityBrandsService.findBrandsByCategoryId(secondCategoryId);
		objectMapper.registerModule(new Hibernate4Module());//父子关系无法序列化
		objectMapper.setSerializationInclusion(Include.NON_EMPTY);//不为空
		resp.getWriter().write(objectMapper.writeValueAsString(spProductBrands));
		return NONE;
	}

	//属性查询
	public String proData() throws Exception {
		//根据品牌查询属性名关联查询属性值，拼接json
		List<SpProductPropertyName> spProductPropertyNames = iCommodityPropertiesService
				.findPropertiesByCategoryId(thirdCategoryId);
		//拼接json
		List<ProductPro> spProductSkuPro =new ArrayList<ProductPro>();//sku
		List<ProductPro> spProductSpuPro =new ArrayList<ProductPro>();//spu
		for (SpProductPropertyName spProductPropertyName : spProductPropertyNames) {
			String sp_InputType = "";
			 if(spProductPropertyName.getSpIssalepro()==0&&spProductPropertyName.getSpIskeypro()==0){//基础属性
				if (spProductPropertyName.getSpIsenumpro() == 1)
					sp_InputType = "下拉框";
				else if (spProductPropertyName.getSpIsimportpro() == 1)
					sp_InputType = "输入框";
				else if (spProductPropertyName.getSpIsmultiselectpro() == 1)
					sp_InputType = "复选框";
				ProductPro productPro = new ProductPro(spProductPropertyName.getSpPropertyname(),
						spProductPropertyName.getSpAlias(), sp_InputType,
						defaultValue(spProductPropertyName.getSpProductPropertyValues()),
						spProductPropertyName.getSpIsrequirepro(),"sp_spu_pro_tbodyAppend");
				spProductSpuPro.add(productPro);
			}else if (spProductPropertyName.getSpIssalepro() == 1) {//销售属性
				if (spProductPropertyName.getSpIsenumpro() == 1)
					sp_InputType = "下拉框";
				else if (spProductPropertyName.getSpIsimportpro() == 1)
					sp_InputType = "输入框";
				else if (spProductPropertyName.getSpIsmultiselectpro() == 1)
					sp_InputType = "复选框";
				ProductPro productPro = new ProductPro(spProductPropertyName.getSpPropertyname(),
						spProductPropertyName.getSpAlias(), sp_InputType,
						defaultValue(spProductPropertyName.getSpProductPropertyValues()),
						spProductPropertyName.getSpIsrequirepro(),"sp_sku_pro_tbodyAppend");
				spProductSkuPro.add(productPro);
			}
		};
		//objectMapper.setSerializationInclusion(Include.NON_EMPTY);//不为空字符串、空数组 vo类才有用
		resp.getWriter().write("["+objectMapper.writeValueAsString(spProductSpuPro)
				+","+objectMapper.writeValueAsString(spProductSkuPro)+"]");
		return NONE;
	}
	
	//添加商品
	public String add() throws Exception{
		//保存商品
		SpProductBinfo spProductBinfo=new SpProductBinfo();
		//商品名称
		spProductBinfo.setSpPdspu(commodity.getSp_spu_title());
		//商品品牌
		spProductBinfo.setSpProductBrands(new SpProductBrands(Integer.valueOf(commodity.getSp_spu_brand())));
		//商品标题
		spProductBinfo.setSpPdtitle("helloo");
		//商品二级标题
		spProductBinfo.setSpPdfeature("hello");
		//商品图片//默认
		
		Set<SpProductSku> spProductSkus = new HashSet<SpProductSku>();
		Set<SpProductBproperties> spProductBproperties=new HashSet<SpProductBproperties>();
		//sku
		for (sp_sku sku : commodity.getSp_sku()) {
			//保存sku
			SpProductSku spProductSku=new SpProductSku();
			spProductSku.setSpPdprice(Long.valueOf(sku.getSp_PdPrice()));//价格
			spProductSku.setSpPdcount(Integer.valueOf(sku.getSp_PdCount()));//数量
			//sku值以‘;’隔开
			spProductSku.setSpSkuproperties(skuHandler(sku.getSp_sku()));//sku
			spProductSku.setSpSkupropertiesname(sku.getSp_sku_name());//sku名称
			spProductSkus.add(spProductSku);
			//把sku存入基础属性
			for (String skuString : sku.getSp_sku()) {
				SpProductBproperties spProductBproperties2=new SpProductBproperties();//spu
				SpProductPropertyName spProductPropertyName=iCommodityPropertiesService.getById(Integer.valueOf(spuHandler(skuString)[0]));
				spProductBproperties2.setSpProductPropertyName(spProductPropertyName);//spu_name
				for (SpProductPropertyValue spProductPropertyValue : spProductPropertyName.getSpProductPropertyValues()) {
					if(spProductPropertyValue.getSpId().equals(Integer.valueOf(spuHandler(skuString)[1]))){
						spProductBproperties2.setSpProductPropertyValue(spProductPropertyValue);//spu_value
						break;
					}
				}
				spProductBproperties2.setSpIssku(1);//是否sku
				spProductBproperties2.setSpProductSku(spProductSku);
				spProductBproperties.add(spProductBproperties2);
			}
		}
		spProductBinfo.setSpProductSkus(spProductSkus);
		//spu
		for (String spu : commodity.getSp_spu()) {
			SpProductBproperties spProductBproperties2=new SpProductBproperties();//spu
			String[] spu2=spuHandler(spu);
			SpProductPropertyName spProductPropertyName=iCommodityPropertiesService.getById(Integer.valueOf(spu2[0]));
			spProductBproperties2.setSpProductPropertyName(spProductPropertyName);
			for (SpProductPropertyValue spProductPropertyValue : spProductPropertyName.getSpProductPropertyValues()) {
				if(spProductPropertyValue.getSpId().equals(Integer.valueOf(Integer.valueOf(spu2[1])))){
					spProductBproperties2.setSpProductPropertyValue(spProductPropertyValue);//spu_value
					break;
				}
			}
			spProductBproperties.add(spProductBproperties2);
		}
		spProductBinfo.setSpProductBproperties(spProductBproperties);
		iCommodityService.save(spProductBinfo);
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");	
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	//获取属性值处理
	public String defaultValue(Set<SpProductPropertyValue> spProductPropertyValues) throws JsonProcessingException{
		String[] name=new String[spProductPropertyValues.size()];
		String[] value=new String[spProductPropertyValues.size()];
		int index=0;
		for (SpProductPropertyValue spProductPropertyValue : spProductPropertyValues) {
			name[index]=spProductPropertyValue.getSpPropertyvalue();
			value[index++]=spProductPropertyValue.getSpProductPropertyName().getSpId()+"-"+String.valueOf(spProductPropertyValue.getSpId());
		}
		return CustomUtil.stringsToString(name)+","+CustomUtil.stringsToString(value);
	}
	
	
	//删除商品
	public String delete() throws Exception {
		//删除商品
		iCommodityService.delete(commodityIds);
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//修改商品
	public String edit() throws Exception {
		//获取商品信息
		SpProductBinfo spProductBinfo = iCommodityService.getById(model.getSpId());
		//根据品牌查询属性名关联查询属性值，拼接json
		List<SpProductPropertyName> spProductPropertyNames = iCommodityPropertiesService
				.findPropertiesByCategoryId(spProductBinfo.getSpProductBrands().getSpProductCategory().getSpId());
		//拼接json
		List<ProductPro> spProductSkuPro = new ArrayList<ProductPro>();//sku
		List<ProductPro> spProductSpuPro = new ArrayList<ProductPro>();//spu
		for (SpProductPropertyName spProductPropertyName : spProductPropertyNames) {
			String sp_InputType = "";
			if (spProductPropertyName.getSpIssalepro() == 0 && spProductPropertyName.getSpIskeypro() == 0) {//基础属性
				if (spProductPropertyName.getSpIsenumpro() == 1)
					sp_InputType = "下拉框";
				else if (spProductPropertyName.getSpIsimportpro() == 1)
					sp_InputType = "输入框";
				else if (spProductPropertyName.getSpIsmultiselectpro() == 1)
					sp_InputType = "复选框";
				Set<SpProductPropertyValue> spProductPropertyValues =new HashSet<SpProductPropertyValue>();
				//基础属性
				for (SpProductBproperties spProductBproperties : spProductBinfo.getSpProductBproperties()) {
					if(spProductPropertyName.getSpId().equals(spProductBproperties.getSpProductPropertyName().getSpId())){
						spProductPropertyValues.add(spProductBproperties.getSpProductPropertyValue());
					}
				}	
				ProductPro productPro = new ProductPro(spProductPropertyName.getSpPropertyname(),
						spProductPropertyName.getSpAlias(), sp_InputType,
						setDefaultValue(spProductPropertyName.getSpProductPropertyValues(),spProductPropertyValues),
						spProductPropertyName.getSpIsrequirepro(), "sp_spu_pro_tbodyAppend_edit");
				spProductSpuPro.add(productPro);
				
			} else if (spProductPropertyName.getSpIssalepro() == 1) {//销售属性
				if (spProductPropertyName.getSpIsenumpro() == 1)
					sp_InputType = "下拉框";
				else if (spProductPropertyName.getSpIsimportpro() == 1)
					sp_InputType = "输入框";
				else if (spProductPropertyName.getSpIsmultiselectpro() == 1)
					sp_InputType = "复选框";
				Set<SpProductPropertyValue> spProductPropertyValues =new HashSet<SpProductPropertyValue>();
				//基础属性
				for (SpProductBproperties spProductBproperties : spProductBinfo.getSpProductBproperties()) {
					if(spProductPropertyName.getSpId().equals(spProductBproperties.getSpProductPropertyName().getSpId())){
						spProductPropertyValues.add(spProductBproperties.getSpProductPropertyValue());
					}
				}	
				ProductPro productPro = new ProductPro(spProductPropertyName.getSpPropertyname(),
						spProductPropertyName.getSpAlias(), sp_InputType,
						setDefaultValue(spProductPropertyName.getSpProductPropertyValues(),spProductPropertyValues),
						spProductPropertyName.getSpIsrequirepro(), "sp_sku_pro_tbodyAppend_edit");
				spProductSkuPro.add(productPro);
			}
		}
		//sku属性
		resp.getWriter().write("["+objectMapper.writeValueAsString(spProductSpuPro)
				+","+objectMapper.writeValueAsString(spProductSkuPro)+"]");
		return NONE;
	}
	
	//获取属性值处理
	public String setDefaultValue(Set<SpProductPropertyValue> spProductPropertyValues,Set<SpProductPropertyValue> spProductPropertyValueDefault) throws JsonProcessingException {
		String[] name = new String[spProductPropertyValues.size()];
		String[] value = new String[spProductPropertyValues.size()];
		int index = 0;
		for (SpProductPropertyValue spProductPropertyValue : spProductPropertyValues) {
			name[index] = spProductPropertyValue.getSpPropertyvalue();
			value[index++] = spProductPropertyValue.getSpProductPropertyName().getSpId() + "-"
					+ String.valueOf(spProductPropertyValue.getSpId());
		}
		String[] defaultValue=new String[spProductPropertyValueDefault.size()];
		index = 0;
		for (SpProductPropertyValue spProductPropertyValue : spProductPropertyValueDefault) {
			defaultValue[index++]=spProductPropertyValue.getSpProductPropertyName().getSpId()+"-"+String.valueOf(spProductPropertyValue.getSpId());
		}
		return CustomUtil.stringsToString(name) + "," + CustomUtil.stringsToString(value)+","+CustomUtil.stringsToString(defaultValue);
	}
	
	
	public static String[] skuHandler(String string) {
		String[] s = string.split("|");
		return s;
	}
	
	//sku以‘;’号隔开
	public static String skuHandler(List<String> sku){
		if (sku == null) {
			return null;
		}
		StringBuilder result = new StringBuilder();
		boolean flag = false;
		for (String string : sku) {
			if (flag) {
				result.append(";");
			} else {
				flag = true;
			}
			result.append(string);
		}
		return result.toString();
	}
	
	public static String[] spuHandler(String string) {
		String[] s = string.split("-");
		return s;
	}
	
	public Integer getTopCategoryId() {
		return topCategoryId;
	}

	public void setTopCategoryId(Integer topCategoryId) {
		this.topCategoryId = topCategoryId;
	}

	public Integer getSecondCategoryId() {
		return secondCategoryId;
	}

	public void setSecondCategoryId(Integer secondCategoryId) {
		this.secondCategoryId = secondCategoryId;
	}

	public Integer getThirdCategoryId() {
		return thirdCategoryId;
	}

	public void setThirdCategoryId(Integer thirdCategoryId) {
		this.thirdCategoryId = thirdCategoryId;
	}
	
	public Commodity getCommodity() {
		return commodity;
	}

	@JSON(serialize = true, deserialize = true)
	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public Integer[] getCommodityIds() {
		return commodityIds;
	}

	@JSON(serialize = true, deserialize = true)
	public void setCommodityIds(Integer[] commodityIds) {
		this.commodityIds = commodityIds;
	}

	
}
