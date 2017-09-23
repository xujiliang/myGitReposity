package com.taotao.service;



import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {
	
	//添加节点内容
	TaotaoResult insertContent(TbContent content);
	
	//查询表tb_content中的内容并返回
	EasyUIDataGridResult selectContentList(Integer page,int rows);
}
