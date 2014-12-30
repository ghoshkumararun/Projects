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
					<p class="text-muted" style="font-size: 24px; font-weight: bold;">Create Tutorial</p>
					<s:actionerror cssClass="alert bg-warning text-danger" cssStyle="list-style: none"/>
					<s:actionmessage cssClass="alert bg-success text-success" cssStyle="list-style: none"/>
					
					<s:form id="formTutorial" action="createTutorialPerform" namespace="/tutorial" cssClass="form-horizontal" method="post" enctype="multipart/form-data">
						<div class="form-group">
							<label for="txttutorialName" class="col-md-2 control-label">Tutorial</label>
							<div class="col-md-5">
								<input type="text" id="tutorialName" name="tutorialName" placeholder="Enter tutorial" class="form-control">
							</div>
							<span id="msg-tutorialName" class="col-md-5"></span>
						</div>
						<div class="form-group">
							<label for="groupTutorial" class="col-md-2 control-label">Group</label>
							<div class="col-md-5">
								<select id="groupTutorial" name="groupTutorial" class="form-control">
									<option value="0">Select Group Tutorial</option>
									<c:forEach var="group" items="${groups}">
										<option value="${group.groupId}">${group.groupName}</option>
									</c:forEach>
								</select>
							</div>
							<span id="msg-groupTutorial" class="col-md-5"></span>
						</div>
						<div class="form-group">
							<label class="col-md-2">Image</label>
							<div class="col-md-5">
								<input type="file" id="upload" name="upload" accept="image/*">
							</div>
						</div>

						<div class="form-group">
							<div class="col-md-12">
								<textarea id="tutorialDetail" name="tutorialDetail"></textarea>
							</div>
						</div>

						<div class="form-group">
							<span id="msg-tutorialDetail" class="col-md-5"></span>
							<div class="col-md-offset-4 col-md-3">
								<input type="hidden" id="tutorialDetailReport" name="tutorialDetailReport">
								<input type="submit" value="SAVE" class="btn btn-block btn-tutorial" />
							</div>
						</div>
					</s:form>
					
					<script type="text/javascript">
					
						$(function() {
							// CKEDITOR.instances.editorTutorial.getData() เอาข้อมูล html
							// CKEDITOR.instances.editorTutorial.document.getBody().getText(); เอาข้อมูล text
							$('#formTutorial').submit(function() {
								var tutorialName = $('#tutorialName').val();
								var groupTutorial = $('#groupTutorial').val();
								var tutorialDetail = CKEDITOR.instances.tutorialDetail.getData();
								var tutorialDetailReport = CKEDITOR.instances.tutorialDetail.document.getBody().getText();
								
								console.log();
								if (chkName(tutorialName) & chkGroup(groupTutorial) & chkEditorTutorial(tutorialDetail)) {
									$('#tutorialDetailReport').val(tutorialDetailReport);
									return true;
								}
								return false;
							});
						});
						
						// check via click
						function chkName(tutorialName) {
							if (tutorialName == '') {
								document.getElementById('msg-tutorialName').innerHTML = "<label class='msg-error'>กรุณากรอก 'ชื่อบทเรียน'</label>";
								return false;
							}
							return true;
						}
						function chkGroup(groupTutorial) {
							if (groupTutorial == '0') {
								document.getElementById('msg-groupTutorial').innerHTML = "<label class='msg-error'>กรุณาเลือก 'กลุ่ม'</label>";
								return false;
							}
							return true;
						}
						function chkEditorTutorial(tutorialDetail) {
							if (tutorialDetail == '') {
								document.getElementById('msg-tutorialDetail').innerHTML = "<label class='msg-error'>กรุณากรอก 'รายละเอียด'</label>";
								return false;
							}
							return true;
						}
						
						// focus via keyboard & focus/hover
						$(document).on("focus", "#groupTutorial", function(){
							document.getElementById('msg-groupTutorial').innerHTML = '';
						});
						$(document).on("focus", "#tutorialName", function(){
							document.getElementById('msg-tutorialName').innerHTML = '';
						});
						
						CKEDITOR.replace('tutorialDetail',{
											on: {
												focus: focusEditorTutorial
											},
											height : '200',
											toolbar : [
														[ 'Source' ],
														[ 'Bold', 'Italic', 'Underline', 'Strike'],
														[ 'Subscript', 'Superscript'],
														[ 'NumberedList', 'BulletedList'],
														[ 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock' ],
														[ 'Font', 'FontSize' ],
														[ 'TextColor', 'BGColor', 'Blockquote', 'Table']
													]
										});
						function focusEditorTutorial() {
							document.getElementById('msg-tutorialDetail').innerHTML = '';
						}
					</script>
				</div>
			</div>
		</div>
	</div>
</body>
</html>