<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'news_List.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/base.css" />
	</head>

	<body style="text-align: center" onload="noteAlert()">
		<table width="98%" border="0" align="center" cellpadding="0"
			cellspacing="0" style="margin-bottom: 8px">
			<tr>
				<td>
					<div style='float: left'>
						<img height="16" src="images/add.png" width="16" />
						&nbsp;
						<strong>新闻分类列表</strong>
					</div>
				</td>
			</tr>
			<tr>
				<td height="1" background="<%=path%>/images/sp_bg.gif" style='padding: 0px'>
				</td>
			</tr>
		</table>
		<table class="table">
			<thead>
				<tr>
					<td style="width: 5%;" class="gvtitle">
						序号
					</td>
					<td style="width: 10%;" class="gvtitle">
					   新闻分类名称
					</td>
					 
					<td style="width: 15%;" class="gvtitle">
					 创建时间
					</td>

					<td class="gvtitle" style="width: 10%;">
						操作
					</td>
				</tr>
				<c:forEach var="list" items="${newsTypeList}" varStatus="status">
					<tr>
						<td>
							${status.count}
						</td>
						<td>
							${list.TYPENAME}
						</td>
						<td>
						<fmt:formatDate value="${list.CREATETIME}" pattern="yyyy-MM-dd hh:mm:ss"/>
			
						</td>

						<td>
							<a href="NewsTypeServlet?action=edit&id=${list.ID}">修改</a>&nbsp;|&nbsp;
							<a href="NewsTypeServlet?action=delete&id=${list.ID}">删除</a>
						</td>
					</tr>
				</c:forEach>
			</thead>
			<tbody>
			</tbody>
		</table>

	</body>
</html>
<script>
	//获得标识并显示：             
	function noteAlert() {

		var alertNote = "${alertNote}";
		if (alertNote == "1") {
			alert("操作成功!");

		} else if (alertNote == "0") {
			alert("操作失败，请联系管理员!");
		}
	}
</script>