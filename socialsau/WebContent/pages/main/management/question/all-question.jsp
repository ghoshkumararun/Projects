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
	<%@ include file="/pages/main/common/navbar.jsp"%>
	<div class="container">
		<div class="row section-main">
			<div class="nav sidebar-section">
				<%@ include file="/pages/main/management/question/common/question-sidebar.jsp"%>
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
						<p class="text-muted" style="font-size: 24px; font-weight: bold;">Question Library.</p>
							<div class="form-horizontal">
								<c:forEach var="question" items="${questions}" varStatus="myStatus">
									<div class="form-group" style="margin-bottom: 10px;">
										<span class="col-md-1">${myStatus.index + 1}</span>
										<span class="col-md-7" style="word-wrap: break-word;">${question.questionName}</span>
										<div class="col-md-1">
											<a href="editQuestion?tutorialId=${tutorial.tutorialId}&questionId=${question.questionId}"><span class="glyphicon glyphicon-pencil"></span></a>
										</div>
										<div class="col-md-1">
											<a href="javascript:void(0);" onclick="return deleteQuestion('${question.questionId}')"><span class="glyphicon glyphicon-trash"></span></a>
										</div>
										<div class="col-md-1">
											<select class="status" data-id="${question.questionId}">
												<c:if test="${question.status == 'A'}">
													<option value="A" selected>Active</option>
													<option value="I">Inactive</option>
												</c:if>
												<c:if test="${question.status == 'I'}">
													<option value="A">Active</option>
													<option value="I" selected>Inactive</option>
												</c:if>
											</select>
										</div>
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
							
							$(document).on('change', '.status', function() {
								var questionId = $(this).data('id');
								var status = $(this).val();
								//console.log(questionId);
								//console.log(status);
								$.ajax({
									url: '<s:url action="updateQuestionStatusAjax" namespace="/question" />',
									data: {questionId: questionId, status: status},
									type: 'post',
									success: function() {
										if (status == 'A') {
											alert("ระบบทำการ \'แสดง\' คำถาม เรียบร้อย");
										}else{
											alert("ระบบทำการ \'ซ่อน\' คำถาม เรียบร้อย");
										}
									}
								});
							});
							
							function deleteQuestion(questionId){
								if (confirm("ยืนยันการลบ Question \n\n\'หมายเหตุ\' เมื่อท่านยืนยันการลบข้อมูล \nข้อมูลที่เกี่ยวข้องทั้งหมดจะถูกลบอย่างถาวร")) {
									$.ajax({
										url: '<s:url action="deleteQuestionPerformAJAX" namespace="/question" />',
										data: {questionId: questionId},
										type: 'post',
										success: function() {
											// alert(data);
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