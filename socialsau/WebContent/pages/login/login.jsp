<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/pages/common/header-login.jsp"%>
<script src="${pageContext.request.contextPath}/resources/js/chkDuplicate.js" type="text/javascript"></script>
</head>
<body class="bgpattern" >
	<div class="container">
		<div class="row">
			<div class="content-main">
				<div class="form-horizontal">
					<div class="form-group">
						<div class="col-md-7">
							<img src="${pageContext.request.contextPath}/resources/images/login.png">
						</div>
						<div class="col-md-5">
						
							<s:form id="frmLogin" action="login-submit" namespace="/login" method="post" cssClass="form-horizontal">
								<h3 style="margin-bottom: 20px;">Please Sign in &nbsp;<span class="glyphicon glyphicon-lock"></span></h3>
								<br>
								<s:actionerror cssClass="alert bg-warning text-danger" cssStyle="list-style: none"/>
								<s:actionmessage cssClass="alert bg-success text-success" cssStyle="list-style: none"/>
								<div class="form-group">
									<div class="col-md-12">
										<input id="emailLogin" name="emailLogin" type="text" class="form-control" placeholder="Email" autofocus/>
										<span id="msg-emailLogin" class="col-md-12"></span>
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-12">
										<input id="passwordLogin" name="passwordLogin" type="password" class="form-control" placeholder="Password" />
										<span id="msg-passwordLogin" class="col-md-12"></span>
									</div>
								</div>
								
								<div class="form-group" >
									<div class="col-md-5">
										<input id="submit" type="submit" value="Sign in" class="btn btn-success btn-block" />
									</div>
									<div class="col-md-7">
										<img id="indicator" src="${pageContext.request.contextPath}/resources/images/indicator.gif" style="display: none;">
									</div>
								</div>
								<a href="<s:url action='register' namespace='/'/>" style="text-decoration: none;"> <span class="glyphicon glyphicon-globe"></span> &nbsp;Register now. </a>
								<br/>
								<a href="<s:url action='checkConfirmCode' namespace='/user'/>" style="text-decoration: none;"> 
									<span class="glyphicon glyphicon-retweet"></span> &nbsp;Confirmation Email
								</a>
							</s:form>
						</div>
					</div>
				</div>
				
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$("#frmLogin").submit(function() {
			var email = $('#emailLogin').val();
			var password = $('#passwordLogin').val();
			if (chkEmail(email) & chkPassword(password)) {
				$('#indicator').css({ display: 'block'});
				return true;
			}
			return false;
		});
	
		function chkEmail(email) {
			// + ตัวอักษรซ้ำ 1 ตัวขึ้นไป
			var emailReg = /^[a-zA-Z0-9._-]+\@sau.ac.th$/;
			var regExp1 = /@{2,}/; // 2 ครั้งหรือมากกว่า
			var regExp2 = /@{1,}[a-zA-Z0-9._-]+@{1,}/;
			if (email == '') {
				document.getElementById('msg-emailLogin').innerHTML = "<label class='control-label msg-error'>กรุณากรอก อีเมล์</label>";
				return false;
			}else if (regExp1.test(email) || regExp2.test(email)) {
				document.getElementById('msg-emailLogin').innerHTML = "<label class='control-label msg-error'>ห้ามกรอก @ เกิน 1 ตัว</label>";
				return false;
			}else if (!emailReg.test(email)) {
				document.getElementById('msg-emailLogin').innerHTML = "<label class='control-label msg-error'>กรุณากรอก อีเมล์ @sau.ac.th</label>";
				return false;
			}
			return true;
		}
		function chkPassword(password) {
			if (password == '') {
				document.getElementById('msg-passwordLogin').innerHTML = "<label class='control-label msg-error'>กรุณากรอก รหัสผ่าน</label>";
				return false;
			} else if (password.length < 6) {
				document.getElementById('msg-passwordLogin').innerHTML = "<label class='control-label msg-error'>รหัสผ่านอย่างน้อย 6 ตัว</label>";
				return false;
			}
			return true;
		}
	
		// keypress 
		$(document).on('keypress', '#emailLogin', function(e) {
			var code = (e.keyCode ? e.keyCode : e.which);
			var charCode = String.fromCharCode(code);
			// 37 left, 38 top , 39 right, 40 down, 46 del
			if (!charCode.match(/^[a-zA-Z0-9._\-\@\t\b\r]+$/) && code != 46 && code != 37 && code != 38 && code != 39 && code != 40) {
				document.getElementById('msg-emailLogin').innerHTML = "<label class='control-label msg-error'>กรอก เฉพาะตัวอักษรเท่านั้น</label>";
				return false;
			}
			document.getElementById('msg-emailLogin').innerHTML = '';
			return true;
		});
		$(document).on('keypress', '#passwordLogin', function(e) {
			var code = (e.keyCode ? e.keyCode : e.which);
			var charCode = String.fromCharCode(code);
			// \d 0-9
			if (!charCode.match(/^[a-zA-Z0-9\t\b\r]+$/) && code != 46 && code != 37 && code != 38 && code != 39 && code != 40) {
				document.getElementById('msg-passwordLogin').innerHTML = "<label class='control-label msg-error'>กรอก เฉพาะตัวอักษรเท่านั้น</label>";
				return false;
			}
			document.getElementById('msg-passwordLogin').innerHTML = '';
			return true;
		});
		
		//focus
		$(document).on('focus', '#emailLogin', function() {
			document.getElementById('msg-emailLogin').innerHTML = '';
		});
		$(document).on('focus', '#passwordLogin', function() {
			document.getElementById('msg-passwordLogin').innerHTML = '';
		});
		
	</script>
</body>
</html>