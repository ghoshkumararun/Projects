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
<style type="text/css">
	.errorMessage {
		display: block;
		padding: 15px;
		margin-bottom: 20px;
		border: 1px solid transparent;
		border-radius: 4px;
		background-color: #fcf8e3;
		color: #a94442;
	}
</style>
</head>
<body class="bgpattern">
	<div class="container">
		<div class="row">
			<div class="section-fileupload">
				<form action="fileuploadPerform" method="post" class="form-horizontal" enctype="multipart/form-data" >
					<h3 style="margin-bottom: 30px;">Upload Photos &nbsp;<span class="glyphicon glyphicon-upload"></span></h3>
					<s:actionerror cssClass="alert bg-warning text-danger" cssStyle="list-style: none"/>
					<s:actionmessage cssClass="alert bg-success text-success" cssStyle="list-style: none"/>
					
					<div class="div-upload" style="margin-bottom: 15px;">
						<div class="form-group" style="margin-bottom: 0;">
							<div class="col-md-offset-1 col-md-8">
								<input type="file" name="upload" accept="image/*" >
							</div>
							<div class="col-md-3">
								<a href="javascript:void(0)" class="delete-fileupload"><span class="glyphicon glyphicon-remove"></span></a>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-md-offset-3 col-md-9">
							<input type="submit" value="Upload" class="btn btn-success btn-upload">
							<input type="button" value="Add more..." class="btn btn-danger btn-add-more">
						</div>
					</div>
					<br>
					<div class="form-group">
						<div class="col-md-offset-9 col-md-3">
							<a href="<s:url action='%{#session.user.urlProfile}' namespace='/profile'/>" style="text-decoration: none;"> 
								<input type="button" value="Back" class="btn btn-info btn-block" />
							</a>
						</div>
					</div>
					
					<c:if test="${not empty photoUploads}">
						<h3 style="margin-bottom: 30px;">Upload Recent&nbsp;<span class="glyphicon glyphicon-refresh"></span></h3>
						<div class="form-group" style="text-align: center;">
							<c:forEach var="photoUpload" items="${photoUploads}">
								<div class="col-md-12" style="text-align: center;">
									<img src="${pageContext.request.contextPath}/resources/photos/${photoUpload.userId}/${photoUpload.photoUrl}" title="${photoUpload.photoUrl}" width="150" height="150" class="img-rounded" style="margin-bottom: 10px; margin-right: 10px;">
									<a href="javascript:void(0)" style="text-decoration: none;" data-id="${photoUpload.photoId}" class="delete-recent-file"><strong class="text-danger">Cancel &nbsp;<span class="glyphicon glyphicon-remove"></span></strong></a>
								</div>
							</c:forEach>
						</div>
					</c:if>
				</form>
				
				<script type="text/javascript">
					$(function() {
						$('.btn-add-more').click(function() {
							$('.div-upload').append(
									'<div class="form-group" style="margin-bottom: 0;">' + 
										'<div class="col-md-offset-1 col-md-8">' +
											'<input type="file" name="upload" accept="image/*" >' +
										'</div>' +
										'<div class="col-md-1">' +
											'<a href="javascript:void(0)" class="delete-fileupload"><span class="glyphicon glyphicon-remove"></span></a>' +
										'</div>' +
									'</div>'
							);
						});
					});
					
					$(document).on('click', '.delete-fileupload', function() {
						$(this).parent().parent().fadeOut('slow');
					});
					
					$(document).on('click', '.delete-recent-file', function() {
						var photoId = $(this).data('id');
						$.ajax({
							url: "<s:url action='deletePhotoUpload' namespace='/upload'/>", 
							data: {photoId: photoId}, 
							type: 'post'
						});
						$(this).parent().fadeOut('slow');
					});
					
				</script>
			</div>
		</div>
	</div>
</body>
</html>