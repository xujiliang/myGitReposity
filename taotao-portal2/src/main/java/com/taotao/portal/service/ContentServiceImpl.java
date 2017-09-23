package com.taotao.portal.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.HttpClientUtil;
import com.taotao.common.pojo.JsonUtils;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.portal.pojo.AdNode;

@Service
public class ContentServiceImpl implements ContentService{
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	
	@Value("${REST_CONTENT_URL}")
	private String REST_CONTENT_URL;
	
	@Value("${REST_CONTENT_AD1_CID}")
	private String REST_CONTENT_AD1_CID;
	
	@Override
	public String getAd1List() {
		// TODO Auto-generated method stub
		String json = HttpClientUtil.doGet(REST_BASE_URL+REST_CONTENT_URL+REST_CONTENT_AD1_CID);
		TaotaoResult taoatoResult = TaotaoResult.formatToList(json, TbContent.class); 
		
		List<TbContent> resultList = (List<TbContent>) taoatoResult.getData();
		
		List<AdNode> list = new ArrayList<>();
		for (TbContent tbContent : resultList) {
			AdNode node = new AdNode();
//			"srcB": "/image/11.jpg",
//	        "height": 240,
//	        "alt": "",
//	        "width": 670,
//	        "src": "/image/11.jpg",
//	        "widthB": 550,
//	        "href": "http://sale.jd.com/act/e0FMkuDhJz35CNt.html?cpdad=1DLSUE",
//	        "heightB": 240
			node.setHeight(240);
			node.setHeightB(240);

			node.setAlt(tbContent.getSubTitle());
			node.setWidth(670);
			node.setWidthB(550);
			node.setSrcB(tbContent.getPic2());
			node.setSrc(tbContent.getPic());
			node.setHref(tbContent.getUrl());
			list.add(node);
			
		}
		//把list转换为json数据
		String jsonTResult = JsonUtils.objectToJson(list);
		return jsonTResult;
	}

}
