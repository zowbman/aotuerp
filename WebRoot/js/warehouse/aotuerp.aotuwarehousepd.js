$(function() {
	//仓库tree
	$('#warehouseNav').tree({
		url : 'warehouse_warehouseTree.action',//请求url
		lines : true,
		onClick : function(node){
			var selectedId=node.id;
			$('#aotuerpSkupd').datagrid('load', { 'spId': selectedId });
		}
	});
	
	//sku商品列表
	$('#aotuerpSkupd')
			.datagrid(
					{
						url : 'warehouse_warehousePdListData.action',// url请求地址
						fit : true,
						fitColums : true,
						striped : true,
						rownumbers : true,
						border : false,
						pagination : true,
						pageSize : 10,
						pageList : [ 10, 20, 30, 40, 50 ],
						pageNumber : 1,
						toolbar : '#aotuerpSkupd_tool',
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
							field : 'sp_Pdspu',
							title : '商品名称',
							width : 200,
						}, {
							field : 'sp_Pdproname',
							title : '规格/型号',
							width : 200,
						}, {
							field : 'sp_Pdbrand',
							title : '品牌',
							width : 200,
						}, {
							field : 'sp_PdCategory',
							title : '类别',
							width : 200,
						}, {
							field : 'sp_Quantity',
							title : '库存数量',
							width : 200,
						}] ]
					});


	//功能栏
	aotuerpSkupd_tool = {
			// 取消所有选定
			redo : function() {
				$('#aotuerpSkupd').datagrid('unselectAll');
			},
			reload : function() {
				$('#aotuerpSkupd').datagrid('reload');
			},
	}
});