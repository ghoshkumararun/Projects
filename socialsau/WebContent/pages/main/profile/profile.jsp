<%@page import="com.sau.socialsau.dto.User"%>
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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/magnific-popup.css" >
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.magnific-popup.js"></script>
<style type="text/css">
	a:HOVER, a:FOCUS {
		text-decoration: none;
	}
	.section-profile {
		margin-top: 50px;
		height: 200px;
		background-color: ${userProfile.colorProfile};
	}
	.border-dynamic {
		border-top: ${userProfile.colorProfile};
	}
	.bg-dynamic{
		background-color: ${userProfile.colorProfile};
		color: #fff;
	}
</style>
</head>
<link >
<body>
	<%@ include file="/pages/main/common/navbar.jsp" %>
	
	<div class="container">
		<div class="row">
			<div class="section-profile">
				<div class="form-group">
					<div class="col-sm-2">
						<c:if test="${not empty userProfile.imageProfile}">
							<a class="img-myprofile" href="${pageContext.request.contextPath}/resources/photos-profile/${userProfile.imageProfile}">
								<img id="image-profile" src="${pageContext.request.contextPath}/resources/photos-profile/${userProfile.imageProfile}" width="150" height="150" class="img-rounded" style="position: absolute; margin-top: 80px;">
							</a>
						</c:if>
						<c:if test="${empty userProfile.imageProfile}">
							<img src="${pageContext.request.contextPath}/resources/images/no-image-1.png" width="150" height="150" style="position: absolute; margin-top: 80px;" class="img-thumbnail">
						</c:if>
					</div>
					<div class="col-md-10">
						<h3 style="color: white; margin: 150px 0 0 10px;"><strong>${userProfile.firstname} &nbsp; ${userProfile.lastname}</strong></h3>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="container">
		<div class="row">
			<div class="border-dynamic" style="margin-top: 40px; margin-bottom: 10px;"></div>
			<div style="width: 49%; float: left; margin-right: 1%;">
			
				<!-- Personal Profile -->
				<div class="form-horizontal content">
					<h4 style="margin-bottom: 30px;">Personal<strong> Profile</strong></h4>
					<s:actionmessage cssClass="alert bg-success text-success" cssStyle="list-style: none"/>
					<div class="form-group">
						<div class="col-md-2"><strong>Email</strong></div>
						<div class="col-md-10"><span>${userProfile.email}</span></div>
					</div>
					<div class="form-group">
						<div class="col-md-2"><strong>Birthday</strong></div>
						<%
							User user = (User)request.getAttribute("userProfile");
							if (user != null) {
								DateFormat df = new SimpleDateFormat("EEEE ที่ d MMMM พ.ศ.yyyy", new Locale("th", "TH"));
								String birthday = df.format(user.getBirthday());
								pageContext.setAttribute("birthday", birthday);	
							}
						%>
						<%-- <fmt:formatDate pattern="EEEE MMMM dd, yyyy" value="${viewUser.birthday}"/> --%>
						<div class="col-md-10"><span>${pageScope['birthday']}</span></div>
					</div>
				</div>
				
				<!-- Profile Photos -->
				<div class="form-horizontal content">
					<div class="form-group">
						<h4 style="margin-bottom: 10px;" class="col-md-4">Profile<strong> Photos</strong></h4>
						<div class="col-md-offset-4 col-md-4">
							<input type="button" value="Upload Photos" class="btn btn-block bg-dynamic btn-fileupload">
						</div>
					</div>
					<div class="form-group" style="margin-left: 15px !important;">
						<c:forEach var="photo" items="${photos}">
							<c:if test="${userProfile.userId == photo.userId}">
								<a class="img-myprofile" href="${pageContext.request.contextPath}/resources/photos/${photo.userId}/${photo.photoUrl}">
									<img src="${pageContext.request.contextPath}/resources/photos/${photo.userId}/${photo.photoUrl}" width="100" height="100" class="img-rounded" style="margin: 2px 0;">
								</a>
							</c:if>
						</c:forEach>
					</div>
				</div>
				
			</div>
			
			<div style="width: 50%; float: left;">
			
				<!-- Post ... -->
				<div class="content-profile">
					<textarea id="post" rows="3" style="resize: none;" class="form-control" placeholder="Post ..."></textarea>
					<div class="form-group">
						<span id="msg-post" class="col-md-4" style="margin-top: 10px;"></span>
						<div class="col-md-offset-4 col-md-4" style="padding-right: 0;">
							<input type="button" value="Post" class="btn btn-block bg-dynamic btn-profile-post" style="margin-top: 10px;">
						</div>
					</div>
				</div>
				
				<c:forEach var="post" items="${posts}">
					<div class="form-horizontal content-profile">
					
						<!-- User Post -->
						<div class="form-group section-post">
							<!-- Image User -->
							<div class="col-md-2">
								<c:if test="${not empty post.imageProfile}">
									<a href="${post.urlProfile}">
										<img src="${pageContext.request.contextPath}/resources/photos-profile/${post.imageProfile}" width="50" height="50" class="img-rounded">
									</a>
								</c:if>
								<c:if test="${empty post.imageProfile}">
									<img src="${pageContext.request.contextPath}/resources/images/no-image-1.png" width="50" height="50" style="border: 1px solid #eee;" class="img-rounded">
								</c:if>
							</div>
							<!-- Date time -->
							<span class="col-md-8" style="vertical-align: middle;">
								<a href="${post.urlProfile}"><strong>${post.firstname} &nbsp; ${post.lastname} <br></strong></a>
								<fmt:formatDate pattern="MMMM dd, yyyy HH:mm:ss" value="${post.postCreate}"/>
							</span>
							
							<!-- ICON Edit & Delete -->
							<c:if test="${sessionScope['user'].userId == post.postBy }">
								<div class="col-md-2 text-right">
									<span class="glyphicon glyphicon-pencil btn-edit-post" data-id="${post.postId}" style="cursor: pointer; "></span>
									<span class="glyphicon glyphicon-trash btn-delete-post" data-id="${post.postId}" style="cursor: pointer;"></span>
								</div>
							</c:if>
						</div>
						<hr>
						
						<div class="form-group">
							<span class="col-md-12 postDetail" style="word-break: break-all;" data-id="${post.postId}">${post.postDetail}</span>
						</div>
						
					</div>
				</c:forEach>
				
				<!-- dialog icon edit -->
				<div id="dialog-edit-post" title="Edit Post">
					<form name="form-dialog" class="form-horizontal">
						<div class="form-group" style="margin-bottom: 0;">
							<textarea rows="5" cols="100" id="dialog-txt-post" class="form-control" data-id=""></textarea>
							<span id="msg-dialog-txt-post" class="col-md-12"></span>
						</div>
					</form>
				</div>
				
			</div>
		</div>
		
		<script type="text/javascript">
			$(document).ready(function(){
				
				$('.img-myprofile').magnificPopup({type:'image'});
				
				// คลิก Add Photos
				$(document).on('click', '.btn-fileupload', function() {
					window.location = '<s:url action="fileupload" namespace="/upload" />';
				});
				
				// กดปุ่ม edit
				// Step 1
				$(document).on('click', '.btn-edit-post', function() {
					var postId = $(this).data('id');
					$('#dialog-txt-post').val($('.postDetail[data-id=' + postId + ']').text());
					$('#dialog-txt-post').attr('data-id', postId);
					$( "#dialog-edit-post" ).dialog("open");
				});
				
				// Step 2
				$( "#dialog-edit-post" ).dialog({
					 autoOpen: false,
					 resizable: false,
				     modal: true, 
				     width: 500,
				     height: 300,
				     buttons: {
				    	 Save: function() {
				    		var postId = $('#dialog-txt-post').data('id');
				    		var postDetail = $('#dialog-txt-post').val();
				    		if (postDetail == '') {
				    			document.getElementById('msg-dialog-txt-post').innerHTML = "<label class='msg-error'>กรุณากรอก 'ข้อความ'</label>";
				    		}else {
				    			$.ajax({
				    				url: '<s:url action="editPostAjax" namespace="/profile" />',
				    				data: {postId: postId, postDetail: postDetail}, 
				    				type: 'POST',
				    				success: function() {
				    					$('.postDetail[data-id=' + postId + ']').text(postDetail);
				    				}
				    			});
				    			$(this).dialog("close");
				    		} 
				    		return false;
				    	 },
				    	 Reset: function() {
				    		$('#dialog-txt-post').val('');
				    	 }
				     },
				     close: function() {
				    	 $('#msg-dialog-txt-post').text('');
				    	 //document.getElementById('msg-dialog-txt-post').innerHTML = '';
				     }
				});
				 
			});
			
			
			$(document).on('click', '.btn-delete-post', function() {
				if (confirm("ยืนยันการลบ \n\n\'หมายเหตุ\' เมื่อยืนยันการลบข้อมูลของ Post \n" +  " ข้อมูล Post จะถูกลบอย่างถาวร")) {
					var postId = $(this).data('id');
					$.ajax({
						url: '<s:url action="deletePostAjax" namespace="/profile" />', 
						data: {postId: postId}, 
						type: 'POST',
						success: function() {
							$('.btn-delete-post[data-id=' + postId + ']').parent().parent().parent().fadeOut('slow');
						},
						error: function() {
							alert("การทำงานผิดพลาดภายในโปรแกรม");
						}
					});
				}
			});
			
			$(document).on('click', '.btn-profile-post', function() {
				var post = $('#post').val();
				if (chkPost(post)) {
					var userId = "${userProfile.userId}";
					var postBy = "${sessionScope['user'].userId}";
					$.ajax({
						url: '<s:url action="postAjax" namespace="/profile" />',
						data: {userId: userId, postBy: postBy, post: post},
						type: 'POST',
						success: function() {
							//alert("ระบบทำการบันทึกข้อมูลเรียบร้อยแล้ว");
							$('#post').val('');
							location.reload();
						},
						error: function() {
							alert("การทำงานผิดพลาดภายในโปรแกรม");
						}
					});
					
				}
				return false;
			});
			
			function chkPost(post) {
				if (post == '') {
					document.getElementById("msg-post").innerHTML = "<label class='msg-error'>กรุณากรอก 'ข้อความ'</label>";
					return false;
				}
				return true;
			}
			
			$(document).on('focus', '#post', function() {
				document.getElementById("msg-post").innerHTML = '';
			});
		</script>
	</div>
</body>
</html>