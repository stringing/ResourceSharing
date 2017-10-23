<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <style type="text/css">
    	#a:link{
    		color:#F08080;
    		text-decoration:none;
    	}
    	#a:hover{
    		color:#F08080;
    		text-decoration:none;
    	}
    	#a:visited{
    		color:#F08080;
    		text-decoration:none;
    	}
    	#a:active{
    		color:#F08080;
    		text-decoration:none;
    	}
    	#a{
    		font-size:20px;
    	}
    	#b:link{
    		color:#FFB90F;
    		text-decoration:none;
    	}
    	#b:hover{
    		color:#FFB90F;
    		text-decoration:none;
    	}
    	#b:visited{
    		color:#FFB90F;
    		text-decoration:none;
    	}
    	#b:active{
    		color:#FFB90F;
    		text-decoration:none;
    	}
    	#b{
    		font-size:20px;
    	}
    	div{
    		width: 725px;
			height: 50px;
    		position:fixed;
    		top:50px;
    		left:50%;
    		margin-left: -385px;
    	}
    	font{
    		color:7EC0E0;
    	}
    	
    </style>
  </head>
  
  <body style="text-align:center; background-image:url(background.jpg); background-size:cover;">
  <div >
    <font face="Baron Neue" size="10">525资源共享</font><br/><br/><br/>
    <a id="a" href="${pageContext.request.contextPath }/servlet/NewFindAllServlet?pc=1">下载文件</a>
    <a id="b" href="${pageContext.request.contextPath }/servlet/ControllerServlet?v=toUpload">上传文件</a>
    </div>
  </body>
</html>
