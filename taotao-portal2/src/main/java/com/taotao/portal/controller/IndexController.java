package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.portal.service.ContentService;

/**
 * 首页测试
 * @author XJL
 *
 */
@Controller
public class IndexController {
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/index")
	public String showIndex(Model model){
	
		String jsonData = contentService.getAd1List();
		model.addAttribute("ad1", jsonData);
		
		return "index";
	}
	
	@RequestMapping(value="/posttest",method=RequestMethod.POST)
	@ResponseBody
	public String httpClientTest(String name,String pass){
		System.out.println("name:"+name);
		System.out.println("pass:"+ pass);
		return "OK";
	}
}
