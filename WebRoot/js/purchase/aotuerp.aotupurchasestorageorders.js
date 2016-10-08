$(function() {
	//进货订单
	$('#purchaseOrders').panel({    
		fit:true,   
		border:false,
	});
	
	/*//添加商品
	$('#addPdSku').linkbutton({    
	    iconCls: 'icon-search',
	    plain:true,
	    onClick:function(){
	    	$('#addPdSku-body').dialog('open');
	    }
	});  
	//移除商品
	$('#removePdSku').linkbutton({    
	     iconCls: 'icon-search',
	    plain:true,
	    onClick:function(){
	    	if(!obj.editRow){
		    	var selectedData=$('#purchaseOrdersPd').datagrid('getSelections');
		    	$.each(selectedData,function(i,item){
		    		$('#purchaseOrdersPd').datagrid('deleteRow',$('#purchaseOrdersPd').datagrid('getRowIndex',item))
		    	})	    		
	    	}else{
	    		$.messager.alert('提示','请保存商品后再进行移除商品操作','warning');
	    	}
	    }
	}); */
	
	//导入订单
	$('#importPurchaseOrders').linkbutton({    
	    /*iconCls: 'icon-search',*/
	    plain:true,
	    onClick:function(){
	    	$('#importPurchaseOrders-body').dialog('open');
	    }
	});  
	
	//保存商品
	$('#savePdSku-pso').linkbutton({    
	    /* iconCls: 'icon-search',*/
	    plain:true,
	    onClick:function(){
	    	$('#purchaseStorageOrdersPd').datagrid('acceptChanges');
	    	obj_pso.editRow=false;
		    $('#savePdSku-pso,#redoPdSku-pso').hide();
	    },
	});
	
	//取消编辑
	$('#redoPdSku-pso').linkbutton({    
	    /* iconCls: 'icon-search',*/
	    plain:true,
	    onClick:function(){
	    	//var data=$('#purchaseStorageOrdersPd').datagrid('getChanges');
		    $('#purchaseStorageOrdersPd').datagrid('rejectChanges');
		    //添加商品
		   /* console.log(data);
		    $.each(data,function(i,item){
				$('#purchaseStorageOrdersPd').datagrid('appendRow',{
					sp_id:item.sp_id,//编号
					sp_idShow:item.sp_idShow,//编号
					sp_Pdspu:item.sp_Pdspu,//商品名称
					sp_Pdproname:item.sp_Pdproname,//规则/型号
					sp_Pdbrand:item.sp_Pdbrand,//品牌
					sp_PdCategory:item.sp_PdCategory,//类别
					sp_Quantity:1,//数量
					sp_UnitPrice:item.sp_UnitPrice,//单价
					sp_TotalPrice:1*item.sp_UnitPrice,//总价
				});
			});*/
		    obj_pso.editRow=false;
		    $('#savePdSku-pso,#redoPdSku-pso').hide();
		},
	}); 
	
	// 导入入货订单dialog
	$('#importPurchaseOrders-body').dialog({
		width : 1000,
		height : 500,
		title : '导入进货订单',
		modal : true,
		closed : true,
		buttons : [ {
			text : '选定',
			/* iconCls:'' */
			handler : function() {
				var selectedData=$('#importPurchaseOrders-datagrid').datagrid('getSelected');
				if(selectedData!=null){
					//console.log(selectedData);
					
					$('#purchaseOrdersNum-pso-hidden').val(selectedData.sp_PurchaseOrder_Id);//进货订单
					$('#purchaseStorageOrdersNum-hidden').val(selectedData.sp_PurchaseOrderId.replace('DD',''));//进货单
					$('#purchaseStorageOrdersNum-hidden').prev().text('进货单号：'+$('#purchaseStorageOrdersNum-hidden').val());
					
					
					$('#spSupplier-pso').textbox('setValue',selectedData.sp_Supplier);//供货商
					$('#spSupplier-pso-hidden').val(selectedData.sp_Supplier_Id);
					
					$('#empl-pso').textbox('setValue',selectedData.sp_Employee);//经办人
					$('#emplId-pso-hidden').val(selectedData.sp_Employee_Id);
					
					$('#depart-pso').val(selectedData.sp_EmployeeDepart);//部门
					
					$('#warehouse-pso').textbox('setValue',selectedData.sp_Warehousename);//仓库
					$('#warehouse-pso-hidden').val(selectedData.sp_Warehousename_Id);//
					
					$('#purchaseStorageOrdersPd').datagrid('loadData',selectedData.sp_PurchaseOrderPds);
				}
				$('#importPurchaseOrders-body').dialog('close');
			}
		}, {
			text : '取消',
			/* iconCls:'' */
			handler : function() {
				$('#importPurchaseOrders-body').dialog('close');
			}
		} ],
		onOpen:function(){
			$('#importPurchaseOrders-datagrid').datagrid({
				url:'purchase_purchaseOrdersListData.action',
			});
		},
	});
	
	//入货订单datagrid
	$('#importPurchaseOrders-datagrid')
	.datagrid(
			{
				singleSelect:true,
				//checkOnSelect:false,
				fitColums : true,
				striped : true,
				rownumbers : true,
				border : false,
				pagination : true,
				pageSize : 10,
				pageList : [ 10, 20, 30, 40, 50 ],
				pageNumber : 1,
				columns : [ [ {
					field : 'sp_PurchaseOrderNumber_Id',
					title : '自动编号',
					width : 100,
					checkbox : true,
				},{
					field : 'sp_Createdate',
					title : '创建日期',
					width : 150,
					formatter:function(value,row,index){//格式化时间
                       var unixTimestamp = new Date(value); 
                       return getTime(value.toString().substring(0,10));   
                   },
				}, {
					field : 'sp_PurchaseOrderId',
					title : '订单编号',
					width : 150,
				},{
					field : 'sp_Planarrivaldate',
					title : '预计到货日期',
					width : 150,
					formatter:function(value,row,index){//格式化时间     
	                    return getTime(value.toString().substring(0,10)); 
	                },
				},{
					field : 'sp_Supplier',
					title : '供货单位',
					width : 250,
				}, {
					field : 'sp_Warehousename',
					title : '仓库',
					width: 150,
				}, {
					field : 'sp_Employee',
					title : '经手人',
					width: 100,
				}, {
					field : 'sp_EmployeeDepart',
					title : '部门',
					width: 100,
				}, {
					field : 'sp_Orderamount',
					title : '订单金额',
					width: 150,
				}, {
					field : 'sp_PurchaseOrdersStatus',
					title : '进度',
					width: 50,
				}, {
					field : 'sp_Remark',
					title : '备注',
					width: 150,
				}] ],
			});
	
	
	//sku商品datagrid
	$('#purchaseStorageOrdersPd')
	.datagrid(
			{
				
				//checkOnSelect:false,
				singleSelect:true,
				fitColums : true,
				striped : true,
				rownumbers : true,
				border : false,
				pagination : true,
				pageSize : 10,
				pageList : [ 10, 20, 30, 40, 50 ],
				pageNumber : 1,
				columns : [ [ {
					field : 'spId',
					title : '自动编号',
					width : 100,
					checkbox : true,
				},{
					field : 'spSkuid',
					title : '商品编号',
					width : 150,
					formatter: function(value,row,index){
						return row.spProductSku.spSkuid;
					},
				}, {
					field : 'spPdspu',
					title : '商品名称',
					width : 150,
					formatter: function(value,row,index){
						return row.spProductSku.spProductBinfo.spPdspu;
					}
				},{
					field : 'spSkupropertiesname',
					title : '规格/型号',
					width : 250,
					formatter: function(value,row,index){
						return row.spProductSku.spSkupropertiesname;
					}
				},{
					field : 'spBrandn',
					title : '品牌名称',
					width : 150,
					formatter: function(value,row,index){
						return row.spProductSku.spProductBinfo.spProductBrands.spBrandn;
					}
				}, {
					field : 'spCategoryn',
					title : '类别',
					width: 150,
					formatter: function(value,row,index){
						return row.spProductSku.spProductBinfo.spProductBrands.spProductCategory.spCategoryn;
					}
				}, {
					field : 'spQuantity',
					title : '数量',
					width: 150,
					editor:{
						type:'numberbox',
						options:{
							required:true,
						},
					},
				}, {
					field : 'spUnitprice',
					title : '单价',
					width: 150,
					editor:{
						type:'numberbox',
						options:{
							required:true,
						},
					},
				}, {
					field : 'spTotalprice',
					title : '金额',
					width: 150,
				}, {
					field : 'sp_Remark',
					title : '备注',
					width: 150,
				}] ],
				onDblClickRow:function(index, row){
					if(!obj_pso.editRow){
						$('#savePdSku-pso,#redoPdSku-pso').show();
						$('#purchaseStorageOrdersPd').datagrid('beginEdit', index);
						var ed = $('#purchaseStorageOrdersPd').datagrid('getEditor', {index:index,field:'spQuantity'});
						$(ed.target).next().children().first().focus();
						obj_pso.editRow=true;
					}
				},
				onAfterEdit:function(index, row, changes){
					$('#purchaseStorageOrdersPd').datagrid('updateRow',{
						index:index,
						row:{
							spTotalprice:row.spQuantity*row.spUnitprice,
						}
					});

				}
			});
	
	//供应商
	$('#spSupplier-pso').textbox({
		buttonText : '选择',
		editable : false,
		required : true,
		onClickButton:function(){
			$('#purchaseOrders-supplier-pso').dialog('open');
		}
	});
	
	// 供应商dialog
	$('#purchaseOrders-supplier-pso').dialog({
		width : 1000,
		height : 500,
		title : '选择供应商',
		modal : true,
		closed : true,
		buttons : [ {
			text : '选定',
			/* iconCls:'' */
			handler : function() {
				var selectedData=$('#purchaseOrders-supplier-pso-datagrid').datagrid('getSelected');
				if(selectedData!=null){
					$('#spSupplier-pso').textbox('setValue',selectedData.sp_SuSup);
					$('#spSupplier-pso-hidden').val(selectedData.sp_id+"-"+selectedData.sp_SuId);
				}
				$('#purchaseOrders-supplier-pso').dialog('close');
			}
		}, {
			text : '取消',
			/* iconCls:'' */
			handler : function() {
				$('#purchaseOrders-supplier-pso').dialog('close');
			}
		} ],
		onOpen:function(){
			$('#purchaseOrders-supplier-pso-datagrid').datagrid({
				url:'spsupplier_listData.action',
			});
		},
	});
	

	// 供应商信息列表
	$('#purchaseOrders-supplier-pso-datagrid').datagrid({
		fit : true,
		fitColums : true,
		striped : true,
		rownumbers : true,
		border : false,
		singleSelect:true,
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		pageNumber : 1,
		columns : [ [ {
			field : 'sp_id',
			title : '自动编号',
			width : 100,
			checkbox : true,
			rowspan : 2,
		}, {
			title : '供应商信息',
			width : 100,
			colspan : 6,
		} ], [ {
			field : 'sp_SuId',
			title : '供应商ID',
			width : 100,
		}, {
			field : 'sp_SuSup',
			title : '商家名称',
			width : 200,
		}, {
			field : 'sp_SuCont',
			title : '商家联系人姓名',
			width : 200,
		}, {
			field : 'sp_SuMobie',
			title : '商家手机号码',
			width : 120,
		}, {
			field : 'sp_SuTel',
			title : '商家联系电话',
			width : 120,
		}, {
			field : 'sp_SuDistrict',
			title : '商家所在地',
			width : 150,
		} ] ]
	});
	
	// 付款日期
	$('#paymentDate').datebox({      
	    formatter : function(date){
			return date.getFullYear()+"-"+(date.getMonth() + 1)+"-"+date.getDate();
		},
		sharedCalendar : '#paymentD',
		editable :false,
		required : true,
	});
	
	$('#paymentD').calendar({
		firstDay : 1,
	});
	
	//仓库
	$('#warehouse-pso').textbox({    
	    buttonText:'选择',   
	    editable : false,
		required : true,
		onClickButton:function(){
			$('#purchaseOrders-warehouse-pso').dialog('open');
		}
	});
	
	// 仓库dialog
	$('#purchaseOrders-warehouse-pso').dialog({
		width : 1000,
		height : 500,
		title : '选择仓库',
		modal : true,
		closed : true,
		buttons : [ {
			text : '选定',
			/* iconCls:'' */
			handler : function() {
				var selectedData=$('#purchaseOrders-warehouse-pso-datagrid').datagrid('getSelected');
				if(selectedData!=null){
					$('#warehouse-pso').textbox('setValue',selectedData.sp_warehousename);
					$('#warehouse-pso-hidden').val(selectedData.sp_id);
				}
				$('#purchaseOrders-warehouse-pso').dialog('close');
			}
		}, {
			text : '取消',
			/* iconCls:'' */
			handler : function() {
				$('#purchaseOrders-warehouse-pso').dialog('close');
			}
		} ],
		onOpen:function(){
			$('#purchaseOrders-warehouse-pso-datagrid').datagrid({
				url:'warehouse_listData.action',
			});
		},
	});
	
		$('#purchaseOrders-warehouse-pso-datagrid').datagrid({
		// checkOnSelect:false,
		fit:true,
		fitColums : true,
		striped : true,
		rownumbers : true,
		border : false,
		singleSelect:true,
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		pageNumber : 1,
		columns : [ [ {
			field : 'sp_id',
			title : '自动编号',
			width : 50,
			checkbox : true,
			rowspan : 2,
		}, {
			title : '仓库信息',
			width : 100,
			colspan : 5,
		} ], [ {
			field : 'sp_idShow',
			title : '仓库编号',
			width : 100,
		}, {
			field : 'sp_warehousename',
			title : '仓库名称',
			width : 200,
		},{
			field : 'sp_pinyin',
			title : '仓库拼音码',
			width : 200,
		},{
			field : 'sp_empwarehouse',
			title : '仓库管理员',
			width : 200,
		},{
			field : 'sp_remark',
			title : '备注',
			width : 100,
		}] ],
	});
	
	
	//经办人
	$('#empl-pso').textbox({    
	    buttonText:'选择',   
	    editable : false,
		required : true,
		onClickButton:function(){
			$('#purchaseOrders-empl-pso').dialog('open');
		}
	});
	
	// 经办人dialog
	$('#purchaseOrders-empl-pso').dialog({
		width : 1000,
		height : 500,
		title : '选择经办人',
		modal : true,
		closed : true,
		buttons : [ {
			text : '选定',
			/* iconCls:'' */
			handler : function() {
				var selectedData=$('#purchaseOrders-empl-pso-datagrid').datagrid('getSelected');
				if(selectedData!=null){
					//员工
					$('#empl-pso').textbox('setValue',selectedData.sp_Eprealname);
					$('#emplId-pso-hidden').val(selectedData.sp_id+"-"+selectedData.sp_EpId);
					//部门
					$('#depart-pso').val(selectedData.sp_EpDepart);
				}
				$('#purchaseOrders-empl-pso').dialog('close');
			}
		}, {
			text : '取消',
			/* iconCls:'' */
			handler : function() {
				$('#purchaseOrders-empl-pso').dialog('close');
			}
		} ],
		onOpen:function(){
			$('#purchaseOrders-empl-pso-datagrid').datagrid({
				url:'sysmm_listData.action',
			});
		},
	});
	
	$('#purchaseOrders-empl-pso-datagrid').datagrid({
		// checkOnSelect:false,
		fit:true,
		fitColums : true,
		striped : true,
		rownumbers : true,
		border : false,
		singleSelect:true,
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		pageNumber : 1,
		columns : [ [ {
			field : 'sp_id',
			title : '自动编号',
			width : 50,
			checkbox : true,
			rowspan : 2,
		}, {
			title : '员工信息',
			width : 100,
			colspan : 4,
		} ], [ {
			field : 'sp_EpId',
			title : '员工编号',
			width : 100,
		}, {
			field : 'sp_Eprealname',
			title : '姓名',
			width : 200,
		}, {
			field : 'sp_Epsex',
			title : '性别',
			width : 200,
		}, {
			field : 'sp_EpDepart',
			title : '所属部门',
			width : 200,
		}] ],
	});
	
	//提交
	$('#purchaseStorageOrdersSubmit').linkbutton({
		 /*iconCls: 'icon-search',*/
	    plain:true,
		onClick:function(){
			if($('#purchaseStorageOrders').form('validate')&&$('#purchaseStorageOrdersPd').datagrid('getData').total>0){
				$.messager.confirm('确定', '你要提交进货单吗', function(flag) {
					if(flag){
						
						//参数封装
						var arr=new Array();
						var map={};
						var temp={},temp1={};
						
						//进货单编号
						map['spPurchaseId']=$('#purchaseStorageOrdersNum-hidden').val();
						
						
						temp={},temp1={};
						//进货订单
						temp['spId']=$('#purchaseOrdersNum-pso-hidden').val();
						map['spAotuerpPurchaseOrders']=temp;
						
						//供货商
						temp={},temp1={};
						temp['spId']=Number($('#spSupplier-pso-hidden').val().split('-')[0]);
						temp['spSuid']=Number($('#spSupplier-pso-hidden').val().split('-')[1]);
						temp1['spSupplierBinfoKey']=temp;
						map['spSupplierBinfo']=temp1;
						
						//经手人
						temp={},temp1={};
						temp['spId']=Number($('#emplId-pso-hidden').val().split('-')[0]);
						temp['spEpid']=Number($('#emplId-pso-hidden').val().split('-')[1]);
						temp1['spEmployeeBinfoKey']=temp;
						map['spEmployeeBinfo']=temp1;

						//仓库
						temp={};
						temp['spId']=Number($('#warehouse-pso-hidden').val());
						map['spAotuerpWarehouseInfo']=temp;
						
						//付款日期
						map['spPaymentdate']=$('#paymentDate').datebox('getValue')+' 00:00:00';
						
						////备注
						map['spRemark']=$('#remark-pso').val();
						
						//sku商品
						$.each($('#purchaseStorageOrdersPd').datagrid('getData').rows,function(i,item){
							var map1={};
							var map2={};
							map2['spSkuid']=item.spProductSku.spSkuid
							map1['spProductSku']=map2;
							map1['spQuantity']=item.spQuantity;
							map1['spUnitprice']=item.spUnitprice;
							arr.push(map1);
						});
						
						map['spAotuerpPurchaseListPds']=arr;
						
						var data={};
						data['spAotuerpPurchaseList']=map;
						
						//console.log(JSON.stringify(data));
						$.ajax({
							url : 'purchase_purchaseStorageOrdersSubmit.action',//请求路径
							type : 'POST',
							contentType:'application/json;charset=utf-8',
							data :JSON.stringify(data),
							beforeSend : function(){
								$.messager.progress({
									text : '正在新增进货单中...',
								});
							},
							success : function(data,response,status){
								$.messager.progress('close');
								if(data.code === 0){//成功  
									$.messager.show({
										title :'提示',
										msg : '进货单新增成功!'
									});
									//刷新当前tab
								    var currTab =  self.parent.$('#tabs').tabs('getSelected'); //获得当前tab
								    var url = currTab.panel('options').href;
								    currTab.panel('refresh', url);
								    
								}else{
									$.messager.alert('进货单新增失败','未知错误，请重试','warning');
								}
							}
						});
					}
				});
			}else{
				$.messager.alert('消息','请填写必填项以及添加进货商品信息');    
			}
	    }
	});
	

	/*// 添加商品
	$('#addPdSku-body').dialog({
		width : 1000,
		height : 500,
		title : '添加商品',
		modal : true,
		closed : true,
		buttons : [ {
			text : '选定',
			 iconCls:'' 
			handler : function() {
				var data=$('#addPdSku-datagrid').datagrid('getSelections');
				$.each(data,function(i,item){
					$('#purchaseOrdersPd').datagrid('appendRow',{
						sp_id:item.sp_id,//编号
						sp_idShow:item.sp_idShow,//编号
						sp_Pdspu:item.sp_Pdspu,//商品名称
						sp_Pdproname:item.sp_Pdproname,//规则/型号
						sp_Pdbrand:item.sp_Pdbrand,//品牌
						sp_PdCategory:item.sp_PdCategory,//类别
						sp_Quantity:1,//数量
						sp_UnitPrice:item.sp_UnitPrice,//单价
						sp_TotalPrice:1*item.sp_UnitPrice,//总价
					});
				});
				$('#addPdSku-body').dialog('close');
			}
		}, {
			text : '取消',
			 iconCls:'' 
			handler : function() {
				$('#addPdSku-body').dialog('close');
			}
		} ],
		onOpen : function() {
			// 加载商品(sku)
			$('#addPdSku-datagrid').datagrid({
				toolbar : '#purchaseOrdersPd_tool',
				url : 'purchase_listData.action',
			});
		}
	});

	//商品
	$('#addPdSku-datagrid').datagrid({
		fitColums : true,
		striped : true,
		border : false,
		fit:true,
		rownumbers : true,
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
		}, {
			field : 'sp_Pdspu',
			title : '商品名称',
			width: 100,
		}, {
			field : 'sp_Pdproname',
			title : '规格/型号',
			width: 200,
		}, {
			field : 'sp_Pdbrand',
			title : '品牌名称',
			width: 100,
		}, {
			field : 'sp_PdCategory',
			title : '类别',
			width: 100,
		}, {
			field : 'sp_Quantity',
			title : '库存数量',
			width: 100,
		}  ] ],
	});*/
	
	obj_pso={
			editRow:false,
	}
	
	/*//功能栏
	purchaseOrdersPd_tool = {

			// 取消所有选定
			redo : function() {
				$('#addPdSku-datagrid').datagrid('unselectAll');
			},
			reload : function() {
				$('#addPdSku-datagrid').datagrid('reload');
			},
	}
	*/
	function getTime(/** timestamp=0 **/) {
        var ts = arguments[0] || 0;
        var t, y, m, d, h, i, s;
        t = ts ? new Date(ts * 1000) : new Date();
        y = t.getFullYear();
        m = t.getMonth() + 1;
        d = t.getDate();
        h = t.getHours();
        i = t.getMinutes();
        s = t.getSeconds();
        // 可根据需要在这里定义时间格式  
        return y + '-' + (m < 10 ? '0' + m : m) + '-' + (d < 10 ? '0' + d : d) + ' ' + (h < 10 ? '0' + h : h) + ':' + (i < 10 ? '0' + i : i) + ':' + (s < 10 ? '0' + s : s);
    }
});


