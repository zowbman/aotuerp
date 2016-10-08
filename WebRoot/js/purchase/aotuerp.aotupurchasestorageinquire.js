$(function() {
	//进货单datagrid
	$('#purchaseStorageInquire-datagrid')
	.datagrid(
			{
				fit:true,
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
				toolbar:'#purchaseStorageInquire_tool',
				url:'purchase_purchaseStorageOrdersListData.action',
				columns : [ [ {
					field : 'sp_PurchaseOrderNumber_Id',
					title : '自动编号',
					width : 100,
					checkbox : true,
				},{
					field : 'sp_Createdate',
					title : '进货日期',
					width : 150,
					formatter:function(value,row,index){//格式化时间
                       var unixTimestamp = new Date(value); 
                       return getTime(value.toString().substring(0,10));   
                   },
				}, {
					field : 'sp_PurchaseStorageOrderId',
					title : '进货单号',
					width : 150,
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
					title : '总金额',
					width: 150,
				}, {
					field : 'sp_PurchaseOrderId',
					title : '进货订单号',
					width: 150,
				},{
					field : 'sp_Remark',
					title : '备注',
					width: 150,
				}] ],
				onSelect:function(index, row){
					$('#purchaseStorageInquirePd-datagrid').datagrid('loadData',row.sp_PurchaseOrderPds);
					
				},
			});
	
	//进货单sku商品datagrid
	$('#purchaseStorageInquirePd-datagrid')
	.datagrid(
			{
				fit:true,
				singleSelect:true,
				//checkOnSelect:false,
				fitColums : true,
				striped : true,
				rownumbers : true,
				border : false,
				columns : [ [{
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
				onClickRow: function (rowIndex, rowData) {
                    $(this).datagrid('unselectRow', rowIndex);
                },
			});
	
	
	//进货单审核窗口
	$('#purchaseStorageInquire-check-dialog').dialog({
		width : 500,
		height : 200,
		title : '单据操作',
		modal : true,
		closed : true,
		buttons : [ {
			text : '审核单据',
			iconCls:'', 
			handler : function() {
				$.messager.confirm('确认', '单据是否审核通过', function(flag) {
					$.ajax({
						type : 'POST',
						url : 'purchase_purchaseStorageOrdersCheck.action',
						data :{
							spPurchaseId : $('#purchaseStorageInquire-datagrid').datagrid('getSelected').sp_PurchaseStorageOrderId,
						},
						beforeSend : function(){
							$.messager.progress({
								text : '正在审核单据数据...',
							});
						},
						success : function(data) {
							$.messager.progress('close');
							if (data.code === 0) {
								$('#purchaseStorageInquire-datagrid').datagrid('reload');
								$('#purchaseStorageInquire-datagrid').datagrid('unselectAll');
								$('#purchaseStorageInquirePd-datagrid').datagrid('loadData',{rows: []})
								$.messager.show({
									title : '提示',
									msg : '单据已被审核成功'
								});
								$('#purchaseStorageInquire-check-dialog').dialog('close');
							} else {
								$.messager.alert('审核单据失败', '未知错误，请重试','warning');
							}
						},
					});
				});
			}
		}, {
			text : '取消',
			iconCls:'',
			handler : function() {
				$('#purchaseStorageInquire-check-dialog').dialog('close');
			}
		} ],
	});  

	//功能栏
	purchaseStorageInquire_tool = {
			//审核
			check:function(){
				var rows = $('#purchaseStorageInquire-datagrid').datagrid('getSelections');
				if (rows.length > 0) {
					$('#purchaseStorageInquire-check-dialog').dialog('open');
					$('#purchaseStorageInquire-check-dialog .purchaseStorageNum span').text(rows[0].sp_PurchaseStorageOrderId);
				}else{
					$.messager.alert('警告操作','单据审核至少选定一条数据！','warning');
				}
			},
			// 取消所有选定
			redo : function() {
				$('#purchaseStorageInquire-datagrid').datagrid('unselectAll');
			},
			reload : function() {
				$('#purchaseStorageInquire-datagrid').datagrid('reload');
			},
	}
	
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


