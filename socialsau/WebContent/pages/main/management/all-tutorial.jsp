<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/pages/main/management/common/header-tutorials.jsp"%>
</head>
<script type="text/javascript">
	$(function() {
		fnComboboxTutorials();
	});
	
	/* เมื่อเลือก  combobox */
	$(document).on("change", "#groupTutorial", function() {
		fnComboboxTutorials();
	});
	
	function fnComboboxTutorials() {
		$('#loader').css({ display: 'block' });
		$.ajaxSetup({ async : false });
		
		// เอา value ของ Group ที่เลือก
		var groupTutorial = $('#groupTutorial option:selected').val();
		$('.tableTutorials').children().remove();
		$.ajax({
			url: '<s:url action="tutorialsByGroupId" namespace="/json" />',
			type: 'post',
			dataType: 'json',
			data: {groupId : groupTutorial},
			success: function(data) {
				$('.tableTutorials').append(
						'<thead style="background-color: #ddd;">' + 
							'<tr>' +
								'<th>No.</td>' +
								'<th>Tutorial</td>' +
								'<th>User Update</th>' +
								'<th>Update</th>' +
								'<th></th>' +
								'<th></th>' +
								'<th></th>' +
							'</tr>' +
						'</thead>' + 
						'<tbody>'
				);
				
				//console.log(data);
				if ( data != null ) {
					$.each(data, function(idx, obj){
						//console.log(obj);
						var imgTutorial;
						if (obj.tutorialImage != null) {
							imgTutorial = '<img id="image-profile" src="${pageContext.request.contextPath}/resources/photos-tutorial/' + obj.tutorialImage + '" width="50" height="50" class="img-rounded">'
						}else {
							imgTutorial = '<img src="${pageContext.request.contextPath}/resources/images/no-image-1.png" width="50" height="50" >';
						}
						
						// selected - dropdown
						var cmdStatus = '<select id="' + obj.tutorialId + '" data-id="' + obj.tutorialId + '" class="status">';
						if (obj.status == 'A' ) {
							cmdStatus += '<option value="A" selected>Active</option>' + '<option value="I">Inactive</option>';	
						}else {
							cmdStatus += '<option value="A">Active</option>' + '<option value="I" selected>Inactive</option>';
						}
						cmdStatus += '</select>';
						
						$('.tableTutorials').append(
								'<tr>' + 
									'<td width="10%">' + imgTutorial + '</td>' +
									'<td width="25%">' + obj.tutorialName + '</td>' + 
									'<td width="20%">' + obj.userUpdate + '</td>' +
									'<td width="25%">' + obj.tutorialUpdateString + '</td>' + 
									'<td width="5%" align="center">' + '<a href="viewTutorial?tutorialId=' + obj.tutorialId + '"><span class="glyphicon glyphicon-search"></span></a></td>' +
									'<td width="5%" align="center">' + '<a href="javascript:void(0);" onclick="return deleteTutorial(' + obj.tutorialId + ')"><span class="glyphicon glyphicon-trash"></span></a></td>' +
									'<td width="10%" align="center">' + cmdStatus + '</td>' +
								'</tr>'
						);	
					});
				}else {
					$('.tableTutorials').append(
							'<tr>' +
								'<td colspan="7" style="text-align: center;">No data</td>' +
							'</tr>'
					);
				}
				$('.tableTutorials').append('</tbody>');
			}
		});
		
		$.ajaxSetup({ async : true });
		$('#loader').css({ display: 'none' });
	}

	function deleteTutorial(tutorialId) {
		if (confirm("ยืนยันการลบ Tutorial \n\n\'หมายเหตุ\' เมื่อท่านยืนยันการลบข้อมูล \nข้อมูลที่เกี่ยวข้องทั้งหมดจะถูกลบอย่างถาวร")) {
			$.ajax({
				url: '<s:url action="deleteTutorialAJAX" namespace="/tutorial" />',
				data: {tutorialId: tutorialId},
				type: 'post',
				success: function() {
					location.reload();
				}
			});
		}
		
		/* // เช็คข้อมูลของ chapter question และ video ของ tutorial
		$.ajax({
			url: '<s:url action="findTutorialRelationship" namespace="/tutorial" />',
			data: {tutorialId: tutorialId},
			type: 'post',
			success: function(data) {
				// chapter_fail	question_fail video_fail
				// console.log(data);
				var messageAlert;
				if (data == "chapter_fail" || data == "question_fail" || data == "video_fail") {
					messageAlert = "Tutorial มีความสัมพันธ์กับข้อมูล \n\n\'หมายเหตุ\' เมื่อท่านยืนยันการลบข้อมูล \nข้อมูลที่เกี่ยวข้องทั้งหมดจะถูกลบอย่างถาวร";
				}else {
					messageAlert = "ยืนยันการลบข้อมูล  Tutorial";
				}
				
				if (confirm(messageAlert)) {
					$.ajax({
						url: '<s:url action="deleteTutorialAJAX" namespace="/tutorial" />',
						data: {tutorialId: tutorialId},
						type: 'post',
						success: function() {
							location.reload();
						}
					});
				}
				
			}
		}); */
	}
	
	$(document).on('change', '.status', function() {
		//console.log($(this).val());
		//console.log($(this).data('id'));
		var tutorialId = $(this).data('id');
		var status = $(this).val();
		$.ajax({
			url: '<s:url action="updateTutorialStatusAjax" namespace="/tutorial" />',
			data: {tutorialId: tutorialId, status: status},
			type: 'post',
			success: function() {
				if (status == 'A') {
					alert("ระบบทำการ \'แสดง\' วิชาเรียบร้อย");
				}else{
					alert("ระบบทำการ \'ซ่อน\' วิชาเรียบร้อย");
				}
			}
		});
	});
	
</script>
<body>
	<%@ include file="/pages/main/common/navbar.jsp"%>
	<div class="container">
		<div class="row section-main">
			<div class="nav sidebar-section">
				<%@ include file="/pages/main/management/common/main-sidebar.jsp"%>
			</div>
			<div class="content-layout">
				<div class="content">
					<div class="form-horizontal">
						<div class="form-group">
							<label for="groupTutorial" class="col-md-3 control-label text-muted">Group Tutorials</label>
							<div class="col-md-6">
								<select id="groupTutorial" name="groupTutorial" class="form-control">
									<option value="0">All Tutorials</option>
									<c:forEach var="group" items="${groups}">
										<option value="${group.groupId}">${group.groupName}</option>
									</c:forEach>
								</select>
							</div>
							<span class="col-md-2"><img id="loader" src="${pageContext.request.contextPath}/resources/images/indicator.gif" style="display: none; margin: 0 auto;"></span>
						</div>
						<div class="form-group">
							<table class="table table-hover tableTutorials">
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>