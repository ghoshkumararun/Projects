<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<!-- Import CSS & JavaScript -->
<%@ include file="/pages/main/management/common/header-tutorials.jsp"%>
<script src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js"
	type="text/javascript"></script>

</head>
<body>
	<%@ include file="/pages/main/common/navbar.jsp"%>
	<div class="container">
		<div class="row section-main">
			<div class="nav sidebar-section">
				<%@ include file="/pages/main/management/common/main-sidebar.jsp" %>
			</div>

			<div class="content-layout">
				<div class="content">
					<p class="text-muted" style="font-size: 24px; font-weight: bold;">Create Group</p>
					<div class="form-horizontal">
					
						<div class="form-group">
							<label for="txtTutorial" class="col-md-2 control-label">Group name</label>
							<div class="col-md-5">
								<input type="text" id="groupName" name="groupName" placeholder="Enter Group" class="form-control">
							</div>
							<span id="msg-groupName" class="col-md-5"></span>
						</div>
						
						<div class="form-group">
							<div class="col-md-offset-2 col-md-3">
								<input type="button" value="SAVE" class="btn btn-block btn-group" />
							</div>
						</div>
						
						<script type="text/javascript">
							$('.btn-group').on('click', function() {
								var groupName = $('#groupName').val();
								
								if (chkGroupName(groupName)) {
									$.ajax({
										url: '<s:url action="createGroupPerformAJAX" namespace="/group" />',
										data: {groupName: groupName},
										type: 'POST',
										success: function(data) {
											alert("ระบบทำการบันทึกข้อมูลเรียบร้อยแล้ว");
											window.location = '${pageContext.request.contextPath}/group/deleteGroup';
										},
										error: function() {
											alert("การทำงานผิดพลาดภายในโปรแกรม");
										}
									});
								}
									
							});
							
							function chkGroupName(groupName) {
								if (groupName == '') {
									document.getElementById('msg-groupName').innerHTML = "<label class='control-label msg-error'>กรุณากรอก ชื่อกลุ่ม</label>";
									return false;
								}
								return true;
							}
							
							$('#groupName').on('focus', function() {
								document.getElementById('msg-groupName').innerHTML = '';
							});
						</script>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>