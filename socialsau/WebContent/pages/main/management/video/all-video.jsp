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
	 	text-align: center;
	}	
</style>
</head>
<body>
	<%@ include file="/pages/main/common/navbar.jsp"%>
	<div class="container">
		<div class="row section-main">
			<div class="nav sidebar-section">
				<%@ include file="/pages/main/management/video/common/video-sidebar.jsp"%>
			</div>
			
			<div class="content-layout">
			
				<c:if test="${empty videos}">
					<div class="content">
						<p class="text-muted" style="font-size: 24px; font-weight: bold;">Video Library.</p>
						<div class="form-horizontal">
							<table class="table table-hover">
							<thead style="background-color: #ddd;">
								<tr>
									<th style="width: 5%;">No.</th>
									<th style="width: 20%;">Video name</th>
									<th style="width: 60%;">URL</th>
									<th style="width: 5%;"></th>
									<th style="width: 5%;"></th>
									<th style="width: 5%;"></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="5" style="text-align: center;">No data</td>
								</tr>
							</tbody>
						</table>
					</div>
				</c:if>
			
				<c:if test="${not empty videos}">
					<div class="content">
						<p class="text-muted" style="font-size: 24px; font-weight: bold;">Video Library.</p>
						<div class="form-horizontal">
							<table class="table table-hover">
								<thead style="background-color: #ddd;">
									<tr>
										<th style="width: 5%;">No.</th>
										<th style="width: 20%;">Video name</th>
										<th style="width: 60%;">URL</th>
										<th style="width: 5%;"></th>
										<th style="width: 5%;"></th>
										<th style="width: 5%;"></th>
									</tr>
								</thead>
								<c:forEach var="video" items="${videos}" varStatus="myStatus">
									<tr>
										<td>${myStatus.index + 1}</td>
										<td style="text-align: left;">${video.videoName}</td>
										<td style="text-align: left;">
											<iframe src="${video.videoUrl}" width="100%" height="200px"></iframe>
										</td>
										<td>
											<a href="editVideo?tutorialId=${video.tutorialId}&videoId=${video.videoId}"><span class="glyphicon glyphicon-pencil"></span></a>
										</td>
										<td>
											<a href="javascript:void(0)" data-id="${video.videoId}" class="btn-delete"><span class="glyphicon glyphicon-trash"></span></a>
										</td>
										<td>
											<select class="status" data-id="${video.videoId}">
												<c:if test="${video.status == 'A'}">
													<option value="A" selected>Active</option>
													<option value="I">Inactive</option>
												</c:if>
												<c:if test="${video.status == 'I'}">
													<option value="A">Active</option>
													<option value="I" selected>Inactive</option>
												</c:if>
											</select>
										</td>
									</tr>
								</c:forEach>
							</table>
							
							<script type="text/javascript">
								$(document).on('change', '.status' , function() {
									var videoId = $(this).data('id');
									var status = $(this).val();
									//console.log(videoId);
									//console.log(status);
									$.ajax({
										url: '<s:url action="updateVideoStatusAjax" namespace="/video" />',
										data: {videoId: videoId, status: status},
										type: 'post',
										success: function() {
											if (status == 'A') {
												alert("ระบบทำการ \'แสดง\' วิดีโอ เรียบร้อย");
											}else{
												alert("ระบบทำการ \'ซ่อน\' วิดีโอ เรียบร้อย");
											}
										}
									});
								})
							
								$(document).on('click', '.btn-delete', function() {
									if (confirm("ยืนยันการลบ Video \n\n\'หมายเหตุ\' เมื่อยืนยันการลบข้อมูลของ Video \n" +  " ข้อมูลจะถูกลบอย่างถาวร")) {
										var videoId = $(this).data('id');
										$.ajax({
											url: '<s:url action="deleteVideoPerformAJAX" namespace="/video" />',
											data: {videoId: videoId},
											type: 'post',
											success: function() {
												alert("ระบบทำการลบข้อมูลเรียบร้อยแล้ว");
												location.reload();
											},
											error: function() {
												alert("การทำงานผิดพลาดภายในโปรแกรม");
											}
										});
									}
								});
							</script>
							
						</div>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>