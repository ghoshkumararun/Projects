<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/pages/main/management/common/header-tutorials.jsp"%>
<script src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js"type="text/javascript"></script>
<style type="text/css">
	table, th, td {
		border-top: 0 !important;
	}
</style>
</head>
<body>
	<%@ include file="/pages/main/common/navbar.jsp"%>
	<div class="container">
		<div class="row section-main">
			<div class="nav sidebar-section">
				<%@ include file="/pages/main/management/common/tutorial-sidebar.jsp"%>
			</div>
			<div class="content-layout">
				<div class="content">
					<div class="form-horizontal">
						<table style="width: 100%" class="table">
							<tr>
								<td width="20%" class="text-muted" style="padding-left: 10px">Tutorial Name</td>
								<td width="80%"><strong>${tutorial.tutorialName}</strong></td>
								<%-- <c:if test="${not empty tutorial.tutorialImage}">
									<td rowspan="4" style="text-align: center;">
										<img id="image-profile" src="${pageContext.request.contextPath}/resources/photos-tutorial/${tutorial.tutorialId}/${tutorial.tutorialImage}" width="150" height="150" class="img-rounded">
									</td>
								</c:if>
								<c:if test="${empty tutorial.tutorialImage}">
									<td rowspan="4" style="text-align: center;">
										<img src="${pageContext.request.contextPath}/resources/images/no-image-1.png" width="150" height="150" class="img-thumbnail">
									</td>
								</c:if> --%>
							</tr>
							<%-- <tr>
								<td width="20%" class="text-muted" style="padding-left: 10px;">Tutorial Group</td>
								<td width="80%">${tutorial.groupName}</td>
							</tr> --%>
							<tr>
								<td width="20%" class="text-muted" style="padding-left: 10px;">User Update</td>
								<td width="80%">${tutorial.userUpdate}</td>
							</tr>
							<tr>
								<td width="20%" class="text-muted" style="padding-left: 10px;">Update</td>
								<td width="80%"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${tutorial.tutorialUpdate}" /></td>
							</tr>
							<tr>
								<td width="20%" class="text-muted" style="padding-left: 10px;" valign="top">Introduction</td>
								<td width="80%" style="word-break: break-word;" colspan="2">${tutorial.tutorialDetail}</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>