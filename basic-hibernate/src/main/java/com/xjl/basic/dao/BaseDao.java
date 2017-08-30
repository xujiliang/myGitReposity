package com.xjl.basic.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.service.spi.InjectService;

import com.xjl.basic.model.Pager;

public class BaseDao<T> implements IBaseDao<T> {
	private SessionFactory sessionFactory;
	
	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	@Inject
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession(){
		return sessionFactory.openSession();
	}
	public T add(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(T t) {
		// TODO Auto-generated method stub
		
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	public void load(int id) {
		// TODO Auto-generated method stub
		
	}

	public List<T> list(String hql, Object[] arg) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> list(String hql, Object arg) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> list(String hql) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> list(String hql, Object[] arg, Map<String, Object> alias) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> lsit(String hql, Map<String, Object> alias) {
		// TODO Auto-generated method stub
		return null;
	}

	public Pager<T> find(String hql, Object[] arg) {
		// TODO Auto-generated method stub
		return null;
	}

	public Pager<T> find(String hql, Object arg) {
		// TODO Auto-generated method stub
		return null;
	}

	public Pager<T> find(String hql) {
		// TODO Auto-generated method stub
		return null;
	}

	public Pager<T> find(String hql, Object[] arg, Map<String, Object> alias) {
		// TODO Auto-generated method stub
		return null;
	}

	public Pager<T> find(String hql, Map<String, Object> alias) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object queryObject(String hql, Object[] args) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object queryObject(String hql, Object arg) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object queryObject(String hql) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateByHql(String hql, Object[] args) {
		// TODO Auto-generated method stub
		
	}

	public void updateByHql(String hql, Object arg) {
		// TODO Auto-generated method stub
		
	}

	public void updateByHql(String hql) {
		// TODO Auto-generated method stub
		
	}

	public List<T> listBySql(String sql, Object[] args, Class<T> clz, Boolean hasEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> listBySql(String sql, Object arg, Class<T> clz, Boolean hasEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> listBySql(String sql, Class<T> clz, Boolean hasEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> listBySql(String sql, Object[] args, Map<String, Object> alias, Class<T> clz, Boolean hasEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> listBySql(String sql, Map<String, Object> alias, Class<T> clz, Boolean hasEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	public Pager<T> findBySql(String sql, Object[] args, Class<T> clz, Boolean hasEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	public Pager<T> findBySql(String sql, Object arg, Class<T> clz, Boolean hasEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	public Pager<T> findBySql(String sql, Class<T> clz, Boolean hasEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	public Pager<T> findBySql(String sql, Object[] args, Map<String, Object> alias, Class<T> clz, Boolean hasEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> findBySql(String sql, Map<String, Object> alias, Class<T> clz, Boolean hasEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}