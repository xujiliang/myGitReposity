package com.taotao.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.ItemCatResult;
@Service
public class ItemCatServiceImple implements ItemCatService {

	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	
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
			
			
			
		return resultList;
	}
}