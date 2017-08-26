<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<% 
	pageContext.setAttribute("APP_PATH",request.getContextPath());
%>
<script type="text/javascript" src="${APP_PATH }/static/js/scripts/jquery-1.12.4.min.js"></script>
 <link href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
   <script src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script> 
</head>
<body>
					<!-- 编辑模态框 -->
					<div class="modal fade" id="empUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
						  <div class="modal-dialog" role="document">
						    <div class="modal-content">
						      <div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						        <h4 class="modal-title" >员工修改</h4>
						      </div>
						      <div class="modal-body">
								       	<form class="form-horizontal">
											  <div class="form-group">
											    <label class="col-sm-2 control-label">姓名</label>
											    <div class="col-sm-10" id="empName_update_input_div">
											 
											      <p class="form-control-static" id="emp_update_name"></p>
											      
											   	  <span class="help-block"></span>
											    </div>
											  </div>
											  
											 <div class="form-group">
											    <label class="col-sm-2 control-label">Email</label>
											    <div class="col-sm-10">
											      <input type="text" name="email" class="form-control" id="email_update_input" placeholder="email@qq.com">
											      <span class="help-block"></span>
											    </div>
											  </div>
											  
											  <div class="form-group">
											    <label class="col-sm-2 control-label">性别</label>
											    <div class="col-sm-10">
											            <label class="radio-inline" >
														<input type="radio" name="gender" id="gender_update_input1" value="M" checked="checked"> 男
														</label>
														<label class="radio-inline">
														  <input type="radio" name="gender" id="gender_update_input2" value="F"> 女
														</label>
											    </div>
											  </div>
											  
											  <div class="form-group">
											    <label class="col-sm-2 control-label">部门</label>
											    <div class="col-sm-4">
											      <select class="form-control" name="dId">
													 
												   </select>
											    </div>
											  </div>
											  
										</form>
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						        <button type="button" class="btn btn-primary" id="emp_update_btn">更新</button>
						      </div>
						    </div>
						  </div>
					</div>


		<!-- 模态框-->
			<div class="modal fade" id="empAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				  <div class="modal-dialog" role="document">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				        <h4 class="modal-title" id="myModalLabel">员工新增</h4>
				      </div>
				      <div class="modal-body">
						       	<form class="form-horizontal">
									  <div class="form-group">
									    <label class="col-sm-2 control-label">姓名</label>
									    <div class="col-sm-10" id="empName_add_input_div">
									 
									      <input type="text" name="empName" class="form-control" id="empName_add_input" 
									      placeholder="2-5位中文或5-16位字母的组合">
									   	  <span class="help-block"></span>
									    </div>
									  </div>
									  
									 <div class="form-group">
									    <label class="col-sm-2 control-label">Email</label>
									    <div class="col-sm-10">
									      <input type="text" name="email" class="form-control" id="email_add_input" placeholder="email@qq.com">
									      <span class="help-block"></span>
									    </div>
									  </div>
									  
									  <div class="form-group">
									    <label class="col-sm-2 control-label">性别</label>
									    <div class="col-sm-10">
									            <label class="radio-inline" >
												<input type="radio" name="gender" id="gender_add_input1" value="M" checked="checked"> 男
												</label>
												<label class="radio-inline">
												  <input type="radio" name="gender" id="gender_add_input2" value="F"> 女
												</label>
									    </div>
									  </div>
									  
									  <div class="form-group">
									    <label class="col-sm-2 control-label">部门</label>
									    <div class="col-sm-4">
									      <select class="form-control" name="dId">
											 
										   </select>
									    </div>
									  </div>
									  
								</form>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				        <button type="button" class="btn btn-primary" id="emp_save_btn">保存</button>
				      </div>
				    </div>
				  </div>
		</div>


  <!--  -->
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h1>SSH-CRUD</h1>
			</div>
		</div>
		<!-- 按钮 -->
		<div class="row">
			<div class="col-md-4 col-md-offset-8">
				<button type="button" class="btn btn-primary" id="emp_add_model_btn">新增</button>
				<button type="button" class="btn btn-danger" id="emp_delete_model_btn"> 删除</button>
			</div>
		</div>
		<!-- 表格数据 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover" id="emps_table">
					<thead>
					<tr>
						<th><input type='checkbox' class='check_all'></th>
						<th>#</th>
						<th>姓名</th>
						<th>性别</th>
						<th>Email</th>
						<th>部门</th>
						<th>操作</th>
					</tr>
					</thead>
					<tbody>
					
					</tbody>
					
					
				</table>
			</div>
		</div>
		<!-- 分页条 -->
		<div class="row">
			<div class="col-md-6" id="page_info_area">
				
			</div>
			<div class="col-md-6" id="page_nav_area">
				
			</div>
		</div>
	</div>
	
	<!-- js事件 -->	
	
	<script type="text/javascript">
		var currentPage;
		var totalInfo;
		//页面加载完成后，发送ajax请求
		$(function(){
			to_pages(1);
		});
		
		
		/**
		//发送请求数据的函数
		*/	
		function to_pages(pn){
			
			$.ajax({
				
				url:"${APP_PATH }/emps",
				data:"pn="+pn,
				type:"GET",
				success:function(result){
					//console.log(result);
					build_emps_table(result);
					build_page_info(result);
					build_page_nav(result);
				}	
			});
		}
		
		
		
		/**
		
			//显示表格信息函数
		*/
		function build_emps_table(result){
			//清空表格内之前的数据
			$("#emps_table tbody").empty();
			var emps = result.extend.pageInfo.list;
			$.each(emps,function(index,item){
				//alert(item.empName);
				var checkbox = $("<td><input type='checkbox' class='check_item'></td>");
				var empIdTd = $("<td></td>").append(item.empId);
				var empNameTd = $("<td></td>").append(item.empName);
				//var gender = item.gender=="M"?"男":"女";
				var genderTd = $("<td></td>").append(item.gender=="M"?"男":"女");
				var eamilTd = $("<td></td>").append(item.email);
				var deptTd = $("<td></td>").append(item.department.deptName);
				
				var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit")
				.append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append(" 编辑 ");
				editBtn.attr("empid",item.empId);
				var delBtn = $("<button></button>")
				.addClass("btn btn-danger btn-sm delete").append($("<span></span>").addClass("glyphicon glyphicon-trash "))
				.append(" 删除");
				delBtn.attr("empid",item.empId).attr("empName",item.empName);
				
				var btnTd = $("<tr></tr>").append(editBtn).append("&nbsp;").append(delBtn)
				
				$("<tr></tr>").append(checkbox).append(empIdTd)
				.append(empNameTd)
				.append(genderTd)
				.append(eamilTd)
				.append(deptTd)
				.append(btnTd)
				.appendTo("#emps_table tbody");
				
			});	
		}
		
		
		
		/**
		面页信息函数
		*/
		function build_page_info(result){
			//清除之前的页数信息
			$("#page_info_area").empty();
			//<div class="col-md-6">
				//当前页${pageInfo.pageNum}, 总共${pageInfo.pages }, 总共${pageInfo.total }条记录
			//</div>
			$("#page_info_area")
			.append("当前页"+result.extend.pageInfo.pageNum+"&nbsp;&nbsp;&nbsp;&nbsp;总共"+result.extend.pageInfo.pages+"页&nbsp;&nbsp;&nbsp;&nbsp;总共"
			+result.extend.pageInfo.total+"条记录");
			totalInfo = result.extend.pageInfo.total;
			currentPage = result.extend.pageInfo.pageNum;
		} 
		
		
		//
		/**
		导航条函数
		*/
		function build_page_nav(result){
			//清除导航条
			$("#page_nav_area").empty();
			
			var ul = $("<ul></ul>").addClass("pagination");
			//首页
			
			var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
			//上一页
			var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
			
			if(result.extend.pageInfo.hasPreviousPage==false){
				firstPageLi.addClass("disabled");
				prePageLi.addClass("disabled");
			}else{
				
				//绑定单击事件
				firstPageLi.click(function(){
					to_pages(1);
				});
				prePageLi.click(function(){
					to_pages(result.extend.pageInfo.pageNum-1);
				})
			}
			//将首页，上一页分别加入到<ul></ul>中
			ul.append(firstPageLi)
			.append(prePageLi);
			
			//该循环中 pages变量为当前页码,相当于for循环中的循环变量i
			//navigatepageNums为一个数组，pages表示当前元素；index为索引
			$.each(result.extend.pageInfo.navigatepageNums,function(index,pages){
				
				var numLi = $("<li></li>").append($("<a></a>").append(pages));
				if(result.extend.pageInfo.pageNum==pages){
					numLi.addClass("active");
				}
				
				numLi.click(function(){
					to_pages(pages);
				})
				//分别将显示的页码加入到<ul>中
				ul.append(numLi);
			})
			
			//下一页
			var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
			//尾页
			var lastPageLi = $("<li></li>").append($("<a></a>").append("尾页").attr("href","#"));
			if(result.extend.pageInfo.hasNextPage==false){
				nextPageLi.addClass("disabled");
				lastPageLi.addClass("disabled");
			}else{
				nextPageLi.click(function(){
					to_pages(result.extend.pageInfo.pageNum+1);
				})
				lastPageLi.click(function(){
					to_pages(result.extend.pageInfo.pages);
				})
			}
			
			//将下一页，尾页添加到<ul>中
			ul.append(nextPageLi)
			.append(lastPageLi);
			//将<ul>标签加入到<nav>标签中
			var navEle = $("<nav></nav>").append(ul);
			navEle.appendTo("#page_nav_area");
		} 
		
		
		<!--为新增按钮绑定模态框事件-->
		$("#emp_add_model_btn").click(function(){
			//清除输入文本框内的内容
			$("#empAddModal form")[0].reset();
			
			
			//清除之前的格式
			$("#empName_add_input").parent().removeClass("has-error has-success");
			$("#email_add_input").parent().removeClass("has-error has-success");
			//清除错误显示信息	
			$("#empName_add_input").next("span").text("");
				$("#email_add_input").next("span").text("");
				
			//每次点击 。发送查询部门信息请求
			getDepts("#empAddModal select");
		
			$("#empAddModal").modal({
				backdrop:"static"
			});  
			  
			 
		  });
		
		
		//查出所有部门信息显示在模态框的下拉列表中
		function getDepts(elem){
			//清除下拉列表信息
			//$("#empAddModal select option").remove("")
			$(elem).empty();
			$.ajax({
				url:"${APP_PATH }/depts",
				type:"GET",
				success:function(result){
					//console.log(result);
					/**
					{"code":100,"msg":"执行成功！",
						"extend":{"depts":[{"deptId":1,"deptName":"研发部"},{"deptId":2,"deptName":"测试部"}]}}
					*/
					//$("#empAddModal select").append();
					var depts = result.extend.depts;
					$.each(depts,function(){
					
						$("<option></option>").append(this.deptName).attr("value",this.deptId)
						.appendTo(elem);
					})
				}
			})
		}

		//模态框中的保存绑定保存单击事件
		$("#emp_save_btn").click(function(){
			
			//将模态框中表单的数据提交给服务器并进行保存
			
			//提交前进行前段校验
			if(!validate_add_form()){
				return false;
			} 
			
			//如果提交按钮ajax属性为error则不进行提交
			if($("#emp_save_btn").attr("ajax")=="error"){
				return false;
			}
			
			//alert($("#empAddModal form").serialize());
			 $.ajax({
				url:"${APP_PATH}/emp",
				type:"POST",
				data:$("#empAddModal form").serialize(),
				success:function(result){
					//alert(result.msg);
					
					//console.log(result);
					if(result.code==100){
						$("#empAddModal").modal('hide');
						//2.显示最后一页数据
						to_pages(totalInfo);
					}else{
						console.log(result);
						if(result.extend.fieldErrors.empName!=undefined){
							show_validate_msg("#empName_add_input","error",result.extend.fieldErrors.empName);
						}
						if(result.extend.fieldErrors.email!=undefined){
							show_validate_msg("#email_add_input","error",result.extend.fieldErrors.email);
						}
						
					}
					//1.关闭模态框
					
				}
			});
		})
		
		//表单数据校验
		//用户名校验
		function validate_add_form(){
			var empName = $("#empName_add_input").val();
			var regName = /(^[a-z0-9_-]{5,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
			//alert(regName.test(empName));
			if(!regName.test(empName)){
				//alert("用户名不合格！");
				show_validate_msg("#empName_add_input","error","用户名格式不合法！");
				
				return false;
			}else{
				show_validate_msg("#empName_add_input","success","");
			}
			
			//邮箱格式校验
			var email = $("#email_add_input").val();
			var regemail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if(!regemail.test(email)){
				//alert("邮箱格式不合法！");
				show_validate_msg("#email_add_input","error","邮箱格式不合法！");
				
				return false;
			}else{
				show_validate_msg("#email_add_input","success","");
				return true;
			}
			
		}
		//校验函数
		function show_validate_msg(elem,status,msg){
			$(elem).parent().removeClass("has-success has-error")
			
			if(status=="success"){
				$(elem).parent().addClass("has-success");
				$(elem).next("span").text(msg);
			}else if(status=="error"){
				$(elem).parent().addClass("has-error");
				$(elem).next("span").text(msg);
			}
		}
		
		//用户名验证
		$("#empName_add_input").change(function(){
			var empName = this.value;
			$.ajax({
				url:"${APP_PATH}/checkname",
				data:"empName="+empName,
				type:"POST",
				success:function(result){
					//console.log(result);
					if(result.code==200){
						show_validate_msg("#empName_add_input","error",result.extend.ajMsg);
						$("#emp_save_btn").attr("ajax","error");
					}else{
						show_validate_msg("#empName_add_input","success","");
						$("#emp_save_btn").attr("ajax","success");
						
					}
				}
			})
		});
		
		//编辑按钮单击事件
		$(document).on("click",".edit",function(){
			$("#email_update_input").parent().removeClass("has-error has-success");
			//清除错误显示信息	
			
			$("#email_update_input").next("span").text("");
			
			getDepts("#empUpdateModal select");
			//console.log(result);
			$("#emp_update_btn").attr("empId",$(this).attr("empid"));
			getEmp($(this).attr("empid"));
			$("#empUpdateModal").modal({
				backdrop:"static"
			}); 
		})
		
		function getEmp(id){
			$.ajax({
				url:"${APP_PATH}/emp/"+id,
				
				type:"GET",
				success:function(result){
					//console.log(result.extend.employee);
					//alert(result.extend.depts.deptName);
					var employee = result.extend.employee;
				   
					$("#emp_update_name").text(employee.empName);
					
					
					$("#email_update_input").val(employee.email);
					$("#empUpdateModal input[name=gender]").val([employee.gender]);
					$("#empUpdateModal select").val([employee.dId]);
				}
			})
		} 
		
		//为更新按钮绑定单击事件，并保存数据
		$("#emp_update_btn").click(function(){
			//邮箱格式校验
			
			var email = $("#email_update_input").val();
			var regemail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if(!regemail.test(email)){
				//alert("邮箱格式不合法！");
				show_validate_msg("#email_update_input","error","邮箱格式不合法！");
				
				return false;
			}else{
				show_validate_msg("#email_update_input","success","");
				//return true;
			}
			//alert($(this).attr("empid"));
			 $.ajax({
				url:"${APP_PATH}/emp/"+$(this).attr("empid"),
				
				type:"PUT",
				data:$("#empUpdateModal form").serialize(),
				success:function(result){
				//	alert(result);
				//关闭模态框
				$("#empUpdateModal").modal('hide');
				//跳转到当前页
				to_pages(currentPage);
				}
			})  
		})
		
		//删除单个信息
		$(document).on("click",".delete",function(){
			 var epmName = $(this).attr("empName");
			var con = confirm("确认删除"+epmName+"的信息么？")
			if(con==true){
				$.ajax({
					
					url:"${APP_PATH}/emp/"+$(this).attr("empid"),
					type:"DELETE",
					//data:"_method=DELETE",
					success:function(result){
						console.log(result);
						//关闭模态框
						//$(".delete").modal('hide');
						//跳转到当前页
						to_pages(currentPage);
						
					}
				})
			}/* else{
				to_pages(currentPage);
			} */
			
			
		})
		
		//为checkbox绑定单击选中或取消事件
		$(".check_all").click(function(){
			
			//alert($(this).prop("checked"));
			$(".check_item").prop("checked",$(this).prop("checked"));
		
		})
		
		$(document).on("click",".check_item",function(){
			//alert($(".check_item:checked").length);
			var num = $(".check_item:checked").length==$(".check_item").length;
			$(".check_all").prop("checked",num);
		})
		
		//批量删除
		$("#emp_delete_model_btn").click(function(){
			//alert("批量删除");
			var empNames = "";
			var empIds = "";
			$.each($(".check_item:checked"),function(){
				  empNames += $(this).parents("tr").find("td:eq(2)").text()+",";
				// empName+=empName;
				empIds += $(this).parents("tr").find("td:eq(1)").text()+"-";
			})
			empNames =  empNames.substring(0,empNames.length-1);
			empIds = empIds.substring(0,empIds.length-1);
			//alert(empNames +"/"+empIds);
			var con = confirm("确定删除"+empNames+"的信息么？");
			if(con==true){
				$.ajax({
					url:"${APP_PATH}/emp/"+empIds,
					type:"DELETE",
					//data:"_method=DELETE",
					success:function(result){
						//console.log(result);
						to_pages(currentPage);
					}
				})
			}
			
		})
	</script>
</body>
</html>