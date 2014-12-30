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
</head>
<body>
	<%@ include file="/pages/main/common/navbar.jsp" %>
	
	<div class="container">
		<div class="row">
			<div class="section-friend">
				<form class="form-horizontal">
					<div class="form-group">
						<span class="col-md-1 control-label"><label for="search-friend">Search</label></span>
						<div class="col-md-4">
							<input type="text" id="search-friend" name="search-friend" class="form-control" placeholder="Enter Firstname, URL profile or Email ?">
						</div>
					</div>
					
					<hr>
					<div class="form-group" style="margin-left: 75px !important;">
						<c:set var="status" value="false" />
						<c:forEach var="friend" items="${friends}">
							<c:if test="${not empty friend.imageProfile}">
								<!-- ชื่อ attr จาก var ที่กำหนด forEach -->
								<s:a action="%{#attr.friend.urlProfile}" namespace="/profile">
									<img src="${pageContext.request.contextPath}/resources/photos-profile/${friend.imageProfile}" title="${friend.firstname} ${friend.lastname}" width="200" height="200" class="img-rounded">
								</s:a>
							</c:if>
							<c:if test="${empty friend.imageProfile}">
								<s:a action="%{#attr.friend.urlProfile}" namespace="/profile">
									<img src="${pageContext.request.contextPath}/resources/images/no-image-1.png" width="200" title="${friend.firstname} ${friend.lastname}" height="200" class="img-rounded">
								</s:a>
							</c:if>
						</c:forEach>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			$(document).tooltip();
			searchAutocomplete();
		});
		
		function searchAutocomplete() {
			$('#search-friend').autocomplete({
				source: function(request, response) {
					var userId = "${sessionScope['user'].userId}";
					$.ajax({
						url: '<s:url action="searchFriendsJSON" namespace="/json" />', 
						data: {userId: userId, keywordsSearch: request.term}, 
						type: 'post',
						dataType: 'json',
						success: function(data) {
							response($.map(data, function(obj) {
								return {
		                            label: obj.email + " (" + obj.firstname + " " + obj.lastname + ")",
		                            value: obj.urlProfile
		                        };
							}));
						}
					});
				}, 
				select: function( event, ui ) {
					// ui.item.value = ค่า value
					var profile = ui.item.value;
					window.location = "${pageContext.request.contextPath}/profile/" + profile;
				}, 
				minLength: 3
			});
		}
	</script>
</body>
</html>