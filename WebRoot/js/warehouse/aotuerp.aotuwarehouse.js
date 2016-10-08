$(function() {
	//仓库列表
	$('#aotuwarehouse')
			.datagrid(
					{
						url : 'warehouse_listData.action',// url请求地址
						fit : true,
						fitColums : true,
						striped : true,
						rownumbers : true,
						border : false,
						pagination : true,
						pageSize : 10,
						pageList : [ 10, 20, 30, 40, 50 ],
						pageNumber : 1,
						toolbar : '#aotuwarehouse_tool',
						columns : [ [ {
							field : 'sp_id',
							title : '自动编号',
							width : 100,
							checkbox : true,
							rowspan : 2,
						}, {
							title : '仓库信息',
							width : 100,
							colspan : 5,
						} ], [ {
							field : 'sp_idShow',
							title : '仓库编号',
							width : 200,
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
	
	//添加商品
	$('#aotuwarehouse_add').dialog({
		width:700,
		height:330,
		title:'新增仓库',
		modal:true,
		closed:true,
		buttons:[{
			text:'提交',
			/*iconCls:''*/
			handler: function(){
				if($('#aotuwarehouse_add').form('validate')){
					if($('#empwarehouse').combotree('getValue')!=0){
							var empwarehouse=$('#empwarehouse').combotree('getValue');
							var map={};
							$('input[name="empSpId"]').val(empwarehouse.split('-')[0]);
							$('input[name="empSpEpId"]').val(empwarehouse.split('-')[1]);
							$.ajax({
							url : 'warehouse_add.action',//请求路径
							type : 'POST',
							data :$('#aotuwarehouse_add').serialize(),
							beforeSend : function(){
								$.messager.progress({
									text : '正在新增中...',
								});
							},
							success : function(data,response,status){
								$.messager.progress('close');
								if(data.code === 0){//成功
									$('#aotuwarehouse').datagrid('reload');
									$('#aotuwarehouse_add').dialog('close').form('reset');
									$.messager.show({
										title :'提示',
										msg : '仓库新增成功!'
									});
								}else{
									$.messager.alert('新增失败','未知错误，请重试','warning',function(){
										$('input[name="spWarehousename"]').select();
									});
								}
							}
						});
					}else{
						$.messager.alert('消息','请选择仓库管理员');    
					}
			}
		}
		},{
			text:'取消',
			/*iconCls:''*/
			handler: function(){
				$('#aotuwarehouse_add').dialog('close').form('reset');
			}
		}],
	});

	//修改仓库信息
	$('#aotuwarehouse_edit').dialog({
		width:700,
		height:330,
		title:'修改仓库信息',
		modal:true,
		closed:true,
		buttons:[{
			text:'提交',
			/*iconCls:''*/
			handler: function(){
				if($('#aotuwarehouse_edit').form('validate')){
					if($('#empwarehouse_edit').combotree('getValue')!=0){
						var empwarehouse=$('#empwarehouse_edit').combotree('getValue');
						var map={};
						$('input[name="empSpId"]').val(empwarehouse.split('-')[0]);
						$('input[name="empSpEpId"]').val(empwarehouse.split('-')[1]);
						$.ajax({
							url : 'warehouse_editSubmit.action',//请求路径
							type : 'POST',
							data : $('#aotuwarehouse_edit').serialize(),
							beforeSend : function(){
								$.messager.progress({
									text : '正在修改中...',
								});
							},
							success : function(data,response,status){
								$.messager.progress('close');
								if(data.code === 0){//成功
									$('#aotuwarehouse').datagrid('reload');
									$('#aotuwarehouse_edit').dialog('close').form('reset');
									$.messager.show({
										title :'提示',
										msg : '仓库信息修改成功!'
									});
								}else{
									$.messager.alert('修改失败','未知错误，请重试','warning',function(){
										$('input[name="spWarehousename"]').select();
									});
								}
							}
						});
					}else{
						$.messager.alert('消息','请选择仓库管理员');    
					}
				}
			}
		},{
			text:'取消',
			/*iconCls:''*/
			handler: function(){
				$('#aotuwarehouse_edit').dialog('close').form('reset');
			}
		}]
	});

	//验证仓库名称
	$('input[name="spWarehousename"]').validatebox({
		required: true,
		//validType : 'length[6,18]',
		//missingMessage: '请输入仓库名称',
		//invalidMessage :'仓库名称在6-18位',
	});

	$('input[name="spWarehousename"]').blur(function(){
		//设置拼音
		$('input[name="spPinyin"]').val(makePy($(this).val()));
	});

	//仓库管理员选择
	$('#empwarehouse').combotree({
		url : 'warehouse_empWarehouseTreeData.action',//请求url
		required : true,
		lines : true,
		//一刷新全部展开
		onLoadSuccess : function(node,data){
			$('#empwarehouse').combotree('setValue',0);
		},
	});

	

	//功能栏
	aotuwarehouse_tool = {
			add : function(){
				$('#aotuwarehouse_add').dialog('open');
			},
			edit : function(){
				var rows =$('#aotuwarehouse').datagrid('getSelections');
				if(rows.length>1){
					$.messager.alert('警告操作','编辑记录只能选定一条数据！','warning');
				}else if(rows.length==1){
					$.ajax({
						type:'POST',
						url : 'warehouse_edit.action',
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
							if(data[0].code==0){
								var obj=$.parseJSON(data[0].data);
								$('#aotuwarehouse_edit').form('load',obj[0]).dialog('open');
								var empwarehouse=0;
								if(obj[0].spEmployeeBinfos.length>0){
									empwarehouse = obj[0].spEmployeeBinfos[0].spEmployeeBinfoKey.spId+"-"+obj[0].spEmployeeBinfos[0].spEmployeeBinfoKey.spEpid;
								}
								
								//仓库管理员更改
								$('#empwarehouse_edit').combotree({
									url : 'warehouse_empWarehouseTreeData.action',//请求url
									required : true,
									lines : true,
									//一刷新全部展开
									onLoadSuccess : function(node,data){
										$.each(data,function(i,item){
											if(empwarehouse==item.id){
												$('#empwarehouse_edit').combotree('setValue',item.id);
											}
										});
										
									},
								});
							}else{
								$.messager.alert('获取数据失败','未知错误，请重试','warning');
							}
						},
					});
				}else if(rows.length==0){
					$.messager.alert('警告操作','编辑记录至少选定一条数据！','warning');
				}
			},
			remove : function() {
				var rows = $('#aotuwarehouse').datagrid('getSelections');
				if (rows.length > 0) {
					$.messager.confirm('确定', '你要删除所选的<strong>' + rows.length
							+ '</strong>条记录吗', function(flag) {
						if (flag) {
							var warehouseIds=[];
							for(var i=0;i<rows.length;i++){
								warehouseIds.push(rows[i].sp_id);
							}
							var map={};
							map['warehouseIds']=warehouseIds;
							$.ajax({
								type : 'POST',
								url : 'warehouse_delete.action',
								contentType:'application/json;charset=utf-8',
								data : JSON.stringify(map),
								beforeSend : function() {
									$('#aotuwarehouse').datagrid('loading');
								},
								success : function(data) {
									if (data.code === 0) {
										$('#aotuwarehouse').datagrid('loaded');
										$('#aotuwarehouse').datagrid('reload');
										$('#aotuwarehouse').datagrid('unselectAll');
										$.messager.show({
											title : '提示',
											msg : '仓库已被删除成功'
										});
									} else {
										$('#aotuwarehouse').datagrid('loaded');
										$('#aotuwarehouse').datagrid('reload');
										$('#aotuwarehouse')
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
				$('#aotuwarehouse').datagrid('unselectAll');
			},
			reload : function() {
				$('#aotuwarehouse').datagrid('reload');
			}
	}
});
