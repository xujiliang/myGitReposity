package com.xjl.basic.dao;

import java.util.List;
import java.util.Map;

import com.xjl.basic.model.Pager;

/**
 * 公共的dao处理对象，该对象中包含了hibernate的所有操作和对SQL的操作
 * @author XJL
 *
 * @param <T>
 */
public interface IBaseDao<T> {
	
	//添加对象
	public T add(T t);
	//更新对象
	public void update(T t);
	//根据Id删除对象
	public void delete(int id);
	//根据id加载对象
	public void load(int id);
	/**
	 * 不分页的列表对象
	 * @param hql
	 * @param arg
	 * @return
	 */
	
	public List<T> list(String hql,Object[] arg);
	
	public List<T> list(String hql,Object arg);
	
	public List<T> list(String hql);
	
	public List<T> list(String hql,Object[] arg,Map<String,Object> alias);
	
	public List<T> lsit(String hql,Map<String,Object> alias);
	
	/**
	 * 分页的列表对象
	 * 
	 * 
	 */
	public Pager<T> find(String hql,Object[] arg);
	
	public Pager<T> find(String hql,Object arg);
	
	public Pager<T> find(String hql);
	
	public Pager<T> find(String hql,Object[] arg,Map<String,Object> alias);
	
	public Pager<T> find(String hql,Map<String,Object> alias);
	
	/**
	 * 查询对象
	 * @param hql
	 * @param args
	 * @return
	 */
	
	public Object queryObject(String hql,Object[] args);
	
	public Object queryObject(String hql,Object arg);
	
	public Object queryObject(String hql);
	
	/**
	 * 根据HQL更新对象
	 * @param hql
	 * @param args
	 */
	public void updateByHql(String hql,Object[] args);
	
	public void updateByHql(String hql,Object arg);
	
	public void updateByHql(String hql);
	
	/**
	 * 
	 * @param sql
	 * @param args
	 * @param clz
	 * @param hasEntity;该对象是否是一个hibernate关联的实体，不过不是需要使用setResultTranform查询
	 * @return
	 */

	public List<T> listBySql(String sql,Object[] args,Class<T> clz,Boolean hasEntity);
	
	public List<T> listBySql(String sql,Object arg,Class<T> clz,Boolean hasEntity);
	
	public List<T> listBySql(String sql,Class<T> clz,Boolean hasEntity);
	
	public List<T> listBySql(String sql,Object[] args,Map<String,Object> alias,Class<T> clz,Boolean hasEntity);
	
	public List<T> listBySql(String sql,Map<String,Object> alias,Class<T> clz,Boolean hasEntity);
	
	/**
	 * 带分页
	 * @param sql
	 * @param args
	 * @param clz
	 * @param hasEntity
	 * @return
	 */
	public Pager<T> findBySql(String sql,Object[] args,Class<T> clz,Boolean hasEntity);
	
	public Pager<T> findBySql(String sql,Object arg,Class<T> clz,Boolean hasEntity);
	
	public Pager<T> findBySql(String sql,Class<T> clz,Boolean hasEntity);
	
	public Pager<T> findBySql(String sql,Object[] args,Map<String,Object> alias,Class<T> clz,Boolean hasEntity);
	
	public List<T> findBySql(String sql,Map<String,Object> alias,Class<T> clz,Boolean hasEntity);
}
