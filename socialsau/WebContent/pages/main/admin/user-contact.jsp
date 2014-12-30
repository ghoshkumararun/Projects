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
<c:if test="${sessionScope['user'].role != 'admin'}">
	<script type="text/javascript">
			alert("โปรดทำการล็อกอิน");
			window.location = '${pageContext.request.contextPath}';
	</script>
</c:if>
<style type="text/css">
	table, th, td {
		text-align: center;
	}
</style>
</head>
<body>
	<%@ include file="/pages/main/common/navbar.jsp" %>
	
	<div class="container">
		<div class="row">
			<div class="section-main">
				<div class="form-horizontal">
					<div class="form-group">
						<%-- <span class="col-md-1 control-label"><label for="search-problem">Search</label></span>
						<div class="col-md-4">
							<input type="text" id="search-user" name="search-user" class="form-control" placeholder="Enter First name, Last name or Email ?">
						</div> --%>
						<span class="col-md-3 text-muted" style="font-size: 24px; font-weight: bold; margin-top: 20px;">Personal Data</span>
						<div class="col-md-2" style="margin-top: 20px;">
							<select id="status" class="form-control">
								<option value="disabled" selected>Disabled</option>
								<option value="enabled">Enabled</option>
							</select>
						</div>
					</div>
					<hr>
					<table class="table table-hover">
						<thead style="background-color: #ddd;">
							<tr>
								<th style="width: 5%;">No.</th>
								<th style="width: 40%;">Contact Detail</th>
								<th style="width: 20%;">Email</th>
								<th style="width: 20%;">Time</th>
								<th style="width: 10%;">Status</th>
								<th style="width: 5%;">Answer</th>
							</tr>
						</thead>
						<tbody class="table-contact">
							<c:forEach var="contact" items="${contacts}" varStatus="myStatus">
								<tr title="${contact.contactFirstname} ${contact.contactLastname}">
									<td>${myStatus.index + 1}</td>
									<td style="text-align: left;">${contact.contactDetail}</td>
									<td>${contact.contactEmail}</td>
									<td><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss"  value="${contact.contactCreate}" /></td>
									<td class="text-success">${contact.contactStatus}</td>
									<td><a href="answerQuestion?contactId=${contact.contactId}"><span class="glyphicon glyphicon-eye-open"></span></a></td>
								</tr>
							</c:forEach>
							
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			$(document).tooltip();
			// searchAutocomplete();
		});
		$(document).on('change', '#status', function() {
			// alert($(this).val());
			$.getJSON("<s:url action='loadContactAdmin' namespace='/json' />", {status: $(this).val()}, function(data) {
				$('.table-contact').html('');
				// console.log(data);
				// check null
				if ($.isEmptyObject(data)) {
					$('.table-contact').html('<tr><td colspan="6"><h4 class="text-muted">ไม่พบข้อมูล</h4></td></tr>');
				}
				$.each(data, function(idx, obj) {
					$('.table-contact').append(
						'<tr>' +
							'<td>' + (idx + 1) + '</td>' +
							'<td style="text-align: left;">' + obj.contactDetail + '</td>' +
							'<td>' + obj.contactEmail + '</td>' +
							'<td>' + obj.contactCreateStr  + '</td>' +
							'<td class="text-success">' + obj.contactStatus  + '</td>' +
							'<td>' + '<a href="answerQuestion?contactId=' + obj.contactId + '"><span class="glyphicon glyphicon-eye-open"></span></a>' + '</td>' + 
						'</tr>'
					);
				});
			});
		});
		/* function searchAutocomplete() {
			$('#search-user').autocomplete({
				minLength: 3,
				source: function(request, response) {
					$.ajax({
						url: '<s:url action="searchContactUserJSON" namespace="/json" />', 
						data: {keywordsSearch: request.term}, 
						type: 'post',
						dataType: 'json',
						success: function(data) {
							response($.map(data, function(item) {
								return {
		                            label: item.contactEmail + " (" + item.contactFirstname + " " + item.contactLastname + ")",
		                            value: item.contactId
		                        };
							}));
						}
					});
				}, 
				select: function( event, ui ) {
					// ui.item.value = ค่า value
					var contactId = ui.item.value;
					window.location = '<s:url action="answerQuestion" namespace="/admin" />' + '?contactId=' + contactId;
				}
			});
		} */
	</script>
</body>
</html>