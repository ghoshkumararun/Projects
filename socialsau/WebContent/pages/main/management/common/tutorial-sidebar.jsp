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
	<li class="active"><a href="viewTutorial?tutorialId=${tutorial.tutorialId}">Tutorial ID: &nbsp; ${tutorial.tutorialId}</a></li>
	<li class="sau-item"><a href="editTutorial?tutorialId=${tutorial.tutorialId}"><span class="glyphicon glyphicon-edit"></span> &nbsp;Edit Tutorial</a></li>
	<li class="sau-item"><a href="../chapter/allChapter?tutorialId=${tutorial.tutorialId}"><span class="glyphicon glyphicon-file"></span> &nbsp;Chapter</a></li>
	<li class="sau-item"><a href="../question/allQuestion?tutorialId=${tutorial.tutorialId}"><span class="glyphicon glyphicon-question-sign"></span> &nbsp;Question</a></li>
	<li class="sau-item"><a href="../video/allVideo?tutorialId=${tutorial.tutorialId}"><span class="glyphicon glyphicon-play-circle"></span> &nbsp;Video</a></li>
	<%-- <li class="sau-item"><a href="javascript:void(0);" onclick="deleteTutorial('${tutorial.tutorialId}')"><span class="glyphicon glyphicon-trash"></span> &nbsp;Delete Tutorial</a></li> --%>
	<li class="active"><a href="../tutorial/all">Back ...</a></li>
</ul>