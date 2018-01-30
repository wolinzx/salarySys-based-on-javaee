<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML >
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'searchstaff.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="/MyDemo/css/admin/basesalary.css">
	<link rel="stylesheet" type="text/css" href="css/admin/iconfont.css"/>
	<script src="/MyDemo/js/basesalary.js"></script>
	<script src="/MyDemo/js/util.js"></script>
  </head>
  <body>
    <div id="main">
			<div id="main-head">
				<input id="main-head-search" type="text" name="searchvalue" placeholder="输入工号"/><i class="iconfont icon-sousuo"></i>
				<input id="searchall" type="button" value="搜索全部" onclick="searchAll()"/>
			</div>
			<div id="main-content">
				<i class="iconfont icon-daifukuan" id="indexicon"><span>此页面显示工资信息</span></i>
			</div>
			<div id="main-page">每页最多显示6条记录&nbsp;|&nbsp;<a id="up">上一页</a>&nbsp;|&nbsp;<a id="next">下一页</a>&nbsp;|</div>
			<div id="info-title">&nbsp;&nbsp;&nbsp;<span class="iconfont icon-shenfenzheng" style="color:white"></span>&nbsp;&nbsp;个人信息</div>
			<div id="taxtip">工资计算详情：(基本工资-(迟到次数+早退次数)*20-请假*(基本工资/30)-税收)</div><img src="/MyDemo/img/tax.png" />
			<div id="info-btn"><input type="button" id="xiugaibtn" value="计算总获得工资" onclick="changeSt()"/></div>
		</div>
  </body>
</html>
