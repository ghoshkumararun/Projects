<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="net.tanesha.recaptcha.ReCaptcha"%>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/pages/common/header-login.jsp"%>
<script src="${pageContext.request.contextPath}/resources/js/chkDuplicate.js" type="text/javascript"></script>
</head>
<body class="bgpattern">
	<div class="container-fluid shapes">
		<s:form id="frmAddUser" action="addUser" namespace="/user" cssClass="form-horizontal" method="post">
			
			<div class="form-group">
				<div class="col-md-5"><h2 style="margin-bottom: 30px;">Register Form</h2></div>
				<div class="col-md-2">
					<img id="indicator" src="${pageContext.request.contextPath}/resources/images/indicator.gif" style="margin-top: 20px; display: none;">
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-7">
					<s:actionerror cssClass="alert bg-warning text-danger" cssStyle="list-style: none"/>
					<s:actionmessage cssClass="alert bg-success text-success" cssStyle="list-style: none"/>
					<input id="firstname" name="firstname" type="text" class="form-control" placeholder="First name" autofocus />
				</div>
				<span id="msg-firstname" class="col-md-5"></span>
			</div>

			<div class="form-group">
				<div class="col-md-7">
					<input id="lastname" name="lastname" type="text" class="form-control" placeholder="Last name" />
				</div>
				<span id="msg-lastname" class="col-md-5"></span>
			</div>

			<div class="form-group">
				<div class="col-md-7">
					<input id="email" name="email" type="text" class="form-control" placeholder="Email Address" />
					<%-- <div class="input-group">
						<input id="email" name="email" type="text" class="form-control" placeholder="Email Address" />
						<span class="input-group-addon"><strong class="text-muted">@sau.ac.th</strong></span>
					</div> --%>
				</div>
				<span id="msg-email" class="col-md-5"></span>
			</div>
			
			<div class="form-group">
				<div class="col-md-7">
					<input id="password" name="password" type="password" class="form-control" placeholder="Password" />
				</div>
				<span id="msg-password" class="col-md-5"></span>
			</div>

			<div class="form-group">
				<div class="col-md-7">
					<input id="birthday" name="birthday" type="text" class="form-control" placeholder="Birthday" autocomplete="off" readonly style="cursor: pointer;" />
				</div>
				<div id="msg-birthday" class="col-md-5"></div>
			</div>

			<div class="form-group">
				<div class="col-md-7">
					<%
						ReCaptcha c = ReCaptchaFactory.newReCaptcha("6LffNf0SAAAAAP8tl_gKhFeHbn6d40iARTgotca7", "6LffNf0SAAAAANVQKrXWtQ0wTXNWcTYcF-EsDxai", false);
						out.print(c.createRecaptchaHtml(null, null));
					%>
				</div>
				<div id="msg-captcha" class="col-md-5"></div>
			</div>

			<div class="form-group">
				<div class="col-md-3">
					<input id="back" type="button" value="Back" class="btn btn-info btn-block btn-back" onclick="fnBack()"/>
				</div>
				<div class="col-md-4">
					<input id="submit" type="submit" value="Register" class="btn btn-success btn-block" />
				</div>
			</div>
			
		</s:form>
	</div>
	
	<script type="text/javascript">
		$(function() {
			datePicker();
		});
		
		function fnBack() {
			window.location = "<s:url action='login' namespace='/' />";
		}
		
		function datePicker() {
			$('#birthday').datepicker( {
						changeMonth : true,
						changeYear : true,
						dateFormat : 'yy-MM-dd',
						yearRange : '1900:2000',
						defaultDate : new Date(1992, 00, 01)
						/* onSelect: function(dateText) {
							var currentDate = $('#birthday').datepicker('getDate');
							day  = currentDate.getDate(),  
							month = currentDate.getMonth() + 1,              
							year =  currentDate.getFullYear();
							console.log(day + ' ' + month + ' ' + year);
						} */
						/* monthNamesShort : [ "January", "February", "March",
								"April", "May", "June", "July", "August",
								"September", "October", "November", "December" ], */
						// dayNamesMin : [ 'Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday' ]
			});
		}
		
		
		$('#frmAddUser').submit(function() {
			var firstname = $('#firstname').val();
			var lastname = $('#lastname').val();
			var email = $('#email').val();
			var password = $('#password').val();
			var birthday = $('#birthday').val();
			var captcha = $('#recaptcha_response_field').val();
			if (chkFirstname(firstname) & chkLastname(lastname) & chkEmail(email) & chkPassword(password) & chkBirthday(birthday) & chkCaptcha(captcha)) {
				$('#indicator').css({ display: 'block' });
				$('#submit, #back').attr('disabled', 'disabled');
				$('#back').attr('style', '');
				return true;
			}
			return false;
		});
		
		function chkFirstname(firstname) {
			if (firstname == '') {
				document.getElementById('msg-firstname').innerHTML = "<label class='control-label msg-error'>กรุณากรอก ชื่อ</label>";
				return false;
			} else if (chkDuplicate(firstname, 'msg-firstname', 5)) {
				return false;
			}
			return true;
		}
		function chkLastname(lastname) {
			if (lastname == '') {
				document.getElementById('msg-lastname').innerHTML = "<label class='control-label msg-error'>กรุณากรอก นามสกุล</label>";
				return false;
			}else if (chkDuplicate(lastname, 'msg-lastname', 5)) {
				return false;
			}
			return true;
		}
		function chkEmail(email) {
			// + ตัวอักษรซ้ำ 1 ตัวขึ้นไป
			var emailReg = /^[a-zA-Z0-9._-]+\@sau.ac.th$/;
			var regExp1 = /@{2,}/; // 2 ครั้งหรือมากกว่า
			var regExp2 = /@{1,}[a-zA-Z0-9._-]+@{1,}/;
			if (email == '') {
				document.getElementById('msg-email').innerHTML = "<label class='control-label msg-error'>กรุณากรอก อีเมล์</label>";
				return false;
			}else if (regExp1.test(email) || regExp2.test(email)) {
				document.getElementById('msg-email').innerHTML = "<label class='control-label msg-error'>ห้ามกรอก @ เกิน 1 ตัว</label>";
				return false;
			}else if (!emailReg.test(email)) {
				document.getElementById('msg-email').innerHTML = "<label class='control-label msg-error'>กรุณากรอก อีเมล์ @sau.ac.th</label>";
				return false;
			}
			return true;
		}
		function chkPassword(password) {
			if (password == '') {
				document.getElementById('msg-password').innerHTML = "<label class='control-label msg-error'>กรุณากรอก รหัสผ่าน</label>";
				return false;
			} else if (password.length < 6) {
				document.getElementById('msg-password').innerHTML = "<label class='control-label msg-error'>รหัสผ่านอย่างน้อย 6 ตัว</label>";
				return false;
			}
			return true;
		}
		function chkBirthday(birthday) {
			
			// มากกว่าหรือเท่ากับ 18 - ตั้งแต่ 18 ขึ้นไป
			/* var today = new Date();
			
			var currentDate = $('#birthday').datepicker('getDate');
			day  = currentDate.getDate(),  
			month = currentDate.getMonth() + 1,              
			year =  currentDate.getFullYear();
			console.log(day + ' ' + month + ' ' + year); */ 
			
			if (birthday == '') {
				document.getElementById('msg-birthday').innerHTML = "<label class='control-label msg-error'>กรุณาเลือก วันเกิด</label>";
				return false;
			}
			/* else if (year > today.getFullYear() - 18) {
				document.getElementById('msg-birthday').innerHTML = "<label class='control-label msg-error'>กรุณาอายุ ตั้งแต่ 18 ปีขึ้นไป</label>";
				return false;
			} */
			return true;
		}
		
		function chkCaptcha(captcha) {
			if (captcha == '') {
				document.getElementById('msg-captcha').innerHTML = "<label class='control-label msg-error'>กรุณากรอกรหัสภาพ</label>";
				return false;
			}
			return true;
		}
		
		
		// keypress
		$(document).on('keypress', '#firstname', function(e) {
			var code = (e.keyCode ? e.keyCode : e.which);
			var charCode = String.fromCharCode(code);
			// 37 left, 38 top , 39 right, 40 down, 46 del
			if (!charCode.match(/^[a-zA-Z0-9ก-์\t\b\r]+$/) && code != 46 && code != 37 && code != 38 && code != 39 && code != 40) {
				document.getElementById('msg-firstname').innerHTML = "<label class='control-label msg-error'>กรอก เฉพาะตัวอักษรเท่านั้น</label>";
				return false;
			}
			document.getElementById('msg-firstname').innerHTML = '';
			return true;
		});
		$(document).on('keypress', '#lastname', function(e) {
			var code = (e.keyCode ? e.keyCode : e.which);
			var charCode = String.fromCharCode(code);
			if (!charCode.match(/^[a-zA-Z0-9ก-์\t\b\r]+$/) && code != 46 && code != 37 && code != 38 && code != 39 && code != 40) {
				document.getElementById('msg-lastname').innerHTML = "<label class='control-label msg-error'>กรอก เฉพาะตัวอักษรเท่านั้น</label>";
				return false;
			}
			document.getElementById('msg-lastname').innerHTML = '';
			return true;
		});
		$(document).on('keypress', '#email', function(e) {
			var code = (e.keyCode ? e.keyCode : e.which);
			var charCode = String.fromCharCode(code);
			if (!charCode.match(/^[0-9a-zA-Z._\-\@\t\b\r]+$/) && code != 46 && code != 37 && code != 38 && code != 39 && code != 40) {
				document.getElementById('msg-email').innerHTML = "<label class='control-label msg-error'>กรอก เฉพาะตัวอักษรเท่านั้น</label>";
				return false;
			}
			document.getElementById('msg-email').innerHTML = '';
			return true;
		});
		$(document).on('keypress', '#password', function(e) {
			var code = (e.keyCode ? e.keyCode : e.which);
			var charCode = String.fromCharCode(code);
			if (!charCode.match(/^[0-9a-zA-Z\t\b\r]+$/) && code != 46 && code != 37 && code != 38 && code != 39 && code != 40) {
				document.getElementById('msg-password').innerHTML = "<label class='control-label msg-error'>กรอก เฉพาะตัวอักษรเท่านั้น</label>";
				return false;
			}
			document.getElementById('msg-password').innerHTML = '';
			return true;
		});
		
		// Focus
		$(document).on('focus', '#firstname', function() {
			document.getElementById('msg-firstname').innerHTML = '';
		});
		$(document).on('focus', '#lastname', function() {
			document.getElementById('msg-lastname').innerHTML = '';
		});
		$(document).on('focus', '#email', function() {
			document.getElementById('msg-email').innerHTML = '';
		});
		$(document).on('focus', '#password', function() {
			document.getElementById('msg-password').innerHTML = '';
		});
		$(document).on('focus', '#birthday', function() {
			document.getElementById('msg-birthday').innerHTML = '';
		});
		$(document).on('focus', '#recaptcha_response_field', function() {
			document.getElementById('msg-captcha').innerHTML = '';
		});
		
	</script>
</body>
</html>