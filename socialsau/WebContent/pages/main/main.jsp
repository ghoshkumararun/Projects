<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/pages/main/common/header-main.jsp" %>
</head>
<body>
	<%@ include file="/pages/main/common/navbar.jsp" %>
	<div class="section-tutorial-bgpattern">
		<div class="container">
			<div class="content-main">
				<div class="form-group">
					<div class="col-sm-9">
						<h1 style="color: white; text-align: right;"><strong>Your Welcome WebSite.</strong> </h1>
						<h3 style="color: white;">You can study <span id="tutorials" class="label lbl-tutorial" style="font-size: 20px; display: inline-block; width: 300px; background-color: white; color: black;">Tutorials</span> in your website.</h3>
					</div>
					<div class="col-md-3">
						<!-- อิสระจากเนื้อหาปกติ position  absolute -->
						<span class="glyphicon glyphicon-cloud" style="font-size: 200px; color: white; position: absolute; z-index: 1"></span>
						<h1 style="color: #5cb85c; z-index: 2; position: absolute; padding: 60px 60px;"><strong>SAU</strong></h1>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="container">
		<div class="row">
			<div class="form-group">
				<div class="col-md-3">
					<h1 class="text-center"><strong><span class="label label-success" style="border-radius: 35px;">Learning</span></strong></h1>
				</div>
				<div class="col-md-1">
					<h1 class="text-center text-success"><strong style="vertical-align: middle;"><span class="glyphicon glyphicon-retweet"></span></strong></h1>
				</div>
				<div class="col-md-3">
					<h1 class="text-center"><strong><span class="label label-success" style="border-radius: 35px;">Testing</span></strong></h1>
				</div>
				<div class="col-md-1">
					<h1 class="text-center text-success"><strong style="vertical-align: middle;"><span class="glyphicon glyphicon-retweet"></span></strong></h1>
				</div>
				<div class="col-md-4">
					<h1 class="text-center"><strong><span class="label label-success" style="border-radius: 35px;">Report Chart</span></strong></h1>
				</div>
			</div>
		</div>
	</div>
	<div class="border-dynamic"></div>
	<div class="container">
		<div class="row">
			<h1 style="margin-bottom: 20px;">Tutorials <strong>Library</strong></h1>
			
			<div class="form-horizontal">
				<div class="form-group">
				
					<!-- Group -->
					<c:forEach var="group" items="${groups}">
						<div class="col-md-4">
							<div class="panel panel-success">
								<div class="panel-heading">
									<h4>${group.groupName}</h4>
								</div>
								<div class="panel-body">
									<ul>
										<!-- Tutorial -->
										<c:forEach var="tutorial" items="${tutorials}">
											<c:if test="${tutorial.groupId == group.groupId}">
												<li style="list-style: none;">
													<a href="javascript:void(0);" data-id="${tutorial.tutorialId}" class="url_tutorialId">${tutorial.tutorialName}</a>
												</li>
											</c:if>
										</c:forEach>
									</ul>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
				
				<%-- <c:set var="count" value="1" />
				<c:forEach var="group" items="${groups}" >
					<c:if test="${count == 1}">
						<div class="form-group">
					</c:if>
					
					<!-- Group grid 4, 4, 4 -->
					<div class="col-md-4">
						<div class="panel panel-success">
							<div class="panel-heading">
								<h3>${group.groupName}</h3>
							</div>
							<div class="panel-body">									
								<ul>
									<c:forEach var="tutorial" items="${tutorials}">
										<c:if test="${tutorial.groupId == group.groupId}">
											<li style="list-style: none;">
												<a href="javascript:void(0);" data-id="${tutorial.tutorialId}" class="url_tutorialId">${tutorial.tutorialName}</a>
											</li>
										</c:if>
									</c:forEach>
								</ul>
							</div>
						</div>
					</div>
					
					<!-- 1 แถว แสดง 3 group แล้วกำหนดตัว couter ให้เท่ากับ 0 + 1 = 1 เป็นตัวนับแถวใหม่ -->
					<c:if test="${count == 3}">
						</div>
						<c:set var="count" value="0" />
					</c:if>
					<c:set var="count" value="${count + 1}" />
				</c:forEach> --%>
				
			</div>
		</div>
	</div>
	
	<s:form id="frmTutorial" action="tutorial" namespace="/main">
		<input type="hidden" id="tutorialId" name="tutorialId">
	</s:form>
	
	<script type="text/javascript">
		$(document).on('click', '.url_tutorialId', function() {
			var tutorialId = $(this).data('id');
			$('#tutorialId').val(tutorialId);
			$('#frmTutorial').submit();
		});
	</script>
</body>
</html>