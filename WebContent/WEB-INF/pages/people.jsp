<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = "http://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath();
			request.setAttribute("basePath", path);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--声明文档兼容模式，表示使用IE浏览器的最新模式-->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!--设置视口的宽度(值为设备的理想宽度)，页面初始缩放值<理想宽度/可见宽度>-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

<title>Insert title here</title>
<link href="${basePath}/resources/css/bootstrap.min.css"
	rel="stylesheet" />
<style>
* {
	margin: 0px;
	padding: 0px;
}

.close {
	display: block;
	width: 5px;
	height: 5px;
	position: absolute;
	right: 10px;
	top: 0px;
	cursor: pointer;
}
</style>
<script type="text/javascript">
	var basePath = "${basePath}";
</script>
<script type="text/javascript" src="${basePath}/resources/js/jquery.js"></script>
<script type="text/javascript"
	src="${basePath}/resources/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(function(){ 
	　　$("#addPeople").click(function(){
	    $("#addTable").show(); 
	　　});
	   $(".close").click(function(){
		$("#addTable").hide(); 
		$("#updateTable").hide();
	   });
	   $(".updateButton").click(function(){
		  var pId = $(this).parent().parent().children(":first").text();
		 // alert(pId);
		  $.ajax({ url: "${basePath}/People/get",
			       type:"get",
			       data:{
			    	  "pId":pId
			       },
			       dataType:"json",
			       success: function(result){
			       var pId = result.pId;
			       var pName = result.pName;
			       var age = result.age;
			       var job = result.job;
			       $("#updateTable").find("input").eq(0).val(pId);
			       $("#updateTable").find("input").eq(1).val(pName);
			       $("#updateTable").find("input").eq(2).val(age);
			       $("#updateTable").find("input").eq(3).val(job);
			       $("#updateTable").show();
			         }});
	   });
	});
</script>
</head>
<body>
	<table class="table">
		<thead>
			<tr>
				<th>People-ID</th>
				<th>姓名</th>
				<th>年龄</th>
				<th>职业</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach var="people" items="${pageBean.pageData}">
				<tr>
					<th>${people.pId}</th>
					<th>${people.pName}</th>
					<th>${people.age}</th>
					<th>${people.job}</th>
					<th><a href="${basePath}/People/delete?pId=${people.pId}">删除</a></th>
					<th><a class="updateButton" href="javascript:void(0);">编辑</a></th>
				</tr>
			</c:forEach>

		</tbody>
	</table>
	<ul class="pagination"
		style="margin: 0 auto; width: 300px; display: block;">
		<!-- 上一页 -->
		<!-- 判断当前页是否是第一页 -->
		<c:if test="${pageBean.currentPage==1 }">
			<li class="disabled"><a href="javascript:void(0);"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
			</a></li>
		</c:if>
		<c:if test="${pageBean.currentPage!=1 }">
			<li><a
				href="${basePath }/People/page?page=${pageBean.currentPage-1}"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
			</a></li>
		</c:if>
		<c:forEach begin="1" end="${pageBean.totalPage }" var="page">


			<!-- 判断当前页 -->
			<c:if test="${pageBean.currentPage==page }">
				<li class="active"><a href="javascript:void(0);">${page}</a></li>
			</c:if>
			<c:if test="${pageBean.currentPage!=page }">
				<li><a href="${basePath}/People/page?page=${page}">${page}</a></li>
			</c:if>

		</c:forEach>
		<!-- 判断当前页是否是最后一页 -->
		<c:if test="${pageBean.currentPage==pageBean.totalPage }">
			<li class="disabled"><a href="javascript:void(0);"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a></li>
		</c:if>
		<c:if test="${pageBean.currentPage!=pageBean.totalPage }">
			<li><a
				href="${basePath }/People/page?page=${pageBean.currentPage+1}"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a></li>
		</c:if>

	</ul>
	<br />
	<br />
	<button type="button" class="btn btn-primary" id="addPeople"
		style="display: block;">新增</button>
	<div id="addTable"
		style="margin-top: 20px; padding-left: 500px; display: none;">
		<form action="${basePath}/People/add" method="post"
			class="bs-example bs-example-form" role="form"
			style="width: 300px; border-color: #F0F0F0; padding: 20px; border-style: solid; border-width: 2px; position: relative;">
			<h4>增加一个People</h4>
			<p class="close">X</p>
			</br>
			<div class="input-group">
				<span class="input-group-addon">姓名</span> <input name="pName"
					type="text" class="form-control" required="required"
					placeholder="请输入名字" style="width: 200px" />
			</div>
			</br>
			<div class="input-group">
				<span class="input-group-addon">年龄</span> <input name="age"
					type="text" class="form-control" required="required"
					onkeyup="value=value.replace(/[^\d]/g,'')" placeholder="请输入数字"
					style="width: 200px" />
			</div>
			</br>
			<div class="input-group">
				<span class="input-group-addon">职业</span> <input name="job"
					type="text" class="form-control" required="required"
					placeholder="请输入职业" style="width: 200px" />
			</div>
			</br> <input type="submit" value="提交" /> </br>
		</form>
	</div>
	<div id="updateTable"
		style="margin-top: 20px; padding-left: 500px; display: none;">
		<form action="${basePath}/People/update" method="post"
			class="bs-example bs-example-form" role="form"
			style="width: 300px; border-color: #F0F0F0; padding: 20px; border-style: solid; border-width: 2px; position: relative;">
			<h4>编辑信息</h4>
			<p class="close">X</p>
			</br>
			<div class="input-group">
				<input name="pId" type="hidden" />
			</div>
			<div class="input-group">
				<span class="input-group-addon">姓名</span> <input name="pName"
					type="text" class="form-control" required="required"
					placeholder="请输入名字" style="width: 200px" />
			</div>
			</br>
			<div class="input-group">
				<span class="input-group-addon">年龄</span> <input name="age"
					type="text" class="form-control" required="required"
					onkeyup="value=value.replace(/[^\d]/g,'')" placeholder="请输入数字"
					style="width: 200px" />
			</div>
			</br>
			<div class="input-group">
				<span class="input-group-addon">职业</span> <input name="job"
					type="text" class="form-control" required="required"
					placeholder="请输入职业" style="width: 200px" />
			</div>
			</br> <input type="submit" value="提交" /> </br>
		</form>
	</div>
</body>
</html>