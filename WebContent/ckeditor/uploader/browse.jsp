<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>ͼƬ���</title>
<script type="text/javascript">
//��κ������ص㣬��Ȼ���ܺ�CKEditor������
function funCallback(funcNum,fileUrl){
	var parentWindow = ( window.parent == window ) ? window.opener : window.parent;
	parentWindow.CKEDITOR.tools.callFunction(funcNum, fileUrl);
	window.close();
}
</script>
</head>
<body>
<%
	String path = request.getContextPath() + "/";
	String type = "";
	if(request.getParameter("type") != null)//��ȡ�ļ�����
		type = request.getParameter("type").toLowerCase() + "/";
	String clientPath = "upload/" + type;
	File root = new File(request.getSession().getServletContext().getRealPath(clientPath));
	if(!root.exists()){
		root.mkdirs();
	}
	String callback = request.getParameter("CKEditorFuncNum");
	File[] files = root.listFiles();
	if(files.length > 0){
		for(File file:files ) {
			String src = path + clientPath + file.getName();
			out.println("<img width='110px' height='70px' src='" + src + "' alt='" + file.getName() + "' onclick=\"funCallback("+callback+",'"+ clientPath + file.getName() +"')\">");
		}
	}else{
		out.println("<h3>δ��⵽��Դ��</h3>");
	}
 %>
</body>
</html>