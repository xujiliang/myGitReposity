package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbItemMapper tbItemMapper;
	@Override
	public TbItem getItemById(Long itemid) {
		// TODO Auto-generated method stub
		TbItem item = tbItemMapper.selectByPrimaryKey(itemid);
		return item;
	}
	
	/**
	 * 分页查询
	 */
	@Override
	public EasyUIDataGridResult getItemList(Integer page, int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);
		TbItemExample example = new TbItemExample();
		
		List<TbItem> list = tbItemMapper.selectByExample(example);
		PageInfo pageInfo = new PageInfo<>(list);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}

}
