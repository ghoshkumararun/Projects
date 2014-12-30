<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/pages/main/management/common/header-tutorials.jsp"%>
<script src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js"type="text/javascript"></script>
</head>
<body>
	<%@ include file="/pages/main/common/navbar.jsp"%>
	<div class="container">
		<div class="row section-main">
			<div class="content">
				<div class="form-horizontal">
					<div class="form-group">
						<h3 class="col-md-12 text-muted text-center"><strong>Chapter ${chapter.chapterNo} &nbsp; ${chapter.chapterName}</strong></h3> 
					</div>
					<div class="form-group">
						<span class="col-md-2 text-muted"><strong>Chapter ID</strong> &nbsp;&nbsp; ${chapter.chapterId}</span>
						
						<span class="col-md-1 text-muted"><strong>User</strong></span>
						<span class="col-md-2 text-muted">${chapter.userUpdate}</span>
						
						<span class="col-md-1 text-muted"><strong>Update</strong></span> 
						<span class="col-md-3 text-muted"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${chapter.chapterUpdate}" /></span>
						
						<span class="col-md-2 text-right"><strong><a href="javascript:void(0);" onclick="history.back();">Back ...</a></strong></span> 
					</div>
					<br/>
					<div class="form-group">
						<span class="col-md-12" style="word-break: break-word;">${chapter.chapterDetail}</span> 
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>