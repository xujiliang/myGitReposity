package com.taotao.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.taotao.common.pojo.JsonUtils;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.rest.component.JedisClient;

import redis.clients.jedis.Jedis;

@Service
public class ContentServiceImpl implements ContentService {
	
	@Autowired
	private TbContentMapper tbContentMApper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${REDIS_CONTENT_KEY}")
	private String REDIS_CONTENT_KEY;
	
	@Override
	public List<TbContent> getContentList(long cid) {
		// TODO Auto-generated method stub
		//从redis 中取缓存数据
		try {
		String json = jedisClient.hget(REDIS_CONTENT_KEY, cid+"");
		if(!StringUtils.isEmpty(json)){
			List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
			return list;
		}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
		
		TbContentExample example  = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(cid);
		List<TbContent> list = tbContentMApper.selectByExample(example);
		
		//向缓存中添加数据
		try {
			jedisClient.hset(REDIS_CONTENT_KEY, cid+"", JsonUtils.objectToJson(list));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public TaotaoResult syncContent(long cid) {
		// TODO Auto-generated method stub
		jedisClient.hdel(REDIS_CONTENT_KEY, cid+"");
		return TaotaoResult.ok();
	}

}