package com.taotao.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.taotao.common.pojo.JsonUtils;
import com.taotao.common.pojo.PictureResult;
import com.taotao.service.PictureService;
@Controller
public class PictureController2 {
	@Autowired
	private PictureService pictureService;
	
//	@RequestMapping("/pic/upload")
//	@ResponseBody
//	public PictureResult uploadPic(MultipartFile picfile){
//		System.out.println("xxxxxxxxxxx");
//		 PictureResult pictureResult = pictureService.uploadPicture(picfile);
//		 System.out.println("yyyyyyyyy");
//		return pictureResult;
//				
//	}
	@RequestMapping("pic/upload")
	@ResponseBody
	 public String springUpload(HttpServletRequest request) throws IllegalStateException, IOException
	    {
	    PictureResult pictureResult= new PictureResult();    
		long  startTime=System.currentTimeMillis();
	         //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
	        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
	                request.getSession().getServletContext());
	        //检查form中是否有enctype="multipart/form-data"
	        if(multipartResolver.isMultipart(request))
	        {
	            //将request变成多部分request
	            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
	           //获取multiRequest 中所有的文件名
	            Iterator iter=multiRequest.getFileNames();
	            String picturepath = null;
	            while(iter.hasNext())
	            {
	                //一次遍历所有文件
	                MultipartFile file=multiRequest.getFile(iter.next().toString());
	                if(file!=null)
	                {
	                    String path="E:picture"+file.getOriginalFilename();
	                    picturepath = path;
	                    //上传
	                    file.transferTo(new File(path));
	                }
	                 
	            }
	            pictureResult.setError(0);
		        pictureResult.setMessage("上传成功22！");
		        pictureResult.setUrl(picturepath);
	        }
	        long  endTime=System.currentTimeMillis();
	        System.out.println("方法三的运行时间："+String.valueOf(endTime-startTime)+"ms");
	        String json = JsonUtils.objectToJson(pictureResult);
	    return json; 
	    }
}