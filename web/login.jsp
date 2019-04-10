<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
 String path=request.getContextPath();
 pageContext.setAttribute("path", path);

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>青柠邮箱</title>
	<link rel="stylesheet" type="text/css" href="${path}/css/jiyun.css">
	<script src="${path}/js/jquery.min.js" type="text/javascript">
	</script>
<script>
  function login(){
	  var username=$("#username").val();
	  var password=$("#password").val();
	  $.ajax( {
			type :'post',
			url :"${path}/login.shtml?username="+username+"&password="+password,
			success : function(data) {
				if(data==0){
					$("#tips").html("用户名或者密码错误");
				}else{
					window.location.href="${path}/index.shtml";
					}
			}
		});
	  
	  
  }

</script>
</head>
<body>
	<div class="logo">
		<img src="${path}/images/picture_03.gif" class="logoimg">
		<p class="p1">积云学生管理系统</p>
	</div>
	<div class="main">
		<img src="${path}/images/picture_07.gif" class="pic">
		<form  class="form" >
		    <p class="p3">用户登录</p>
		    <img src="${path}/images/picture_10.gif" class="userimg"><br/>
			<input type="text"  class="txt" placeholder="账号" name="username" id="username"><br/>
			<input type="password"  class="psw" placeholder="密码" name="password" id="password"><br/>
			
			<p class="p3" style="margin-top:-14px;"><span style="color:red" id="tips"></span></p>
       		<p class="p3" style="margin-top:40px;"><button type="button" onclick="login()">登录</button></p>
		</form>
	</div>
	<div class="footer">
		<p class="p4"><a href="#">关于青檬</a><a href="#">社区规范</a><a href="#">联系我们</a><a href="#">隐私政策</a>|<a href="#" style="margin-left: 17px;">公司版权所有&copy;1997-2015</a></p>
	</div>

</body>

</html>
