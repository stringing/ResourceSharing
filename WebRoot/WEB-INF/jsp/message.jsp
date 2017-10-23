<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>消息</title>
    <style type="text/css">
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
    	div{
    		width: 725px;
			height: 50px;
    		position:fixed;
    		top:235px;
    		left:50%;
    		margin-left: -385px;
    	}
    </style>

  </head>
  
  <body style="text-align:center; background-image:url(../background3.jpg); background-size:cover;">
  <div>
    <h1>${message }</h1><br/>
    <a href="${page }">返回</a>
    </div>
  </body>
</html>
