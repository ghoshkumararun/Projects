<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/pages/main/common/header-main.jsp" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/jquery-imgareaselect/css/imgareaselect-default.css" >
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-imgareaselect/scripts/jquery.imgareaselect.pack.js"></script>
<script type="text/javascript">
	$(function() {
		$('#imgProfile').imgAreaSelect({ aspectRatio: '3:3', handles: true });
	});
</script>
</head>
<body>
	<%@ include file="/pages/main/common/navbar.jsp" %>
	
	<div class="container">
		<div class="row section-main">
			<div class="nav sidebar-section">
				<%@ include file="/pages/main/setting/common/sidebar.jsp"%>
			</div>
			<div class="content-layout">
				<div class="content">
					<div class="form-horizontal">
						<div class="form-group" style="text-align: center;">
							<img id="imgProfile" name="imgProfile" src="${pageContext.request.contextPath}/resources/photos/${photo.userId}/${photo.photoUrl}" ><br>
						</div>
						<%-- <p class="text-muted" style="font-size: 18px; font-weight: bold; margin-bottom: 10px; text-align: center;">Profile Photo Example</p>
						<div class="form-group">
							<div id="preview" style="width: 150px; height: 150px; overflow: hidden; margin: 0 auto;">
								<img src="${pageContext.request.contextPath}/resources/photos/${photo.userId}/${photo.photoUrl}" >
							</div>
						</div> --%>
						<div class="form-group">
							<span class="col-md-offset-2 col-md-3"><strong>X Coordinate</strong></span>
							<span class="col-md-1 x-coordinate"></span>
						</div>
						<div class="form-group">
							<span class="col-md-offset-2 col-md-3"><strong>Y Coordinate</strong></span>
							<span class="col-md-1 y-coordinate"></span>
						</div>
						<div class="form-group">
							<span class="col-md-offset-2 col-md-3"><strong>Selection Width</strong></span>
							<span class="col-md-1 selection-width"></span>
						</div>
						<div class="form-group">
							<span class="col-md-offset-2 col-md-3"><strong>Selection Height</strong></span>
							<span class="col-md-1 selection-height"></span>
						</div>
						
						<div class="form-group">
							<div class="col-md-offset-9 col-md-2">
								<input type="hidden" id="photoUrl" value="${photo.photoUrl}">
								<input type="hidden" id="x">
								<input type="hidden" id="y">
								<input type="hidden" id="width">
								<input type="hidden" id="height">
								<input type="submit" name="submit" value="Save" class="btn btn-block btn-success btn-save"/>
							</div>
						</div>
					</div>
					
				</div>
			</div>
			<script type="text/javascript">
				$(function() {
					// แก้ไขความกว้าง ยาว
					/* var widthPhoto = $('#imgProfile').width();
					var heightPhoto = $('#imgProfile').height();
					if (widthPhoto > 600) {
						widthPhoto = widthPhoto / 2;
						heightPhoto = heightPhoto / 2;
					}else if (widthPhoto > 1000) {
						widthPhoto = widthPhoto / 3;
						heightPhoto = heightPhoto / 3;
					}
					$('#imgProfile').css({width : widthPhoto, height: heightPhoto}); */
					
					
					$('#imgProfile').imgAreaSelect({handles: true, onSelectEnd: preview});
					
					function preview(img, selection) {
						
						/* // มาตราส่วน, อัตราส่วน
					    var scaleX = 150 / selection.width;
					    var scaleY = 150 / selection.height;
					    
					    // ใช้ปัดเศษทศนิยมตามปกติ
					    var width = Math.round(scaleX * widthPhoto);
					    var height = Math.round(scaleY * heightPhoto);
					    
					    var marginLeft = -Math.round(scaleX * selection.x1);
					    var marginTop = -Math.round(scaleY * selection.y1);
					    $('#preview img').css({
					        width: width,
					        height: height,
					        marginLeft: marginLeft,
					        marginTop: marginTop
					    }); */ 
					    
					    // hidden
					    $('#x').val(selection.x1);
					    $('#y').val(selection.y1);
					    $('#width').val(selection.width);
					    $('#height').val(selection.height);
					    
					    // แสดงข้อความ
					    $('.x-coordinate').text(selection.x1);
					    $('.y-coordinate').text(selection.y1);
					    $('.selection-width').text(selection.width);
					    $('.selection-height').text(selection.height);
					}
					
					$(document).on('click', '.btn-save', function() {
						var photoUrl = $('#photoUrl').val();
						var x = $('#x').val();
					    var y = $('#y').val();
					    var width = $('#width').val();
					    var height = $('#height').val();
					    /* alert("x " + x);
					    alert("y " + y);
					    alert(width);
					    alert(height); */
					    if (x == '' || y == '' || width == '' || height == '') {
					    	alert("กรุณา Crop รูปภาพ Profile");
					    	return false;
					    }
						$.ajax({
							url: '<s:url action="profilePhotoStep2Ajax" namespace="/setting" />', 
							data: {photoUrl: photoUrl, x: x, y: y, width: width, height: height},
							type: 'post',
							success: function() {
								alert("ระบบทำการบันทึกข้อมูลเรียบร้อยแล้ว");
								window.location = '${pageContext.request.contextPath}/setting/personalData';
								/* window.location = '<s:url action="%{#session.user.urlProfile}" namespace="/profile" />'; */
							}
						});
					});
				});
			</script>
		</div>
	</div>
</body>
</html>