<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>

<link id="themelink" rel="stylesheet" type="text/css" href="easyui/themes/black/easyui.css">   
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">   
<script type="text/javascript" src="easyui/jquery.min.js"></script>   
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>  

</head>
<body class="easyui-layout">   
    <div data-options="region:'north',split:true" style="height:80px;">
    	<div id="logo">
    		<img src="images/timg.jpg">
    	</div>
    	
    	<div id="Logindiv" style="position:absolute;right:20px;top:20px;">
    		欢迎您，【超级管理员】！
    		 <a href="javascript:void(0)" id="mb" class="easyui-menubutton"     
       					 data-options="menu:'#mm',iconCls:'icon-edit'">Edit</a>   
		  		  <div id="mm" style="width:150px;">   
					    <div >default</div> 
					    <div >black</div>
					    <div >gray</div>
					    <div >icons</div>
					    <div >material</div>  
					    <div >bootstrap</div> 
					</div>  
    	</div>
    </div>   
    <div data-options="region:'south',split:true" style="height:30px;">
    	<div style="text-align: center;">
    	 版权所有，翻版必究！
    	</div>
    </div>   
       
    <div data-options="region:'west',title:'West',split:true" style="width:200px;">
    	
    </div>   
    <div data-options="region:'center',title:'center title'" style="padding:5px;background:#eee;">
		  		
    </div>   
    
    <script type="text/javascript">
    //	$(function(){
    		$("#mm").menu({
    			onClick:function(item){
    				//alert(item.text);
    				var themename = item.text;
    		//	var href = $("#themelink").attr("href");
    			var href="easyui/themes/"+themename+"/easyui.css";
    			$("#themelink").val("href",href);
    			}
    		})
    //	})
    </script>
</body> 
</html>