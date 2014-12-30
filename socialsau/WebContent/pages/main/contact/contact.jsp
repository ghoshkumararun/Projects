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
</head>
<body>
	<%@ include file="/pages/main/common/navbar.jsp" %>
	
	<div class="container">
		<div class="row section-main">
			<div class="nav sidebar-section">
				<%@ include file="/pages/main/contact/common/contact-sidebar.jsp"%>
			</div>
			
			<form class="form-horizontal">
				<div class="form-group">
					<div class="col-md-8">
						<p class="text-muted" style="font-size: 24px; font-weight: bold; margin-bottom: 20px;">Contact <i class="glyphicon glyphicon-send text-muted"></i></p>
						<div class="form-group">
							<label for="firstname" class="col-md-2 control-label">First name</label>
							<div class="col-md-6">
								<input type="text" id="firstname" name="firstname" class="form-control" value="${sessionScope['user'].firstname}">
							</div>
							<span id="msg-firstname" class="col-md-4"></span>
						</div>
						<div class="form-group">
							<label for="lastname" class="col-md-2 control-label">Last name</label>
							<div class="col-md-6">
								<input type="text" id="lastname" name="lastname" class="form-control" value="${sessionScope['user'].lastname}">
							</div>
							<span id="msg-lastname" class="col-md-4"></span>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">Email </label>
							<span class="col-md-6 control-label">${sessionScope['user'].email}</span>
						</div>
						<div class="form-group">
							<label for="problemDetail" class="col-md-2 control-label">Detail </label>
							<div class="col-md-6">
								<textarea id="problemDetail" name="problemDetail" rows="6" style="width: 100%; resize: none;" class="form-control"></textarea>
							</div>
							<span id="msg-problemDetail" class="col-md-4"></span>
						</div>
						<div class="form-group">
							<div class="col-md-offset-5 col-md-3">
								<input type="hidden" id="email" value="${sessionScope['user'].email}">
								<input type="hidden" id="userId" value="${sessionScope['user'].userId}">
								<input type="button" value="Send" class="btn btn-block btn-success btn-send">
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	
	<script type="text/javascript">
		$(document).on('click', '.btn-send', function() {
			var userId = $('#userId').val();
			var firstname = $('#firstname').val();
			var lastname = $('#lastname').val();
			var email = $('#email').val();
			var problemDetail = $('#problemDetail').val();
			
			if (chkFirstname(firstname) & chkLastname(lastname) & chkProblemDetail(problemDetail)) {
				// console.log('success');
				$.ajax({
					url: '<s:url action="sendPerformAjax" namespace="/contact" />',
					data: {userId: userId, firstname: firstname, lastname: lastname, email: email, problemDetail: problemDetail},
					type: 'post',
					success: function() {
						alert("ระบบทำการบันทึกข้อมูลเรียบร้อยแล้ว");
						window.location = '<s:url action="send" namespace="/contact" />';
					}
				});
				return true;
			}
			// console.log('fail');
			return false;
		});
		
		// check input
		function chkFirstname(firstname) {
			if (firstname == '') {
				document.getElementById('msg-firstname').innerHTML = "<label class='msg-error'>กรุณากรอก 'ชื่อ'</label>";
				return false;
			}
			return true;
		}
		function chkLastname(lastname) {
			if (lastname == '') {
				document.getElementById('msg-lastname').innerHTML = "<label class='msg-error'>กรุณากรอก 'นามสกุล'</label>";
				return false;
			}
			return true;
		}
		function chkProblemDetail(problemDetail) {
			if (problemDetail == '') {
				document.getElementById('msg-problemDetail').innerHTML = "<label class='msg-error'>กรุณากรอก 'รายละเอียด'</label>";
				return false;
			}
			return true;
		}
	</script>
</body>
</html>