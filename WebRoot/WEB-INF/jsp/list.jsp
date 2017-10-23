<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>资源列表</title>
    <style type="text/css">
    	a:link{
    		color:#6495ED;
    		text-decoration:none;
    	}
    	a:hover{
    		color:#6495ED;
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
    	font{
    		font-family: 微软雅黑;
    		color:#CDAA7F;
    	}
    	#ds{
    		color:#FF4040;
    	}
    	#pageC{
    		color:#1874CD;
    	}
    	#ipc{
    		color:#FF0000;
    	}
    </style>
	<%String path = request.getContextPath(); %>
	<script type="text/javascript">
		function toDownload(i){
			var path = document.getElementById("path").value;
			if(i==document.getElementById(i).innerHTML){
				window.location.href='<%=path%>/servlet/DownloadServlet?id='+i+'&path=' + path;
			}
		}
	</script>
	
  </head>
  
  <body style="text-align:center; background-image:url(../background1.jpg); background-size:cover;">
    <table align="center" border="1" cellspacing="0" cellpadding="17">
    <tr>
    	<td width="150" height="30"><font>文件</font></td>
    	<td width="150" height="30"><font>文件说明</font></td>
    	<td width="150" height="30"><font>上传时间</font></td>
    	<td width="45" height="30"><font>下载</font></td>
    </tr>
    </table>
    <c:forEach items="${pageBean.beanList}" var="l" varStatus="status">
    	<table align="center" border="1" cellspacing="0" cellpadding="17">
    	<tr>
    		<td width="150" height="75"><font>文件<font id="${status.index+1+(pageBean.getPc()-1)*5 }">${status.index+1+(pageBean.getPc()-1)*5 }</font>:
    		<br/>
    		${l.getName() }</font></td>
    		<td width="150" height="75"><font>${l.getDescription() }</font></td>
    		<td width="150" height="75"><font>${l.getTime() }</font></td>
    		<td width="45" height="75">
    		<form action="${pageContext.request.contextPath }/servlet/DownloadServlet" method="post">
    		<input type="hidden" name="id" value="${status.index+1+(pageBean.getPc()-1)*5 }"/>
    		<input type="hidden" name="path" value="${path }"/>
    		<input type="hidden" name="name" value="${l.getName() }"/>
    		<input type="submit" value="下载"/></form>
    		</td>
    	</tr>
    	</table>
    </c:forEach>
    
    <br/>
    	<font id="pageC">第${pageBean.getPc() }页/共${pageBean.getTp() }页</font>&nbsp;
    	<a href="${pageContext.request.contextPath }/servlet/NewFindAllServlet?pc=1">首页</a>
    	<c:if test="${pageBean.getPc() > 1 }">
    	<a href="${pageContext.request.contextPath }/servlet/NewFindAllServlet?pc=${pageBean.getPc() - 1}">上一页</a>
    	</c:if>
    	
    	<c:choose>
    		<c:when test="${pageBean.getTp() <= 10 }">
    			<c:set var="begin" value="1"/>
    			<c:set var="end" value="${pageBean.getTp() }"/>
    		</c:when>
    		<c:otherwise>
    			<c:set var="begin" value="${pageBean.getPc() - 4 }"/>
    			<c:set var="end" value="${pageBean.getPc() + 5 }"/>
    			<c:if test="${begin < 1 }">
    				<c:set var="begin" value="1"/>
    				<c:set var="end" value="10"/>
    			</c:if>
    			<c:if test="${end > pageBean.getTp() }">
    				<c:set var="begin" value="${pageBean.getTp() - 9 }"/>
    				<c:set var="end" value="${pageBean.getTp() }"/>
    			</c:if>
    		</c:otherwise>
    	</c:choose>
    	
    	<c:forEach var="i" begin="${begin }" end="${end }">
    		<c:choose>
    			<c:when test="${i eq pageBean.getPc() }">
    				<font id="ipc">${i }</font>
    			</c:when>
    			<c:otherwise>
    				<a href="${pageContext.request.contextPath }/servlet/NewFindAllServlet?pc=${i }">${i }</a>
    			</c:otherwise>
    		</c:choose>
    		
    	</c:forEach>
    	
    	<c:if test="${pageBean.getPc() < pageBean.getTp() }">
    	<a href="${pageContext.request.contextPath }/servlet/NewFindAllServlet?pc=${pageBean.getPc() + 1}">下一页</a>
    	</c:if>
    	<a href="${pageContext.request.contextPath }/servlet/NewFindAllServlet?pc=${pageBean.getTp()}">尾页</a>
    	<br/>
    	<a href="${pageContext.request.contextPath }/servlet/NewFindAllServlet">刷新</a>
    	<a href="${pageContext.request.contextPath }/index.jsp">返回</a>
        </body>
</html>
