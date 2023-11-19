<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.myweb.domain.*"%> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			String logintype=session.getAttribute("logintype").toString();
			
		  if(logintype==null)
		  {
		  %>
		       <script>document.location.href="login.jsp"</script>
		  <%}							 
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>移动端健康管理系统</title>
	</head>

	<frameset rows="98,*,8" frameborder="no" border="0" framespacing="0">
		<frame src="<%=path%>/admin/top.jsp" name="topFrame" scrolling="No"
			noresize="noresize" id="topFrame" />
		<frame src="<%=path%>/admin/center.jsp" name="mainFrame" id="mainFrame" />
		<frame src="<%=path%>/admin/down.jsp" name="bottomFrame" scrolling="No"
			noresize="noresize" id="bottomFrame" />
	</frameset>
	<noframes>
		<body>
		</body>
	</noframes>
</html>