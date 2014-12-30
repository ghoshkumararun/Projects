<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/pages/main/management/common/header-tutorials.jsp"%>
<script src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js" type="text/javascript"></script>
</head>
<body>
	<%@ include file="/pages/main/common/navbar.jsp"%>
	<div class="container">
		<div class="row section-main">
			<div class="nav sidebar-section">
				<%@ include file="/pages/main/management/common/tutorial-sidebar.jsp"%>
			</div>
			<div class="content-layout">
				<div class="content">
					<s:actionerror cssClass="alert bg-warning text-danger" cssStyle="list-style: none"/>
					<s:actionmessage cssClass="alert bg-success text-success" cssStyle="list-style: none"/>
					
					<s:form id="formEditTutorial" action="editTutorialPerform" namespace="/tutorial" cssClass="form-horizontal" method="post" enctype="multipart/form-data">
						<div class="form-group">
							<span class="col-md-3 control-label text-muted">Tutorial Name </span> 
							<div class="col-md-4">
								<input type="text" id="tutorialName" name="tutorialName" class="form-control" value="${tutorial.tutorialName}"/>
							</div>
							<span id="msg-tutorialName" class="col-md-5"></span>
						</div>
						<div class="form-group">
							<span class="col-md-3 control-label text-muted">Group </span> 
							<div class="col-md-4">
								<select id="groupTutorial" name="groupTutorial" class="form-control">
									<option value="0">Select Group Tutorial</option>
									<c:forEach var="group" items="${groups}">
										<c:choose>
											<c:when test="${tutorial.groupId == group.groupId}">
												<option value="${group.groupId}" selected>${group.groupName}</option>
											</c:when>
											<c:otherwise>
												<option value="${group.groupId}">${group.groupName}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>
							<span id="msg-groupTutorial" class="col-md-5"></span>
						</div>
						
						<div class="form-group">
							<span class="col-md-3 text-muted">Image</span>
							<div class="col-md-9">
								<input type="file" id="upload" name="upload" accept="image/*">
							</div>
						</div>
						
						<div class="form-group">
							<c:choose>
								<c:when test="${tutorial.tutorialImage != null && tutorial.tutorialImage != ''}">
									<div class="col-md-offset-3 col-md-5">
										<img src="${pageContext.request.contextPath}/resources/photos-tutorial/${tutorial.tutorialImage}" width="100" height="100" class="img-rounded">
									</div>
								</c:when>
								<c:otherwise>
									<div class="col-md-offset-3 col-md-5">
										<img src="${pageContext.request.contextPath}/resources/images/no-image-1.png">
									</div>
								</c:otherwise>
							</c:choose>
						</div>
						
						<div class="form-group">
							<span class="col-md-3 text-muted">Create Date</span> 
							<span class="col-md-9 text-muted"><fmt:formatDate	pattern="yyyy-MM-dd HH:mm:ss" value="${tutorial.tutorialUpdate}" /></span>
						</div>
						<div class="form-group">
							<span class="col-md-3 text-muted">Introduction <span id="msg-tutorialDetail"></span></span> 
							<div class="col-md-9">
								<textarea id="tutorialDetail" name="tutorialDetail">${tutorial.tutorialDetail}</textarea>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-offset-9 col-md-3">
								<input type="hidden" id="tutorialId" name="tutorialId" value="${tutorial.tutorialId}"> 
								<input type="hidden" id="tutorialDetailReport" name="tutorialDetailReport">
								<input type="submit" value="EDIT" class="btn btn-block btn-chapter" />
							</div>
						</div>
						<script type="text/javascript">
							$(function() {
								// CKEDITOR.instances.editorTutorial.getData() เอาข้อมูล html
								// CKEDITOR.instances.editorTutorial.document.getBody().getText(); เอาข้อมูล text
								$('#formEditTutorial').submit(function() {
									var tutorialName = $('#tutorialName').val();
									var groupTutorial = $('#groupTutorial').val();
									var tutorialDetail = CKEDITOR.instances.tutorialDetail.getData();
									var tutorialDetailReport = CKEDITOR.instances.tutorialDetail.document.getBody().getText();
									if (chkTutorialName(tutorialName) & chkGroup(groupTutorial) & chkEditorTutorial(tutorialDetail)) {
										$('#tutorialDetailReport').val(tutorialDetailReport);
										return true;
									}
									return false;
								});
							});
							
							function chkTutorialName(tutorialName) {
								if (tutorialName == '') {
									document.getElementById('msg-tutorialName').innerHTML = "<label class='control-label msg-error'>กรุณากรอก 'ชื่อบทเรียน'</label>";
									return false;
								}
								return true;
							}
							
							function chkGroup(groupTutorial) {
								if (groupTutorial == 0) {
									document.getElementById('msg-groupTutorial').innerHTML = "<label class='control-label msg-error'>กรุณาเลือก 'กลุ่ม'</label>";
									return false;
								}
								return true;
							}
							
							function chkEditorTutorial(tutorialDetail) {
								if (tutorialDetail.length == 0) {
									document.getElementById('msg-tutorialDetail').innerHTML = "<label class='msg-error'>กรุณากรอก<br/>'รายละเอียด'</label>";
									return false;
								}
								return true;
							}
							
							$(document).on("change", "#groupTutorial", function(){
								document.getElementById('msg-groupTutorial').innerHTML = '';
							});
							$(document).on("keypress", "#tutorialName", function(){
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
													[ 'TextColor', 'BGColor', 'Blockquote', 'Table']]
									});
							function focusEditorTutorial() {
								document.getElementById('msg-tutorialDetail').innerHTML = '';
							}
						</script>
					</s:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>