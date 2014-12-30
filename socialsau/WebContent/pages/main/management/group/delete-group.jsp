<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<!-- Import CSS & JavaScript -->
<%@ include file="/pages/main/management/common/header-tutorials.jsp"%>
<body>
	<%@ include file="/pages/main/common/navbar.jsp"%>
	<div class="container">
		<div class="row section-main">
			<div class="nav sidebar-section">
				<%@ include file="/pages/main/management/common/main-sidebar.jsp" %>
			</div>

			<div class="content-layout">
				<div class="content">
					<p class="text-muted" style="font-size: 24px; font-weight: bold;">Delete Group</p><br/>
					<table class="table table-hover">
						<thead style="background-color: #ddd;">
							<tr>
								<th style="text-align: center;">Group ID</th>
								<th>Group Name</th>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="group" items="${groups}">
								<tr>
									<td style="text-align: center;">${group.groupId}</td>
									<td>${group.groupName}</td>
									<td><a href="javascript:void(0)" onclick="return deleteGroup('${group.groupId}')"><span class="glyphicon glyphicon-trash"></span></a></td>
									<td>
										<select class="status" data-id="${group.groupId}">
											<c:if test="${group.status == 'A'}">
												<option value="A" selected>Active</option>
												<option value="I">Inactive</option>
											</c:if>
											<c:if test="${group.status == 'I'}">
												<option value="A">Active</option>
												<option value="I" selected>Inactive</option>
											</c:if>
										</select>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
					<script type="text/javascript">
						$(document).on('change', '.status', function(){
							// console.log($(this).val());
							// console.log($(this).data('id'));
							var groupId = $(this).data('id');
							var status = $(this).val();
							$.ajax({
								url: '<s:url action="updateGroupStatusAjax" namespace="/group" />',
								data: {groupId: groupId, status: status},
								type: 'post',
								success: function() {
									if (status == 'A') {
										alert("ระบบทำการ \'แสดง\' กลุ่มบทเรียน เรียบร้อย");
									}else{
										alert("ระบบทำการ \'ซ่อน\' กลุ่มบทเรียน เรียบร้อย");
									}
								}
							});
						});
						
						function deleteGroup(groupId) {
							if (confirm("ยืนยันการลบ Group \n\n\'หมายเหตุ\' เมื่อท่านยืนยันการลบข้อมูล \nข้อมูลที่เกี่ยวข้องทั้งหมดจะถูกลบอย่างถาวร")) {
								$.ajax({
									url: '<s:url action="deleteGroupPerformAJAX" namespace="/group" />',
									data: {groupId: groupId},
									type: 'post',
									success: function() {
										location.reload();
									}
								});
							}
							
							/* $.ajax({
								url: '<s:url action="findGroupRelationship" namespace="/group" />',
								data: {groupId: groupId},
								type: 'post',
								success: function(data) {
									// fail, success
									var messageAlert;
									if (data == 'fail') {
										messageAlert = "Group มีความสัมพันธ์กับข้อมูล \n\n\'หมายเหตุ\' เมื่อท่านยืนยันการลบข้อมูล \nข้อมูลที่เกี่ยวข้องทั้งหมดจะถูกลบอย่างถาวร";
									}else {
										messageAlert = "ยืนยันการลบข้อมูล  Group";
									}
									
									if (confirm(messageAlert)) {
										$.ajax({
											url: '<s:url action="deleteGroupPerformAJAX" namespace="/group" />',
											data: {groupId: groupId},
											type: 'post',
											success: function() {
												location.reload();
											}
										});
									}
								}
							}); */
						}
					</script>
					
				</div>
			</div>
		</div>
	</div>
</body>
</html>