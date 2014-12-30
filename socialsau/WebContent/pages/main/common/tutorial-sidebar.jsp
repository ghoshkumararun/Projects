<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<li class="active">
		<s:form id="frmTutorial" action="tutorial" namespace="/main">
			<input type="hidden" id="tutorialId" name="tutorialId">
		</s:form>
		<a href="javascript:void(0);" data-id="${tutorial.tutorialId}" class="url_tutorialId"><span class="glyphicon glyphicon-home"></span> &nbsp; ${tutorial.tutorialName}</a>
	</li>
	
	<s:form id="frmChapter" action="chapter" namespace="/main">
		<input type="hidden" id="tutorialIdChapter" name="tutorialId">
		<input type="hidden" id="chapterId" name="chapterId">
	</s:form>
	<c:forEach var="chapter" items="${chapters}">
		<li class="sau-item"><a href="javascript:void(0);" data-tutorial="${tutorial.tutorialId}" data-chapter="${chapter.chapterId}" class="url_chapterId">${chapter.chapterName}</a></li>
	</c:forEach>
	
	<s:form id="frmVideo" action="video" namespace="/main">
		<input type="hidden" id="tutorialIdVideo" name="tutorialId">
	</s:form>
	<li class="active"><a href="javascript:void(0);" data-id="${tutorial.tutorialId}" class="url_videoId"><span class="glyphicon glyphicon-play-circle"></span> &nbsp; Videos</a></li>
	
	<s:form id="frmQuestion" action="questions" namespace="/main">
		<input type="hidden" id="tutorialIdQuestion" name="tutorialId">
	</s:form>
	<li class="active"><a href="javascript:void(0);" data-id="${tutorial.tutorialId}" class="url_questionId"><span class="glyphicon glyphicon-home"></span> &nbsp; Questions</a></li>
</ul>

<script type="text/javascript">
	$(document).on('click', '.url_tutorialId', function() {
		var tutorialId = $(this).data('id');
		$('#tutorialId').val(tutorialId);
		$('#frmTutorial').submit();
	});
	$(document).on('click', '.url_chapterId', function() {
		var tutorialId = $(this).data('tutorial');
		var chapterId = $(this).data('chapter');
		$('#tutorialIdChapter').val(tutorialId);
		$('#chapterId').val(chapterId);
		$('#frmChapter').submit();
	});
	$(document).on('click', '.url_videoId', function() {
		var tutorialId = $(this).data('id');
		$('#tutorialIdVideo').val(tutorialId);
		$('#frmVideo').submit();
	});
	$(document).on('click', '.url_questionId', function() {
		var questoinId = $(this).data('id');
		$('#tutorialIdQuestion').val(questoinId);
		$('#frmQuestion').submit();
	});
</script>