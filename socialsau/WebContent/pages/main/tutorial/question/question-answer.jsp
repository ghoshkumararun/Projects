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
	.ui-state-default, .ui-widget-content .ui-state-default, .ui-widget-header .ui-state-default {
		background: white;
	}
</style>
</head>
<body>
	<%@ include file="/pages/main/common/navbar.jsp" %>
	<div class="container">
		<div class="row section-main">
			<div class="nav sidebar-section">
				<%@ include file="/pages/main/common/tutorial-sidebar.jsp" %>
			</div>
			
			<div class="content-layout">
				<c:if test="${empty questions}">
					<div class="content">
						<p class="text-muted" style="font-size: 24px; font-weight: bold;">Question Library.</p>
						<div class="form-horizontal">
							<div class="form-group">
								<div class="col-md-12" style="text-align: center;">
									<label>No data</label>
								</div>
							</div>
						</div>
					</div>
				</c:if>
			
				<c:if test="${not empty questions}">
					<div class="content">
						<p class="text-muted" style="font-size: 24px; font-weight: bold;">Question Answer.</p>
							<div class="form-horizontal">
								<c:forEach var="question" items="${questions}" varStatus="myStatus">
									<div class="form-group" style="margin-bottom: 10px;">
										<span class="col-md-1">${myStatus.index + 1}</span>
										<span class="col-md-11" style="word-wrap: break-word;">${question.questionName}</span>
									</div>
									<div class="form-group">
										<div class="col-md-offset-1 col-md-11 accordion">
											<h3 class="text-muted accordion-header">View Answer</h3>
											<div>
												<p><span class="text-success"><strong>Answer:</strong></span> Option ${question.questionAnswer}</p>
												<p>${question.questionExplanation}</p>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
						<script type="text/javascript">
							$(function() {
								$('.accordion').accordion({
								      collapsible: true,
								      header: ".accordion-header",
								      active: false, // ปกติจะเป็น true คือแสดงออกมา
								      heightStyle: 'content' // ไม่ให้มี scroll bar
							    });
							});
						</script>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>