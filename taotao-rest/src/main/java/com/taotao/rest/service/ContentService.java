package com.taotao.rest.service;

import java.util.List;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {

	List<TbContent> getContentList(long cid);
	
	TaotaoResult syncContent(long cid);
}