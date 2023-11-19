<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title></title>
		<base target="_self">
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/base.css" />

	</head>
	<body leftmargin="8" topmargin='8' onload="noteAlert()">
		<form id="form" action="<%=path%>/UserServlet?action=editSave" method="post">
		<input type="hidden" name="id" id="id" value='<c:out value="${user.id}"></c:out>'/>
	
			<table width="98%" border="0" align="center" cellpadding="0"
				cellspacing="0" style="margin-bottom: 8px">
				<tr>
					<td>
						<div style='float: left'>
							<img height="16" src="images/add.png" width="16" />
							&nbsp;
							<strong>修改 -用户</strong>
						</div>
					</td>
				</tr>
				<tr>
					<td height="1" background="images/sp_bg.gif" style='padding: 0px'>
					</td>
				</tr>
			</table>
			<table width="98%" align="center" border="0" cellpadding="4"
				cellspacing="1" bgcolor="#CBD8AC" style="margin-bottom: 8px">
				<tr>
					<td colspan="4" background="images/wbg.gif" class='title'>
						<div style='float: left'>
							<span>必填项 <font color="red">(*)</font> </span>
						</div>
					</td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td width="12%" align="center">
						账号:
					</td>
					<td width="38%">
						 ${user.loginname}
					</td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td width="12%" align="center">
						登录密码:
					</td>
					<td width="38%">
						<input type="text" ID="loginpsw" name="loginpsw"
							Height="21px" Width="196px" value="${user.loginpsw}"/>
						&nbsp;
						<font color="red">(*)</font>
					</td>
				</tr>
				 
				 <tr bgcolor="#FFFFFF">
					<td width="12%" align="center">
						姓名\单位名称:
					</td>
					<td width="38%">
						<input type="text" ID="username" name="username" Height="21px" value="${user.username}"
							Width="196px" />
					</td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td width="12%" align="center">
						 邮箱:
					</td>
					<td width="38%">
						<input type="text" ID="email" name="email" Height="21px" value="${user.email}"
							Width="196px" />
					</td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td width="12%" align="center">
						 电话:
					</td>
					<td width="38%">
						<input type="text" ID="tel" name="tel" Height="21px" Width="196px" value="${user.tel}"/>
					</td>
				</tr>

				<tr bgcolor="#FFFFFF">
					<td align="center">
					</td>
					<td colspan="3">
						&nbsp;&nbsp;
						<br />
						<input type="submit" value="保存" />
						&nbsp;
						<a href="<%=path%>/UserServlet?action=list" target="right">返回列表</a>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
<script>
	//获得标识并显示：             
	function noteAlert() {
		var alertNote = "${alertNote}";
		if (alertNote == "1") {
			alert("操作成功!");

		} else if (alertNote == "0") {
			alert("添加失败，请联系管理员!");
		}
	}
</script>
