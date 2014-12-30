<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<title>Social SAU</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/favicon.ico">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/jquery-ui/jquery-ui.min.css" >
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css" >
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" >

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-ui/jquery-ui.min.js"></script>

<c:if test="${sessionScope['user'] == null}">
	<script type="text/javascript">
			alert("โปรดทำการล็อกอิน");
			window.location = '${pageContext.request.contextPath}';
	</script>
</c:if>