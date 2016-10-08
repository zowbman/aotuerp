$(function() {
	//进货订单
	$('#purchaseOrders').panel({    
		fit:true,   
		border:false,
	});
	
	//添加商品
	$('#addPdSku').linkbutton({    
	    /*iconCls: 'icon-search',*/
	    plain:true,
	    onClick:function(){
	    	$('#addPdSku-body').dialog('open');
	    }
	});  
	//移除商品
	$('#removePdSku').linkbutton({    
	    /* iconCls: 'icon-search',*/
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
	}); 
	
	//保存商品
	$('#savePdSku').linkbutton({    
	    /* iconCls: 'icon-search',*/
	    plain:true,
	    onClick:function(){
	    	$('#purchaseOrdersPd').datagrid('acceptChanges');
	    	obj.editRow=false;
		    $('#savePdSku,#redoPdSku').hide();
	    },
	});
	
	//取消编辑
	$('#redoPdSku').linkbutton({    
	    /* iconCls: 'icon-search',*/
	    plain:true,
	    onClick:function(){
	    	var data=$('#purchaseOrdersPd').datagrid('getChanges');
		    $('#purchaseOrdersPd').datagrid('rejectChanges');
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
		    obj.editRow=false;
		    $('#savePdSku,#redoPdSku').hide();
		},
	}); 
	
	//sku商品datagrid
	$('#purchaseOrdersPd')
	.datagrid(
			{
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
					field : 'sp_Pdproname',
					title : '规格/型号',
					width : 250,
				},{
					field : 'sp_Pdbrand',
					title : '品牌名称',
					width : 150,
				}, {
					field : 'sp_PdCategory',
					title : '类别',
					width: 150,
				}, {
					field : 'sp_Quantity',
					title : '数量',
					width: 150,
					editor:{
						type:'numberbox',
						options:{
							required:true,
						},
					},
				}, {
					field : 'sp_UnitPrice',
					title : '单价',
					width: 150,
					editor:{
						type:'numberbox',
						options:{
							required:true,
						},
					},
				}, {
					field : 'sp_TotalPrice',
					title : '金额',
					width: 150,
				}, {
					field : 'sp_Remark',
					title : '备注',
					width: 150,
				}] ],
				onDblClickRow:function(index, row){
					if(!obj.editRow){
						$('#savePdSku,#redoPdSku').show();
						$('#purchaseOrdersPd').datagrid('beginEdit', index);
						var ed = $('#purchaseOrdersPd').datagrid('getEditor', {index:index,field:'sp_Quantity'});
						$(ed.target).next().children().first().focus();
						obj.editRow=true;
					}
				},
				onAfterEdit:function(index, row, changes){
					$('#purchaseOrdersPd').datagrid('updateRow',{
						index:index,
						row:{
							sp_TotalPrice:row.sp_Quantity*row.sp_UnitPrice,
						}
					});

				}
			});
	
	//供应商
	$('#spSupplier').textbox({
		buttonText : '选择',
		editable : false,
		required : true,
		onClickButton:function(){
			$('#purchaseOrders-supplier').dialog('open');
		}
	});
	
	// 供应商dialog
	$('#purchaseOrders-supplier').dialog({
		width : 1000,
		height : 500,
		title : '选择供应商',
		modal : true,
		closed : true,
		buttons : [ {
			text : '选定',
			/* iconCls:'' */
			handler : function() {
				var selectedData=$('#purchaseOrders-supplier-datagrid').datagrid('getSelected');
				$('#spSupplier').textbox('setValue',selectedData.sp_SuSup);
				$('#spSupplier-hidden').val(selectedData.sp_id+"-"+selectedData.sp_SuId);
				$('#purchaseOrders-supplier').dialog('close');
			}
		}, {
			text : '取消',
			/* iconCls:'' */
			handler : function() {
				$('#purchaseOrders-supplier').dialog('close');
			}
		} ],
		onOpen:function(){
			$('#purchaseOrders-supplier-datagrid').datagrid({
				url:'spsupplier_listData.action',
			});
		},
	});
	

	// 供应商信息列表
	$('#purchaseOrders-supplier-datagrid').datagrid({
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
	
	// 预计到货日期
	$('#planArrivalDate').datebox({      
	    formatter : function(date){
			return date.getFullYear()+"-"+(date.getMonth() + 1)+"-"+date.getDate();
		},
		sharedCalendar : '#planAD',
		editable :false,
		required : true,
	});
	
	$('#planAD').calendar({
		firstDay : 1,
	});
	
	//合同号
	$('#orderContractId').textbox({   
		required : true,
	});
	
	//仓库
	$('#warehouse').textbox({    
	    buttonText:'选择',   
	    editable : false,
		required : true,
		onClickButton:function(){
			$('#purchaseOrders-warehouse').dialog('open');
		}
	});
	
	// 仓库dialog
	$('#purchaseOrders-warehouse').dialog({
		width : 1000,
		height : 500,
		title : '选择仓库',
		modal : true,
		closed : true,
		buttons : [ {
			text : '选定',
			/* iconCls:'' */
			handler : function() {
				var selectedData=$('#purchaseOrders-warehouse-datagrid').datagrid('getSelected');
				$('#warehouse').textbox('setValue',selectedData.sp_warehousename);
				$('#warehouse-hidden').val(selectedData.sp_id);
				$('#purchaseOrders-warehouse').dialog('close');
			}
		}, {
			text : '取消',
			/* iconCls:'' */
			handler : function() {
				$('#purchaseOrders-warehouse').dialog('close');
			}
		} ],
		onOpen:function(){
			$('#purchaseOrders-warehouse-datagrid').datagrid({
				url:'warehouse_listData.action',
			});
		},
	});
	
		$('#purchaseOrders-warehouse-datagrid').datagrid({
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
	$('#empl').textbox({    
	    buttonText:'选择',   
	    editable : false,
		required : true,
		onClickButton:function(){
			$('#purchaseOrders-empl').dialog('open');
		}
	});
	
	// 经办人dialog
	$('#purchaseOrders-empl').dialog({
		width : 1000,
		height : 500,
		title : '选择经办人',
		modal : true,
		closed : true,
		buttons : [ {
			text : '选定',
			/* iconCls:'' */
			handler : function() {
				var selectedData=$('#purchaseOrders-empl-datagrid').datagrid('getSelected');
				//员工
				$('#empl').textbox('setValue',selectedData.sp_Eprealname);
				$('#emplId-hidden').val(selectedData.sp_id+"-"+selectedData.sp_EpId);
				//部门
				$('#depart').val(selectedData.sp_EpDepart);
				
				$('#purchaseOrders-empl').dialog('close');
			}
		}, {
			text : '取消',
			/* iconCls:'' */
			handler : function() {
				$('#purchaseOrders-empl').dialog('close');
			}
		} ],
		onOpen:function(){
			$('#purchaseOrders-empl-datagrid').datagrid({
				url:'sysmm_listData.action',
			});
		},
	});
	
	$('#purchaseOrders-empl-datagrid').datagrid({
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
	$('#purchaseOrdersSubmit').linkbutton({
		 /*iconCls: 'icon-search',*/
	    plain:true,
		onClick:function(){
			if($('#purchaseOrders').form('validate')&&$('#purchaseOrdersPd').datagrid('getData').total>0){
				$.messager.confirm('确定', '你要提交进货订单吗', function(flag) {
					if(flag){
						//参数封装
						var arr=new Array();
						
						var map={};
						var temp={},temp1={};
						
						//订单编号
						temp['spId']=Number($('#purchaseOrdersNum-hidden').val());
						map['spAotuerpPurchaseOrdersNumbers']=temp;
						
						temp={},temp1={};
						//供货商
						temp['spId']=Number($('#spSupplier-hidden').val().split('-')[0]);
						temp['spSuid']=Number($('#spSupplier-hidden').val().split('-')[1]);
						temp1['spSupplierBinfoKey']=temp;
						map['spSupplierBinfo']=temp1;
						
						
						temp={},temp1={};
						temp['spId']=Number($('#emplId-hidden').val().split('-')[0]);
						temp['spEpid']=Number($('#emplId-hidden').val().split('-')[1]);
						temp1['spEmployeeBinfoKey']=temp;
						map['spEmployeeBinfo']=temp1;
						
						//合同号
						map['spOrdercontractid']=$('#orderContractId').val();
						
						//仓库
						temp={};
						temp['spId']=Number($('#warehouse-hidden').val());
						map['spAotuerpWarehouseInfo']=temp;
						//到货日期
						//map['spPlanarrivaldate']=$('#planArrivalDate').datebox('getValue')+" 00:00:00";
						map['spPlanarrivaldate']='2015-11-11 00:00:00';
						////备注
						map['spRemark']=$('#remark').val();
						
						$.each($('#purchaseOrdersPd').datagrid('getData').rows,function(i,item){
							var map1={};
							var map2={};
							map2['spSkuid']=item.sp_id
							map1['spProductSku']=map2;
							map1['spQuantity']=item.sp_Quantity;
							map1['spUnitprice']=item.sp_UnitPrice;
							arr.push(map1);
						});
						map['spAotuerpPurchaseOrderPds']=arr;
						var data={};
						data['spAotuerpPurchaseOrders']=map;
						//console.log(JSON.stringify(data));
						
						$.ajax({
							url : 'purchase_purchaseOrdersSubmit.action',//请求路径
							type : 'POST',
							contentType:'application/json;charset=utf-8',
							data :JSON.stringify(data),
							beforeSend : function(){
								$.messager.progress({
									text : '正在新增进货订单中...',
								});
							},
							success : function(data,response,status){
								$.messager.progress('close');
								if(data.code === 0){//成功
									//$('#purchaseOrders').form('reset');
									//$('#purchaseOrdersPd').datagrid('loadData', { total: 0, rows: [] });  
									$.messager.show({
										title :'提示',
										msg : '进货订单新增成功!'
									});
									
									//刷新当前tab
								    var currTab =  self.parent.$('#tabs').tabs('getSelected'); //获得当前tab
								    var url = currTab.panel('options').href;
								    currTab.panel('refresh', url);
								    
								}else{
									$.messager.alert('进货订单新增失败','未知错误，请重试','warning');
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
	

	// 添加商品
	$('#addPdSku-body').dialog({
		width : 1000,
		height : 500,
		title : '添加商品',
		modal : true,
		closed : true,
		buttons : [ {
			text : '选定',
			/* iconCls:'' */
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
			/* iconCls:'' */
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
	});
	
	obj={
			editRow:false,
	}
	//功能栏
	purchaseOrdersPd_tool = {

			// 取消所有选定
			redo : function() {
				$('#addPdSku-datagrid').datagrid('unselectAll');
			},
			reload : function() {
				$('#addPdSku-datagrid').datagrid('reload');
			},
	}
});


