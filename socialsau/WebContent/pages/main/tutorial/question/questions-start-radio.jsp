<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/pages/main/common/header-main.jsp" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/jquery.countdown/jquery.countdown.css"> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery.countdown/jquery.plugin.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery.countdown/jquery.countdown.js"></script>
</head>
<body>
	<s:url action="main" namespace="/" />
	<%@ include file="/pages/main/common/navbar.jsp" %>
	<div class="container">
		<div class="row section-main">
			<div class="nav sidebar-section">
				<%@ include file="/pages/main/common/tutorial-sidebar.jsp" %>
			</div>
			<div class="content-layout">
				<c:if test="${empty questions}">
					<p class="text-danger" style="font-size: 24px; font-weight: bold; text-align: center; margin: 50px;">ไม่พบคำถาม &nbsp;<span class="glyphicon glyphicon-ban-circle"></span></p>
				</c:if>
			
				<c:if test="${not empty questions}">
					<div class="content">
						<%-- <p class="text-muted" style="font-size: 24px; font-weight: bold; text-align: center;">
							<strong>${tutorial.tutorialName}</strong> Questions &nbsp; <span class="glyphicon glyphicon-question-sign"></span>
						</p> --%>
						<div class="form-horizontal">
						
							<div class="form-group">
								<span id="countdown" class="col-md-12"></span>
								<script type="text/javascript">
									// เวลา เช่น +1h +1m +15s  description: 'ช้อความ'
									$('#countdown').countdown({until: '+1h +30m', description: 'You have 1 hours 30 minutes to go.'});
								</script>
							</div>
							
							<c:forEach var="question" items="${questions}" varStatus="myStatus">
								<div class="form-group">
									<span class="col-md-1 text-muted" style="padding-right: 0px !important; width: 30px;"><strong>${myStatus.index + 1})</strong></span>
									<span class="col-md-11">${question.questionName}</span>
									<%-- <div class="radio">
										<span class="col-md-12"><strong class="text-success"><input type="radio" class="questions" name="${question.questionId}" data-id="${question.questionId}" value="D"> D. &nbsp;&nbsp;&nbsp; </strong>${question.questionD}</span>
									</div> --%>
									<div class="radio">
										<span class="col-md-12"><strong class="text-success"><input type="radio" name="${question.questionId}" class="rdoAnswer" value="A" data-id="${question.questionId}" checked> A. &nbsp;&nbsp;&nbsp; </strong>${question.questionA}</span>
									</div>
									<div class="radio">
										<span class="col-md-12"><strong class="text-success"><input type="radio" name="${question.questionId}" class="rdoAnswer" value="B" data-id="${question.questionId}" > B. &nbsp;&nbsp;&nbsp; </strong>${question.questionB}</span>
									</div>
									<div class="radio">
										<span class="col-md-12"><strong class="text-success"><input type="radio" name="${question.questionId}" class="rdoAnswer" value="C" data-id="${question.questionId}" > C. &nbsp;&nbsp;&nbsp; </strong>${question.questionC}</span>
									</div>
									<div class="radio">
										<span class="col-md-12"><strong class="text-success"><input type="radio" name="${question.questionId}" class="rdoAnswer" value="D" data-id="${question.questionId}" > D. &nbsp;&nbsp;&nbsp; </strong>${question.questionD}</span>
									</div>
								</div>
								<hr>
							</c:forEach>
								
							<div class="form-group">
								<div class="col-md-2">
									<input type="button" value="Submit" class="btn btn-success btn-block btn-questionSubmit">
								</div>
							</div>
							
							<script type="text/javascript">
								$(function() {
									$(document).on('click', '.btn-questionSubmit', function() {
										var hour = 0, minutes = 0, seconds = 0;
										var timer = "";
										// firebug --> เอาค่ามา
										$('.countdown-amount').each(function(idx) {
											if (idx == 0) {
												hour = $(this).text();
											}else if (idx == 1) {
												minutes = $(this).text();
											}else {
												seconds = $(this).text();
											}
											timer = hour + ":" + minutes + ":" + seconds;
										});
										
										var questionsIDAnswer = "";
										
										$('.rdoAnswer:checked').each(function() {
											var questionId = $(this).data('id');
											var answer = $(this).val();
											questionsIDAnswer += questionId + ":" + answer + ", ";
										});
										
										//8:A, 13:A, 6:A, 9:A, 11:A, 14:A, 3:A, 5:A, 12:A, 4:A, 
										console.log(questionsIDAnswer);
										
										var tutorialId = "${tutorial.tutorialId}";
										var lastTest= "${lastTest + 1}";
										
										$.ajax({
											url: '<s:url action="saveAnswerAndScore" namespace="/main" />', 
											data: {tutorialId: tutorialId, lastTest: lastTest, questionsIDAnswer: questionsIDAnswer, timer: timer},
											type: 'post',
											success: function(data) {
												alert(data);
												window.location = "questions?tutorialId=${tutorial.tutorialId}";
											}
										});
										
									});
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