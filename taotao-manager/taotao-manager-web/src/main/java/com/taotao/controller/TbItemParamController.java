package com.taotao.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ItemParamService;

@Controller
@RequestMapping("/item/param")
public class TbItemParamController {
	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getItemParam(Integer page,int rows){
		EasyUIDataGridResult result = itemParamService.getItemParamList(page, rows);
		System.out.println("111111");
		return result;
	}
	
	//通过页面item-param-add传来的cid查询数据库，返回查询结果的JSON数据
	@RequestMapping("/query/itemcatid/{cid}")
	@ResponseBody
	public TaotaoResult getItemParamBCid(@PathVariable Integer cid){
		
		TaotaoResult taotaoResult = itemParamService.getItemParamByCid(cid);
		
		
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
		
		TaotaoResult taotaoResult = itemParamService.insertItemParam(cid, paramData);
		return taotaoResult;
	}
	
	
	/**
	 * 根据商品ids删除商品参数模板
	 * /item/param/delete
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult deleteItemParam(@RequestParam String ids){
		System.out.println(ids);
		if(ids.contains(",")){
			List<Long> list = new ArrayList<>();
			String[] str_id = ids.split(",");
			for (String id : str_id) {
				list.add(Long.parseLong(id));
				
			}
			TaotaoResult result = itemParamService.deleteItemsParam(list);
			return result;
		}else{
			Long id = Long.parseLong(ids);
			TaotaoResult result = itemParamService.deleteItemParamById(id);
			return result;
		}
		
		
	}
}
