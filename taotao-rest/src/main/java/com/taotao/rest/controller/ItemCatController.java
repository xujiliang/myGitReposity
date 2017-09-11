package com.taotao.rest.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.JsonUtils;
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
}
