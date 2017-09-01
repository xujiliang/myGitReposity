package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.service.ItemCatService;

@Controller
@RequestMapping("/item/cat")
public class TbItemCatController {
	@Autowired
	private ItemCatService ItemCatService;
	@RequestMapping("/list")
	@ResponseBody
	public List<EasyUITreeNode> getItemCats(@RequestParam(value="id",defaultValue="0" ) long parentId){
		List<EasyUITreeNode> list =  ItemCatService.getItemCatList(parentId);
		return list;
	}
}
