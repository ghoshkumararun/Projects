<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/pages/main/common/header-main.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/colpick/js/colpick.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/colpick/css/colpick.css" >
</head>
<body>
	<%@ include file="/pages/main/common/navbar.jsp" %>
	
	<div class="container">
		<div class="row section-main">
			<div class="nav sidebar-section">
				<%@ include file="/pages/main/setting/common/sidebar.jsp"%>
			</div>
			<div class="content-layout">
				<div class="content">
					<div class="form-horizontal">
						<p style="font-size: 24px; font-weight: bold; margin-bottom: 20px;" class="text-muted">Profile Template</p>
						<div class="form-group">
							<div class="col-md-6">
								<div id="navbar-color"></div>
							</div>
						</div>
						<div class="form-group">
							<div id="show-background" class="col-md-12" style="height: 200px; background-color: #f7f7f7;">
								<c:if test="${not empty user.imageProfile}">
									<img id="image-profile" src="${pageContext.request.contextPath}/resources/photos-profile/${user.imageProfile}" width="150" height="150" class="img-rounded" style="position: absolute; margin-top: 80px;">
								</c:if>
								<c:if test="${empty user.imageProfile}">
									<img src="${pageContext.request.contextPath}/resources/images/no-image-1.png" width="150" height="150" style="position: absolute; margin-top: 80px;">
								</c:if>
							</div>
						</div>
						<div class="form-group" style="margin-top: 50px;">
							<div class="col-md-offset-10 col-md-2">
								<input type="hidden" id="color-profile" name="color-profile">
								<input type="button" value="Save" class="btn btn-block btn-save" style="background-color: #f7f7f7;">
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<script type="text/javascript">
				$(function() {
					$('#navbar-color').colpick({
						flat: true,
						layout: 'rgbhex',
						submit:0,
						// colorScheme:'dark',
						onChange: function(hsb,hex,rgb) {
							$('#color-profile').val('#' + hex);
							$('#show-background').attr('style', 'background-color: #' + hex + '; height: 150px;');
							$('.btn-save').attr('style', 'background-color: #' + hex + '; color: #FFFFFF;');
						}
					});
					$(document).on('click', '.btn-save', function() {
						var colorProfile = $('#color-profile').val();
						$.ajax({
							url: '<s:url action="templateUpdateAjaxColor" namespace="/setting" />',
							data: {colorProfile: colorProfile},
							type: 'post',
							success: function() {
								alert("ระบบทำการบันทึกข้อมูลเรียบร้อยแล้ว");
								window.location = '<s:url action="%{#session.user.urlProfile}" namespace="/profile" />';
							}
						});
					});
				});
			</script>
		</div>
	</div>
</body>
</html>