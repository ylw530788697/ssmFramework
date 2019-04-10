<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
    <h3>修改商品</h3>
    <form method="post" action="${path}/updateItems.shtml">
    <table>
    <input type="hidden" name="itemsId" value="${items.itemsId}"/>
    <tr><td>商品名称:</td><td><input type="text" name="itemsName" value="${items.itemsName}"></td>  </tr>
    <tr><td>商品价格:</td><td><input type="text" name="price" value="${items.price}"></td>  </tr>
    <tr><td colspan="2"><input type="submit" value="保存"></td>  </tr>
    </table>
    
    </form>
    
    </center>
  </body>
</html>
