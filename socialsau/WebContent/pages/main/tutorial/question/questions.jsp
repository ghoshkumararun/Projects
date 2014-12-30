<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/pages/main/common/header-main.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/highcharts.js"></script>
<style type="text/css">
	table, th, td {
		text-align: center;
	}
</style>
</head>
<body>
	<%@ include file="/pages/main/common/navbar.jsp" %>
	<div class="container">
		<div class="row section-main">
			<div class="nav sidebar-section">
				<%@ include file="/pages/main/common/tutorial-sidebar.jsp" %>
			</div>
			<div class="content-layout">
				<div class="content">
					<p style="font-size: 24px; font-weight: bold;" class="text-muted">Question Library.</p>
					<c:if test="${empty questions}">
						<p class="text-danger" style="font-size: 24px; font-weight: bold; margin: 10px;">ไม่พบคำถาม &nbsp;<span class="glyphicon glyphicon-ban-circle"></span></p>
					</c:if>
				
					<c:if test="${not empty questions}">
							<div class="form-horizontal">
								<div class="form-group">
									<span class="col-md-12">Number of Questions &nbsp;&nbsp; ${countQuestion} &nbsp;&nbsp; (Questions) </span>
								</div>
								<div class="form-group">
									<div class="col-md-2">
										<s:form action="questionStart" namespace="/main">
											<input type="hidden" name="tutorialId" value="${tutorial.tutorialId}">
											<c:if test="${countQuestion < 10}">
												<input type="submit" value="Not Start" class="btn btn-success btn-block" disabled>
											</c:if>
											<c:if test="${countQuestion >= 10}">
												<input type="submit" value="Start" class="btn btn-success btn-block">
											</c:if>
										</s:form>
									</div>
									<div class="col-md-2">
										<s:form action="questionsAnswer" namespace="/main">
											<input type="hidden" name="tutorialId" value="${tutorial.tutorialId}">
											<input type="submit" value="Answer" class="btn btn-primary btn-block">
										</s:form>
									</div>
								</div>
							</div>
					</c:if>
				
					<c:if test="${not empty scores}">
						<hr>
						<div class="form-horizontal">
							<div class="form-group">
								<div class="col-md-8">
									<p class="text-muted" style="font-size: 24px; font-weight: bold; text-align: right;"><strong>Summary</strong> &nbsp; <span class="glyphicon glyphicon-stats"></span></p>
								</div>
							</div>
							<div class="form-group">
								<span class="col-md-offset-8 col-md-4">
									<input type="button" class="btn btn-danger btn-block btn-chartLine" value="Line Chart">
									<input type="button" class="btn btn-warning btn-block btn-chartColumn" value="Line Column">
								</span>
							</div>
							<br>
							<div id="chart-percent" style="width: 100%; height: 400px; margin-bottom: 20px;"></div>
						</div>
						
						<%-- <table class="table table-hover">
							<thead style="background-color: #ddd;">
								<tr>
									<th>No. <span class="text-success">(ครั้งที่)</span></th>
									<th>Score <span class="text-success">(Correct)</span></th>
									<th>Percent</th>
									<th>Date time</th>
								</tr>
							</thead>
							<tbody>
								<fmt:setLocale value="th_TH"/>
								<c:forEach var="score" items="${scores}">
									<tr>
										<td>${score.testNo}</td>
										<td><label class="text-success" style="font-weight: bold;">${score.scoreCorrect}</label> / ${score.maxScore}</td>
										<td>${score.percentCorrect}%</td>
										<td><fmt:formatDate pattern="d MMMM yyyy เวลา HH:mm:ss" value="${score.scoreCreate}" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table> --%>
						
					</c:if>
				
				</div>
				
				<!-- <div id="dialog" style="display: none;">
					<p>Loading Page</p>
				</div> -->
				<script type="text/javascript">
					$(function() {
						// first load
						loadData('line');
					});
					
					// HighCharts via click event
					$(document).on('click', '.btn-chartLine', function() {
						loadData('line');
					});
					$(document).on('click', '.btn-chartColumn', function() {
						loadData('column');
					});
					
					function loadData(typeChart) {
							$.ajax({
								url: "<s:url action='loadChartJSON' namespace='/json' />",
								data: {userId: '${sessionScope.user.userId}', tutorialId: '${tutorial.tutorialId}'},
								type: 'POST',
								dataType: 'JSON',
								success: function(data) {
									loadChart(data, typeChart);
								}
							});
					}
					
					function loadChart(data, typeChart) {
						
						var categories = []; // แกน x ข้อความ
						var seriesData = []; // ข้อมูล
						
						$.each(data, function(idx, obj) {
							categories.push('No. ' + obj.testNo);
							seriesData.push(obj.percentCorrect);
						});
						
						console.log("categories : " + categories);
						console.log("seriesData : " + seriesData);
						
						// JSON.stringify(jsonObj) แปลง Obj JSON เป็น JSON string
						// JSON.parse(jsonStr) แปลง JSON String ---> JSON Obj
						
						$('#chart-percent').highcharts({
							title: {
				                text: '',
				            },
				            yAxis: { min: 0, max: 100 },
				            xAxis: { categories: categories }, 
					        chart: {
					        	type: typeChart,
					        	marginTop: 30,
					        	marginRight: 50,
					        	marginBottom: 90,
					        	marginLeft : 75
					        },
					        series: [{
					        	name: 'Score (Percent)', 
					        	data: seriesData
					        }]
					    });
					}
					
				</script>
			</div>
		</div>
	</div>
</body>
</html>