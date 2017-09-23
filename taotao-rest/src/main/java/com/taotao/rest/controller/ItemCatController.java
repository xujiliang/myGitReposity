package com.taotao.rest.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.ExceptionUtil;
import com.taotao.common.pojo.JsonUtils;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.rest.pojo.ItemCatResult;
import com.taotao.rest.service.ItemCatService;

@Controller
@RequestMapping(value="/item/cat",produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
@ResponseBody
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;
	@RequestMapping("/list")
	public String getItemCatList(String callback){
		ItemCatResult result = itemCatService.getItemCat();
		if(StringUtils.isBlank(callback)){
		String json = JsonUtils.objectToJson(result);
		return json;
		}
		String json = JsonUtils.objectToJson(result);
		 
		return callback+"("+json+");";
		}
	
	@RequestMapping("/cat/sync/{parentId}")
	@ResponseBody
	public TaotaoResult syncCatContent(@PathVariable long parentId){
		
		
		try {
			TaotaoResult result = itemCatService.syncItemCat(parentId);
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
	}
}
