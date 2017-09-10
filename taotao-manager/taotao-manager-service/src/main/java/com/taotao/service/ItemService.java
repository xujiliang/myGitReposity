package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {
	TbItem getItemById(Long itemid); 
	
	EasyUIDataGridResult getItemList(Integer page,int rows);
	
	TaotaoResult createItem(TbItem item,String desc,String itemparam);
	
	String getItemParamHtml(long id);
}
