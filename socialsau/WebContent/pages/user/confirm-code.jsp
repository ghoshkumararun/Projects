<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/pages/common/header-login.jsp"%>
<script src="${pageContext.request.contextPath}/resources/js/chkDuplicate.js" type="text/javascript"></script>
</head>

<body class="bgpattern">
	<div class="container-fluid shapes">
	
		<s:form id="frmConfirmCode" action="checkConfirmCodePerform" namespace="/user"  method="post" cssClass="form-horizontal" >
			<h2 style="margin-bottom: 40px;">Enter a Confirmation Code</h2>
			
			<div class="form-group">
				<div class="col-md-7">
					<s:actionerror cssClass="alert bg-warning text-danger" cssStyle="list-style: none"/>
					<s:actionmessage cssClass="alert bg-success text-success" cssStyle="list-style: none"/>
					<c:choose>
						<c:when test="${not empty param.confirmEmail}">
							<input id="confirmEmail" name="confirmEmail" type="text" class="form-control" placeholder="EMAIL" value="${param.confirmEmail}"/>
						</c:when>
						<c:otherwise>
							<input id="confirmEmail" name="confirmEmail" type="text" class="form-control" placeholder="EMAIL" autofocus autocomplete="off"/>
						</c:otherwise>
					</c:choose>
				</div>
				<span id="msg-confirmEmail" class="col-md-5"></span>
			</div>

			<div class="form-group">
				<div class="col-md-7">
					<c:choose>
						<c:when test="${not empty param.confirmCode}">
							<input id="confirmCode" name="confirmCode" type="text" class="form-control" placeholder="CODE" maxlength="20" autocomplete="off" value="${param.confirmCode}"/>
						</c:when>
						<c:otherwise>
							<input id="confirmCode" name="confirmCode" type="text" class="form-control" placeholder="CODE" autocomplete="off" maxlength="20" min="5"/>
						</c:otherwise>
					</c:choose>
				</div>
				<span id="msg-confirmCode" class="col-md-5"></span>
			</div>

			<div class="form-group">
				<div class="col-md-3">
					<input id="back" type="button" value="Back" class="btn btn-info btn-block" onclick="fnBack()"/>
				</div>
				<div class="col-md-4">
					<input id="submit" type="submit" value="Confirm" class="btn btn-success btn-block" />
				</div>
			</div>
			
		</s:form>
	</div>
	
	
	
	<script type="text/javascript">
	
		function fnBack() {
			window.location = "<s:url action='login' namespace='/' />";
		}
	
		$('#frmConfirmCode').submit(function() {
			var confirmEmail = $('#confirmEmail').val();
			var confirmCode = $('#confirmCode').val();
			if (chkEmailConfirmCode(confirmEmail) & chkConfirmCode(confirmCode)) {
				return true;
			}
			return false;
		});
		
		// check before submit
		function chkEmailConfirmCode(confirmEmail) {
			// + ตัวอักษรซ้ำ 1 ตัวขึ้นไป
			var emailReg = /^[a-zA-Z0-9._-]+\@sau.ac.th$/;
			var regExp1 = /@{2,}/; // 2 ครั้งหรือมากกว่า
			var regExp2 = /@{1,}[a-zA-Z0-9._-]+@{1,}/;
			if (confirmEmail == '') {
				document.getElementById('msg-confirmEmail').innerHTML = "<label class='control-label msg-error'>กรุณากรอก อีเมล์</label>";
				return false;
			}else if (regExp1.test(confirmEmail) || regExp2.test(confirmEmail)) {
				document.getElementById('msg-confirmEmail').innerHTML = "<label class='control-label msg-error'>ห้ามกรอก @ เกิน 1 ตัว</label>";
				return false;
			}else if (!emailReg.test(confirmEmail)) {
				document.getElementById('msg-confirmEmail').innerHTML = "<label class='control-label msg-error'>กรุณากรอก อีเมล์ @sau.ac.th</label>";
				return false;
			}
			return true;
		}
		function chkConfirmCode(confirmCode) {
			if (confirmCode == '') {
				document.getElementById('msg-confirmCode').innerHTML = "<label class='control-label msg-error'>กรุณากรอก Confirm Code</label>";
				return false;
			} else if (confirmCode.length != 20) {
				document.getElementById('msg-confirmCode').innerHTML = "<label class='control-label msg-error'>Confirm Code จำนวน 20 หลัก</label>";
				return false;
			}
			return true;
		}
		
		// keypress
		$(document).on('keypress', '#confirmEmail', function(e) {
			var code = (e.keyCode ? e.keyCode : e.which);
			var strInput = String.fromCharCode(code);
			// \d 0-9
			if (!strInput.match(/^[a-zA-Z0-9._\-\@\t\b\r]+$/) && code != 46 && code != 37 && code != 38 && code != 39 && code != 40) {
				document.getElementById('msg-confirmEmail').innerHTML = "<label class='control-label msg-error'>กรอก เฉพาะตัวอักษรเท่านั้น</label>";
				return false;
			}
			document.getElementById('msg-confirmEmail').innerHTML = "";
			return true;
		});
		$(document).on('keypress', '#confirmCode', function(e) {
			var code = (e.keyCode ? e.keyCode : e.which);
			var strInput = String.fromCharCode(code);
			if (!strInput.match(/^[a-zA-Z0-9\t\b\r]+$/) && code != 46 && code != 37 && code != 38 && code != 39 && code != 40) {
				document.getElementById('msg-confirmCode').innerHTML = "<label class='control-label msg-error'>กรอก เฉพาะตัวอักษรเท่านั้น</label>";
				return false;
			}/* else if ($('#confirmCode').val().length + 1 > 20) {
				document.getElementById('msg-confirmCode').innerHTML = "<label class='control-label msg-error'>Confirm Code จำนวน 20 ตัวเท่านั้น</label>";
				return false;
			} */
			document.getElementById('msg-confirmCode').innerHTML = "";
			return true;
		});
		
		// focus
		$(document).on('focus', '#confirmEmail', function() {
			document.getElementById('msg-confirmEmail').innerHTML = '';
		});
		$(document).on('focus', '#confirmCode', function() {
			document.getElementById('msg-confirmCode').innerHTML = '';
		});
	</script>
</body>
</html>