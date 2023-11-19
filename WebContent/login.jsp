<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>移动端健康管理系统</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="css/login.css" rel="stylesheet"/>
		<script type="text/javascript">
	function loginSys() {
		var loginName = document.getElementById("loginname").value;

		var password = document.getElementById("loginpassword").value;
		if (loginName == "" || loginName == null) {
			alert("登陆账号不能为空!");
			return false;
		}
		if (password == "" || password == null) {
			alert("登陆密码不能为空!");
			return false;
		}
		this.form1.submit();
	}
	function reg() {
		 var obj = new Object();
         window.showModalDialog("user_Reg.jsp",obj,"dialogWidth=500px;dialogHeight=400px");
	}
</script>
	</head>

	<body>

		<div id="login">

			<div id="top">
				<div id="top_left">
					 
					<img src="images/login_03.gif" />
				</div>
				<div id="top_center"></div>
			</div>

			<div id="center">
				<div id="center_left"></div>
				<div id="center_middle">
					<form action="LoginServlet"  method="post" id="form1">
					 
						<div id="user">
							用 户
							<input type="text" name="loginname" id="loginname" />
						</div>
						<div id="password">
							密 码
							<input type="password" name="password" id="loginpassword" />
						</div>
						   <input type="hidden" id="logintype" name="logintype" value="1" />
						<!--<div id="password">
							用户类型
							 <select id="logintype" name="logintype" style="width:100px"><option value="0">普通用户</option><option value="1">管理员</option></select>
						</div>
						--><div id="btn">
							<a href="#" onClick="return loginSys()";>登录</a> <!--<a href="#" onClick="return reg()";>注册</a>
						--></div>
					</form>
				</div>
				<div id="center_right"></div>
			</div>
			<div id="down">
				<div id="down_left">
					<div id="inf">
						<span class="inf_text">版本信息</span>
						<span class="copyright">后台管理 2023 v1.0</span>
					</div>
				</div>
				<div id="down_center"></div>
			</div>

		</div>
	</body>
</html>

<script type="text/javascript">
	window.onload = function() {	 
		var alertNote = "${alertNote}";
		  if (alertNote == "0") {
			alert("登陆失败，请检查用户名和密码是否正确!");
		}
	} 
</script>
