$(function() {
	//宝贝列表
	$('#aotutreasure-datagrid').datagrid({
		fit : true,
		fitColums : true,
		striped : true,
		rownumbers : true,
		border : false,
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		pageNumber : 1,
		toolbar : '#aotutreasure_tool',
		url:'treasure_treasureShelvesOrderListData.action',
		columns : [ [ {
			field : 'sp_id',
			title : '自动编号',
			width : 100,
			checkbox : true,
		}, {
			field : 'sp_idShow',
			title : '宝贝编号',
			width : 150,
		}, {
			field : 'sp_Treasuretitle',
			title : '宝贝名称',
			width : 150,
		}, {
			field : 'sp_TreasureStatus',
			title : '宝贝状态',
			width : 250,
		},{
			field : 'sp_Opt',
			title : '操作',
			width : 300,
			formatter:function(value,row,index){
                var btn = '<a class="treasureDetail" onclick="treasureDetail(\''+row.sp_id+'\')" href="javascript:void(0)">查看宝贝详情</a>';  
                return btn;  
            },
		} ] ],
		onLoadSuccess:function(data){
			 $('.treasureDetail').linkbutton({text:'查看宝贝详情',plain:true,/*iconCls:'icon-edit'*/});  
		        $('#aotutreasure-datagrid').datagrid('fixColumnSize')
		},
	});
	
	//宝贝详情
	$('#aotutreasure_detail-dialog').dialog({
		width:1000,
		height:600,
		title:'宝贝详情',
		modal:true,
		closed:true,
		
	});
	
	//宝贝订单审核操作
	$('#aotutreasure_check-dialog').dialog({
		width : 500,
		height : 200,
		title : '宝贝上架订单审核操作',
		modal : true,
		closed : true,
		buttons : [ {
			text : '通过',
			/* iconCls:'' */
			handler : function() {
				$.messager.confirm('确定', '是否确认所选宝贝审核通过', function(flag) {
					if (flag) {
						treasureOrderCheck(true);
					}
				});
			}
		}, {
			text : '不通过',
			/* iconCls:'' */
			handler : function() {
				$.messager.confirm('确定', '是否确认所选宝贝审核不通过', function(flag) {
					if (flag) {
						treasureOrderCheck(false);
					}
				});
			}
		} ],
		onOpen:function(){
			var treasureData=$('#aotutreasure-datagrid').datagrid('getSelections');
			var body=$('#aotutreasure_check-dialog').dialog('body');
			var html="";
			html+='<div style="margin:20px;">';
			html+='<h2>审核的宝贝有：</h2>'
			$.each(treasureData,function(i,item){
				html+='<p style="margin:5 0 0 5;">'+item.sp_id+'</p>';
			});
			html+='</div>';
			body.html(html);
		}
	});
	
	
	//功能栏
	aotutreasure_tool = {
			// 审核
			check : function() {
				var rows = $('#aotutreasure-datagrid').treegrid('getSelections');
				if (rows.length > 0) {
					$('#aotutreasure_check-dialog').dialog('open');
					
				}else{
					$.messager.alert('警告操作','审核宝贝上架订单至少选定一条数据！','warning');
				}
			},
			// 取消所有选定
			redo : function() {
				$('#aotutreasure-datagrid').datagrid('unselectAll');
			},
			reload : function() {
				$('#aotutreasure-datagrid').datagrid('reload');
			},
	}
	
});

function treasureDetail(spId){
	$('#aotutreasure_detail').dialog('open');
}

//审核宝贝
function treasureOrderCheck(isPass){
	//封装参数
	var spAotuerpTreasureIds=[];
	$.each($('#aotutreasure-datagrid').datagrid('getSelections'),function(i,item){
		spAotuerpTreasureIds.push(item.sp_id);
	});
	var data={};
	data['spAotuerpTreasureIds']=spAotuerpTreasureIds;
	data['treasureOrderCheck']=isPass;
	$.ajax({
		type : 'POST',
		url : 'treasure_treasureShelvesOrderCheck.action',
		contentType:'application/json;charset=utf-8',
		data : JSON.stringify(data),
		success : function(data) {
			if (data.code === 0){
				$.messager.show({
					title : '提示',
					msg : '所选宝贝已审核通过，允许发布此宝贝任务!'
				});
				$('#aotutreasure_check-dialog').dialog('close');
				$('#aotutreasure-datagrid').datagrid('reload');
			} else {
				$.messager.alert('审核失败', '未知错误，请重试',
						'warning');
			}
		},
	});
}

