<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head id="Head1" runat="server">
		<link href="css1.css" rel="stylesheet" type="text/css" />
		<title>移动端健康管理系统</title>
		<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.STYLE1 {
	font-size: 12px;
	color: #FFFFFF;
}

.STYLE3 {
	font-size: 12px;
	color: #033d61;
}
-->
</style>
		<style type="text/css">
.menu_title SPAN {
	FONT-WEIGHT: bold;
	LEFT: 3px;
	COLOR: #ffffff;
	POSITION: relative;
	TOP: 2px
}

.menu_title2 SPAN {
	FONT-WEIGHT: bold;
	LEFT: 3px;
	COLOR: #FFCC00;
	POSITION: relative;
	TOP: 2px
}
</style>
	</head>
	<body>
		<form id="form1" runat="server">

			<script>
var he=document.body.clientHeight-105
document.write("<div id=tt style=height:"+he+";overflow:hidden>")
    </script>

			<table width="165" height="100%" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td height="28" background="../images/main_40.gif">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="19%">
									&nbsp;
								</td>
								<td width="81%" height="20">
									<span class="STYLE1">系统菜单</span>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td valign="top">
						<table width="151" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										 
										<tr>
											<td background="../images/main_51.gif" id="submenu1">
												<div class="sec_menu">
													<table width="100%" border="0" cellspacing="0"
														cellpadding="0">
														  
																	<tr>
																		<td width="16%" height="25">
																			<div align="center">
																				<img src="../images/left.gif" width="10" height="10" />
																			</div>
																		</td>
																		<td width="84%" height="23">
																			<table width="95%" border="0" cellspacing="0"
																				cellpadding="0">
																				<tr>

																					<td style="cursor: hand; height: 20px;"
																						onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3

'; "
																						onmouseout="this.style.borderStyle='none'">
																						<span class="STYLE3"><a href="news2_Add.jsp" target="right">添加健康知识</a> </span>
																					</td>
																				</tr>
																			</table>
																		</td>
																	</tr>
																	<tr>
																		<td width="16%" height="25">
																			<div align="center">
																				<img src="../images/left.gif" width="10" height="10" />
																			</div>
																		</td>
																		<td width="84%" height="23">
																			<table width="95%" border="0" cellspacing="0"
																				cellpadding="0">
																				<tr>
																					<td style="cursor: hand; height: 20px;"
																						onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3

'; "
																						onmouseout="this.style.borderStyle='none'">
																						<span class="STYLE3"><a href="<%=path%>/News2Servlet?action=list" target="right">健康知识</a> </span>
																					</td>
																				</tr>
																			</table>
																		</td>
																	</tr>
																	<tr>
												<td width="16%" height="25">
													<div align="center">
														<img src="../images/left.gif" width="10" height="10" />
													</div>
												</td>
												<td width="84%" height="23">
													<table width="95%" border="0" cellspacing="0"
														cellpadding="0">
														<tr>
															<td height="20" style="cursor: hand"
																onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3

'; "
																onmouseout="this.style.borderStyle='none'">
																<span class="STYLE3"><a
																	href="<%=path%>/JtcyServlet?action=list" target="right">亲友信息管理</a>
																</span>
															</td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td width="16%" height="25">
													<div align="center">
														<img src="../images/left.gif" width="10" height="10" />
													</div>
												</td>
												<td width="84%" height="23">
													<table width="95%" border="0" cellspacing="0"
														cellpadding="0">
														<tr>
															<td height="20" style="cursor: hand"
																onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3

'; "
																onmouseout="this.style.borderStyle='none'">
																<span class="STYLE3"><a
																	href="<%=path%>/DanganServlet?action=list" target="right">健康档案管理管理</a>
																</span>
															</td>
														</tr>
													</table>
												</td>
											</tr>
																	<tr>
												<td width="16%" height="25">
													<div align="center">
														<img src="../images/left.gif" width="10" height="10" />
													</div>
												</td>
												<td width="84%" height="23">
													<table width="95%" border="0" cellspacing="0"
														cellpadding="0">
														<tr>
															<td height="20" style="cursor: hand"
																onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3

'; "
																onmouseout="this.style.borderStyle='none'">
																<span class="STYLE3"><a
																	href="<%=path%>/UserServlet?action=list" target="right">注册用户管理</a>
																</span>
															</td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td width="16%" height="25">
													<div align="center">
														<img src="../images/left.gif" width="10" height="10" />
													</div>
												</td>
												<td width="84%" height="23">
													<table width="95%" border="0" cellspacing="0"
														cellpadding="0">
														<tr>
															<td height="20" style="cursor: hand"
																onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3

'; "
																onmouseout="this.style.borderStyle='none'">
																<span class="STYLE3"><a
																	href="<%=path%>/admin/admin_Add.jsp" target="right">添加系统用户</a>
																</span>
															</td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td height="23">
													<div align="center">
														<img src="../images/left.gif" width="10" height="10" />
													</div>
												</td>
												<td height="23">
													<table width="95%" border="0" cellspacing="0"
														cellpadding="0">
														<tr>
															<td height="20" style="cursor: hand"
																onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3

'; "
																onmouseout="this.style.borderStyle='none'">
																<span class="STYLE3"><a
																	href="<%=path%>/AdminServlet?action=list"
																	target="right">系统用户列表</a> </span>
															</td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td height="23">
													<div align="center">
														<img src="../images/left.gif" width="10" height="10" />
													</div>
												</td>
												<td height="23">
													<table width="95%" border="0" cellspacing="0"
														cellpadding="0">
														<tr>
															<td height="20" style="cursor: hand"
																onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3

'; "
																onmouseout="this.style.borderStyle='none'">
																<span class="STYLE3"><a
																	href="<%=path%>/loginOut.jsp">安全退出</a> </span>
															</td>
														</tr>
													</table>
												</td>
											</tr>
																</table>
															</td>
														</tr>
														<tr>
															<td height="5">
																<img src="../images/main_52.gif" width="151" height="5" />
															</td>
														</tr>
													</table>
												</div>
											</td>
										</tr>

									   
								 
							</table>
					 
					</td>
				</tr>
			</table>
			</td>
			</tr>

			</table>
			 


			<script>



function showsubmenu(sid)
{
whichEl = eval("submenu" + sid);
imgmenu = eval("imgmenu" + sid);
if (whichEl.style.display == "none")
{
eval("submenu" + sid + ".style.display=\"\";");
imgmenu.background="images/main_47.gif";
}
else
{
eval("submenu" + sid + ".style.display=\"none\";");
imgmenu.background="images/main_48.gif";
}
}
    </script>

		</form>
	</body>
</html>

