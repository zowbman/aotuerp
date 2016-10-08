$(function(){
	//导航 数据库 id标识 text名称 state状态 iconCls图标 URL地址 nid父节点，数据库读出来，把转为json数据
	$('#nav').accordion({
		fit:true,
		border:false,
		animate:true,
		plain:true
	});
	
	$.ajax({
	    url: 'aotuerp_nav.action',
	    success: function (data) {
	       $.each(data, function (i, n) {
	            $('#nav').accordion('add', {
	                title: n.text,
	                //click:GetSmallMenu(n.MenuID,n.MenuName),
	               // iconCls: 'icon-menu-' + n.MenuImg.replace(new RegExp('.png'), ''),
	                selected: false,
	                content: '<div style="padding:10px;" id="navTree' + n.id + '"></div>',
	            });
	        
	            $('#navTree'+n.id).tree({
	            	data:n.children,
	            	onLoadSuccess : function(node,data){
	        			if(data){
	        				$(data).each(function(index, value) {
	        					//console.log(value);
	        					if(this.state=='closed'){
	        						$('#navTree'+n.id).tree('expandAll');
	        					}
	        				});
	        			}
	        		},
	        		onClick : function(node){
	        			if(node.url){
	        				if($('#tabs').tabs('exists',node.text)){
	        					$('#tabs').tabs('select',node.text);
	        				}else{
	        					$('#tabs').tabs('add',{
	        						title : node.text,
	        						closable : true,
	        						href : node.url+'.action',
	        						//还可以设置图标
	        						queryParams:{
	        							twoNav:node.id,
	        						}
	        					});
	        				}
	        			}
	        		}
	            });
	        });
	    }
	});   
	
	//选项卡
	$('#tabs').tabs({
		fit : true,
		border : false,
	});
});