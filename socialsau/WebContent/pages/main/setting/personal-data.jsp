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
<script type="text/javascript">
	$(function() {
		datePicker();
	});
	
	function datePicker() {
		$('#birthday').datepicker({
				changeMonth : true,
				changeYear : true,
				dateFormat : 'yy-MM-dd',
				yearRange : '1900:2000',
				defaultDate : new Date(1992, 00, 01)
				/* monthNamesShort : [ "January", "February", "March",
						"April", "May", "June", "July", "August",
						"September", "October", "November", "December" ], */
				// dayNamesMin : [ 'Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday' ]
			});
	}
</script>
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
						<p style="font-size: 24px; font-weight: bold; margin-bottom: 20px;" class="text-muted">Personal Data</p>
						<div class="form-group">
							<span class="col-md-2">User Id</span>
							<div class="col-md-2">
								<span class="label label-default" style="font-size: 18px;">${user.userId}</span>
							</div>
						</div>
						<div class="form-group">
							<span class="col-md-2 control-label">First Name</span>
							<div class="col-md-4">
								<input id="firstname" name="firstname" type="text" class="form-control" value="${user.firstname}" disabled>
							</div>
							<span id="msg-firstname" class="col-md-5"></span>
						</div>
						<div class="form-group">
							<span class="col-md-2 control-label">Last Name</span>
							<div class="col-md-4">
								<input id="lastname" name="lastname" type="text" class="form-control" value="${user.lastname}" disabled>
							</div>
							<span id="msg-lastname" class="col-md-5"></span>
						</div>
						<div class="form-group">
							<span class="col-md-2">Email</span>
							<span class="col-md-4 text-muted">${user.email}</span>
						</div>
						<div class="form-group">
							<span class="col-md-2 control-label">Birthday</span>
							<div class="col-md-4">
								<fmt:formatDate var="birthdayUser" value="${user.birthday}" pattern="dd-MMMM-yyyy" scope="page"/>
								<input id="birthday" name="birthday" type="text" class="form-control" value="${birthdayUser}" readonly style="cursor: pointer;">
							</div>
							<span id="msg-birthday" class="col-md-5"></span>
						</div>
						<div class="form-group">
							<span class="col-md-2">User Profile</span>
							<span class="col-md-4 text-muted">${user.urlProfile}</span>
						</div>
						<div class="form-group div-btn">
							<div class="col-md-offset-2 col-md-2">
								<input type="button" value="Change" class="btn btn-block btn-success btn-change">
							</div>
						</div>
					</div>
					<script type="text/javascript">
						$(document).on('click', '.btn-change', function() {
							$('#firstname').removeAttr('disabled');
							$('#lastname').removeAttr('disabled');
							$('.div-btn').html(
								'<div class="col-md-offset-2 col-md-2">' + 
									'<input type="button" value="Save" class="btn btn-block btn-info btn-save">' +
								'</div>' +
								'<div class="col-md-2">' +
									'<input type="reset" value="Reset" class="btn btn-block btn-danger btn-reset">' +
								'</div>'
							);
						});
						
						$(document).on('click', '.btn-reset', function() {
							window.location = '${pageContext.request.contextPath}/setting/personalData';
						});
						
						$(document).on('click', '.btn-save', function() {
							var firstname = $('#firstname').val();
							var lastname = $('#lastname').val();
							var birthday = $('#birthday').val();
							if (chkFirstname(firstname) & chkLastname(lastname) & chkBirthday(birthday)) {
								$.ajax({
									url: '<s:url action="personalDataUpdateAjax" namespace="/setting" />', 
									data: {firstname: firstname, lastname: lastname, birthday: birthday}, 
									type: 'post',
									success: function() {
										alert("ระบบทำการบันทึกข้อมูลเรียบร้อยแล้ว");
										window.location = '<s:url action="personalData" namespace="/setting" />';
									}
								});
							}
						});
						
						function chkFirstname(firstname) {
							if (firstname == '') {
								document.getElementById('msg-firstname').innerHTML = "<label class='control-label msg-error'>กรุณากรอก ชื่อ</label>";
								return false;
							}
							return true;
						}
						function chkLastname(lastname) {
							if (lastname == '') {
								document.getElementById('msg-lastname').innerHTML = "<label class='control-label msg-error'>กรุณากรอก นามสกุล</label>";
								return false;
							}
							return true;
						}
						function chkBirthday(birthday) {
							if (birthday == '') {
								document.getElementById('msg-birthday').innerHTML = "<label class='control-label msg-error'>กรุณาเลือก วันเกิด</label>";
								return false;
							}
							return true;
						}
					</script>
					
				</div>
			</div>
		</div>
	</div>
</body>
</html>