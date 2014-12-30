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
		
			<div class="nav sidebar-section">
				<%@ include file="/pages/main/management/chapter/common/chapter-sidebar.jsp"%>
			</div>
			
			<div class="content-layout">
			
				<c:if test="${empty chapters}">
					<p class="text-danger" style="font-size: 24px; font-weight: bold; text-align: center; margin: 50px;">ไม่พบบทเรียน &nbsp;<span class="glyphicon glyphicon-ban-circle"></span></p>
				</c:if>
			
				<c:if test="${not empty chapters}">
					<div class="content">
						<p class="text-muted" style="font-size: 24px; font-weight: bold;">Change Chapter. &nbsp;&nbsp; <span class="glyphicon glyphicon-sort"></span></p>
						<p class="text-muted">กรุณาจัดลำดับบทเรียนที่ต้องการ โดยใช้เมาส์เลือกตำแหน่งที่ต้องการลากแล้วปล่อย</p>
						<div class="form-horizontal">
							<ul id="sortable">
								<c:forEach var="chapter" items="${chapters}" varStatus="myStatus">
									<li class="item-question" data-id="${chapter.chapterId}" style="list-style: decimal; cursor: move;">
										<p>${chapter.chapterName}</p>
									</li>
								</c:forEach>
							</ul>
							<div class="form-group">
								<div class="col-md-2">
									<input type="button" value="SUBMIT" class="btn btn-block btn-changeChapter">
								</div>
							</div>
						</div>
					</div>
				</c:if>
				<script type="text/javascript">
					$(function() {
						// sortable
						$('#sortable').sortable();
						
						// click ปุ่ม
						$('.btn-changeChapter').click(function() {
							var chapterIdCollection = "";
							$('li.item-question').each(function(idx) {
								chapterIdCollection += $(this).data('id') + ", ";
							});
							console.log(chapterIdCollection);
							$.ajax({
								url: '<s:url action="changeChapterPerformAJAX" namespace="/chapter" />',
								data: {chapterIdCollection: chapterIdCollection},
								type: 'POST',
								success: function(data) {
									if (data == "success") {
										if (data == "success") {
											alert("บันทึกข้อมูลเรียบร้อยแล้ว");
										}
										window.location = "allChapter?tutorialId=${tutorial.tutorialId}";
									}
								},
								error: function() {
									alert("การทำงานผิดพลาดภายในโปรแกรม");
								}
							});
						});
					});
				</script>
			</div>
			
		</div>
	</div>
</body>
</html>