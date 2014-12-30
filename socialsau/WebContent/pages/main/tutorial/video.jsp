<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/pages/main/common/header-main.jsp" %>
<style type="text/css">
	.ui-state-default, .ui-widget-content .ui-state-default, .ui-widget-header .ui-state-default {
		background: white;
	}
	.form-group {
		margin-bottom: 0 !important;
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
				<div class="content">
					<p style="font-size: 28px; font-weight: bold; text-align: center;" class="text-muted"><strong>Video</strong> <span class="glyphicon glyphicon-play-circle"></span></p>
					<div class="form-horizontal">
						<c:forEach var="video" items="${videos}">
							<div class="form-group accordion">
								<h3 class="text-muted accordion-header">Video ${video.videoName}</h3>
								<div style="text-align: center;">
									<iframe src="${video.videoUrl}" width="100%" height="400"></iframe>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
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
	</div>
</body>
</html>