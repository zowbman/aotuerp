/** @serializedParams looks like "prop1=value1&prop2=value2".   
Nested property like 'prop.subprop=value' is also supported **/ 
//序列化表单复杂对象
function paramString2obj (serializedParams) { 

var obj={}; 
function evalThem (str) { 
var attributeName = str.split("=")[0]; 
var attributeValue = str.split("=")[1]; 
if(!attributeValue){ 
return ; 
} 
var array = attributeName.split("."); 
for (var i = 1; i < array.length; i++) { 
var tmpArray = Array(); 
tmpArray.push("obj"); 
for (var j = 0; j < i; j++) { 
tmpArray.push(array[j]); 
}; 
var evalString = tmpArray.join("."); 
// alert(evalString); 
if(!eval(evalString)){ 
eval(evalString+"={};"); 
} 
}; 
eval("obj."+attributeName+"='"+attributeValue+"';"); 

}; 
var properties = serializedParams.split("&"); 
for (var i = 0; i < properties.length; i++) { 
evalThem(properties[i]); 
}; 
return obj; 
} 

//方法名formjson
$.fn.formjson = function(){ 
var serializedParams = this.serialize(); 
var obj = paramString2obj(serializedParams); 
return JSON.stringify(obj); 
} 


//扩展表单序列化（简单类型）
/*$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();//序列化为form一个个的对象
	$.each(a, function() {
		if (o[this.name]) {//如果已经存在这个name(可能是重复的input)
			if (!o[this.name].push) {//如果是不是一个数组，也就是此时为第二个相同的name
				o[this.name] = [ o[this.name] ];//将其变为数组
			}
			o[this.name].push(this.value || '');//变成数组后将值转换为字符串加入刚才变好的的数组
		} else {
			o[this.name] = this.value || '';//如果不是重复的则转换为字符串写入o对象
		}
	});
	return o;
};*/