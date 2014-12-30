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
	table,th,td{
		text-align: center !important;
	}
</style>
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
						<p style="font-size: 24px; font-weight: bold; margin-bottom: 20px;" class="text-muted">Photo <span class="glyphicon glyphicon-camera"></span></p>
						<div class="form-group">
							<table class="table">
								<thead style="background-color: #ddd;">
									<tr>
										<th>Photo</th>
										<th>Size(bytes.)</th>
										<th>Upload Date</th>
										<th>Delete</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="photo" items="${photos}">
										<tr>
											<td>
												<img src="${pageContext.request.contextPath}/resources/photos/${photo.userId}/${photo.photoUrl}" width="100" height="100">
											</td>
											<td>${photo.photoSize}</td>
											<fmt:setLocale value="th_TH"/>
											<td><fmt:formatDate pattern="EEEE ที่ d MMMM พ.ศ. yyyy" value="${photo.photoCreate}" /></td>
											<td><a href="javascript:void()" class="btn-delete" data-id="${photo.photoId}"><span class="glyphicon glyphicon-remove"></span></a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						
						<script type="text/javascript">
							$(function() {
								$(document).on('click', '.btn-delete', function() {
									if (confirm("ยืนยันการลบ \n\'หมายเหตุ\' เมื่อยืนยันการลบ ภาพจะถูกลบอย่างถาวร")) {
										var photoId = $(this).data('id');
										$.ajax({
											url: "<s:url action='deletePhoto' namespace='/setting'/>", 
											data: {photoId: photoId}, 
											type: 'post',
											success: function() {
												location.reload();
											}
										});
									}
								});
							});
						</script>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>