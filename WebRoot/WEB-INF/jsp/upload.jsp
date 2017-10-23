<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
  <head>
    <title>上传文件</title>
    <%String path = request.getContextPath(); %>
    
    <style type="text/css">
    	#font{
    		color:#FFFFFF;
    	}
    	#title{
    		color:#FFFFFF;
    		font-size:25px;
    	}
    	a:link{
    		color:6495ED;
    		text-decoration:none;
    	}
    	a:hover{
    		color:#EE0000;
    		text-decoration:none;
    	}
    	a:visited{
    		color:#6495ED;
    		text-decoration:none;
    	}
    	a:active{
    		color:#6495ED;
    		text-decoration:none;
    	}
    	}
    </style>
    
    <script type="text/javascript">
    	function n(){
    		document.getElementById("upload").disabled = true;
    		document.getElementById("msg").innerHTML = "正在上传中，请耐心等待...";
    	}
    </script>

  </head>
  
  <body style="text-align:center; background-image:url(../background2.jpg); background-size:cover;">
  
  <form action="${pageContext.request.contextPath }/servlet/UploadServlet" method="post" enctype="multipart/form-data">
  	
  	<font id="title">请选择文件</font><br/><br/>
  	<font id="font">文件路径：</font>
  	<input name="file" type="file"/><br/><br/>
  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	<font id="font">文件描述：&nbsp;&nbsp;</font><input name="description" type="text"/>
  	<input type="submit" id="upload" onclick="n()" value="上传"/>
    <a href="${pageContext.request.contextPath }/index.jsp">返回</a><br/><br/>
    <font color="red" id="msg"></font>
    </form>
  </body>
</html>
