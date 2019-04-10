<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
pageContext.setAttribute("path", path);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  
  </head>
  
  <body>
  <center>
   <h3>欢迎${user.username}</h3>光临 <br>
   <a href="${path}/searchItems.shtml">商品管理</a>  <a>订单管理</a>
   </center>
  </body>
</html>
