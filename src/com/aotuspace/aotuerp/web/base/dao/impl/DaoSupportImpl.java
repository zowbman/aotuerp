package com.aotuspace.aotuerp.web.base.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.aotuspace.aotuerp.web.base.dao.DaoSupport;
/**
 * 
 * Title:DaoSupportImpl
 * Description:通用dao实现类
 * Company:aotuspace
 * @author    sida
 * @date      2015-9-2 下午12:28:14
 *
 */
@Transactional
@SuppressWarnings("unchecked")
public class DaoSupportImpl<T> implements DaoSupport<T> {

	@Resource
	private SessionFactory sessionFactory;
	
	private Class<T> clazz;
	
	public DaoSupportImpl(){
		//使用反射技术得到T的真实类型
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();//获取当前new的对象的泛型的父类类型
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0];//获取第一个类型参数的真实类型
		System.out.println("clazz------>" + clazz);
	}
	
	//获取当前可用的Session
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	
	public void save(T entity)throws Exception{
		getSession().save(entity);
		
	}

	public void update(T entity)throws Exception{
		getSession().update(entity);
		
	}

	public void delete(Integer id)throws Exception{
		Object obj = getById(id);
		if(obj != null){
			getSession().delete(obj);
		}
	}
	
	
	public void delete(Integer[] ids) throws Exception {
		List<T> list = getByIds(ids);
		if(list!=null){
			for (T t : list) {
				getSession().delete(t);
			}
		}
	}

	//复合主键
	public void delete(Object object)throws Exception{
		Object obj=getByObject(object);
		if(obj!=null){
			getSession().delete(obj);
		}
	}
	
	//批量删除（复合主键）
	public void deleteByList(Object objects) throws Exception {
		List<T> list=getByObjects((List<Object>) objects);
		if(list!=null){
			for(int i=0;i<list.size();i++){
				getSession().delete(list.get(i));
			}
		}
	}

	public T getById(Integer id)throws Exception{
		if(id == null){
			return null;
		}else{
			return (T) getSession().get(clazz, id);
		}
	}
	
	//复合主键
	public T getByObject(Object object)throws Exception{
		if(object ==null){
			return null;
		}else{
			return (T) getSession().get(clazz, (Serializable) object);
		}
	}
	
	//批量查询复合主键
	public List<T> getByObjects(List<Object> objects) throws Exception {
		if(objects==null){
			return null;
		}else{
			List<T> list=new ArrayList<T>();
			for (Object object : objects) {
				list.add(getByObject(object));
			}
			return list;
		}
	}

	public List<T> getByIds(Integer[] ids) throws Exception {
		if (ids == null || ids.length == 0) {
			return Collections.EMPTY_LIST;
		} else {
			return getSession().createQuery("From " + clazz.getSimpleName() + " Where id In(:ids)")
					.setParameterList("ids", ids).list();
		}
	}

	public List<T> findAll() throws Exception {
		return getSession().createQuery("From " + clazz.getSimpleName()).list();
	}
	
}
