$(function() {
	
	//发布宝贝任务panel
	$('#treasureTaskRelease-panel').panel({
		fit:true,
		border:false,
	});
	//发布宝贝任务表单panel
	$('#treasureTaskRelease_form').panel({
		//fit:true,
		border:false,
	})
	
	//导入宝贝按钮
	$('#importTreasureForTask').linkbutton({    
	    /*iconCls: 'icon-search',*/
	    plain:true,
	    onClick:function(){
	    	$('#importTreasureForTask-dialog').dialog('open');
	    },
	});  
	
	//导入宝贝dialog
	$('#importTreasureForTask-dialog').dialog({
		width : 1000,
		height : 500,
		title : '选择宝贝',
		modal : true,
		closed : true,
		buttons : [ {
			text : '选择',
			/* iconCls:'' */
			handler : function() {
				$('#treasureTaskRelease_form').panel({
					href:'treasuretask_releaseForm.action',
					queryParams:{
						'spAotuerpTreasureInfo.spId':$('#importTreasureForTask-datagrid').datagrid('getSelected').sp_id,
					},
					onLoad:function(){
						$('#importTreasureForTask-dialog').dialog('close');
						//初始化奖励第一项的input
						//查询rewards-table下的全部input
						$.each($('.rewards-table').find('input'),function(i,item){
							$(item).textbox({    
								required: true,
								width:175,
							});
						});
						
						//任务时效
						$('#taskAging').textbox({    
							required: true,
							width:175,
						});
						
						//发布任务按钮
						$('#treasureTaskBtn').linkbutton({
							onClick:function(){
								treasureTaskBtn();
							},
						});
						
					}
				});
			}
		}, {
			text : '取消',
			/* iconCls:'' */
			handler : function() {
				$('#importTreasureForTask-dialog').dialog('close');
			}
		} ],
		onOpen:function(){
			$('#importTreasureForTask-datagrid').datagrid({
				url:'treasuretask_importTreasureData.action',
			});
		},
	});

	//导入宝贝datagrid
	$('#importTreasureForTask-datagrid')
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
					title : '宝贝编号',
					width : 150,
				},{
					field : 'sp_Treasuretitle',
					title : '宝贝名称',
					width : 250,
				},{
					field : 'sp_TreasureStatus',
					title : '宝贝状态',
					width : 150,
				}] ],
			});
	
	//发布宝贝任务
	function treasureTaskBtn(){
		//获取当前选择的tab项
		var selectedTab=$('#sp_task_mode-tabs').tabs('getSelected');
		//校验tab里面的input
		if(selectedTab.panel('body').form('validate')){
			//有效天数
			if($('#taskAging').textbox('isValid')){
				//封装json
				var treasureTask={};
				
				var temp={};
				//获取宝贝id
				temp['spId']=$('#treasureId').val();
				treasureTask['spAotuerpTreasureInfo']=temp
				
				//获取提成奖励
				var treasureTaskLevels=[];
				//获取任务种类id
				var taskModeId=selectedTab.find('#taskModeId').val();
				//获取是否区间
				var isInterval=selectedTab.find('#isInterval').val()
				//获取table行数
				var aTr = $('#rewards-table'+taskModeId).find('tr');
				for(var i=0;i<aTr.length-1;i++){
					var treasureTaskLevel={};
					//销售数量是否为区间形式
					if(isInterval==0){//不是区间
						//task任务模式id_sale_qantity_i
						treasureTaskLevel['spRewardlevel']=$('#task'+taskModeId+"_sale_qantity_"+i).textbox('getValue');
						treasureTaskLevels.push(treasureTaskLevel);
					}else{//是区间
						//task任务模式id_sale_qantity_min_i(最小)
						//console.log($('#task'+taskModeId+"_sale_qantity_min_"+i).textbox('getValue'));
						//task任务模式id_sale_qantity_max_i(最大)
						//console.log($('#task'+taskModeId+"_sale_qantity_max_"+i).textbox('getValue'));
						//treasureTaskLevel['spRewardlevel']=$('#task'+taskModeId+"_sale_qantity_min_"+i).textbox('getValue')+","+$('#task'+taskModeId+"_sale_qantity_max_"+i).textbox('getValue');
					}
					
					var treasureTaskLevelProfits=[];
					var treasureTaskLevelProfit={};
					//提成利润（百分比）
					//task任务模式id_sale_profit_percentage_i
					treasureTaskLevelProfit['spProfitpercentage']=$('#task'+taskModeId+"_sale_profit_percentage_"+i).textbox('getValue');
					//利润（元）
					//task任务模式id_sale_profit_yuan_i
					treasureTaskLevelProfit['spProfityuan']=$('#task'+taskModeId+"_sale_profit_yuan_"+i).textbox('getValue');
					treasureTaskLevelProfits.push(treasureTaskLevelProfit);
					treasureTaskLevel['spAotuerpTreasureTaskLevelProfits']=treasureTaskLevelProfits
				}
				treasureTask['spAotuerpTreasureTaskLevels']=treasureTaskLevels;
				
				//任务模式
				temp={};
				temp['spId']=taskModeId;
				treasureTask['spAotuerpTreasureTaskMode']=temp;
				
				//获取任务时效
				treasureTask['spTaskaging']=$('#taskAging').textbox('getValue');
				
				var data={};
				data['spAotuerpTreasureTask']=treasureTask;
				console.log(JSON.stringify(data));
				//ajax提交
				$.ajax({
					type : 'POST',
					url : 'treasuretask_releaseSubmit.action',
					contentType:'application/json;charset=utf-8',
					data :JSON.stringify(data),
					success : function(data) {
						if (data.code === 0) {
							$.messager.show({
								title : '提示',
								msg : '发布宝贝任务成功，请在凹凸空间网站查看'
							});
							//刷新当前tab
						    var currTab =  self.parent.$('#tabs').tabs('getSelected'); //获得当前tab
						    var url = currTab.panel('options').href;
						    currTab.panel('refresh', url);
							
						} else {
							$.messager.alert('发布宝贝任务失败', '未知错误，请重试','warning');
						}
					}
				});
			}else{
				$('#taskAging').textbox('textbox').focus();
			}
		}
	}
	
});

//添加奖励行
function addReward (taskModeId,isInterval){
	var aTr = document.getElementById('rewards-table'+taskModeId).getElementsByTagName('tr');
	var _task_sale_qantity_='';
		$(isInterval == 0 ? _task_sale_qantity_=('<td>' + '<input type="text" id="task'+taskModeId+'_sale_qantity_'+(aTr.length-1)+'">' + '  件</td>') : _task_sale_qantity_=('<td>' + '<input type="text" id="task'+taskModeId+'_sale_qantity_min_'+(aTr.length-1)+'">' +' 至 '+'<input type="text" id="task'+taskModeId+'_sale_qantity_max_'+(aTr.length-1)+'">'+'  件</td>'));
    var aDlist = '<tr>' +
    		_task_sale_qantity_+
            '<td>' + '<input type="text" id="task'+taskModeId+'_sale_profit_percentage_'+(aTr.length-1)+'">' + '  %</td>' +
            '<td>' + '<input type="text" id="task'+taskModeId+'_sale_profit_yuan_'+(aTr.length-1)+'">' + '  元</td>' +
            '<td align="center"><span ><a style="color:#333;" onclick="removeReward(this)" href="javascript:;">删除</a></span></td>' +
      '</tr>';
    
    $('#rewards-table'+taskModeId).find('tbody').append(aDlist);
    
    $.each($(aDlist).find('input'),function(i,item){
    	$('#'+$(item).attr('id')).textbox({
    		required: true,
    		width:175,
    	});
    });
    
    /*$('.add-icon').click(function(){
            //$('#addCont').find('tr').css('display','table-row');
            $('#addCont').find('tbody').append(aDlist);        
    });*/
    
    //$('.del-list').live('click',function(){
           // $(this).parent().parent().remove();
            //当添加若干行再删除到最后一行时，怎么让最上面的标题行也消失（高手请补充完下面代码，谢谢！） 
    // });
   
   //$(父节点).on("事件","动态生成子节点",handler) 
    /*$('#addCont').on('click','.del-list',function(){
        $(this).parent().parent().remove();
        //当添加若干行再删除到最后一行时，怎么让最上面的标题行也消失（高手请补充完下面代码，谢谢！） 
    }); */                                               
}
//删除奖励行
function removeReward(obj){
	// console.log($(this));
	$(obj).parent().parent().parent().remove();
}


