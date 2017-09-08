package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemParamMapper;

import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.service.ItemParamService;
@Service
public class ItemParamServiceImpl implements ItemParamService {
	
	
	@Autowired
	private TbItemParamMapper tbItemParemMapper;
	@Override
	public EasyUIDataGridResult getItemParamList(Integer page, int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);
		TbItemParamExample example  = new TbItemParamExample();
		List<TbItemParam> list = tbItemParemMapper.selectByExampleWithBLOBs(example);
		System.out.println("List:"+list);
		PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}
	
	//查询数据库中是否存在cid对应的记录
	@Override
	public TaotaoResult getItemParamByCid(long cid) {
		// TODO Auto-generated method stub
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria  = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = tbItemParemMapper.selectByExampleWithBLOBs(example);
		if(list!=null&&list.size()>0){
			TbItemParam tbItemParam = list.get(0);
			return TaotaoResult.ok(tbItemParam);
		}
		
		return TaotaoResult.ok();
	}

	/**
	 * 新增商品规格方法
	 */
	@Override
	public TaotaoResult insertItemParam(long cid, String paramData) {
		// TODO Auto-generated method stub
		TbItemParam tbItemParam = new TbItemParam();
		tbItemParam.setItemCatId(cid);
		Date date = new Date();
		tbItemParam.setCreated(date);
		tbItemParam.setUpdated(date);
		tbItemParam.setParamData(paramData);
		tbItemParemMapper.insert(tbItemParam);
		
		return TaotaoResult.ok();
	}

	

}

