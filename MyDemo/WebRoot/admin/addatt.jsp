<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8" />
		<title></title>
		<link rel="stylesheet" type="text/css" href="/MyDemo/css/admin/addstaff.css"/>
		<link rel="stylesheet" type="text/css" href="/MyDemo/css/admin/iconfont.css"/>
		<script src="/MyDemo/js/addatt.js"></script>
	</head>
	<body>
		<div id="main">
			<div id="main-head">
				&nbsp;&nbsp;&nbsp;<span class="iconfont icon-jiajianzujianjiahao"></span>&nbsp;&nbsp;出勤登记
			</div>
			<div id="main-content">
				<table id="addtable">
					<tr>
						<td>工号:&nbsp;</td>
						<td><input type="text" name="工号" id="staffid"/><span>请输入工号</span></td>
					</tr>
					<tr>
						<td>登记日期:&nbsp;</td>
						<td><input type="date" name="登记日期" id="attdate"/></td>
					</tr>
					<tr>
						<td>登记类型:&nbsp;</td>
						<td>
							<select name="" id="type"> 
								<option value="请假">请假</option> 
								<option value="迟到">迟到</option>
								<option value="早退">早退</option> 
							</select> 
						</td>
					</tr>			
				</table>
			</div>
			
			<div id="main-foot">
				<input type="button" value="保存" onclick="addSt()"/>
				<input type="reset" value="重置"/>
			</div>
		</div>
	</body>
</html>
