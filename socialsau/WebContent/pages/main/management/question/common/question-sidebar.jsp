<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div style="text-align: center; margin: 10px;">
	<c:if test="${not empty tutorial.tutorialImage}">
		<img id="image-profile" src="${pageContext.request.contextPath}/resources/photos-tutorial/${tutorial.tutorialImage}" width="150" height="150" class="img-rounded">
	</c:if>
	<c:if test="${empty tutorial.tutorialImage}">
		<img src="${pageContext.request.contextPath}/resources/images/no-image-1.png" width="150" height="150" class="img-thumbnail">
	</c:if>
</div>
<ul class="nav nav-pills nav-stacked">
	<li class="active"><a href="javascript:void(0)">Question Management</a></li>
	<li class="sau-item"><a href="../question/allQuestion?tutorialId=${tutorial.tutorialId}"><span class="glyphicon glyphicon-plus-sign"></span> &nbsp;Question Library</a></li>
	<li class="sau-item"><a href="../question/createQuestion?tutorialId=${tutorial.tutorialId}"><span class="glyphicon glyphicon-floppy-saved"></span> &nbsp;Create Question</a></li>
	<li class="active"><a href="../tutorial/viewTutorial?tutorialId=${tutorial.tutorialId}">Back Tutorial</a></li>
</ul>