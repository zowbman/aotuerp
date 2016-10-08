//扩展easyui表单的验证
$
		.extend(
				$.fn.validatebox.defaults.rules,
				{
					//验证汉字  
					CHS : {
						validator : function(value) {
							return /^[\u0391-\uFFE5]+$/.test(value);
						},
						message : '请输入汉字'
					},
					//移动手机号码验证    
					Mobile : {//value值为文本框中的值  
						validator : function(value) {
							var reg = /^1[3|4|5|8|9]\d{9}$/;
							return reg.test(value);
						},
						message : '请输入有效的手机号码'
					},
					//国内邮编验证   
					ZipCode : {
						validator : function(value) {
							var reg = /^[0-9]\d{5}$/;
							return reg.test(value);
						},
						message : 'The zip code must be 6 digits and 0 began.'
					},
					//数字
					Number : {
						validator : function(value) {
							var reg = /^[0-9]*$/;
							return reg.test(value);
						},
						message : '请输入数字'
					},
					IdCard : {
						validator : function(value) {
							var reg = /^(\d{6})(18|19|20)?(\d{2})([01]\d)([0123]\d)(\d{3})(\d|X)?$/;
							return reg.test(value);
						},
						message : '请输入有效的身份证号码'
					},
					//银行卡
					BankId : {
						validator : function(value) {
							return luhmCheck(value);
						},
						message : '请输入有效银行卡号码'
					}
				})

//Create Time:  07/28/2011
//Operator:     liuzw
//Description:  银行卡号Luhm校验

//Luhm校验规则：16位银行卡号（19位通用）:

// 1.将未带校验位的 15（或18）位卡号从右依次编号 1 到 15（18），位于奇数位号上的数字乘以 2。
// 2.将奇位乘积的个十位全部相加，再加上所有偶数位上的数字。
// 3.将加法和加上校验位能被 10 整除。
function luhmCheck(bankno) {
	var lastNum = bankno.substr(bankno.length - 1, 1);//取出最后一位（与luhm进行比较）

	var first15Num = bankno.substr(0, bankno.length - 1);//前15或18位
	var newArr = new Array();
	for ( var i = first15Num.length - 1; i > -1; i--) { //前15或18位倒序存进数组
		newArr.push(first15Num.substr(i, 1));
	}
	var arrJiShu = new Array(); //奇数位*2的积 <9
	var arrJiShu2 = new Array(); //奇数位*2的积 >9

	var arrOuShu = new Array(); //偶数位数组
	for ( var j = 0; j < newArr.length; j++) {
		if ((j + 1) % 2 == 1) {//奇数位
			if (parseInt(newArr[j]) * 2 < 9)
				arrJiShu.push(parseInt(newArr[j]) * 2);
			else
				arrJiShu2.push(parseInt(newArr[j]) * 2);
		} else
			//偶数位
			arrOuShu.push(newArr[j]);
	}

	var jishu_child1 = new Array();//奇数位*2 >9 的分割之后的数组个位数
	var jishu_child2 = new Array();//奇数位*2 >9 的分割之后的数组十位数
	for ( var h = 0; h < arrJiShu2.length; h++) {
		jishu_child1.push(parseInt(arrJiShu2[h]) % 10);
		jishu_child2.push(parseInt(arrJiShu2[h]) / 10);
	}

	var sumJiShu = 0; //奇数位*2 < 9 的数组之和
	var sumOuShu = 0; //偶数位数组之和
	var sumJiShuChild1 = 0; //奇数位*2 >9 的分割之后的数组个位数之和
	var sumJiShuChild2 = 0; //奇数位*2 >9 的分割之后的数组十位数之和
	var sumTotal = 0;
	for ( var m = 0; m < arrJiShu.length; m++) {
		sumJiShu = sumJiShu + parseInt(arrJiShu[m]);
	}

	for ( var n = 0; n < arrOuShu.length; n++) {
		sumOuShu = sumOuShu + parseInt(arrOuShu[n]);
	}

	for ( var p = 0; p < jishu_child1.length; p++) {
		sumJiShuChild1 = sumJiShuChild1 + parseInt(jishu_child1[p]);
		sumJiShuChild2 = sumJiShuChild2 + parseInt(jishu_child2[p]);
	}
	//计算总和
	sumTotal = parseInt(sumJiShu) + parseInt(sumOuShu)
			+ parseInt(sumJiShuChild1) + parseInt(sumJiShuChild2);

	//计算Luhm值
	var k = parseInt(sumTotal) % 10 == 0 ? 10 : parseInt(sumTotal) % 10;
	var luhm = 10 - k;

	if (lastNum == luhm) {
		return true;
	} else {
		return false;
	}
}