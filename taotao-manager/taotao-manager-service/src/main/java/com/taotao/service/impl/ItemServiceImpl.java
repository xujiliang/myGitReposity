package com.taotao.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.IDUtils;
import com.taotao.common.pojo.JsonUtils;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.pojo.TbItemParamItemExample.Criteria;
import com.taotao.service.ItemService;
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbItemMapper tbItemMapper;
	@Autowired
	private TbItemDescMapper tbItemdescMapper;
	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;
	
	
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
	
	//新增商品
	@Override
	public TaotaoResult createItem(TbItem item, String desc,String itemparam) {
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
		
		TbItemParamItem tbItemParamItem = new TbItemParamItem();
		tbItemParamItem.setCreated(date);
		tbItemParamItem.setUpdated(date);
		tbItemParamItem.setItemId(itemId);
		tbItemParamItem.setParamData(itemparam);
		tbItemParamItemMapper.insert(tbItemParamItem);
		
				
		return TaotaoResult.ok();
	}

	/**
	 * 根据商品id获取商品参数信息，返回HTML片段
	 */
	@Override
	public String getItemParamHtml(long id) {
		// TODO Auto-generated method stub
		
		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(id);
		List<TbItemParamItem> list = tbItemParamItemMapper.selectByExampleWithBLOBs(example);
		if(list==null||list.isEmpty()){
			return "";
		}else{
			TbItemParamItem param = list.get(0);
			String paramData = param.getParamData();
			//将paramData转换成java对象
			List<Map> listMap = JsonUtils.jsonToList(paramData, Map.class);
			//遍历List 生成HTML
			StringBuffer sb= new StringBuffer();
			sb.append("<table cellspacing=\"0\" width=\"100%\" border=\"1\" class=\"Ptable\">\n");
			sb.append("   \n" );
			sb.append("    <tbody>\n" );
			for (Map map : listMap) {
				sb.append("        <tr class=\"tm-tableAttrSub\">\n" );
				sb.append("            <th colspan=\"2\">"+map.get("group")+"</th>\n" );
				sb.append("        </tr>\n" );
				List<Map> paramMap = (List<Map>) map.get("params");
				for (Map map2 : paramMap) {
					sb.append("        <tr>\n" );
					sb.append("            <th>"+map2.get("k")+"</th>\n" );
					sb.append("            <td>&nbsp;"+map2.get("v")+"</td>\n" );
					sb.append("        </tr>\n" );
				}
			}
			
			
			sb.append("    </tbody>\n" );
			sb.append("</table>");
			System.out.println("sssss");
			return sb.toString();
			
		}
		
	}

}
