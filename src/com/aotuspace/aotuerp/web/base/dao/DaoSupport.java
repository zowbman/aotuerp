package com.aotuspace.aotuerp.web.base.dao;

import java.util.List;

/**
 * 
 * Title:DaoSupport
 * Description:通用dao接口
 * Company:aotuspace
 * @author    sida
 * @date      2015-9-2 下午12:17:21
 *
 */
public interface DaoSupport<T> {
	
	//保存实体
	void save(T entity)throws Exception;
	
	//删除实体
	void delete(Integer id)throws Exception;
	
	//删除多个id
	void delete(Integer[] ids) throws Exception;
	
	//删除（复合主键）
	void delete(Object object)throws Exception;
	
	//批量删除（复合主键）
	void deleteByList(Object objects)throws Exception;

	//更新实体
	void update(T entity)throws Exception;
	
	//按Id查询
	T getById(Integer id)throws Exception;
	
	//按Id查询
	List<T> getByIds(Integer[] ids)throws Exception;
	
	//按object查询(复合主键)
	T getByObject(Object object)throws Exception;
	
	//批量查询(复合主键)
	List<T> getByObjects(List<Object> objects)throws Exception;
	
	//查询所有
	List<T> findAll()throws Exception;
}
