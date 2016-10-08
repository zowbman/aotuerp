$(function() {
	//任务管理模块功能导航
	$('#treasureTaskNav').tree({
		url : 'treasuretask_nav.action',//请求url
		lines : true,
		onClick : function(node){
			if(node.url){
				if($('#treasureTaskMtabs').tabs('exists',node.text)){
					$('#treasureTaskMtabs').tabs('select',node.text);
				}else{
					$('#treasureTaskMtabs').tabs('add',{
						title : node.text,
						closable : true,
						href : node.url+'.action',
					});
				}
			}
		},
		queryParams:{
			twoNav:$('input[name="treasureTaskNav"]').val(),
		},
	});
	
	//任务管理tabs
	$('#treasureTaskMtabs').tabs({
		fit : true,
		border : false,
		/*plain:true,
		pill:true,*/
	});
	
});