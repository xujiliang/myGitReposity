package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.service.ContentCategoryService;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	
	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;
	
	//获取所有分类信息
	@Override
	public List<EasyUITreeNode> getContentCatList(long parentId) {
		// TODO Auto-generated method stub
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list  = tbContentCategoryMapper.selectByExample(example);
		List<EasyUITreeNode> relist = new ArrayList<>();
		for (TbContentCategory tbContentCategory : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent()?"closed":"open");
			relist.add(node);
		}
		return relist;
	}
	
	
	//根据父id新增节点，并返回主键 id
	@Override
	public TaotaoResult insertCategoryNode(long parentId, String nodeName) {
		// TODO Auto-generated method stub
		
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setParentId(parentId);
		contentCategory.setName(nodeName);
		//状态。可选值:1(正常),2(删除)',
		contentCategory.setStatus(1);
		Date date = new Date();
		contentCategory.setUpdated(date);
		contentCategory.setCreated(date);
		//'排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数',
		contentCategory.setSortOrder(1);
		contentCategory.setIsParent(false);
		tbContentCategoryMapper.insert(contentCategory);
		//返回主键id
		long id = contentCategory.getId();
		//判断父节点的isparent属性
		TbContentCategory parentNode = tbContentCategoryMapper.selectByPrimaryKey(parentId);
		if(!parentNode.getIsParent()){
			parentNode.setIsParent(true);
			//更新父节点
			tbContentCategoryMapper.updateByPrimaryKey(parentNode);
		}
		
		return TaotaoResult.ok(id);
	}

	/**
	 * 更新分类节点名
	 */
	@Override
	public TaotaoResult updateCatNodeName(long id, String nodeName) {
		// TODO Auto-generated method stub
		TbContentCategory contentCategory = tbContentCategoryMapper.selectByPrimaryKey(id);
		contentCategory.setName(nodeName);
		contentCategory.setUpdated(new Date());
		tbContentCategoryMapper.updateByPrimaryKey(contentCategory);
		return TaotaoResult.ok();
	}

	/**
	 * 删除节点
	 */
	@Override
	public TaotaoResult deleteCatNode(long id) {
		// TODO Auto-generated method stub
		TbContentCategory contentNode = tbContentCategoryMapper.selectByPrimaryKey(id);
		
		//如果该节点为叶子节点，执行删除
		if(!contentNode.getIsParent()){
			 tbContentCategoryMapper.deleteByPrimaryKey(id);
			 return TaotaoResult.ok();
		}else{
			
			//如果该节点为父节点执行递归删除
			TaotaoResult result = deleteNode(id);
			
			//递归删除该以该节点id为父节点的子节点后，删除该父节点
			tbContentCategoryMapper.deleteByPrimaryKey(id);
			return result;
		}
		
		
	}
	
	//递归删除分类节点及其子节点
	public TaotaoResult deleteNode(long id){
		
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(id);
		
		//查找出该节点下所有以id为父节点的分类节点
		List<TbContentCategory> contentCat = tbContentCategoryMapper.selectByExample(example);
		
		//遍历每一个分类节点
		for (TbContentCategory tbContentCategory : contentCat) {
			
			//查找出该分类节点的parentId属性
			long parentId = tbContentCategory.getParentId();
			
			//如果该分类节点为叶子节点，即isparent属性为0
			if(!tbContentCategory.getIsParent()){
				
				//执行删除（以parentid属性删除该节点）
				TbContentCategoryExample example2 = new TbContentCategoryExample();
				Criteria criteria2 = example2.createCriteria();
				criteria2.andParentIdEqualTo(parentId);
				
				tbContentCategoryMapper.deleteByExample(example2);
				
			}else{
				//如果该节点部位叶子节点，即isparent属性为1，执行递归
				deleteNode(tbContentCategory.getId());
				
			}
			
		}
		return TaotaoResult.ok();
	}

}

