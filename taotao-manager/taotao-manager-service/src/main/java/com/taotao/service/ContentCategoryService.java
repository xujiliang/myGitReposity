package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;

public interface ContentCategoryService {
	//获取内容分类信息
	List<EasyUITreeNode> getContentCatList(long parentId);
	
	//新增节点，并返回主键id
	TaotaoResult insertCategoryNode(long parentId,String nodeName);
	
	//更新分类节点名
	TaotaoResult updateCatNodeName(long id,String nodeName);
	
	//删除节点
	TaotaoResult deleteCatNode(long id);
	
}