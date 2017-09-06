package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.pojo.PictureResult;
import com.taotao.service.PictureService;
@Controller
public class PictureController {
	@Autowired
	private PictureService pictureService;
	
	//@RequestMapping("/pic/upload")
	//@ResponseBody
	public PictureResult uploadPic(MultipartFile picfile){
		System.out.println("xxxxxxxxxxx");
		 PictureResult pictureResult = pictureService.uploadPicture(picfile);
		 System.out.println("yyyyyyyyy");
		return pictureResult;
				
	}
}
