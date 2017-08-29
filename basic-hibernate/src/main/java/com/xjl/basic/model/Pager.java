package com.xjl.basic.model;

import java.util.List;

/**
 * 
 * @author XJL
 *分页对象
 * @param <T>
 */
public class Pager<T> {
	/*
	 * 分页大小
	 */
	private int size;
	/**
	 * 分页起始页
	 */
	private int offset;
	
	/**
	 * 总记录数
	 */
	private int total;
	
	/**
	 * 分页数据
	 */
	private List<T> datas;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	
	
}
