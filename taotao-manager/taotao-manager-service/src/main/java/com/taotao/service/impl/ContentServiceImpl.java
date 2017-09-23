package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {
	
	@Autowired
	private TbContentMapper tbContentMapper;

	@Override
	public TaotaoResult insertContent(TbContent content) {
		// TODO Auto-generated method stub
		Date date  = new Date(); 
		content.setCreated(date);
		content.setUpdated(date);
		tbContentMapper.insert(content);
		return TaotaoResult.ok();
	}
	
	/**
	 * 根据节点id查出所有节点的内容
	 */
	@Override
	public EasyUIDataGridResult selectContentList(Integer page,int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);
		TbContentExample example = new TbContentExample();
	//	Criteria criteria = example.createCriteria();
		
		List<TbContent> list  = tbContentMapper.selectByExample(example);
		PageInfo<TbContent> info = new PageInfo<>(list);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		
		result.setRows(list);
		result.setTotal(info.getTotal());
		return result;
	}

}
