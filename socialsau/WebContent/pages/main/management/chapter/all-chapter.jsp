<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/pages/main/management/common/header-tutorials.jsp"%>
<script src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js" type="text/javascript"></script>
</head>
<body>
	<%@ include file="/pages/main/common/navbar.jsp" %>
	<div class="container">
		<div class="row section-main">
			<div class="nav sidebar-section">
				<%@ include file="/pages/main/management/chapter/common/chapter-sidebar.jsp"%>
			</div>
			
			<div class="content-layout">
			
				<c:if test="${empty chapters}">
					<div class="content">
						<p class="text-muted" style="font-size: 24px; font-weight: bold;">Chapter Library. &nbsp;&nbsp; <span class=" glyphicon glyphicon-paperclip"></span></p>
						<table class="table table-hover">
							<thead style="background-color: #ddd;">
								<tr>
									<th style="text-align: center;">No.</th>
									<th></th>
									<th style="text-align: center;">Description</th>
									<th style="text-align: center;">Edit</th>
									<th style="text-align: center;"></th>
									<th style="text-align: center;"></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="6" style="text-align: center;">No data</td>
								</tr>
							</tbody>
						</table>
				</c:if>
				
				<c:if test="${not empty chapters}">
					<div class="content">
						<p class="text-muted" style="font-size: 24px; font-weight: bold;">Chapter Library. &nbsp;&nbsp; <span class=" glyphicon glyphicon-paperclip"></span></p>
						<table class="table table-hover">
							<thead style="background-color: #ddd;">
								<tr>
									<th style="text-align: center;">No.</th>
									<th></th>
									<th style="text-align: center;">Description</th>
									<th style="text-align: center;">Edit</th>
									<th style="text-align: center;"></th>
									<th style="text-align: center;"></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="chapter" items="${chapters}">
									<tr>
										<c:if test="${empty chapter.chapterNo}">
											<td width="15%" style="text-align: center;"><label class="label label-default" style="font-size: 14px;">ยังไม่ระบุ</label></td>
										</c:if>
										<c:if test="${not empty chapter.chapterNo}">
											<td width="15%" style="text-align: center;">${chapter.chapterNo}</td>
										</c:if>
										<td width="55%">${chapter.chapterName}</td>
										<td width="10%" style="text-align: center;"><a href="descriptionChapter?chapterId=${chapter.chapterId}"><span class="glyphicon glyphicon-eye-open"></span></a></td>
										<td width="10%" style="text-align: center;"><a href="editChapter?tutorialId=${tutorial.tutorialId}&chapterId=${chapter.chapterId}"><span class="glyphicon glyphicon-pencil"></span></a></td>
										<td width="10%" style="text-align: center;"><a href="javascript:void(0);" onclick="return deleteChapter('${tutorial.tutorialId}', '${chapter.chapterId}')"><span class="glyphicon glyphicon-trash"></span></a></td>
										<td>
											<select class="status" data-id="${chapter.chapterId}">
												<c:if test="${chapter.status == 'A'}">
													<option value="A" selected>Active</option>
													<option value="I">Inactive</option>
												</c:if>
												<c:if test="${chapter.status == 'I'}">
													<option value="A">Active</option>
													<option value="I" selected>Inactive</option>
												</c:if>
											</select>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<script type="text/javascript">
							$(document).on('change', '.status', function() {
								var chapterId = $(this).data('id');
								var status = $(this).val();
								$.ajax({
									url: '<s:url action="updateChapterStatusAjax" namespace="/chapter" />',
									data: {chapterId: chapterId, status: status},
									type: 'post',
									success: function() {
										if (status == 'A') {
											alert("ระบบทำการ \'แสดง\' บทเรียน เรียบร้อย");
										}else{
											alert("ระบบทำการ \'ซ่อน\' บทเรียน เรียบร้อย");
										}
									}
								});
							});
						
							function deleteChapter(tutorialId, chapterId) {
								// chapterId ใช้สำหรับ ลบข้อมูล
								// tutoiralId ใช้สำหรับ หาข้อมูลของ chapter ทั้งหมดได้เป็น List ASC แล้วเอามา update บทที่ ใหม่
								if (confirm("ยืนยันการลบ Chapter \n\n\'หมายเหตุ\' เมื่อยืนยันการลบข้อมูลของ Chapter \n" +  " ข้อมูลจะถูกลบอย่างถาวร")) {
									$.ajax({
										url: '<s:url action="deleteChapterAJAX" namespace="/chapter" />',
										data: {tutorialId: tutorialId, chapterId: chapterId},
										type: 'POST',
										success: function() {
											//alert("ลบข้อมูลเสร็จสิ้น");
											location.reload();
										}
									});
								}
							}
						</script>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>