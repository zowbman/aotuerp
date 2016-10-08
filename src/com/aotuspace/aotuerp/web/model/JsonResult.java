package com.aotuspace.aotuerp.web.model;

/**
 * 
 * Title:JosonResult
 * Description:Json返回信息类
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-9-24 下午6:16:52
 *
 */
public class JsonResult {
	private int code;	//代码
	private String msg;	//信息
	private Object data;//数据
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
