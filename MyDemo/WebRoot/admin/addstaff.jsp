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
		<script src="/MyDemo/js/addstaff.js"></script>
	</head>
	<body>
		<div id="main">
			<div id="main-head">
				&nbsp;&nbsp;&nbsp;<span class="iconfont icon-jiajianzujianjiahao"></span>&nbsp;&nbsp;添加员工
			</div>
			<div id="main-content">
				<table id="addtable">
					<tr>
						<td>姓名:&nbsp;</td>
						<td><input type="text" name="姓名" id="userName"/><span>请输入姓名</span></td>
					</tr>
					<tr>
						<td>密码:&nbsp;</td>
						<td><input type="password" name="密码" id="passWord" value="123456"/><span>密码默认123456</span></td>
					</tr>
					<tr>
						<td>性别:&nbsp;</td>
						<td>
						<input type="radio" name="sex" value="男" class="sex" checked/>男
						<input type="radio" name="sex" value="女" class="sex"/>女
						</td>
					</tr>
					<tr>
						<td>工龄:&nbsp;</td>
						<td><input type="text" name="工龄" id="seniority"/><span>请输入工龄</span></td>
					</tr>
					<tr>
						<td>生日:&nbsp;</td>
						<td><input type="date" name="生日" id="birth"/></td>
					</tr>
					<tr>
						<td>岗位:&nbsp;</td>
						<td>
							<select name="" id="post"> 
								<option value="研发">研发</option> 
								<option value="设计">设计</option>
								<option value="产品经理">产品经理</option> 
								<option value="策划">策划</option> 
								<option value="运营">运营</option> 
								<option value="编辑">编辑</option>  
							</select> 
						</td>
					</tr>
					<tr>
						<td>部门:&nbsp;</td>
						<td><input type="text" name="部门" id="depid"/><span>请输入所属部门</span></td>
					</tr>
					<tr>
						<td>手机:&nbsp;</td>
						<td><input type="text" name="手机" id="phone"/><span>请输入联系方式</span></td>
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
