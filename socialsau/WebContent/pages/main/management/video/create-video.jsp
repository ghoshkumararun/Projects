<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/pages/main/management/common/header-tutorials.jsp"%>
<script src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js"type="text/javascript"></script>
<style type="text/css">
	table, th, td {
	 	text-align: center;
	}	
</style>
</head>
<body>
	<%@ include file="/pages/main/common/navbar.jsp"%>
	<div class="container">
		<div class="row section-main">
			<div class="nav sidebar-section">
				<%@ include file="/pages/main/management/video/common/video-sidebar.jsp"%>
			</div>
			
			<div class="content-layout">
				<div class="content">
					<p class="text-muted" style="font-size: 24px; font-weight: bold;">Create Video</p>
					<div class="form-horizontal">
						<div class="form-group">
							<span class="col-md-2 control-label">Video name</span>
							<div class="col-md-10">
								<input type="text" id="videoName" class="form-control">
							</div>
							<span id="msg-videoName" class="col-md-5"></span>
						</div>
						<div class="form-group">
							<span class="col-md-2 control-label">URL</span>
							<div class="col-md-10">
								<textarea id="url" rows="2" style="width: 100%; resize: none;" class="form-control"></textarea>
							</div>
							<span id="msg-url" class="col-md-5"></span>
						</div>
						
						<div class="form-group">
							<span class="col-md-2">
								<input type="button" value="Preview" class="btn btn-block btn-danger btn-preview">
								<input type="hidden" id="tutorialId" value="${tutorial.tutorialId}">
								<br>
								<input type="button" class="btn btn-block btn-success btn-save" value="Save">
							</span>
							<div class="col-md-10 col-preview">
								
							</div>
						</div>
						
						<script type="text/javascript">
						
							// Click btn preview
							$(document).on('click', '.btn-preview', function() {
								var url = $('#url').val();
								if (chkUrl(url)){ 
									var code = codeURL(url);
									if (code == false) return false
									$('.col-preview').html('<iframe src="' + "http://www.youtube.com/embed/" + code + '" width="100%" height="300"></iframe>');
								}
							});
						
							// Click btn save
							$(document).on('click', '.btn-save', function() {
								var tutorialId = $('#tutorialId').val();
								var videoName = $('#videoName').val();
								var url = $('#url').val();
								
								if (chkVideoName(videoName) & chkUrl(url)) {
									var code = codeURL(url);
									if (code == false) return false;
									var youtube = "http://www.youtube.com/embed/" + code;
									
									$.ajax({
										url: '<s:url action="createVideoPerformAJAX" namespace="/video" />', 
										data: {tutorialId: tutorialId, videoName: videoName, url: youtube, code: code},
										type: 'post',
										success: function() {
											alert("ระบบทำการบันทึกข้อมูลเรียบร้อยแล้ว");
											window.location = "allVideo?tutorialId=${tutorial.tutorialId}";
										},
										error: function() {
											alert("การทำงานผิดพลาดภายในโปรแกรม");
										}
									});
								}
								return false;
							});
							
							function codeURL(url){
								var code = null;
								if (url.indexOf("www.youtube.com/watch?v") != -1) { // พบ
									var code = url.split('/watch?v=');
								}else if (url.indexOf("www.youtube.com/embed/") != -1) {
									var code = url.split('/embed/');
								}else {
									document.getElementById('msg-url').innerHTML = "<label class='control-label msg-error'>กรอก เฉพาะลิงค์วิดีโอ Youtube เท่านั้น</label>";
									return false;
								}
								return code[1];
							}
							
							// check input
							function chkVideoName(videoName) {
								if (videoName == '') {
									document.getElementById('msg-videoName').innerHTML = "<label class='msg-error'>กรุณากรอก 'ชื่อชื่อวิดีโอ'</label>";
									return false;
								}
								return true;
							}
							
							function chkUrl(url) {
								if (url == '') {
									document.getElementById('msg-url').innerHTML = "<label class='msg-error'>กรุณากรอก 'ลิงค์ของวิดีโอ'</label>";
									return false;
								}
								return true;
							}
							
							// focus
							$(document).on('focus', '#videoName', function() {
								document.getElementById('msg-videoName').innerHTML = '';
							});
							$(document).on('focus', '#url', function() {
								document.getElementById('msg-url').innerHTML = '';
							});
							
						</script>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>