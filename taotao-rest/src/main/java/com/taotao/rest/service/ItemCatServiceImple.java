package com.taotao.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.taotao.common.pojo.JsonUtils;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.rest.component.JedisClient;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.ItemCatResult;

import redis.clients.jedis.Jedis;
@Service
public class ItemCatServiceImple implements ItemCatService {

	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("REDIS_CATCATEGORY_KEY")
	private String REDIS_CATCATEGORY_KEY;
	
	@Override
	public ItemCatResult getItemCat() {
		// TODO Auto-generated method stub
		 List list = getItemCatList(0l);
		 ItemCatResult itemCatResult = new ItemCatResult();
		 itemCatResult.setData(list);
		return itemCatResult;
	}
	
	//
	public List getItemCatList(long parentId){
		
		//从redis缓存中取数据，如果redis缓存中数据为空，则查询数据库；
		try {
			String json = jedisClient.hget(REDIS_CATCATEGORY_KEY, parentId+"");
			if(!StringUtils.isEmpty(json)){
				List resultList = JsonUtils.jsonToList(json, CatNode.class);
				return resultList;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
		List resultList = new ArrayList<>();
		int index =0;
		for (TbItemCat tbItemCat : list) {
			
			if(index>=14){
			break;
			}
						//如果是父节点执行
						if(tbItemCat.getIsParent()){
							
						CatNode node = new CatNode();
						//url
						node.setUrl("/products/"+tbItemCat.getId()+"html");
						//如果父节点为0，n格式为："<a href='/products/1.html'>图书、音像、电子书刊</a>",
						if(parentId==0){
							index++;
							node.setName("<a href='/products/"+tbItemCat.getId()+"html'>"+tbItemCat.getName()+"</a>");
						}else{
							node.setName(tbItemCat.getName());
						}
						
						//item
						node.setItems(getItemCatList(tbItemCat.getId()));
						resultList.add(node);
						}else{
							//如果tbItemCat是子节点
							String item = "/products/"+tbItemCat.getId()+".html|"+tbItemCat.getName();
							resultList.add(item);
						}
						
					}
			//向redis缓存中添加数据
			try {
				jedisClient.hset(REDIS_CATCATEGORY_KEY, parentId+"", JsonUtils.objectToJson(resultList));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		return resultList;
	}

	@Override
	public TaotaoResult syncItemCat(long parentId) {
		// TODO Auto-generated method stub
		 jedisClient.hdel(REDIS_CATCATEGORY_KEY, parentId+"");
		return TaotaoResult.ok();
	}
}
