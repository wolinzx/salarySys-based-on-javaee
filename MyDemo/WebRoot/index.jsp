<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户登录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/login.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/iconfont.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/admin/iconfont.css">
  </head>
  
  <body>
  <SCRIPT LANGUAGE="JavaScript">
	function changeCode() {  
	       var img=document.getElementById("checkCode"); 
	       img.src="<%=basePath%>imgcode.jsp?date="+new Date();//此处很重要，不加后面的就不会变
	}    
	</SCRIPT>
  <div class="head">
  <%-- 	<img class="logoimg" src="<%=basePath%>img/logo.ico" /> --%>
  	<span>工资信息管理系统</span>
  </div>
  <div><img  class="mainimg" src="<%=basePath%>img/main.jpg" ></div>
  <div class="user"><i class="iconfont icon-yonghu" style="font-size:90px;"></i></div>
  <div class="login">
    <form action="servlet/LoginServlet" method="post">
    	<div class="title">用户登录</div>
    	<div><i class="iconfont icon-wode" ></i><input type="text" class="text" name="username" placeholder="Username"/></div>
    	<div><i class="iconfont icon-shouhuodizhiyebianji" ></i><input type="password" class="text" name="password" placeholder="Password"/></div>
    	<div>
    		<i id="codei" class="iconfont icon-qiandao" ></i>
	    	<input type="text" class="code" name="code" placeholder="Code"/>
	    	<div class="codeimg"><img id="checkCode" src="<%=basePath%>imgcode.jsp"/>
	    	<span class="change" onclick="changeCode();" >&nbsp;&nbsp;点击刷新</span>
	    	</div>
    	</div>
    	<div class="radio">
    	<input type="radio" name="usertype" value="0" />部门
    	<input type="radio" name="usertype" class="radio" value="1"/>员工
    	<input type="radio" name="usertype" class="radio" value="2" checked/>管理员
    	</div>
    	<%
			String message = (String) request.getAttribute("message");
			if (message != null) {
				out.print("<font color='red'>&nbsp;&nbsp;" + message + "</font>");
			}
		%>
    	<div class="button">
    	<input type="submit" class="submit1" value="登陆" />

   		</div>
    </form>
    
    </div>
    <div class="foot">Copyright © 2017 - 2018  403 Inc. All Rights Reserved</div>
  </body>
</html>
