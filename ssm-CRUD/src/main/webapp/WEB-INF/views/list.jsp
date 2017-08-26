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
<script type="text/javascript" src="${APP_PATH }/static/js/scripts/jquery.js"></script>
 <link href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
   <script src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script> 
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h1>SSH-CRUD</h1>
			</div>
		</div>
		<!-- 按钮 -->
		<div class="row">
			<div class="col-md-4 col-md-offset-8">
				<button type="button" class="btn btn-success">新增</button>
				<button type="button" class="btn btn-danger">删除</button>
			</div>
		</div>
		<!-- 表格数据 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover">
					<tr>
						<th>#</th>
						<th>姓名</th>
						<th>性别</th>
						<th>Email</th>
						<th>部门</th>
						<th>操作</th>
					</tr>
					<c:forEach items="${pageInfo.list }" var="emp">
						<tr>
							<th>${emp.empId }</th>
							<th>${emp.empName }</th>
							<th>${emp.gender=="M"?"男":"女" }</th>
							<th>${emp.email }</th>
							<th>${emp.department.deptName }</th>
							<th>
								<button type="button" class="btn btn-success btn-sm">
								<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> 编辑
								</button>
								<button type="button" class="btn btn-danger btn-sm">
								<span class="glyphicon glyphicon-trash" aria-hidden="true"></span> 删除
								</button>
							</th>
					  </tr>
					</c:forEach>
					
					
				</table>
			</div>
		</div>
		<!-- 分页条 -->
		<div class="row">
			<div class="col-md-6">
				当前页${pageInfo.pageNum}, 总共${pageInfo.pages }, 总共${pageInfo.total }条记录
			</div>
			<div class="col-md-6">
				<nav aria-label="Page navigation">
					  <ul class="pagination">
					  <li><a href="${APP_PATH }/emps?pn=1">首页</a></li>
					 
					  <!-- 上一页 -->
					  <c:if test="${pageInfo.hasPreviousPage }">
					  	<li>
					      <a href="${APP_PATH }/emps?pn=${pageInfo.pageNum-1}" aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					      </a>
					    </li>
					  </c:if>
					  
					   
					    
					    <c:forEach items="${pageInfo.navigatepageNums }" var="p">
					    	<c:if test="${p==pageInfo.pageNum }">
					    		<li class="active"><a href="#">${p }</a></li>
					    	</c:if>
					    	<c:if test="${p!=pageInfo.pageNum }">
					    		 <li><a href="${APP_PATH }/emps?pn=${p }">${p }</a></li>
					    	</c:if>
					    	
					    </c:forEach>
					    
					    <!-- 下一页 -->
					    <c:if test="${pageInfo.hasNextPage }">
					    	<li>
					     		 <a href="${APP_PATH }/emps?pn=${pageInfo.pageNum+1}" aria-label="Next">
					        		<span aria-hidden="true">&raquo;</span>
					      		</a>
					    </li>
					    </c:if>
					    
					    <li><a href="${APP_PATH }/emps?pn=${pageInfo.pages}">尾页</a></li>
					  </ul>
				</nav>
			</div>
		</div>
	</div>
</body>
</html>