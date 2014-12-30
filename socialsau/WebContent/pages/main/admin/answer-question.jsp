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
<c:if test="${sessionScope['user'].role != 'admin'}">
	<script type="text/javascript">
			alert("โปรดทำการล็อกอิน");
			window.location = '${pageContext.request.contextPath}';
	</script>
</c:if>
<style type="text/css">
	table, th, td {
		text-align: center;
	}
</style>
</head>
<body>
	<%@ include file="/pages/main/common/navbar.jsp" %>
	
	<div class="container">
		<div class="row">
			<div class="section-main" style="width: 500px; margin-top: 100px;">
				<div class="form-horizontal content">
					<p class="text-muted text-center" style="font-size: 24px; font-weight: bold; margin-bottom: 20px;">Answer</p>
					<div class="form-group">
						<span class="col-md-3 text-muted">Email </span>
						<div class="col-md-9">
							<p>${contact.contactEmail}</p>
						</div>
					</div>
					<div class="form-group">
						<span class="col-md-3 text-muted">Contact Detail</span>
						<div class="col-md-9">
							<p>${contact.contactDetail}</p>
						</div>
					</div>
					<div class="form-group">
						<span class="col-md-3 text-muted">Answer</span>
						<div class="col-md-9">
							<textarea id="answer-question" rows="5" style="width: 100%; resize: none;" class="form-control">${contact.contactAnswer}</textarea>
						</div>
						<span id="msg-answer" class="col-md-offset-3 col-md-4"></span>
					</div>
					<div class="form-group">
						<div class="col-md-offset-9 col-md-3">
							<input type="hidden" id="contactId" value="${contact.contactId}">
							<input type="button" value="Send" class="btn btn-block btn-success btn-send">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	$(document).on('click', '.btn-send', function() {
		var contactId = $('#contactId').val();
		var answer = $('#answer-question').val();
		
		if (chkAnswer(answer)) {
			// console.log('success');
			$.ajax({
				url: '<s:url action="answerQuestionUpdateAjax" namespace="/admin" />',
				data: {contactId: contactId, answer: answer},
				type: 'post',
				success: function() {
					alert("ระบบทำการบันทึกข้อมูลเรียบร้อยแล้ว");
					window.location = '<s:url action="userContact" namespace="/admin" />';
				}
			});
			return true;
		}
		// console.log('fail');
		return false;
	});
	
	// check input
	function chkAnswer(answer) {
		if (answer == '') {
			document.getElementById('msg-answer').innerHTML = "<label class='msg-error'>กรุณากรอก 'คำตอบ'</label>";
			return false;
		}
		return true;
	}
	</script>
</body>
</html>