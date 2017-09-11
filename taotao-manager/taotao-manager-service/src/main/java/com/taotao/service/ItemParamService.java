package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;


public interface ItemParamService {
	//查询参数模板
	EasyUIDataGridResult getItemParamList(Integer page, int rows);
	
	//查询数据库中是否存在cid的模板
	TaotaoResult getItemParamByCid(long cid);
	
	//新增商品规格参数
	TaotaoResult insertItemParam(long cid,String paramData);
	
	//删除商品规格模板
	TaotaoResult deleteItemParamById(long id);
	
	TaotaoResult deleteItemsParam(List<Long> list);
	
	
}
