<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<% 
String path = request.getContextPath(); 
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
String paramId = request.getParameter("params");             //用request得到 
%> 
<h1>undefined</h1>

<form id="itemParamUpdate"  class="itemForm" method="post">


</form>
<script type="text/javascript">

var itemAddEditor ;
//页面初始化完毕后执行此方法

	//创建富文本编辑器
	//itemAddEditor = TAOTAO.createEditor("#itemAddForm [name=desc]");
	//初始化类目选择和图片上传器
	$(function(id){
		//根据商品的分类id取商品 的规格模板，生成规格信息。第四天内容。
		TAOTAO.changeItemParam(id, "itemAddForm");
	});


</script>