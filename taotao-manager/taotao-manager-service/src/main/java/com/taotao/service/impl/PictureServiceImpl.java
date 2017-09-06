package com.taotao.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.pojo.PictureResult;
import com.taotao.service.PictureService;
@Service
public class PictureServiceImpl implements PictureService {

	@Override
	public PictureResult uploadPicture(MultipartFile picfile) {
		// TODO Auto-generated method stub
		PictureResult pictureResult = new PictureResult();
//		if(picfile.isEmpty()){
//			pictureResult.setError(1);
//			pictureResult.setMessage("图片不能为空！");
//			return pictureResult;
//		}
		System.out.println("zzzzzzzz");
		String oldFileName = picfile.getOriginalFilename(); // 获取上传文件的原名
	//  System.out.println(oldFileName);
	  // 存储图片的虚拟本地路径（这里需要配置tomcat的web模块路径，双击猫进行配置）
	   String saveFilePath = "E://picture";
	  // 上传图片
	   if (picfile != null && oldFileName != null && oldFileName.length() > 0) {
	   // 新的图片名称
	   String newFileName = UUID.randomUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
	   // 新图片
	   File newFile = new File(saveFilePath + "\\" + newFileName);
	   // 将内存中的数据写入磁盘
	   try {
		picfile.transferTo(newFile);
	} catch (IllegalStateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	   
	   
	   // 将新图片名称返回到前端
	   pictureResult.setError(0);
	   pictureResult.setMessage("上传成功！");
	   pictureResult.setUrl(saveFilePath + "\\" + newFileName);
	   
	  }
	  	return pictureResult;
	 
	}
}