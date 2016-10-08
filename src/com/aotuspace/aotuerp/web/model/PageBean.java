package com.aotuspace.aotuerp.web.model;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * 
 * Title:PageBean
 * Description:分页
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-9-23 下午8:23:59
 *
 */
public class PageBean<T> {
	private Class<T> clazz;
	
	private List<T> recordList;
	private int pageCount;
	
	public PageBean() {
		// 使用反射技术得到T的真实类型
		ParameterizedType pt = (ParameterizedType) this.getClass()
				.getGenericSuperclass();// 获取当前new的对象的泛型的父类类型
		// 得到是一个包含这个泛型信息的对象
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0];// 返回当前类型真实的类型参数的类型，是一个数组，为什么是数组，map就是有两个类型参数。
		System.out.println("class-->>" + clazz.getSimpleName());
		// .getSuperclass() 父类、超类
		// .getClass()当前新建的对象，这个对象是子类
	}
	
	public PageBean(int pageCount, List<T> recordList) {
		this.pageCount=pageCount;
		this.recordList=recordList;
	}

	public List<T> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<T> recordList) {
		this.recordList = recordList;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

}
