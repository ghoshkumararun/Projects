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
					<s:form id="frmPassword" action="changePassword" namespace="/setting" method="post" cssClass="form-horizontal">
						<p style="font-size: 24px; font-weight: bold; margin-bottom: 20px;" class="text-muted">Change Password</p>
						<s:actionerror cssClass="alert bg-warning text-danger" cssStyle="list-style: none"/>
						<s:actionmessage cssClass="alert bg-success text-success" cssStyle="list-style: none"/>
						
						<div class="form-group">
							<label for="old-password" class="col-md-3 control-label">Old Password</label>
							<div class="col-md-4">
								<input id="old-password" name="old-password" type="password" class="form-control">
							</div>
							<span id="msg-old-password" class="col-md-5"></span>
						</div>
						<div class="form-group">
							<label for="new-password" class="col-md-3 control-label">New Password</label>
							<div class="col-md-4">
								<input id="new-password" name="new-password" type="password" class="form-control">
							</div>
							<span id="msg-new-password" class="col-md-5"></span>
						</div>
						<div class="form-group">
							<label for="confirm-password" class="col-md-3 control-label">Confirm Password</label>
							<div class="col-md-4">
								<input id="confirm-password" name="confirm-password" type="password" class="form-control">
							</div>
							<span id="msg-confirm-password" class="col-md-5"></span>
						</div>
						<div class="form-group">
							<span id="msg-check-confirmpassword" class="col-md-offset-3 col-md-5"></span>
						</div>
						<div class="form-group">
							<div class="col-md-offset-3 col-md-2">
								<input type="submit" value="Submit" class="btn btn-block btn-success">
							</div>
							<div class="col-md-2">
								<input type="reset" value="Reset" class="btn btn-block btn-danger btn-reset">
							</div>
						</div>
					</s:form>
					<script type="text/javascript">
						$(function() {
							$('#frmPassword').submit(function() {
								var oldPassword = $('#old-password').val();
								var newPassword = $('#new-password').val();
								var confirmPassword = $('#confirm-password').val();
								if (chkOldPassword(oldPassword) & chkNewPassword(newPassword) & chkConfirmPassword(confirmPassword)) {
									if (checkConfirmPassword(newPassword, confirmPassword)) {
										return true;
									}
								}
								return false;
							});
							
							// ตรวจสอบ รหัสผ่านต้องตรงกัน
							function checkConfirmPassword(newPassword, confirmPassword) {
								if (newPassword != confirmPassword) {
									document.getElementById('msg-check-confirmpassword').innerHTML = "<label class='msg-error'>กรุณากรอก 'รหัสผ่านทั้ง 2 ช่องให้ตรงกัน'</label>";
									return false;
								}
								return true;
							}
							
							// chk ตรวจสอบค่าว่าง
							function chkOldPassword(oldPassword) {
								if (oldPassword == '') {
									document.getElementById('msg-old-password').innerHTML = "<label class='msg-error'>กรุณากรอก 'รหัสผ่านปัจจุบัน'</label>";
									return false;
								}
								return true;
							}
							function chkNewPassword(newPassword) {
								if (newPassword == '') {
									document.getElementById('msg-new-password').innerHTML = "<label class='msg-error'>กรุณากรอก 'รหัสผ่านใหม่'</label>";
									return false;
								}
								return true;
							}
							function chkConfirmPassword(confirmPassword) {
								if (confirmPassword == '') {
									document.getElementById('msg-confirm-password').innerHTML = "<label class='msg-error'>กรุณากรอก 'ยืนยันรหัสผ่าน'</label>";
									return false;
								}
								return true;
							}
							
							// focus
							$(document).on('focus', '#old-password', function() {
								document.getElementById('msg-old-password').innerHTML = '';
							});
							$(document).on('focus', '#new-password', function() {
								document.getElementById('msg-new-password').innerHTML = '';
							});
							$(document).on('focus', '#confirm-password', function() {
								document.getElementById('msg-confirm-password').innerHTML = '';
							});
							// ปุ่ม reset
							$(document).on('click', '.btn-reset', function() {
								document.getElementById('msg-old-password').innerHTML = '';
								document.getElementById('msg-new-password').innerHTML = '';
								document.getElementById('msg-confirm-password').innerHTML = '';
								document.getElementById('msg-check-confirmpassword').innerHTML = '';
							});
						});
					</script>					
				</div>
			</div>
		</div>
	</div>
</body>
</html>