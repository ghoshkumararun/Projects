<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/pages/main/common/header-main.jsp" %>
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
						<div class="form-group" style="margin-left: 40px !important;">
							<c:forEach var="photo" items="${photos}">
								<img src="${pageContext.request.contextPath}/resources/photos/${photo.userId}/${photo.photoUrl}" width="200" height="200" class="img-rounded img-user" data-id="${photo.photoId}" style="cursor: pointer;">
							</c:forEach>
						</div>
						<s:form id="frmPhoto" action="profilePhotoStep2" namespace="/setting"><input type="hidden" id="photoId" name="photoId"></s:form>
					</div>
					
					<script type="text/javascript">
						$(document).on('click', '.img-user', function() {
							var photoId = $(this).data('id');
							$('#photoId').val(photoId);
							$('#frmPhoto').submit();
						});
					</script>
				</div>
			</div>
		</div>
	</div>
</body>
</html>