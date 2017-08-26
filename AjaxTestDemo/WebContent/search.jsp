<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
   pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Insert title here</title>
<style type="text/css">
#mydiv{
position:absolute;
left:50%;
top:50%;
margin-left:-200px;
margin-top:-50px;
}

</style>
<script type="text/javascript">
//获得用户输入的关联信息
 		var xmlHttp;
          function getMoreContents(){
              //获得用户的输入信息
             
	            var content = document.getElementById("keyword");
              if(content.value==""){
            	  return;
              }
             //给服务器发送用户输入的内容，使用XmlHttp对象
             xmlHttp = createXmlHttp();
           //  alert(xmlHttp);
           //给服务器发送数据
           var url = "search?keyword="+escape(content.value);
           xmlHttp.open("get",url,true);
           //xmlHttp绑定回调方法，当服务器状态改变时调用
           xmlHttp.onreadystatechange=callback;
           xmlHttp.send(null);
          }
          
          //创建XmlHttp对象
          
          function createXmlHttp(){
        	  var xmlHttp;
        	  if(window.XMLHttpRequest){
        		  xmlHttp = new XMLHttpRequest();
        		  
        	  }
        	  if(window.ActiveXObject){
        		  xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        		  if(!xmlHttp){
        			  xmlHttp = new ActiveXObject("Msxml2.XMLHttp");
        		  }
        	  }
        	  return xmlHttp;
          }
          //回调函数
          function callback(){
        	  if(xmlHttp.readyState==4){
        		  if(xmlHttp.status==200){
        			  //交互成功，获得相应的文本格式数据
                       var result = xmlHttp.responseText;
        			  //解析获得数据
        			  var json = eval("("+result+")");
        			  //获得数据后将这些数据展示到输入框的下面
        			  //alert(json);
        			  document.getElementById("xianshi").innerHTML= json ;
        		  }
        	  }
          }
          //设置关联数据的展示,参数代表从服务器传过来的数据
          
          
</script>
</head>
<body>
<div id="mydiv">
    <!-- 输入框 -->
    <input type="text" size="50" id="keyword" onkeyup="getMoreContents()"/>
    <input type="button" value="百度一下a" width="50px"/>
    <!-- 内容展示区域 -->
    <div id="popDiv">
         <table id="content_table"  bgcolor="#FFFAFA" border="0" cellspacing="0">
            <tbody id="content_table_body">
            	数据库搜索结果是:<font color="red"><div id="xianshi"></div></font>
            </tbody>
            
         </table>
    </div>
</div>

</body>
</html>