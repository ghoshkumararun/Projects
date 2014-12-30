<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/pages/main/management/common/header-tutorials.jsp"%>
<script src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js"type="text/javascript"></script>
</head>
<body>
	<%@ include file="/pages/main/common/navbar.jsp"%>
	<div class="container">
		<div class="row section-main">
			<div class="nav sidebar-section">
				<%@ include file="/pages/main/management/question/common/question-sidebar.jsp"%>
			</div>
			
			<div class="content-layout">
				<div class="content">
					<p class="text-muted" style="font-size: 24px; font-weight: bold;">Create Question with Radio</p>
					
					<div class="form-horizontal">
						
						<div class="form-group">
							<span class="col-md-2">Question <span id="msg-editorQuestion"></span></span>
							<div class="col-md-10">
								<textarea id="editorQuestion" name="editorQuestion" class="form-control"></textarea>
							</div>
						</div>
						
						<div class="form-group">
							<label for="questionA" class="col-md-2 control-label">Choice A </label>
							<div class="col-md-7">
								<input type="text" id="questionA" name="questionA" class="form-control" >
							</div>
							<span id="msg-questionA" class="col-md-3"></span>
						</div>
						
						<div class="form-group">
							<label for="questionB" class="col-md-2 control-label">Choice B </label>
							<div class="col-md-7">
								<input type="text" id="questionB" name="questionB" class="form-control" >
							</div>
							<span id="msg-questionB" class="col-md-3"></span>
						</div>
						
						<div class="form-group">
							<label for="questionC" class="col-md-2 control-label">Choice C </label>
							<div class="col-md-7">
								<input type="text" id="questionC" name="questionC" class="form-control" >
							</div>
							<span id="msg-questionC" class="col-md-3"></span>
						</div>
						
						<div class="form-group">
							<label for="questionD" class="col-md-2 control-label">Choice D </label>
							<div class="col-md-7">
								<input type="text" id="questionD" name="questionD" class="form-control" >
							</div>
							<span id="msg-questionD" class="col-md-3"></span>
						</div>
						
						<div class="form-group">
							<span class="col-md-2 control-label">Answer </span>
							<div class="col-md-10">
								<label class="radio-inline">
									<input type="radio" name="questionAnswers" value="A" class="rdoAnswer" checked> Choice A
								</label>
								<label class="checkbox-inline">
									<input type="radio" name="questionAnswers" value="B" class="rdoAnswer"> Choice B
								</label>
								<label class="checkbox-inline">
									<input type="radio" name="questionAnswers" value="C" class="rdoAnswer"> Choice C
								</label>
								<label class="checkbox-inline">
									<input type="radio" name="questionAnswers" value="D" class="rdoAnswer"> Choice D
								</label>
							</div>
						</div>
						
						<div class="form-group">
							<span class="col-md-2">Explanation <span id="msg-explanation"></span></span>
							<div class="col-md-10">
								<textarea id="editorExplanation" name="editorExplanation" class="form-control" style="resize: none;"></textarea>
							</div>
						</div>

						<div class="form-group">
							<div class="col-md-offset-9 col-md-3">
								<input type="hidden" id="tutorialId" value="${tutorial.tutorialId}" />
								<input type="button" value="SAVE" class="btn btn-block btn-question" />
							</div>
						</div>
					</div>
					
					
					<script type="text/javascript">
						$('.btn-question').on('click', function() {
							var tutorialId = $('#tutorialId').val();
							var questionName = CKEDITOR.instances.editorQuestion.getData();
							var questionA = $('#questionA').val();
							var questionB = $('#questionB').val();
							var questionC = $('#questionC').val();
							var questionD = $('#questionD').val();
							var explanation = CKEDITOR.instances.editorExplanation.getData();
							
							var questionNameReport = CKEDITOR.instances.editorQuestion.document.getBody().getText();
							var explanationReport = CKEDITOR.instances.editorExplanation.document.getBody().getText();
							
							var answer = $('.rdoAnswer:checked').val();
							
							if (chkEditorQuestion(questionName) & chkQuestionA(questionA) & chkQuestionB(questionB) & chkQuestionC(questionC) & chkQuestionD(questionD) & chkEditorExplanation(explanation)) {
								$.ajax({
									url: '<s:url action="createQuestionPerformAJAX" namespace="/question" />',
									data: {
										tutorialId: tutorialId,
										questionName: questionName,
										questionA: questionA,
										questionB: questionB,
										questionC: questionC,
										questionD: questionD,
										answer: answer,
										explanation: explanation,
										questionNameReport: questionNameReport, 
										explanationReport: explanationReport
									},
									type: 'POST',
									success: function() {
										alert("ระบบทำการบันทึกข้อมูลเรียบร้อยแล้ว");
										window.location = "allQuestion?tutorialId=${tutorial.tutorialId}";
									},
									error: function() {
										alert("การทำงานผิดพลาดภายในโปรแกรม");
									}
								});
							}
						});
						
						// validation
						function chkEditorQuestion(questionName) {
							if (questionName.length == 0) {
								document.getElementById('msg-editorQuestion').innerHTML = "<label class='msg-error'>กรุณากรอก<br/>'คำถาม'</label>";
								return false;
							}
							return true;
						}
						function chkQuestionA(questionA) {
							if (questionA == '') {
								document.getElementById('msg-questionA').innerHTML = "<label class='msg-error'>กรุณากรอก Choice A</label>";
								return false;
							}
							return true;
						}
						function chkQuestionB(questionB) {
							if (questionB == '') {
								document.getElementById('msg-questionB').innerHTML = "<label class='msg-error'>กรุณากรอก Choice B</label>";
								return false;
							}
							return true;
						}
						function chkQuestionC(questionC) {
							if (questionC == '') {
								document.getElementById('msg-questionC').innerHTML = "<label class='msg-error'>กรุณากรอก Choice C</label>";
								return false;
							}
							return true;
						}
						function chkQuestionD(questionD) {
							if (questionD == '') {
								document.getElementById('msg-questionD').innerHTML = "<label class='msg-error'>กรุณากรอก Choice D</label>";
								return false;
							}
							return true;
						}
						function chkEditorExplanation(explanation) {
							if (explanation.length == 0) {
								document.getElementById('msg-explanation').innerHTML = "<label class='msg-error'>กรุณากรอก<br/>'รายละเอียด'</label>";
								return false;
							}
							return true;
						}
						
						// focus
						$(document).on("focus", "#questionA", function(){
							document.getElementById('msg-questionA').innerHTML = '';
						}); 
						$(document).on("focus", "#questionB", function(){
							document.getElementById('msg-questionB').innerHTML = '';
						}); 
						$(document).on("focus", "#questionC", function(){
							document.getElementById('msg-questionC').innerHTML = '';
						}); 
						$(document).on("focus", "#questionD", function(){
							document.getElementById('msg-questionD').innerHTML = '';
						}); 
						$(document).on('focus', '.chkAnswer', function() {
							document.getElementById('msg-answer').innerHTML = '';
						});
						
						// CKEditor Question
						CKEDITOR.replace('editorQuestion', {
											on: {
												focus: focusEditorQuestion
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
						function focusEditorQuestion() {
							document.getElementById('msg-editorQuestion').innerHTML = '';
						}
						
						// CKEditor Question
						CKEDITOR.replace('editorExplanation', {
											on: {
												focus: focusEditorExplanation
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
						function focusEditorExplanation() {
							document.getElementById('msg-explanation').innerHTML = '';
						}
					</script>
				</div>
			</div>
		</div>
	</div>
</body>
</html>