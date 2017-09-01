package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
@Controller
public class TbItemController {
	@Autowired
	private ItemService itemService;
	/**
	 * 页面跳转 ，跳转到{itemid}页面
	 * @param itemid
	 * @return
	 */
	@RequestMapping("/item/{itemid}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemid){
		TbItem item = itemService.getItemById(itemid);
		return item;
	}
	
	
	/**
	 * 根据page 和 rows 查出TbItem信息
	 * @param page：第几页
	 * @param rows:每页信息信息数
	 * @return
	 */
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGridResult getItems(Integer page,int rows){
	
		EasyUIDataGridResult easyUIDataGridResult = itemService.getItemList(page, rows);
		return easyUIDataGridResult;
	}
	
}