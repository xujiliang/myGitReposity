package com.taotao.controller;

import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.HttpClientUtil;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;

@RequestMapping("/content")
@Controller
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	
	@Value("${REST_CONTENT_SYNC_URL}")
	private String REST_CONTENT_SYNC_URL;
	
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult addContent(TbContent content){
	
		TaotaoResult result = contentService.insertContent(content);
		
		HttpClientUtil.doGet(REST_BASE_URL+REST_CONTENT_SYNC_URL+content.getCategoryId());
		return result;
	}
	
	/**
	 * 根据查出淘宝Tb_Content表中其所有信息,并返回
	 * /content/query/list
	 */
	@RequestMapping("/query/list")
	@ResponseBody
	public EasyUIDataGridResult selectContentList(Integer page,int rows){
		EasyUIDataGridResult result = contentService.selectContentList(page,rows);
		return result;
	}
	
}
