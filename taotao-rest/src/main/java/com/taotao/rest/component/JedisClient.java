package com.taotao.rest.component;

public interface JedisClient {
	public String set(String key,String value);
	
	public String get(String key);
	
	public Long hset(String key,String Item, String value);
	
	public String hget(String key, String Item);
	
	public Long incr(String key);
	
	public Long desc(String key);
	
	public Long expire(String key, int second);
	
	public Long ttl(String key);
	
	public Long hdel(String key, String item);
	
	
	
	
	
}