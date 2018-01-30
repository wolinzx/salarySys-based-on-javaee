<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta charset="utf-8" />

	<link rel="stylesheet" type="text/css" href="css/welcome.css">
	<link rel="stylesheet" type="text/css" href="css/admin/iconfont.css"/>
	<script src="js/welcome.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/tiaozhuan.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/util.js" type="text/javascript" charset="utf-8"></script>
	
  </head>
 <body class="welcome">
	<div id="main">
		<div id="main-head">
			<div id="main-head-title">
				<i class="iconfont icon-weishiyong" style="font-size: 30px;"></i><span>&nbsp;&nbsp;管理界面</span>
			</div>
			<div id="main-head-user">
				<span>你好，<% out.print(session.getAttribute("username")); %>&nbsp;<i class="iconfont icon-guanyanren" style="font-size: 20px;"></i>&nbsp;|&nbsp;
				<%  
				//显示本地时间
				    java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(    
				     "yyyy-MM-dd");    
				   java.util.Date currentTime = new java.util.Date();    
				   String time = simpleDateFormat.format(currentTime).toString();  
				   out.println("今天日期："+time);  
				 %>  
				 &nbsp;|&nbsp;<a href="servlet/AdminServlet?para=logout">退出系统</a>&nbsp;
     </span>
			</div>
		</div>
		<div id="main-left">
			<div id="tianc">
				
			</div>
			<ul id="main-left-ul">
				<li class="main-left-ul-li"><i class="iconfont icon-wode"></i>员工信息</li>
				<ul class="main-left-ul-child">
					<li id="addstaff" class="child-li">添加员工</li>
					<li id="searchstaff" class="child-li">查找员工</li>
					
				</ul>
				<li class="main-left-ul-li"><i class="iconfont icon-daifukuan" style="font-size: 18px;"></i>工资管理</li>
				<ul class="main-left-ul-child">
					<li id="basesalary" class="child-li">基本工资</li>
					
				</ul>
				<li class="main-left-ul-li"><i class="iconfont icon-qiandao" style="font-size: 18px;"></i>考勤管理</li>
				<ul class="main-left-ul-child">
					<li id="addatt" class="child-li" >出勤登记</li>
					<li id="searchatt" class="child-li" >出勤列表</li>
					
				</ul>
				<li class="main-left-ul-li"><i class="iconfont icon-pingjia" style="font-size: 18px;"></i>记录查询</li>
				<ul class="main-left-ul-child">
					<li id="record" class="child-li">修改记录</li>
					
				</ul>
			</ul>
		</div>
		<div id="main-right">
				<div id="main-right-img">
				</div>
				
				<iframe src="" width="100%" height="100%" border="none" name="mainright" frameborder="0"></iframe>
		</div>
	</div>
 </body>
</html>
