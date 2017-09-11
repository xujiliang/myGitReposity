package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ContentCategoryService;

@RequestMapping("/content/category")
@Controller
public class ContentCatController {
	
	@Autowired
	private ContentCategoryService contentCategoryService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentCatList(@RequestParam(value="id",defaultValue="0")long parentId){
		
		List<EasyUITreeNode> resultList = contentCategoryService.getContentCatList(parentId);
		return resultList;
	}
	
	//新增内容分类，返回主键
	@RequestMapping("/create")
	@ResponseBody
	public TaotaoResult insertContentCat(@RequestParam("parentId") long parentId,@RequestParam("name") String nodeName){
		TaotaoResult result = contentCategoryService.insertCategoryNode(parentId, nodeName);
		return result;
		
	}
	
	/**
	 * 更新分类节点名称
	 */
	@RequestMapping("/update")
	@ResponseBody
	public TaotaoResult updateCatNodeName(@RequestParam("id")long id,@RequestParam("name")String nodeName){
		TaotaoResult result = contentCategoryService.updateCatNodeName(id, nodeName);
		return result;
	}
	
	/**
	 * 根据节点id删除节点及其子节点
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult deleteCatNodeById(@RequestParam("id") long id){
		TaotaoResult result = contentCategoryService.deleteCatNode(id);
		return result;
	}
}
