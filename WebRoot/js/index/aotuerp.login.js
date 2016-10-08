$(function(){
	//登录界面
	$('#aotuerpLogin').dialog({
		title : '凹凸空间进销存管理系统',
		width : 350,
		height : 180,
		modal : true,
		/*iconCls : ''*/
		buttons : '#aotuerpLoginBtn',
	});
	
	//帐号验证
	$('#aotumanagerAccount').validatebox({
		required : true,
		missingMessage : '请输入系统管理员帐号',
		invalidMessage : '帐号不得为空',
	});
	
	//密码验证
	$('#aotumanagerPassword').validatebox({
		required : true,
		validType : 'length[6,30]',//按需更改
		missingMessage : '请输入系统管理员密码',
		invalidMessage : '密码不得为空，且6-30位',
	});
	
	//加载时判断验证
	if(!$('#aotumanagerAccount').validatebox('isValid')){
		$('#aotumanagerAccount').focus();
	}else if(!$('#aotumanagerPassword').validatebox('isValid')){
		$('#aotumanagerPassword').focus();
	}
	
	//登录
	$('#aotuerpLoginBtn a').click(function(){
		if(!$('#aotumanagerAccount').validatebox('isValid')){
			$('#aotumanagerAccount').focus();
		}else if(!$('#aotumanagerPassword').validatebox('isValid')){
			$('#aotumanagerPassword').focus();
		}else{
			//请求登录ajax
			$.ajax({
				url : 'sysmm_login.action',//请求路径
				type : 'POST',
				data : {
					spEpaccount : $('#aotumanagerAccount').val(),
					spEppassword : $('#aotumanagerPassword').val(),
					},
				beforeSend : function(){
					$.messager.progress({
						text : '正在登录中...',
					});
				},
				success : function(data,response,status){
					$.messager.progress('close');
					console.log(data);
					if(data.code === 0){//成功
						location.href='aotuerp_admin.action';//跳转到首页
					}else{
						$.messager.alert('登录失败','系统管理员用户名或密码错误,请重新输入','warning',function(){
							$('#aotumanagerPassword').select();
						});
					}
				}
			});
		}
	});
});