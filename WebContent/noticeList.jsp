<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:url value='/' var="root"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
  <head>
    <base href="${root}">
    
    <title>My JSP 'noticeList.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="${root}resource/css/styles.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${root}resource/js/jquery-1.4.2.min.js"></script>
  </head>
  
  <body>
  
	<ul class="noticeList">
		 <c:forEach items="${datas}" var="notice">
		 <li id="li${notice.noticeId }">[<fmt:formatDate value="${notice.addTime }"  pattern= "yyyy-MM-dd HH:mm:ss" />]
		 <a href="notice!updateA.action?nid=${notice.noticeId }">修改</a>
		 <a href="javascript:del(${notice.noticeId})">删除</a>
		 <Br/><a href="notice!detail.action?nid=${notice.noticeId }">${notice.title }</a></li>
		 </c:forEach>
	</ul>
    
    <br />
    <a href="${root}b.jsp">b.jsp 无权限访问 : 自己实现的权限控制</a>
    <br />
    <a href="${root}c.jsp">c.jsp 有权限访问 : 自己实现的权限控制</a>
    <br />
    <a href="${root}d.jsp">d.jsp 有权限访问 : SPRING内置的权限控制</a>
    <br />
    <a href="${root}e.jsp">e.jsp 无权限访问 : SPRING内置的权限控制</a>
    <br />
	<c:url value="/j_spring_security_logout" var="logoutUrl"/>
	<a href="${logoutUrl}">Log Out</a><br />
	

-------------------------------------------------------------------<br />

<%--有问题！！！
	<sec:authorize url="/b.jsp" method="GET">
		<a href="${root}b.jsp">b.jsp</a><br />
	</sec:authorize>
	<sec:authorize url="/c.jsp" method="GET">
		<a href="${root}c.jsp">c.jsp</a><br />
	</sec:authorize>
	<sec:authorize url="/d.jsp" method="GET">
		<a href="${root}d.jsp">d.jsp</a><br />
	</sec:authorize>
	<sec:authorize url="/e.jsp" method="GET">
		<a href="${root}e.jsp">e.jsp</a><br />
	</sec:authorize>
--%>

-------------------------------------------------------------------<br />
<sec:authorize access="isAuthenticated()">
   YES, you are logged in! <br />
</sec:authorize>

<sec:authorize access="hasRole('ROLE_AAA') and fullyAuthenticated">
ROLE_AAA<br />
</sec:authorize>
<sec:authorize access="hasRole('ROLE_BBB') and fullyAuthenticated">
ROLE_BBB<br />
</sec:authorize>
<sec:authorize access="hasRole('ROLE_CCC') and fullyAuthenticated">
ROLE_CCC<br />
</sec:authorize>

-------------------------------------------------------------------<br />

<sec:authorize ifAnyGranted="ROLE_AAA">
	ROLE_AAA<br />
</sec:authorize>
<sec:authorize ifNotGranted="ROLE_AAA">
	NO ROLE_AAA<br />
</sec:authorize>
<sec:authorize ifAnyGranted="ROLE_BBB">
	ROLE_BBB<br />
</sec:authorize>
<sec:authorize ifNotGranted="ROLE_BBB">
	NO ROLE_BBB<br />
</sec:authorize>
<sec:authorize ifAnyGranted="ROLE_CCC">
	ROLE_CCC<br />
</sec:authorize>
<sec:authorize ifNotGranted="ROLE_CCC">
	NO ROLE_CCC<br />
</sec:authorize>
<sec:authorize ifAllGranted="ROLE_AAA,ROLE_BBB,ROLE_CCC">
	ALL<br />
</sec:authorize>
<sec:authorize ifAnyGranted="ROLE_AAA,ROLE_BBB,ROLE_CCC">
	ANY<br />
</sec:authorize>
  </body>
</html>
