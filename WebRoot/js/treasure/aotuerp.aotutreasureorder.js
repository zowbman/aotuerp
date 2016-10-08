$(function() {
	// 宝贝上架订单panel
	$('#treasureShelvesOrder-panel').panel({
		fit:true,
		border:false,
	});
	$('#treasureShelvesOrder_form').panel({
		// fit:true,
		border:false,
	})
	
	// 导入商品按钮
	$('#importPdForTreasure').linkbutton({    
	    /* iconCls: 'icon-search', */
	    plain:true,
	    onClick:function(){
	    	$('#importPdForTreasure-dialog').dialog('open');
	    },
	});  
	
	// 导入商品dialog
	$('#importPdForTreasure-dialog').dialog({
		width : 1000,
		height : 500,
		title : '选择商品',
		modal : true,
		closed : true,
		buttons : [ {
			text : '选择',
			/* iconCls:'' */
			handler : function() {
				// 获取#importPdForTreasure-datagrid选择什么商品
				var pdForTreasure=$('#importPdForTreasure-datagrid').datagrid('getSelected');
				if(pdForTreasure!=null){
					// 打开此商品sku信息dialog
					$('#importPdSkuForTreasure-dialog').dialog('open');
				}else{
				}
			}
		}, {
			text : '取消',
			/* iconCls:'' */
			handler : function() {
				$('#importPdForTreasure-dialog').dialog('close');
			}
		} ],
		onOpen:function(){
			$('#importPdForTreasure-datagrid').datagrid({
				url:'commodity_listData.action',
			});
		},
	});
	
	// 导入商品Skudialog
	$('#importPdSkuForTreasure-dialog').dialog({
		width : 800,
		height : 400,
		title : '选择商品种类',
		modal : true,
		closed : true,
		buttons : [ {
			text : '选择',
			/* iconCls:'' */
			handler : function() {
				var data=$('#importPdSkuForTreasure-datagrid').datagrid('getSelections');
				var spProductSkuIds=[];
				$.each(data,function(i,item){
					spProductSkuIds.push(item.sp_Skuid);
				});
				var map={};
				map['spProductSkuIds']=spProductSkuIds;
				$.ajax({
					url : 'treasure_importPdSkuData.action',// 请求路径
					type : 'POST',
					contentType:'application/json;charset=utf-8',
					data : JSON.stringify(map),
					beforeSend : function(){
						$.messager.progress({
							text : '正在获取数据中...',
						});
					},
					success : function(data,response,status){
						$.messager.progress('close');
						if(data.code === 0){// 成功
							$('#treasureShelvesOrder_form').panel({
								href:'treasure_treasureShelvesOrderForm.action',
								queryParams:{
									spProductId:$('#importPdForTreasure-datagrid').datagrid('getSelected').sp_id,
								},
								onLoad:function(){
									// 加载sku属性开始
									// 获取组合
									var SKUTitle=new Array();// sku标题
									var SKUZuhe=new Array();// sku组合
									$.each(data.data,function(i,item){
										var oneSku=new Array();// 一个sku
										$.each(item.spProductBpropertieses,function(i,item1){
											// 格式（属性值的名称+“|”+属性名的值+“-”+属性值得值）
											oneSku.push(item1.spProductPropertyValue.spPropertyvalue+"|"+item1.spProductPropertyName.spId+"-"+item1.spProductPropertyValue.spId);
											SKUTitle.push(item1.spProductPropertyName.spPropertyname);
										});
										var map={};
										map['skuId']=item.spSkuid;
										map['skuPro']=oneSku.join(',')
										SKUZuhe.push(map);
									});
									/*
									 * console.log("组合title-->"+SKUTitle);
									 * console.log("组合--->"+SKUZuhe);
									 */
									// 开始建table
									step.Creat_Table(unique(SKUTitle),SKUZuhe);
									// 加载sku属性结束
									
									// 图片上传tabs
									$('#multimage-tabs').tabs({
										fit : true,
										border : true,
										plain:true,
									});
								}
							});
							$('#importPdForTreasure-dialog').dialog('close');
							$('#importPdSkuForTreasure-dialog').dialog('close');
						}else{
							$.messager.alert('获取数据失败','未知错误，请重试','warning');
						}
					}
				});
			}
		}, {
			text : '取消',
			/* iconCls:'' */
			handler : function() {
				$('#importPdSkuForTreasure-dialog').dialog('close');
			}
		} ],
		onOpen:function(){
			$('#importPdSkuForTreasure-datagrid').datagrid({
				url:'commodity_skuListData.action',
				queryParams:{
					// 附带商品id
					spId:$('#importPdForTreasure-datagrid').datagrid('getSelected').sp_id,
				},
			});
		},
	});
	
	// 导入商品datagrid
	$('#importPdForTreasure-datagrid')
	.datagrid({
				singleSelect:true,
				fit:true,
				fitColums : true,
				striped : true,
				rownumbers : true,
				border : false,
				pagination : true,
				pageSize : 10,
				pageList : [ 10, 20, 30, 40, 50 ],
				pageNumber : 1,
				columns : [ [ {
					field : 'sp_id',
					title : '自动编号',
					width : 100,
					checkbox : true,
				},{
					field : 'sp_idShow',
					title : '商品编号',
					width : 150,
				}, {
					field : 'sp_Pdspu',
					title : '商品名称',
					width : 150,
				},{
					field : 'sp_Brandn',
					title : '品牌',
					width : 250,
				},{
					field : 'sp_Categoryn',
					title : '类别',
					width : 150,
				}] ],
			});
	
	// 导入商品skudatagrid
	$('#importPdSkuForTreasure-datagrid')
	.datagrid({
				fit:true,
				fitColums : true,
				striped : true,
				rownumbers : true,
				border : false,
				pagination : true,
				pageSize : 10,
				pageList : [ 10, 20, 30, 40, 50 ],
				pageNumber : 1,
				columns : [ [ {
					field : 'sp_Skuid',
					title : '自动编号',
					width : 100,
					checkbox : true,
				},{
					field : 'sp_SkuidShow',
					title : '商品编号',
					width : 100,
				}, {
					field : 'sp_Pdspu',
					title : '商品名称',
					width : 100,
				},{
					field : 'sp_Pdproname',
					title : '规格/型号',
					width : 150,
				},{
					field : 'sp_Brandn',
					title : '品牌',
					width : 100,
				},{
					field : 'sp_Categoryn',
					title : '类别',
					width : 100,
				},{
					field : 'sp_Quantity',
					title : '库存量',
					width : 100,
				}] ],
			});
	

	// 功能栏
	aotuerpSkupd_tool = {
			// 取消所有选定
			redo : function() {
				$('#aotuerpSkupd').datagrid('unselectAll');
			},
			reload : function() {
				$('#aotuerpSkupd').datagrid('reload');
			},
	}
	
	var step = {
            // SKU信息组合
            Creat_Table: function (SKUTitle,SKUZuhe) {
                step.hebingFunction();
                var SKUObj = SKUTitle;
                // var skuCount = SKUObj.length;//
                var arrayTile = new Array();　// 标题组数
                var arrayInfor = new Array();　// 盛放每组选中的CheckBox值的对象
                var arrayColumn = new Array();// 指定列，用来合并哪些列
                var bCheck = true;// 是否全选
                var columnIndex = 0;
                $.each(SKUObj, function (i, item) {
                    arrayColumn.push(columnIndex);
                    columnIndex++;
                    arrayTile.push(item);
                   
                    if (SKUZuhe.join() == "") {
                        bCheck = false;
                    }
                    // var skuValue = SKUObj.find("li").eq(index).html();
                });

                // 开始创建Table表
                if (bCheck == true) {
                	var zuheInput=new Array();// 文本框(数量、价格)
                    var RowsCount = 0;
                    $("#createTreasureTable").html("");
                    var table = $("<table id=\"TreasureProcess\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width:100%;margin:10px 0 10px 0;\"></table>");
                    table.appendTo($("#createTreasureTable"));
                    var thead = $("<thead></thead>");
                    thead.appendTo(table);
                    var trHead = $("<tr></tr>");
                    trHead.appendTo(thead);
                    // 创建表头
                    $.each(arrayTile, function (index, item) {
                        var td = $("<td>" + item + "</td>");
                        td.appendTo(trHead);
                    });
                    var itemColumHead = $("<td  style=\"width:70px;\">数量</td><td style=\"width:70px;\">价格</td> ");
                    itemColumHead.appendTo(trHead);
                    // var itemColumHead2 = $("<td >商家编码</td><td >商品条形码</td>");
                    // itemColumHead2.appendTo(trHead);

                    var tbody = $("<tbody></tbody>");
                    tbody.appendTo(table);
                   
                    // 组合
                    if (SKUZuhe.length > 0) {
                    	var treasureSkuId=new Array();
                        // 创建行
                        $.each(SKUZuhe, function (index, item) {
                            var td_array = item.skuPro.split(",");
                            var tr = $("<tr></tr>");
                            var _sp_TreasureSkuIdField=item.skuId;
                            tr.appendTo(tbody);
                            $.each(td_array, function (i, values) {
                                var td = $("<td>" + values.split('|')[0] + "</td>");
                                td.appendTo(tr);                            
                            }); 
                            var td1 = $("<td ><input id=\"sp_TreasureSkuField_quantity_"+_sp_TreasureSkuIdField+"\" class=\"l-text textbox\" type=\"text\" value=\"\"></td>");
                            td1.appendTo(tr);
                            var td2 = $("<td ><input id=\"sp_TreasureSkuField_price_"+_sp_TreasureSkuIdField+"\" class=\"l-text textbox\" type=\"text\" value=\"\"></td>");
                            td2.appendTo(tr);
                            // var td3 = $("<td ><input name=\"Txt_NumberSon\"
							// class=\"l-text\" type=\"text\"
							// value=\"\"></td>");
                            // td3.appendTo(tr);
                            // var td4 = $("<td ><input name=\"Txt_SnSon\"
							// class=\"l-text\" type=\"text\"
							// value=\"\"></td>");
                            // td4.appendTo(tr);
                            var map={};
                            map['_sp_TreasureSkuIdField']=item.skuId;
                            zuheInput.push(map)
                            treasureSkuId.push(item.skuId);
                        });
                        $('#treasureSkuId').val(treasureSkuId.join(','));// 设置skuid隐藏域
						$.each(zuheInput,function(i,item){// 文本框 （数量、价格）//加验证
							$('#sp_TreasureSkuField_quantity_'+item._sp_TreasureSkuIdField+'').textbox({
							    required: true,
							});
							$('#sp_TreasureSkuField_price_'+item._sp_TreasureSkuIdField+'').textbox({
							    required: true,
							});
						});
						
						// 宝贝标题
						$('#spTreasuretitle').validatebox({
							required:true,
						});
						// 宝贝卖点
						$('#spTreasuresellingpoints').validatebox({
							required:true,
						});
						
						// 提交按钮
						$('#treasureBtn').linkbutton({
							onClick:function(){
								//表单验证（不含图片）
								if($('#treasureShelvesOrder_form').form('validate')){
									var picFlag=false;
									
									//图片校验
									var previews=$('.multimage-gallery').find('ul').find('li').find('.preview');
									$.each(previews,function(i,preview){
										//获取preview
										var picUrl=$(preview).find('input[name="picUrl'+(i+1)+'"]');
										if(!($(picUrl).is(":empty")&&$.trim($(picUrl).val())=='')){
											picFlag=true;
										}
									});
									if(!picFlag){
										$.messager.alert('提交失败', '对不起，您必须上传至少一张主图片','warning');
										//显示错误
										$('#err_nav_multiPicError').attr('style','display:block');
										return false;
									}else{
										$('#err_nav_multiPicError').attr('style','display:none');
									}
									
									// 封装参数
									var treasureSku=new Array();
									$.each($('#treasureSkuId').val().split(','),function(i,item){
										var temp={
												spProductSku:{
													spSkuid:item,
												},
												spSalesprice:$('#sp_TreasureSkuField_price_'+item).textbox('getValue'),// 销售价
												spSalesquantity:$('#sp_TreasureSkuField_quantity_'+item).textbox('getValue'),// 库存数量
										}
										treasureSku.push(temp);
									});// sku信息
									
									//图片封装
									var imgIds=new Array();
									var imgPrimaryId;//是否主图（1：yes，0：no）
									$.each(previews,function(i,preview){
										//获取preview
										var picUrl=$(preview).find('input[name="picUrl'+(i+1)+'"]');
										if(!($(picUrl).is(":empty")&&$.trim($(picUrl).val())=='')){
											imgIds.push($(picUrl).val());
											if(i==0){
												imgPrimaryId=$(picUrl).val();
											}
										}
									});
									
									
									var params={
											spAotuerpTreasureInfo:{
												spTreasuretitle:$('#spTreasuretitle').val(),// 标题
												spTreasuresellingpoints:$('#spTreasuresellingpoints').val(),// 卖点
												spTreasuredescription:escape(CKEDITOR.instances['treasure_description_ck'].getData()),// 富文本
												spAotuerpTreasureProductSkus:treasureSku,// sku商品
											},
											imgIds:imgIds,//图片
											imgPrimaryId:imgPrimaryId,
											spProductId:$('#spProductBinfoId').val(),// 商品id
									}
									// ajax提交
									$.ajax({
										type : 'POST',
										url : 'treasure_orderSubmit.action',
										contentType:'application/json;charset=utf-8',
										data :JSON.stringify(params),
										success : function(data) {
											if (data.code === 0) {
												$.messager.show({
													title : '提示',
													msg : '宝贝上架订单提交成功，请等待审核'
												});
												// 刷新当前tab
											    var currTab =  self.parent.$('#tabs').tabs('getSelected'); // 获得当前tab
											    var url = currTab.panel('options').href;
											    currTab.panel('refresh', url);
												
											} else {
												$.messager.alert('宝贝上架订单干提交失败', '未知错误，请重试','warning');
											}
										}
									});
								}
							},
						});
						
						
						
                    }
                    // 结束创建Table表
                    arrayColumn.pop();// 删除数组中最后一项
                    // 合并单元格
                    $(table).mergeCell({
                        // 目前只有cols这么一个配置项, 用数组表示列的索引,从0开始
                        cols: arrayColumn
                    });
                }
            },// 合并行
            hebingFunction: function () {
                $.fn.mergeCell = function (options) {
                    return this.each(function () {
                        var cols = options.cols;
                        for (var i = cols.length - 1; cols[i] != undefined; i--) {
                            // fixbug console调试
                            // console.debug(cols[i]);
                            mergeCell($(this), cols[i]);
                        }
                        dispose($(this));
                    });
                };
                // 如果对javascript的closure和scope概念比较清楚, 这是个插件内部使用的private方法
                function mergeCell($table, colIndex) {
                    $table.data('col-content', ''); // 存放单元格内容
                    $table.data('col-rowspan', 1); // 存放计算的rowspan值 默认为1
                    $table.data('col-td', $()); // 存放发现的第一个与前一行比较结果不同td(jQuery封装过的),
												// 默认一个"空"的jquery对象
                    $table.data('trNum', $('tbody tr', $table).length); // 要处理表格的总行数,
																		// 用于最后一行做特殊处理时进行判断之用
                    // 我们对每一行数据进行"扫面"处理 关键是定位col-td, 和其对应的rowspan
                    $('tbody tr', $table).each(function (index) {
                        // td:eq中的colIndex即列索引
                        var $td = $('td:eq(' + colIndex + ')', this);
                        // 取出单元格的当前内容
                        var currentContent = $td.html();
                        // 第一次时走此分支
                        if ($table.data('col-content') == '') {
                            $table.data('col-content', currentContent);
                            $table.data('col-td', $td);
                        } else {
                            // 上一行与当前行内容相同
                            if ($table.data('col-content') == currentContent) {
                                // 上一行与当前行内容相同则col-rowspan累加, 保存新值
                                var rowspan = $table.data('col-rowspan') + 1;
                                $table.data('col-rowspan', rowspan);
                                // 值得注意的是 如果用了$td.remove()就会对其他列的处理造成影响
                                $td.hide();
                                // 最后一行的情况比较特殊一点
                                // 比如最后2行 td中的内容是一样的,
								// 那么到最后一行就应该把此时的col-td里保存的td设置rowspan
                                if (++index == $table.data('trNum'))
                                    $table.data('col-td').attr('rowspan', $table.data('col-rowspan'));
                            } else { // 上一行与当前行内容不同
                                // col-rowspan默认为1, 如果统计出的col-rowspan没有变化, 不处理
                                if ($table.data('col-rowspan') != 1) {
                                    $table.data('col-td').attr('rowspan', $table.data('col-rowspan'));
                                }
                                // 保存第一次出现不同内容的td, 和其内容, 重置col-rowspan
                                $table.data('col-td', $td);
                                $table.data('col-content', $td.html());
                                $table.data('col-rowspan', 1);
                            }
                        }
                    });
                }
                // 同样是个private函数 清理内存之用
                function dispose($table) {
                    $table.removeData();
                }
            },
        }
	
	// 去除重复
	function unique(arr){
		for(var i=0; i<arr.length; i++){
			for(var j=i+1; j<arr.length;j++){
			    if(arr[i]==arr[j]){
			    	arr = removeElement(j,arr);// 删除指定下标的元素
			        i=-1;
			        break;
			    }
			}
		}
		return arr;  
	}
	// 删除数组 用到的函数
	function removeElement(index,array){
		if(index>=0 && index<array.length){
			for(var i=index; i<array.length; i++){
				array[i] = array[i+1];
			}
			array.length = array.length-1;
		}
		return array;
	}
	
	// 为选择宝贝图片绑定事件
	$(document).on('change','#spTreasureImg',function(){
		//定义图片规格
		var imgSpec=[{
						imgWidth:100,
						imgHeight:100,
					}];
		var data={};
		$.each(imgSpec,function(i,item){
			data['specs['+i+'].imgWidth']=item.imgWidth;
			data['specs['+i+'].imgHeight']=item.imgHeight;
		});
		var options={
				url:'treasure_orderPicUpload.action',
				type:'post',
				data:data,
				contentType: 'application/json;charset=utf-8',
				beforeSubmit:function(){
					$.messager.progress({
						text : '正在上传图片请稍后...',
					});
				},
				success:function(data){
					$.messager.progress('close');
						if(data.code==0){//成功
							//获取li
							var lis=$('.multimage-gallery').find('ul').find('li');
							//循环li
							$.each(lis,function(i,li){
								//获取preview
								var picUrl=$(li).find('.preview').find('input[name="picUrl'+(i+1)+'"]');
								if(picUrl.val()==''){
									picUrl.val(data.data.imgId);//设置原图片id
									$(li).addClass('has-img');//添加样式
									//追加img标签  缩略图
									$(li).find('.preview').append('<img src="/pic/'+data.data.imgPath+'_100x100'+data.data.postfix+'">')
									return false;//跳出循环
								}
							})
						}else{//失败
							$.messager.alert('上传图片失败','未知错误，请重试','warning');
						}
					},
					clearForm:true,
					restForm:true,
				}
		$('#imgForm').ajaxSubmit(options);
	})
});