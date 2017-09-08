package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ItemParamService;

@Controller
@RequestMapping("/item/param")
public class TbItemParamController {
	@Autowired
	private ItemParamService itemParamController;
	
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getItemParam(Integer page,int rows){
		EasyUIDataGridResult result = itemParamController.getItemParamList(page, rows);
		System.out.println("111111");
		return result;
	}
	
	//通过页面item-param-add传来的cid查询数据库，返回查询结果的JSON数据
	@RequestMapping("/query/itemcatid/{cid}")
	@ResponseBody
	public TaotaoResult getItemParamBCid(@PathVariable Integer cid){
		
		TaotaoResult taotaoResult = itemParamController.getItemParamByCid(cid);
		
		
		return taotaoResult;
	}
	
	
	/**
	 * 新增商品规格
	 * @param cid
	 * @param paramData
	 * @return
	 */
	
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public TaotaoResult insertItemParam(@PathVariable long cid,String paramData){
		
		TaotaoResult taotaoResult = itemParamController.insertItemParam(cid, paramData);
		return taotaoResult;
	}
}
