$(function() {
	//商品列表
	$('#aotucommodity')
			.datagrid(
					{
						url : 'commodity_listData.action',// url请求地址
						fit : true,
						fitColums : true,
						striped : true,
						rownumbers : true,
						border : false,
						pagination : true,
						pageSize : 10,
						pageList : [ 10, 20, 30, 40, 50 ],
						pageNumber : 1,
						toolbar : '#aotucommodity_tool',
						columns : [ [ {
							field : 'sp_id',
							title : '自动编号',
							width : 100,
							checkbox : true,
							rowspan : 2,
						}, {
							title : '商品信息',
							width : 100,
							colspan : 5,
						} ], [ {
							field : 'sp_idShow',
							title : '商品编号',
							width : 200,
						}, {
							field : 'sp_Pdspu',
							title : '商品名称',
							width : 200,
						},{
							field : 'sp_Brandn',
							title : '品牌名称',
							width : 200,
						},{
							field : 'sp_Categoryn',
							title : '类别',
							width : 100,
						},{
							field : 'sp_Pdcredate',
							title : '创建日期',
							width : 200,
						}/*,{
							field : 'sp_Statusn',
							title : '商品状态',
							width : 200,
						}*/] ],
					});	
	//类目1
	$('#category01').datalist({ 
	    url: 'commodity_categoryTopData.action',
	    height:250,
	    width:200,
	    valueField:'spId',
	    textField:'spCategoryn',
	    onClickRow:function(index,row){
	    	//类目2
	    	$('#category02').datalist({
	    		url: 'commodity_categorySecondData.action',
	    	    queryParams :{
	    	    	'topCategoryId': row.spId,//顶级类目ID
	    		},
	    	});
	    	//品牌清空
	    	$('#brands').datalist('loadData',{rows:[]});
	    },
	}); 
	//类目2
	$('#category02').datalist({
	    height:250,
	    width:200,
	    valueField:'spId',
	    textField:'spCategoryn',
	    onClickRow:function(index,row){
	    	//品牌
	    	$('#brands').datalist({ 
	    		url: 'commodity_brandsData.action',
	    	    queryParams :{
	    	    	'secondCategoryId': row.spId,//顶级类目ID
	    		},
	    	}); 
	    } 
	}); 
	
	//品牌
	$('#brands').datalist({ 
		valueField:'spId',
		textField:'spBrandn',
	    height:250,
	    width:200,
	}); 
	
	//添加商品
	$('#aotucommodity_add').dialog({
		width:1000,
		height:700,
		title:'新增商品',
		modal:true,
		closed:true,
		buttons:[{
			text:'提交',
			/*iconCls:''*/
			handler: function(){
				if($('#aotucommodity_add').form('validate')){
					var nameArr = [];
		            var objArr = $("#sp_sku_pro_tbodyAppend [isRequired],#sp_sku_pro_tbodyAppend [isRequired]");
		            var i = 0;
		            objArr.each(function (i) {
		                var _o = { name: $(this).attr("name"), maxlength: parseInt($(this).attr("maxlength"), 10), label: $(this).attr("label"), type: this.type, tagName: this.tagName, dataType: $(this).attr("dataType"), isRequire: $(this).attr("isRequired") };
		                if (!indexOfNameArr(nameArr, _o)) {
		                    nameArr.push(_o);
		                }
		            });
		            for (i = 0; i < nameArr.length; i++) {
		                var obj = nameArr[i];
		                var _e = document.getElementsByName(obj.name)[0];
		                var v = _e.value; ;
		                //单选框,或复选框
		                if (obj.type == 'radio' || obj.type == 'checkbox') {
		                    if (obj.isRequire == "1") {
		                        v = getGroupValue(obj.name);
		                        if (v == "") {
		                            alert("请选择 [" + obj.label + "] ！");
		                            setGroupfocus(obj.name);
		                            return false;
		                        }
		                    }
		                }
		                else if (obj.isRequire == "1")//其它类型：比如文本框，下拉框，文本域
		                {
		                    if (v == "") {
		                        alert("请输入 [" + obj.label + "] ！");
		                        _e.focus();
		                        return false;
		                    }
		                }
		            }
				//(单选框\复选框\文本框\下拉框\文本域)
				var commodity={};
				//品牌
				commodity['sp_spu_brand']=$('#sp_spu_brand').combobox('getValue');
				//标题
				commodity['sp_spu_title']=$('input[name="spPdspu"]').val();
				//基础属性
				////非easui
				//var _sp_spu_pro=$("#sp_spu_pro_tbodyAppend input:checked,#sp_spu_pro_tbodyAppend select option:selected,#sp_spu_pro_tbodyAppend textarea");
				////easyui
				var _sp_spu_pro=$('#aotucommodity_spu').val().split(',');
				var commoditySpu=new Array();
				$.each(_sp_spu_pro,function(i,item){
					if($.trim(item)!=""){
						commoditySpu.push($('input[name='+item+']').val());
					}
				});

				/*_sp_spu_pro.each(function(){
					if($.trim($(this).val())!=""){
						commoditySpu.push($(this).val());
					}
					
				});*/

				commodity['sp_spu']=commoditySpu;
				//销售属性
				var _sp_sku_pro=$("#sp_sku_pro_tbodyAppend input:checked,#sp_sku_pro_tbodyAppend select option:selected,#sp_sku_pro_tbodyAppend textarea");
				var SKUObj = $(".Father_Title");
				var arrayInfor = new Array();　//盛放每组选中的CheckBox值的对象
				$.each(SKUObj, function (i, item) {
					var itemName = "Father_Item" + i;
					var order = new Array();					
	                $("." + itemName + " input[type=checkbox]:checked").each(function () {
	                    order.push($(this).next().text()+"|"+$(this).val());// 显示的值
	                });
	                arrayInfor.push(order);
				});
				//封装sku
				var commoditySku=new Array();
				var isCreateSku=true;
				//组合形成sku
				var zuheDate =combination_tool(arrayInfor);//生成组合
				$.each(zuheDate,function(i,item){
					isCreateSku=false;
					var temp =item.split(',');
					var sku={};
					var sp_sku=new Array();
					$.each(temp,function(j,item1){
						sp_sku.push(item1.split('|')[1])//sku值
					});
					sku['sp_sku']=sp_sku;
					sku['sp_sku_name']=temp[0].split('|')[0]+'-'+temp[1].split('|')[0];//sku值

					//获取sku价格和数量
					sku['sp_PdCount']=$('#sp_SkuField_quantity_'+temp[0].split('|')[1]+"_"+temp[1].split('|')[1]).val();//数量
					sku['sp_PdPrice']=$('#sp_SkuField_price_'+temp[0].split('|')[1]+"_"+temp[1].split('|')[1]).val();//价格
					commoditySku.push(sku);
				});
				
				if(isCreateSku){
					//非组合形成sku
					$.each(arrayInfor,function(i,item){
						$.each(item,function(j,item1){
							var sku={};
							sku['sp_sku']=item1.split('|')[1]//sku值
							sku['sp_sku_name']=item1.split('|')[0];//sku值
							sku['sp_PdCount']=$('#sp_quantity').val();//数量
							sku['sp_PdPrice']=$('#sp_price').val();//价格
							commoditySku.push(sku);
						});
					});
					
				}
				commodity['sp_sku']=commoditySku;
				var data={};
				data['commodity']=commodity;
				console.log(JSON.stringify(data));
				$.ajax({
					type : 'POST',
					url : 'commodity_add.action',
					contentType:'application/json;charset=utf-8',
					data :JSON.stringify(data),
					success : function(data) {
						if (data.code === 0) {
							$.messager.show({
								title : '提示',
								msg : '商品添加成功'
							});
							
						} else {
							$.messager.alert('商品添加失败', '未知错误，请重试','warning');
						}
						$('#aotucommodity_add').dialog('close').form('reset');
						$('#aotucommodity').datagrid('reload');
					}
				});
			}
		}
		},{
			text:'取消',
			/*iconCls:''*/
			handler: function(){
				$('#aotucommodity_add').dialog('close').form('reset');
			}
		}],
		onOpen:function(){
			$('.content').accordion({    
				border : false,
			}); 
			var expandPanel=$('.content').accordion('getPanel',0);
			var collapse=$('.content').accordion('getPanel',1);
			expandPanel.panel('expand');//展开
			collapse.panel('collapse');//折叠
			
			$('#category01').datalist('reload');
			//二级分类清空
	    	$('#category02').datalist('loadData',{rows:[]});
	    	//品牌清空
	    	$('#brands').datalist('loadData',{rows:[]});
		},
	});
	
	//修改商品
	$('#aotucommodity_edit').dialog({
		width:1000,
		height:700,
		title:'修改商品',
		modal:true,
		closed:true,
		buttons:[{
			text:'提交',
			/*iconCls:''*/
			handler: function(){
				
			}
		},{
			text:'取消',
			/*iconCls:''*/
			handler: function(){
				$('#aotucommodity_edit').dialog('close').form('reset');
			}
		}],
	});
	
	$('#agreementBtn').click(function(){
		var collapse=$('.content').accordion('getPanel',0);
		var expandPanel=$('.content').accordion('getPanel',1);
		var row=$('#category02').datalist('getSelected');//类别属性
		if($('#category01').datalist('getSelected')==null || 
				$('#category02').datalist('getSelected')==null ||
				$('#brands').datalist('getSelected')==null){
			$.messager.alert('提示','请选择商品类目以及品牌后才进行下一步');   
		}else{
			collapse.panel('collapse');//折叠
			expandPanel.panel('expand');//展开
			//清空spu属性和sku属性
			$('#sp_spu_pro_tbodyAppend').empty();
			$('#sp_sku_pro_tbodyAppend').empty();
			$('#createTable').empty();
			//查询类别属性
	    	$.ajax({
				type : 'POST',
				url : 'commodity_proData.action',
				data : {
					'thirdCategoryId':row.spId,
				},
				success : function(data) {
					//console.log(data);
					var _emptyBrand=true;
					//brand品牌
					$('#sp_spu_brand').combobox({
						url : 'commodity_brandsData.action',
						queryParams:{
							'secondCategoryId':row.spId,
						},
						valueField : 'spId',
						textField : 'spBrandn',
						editable :false,
						onLoadSuccess:function(data){
							if(_emptyBrand){
								_emptyBrand=false;
								var map={};
								map['spBrandn']="==请选择==";
								data.unshift(map);//最上
								//data.put();//最底
								$('#sp_spu_brand').combobox('loadData',data);
							}
							$('#sp_spu_brand').combobox('setValue',$('#brands').datalist('getSelected').spId);
						}
					});

					//必要属性
					$('input[name="spPdspu"]').validatebox({
					    required: true,    
					}); 
					$('#sp_price').validatebox({    
					    required: true,    
					}); 
					$('#sp_quantity').validatebox({   
					    required: true,    
					}); 

					//创建spu属性和sku属性
					createSPUSKU(data);
					
			        //SKU信息
			        $("#sp_sku_pro_tbodyAppend input").bind("change", function () {
			           step.Creat_Table();
			        });
			        
			        var step = {
			            //SKU信息组合
			            Creat_Table: function () {
			                step.hebingFunction();
			                var SKUObj = $("#sp_sku_pro_tbodyAppend .Father_Title");
			                //var skuCount = SKUObj.length;//
			                var arrayTile = new Array();　//标题组数
			                var arrayInfor = new Array();　//盛放每组选中的CheckBox值的对象
			                var arrayColumn = new Array();//指定列，用来合并哪些列
			                var bCheck = true;//是否全选
			                var columnIndex = 0;
			                $.each(SKUObj, function (i, item) {
			                    arrayColumn.push(columnIndex);
			                    columnIndex++;
			                    var temp=SKUObj.eq(i).html().replace("：", "");
			                    temp=temp.replace("<span style=\"color:red\">(*)</span>", "");
			                    arrayTile.push(temp);
			                    var itemName = "Father_Item" + i;
			                    //选中的CHeckBox取值
			                    var order = new Array();
			                    $("#sp_sku_pro_tbodyAppend ." + itemName + " input[type=checkbox]:checked").each(function () {
			                        order.push($(this).next().text()+"|"+$(this).val());//显示的值
			                    });
			                    arrayInfor.push(order);
			                    if (order.join() == "") {
			                        bCheck = false;
			                    }
			                    //var skuValue = SKUObj.find("li").eq(index).html();
			                });

			                //开始创建Table表            
			                if (bCheck == true) {
			                	var zuheInput=new Array();//文本框(数量、价格)
			                    var RowsCount = 0;
			                    $("#createTable").html("");
			                    var table = $("<table id=\"process\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width:100%;margin:10px 0 10px 0;\"></table>");
			                    table.appendTo($("#createTable"));
			                    var thead = $("<thead></thead>");
			                    thead.appendTo(table);
			                    var trHead = $("<tr></tr>");
			                    trHead.appendTo(thead);
			                    //创建表头
			                    $.each(arrayTile, function (index, item) {
			                        var td = $("<td>" + item + "</td>");
			                        td.appendTo(trHead);
			                    });
			                    var itemColumHead = $("<td  style=\"width:70px;\">数量</td><td style=\"width:70px;\">价格</td> ");
			                    itemColumHead.appendTo(trHead);
			                    //var itemColumHead2 = $("<td >商家编码</td><td >商品条形码</td>");
			                    //itemColumHead2.appendTo(trHead);

			                    var tbody = $("<tbody></tbody>");
			                    tbody.appendTo(table);

			                    ////生成组合
			                    var zuheDate = step.doExchange(arrayInfor);
			                    if (zuheDate.length > 0) {
			                        //创建行
			                        $.each(zuheDate, function (index, item) {
			                            var td_array = item.split(",");
			                            var tr = $("<tr></tr>");
			                            var _sp_SkuField="";
			                            tr.appendTo(tbody);
			                            $.each(td_array, function (i, values) {
			                                var td = $("<td>" + values.split('|')[0] + "</td>");
			                                td.appendTo(tr);
			                                _sp_SkuField+=((values.split('|')[1])+"_");
			                            });
			                            _sp_SkuField=_sp_SkuField.toString().substring(0,_sp_SkuField.length-1);//去除最后一个‘_’
			                            var td1 = $("<td ><input id=\"sp_SkuField_quantity_"+_sp_SkuField+"\" class=\"l-text textbox\" type=\"text\" value=\"\"></td>");
			                            td1.appendTo(tr);
			                            var td2 = $("<td ><input id=\"sp_SkuField_price_"+_sp_SkuField+"\" class=\"l-text textbox\" type=\"text\" value=\"\"></td>");
			                            td2.appendTo(tr);
			                            //var td3 = $("<td ><input name=\"Txt_NumberSon\" class=\"l-text\" type=\"text\" value=\"\"></td>");
			                            //td3.appendTo(tr);
			                            //var td4 = $("<td ><input name=\"Txt_SnSon\" class=\"l-text\" type=\"text\" value=\"\"></td>");
			                            //td4.appendTo(tr);
			                            var map={};
					                    map['_sp_SkuField']=_sp_SkuField
			                            zuheInput.push(map)
			                        });
									
									$.each(zuheInput,function(i,item){//文本框	（数量、价格）
										$('#sp_SkuField_quantity_'+item._sp_SkuField+'').textbox({
										    required: true,
										});
										$('#sp_SkuField_price_'+item._sp_SkuField+'').textbox({
										    required: true,
										});
									});
			                    }
			                    //结束创建Table表
			                    arrayColumn.pop();//删除数组中最后一项
			                    //合并单元格
			                    $(table).mergeCell({
			                        // 目前只有cols这么一个配置项, 用数组表示列的索引,从0开始
			                        cols: arrayColumn
			                    });
			                    $('#sp_quantity').addClass('disabled');
			                    $('#sp_quantity').attr('readOnly',true);
			                    $('#sp_quantity').validatebox('disableValidation');
			                }else{
			                	$('#sp_quantity').removeClass('disabled');
			                	$('#sp_quantity').attr('readOnly',false);
			                	$('#sp_quantity').validatebox('enableValidation');
			                	$("#createTable").html("");
			                }
			            },//合并行
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
			                    $table.data('col-td', $()); // 存放发现的第一个与前一行比较结果不同td(jQuery封装过的), 默认一个"空"的jquery对象 
			                    $table.data('trNum', $('tbody tr', $table).length); // 要处理表格的总行数, 用于最后一行做特殊处理时进行判断之用 
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
			                                // 比如最后2行 td中的内容是一样的, 那么到最后一行就应该把此时的col-td里保存的td设置rowspan 
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
			            //组合数组
			            doExchange: function (doubleArrays) {
			                var len = doubleArrays.length;
			                if (len >= 2) {
			                    var arr1 = doubleArrays[0];
			                    var arr2 = doubleArrays[1];
			                    var len1 = doubleArrays[0].length;
			                    var len2 = doubleArrays[1].length;
			                    var newlen = len1 * len2;
			                    var temp = new Array(newlen);
			                    var index = 0;
			                    for (var i = 0; i < len1; i++) {
			                        for (var j = 0; j < len2; j++) {
			                            temp[index] = arr1[i] + "," + arr2[j];
			                            index++;
			                        }
			                    }
			                    var newArray = new Array(len - 1);
			                    newArray[0] = temp;
			                    if (len > 2) {
			                        var _count = 1;
			                        for (var i = 2; i < len; i++) {
			                            newArray[_count] = doubleArrays[i];
			                            _count++;
			                        }
			                    }
			                    //console.log(newArray);
			                    return step.doExchange(newArray);
			                }
			                else {
			                    return doubleArrays[0];
			                }
			            }
			        }
			        return step;
				},
			});
		}
	});
	
	//组合
	function combination_tool (doubleArrays) {
         var len = doubleArrays.length;
         if (len >= 2) {
             var arr1 = doubleArrays[0];
             var arr2 = doubleArrays[1];
             var len1 = doubleArrays[0].length;
             var len2 = doubleArrays[1].length;
             var newlen = len1 * len2;
             var temp = new Array(newlen);
             var index = 0;
             for (var i = 0; i < len1; i++) {
                 for (var j = 0; j < len2; j++) {
                     temp[index] = arr1[i] + "," + arr2[j];
                     index++;
                 }
             }
             var newArray = new Array(len - 1);
             newArray[0] = temp;
             if (len > 2) {
                 var _count = 1;
                 for (var i = 2; i < len; i++) {
                     newArray[_count] = doubleArrays[i];
                     _count++;
                 }
             }
             //console.log(newArray);
             return combination_tool(newArray);
         }
         else {
             return doubleArrays[0];
         }
     }
	
	//功能栏
	aotucommodity_tool = {
			add : function(){
				$('#aotucommodity_add').dialog('open');
			},
			edit : function(){
				var rows =$('#aotucommodity').datagrid('getSelections');
				if(rows.length>1){
					$.messager.alert('警告操作','编辑记录只能选定一条数据！','warning');
				}else if(rows.length==1){
					$.ajax({
						type:'POST',
						url : 'commodity_edit.action',
						data : {
							'spId' : rows[0].sp_id,
						},
						beforeSend : function(){
							$.messager.progress({
								text : '正在尝试获取数据...',
							});
						},
						success : function(data){
							$.messager.progress('close');
							$('#aotucommodity_edit').dialog('open');
							createSPUSKU(data);
							if(data.code==0){
								//$('#aotucommodity_edit').dialog('open');
								/*var obj=$.parseJSON(data[0].data);
								$('#aotuuser_edit').form('load',{
									'spUsersBinfoKey.spId':obj[0].spUsersBinfoKey.spId,//id
									'spUsersBinfoKey.spAtuid':obj[0].spUsersBinfoKey.spAtuid,//员工Epid
									'spAccount':obj[0].spAccount,//用户名
									'spPassword':obj[0].spPassword,//密码
									'spUsersDinfo.spNickname':obj[0].spUsersDinfo.spNickname,//昵称
									'spUsersDinfo.spRealname':obj[0].spUsersDinfo.spRealname,//真实姓名
									'spUsersDinfo.spSex':obj[0].spUsersDinfo.spSex,//性别
									'spUsersDinfo.spAge':obj[0].spUsersDinfo.spAge,//年龄
									'spUsersDinfo.spBirth':obj[0].spUsersDinfo.spBirth,//出生日期
									'spUsersDinfo.spCon':obj[0].spUsersDinfo.spCon,//星座
									'spUsersDinfo.spAnimal':obj[0].spUsersDinfo.spAnimal,//生肖
									'spUsersDinfo.spUserdistrict':obj[0].spUsersDinfo.spUserdistrict,//所在地
									'spUsersDinfo.spAddress':obj[0].spUsersDinfo.spAddress,//详细地址
								}).dialog('open');*/
							}
						},
					});
				}else if(rows.length==0){
					$.messager.alert('警告操作','编辑记录至少选定一条数据！','warning');
				}
			},
			remove : function() {
				var rows = $('#aotucommodity').datagrid('getSelections');
				if (rows.length > 0) {
					$.messager.confirm('确定', '你要删除所选的<strong>' + rows.length
							+ '</strong>条记录吗', function(flag) {
						if (flag) {
							var commodityIds=[];
							for(var i=0;i<rows.length;i++){
								commodityIds.push(rows[i].sp_id);
							}
							var map={};
							map['commodityIds']=commodityIds;
							$.ajax({
								type : 'POST',
								url : 'commodity_delete.action',
								contentType:'application/json;charset=utf-8',
								data : JSON.stringify(map),
								beforeSend : function() {
									$('#aotucommodity').datagrid('loading');
								},
								success : function(data) {
									if (data.code === 0) {
										$('#aotucommodity').datagrid('loaded');
										$('#aotucommodity').datagrid('reload');
										$('#aotucommodity').datagrid('unselectAll');
										$.messager.show({
											title : '提示',
											msg : '商品已被删除成功'
										});
									} else {
										$('#aotucommodity').datagrid('loaded');
										$('#aotucommodity').datagrid('reload');
										$('#aotucommodity')
												.datagrid('unselectAll');
										$.messager.alert('删除失败', '未知错误，请重试',
												'warning');
									}
								},
							});
						}
					});
				}else if(rows.length==0){
					$.messager.alert('警告操作','删除记录至少选定一条数据！','warning');
				}
			},
			// 取消所有选定
			redo : function() {
				$('#aotucommodity').datagrid('unselectAll');
			},
			reload : function() {
				$('#aotucommodity').datagrid('reload');
			}
	}
});


function createSPUSKU(data){
	//获取sku和spu
		var spuInputName=new Array();
		var skuInputName=new Array();
		var isRequireSelect=new Array();//下拉框
		for (var z=0;z<data.length;z++){
			var _html= "";
			 for (var i = 0; i < data[z].length; i++) {
		            var _displayName = data[z][i].sp_DisplayName;
		            var _fieldName = data[z][i].sp_FieldName;
		            var _valueType = data[z][i].sp_ValueType;
		            var _valueLength = data[z][i].sp_ValueLength;
		            var _inputType = data[z][i].sp_InputType;
		            var _defaultValue = data[z][i].sp_DefaultValue;
		            var _isRequire = data[z][i].sp_IsRequired;
		            var _sp_ProType=data[z][i].sp_ProType;
		            var _dvArr, _arrDisplay, _arrDisplay, j, _dv, _dvArr2, k;
		            //var _append = (_sp_ProType=="sp_sku_pro_tbodyAppend" ? $("#sp_sku_pro_tbodyAppend") : $("#sp_spu_pro_tbodyAppend"));
		            var _append =$("#"+_sp_ProType+"");
		            _html += "<tr><td "+(_sp_ProType=="sp_sku_pro_tbodyAppend"||_sp_ProType=="sp_sku_pro_tbodyAppend_edit" ? "class='Father_Title'" : "" )+" align='right'>" + (_isRequire == "1" ? "<span style='color:red'>(*)</span>" : "") + _displayName + "：</td><td class='Father_Item"+i+"' align='left'>"; 
		
					if(_sp_ProType=='sp_spu_pro_tbodyAppend'||_sp_ProType=='sp_spu_pro_tbodyAppend_edit'){
						spuInputName.push(_fieldName);
					}else if(_sp_ProType=='sp_sku_pro_tbodyAppend'||_sp_ProType=='sp_sku_pro_tbodyAppend_edit'){
						skuInputName.push(_fieldName);
					}
		           switch (data[z][i].sp_InputType) {
		                case "单选框":
		                    _dvArr = _defaultValue.split(',');
		                    if (_dvArr.length < 2) {
		                        alert("单选框[" + _displayName + "]默认值设置有误，请联系管理员!");
		                        break;
		                    }
		                    _arrDisplay = _dvArr[0].split('|');
		                    _arrValue = _dvArr[1].split('|');
		                    if (_arrDisplay.length != _arrValue.length) {
		                        alert("单选框[" + _displayName + "]默认值设置有误，请联系管理员!");
		                        break;
		                    }
		                    if (_dvArr.length == 3) {
		                        _dv = _dvArr[2];
		                    }
		                    for (j = 0; j < _arrDisplay.length; j++) {
		                        _html += "<input type='radio' " + (_dv == _arrValue[j] ? "checked" : "") + " value='" + _arrValue[j] + "' name='" + _fieldName + "' id='" + _fieldName + "_" + j + "' dataType='" + _valueType + "' isRequired='" + _isRequire + "' maxLength='" + _valueLength + "' label='" + _displayName + "' /><label for='" + _fieldName + "_" + j + "'>" + _arrDisplay[j] + "</label>"
		                    }
		                    break;
		                   
		                case "复选框":
		                    _dvArr = _defaultValue.split(',');
		                    if (_dvArr.length < 2) {
		                        alert("复选框[" + _displayName + "]默认值设置有误，请联系管理员!");
		                        break;
		                    }
		                    _arrDisplay = _dvArr[0].split('|');
		                    _arrValue = _dvArr[1].split('|');
		                    if (_arrDisplay.length != _arrValue.length) {
		                        alert("复选框[" + _displayName + "]默认值设置有误，请联系管理员!");
		                        break;
		                    }
		                    //选中
		                    if (_dvArr.length == 3) {
		                        _dv = _dvArr[2];
		                        _dvArr2 = _dv.split('|');
		                    }
		                    
		                    for (j = 0; j < _arrDisplay.length; j++) {
		                        var _checked = false;
		                        //选中
		                        if(_dvArr2){
			                        for (k = 0; k < _dvArr2.length; k++) {
			                            if (_dvArr2[k] == _arrValue[j]) {
			                                _checked = true;
			                            }
			                        }
		                        }
		                        _html += "<input type='checkbox' " + (_checked ? "checked" : "") + " value='" + _arrValue[j] + "' name='" + _fieldName + "' id='" + _fieldName + "_" + j + "' dataType='" + _valueType + "' isRequired='" + _isRequire + "' maxLength='" + _valueLength + "' label='" + _displayName + "'/><label for='" + _fieldName + "_" + j + "'>" + _arrDisplay[j] + "</label>"
		                    }
		                    break;
		                    
		                case "文本框":
		                    _html += "<input class='textboxcommodity' type='text' id='" + _fieldName + "' name='" + _fieldName + "' value='" + _defaultValue + "' dataType='" + _valueType + "' isRequired='" + _isRequire + "' maxLength='" + _valueLength + "' label='" + _displayName + "'/>"
		                    break;
		                case "下拉框":
		                    _dvArr = _defaultValue.split(',');
		                    if (_dvArr.length < 2) {
		                        alert("下拉框[" + _displayName + "]默认值设置有误，请联系管理员!");
		                        break;
		                    }
		                    _arrDisplay = _dvArr[0].split('|');
		                    _arrValue = _dvArr[1].split('|');
		                    if (_arrDisplay.length != _arrValue.length) {
		                        alert("下拉框[" + _displayName + "]默认值设置有误，请联系管理员!");
		                        break;
		                    }
		                    if (_dvArr.length == 3) {
		                        _dv = _dvArr[2];
		                    }
		                    _html += "<select class='textboxcommodity' name='" + _fieldName + "' id='" + _fieldName + "' dataType='" + _valueType + "' isRequired='" + _isRequire + "' maxLength='" + _valueLength + "' label='" + _displayName + "'>"
		                    _html+="<option selected ></option>";
		                    for (j = 0; j < _arrDisplay.length; j++) {
		                        _html += "<option " + (_dv == _arrValue[j] ? "selected" : "") + " value='" + _arrValue[j] + "'>" + _arrDisplay[j] + "</option>";
		                    }
		                    _html += "</select>"
		                    var map={};
		                    if(_isRequire==1){
		                    	map['isRequire']=1;
		                    }else{
		                    	map['isRequire']=0;
		                    }
		                    map['_fieldName']=_fieldName
		                    isRequireSelect.push(map);
		                    break;	  
		                case "文本域":
		                    _html += "<textarea rows='4' cols='60' id='" + _fieldName + "' name='" + _fieldName + "' dataType='" + _valueType + "' isRequired='" + _isRequire + "' maxLength='" + _valueLength + "' label='" + _displayName + "'>" + _defaultValue + "</textarea>"
		                    break;
		                default:
		                    break;
		            }
		            _html += "</td></tr>";
		            _append.html(_html);
		        }
		}
		
		//spu\sku
		$.each(isRequireSelect,function(i,item){//下拉框	
			if(item.isRequire==1){
				$('#'+item._fieldName+'').combobox({
			    	required: true,
			    	editable :false,
				});
			}else{
				$('#'+item._fieldName+'').combobox({
			    	editable :false,
				});
			}
		});
		
		
		$('#aotucommodity_spu').val(spuInputName.join(','));
		$('#aotucommodity_sku').val(skuInputName.join(','));
}


//获取某一单选/复选组的值
function getGroupValue(groupName) {
    var g = document.getElementsByName(groupName);
    var result = "";
    for (var k = 0; k < g.length; k++) {
        if (g[k].checked) {
            result = g[k].value;
            break;
        }
    }
    return result;
}
//设置某一单选/复选组焦点
function setGroupfocus(groupName) {
    document.getElementsByName(groupName)[0].focus();
}
//辅助函数，用于查找arr中有无o元素
function indexOfNameArr(arr, o) {
    var result = false;
    for (var i = 0; i < arr.length; i++) {
        if (arr[i].name == o.name) {
            result = true;
            break;
        }
    }
    return result;
}



