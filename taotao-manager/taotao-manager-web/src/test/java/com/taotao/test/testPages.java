package com.taotao.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;

public class testPages {

	@Test
	public void test() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		TbItemMapper itemMapper = ctx.getBean(TbItemMapper.class);
		PageHelper.startPage(1, 30);
		
		
		TbItemExample example = new TbItemExample();
		
		List<TbItem> list = itemMapper.selectByExample(example );
		
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		
		Long total = pageInfo.getTotal();
		System.out.println("总记录数："+ total);
		
		int pages = pageInfo.getPages();
		
		System.out.println("总页数："+ pages);
		
		int nums = pageInfo.getPageNum();
		System.out.println("muns:" + nums);
	}

}
