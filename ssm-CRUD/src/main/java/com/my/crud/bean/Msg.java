package com.my.crud.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用的返回类
 * @author XJL
 *
 */
public class Msg {
	//状态码
	private int code;
	//返回信息
	private String msg;
	
	private Map<String,Object> extend = new HashMap<String,Object>();
	
	//
	public static Msg success(){
		Msg result = new Msg();
		result.setCode(100);
		result.setMsg("执行成功！");
		
		return result;
	}
	//
	public static Msg failure(){
		Msg result = new Msg();
		result.setCode(200);
		result.setMsg("执行失败！");
		
		return result;
	}
	//一个快捷添加方法
	public Msg add(String key, Object object){
		this.getExtend().put(key, object);
		return this; 
	}
	
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

	public Map<String, Object> getExtend() {
		return extend;
	}

	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	}
	
	

}
