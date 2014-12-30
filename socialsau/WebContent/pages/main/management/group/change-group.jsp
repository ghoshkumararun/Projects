<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<!-- Import CSS & JavaScript -->
<%@ include file="/pages/main/management/common/header-tutorials.jsp"%>
<script src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js" type="text/javascript"></script>
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
					<p class="text-muted" style="font-size: 24px; font-weight: bold;">Change Group</p>
					<div class="form-horizontal">
					
						<div class="form-group">
							<label for="group" class="col-md-3 control-label">Group Tutorial</label>
							<div class="col-md-6">
								<select id="group" name="group" class="form-control">
									<option value="0">All Tutorials</option>
									<c:forEach var="group" items="${groups}">
										<option value="${group.groupId}">${group.groupName}</option>
									</c:forEach>
								</select>
							</div>
							<span id="msg-group" class="col-md-3"></span>
						</div>
					
						<div class="form-group">
							<label for="groupName" class="col-md-3 control-label">Group Tutorial New:</label>
							<div class="col-md-6">
								<input type="text" id="groupName" name="groupName" placeholder="Enter Group" class="form-control" readonly>
							</div>
							<span id="msg-groupName" class="col-md-3"></span>
						</div>
						<div class="form-group">
							<div class="col-md-offset-3 col-md-3">
								<input type="button" value="EDIT" class="btn btn-block btn-edit" />
							</div>
						</div>
						
						<script type="text/javascript">
							/* เมื่อเลือก  combobox */
							$(document).on('change', '#group', function() {
								var groupName = $('#group option:selected').text();
								var groupValue = $('#group option:selected').val();
								if (groupValue == 0) {
									$('#groupName').val('');
									$('#groupName').attr('readonly', 'true');
								}else {
									$('#groupName').val(groupName);
									$('#groupName').removeAttr('readonly');
								}
								document.getElementById('msg-group').innerHTML = '';
							});
							
							/* btn edit */
							$('.btn-edit').on('click', function() {
								var groupId = $('#group option:selected').val();
								var groupName = $('#groupName').val();
								
								if (chkCombobox(groupId) && chkGroupName(groupName)) {
									$.ajax({
										url: '<s:url action="changeGroupPerformAJAX" namespace="/group" />',
										data: {groupId: groupId, groupName: groupName},
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
							
							function chkCombobox(groupId) {
								if (groupId == 0) {
									document.getElementById('msg-group').innerHTML = "<label class='msg-error'>กรุณาเลือก 'กลุ่ม'</label>";
									return false;
								}
								return true;
							}
							function chkGroupName(groupName) {
								if (groupName == '') {
									document.getElementById('msg-groupName').innerHTML = "<label class='msg-error'>กรุณากรอก 'ชื่อกลุ่ม'</label>";
									return false;
								}
								return true;
							}
							
							// focus
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