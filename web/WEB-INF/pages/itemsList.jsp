<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	pageContext.setAttribute("path", path);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

</head>

<body>
	<center>
		<h3>商品列表</h3>
		<form method="post" action="${path}/searchItemsByVo.shtml">
		商品名称:<input type="text" name="itemsName" value="${items.itemsName}"> 商品价格:<input
			type="text" name="price" value="${items.price}"> <input type="submit" value="搜索">
		 </form>
		 <a href="${path}/toAddItems.shtml">添加</a>
		<table border="1"  width="500">
			<tr height="50">
				<td>商品编号</td>
				<td>商品名称</td>
				<td>商品价格</td>
				<td>编辑</td>
			</tr>
			<c:forEach items="${list}" var="items">
				<tr height="50">
					<td>${items.itemsId}</td>
					<td>${items.itemsName}</td>
					<td>${items.price}</td>
					<td>
					<a href="${path}/deleteItems.shtml?id=${items.itemsId}">删除</a>
					<a href="${path}/toUpdateItems.shtml?id=${items.itemsId}">编辑</a>
					
					</td>
				</tr>
			</c:forEach>
		</table>

	</center>
</body>
</html>
