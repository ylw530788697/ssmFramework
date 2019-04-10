<%--
  Created by IntelliJ IDEA.
  User: v_liwenyang
  Date: 2019/4/9
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
  String path = request.getContextPath();
  pageContext.setAttribute("path", path);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <center>
    <h3>欢迎${user.username}</h3>光临 <br>
    <a href="${path}/searchItems.shtml">商品管理</a>  <a>订单管理</a>
  </center>
  </body>
</html>
