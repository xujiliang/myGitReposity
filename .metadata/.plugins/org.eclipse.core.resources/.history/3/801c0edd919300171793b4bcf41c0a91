package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.IDUtils;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbItemMapper tbItemMapper;
	@Autowired
	private TbItemDescMapper tbItemdescMapper;
	
	
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

	@Override
	public TaotaoResult createItem(TbItem item, String desc) {
		// TODO Auto-generated method stub
		long itemId = IDUtils.genItemId();
		item.setId(itemId);
		//商品状态，1-正常，2-下架，3-删除
		item.setStatus((byte) 1);
		
		Date date  = new Date();
		//创建时间
		item.setCreated(date);
		//更新时间
		item.setUpdated(date);
		//保存数据
		tbItemMapper.insert(item);
		
		//商品描述
		TbItemDesc itemdesc = new TbItemDesc();
		itemdesc.setCreated(date);
		itemdesc.setItemDesc(desc);
		itemdesc.setItemId(itemId);
		itemdesc.setUpdated(date);
		
		tbItemdescMapper.insert(itemdesc);
		return TaotaoResult.ok();
	}

}
