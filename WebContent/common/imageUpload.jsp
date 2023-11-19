<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.jspsmart.upload.SmartUpload"%>
<%@page import="java.util.Date"%>
<%@page import="com.jspsmart.upload.File"%>
<%@page import="java.awt.Image"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base target="_self">

		<title>My JSP 'imageUpload.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body>
		<div>
			<div style="text-align: center; font-size: small;">
				<form action="imageUpload.jsp?oper=upload" method="post"
					enctype="multipart/form-data" name="form2">
					<br>
					<input type="file" name="pic">

					<input type="submit" value="开始上传">

				</form>
			</div>
		</div>
		<%
			String oper = request.getParameter("oper");
			if ("upload".equals(oper)) {

				SmartUpload mySmartUpload = new SmartUpload();
				mySmartUpload.initialize(pageContext);
				try {
					mySmartUpload.upload();
				} catch (Exception e) {
		%>
		<SCRIPT language=javascript>  
          alert("只允许上传.jpg和.jpeg类型图片文件");  
          window.location='uploadpic.jsp';  
          </script>
		<%
			}
				File myFile = mySmartUpload.getFiles().getFile(0);
				String serverPath = request.getContextPath() + "/";
				if (myFile.isMissing()) {
		%>
		<SCRIPT language=javascript>  
               alert("请先选择要上传的文件");  
               </script>
		<%
			} else {
					boolean flagOne = true;
					if (myFile.getFileExt().equals("jpg")
							|| myFile.getFileExt().equals("JPG")
							|| myFile.getFileExt().equals("jpeg")) {
					} else {
						flagOne = false;
		%>
		<script language=javascript>  
                      alert("只允许上传.jpg类型图片文件");  
                      </script>
		<%
			}
					if (myFile.getSize() > 500000) {
						flagOne = false;
		%>
		<script language=javascript>  
                      alert("只允许文件大小500KB内的图片文件");  
                      </script>
		<%
			}
					if (flagOne) {
						String myFileName = String
								.valueOf(new Date().getTime())
								+ ".jpg";
						myFile.saveAs("/upload/" + myFileName,
								myFile.SAVEAS_VIRTUAL);
						java.io.File file = new java.io.File(request
								.getRealPath("/")
								+ "upload/" + myFileName);
						if (file.exists()) {
							Image src = javax.imageio.ImageIO.read(file);
							
		%>
		<SCRIPT language=javascript>  
		                alert("上传成功!");
		                if(window.opener!=undefined)  
						 {  
						    window.opener.returnValue = "upload/<%=myFileName%>";    
						  }else{  
						    window.returnValue = "upload/<%=myFileName%>";   
						  }  
                        
                        window.close();
                        </script>
		<%
			
						}
					}

				}
			}
		%>
	</body>
</html>
