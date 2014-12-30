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
				<%@ include file="/pages/main/management/chapter/common/chapter-sidebar.jsp"%>
			</div>

			<div class="content-layout">
				<div class="content">
					<p class="text-muted" style="font-size: 24px; font-weight: bold;">Create Chapter</p>
					<div class="form-horizontal">
						<div class="form-group">
							<span class="col-md-2">Chapter No.</span>
							<div class="col-md-2">
								<span class="label label-default" style="font-size: 18px;">${lastChapterNo + 1}</span>
							</div>
						</div>
					
						<div class="form-group">
							<label for="chapterName" class="col-md-2 control-label">Name</label>
							<div class="col-md-5">
								<input type="text" id="chapterName" name="chapterName" class="form-control" >
							</div>
							<span id="msg-chapterName" class="col-md-5"></span>
						</div>

						<div class="form-group">
							<div class="col-md-12">
								<textarea id="editorChapter" name="editorChapter"></textarea>
							</div>
						</div>

						<div class="form-group">
							<span id="msg-chkEditorChapter" class="col-md-5"></span>
							<div class="col-md-offset-4 col-md-3">
								<input type="hidden" id="tutorialId" name="tutorialId" value="${tutorial.tutorialId}" />
								<input type="hidden" id="lastChapterNo" value="${lastChapterNo + 1}">
								<input type="button" value="SAVE" class="btn btn-block btn-chapter" />
							</div>
						</div>
					</div>
					
					<script type="text/javascript">
						$('.btn-chapter').on('click', function() {
							var id = $('#tutorialId').val();
							var lastChapterNo = $('#lastChapterNo').val();
							var name = $('#chapterName').val();
							var editorChapterDetail = CKEDITOR.instances.editorChapter.getData();
							var editorChapterDetailReport = CKEDITOR.instances.editorChapter.document.getBody().getText();
							
							if (chkName(name) & chkEditorChapter(editorChapterDetail)) {
								$.ajax({
									url: '<s:url action="createChapterSaveAJAX" namespace="/chapter" />',
									data: {tutorialId: id, lastChapterNo: lastChapterNo, chapterName: name, chapterDetail: editorChapterDetail, chapterDetailReport: editorChapterDetailReport},
									type: 'POST',
									success: function() {
										alert("ระบบทำการบันทึกข้อมูลเรียบร้อยแล้ว");
										window.location = "allChapter?tutorialId=${tutorial.tutorialId}";
									},
									error: function() {
										alert("การทำงานผิดพลาดภายในโปรแกรม");
									}
								});
							}
								
						});
						
						function chkName(name){
							if (name == '') {
								document.getElementById('msg-chapterName').innerHTML = "<label class='msg-error'>กรุณากรอก 'ชื่อบทเรียน'</label>";
								return false;
							}
							return true;
						}
						
						function chkEditorChapter(editorChapterDetail) {
							if (editorChapterDetail.length == 0) {
								document.getElementById('msg-chkEditorChapter').innerHTML = "<label class='msg-error'>กรุณากรอก 'รายละเอียดของบทเรียน'</label>";
								return false;
							}
							return true;
						}
						
						$(document).on("keypress", "#chapterName", function(){
							document.getElementById('msg-chapterName').innerHTML = '';
						});
						
						CKEDITOR.replace('editorChapter', {
											on: {
												focus: focusEditorChapter
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
						function focusEditorChapter() {
							document.getElementById('msg-chkEditorChapter').innerHTML = '';
						}
					</script>
				</div>
			</div>
		</div>
	</div>
</body>
</html>