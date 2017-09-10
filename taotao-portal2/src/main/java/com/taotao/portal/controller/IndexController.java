package com.taotao.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页测试
 * @author XJL
 *
 */
@Controller
public class IndexController {
	@RequestMapping("/index")
	public String showIndex(){
		return "index";
	}
}
