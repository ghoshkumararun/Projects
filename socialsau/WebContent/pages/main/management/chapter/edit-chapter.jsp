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
					<div class="form-horizontal">
						<div class="form-group">
							<label for="chapterName" class="col-md-2 control-label">Name</label>
							<div class="col-md-5">
								<input type="text" id="chapterName" name="chapterName" class="form-control" value="${chapter.chapterName}">
							</div>
							<span id="msg-chapterName" class="col-md-5"></span>
						</div>

						<div class="form-group">
							<div class="col-md-12">
								<textarea id="editorChapter" name="editorChapter">${chapter.chapterDetail}</textarea>
							</div>
						</div>

						<div class="form-group">
							<span id="msg-editorChapter" class="col-md-5"></span>
							<div class="col-md-offset-4 col-md-3">
								<input type="hidden" id="tutorialId" name="tutorialId" value="${tutorial.tutorialId}" />
								<input type="hidden" id="chapterId" name="chapterId" value="${chapter.chapterId}" />
								<input type="button" value="SAVE" class="btn btn-block btn-editChapter" />
							</div>
						</div>
					</div>
					
					<script type="text/javascript">
						$('.btn-editChapter').on('click', function() {
							var tutorial = $('#tutorialId').val();
							var chapter = $('#chapterId').val();
							var chapterName = $('#chapterName').val();
							var editorChapter = CKEDITOR.instances.editorChapter.getData();
							var editorChapterReport = CKEDITOR.instances.editorChapter.document.getBody().getText();
							
							if (chkChapterName(chapterName) & chkEditorChapter(editorChapter)) {
								$.ajax({
									url: '<s:url action="editChapterEditAJAX" namespace="/chapter" />',
									data: {tutorialId: tutorial, chapterId: chapter, chapterName: chapterName, chapterDetail: editorChapter, chapterDetailReport: editorChapterReport},
									type: 'POST',
									success: function(result) {
										alert("ระบบทำการบันทึกข้อมูลเรียบร้อยแล้ว");
										window.location = "${pageContext.request.contextPath}/chapter/allChapter?tutorialId=${tutorial.tutorialId}";
									},
									error: function() {
										alert("การทำงานผิดพลาดภายในโปรแกรม");
									}
								});
							}
								
						});
						
						function chkChapterName(chapterName) {
							if (chapterName == '') {
								document.getElementById('msg-chapterName').innerHTML = "<label class='msg-error'>กรุณากรอก 'ชื่อบทเรียน'</label>";
								return false;
							}
							return true;
						}
						
						function chkEditorChapter(editorChapter) {
							if (editorChapter.length == 0) {
								document.getElementById('msg-editorChapter').innerHTML = "<label class='msg-error'>กรุณากรอก 'รายละเอียดของบทเรียน'</label>";
								return false;
							}
							return true;
						}
						
						$(document).on("keypress", "#chapterName", function(){
							document.getElementById('msg-chapterName').innerHTML = '';
						});
						
						CKEDITOR.replace('editorChapter',{
											on: { focus: focusEditorChapter },
											height : '300',
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
							document.getElementById('msg-editorChapter').innerHTML = '';
						}
					</script>
				</div>
			</div>
		</div>
	</div>
</body>
</html>